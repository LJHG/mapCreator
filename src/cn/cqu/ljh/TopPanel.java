package cn.cqu.ljh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {
    public JButton importImage = new JButton("ImportImage");
    public JButton eraser = new JButton("Eraser");
    public JButton pen = new JButton("Pen");
    public JButton setGrid = new JButton("SetGridSize");
    public JButton generateMapButton = new JButton("GenerateMap");
    public JTextArea cellSize = new JTextArea();
    private JLabel l1 = new JLabel("GridSize(pixel):");

    public  String  imgPath;


    public void init(){
        setLayout(new FlowLayout());
        add(importImage);
//        importImage.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser jf = new JFileChooser();
//                jf.showOpenDialog(importImage);
//                imgPath = jf.getSelectedFile().getAbsolutePath();
//            }
//        });
        add(l1);
        add(cellSize);
        cellSize.setColumns(5);
        add(setGrid);
        add(pen);
        add(eraser);
        add(generateMapButton);


    }
}
