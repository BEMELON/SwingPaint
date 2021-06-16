package SwingPaint.projectB.Application;

import javax.swing.*;

public class Paint extends JFrame {

    public Paint() {
        setSize(1920 / 2, 1080 / 2);
        setLocation(80, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PaintPanel paintPanel = new PaintPanel();
        setContentPane(paintPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Paint main = new Paint();
    }
}
