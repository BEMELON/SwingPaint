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

    public void setFigure(int type, int cursorMode) {
        setCursor(new Cursor(cursorMode));
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
        figureBox.setColor(color);
    }

    private void initToolBar() {
        myToolBar = new MyToolBar();

        //TODO toICON
        MyButton rect = new MyButton("RECT");
            rect.addActionListener(e -> setFigure(Figure.RECT, Cursor.CROSSHAIR_CURSOR));
            myToolBar.add(rect);
        MyButton oval = new MyButton("OVAL");
            oval.addActionListener(e -> setFigure(Figure.OVAL, Cursor.CROSSHAIR_CURSOR));
            myToolBar.add(oval);
        MyButton line = new MyButton("LINE");
            line.addActionListener(e -> setFigure(Figure.LINE, Cursor.CROSSHAIR_CURSOR));
            myToolBar.add(line);
        MyButton fill = new MyButton("채우기");
            fill.addActionListener(e -> setFigure(Figure.FILL, Cursor.HAND_CURSOR));
            myToolBar.add(fill);
        MyButton red = new MyButton(Color.red);
            red.addActionListener(e -> setColor(Color.red));
            myToolBar.add(red);
        MyButton black = new MyButton(Color.black);
            black.addActionListener(e-> setColor(Color.black));
            myToolBar.add(black);
        MyButton yellow = new MyButton(Color.yellow);
            yellow.addActionListener(e -> setColor(Color.yellow));
            myToolBar.add(yellow);
    }

    private void initMenuBar() {
        myMenuBar = new MyMenuBar();

        MyMenu figure = new MyMenu("도형");
            MyMenuItem rect = new MyMenuItem("사각형");
                rect.addActionListener(e -> setFigure(Figure.RECT, Cursor.CROSSHAIR_CURSOR));
                figure.add(rect);
            MyMenuItem oval = new MyMenuItem("타원");
                oval.addActionListener(e -> setFigure(Figure.OVAL, Cursor.CROSSHAIR_CURSOR));
                figure.add(oval);
            MyMenuItem line = new MyMenuItem("선분");
                line.addActionListener(e -> setFigure(Figure.LINE, Cursor.CROSSHAIR_CURSOR));
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
        super.paint(g);
        myToolBar.draw(g);
        myMenuBar.draw(g);
        figureBox.draw(g);
    }

    class MyMouseListener implements MouseListener {
        private Point start, end;

        @Override
        public void mouseClicked(MouseEvent e) {
            myMenuBar.clickEvent(e.getPoint());
            myToolBar.clickEvent(e.getPoint());
            if (type == Figure.FILL)
                figureBox.fill(e.getPoint());
            repaint();
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

            myMenuBar.clickEvent(e.getPoint());
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
