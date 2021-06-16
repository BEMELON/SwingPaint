package SwingPaint.projectA;

import java.awt.*;
import java.io.Serializable;

public abstract class Figure extends FigureBox implements Serializable {
    /**
     * x: x 좌표
     * y: y 좌표
     * width: 넓이
     * height: 높이
     */
    protected int x, y, width, height;

    /**
     * @param x: x
     * @param y: y
     * @param width: width
     * @param height: height
     */
    public Figure(int x, int y, int width, int height){
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    /**
     * (x1, y1) <= (x2, y2) 형태로 변환하여 저장합니다.
     * @param start 시작
     * @param end 끝
     */
    public Figure(Point start, Point end) {
        this.x = Math.min(start.x, end.x);
        this.y = Math.min(start.y, end.y);
        this.width = Math.max(start.x, end.x) - this.x;
        this.height = Math.max(start.y, end.y) - this.y;
    }

    /**
     * start와 end 범위 사이에 있는지 체크합니다.
     * @param start 범위 시작점
     * @param end 범위 끝점
     * @return 사이에 있으면 true 아니면 false
     */
    public boolean isRange(Point start, Point end) {
        return start.x <= x && x + width <= end.x
                && start.y <= y && y + height <= end.y;
    }

    /**
     * Class에 맞추어 paint 합니다.
     * @param g 메인 Frame의 Graphics g
     */
    public abstract void draw(Graphics g);

    /**
     * 도형에 색을 채우도록 설정합니다.
     * @param color 지정된 색
     */
    public void fillFigure(Color color) {
        needfill = true;
        figureColor = color;
    }

    public boolean contains(Point p) {
        return x <= p.x && p.x <= x + width
                && y <= p.y && p.y <= y + height;
    }

    public void moveTo(Point diff) {
        this.x = this.x + diff.x;
        this.y = this.y + diff.y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}