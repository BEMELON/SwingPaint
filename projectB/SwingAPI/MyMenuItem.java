package SwingPaint.projectB.SwingAPI;

import java.awt.*;

public class MyMenuItem extends MyMenu {
    private MyActionListener myActionListener;

    public MyMenuItem(String name) {
        super(name);
    }

    public void addActionListener(MyActionListener listener) {
        this.myActionListener = listener;
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(name, 10, 10);
    }
}
