package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.*;

public class Window extends JFrame {


    private JPanel mainPanel;
    private JButton createButton;
    private JPanel settingsPanel;
    private JPanel paintGraphPaint;

    private JRadioButton checkVertRadioButton;
    private JRadioButton checkRezRadioButton;
    private JRadioButton checkHorizRadioButton;
    private JSpinner spinner1;
    private JRadioButton checkBatRadioButton;
    public ArrayList graphList = new ArrayList();

    public Window() {

        setTitle("Хныкин, таск 8.23");
        //this.setContentPane(paintGraphPaint);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        //this.setVisible(true);
        Container pane = getContentPane();
        MyPanel panel1 = new MyPanel();
        pane.add(panel1);
        pane.add(settingsPanel, BorderLayout.WEST);

        //pane.add(paintGraphPaint);
        //pane.add(settingsPanel);
        // paintGraphPaint.add(paintGraphPaint);
        //Box leftBox = Box.createVerticalBox();
        //mainPanel.add(settingsPanel);
        //paintGraphPaint.add(settingsPanel);

        int countCheck = 0;

        createButton.addActionListener(new ActionListener() {
            MyPanel mp = new MyPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                MyGraph graph = new MyGraph(graphList.size()); // задается размер графа
                graph.print(); // выводим граф
                //mp.check();
            }
        });
    }


    class MyPanel extends JPanel {
        private ArrayList rectRez;
        private Rectangle2D currentRez;

        private ArrayList rectHoriz;
        private Rectangle2D currentHoriz;

        private ArrayList rectVert;
        private Rectangle2D currentVert;

        private ArrayList bat;
        private Rectangle2D currentBat;

        BufferedImage imgRez, imgHoriz, imgVert, imgBat;

        public MyPanel() {
            rectRez = new ArrayList();
            currentRez = null;

            rectHoriz = new ArrayList();
            currentHoriz = null;

            rectVert = new ArrayList();
            currentVert = null;

            bat = new ArrayList();
            currentBat = null;

            addMouseListener(new MyMouse());
            addMouseMotionListener(new MyMove());
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            Graphics2D g3 = (Graphics2D) g;

            Graphics2D g4 = (Graphics2D) g;

            Graphics2D g5 = (Graphics2D) g;

            //  if (checkRezRadioButton.isSelected()) {
            for (int i = 0; i < rectRez.size(); i++) {
                {
                    g2.setComposite(AlphaComposite.SrcOver);
                    try {
                        imgRez = ImageIO.read(new File("textureRez.png"));
                        g2.setPaint(new TexturePaint(imgRez, new Rectangle2D.Double(0, 0, 100, 29)));
                        g2.fill((Rectangle2D) rectRez.get(i));
                    } catch (IOException exception) {

                    }
                }
            }
            //  }

            //if (checkHorizRadioButton.isSelected()) {
            for (int i = 0; i < rectHoriz.size(); i++) {
                {
                    g3.setComposite(AlphaComposite.SrcOver);
                    try {
                        imgHoriz = ImageIO.read(new File("textureHorizConnecrt.png"));
                        g3.setPaint(new TexturePaint(imgHoriz, new Rectangle2D.Double(0, 0, 100, 10)));
                        g3.fill((Rectangle2D) rectHoriz.get(i));
                    } catch (IOException exception) {

                    }
                }
            }
            //  }

            //if (checkVertRadioButton.isSelected()) {
            for (int i = 0; i < rectVert.size(); i++) {
                {
                    g4.setComposite(AlphaComposite.SrcOver);
                    try {
                        imgVert = ImageIO.read(new File("textureVertConnecrt.png"));
                        g4.setPaint(new TexturePaint(imgVert, new Rectangle2D.Double(0, 0, 10, 100)));
                        g4.fill((Rectangle2D) rectVert.get(i));
                    } catch (IOException exception) {

                    }
                }
            }
            // }

            for (int i = 0; i < bat.size(); i++) {
                {
                    g4.setComposite(AlphaComposite.SrcOver);
                    try {
                        imgBat = ImageIO.read(new File("textureBat.png"));
                        g4.setPaint(new TexturePaint(imgBat, new Rectangle2D.Double(0, 0, 10, 100)));
                        g4.fill((Rectangle2D) bat.get(i));
                    } catch (IOException exception) {

                    }
                }
            }

        }

        public void add(Point2D p) {

            if (checkRezRadioButton.isSelected()) {
                currentRez = new Rectangle2D.Double(p.getX() - 50, p.getY() - 14.5, 100, 29);
                rectRez.add(currentRez);
                graphList.add(currentRez);
            } else {
                repaint();
            }


            if (checkHorizRadioButton.isSelected()) {
                currentHoriz = new Rectangle2D.Double(p.getX() - 50, p.getY() - 14.5, 100, 5);
                rectHoriz.add(currentHoriz);
            } else {
                repaint();
            }


            if (checkVertRadioButton.isSelected()) {
                currentVert = new Rectangle2D.Double(p.getX() - 50, p.getY() - 14.5, 5, 100);
                rectVert.add(currentVert);
                //repaint();
            } else {
                repaint();
            }

            if (checkBatRadioButton.isSelected()) {
                currentBat = new Rectangle2D.Double(p.getX() - 50, p.getY() - 14.5, 100, 100);
                bat.add(currentBat);
                //repaint();
            } else {
                repaint();
            }


        }

        public Rectangle2D find(Point2D p) {

            if (checkRezRadioButton.isSelected()) {
                for (int i = 0; i < rectRez.size(); i++) {
                    Rectangle2D a = (Rectangle2D) rectRez.get(i);
                    if (a.contains(p)) return a;
                }
            }

            if (checkHorizRadioButton.isSelected()) {
                for (int i = 0; i < rectHoriz.size(); i++) {
                    Rectangle2D b = (Rectangle2D) rectHoriz.get(i);
                    if (b.contains(p)) return b;
                }
            }

            if (checkVertRadioButton.isSelected()) {
                for (int i = 0; i < rectVert.size(); i++) {
                    Rectangle2D c = (Rectangle2D) rectVert.get(i);
                    if (c.contains(p)) return c;
                }
            }

            if (checkBatRadioButton.isSelected()) {
                for (int i = 0; i < bat.size(); i++) {
                    Rectangle2D d = (Rectangle2D) bat.get(i);
                    if (d.contains(p)) return d;
                }
            }

            return null;
        }

        public void remove(Rectangle2D e) {

            if (checkRezRadioButton.isSelected()) {
                if (e == null) return;
                if (e == currentRez) currentRez = null;
                rectRez.remove(e);
                graphList.remove(e);
            }

            if (checkHorizRadioButton.isSelected()) {
                if (e == null) return;
                if (e == currentHoriz) currentHoriz = null;
                rectHoriz.remove(e);
            }

            if (checkVertRadioButton.isSelected()) {
                if (e == null) return;
                if (e == currentVert) currentVert = null;
                rectVert.remove(e);
            }

            if (checkBatRadioButton.isSelected()) {
                if (e == null) return;
                if (e == currentBat) currentBat = null;
                bat.remove(e);
            }

            repaint();
        }

        class MyMouse extends MouseAdapter {
            public void mousePressed(MouseEvent event) {

                if (checkRezRadioButton.isSelected()) {
                    currentRez = find(event.getPoint());
                    if (currentRez == null) add(event.getPoint());
                }

                if (checkHorizRadioButton.isSelected()) {
                    currentHoriz = find(event.getPoint());
                    if (currentHoriz == null) add(event.getPoint());
                }

                if (checkVertRadioButton.isSelected()) {
                    currentVert = find(event.getPoint());
                    if (currentVert == null) add(event.getPoint());
                }

                if (checkBatRadioButton.isSelected()) {
                    currentBat = find(event.getPoint());
                    if (currentBat == null) add(event.getPoint());
                }
            }

            public void mouseClicked(MouseEvent event) {

                if (checkRezRadioButton.isSelected()) {
                    if (event.getClickCount() >= 2) {
                        currentRez = find(event.getPoint());
                        if (currentRez != null) remove(currentRez);
                    }
                }

                if (checkHorizRadioButton.isSelected()) {
                    if (event.getClickCount() >= 2) {
                        currentHoriz = find(event.getPoint());
                        if (currentHoriz != null) remove(currentHoriz);
                    }
                }

                if (checkVertRadioButton.isSelected()) {
                    if (event.getClickCount() >= 2) {
                        currentVert = find(event.getPoint());
                        if (currentVert != null) remove(currentVert);
                    }
                }

                if (checkBatRadioButton.isSelected()) {
                    if (event.getClickCount() >= 2) {
                        currentBat = find(event.getPoint());
                        if (currentBat != null) remove(currentBat);
                    }
                }
            }
        }

        private class MyMove implements MouseMotionListener {

            public void mouseMoved(MouseEvent event) {
                if (find(event.getPoint()) == null) {
                    setCursor(Cursor.getDefaultCursor());
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }

            public void mouseDragged(MouseEvent event) {

                if (checkRezRadioButton.isSelected()) {
                    if (currentRez != null) {
                        currentRez.setFrame(event.getX() - 50, event.getY() - 14.5, 100, 29);
                        repaint();
                    }
                }

                if (checkHorizRadioButton.isSelected()) {
                    if (currentHoriz != null) {
                        currentHoriz.setFrame(event.getX() - 50, event.getY() - 2.5, 100, 5);
                        repaint();
                    }
                }

                if (checkVertRadioButton.isSelected()) {
                    if (currentVert != null) {
                        currentVert.setFrame(event.getX() - 2.5, event.getY() - 50, 5, 100);
                        repaint();
                    }
                }

                if (checkBatRadioButton.isSelected()) {
                    if (currentBat != null) {
                        currentBat.setFrame(event.getX() - 50, event.getY() - 14.5, 100, 100);
                        repaint();
                    }
                }

                repaint();

            }
        }

        public void check(Point2D p) {

            for (int i = 0; i < rectRez.size(); i++) {
                Rectangle2D rez = (Rectangle2D) rectRez.get(i);
                for (int j = 0; j < rectVert.size(); j++) {
                    Rectangle2D vert = (Rectangle2D) rectVert.get(i);
                }
            }
        }

    }
}



