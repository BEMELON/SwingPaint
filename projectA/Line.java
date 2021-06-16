package SwingPaint.projectA;

import java.awt.*;

public class Line extends Figure implements Cloneable {
    /**
     * 별도의 변환을 거치지 않습니다.
     * @param start x1, y1
     * @param end x2, y2
     */
    public Line(Point start, Point end) {
        super(start, end); // for Syntax check
        this.x = start.x;
        this.y = start.y;
        this.width = end.x;
        this.height = end.y;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(lineColor);
        if(needfill) {
            g.setColor(figureColor);
        }
        ((Graphics2D)g).setStroke(new BasicStroke(strokeSize));
        g.drawLine(x, y, width, height);
    }
}
