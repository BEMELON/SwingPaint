package SwingPaint.projectB.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintPanel extends JPanel {
    private FigureBox figurebox;

    class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

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
    }

    public PaintPanel() {
        figurebox = new FigureBox();

        initMenuBar();
        initToolBar();
        initMenus();

        addMouseListener(new MyMouseListener());
    }

    private void initMenus() {
        MyMenu figure = new MyMenu("도형");
        MyMenuItem rect = new MyMenuItem("사각형");
        rect.addActionListener(new MyActionListener((e) -> paintPanel.setFigure(FIGURE.RECT)));
        figure.add(rect);
        MyMenuItem oval = new MyMenuItem("타원");
        oval.addActionListener(new MyActionListener((e) -> paintPanel.setFigure(FIGURE.OVAL)));
        figure.add(oval);
        MyMenuItem line = new MyMenuItem("선분");
        line.addActionListener(new MyActionListener((e) -> paintPanel.setFigure(FIGURE.LINE)));
        figure.add(line);

        MyMenu color = new MyMenu("색상");
        MyMenuItem black = new MyMenuItem("검정");
        black.addActionListener(new MyActionListener((e) -> paintPanel.setColor(Color.black)));
        color.add(black);
        MyMenuItem red = new MyMenuItem("빨강");
        red.addActionListener(new MyActionListener((e) -> paintPanel.setColor(Color.red)));
        color.add(red);
        MyMenuItem yellow = new MyMenuItem("노랑");
        yellow.addActionListener(new MyActionListener((e) -> paintPanel.setColor(Color.yellow)));
        color.add(yellow);

        myMenuBar.add(figure);
        myMenuBar.add(color);
    }

    private void initToolBar() {
        myToolBar = new MyToolBar();
        add(myToolBar);
    }

    private void initMenuBar() {
        myMenuBar = new MyMenuBar();
        add(myMenuBar);
    }

    @Override
    public void paint(Graphics g) {
        myToolBar.paint(g);
        myMenuBar.paint(g);
        myFigureBox.paint(g);
    }
}
