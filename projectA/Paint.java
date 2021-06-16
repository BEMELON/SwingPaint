package SwingPaint.projectA;

import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {

    public Paint() {
        setLocation(80,0);
        setSize(1920 / 2, 1080 / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        PaintPanel paintPanel = new PaintPanel();
        paintPanel.setBackground(Color.white);

        setContentPane(paintPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Paint main = new Paint();
    }

}
