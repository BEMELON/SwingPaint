package SwingPaint.projectA;

import java.awt.*;

public class Oval extends Figure implements Cloneable{
    public Oval(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Oval(Point start, Point end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics g) {
        if(needfill) {
            g.setColor(figureColor);
            g.fillOval(x, y, width, height);
        }
        ((Graphics2D)g).setStroke(new BasicStroke(strokeSize));
        g.setColor(lineColor);
        g.drawOval(x, y, width, height);
    }
}