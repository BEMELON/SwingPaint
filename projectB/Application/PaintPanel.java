package SwingPaint.projectB.Application;

import SwingPaint.projectB.SwingAPI.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintPanel extends JPanel {
    private FigureBox figurebox;
    private Figure type = Figure.RECT;
    private Color color = Color.black;

    class MyMouseListener implements MouseListener {
        private Point start, end;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            start = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            end = e.getPoint();
            if(!start.equals(end)) {
                dragEvent(start, end);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public PaintPanel() {
        figurebox = new FigureBox();

        initMenuBar();
        initToolBar();

        addMouseListener(new MyMouseListener());
    }

    private void dragEvent(Point start, Point end) {

    }

    public void setFigure(Figure type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void initToolBar() {
        myToolBar = new MyToolBar();

        //TODO toICON
        MyButton rect = new MyButton("RECT");
            rect.addActionListener(new MyActionListener((e) -> setFigure(FIGURE.RECT)));
            myToolBar.add(rect);
        MyButton oval = new MyButton("OVAL");
            oval.addActionListener(new MyActionListener((e) -> setFigure(FIGURE.OVAL)));
            myToolBar.add(oval);
        MyButton line = new MyButton("LINE");
            line.addActionListener(new MyActionListener((e) -> setFigure(FIGURE.LINE)));
            myToolBar.add(line);
        MyButton fill = new MyButton("채우기");
            myToolBar.add(fill);
        MyButton red = new MyButton("RED");
            red.addActionListener(new MyActionListener((e) -> setColor(Color.red)));
            myToolBar.add(red);
        MyButton black = new MyButton("BLACK");
            black.addActionListener(new MyActionListener((e) -> setColor(Color.black)));
            myToolBar.add(black);
        MyButton yellow = new MyButton("YELlOW");
            yellow.addActionListener(new MyActionListener((e) -> setColor(Color.yellow)));
            myToolBar.add(yellow);
        add(myToolBar);
    }

    private void initMenuBar() {
        myMenuBar = new MyMenuBar();

        MyMenu figure = new MyMenu("도형");
        MyMenuItem rect = new MyMenuItem("사각형");
            rect.addActionListener(new MyActionListener((e) -> setFigure(FIGURE.RECT)));
            figure.add(rect);
        MyMenuItem oval = new MyMenuItem("타원");
            oval.addActionListener(new MyActionListener((e) -> setFigure(FIGURE.OVAL)));
            figure.add(oval);
        MyMenuItem line = new MyMenuItem("선분");
            line.addActionListener(new MyActionListener((e) -> setFigure(FIGURE.LINE)));
            figure.add(line);

        MyMenu color = new MyMenu("색상");
            MyMenuItem black = new MyMenuItem("검정");
                black.addActionListener(new MyActionListener((e) -> setColor(Color.black)));
                 color.add(black);
            MyMenuItem red = new MyMenuItem("빨강");
                red.addActionListener(new MyActionListener((e) -> setColor(Color.red)));
                color.add(red);
            MyMenuItem yellow = new MyMenuItem("노랑");
                yellow.addActionListener(new MyActionListener((e) -> setColor(Color.yellow)));
                color.add(yellow);

        myMenuBar.add(figure);
        myMenuBar.add(color);

        add(myMenuBar);
    }

    @Override
    public void paint(Graphics g) {
        myToolBar.paint(g);
        myMenuBar.paint(g);
        myFigureBox.paint(g);
    }
}
