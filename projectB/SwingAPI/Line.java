package SwingPaint.projectB.SwingAPI;

import java.awt.*;

public class Line extends Figure {
    /**
     * 작동 방식이 다르기 때문에, 별도로 동작한다.
     * @param start 시작점
     * @param end 끝점
     */
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
        g.setColor(color);
        g.drawLine(x, y, width, height);
        g.setColor(Color.BLACK);
    }
}
