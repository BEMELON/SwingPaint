package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MyButton {
    private String name;
    private MyActionListener myActionListener;
    private int x, y, width, height;
    private Color color;

    public MyButton(String name) {
        this.name = name;
    }

    public MyButton(Color color) {
        this.color = color;
    }
    public MyButton(Point start, Point end) {
        this.x = Math.min(start.x, end.x);
        this.y = Math.min(start.y, end.y);
        this.width = Math.max(start.x, end.x) - this.x;
        this.height = Math.max(start.y, end.y) - this.y;
    }

    public MyButton(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public void addActionListener(MyActionListener listener) {
        this.myActionListener = listener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void draw(Graphics g) {
        if(color != null) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.black);
        } else {
            g.drawString(name, x + (width - name.length()) / 5, y + 25);
        }
        g.drawRect(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean contains(Point p) {
        return x <= p.x && p.x <= x + width
            && y <= p.y && p.y <= y + height;
    }

    public void click() {
        myActionListener.actionPerformed(new ActionEvent(new Point(), 1, null));
    }
}
