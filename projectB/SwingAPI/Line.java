package SwingPaint.projectB.SwingAPI;

import java.awt.*;

public class Line extends Figure {
    public Line(Point start, Point end) {
        super(start, end);
        this.x = start.x; this.y = start.y;
        this.width = end.x; this.height = end.y;
    }

    public Line(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = x + width; this.height = y + height;
    }

    public Line() {}

    @Override
    public void draw(Graphics g) {
        g.drawLine(x, y, width, height);
    }
}
