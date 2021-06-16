package SwingPaint.projectA;

import SwingPaint.projectA.Figure;

import java.awt.*;

public class Rectangle extends Figure implements Cloneable {
    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Rectangle(Point start, Point end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics g) {
        if(needfill) {
            g.setColor(figureColor);
            g.fillRect(x, y, width, height);
        }
        ((Graphics2D)g).setStroke(new BasicStroke(strokeSize));
        g.setColor(lineColor);
        g.drawRect(x, y, width, height);
    }
}
