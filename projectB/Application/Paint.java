package SwingPaint.projectB.Application;

import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {
    private PaintPanel paintPanel = new PaintPanel();

    public Paint() {
        setSize(1920 / 2, 1080 / 2);
        setLocation(80, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(paintPanel);

        setLayout(new MyFlowLayout());
        setLayout(new MyGridLayout());

        setVisible(true);
    }



    public static void main(String[] args) {
        Paint main = new Paint();
    }
}
