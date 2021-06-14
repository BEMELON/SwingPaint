package SwingPaint.projectA;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomMenu extends JMenu {
    public CustomMenu(String name) {
        super(name);
        setFont(new Font("바탕", Font.PLAIN, 15));
    }
}
