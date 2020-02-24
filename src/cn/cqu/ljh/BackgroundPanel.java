package cn.cqu.ljh;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;

public class BackgroundPanel extends JPanel {

    private Image image = (Image) new ImageIcon("./white.png").getImage(); //默认设置为白色图片（其实是为了避免不设置图片时会鬼畜）
    private int cellSize;
    private int height;
    private int width;
    private Grid[][] grids;
    private char map[][];
    private boolean isPen;

    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件
    protected void paintComponent(Graphics g) {
        if(image != null)
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

    }
    public BackgroundPanel(String imgpath){

        cellSize = 20; //未指定时，设置cellsize为20
        isPen = true;
        try {
            if(imgpath != null)
                image = ImageIO.read(new File(imgpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BackgroundPanel(String imgpath, int c)
    {
        isPen = true;
        cellSize = c;
        try {
            if(imgpath != null)
                image = ImageIO.read(new File(imgpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void init() {


        height = 800/cellSize;
        width  = 1600/cellSize;
        setLayout(null);
        grids = new Grid[width][height];
        map = new char[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                map[i][j] = '0';
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grids[i][j] = new Grid(Color.lightGray, cellSize);
                grids[i][j].setLocation(i * cellSize, j * cellSize);
                grids[i][j].setOpaque(false);
                add(grids[i][j]);



//                int finalI = i;
//                int finalJ = j;
//
//                final boolean[] isPressed = {false}; //?
//
//                grids[i][j].addMouseListener(new MouseListener() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//
//                    }
//
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//
//                    }
//
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//
//                    }
//
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//
////                        grids[finalI][finalJ].setOpaque(true);
////                        grids[finalI][finalJ].setBackground(Color.GRAY);
//
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                        if(grids[finalI][finalJ].getBackground() == Color.GRAY){
//                            grids[finalI][finalJ].setOpaque(false);
//                            repaint(); //这里要用repaint
//                        }
//                    }
//                });
            }
        }

        //不对单独的控件进行监听，而是对整个Panel进行监听
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = getMousePosition().x/cellSize;
                int y = getMousePosition().y/cellSize;
                if(isPen){
                    grids[x][y].setOpaque(true);
                    grids[x][y].setBackground(Color.BLACK);
                    map[y][x] = '1';
                }
                else {
                    grids[x][y].setOpaque(false);
                    map[y][x] = '0';
                    repaint();//效果不好,但没办法
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = getMousePosition().x/cellSize;
                int y = getMousePosition().y/cellSize;
                if(isPen){
                    grids[x][y].setOpaque(true);
                    grids[x][y].setBackground(Color.BLACK);
                    map[y][x] = '1';
                }
                else{
                    grids[x][y].setOpaque(false);
                    map[y][x] = '0';
                    repaint();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //暂时不做指着变色 离开恢复的功能了，子控件会覆盖父控件的监听，在父控件里面又不好写
//                int x = getMousePosition().x/cellSize;
//                int y = getMousePosition().y/cellSize;
//                grids[x][y].setOpaque(true);
//                grids[x][y].setBackground(Color.GRAY);
//                if(!grids[x][y].isOpaque())
//                {
//
//                }

//                if(grids[x][y].isOpaque() && grids[x][y].getBackground() == Color.GRAY)
//                {
//                    grids[x][y].setOpaque(false);
//                }
            }
        });
    }

    public void setGridSize(int size){
        this.cellSize = size;
    }

    public void choosePen()
    {
        isPen = true;
    }

    public void chooseEraser()
    {
        isPen = false;
    }

    public void generateMap(String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(Integer.toString(height));
            System.out.println(height);
            fw.write("\n");
            fw.write(Integer.toString(width));
            System.out.println(width);
            fw.write("\n");
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    fw.write(map[i][j]);
                    System.out.print(map[i][j]);
                }
                System.out.println();
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
