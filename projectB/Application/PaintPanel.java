package SwingPaint.projectB.Application;

import SwingPaint.projectB.SwingAPI.*;
import SwingPaint.projectB.SwingAPI.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintPanel extends JPanel {
    private final FigureBox figureBox;
    private MyToolBar myToolBar;
    private MyMenuBar myMenuBar;
    private int type = Figure.RECT;
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
        figureBox = new FigureBox();

        initMenuBar();
        initToolBar();

        addMouseListener(new MyMouseListener());
    }

    private void dragEvent(Point start, Point end) {
        switch (type) {
            case Figure.RECT:
                figureBox.add(new Rectangle(start, end)); break;
            case Figure.OVAL:
                figureBox.add(new Oval(start, end)); break;
            case Figure.LINE:
                figureBox.add(new Line(start, end)); break;
        }
    }

    public void setFigure(int type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void initToolBar() {
        myToolBar = new MyToolBar();

        //TODO toICON
        MyButton rect = new MyButton("RECT");
            rect.addActionListener(e -> setFigure(Figure.RECT));
            myToolBar.add(rect);
        MyButton oval = new MyButton("OVAL");
            oval.addActionListener(e -> setFigure(Figure.OVAL));
            myToolBar.add(oval);
        MyButton line = new MyButton("LINE");
            line.addActionListener(e -> setFigure(Figure.LINE));
            myToolBar.add(line);
        MyButton fill = new MyButton("채우기");
            myToolBar.add(fill);
        MyButton red = new MyButton("RED");
            red.addActionListener(e -> setColor(Color.red));
            myToolBar.add(red);
        MyButton black = new MyButton("BLACK");
            black.addActionListener(e-> setColor(Color.black));
            myToolBar.add(black);
        MyButton yellow = new MyButton("YELlOW");
            yellow.addActionListener(e -> setColor(Color.yellow));
            myToolBar.add(yellow);
    }

    private void initMenuBar() {
        myMenuBar = new MyMenuBar();

        MyMenu figure = new MyMenu("도형");
            MyMenuItem rect = new MyMenuItem("사각형");
                rect.addActionListener(e -> setFigure(Figure.RECT));
                figure.add(rect);
            MyMenuItem oval = new MyMenuItem("타원");
                oval.addActionListener(e -> setFigure(Figure.OVAL));
                figure.add(oval);
            MyMenuItem line = new MyMenuItem("선분");
                line.addActionListener(e -> setFigure(Figure.LINE));
                figure.add(line);

        MyMenu color = new MyMenu("색상");
            MyMenuItem black = new MyMenuItem("검정");
                black.addActionListener(e -> setColor(Color.black));
                 color.add(black);
            MyMenuItem red = new MyMenuItem("빨강");
                red.addActionListener(e -> setColor(Color.red));
                color.add(red);
            MyMenuItem yellow = new MyMenuItem("노랑");
                yellow.addActionListener(e -> setColor(Color.yellow));
                color.add(yellow);

        myMenuBar.add(figure);
        myMenuBar.add(color);

    }

    @Override
    public void paint(Graphics g) {
        myToolBar.draw(g);
        myMenuBar.draw(g);
        figureBox.draw(g);
    }
}
