package SwingPaint.projectB.SwingAPI;

import SwingPaint.projectB.SwingAPI.Figure;

import java.awt.*;

public class Rectangle extends Figure {

    public Rectangle(Point start, Point end) {
        super(start, end);
    }

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Rectangle() {}

    @Override
    public void draw(Graphics g) {
        if(flagFill) {
            g.setColor(figureColor);
            g.fillRect(x, y, width, height);
        }
        g.setColor(color);
        g.drawRect(x, y, width, height);
        g.setColor(Color.BLACK);
    }
}
