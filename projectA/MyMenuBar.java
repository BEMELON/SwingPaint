package SwingPaint.projectA;

import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(int r, int g, int b) {
        float[] color = new float[3];
        Color.RGBtoHSB(r, g, b, color);
        setBackground(Color.getHSBColor(color[0], color[1], color[2]));
        setBorder(new LineBorder(Color.BLACK));
        UIManager.put("PopupMenu.border", new LineBorder(Color.black));
    }
}
