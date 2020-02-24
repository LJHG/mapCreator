package cn.cqu.ljh;

import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.AclEntry;
import java.util.concurrent.Flow;
import java.util.jar.JarEntry;

public class drawMapFrame extends JFrame {
    private BackgroundPanel centerPanel;
    private TopPanel topPanel;
    private String imgPath;
    private int cellSize;

    public drawMapFrame(){

    }

    public void initFrame()
    {
        topPanel = new TopPanel();
        centerPanel = new BackgroundPanel(imgPath);
        setLayout(new BorderLayout());
        centerPanel.init();
        topPanel.init();
        add(topPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        setSize(1800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //位于屏幕中央


        //选择文件按钮监听
        topPanel.importImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.showOpenDialog(topPanel.importImage);
                imgPath = jf.getSelectedFile().getAbsolutePath();
                //remove原来的panel 用新的替代
                remove(centerPanel);
                centerPanel = new BackgroundPanel(imgPath);
                centerPanel.init();
                System.out.println(imgPath);
                add(centerPanel,BorderLayout.CENTER); //重新添加
                revalidate(); //repaint不行
            }
        });

        //设置grid大小监听
        topPanel.setGrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //remove原来的panel 用新的替代
                remove(centerPanel);
                int newCellSize = Integer.parseInt(topPanel.cellSize.getText());
                centerPanel = new BackgroundPanel(imgPath,newCellSize);
                centerPanel.init();
                add(centerPanel,BorderLayout.CENTER); //重新添加
                revalidate();
            }
        });

        //选择笔按钮监听
        topPanel.pen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.choosePen();
            }
        });

        //选择橡皮按钮监听
        topPanel.eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.chooseEraser();
            }
        });

        //导出地图按钮监听
        topPanel.generateMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.showSaveDialog(topPanel.generateMapButton);
                String filename = jf.getSelectedFile().getAbsolutePath();
                centerPanel.generateMap(filename);
            }
        });


    }

    public static void main(String[] args) {
        drawMapFrame frame = new drawMapFrame();
        frame.initFrame();
        frame.setVisible(true);
        frame.setResizable(false);

        System.out.println(frame.centerPanel.getSize());

        //centerpanel [width=1486,height=727]
    }

}
