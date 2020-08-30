package com.IvanEndorphin;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import static com.IvanEndorphin.Digital_tree.*;

public class PrimitivesAppMy {
    public static int FieldWidth = 700;
    public static int FieldHeight = 300;

    private JFrame frame;
    public DrawerPanel drawerPanel;

    public static void main(String[] args) {
        PrimitivesAppMy app = new PrimitivesAppMy();
        app.start();
    }

    public PrimitivesAppMy(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FieldWidth, FieldHeight);

        drawerPanel = new DrawerPanel();
        drawerPanel.setSize(FieldWidth, FieldHeight);

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.setSize(400, 30);

        mainContainer.add(bottomPanel, BorderLayout.SOUTH);
        mainContainer.add(drawerPanel, BorderLayout.CENTER);









    }

    /*private void onAddClick() {
        String selected = shapesList.getSelectedItem().toString();

        if(selected.equals("Rectangle")){
            drawerPanel.addRectangle();
        }else if(selected.equals("Circle")){
            drawerPanel.addCircle();
        }

        drawerPanel.repaint();
    }*/

    /*private void onAnimateClick() {
        drawerPanel.animating = !drawerPanel.animating;
        animateButton.setLabel(drawerPanel.animating?"Stop":"Animate");
    }*/

    public void start() {
        frame.setVisible(true);
        startTimer();
    }

    public void makeField(){
        for (int j = 0, ycord = 20; j < FieldRect.length ; j++, ycord+=20) {
                 for (int i = 0, xcord = 20; i < FieldRect[0].length ; i++,xcord+=20) {

            drawerPanel.addRectangle(ycord, xcord, Color.GRAY, i, j);

        }

        }


        drawerPanel.repaint();

    }
      public void makeField2(){


          drawerPanel.addRectangle(20, 20, Color.GRAY, 0, 0);
          drawerPanel.addRectangle(20, 40, Color.GRAY, 1, 0);




          drawerPanel.repaint();

    }


    private void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(drawerPanel.animating) {
                    drawerPanel.nextState();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 10);
    }


    void repaint(){
        drawerPanel.repaint();

    }
}