package SwingPaint.projectB.SwingAPI;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;

public abstract class Figure {
    /**
     * CONSTANT를 제공하여, main에서 활용할 수 있도록 함.
     */
    public static final int RECT = 0;
    public static final int OVAL = 1;
    public static final int LINE = 2;
    public static final int FILL = 3;

    // (x, y) , 넓이, 높이
    protected int x, y, width, height;

    // 도형 내부를 색으로 채워야 하는지
    protected Boolean flagFill = false;

    // 선 색과 내부 색
    protected Color color, figureColor;

    public Figure(Point start, Point end) {
        this.x = Math.min(start.x, end.x);
        this.y = Math.min(start.y, end.y);
        this.width = Math.max(start.x, end.x) - x;
        this.height = Math.max(start.y, end.y) - y;
    }

    public Figure(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public Figure() {}

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFigureColor(Color color) {
        this.figureColor = color;
    }

    public boolean contains(Point p) {
        return x <= p.x && p.x <= x + width
            && y <= p.y && p.y <= y + height;
    }

    public void fill() {
        flagFill = true;
    }

    public abstract void draw(Graphics g);
}
