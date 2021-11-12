package com.mhz.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    private static int WIDTH=20, HEIGHT = 20;

    private int x, y;
    private Dir dir; //位置

    private boolean live = true;
    TankFrame tankFrame=null;

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }
    public void paint(Graphics g){/*Graphics 相当于系统的画笔*/
        if(!live){
            tankFrame.bullets.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        move();

    }
    private void move() {

        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WITH || y > TankFrame.GAME_HEIGHT) live = false;
    }
}
