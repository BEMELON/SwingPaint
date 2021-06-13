package SwingPaint.projectA;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Paint 프레임에서 사용하는 인터페이스
 * Composite 패턴을 사용하려 Figure 내부에는 Rectangle, Oval, Line, Group이 있다.
 */
public class FigureBox {
    private ArrayList<Figure> figures;
    private JFrame main;
    public FigureBox(JFrame main) {
        figures = new ArrayList<>();
        this.main = main;
    }

    /**
     * Figure을 list에 더한다.
     * @param f Figure 클래스 중 하나
     */
    public void add(Figure f) {
        figures.add(f);
    }

    /**
     * 모든 Figure를 paint합니다.
     * @param g MainFrame에서의 paint
     */
    public void draw(Graphics g) {
        for(Figure f: figures) {
            f.draw(g);
        }
    }

    /**
     * 주어진 위치에 해당되는 도형을 Group으로 설정
     * @param start 시작점
     * @param end 끝점
     */
    public void addGroup(Point start, Point end) {
        int x = Math.min(start.x, end.x); int y = Math.min(start.y, end.y);
        int x2 = Math.max(start.x, end.x); int y2 = Math.max(start.y, end.y);
        start = new Point(x, y);
        end = new Point(x2, y2);
//        System.out.println("[DEBUG] BeforeGroup : " + figures);
        FigureGroup newGroup = new FigureGroup(start, end);
        for(Figure figure: figures) {
            if(figure.isRange(start, end)) {
                newGroup.add(figure);
            }
        }
//        System.out.println("[DEBUG] processing, getFigures() : " + newGroup.getFigures());
        if(!(newGroup.getFigures()).isEmpty()) {
            figures.removeAll(newGroup.getFigures());
            figures.add(newGroup);
        }
//        System.out.println("[DEBUG] AfterGroup : " + figures);
    }
}

abstract class Figure {
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
}

class FigureGroup extends Figure {
    private ArrayList<Figure> figures = new ArrayList<>();
    public FigureGroup(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public FigureGroup(Point start, Point end) {
        super(start, end);
    }

    /**
     * figure를 관리 대상에 추가
     * @param f figure
     */
    public void add(Figure f) {
        figures.add(f);
    }
    @Override
    public void draw(Graphics g) {
        for(Figure figure: figures) {
            figure.draw(g);
        }
    }

    /**
     * @return FigureGruop이 가지고 있는 Figures
     */
    public ArrayList<Figure> getFigures() {
        return figures;
    }
}
class Rectangle extends Figure {
    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Rectangle(Point start, Point end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
    }
}
class Oval extends Figure {
    public Oval(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Oval(Point start, Point end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, width, height);
    }
}
class Line extends Figure {
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
        g.drawLine(x, y, width, height);
    }
}
