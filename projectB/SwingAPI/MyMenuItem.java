package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MyMenuItem extends MyMenu {
    // 클릭되었을 때 실행될 ActionListener
    private MyActionListener myActionListener;

    public MyMenuItem(String name) {
        super(name);
    }

    public void addActionListener(MyActionListener listener) {
        this.myActionListener = listener;
    }


    @Override
    public boolean contains(Point p) {
        return  x <= p.x && p.x <= x + width
             && y <= p.y && p.y <= y + height;
    }

    @Override
    public void click() {
        myActionListener.actionPerformed(new ActionEvent(new Point(), 1, ""));
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(name, x + tx, y + ty);
        g.drawRect(x, y, width, height);
    }
}
