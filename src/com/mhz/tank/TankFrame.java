package com.mhz.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN,this);
    List<Bullet> bullets = new ArrayList<>();
    Bullet bullet = new Bullet(300,300,Dir.DOWN,this);

    static final int GAME_WITH =800, GAME_HEIGHT = 600;

    public TankFrame(){
        setTitle("Tank War");
        setSize(GAME_WITH, GAME_HEIGHT);
        //不可编辑窗口大小
        setResizable(false);
        //窗口显示
        setVisible(true);
        //添加键盘监听
        this.addKeyListener(new MyKeyListener());

        //窗口监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //双缓冲，解决画面闪烁问题
    Image offScreenImage = null;//这张图片实际上是定义在内存里的
    @Override
    public void update(Graphics g){
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WITH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WITH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    @Override
    public void paint(Graphics g){/*Graphics 相当于系统的画笔*/

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
    }

    //键盘监听事件
    class MyKeyListener extends KeyAdapter{

        boolean bL = false;//上
        boolean bR = false;//下
        boolean bU = false;//左
        boolean bD = false;//右

        //按下键
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                 case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        //松开键
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bD && !bR && !bU) myTank.setMoving(false);
            else{
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}