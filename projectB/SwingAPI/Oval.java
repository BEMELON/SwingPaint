package SwingPaint.projectB.SwingAPI;

import java.awt.*;

public class Oval extends Figure {
    public Oval(Point start, Point end) {
        super(start, end);
    }

    public Oval(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Oval() {}

    @Override
    public void draw(Graphics g) {
        if(flagFill) {
            g.setColor(figureColor);
            g.fillOval(x, y, width, height);
        }
        g.setColor(color);
        g.drawOval(x, y, width, height);
        g.setColor(Color.black);
    }

}
