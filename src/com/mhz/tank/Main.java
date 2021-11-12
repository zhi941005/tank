package com.mhz.tank;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame t = new TankFrame();
        while (true) {
            Thread.sleep(50);//歇50毫秒
            t.repaint();
        }
    }
}
