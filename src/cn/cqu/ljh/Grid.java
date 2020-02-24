package cn.cqu.ljh;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Grid extends JLabel {
    private Color color;
    private int gridSize;


    public Grid(Color color,int gridSize){
        //设置边框
        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        setBorder(border);
        //设置背景颜色
        setOpaque(true);
        setBackground(color);
        //设置大小
        setSize(gridSize,gridSize);
    }
}
