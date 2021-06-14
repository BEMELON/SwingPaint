package SwingPaint.projectA;


import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Paint 프레임에서 사용하는 인터페이스
 * Composite 패턴을 사용하려 Figure 내부에는 Rectangle, Oval, Line, Group이 있다.
 */
public class FigureBox implements Serializable {
    private ArrayList<Figure> figures;
    protected Color lineColor = Color.BLACK,
                    figureColor = Color.BLACK;
    protected Boolean needfill = false,
                      needSelect = false;

    public FigureBox() {
        figures = new ArrayList<>();
    }

    /**
     * Figure을 list에 더한다.
     * @param f Figure 클래스 중 하나
     */
    public void add(Figure f) {
        needSelect = false;
        f.setColor(lineColor);
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
        needSelect = false;
        Point newStart = getStart(start, end);
        Point newEnd = getStart(start, end);
//        System.out.println("[DEBUG] BeforeGroup : " + figures);
        FigureGroup newGroup = new FigureGroup(newStart, newEnd);
        for(Figure figure: figures) {
            if(figure.isRange(newStart, newEnd)) {
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

    /**
     * 그룹을 해제합니다.
     * 다중 그룹에서는 그룹으로 분해됩니다.
     * 단일 그룹에서는 도형들로 분해됩니다.
     * @param start 시작점
     * @param end 끝점
     */
    public void removeGroup(Point start, Point end) {
//        System.out.println("[DEBUG] Before Degroup: " + figures);
        needSelect = false;
        Point newStart = getStart(start, end);
        Point newEnd = getEnd(start, end);
        ArrayList<FigureGroup> groups = new ArrayList<>();
        for(Figure figure: figures) {
            if(figure instanceof FigureGroup &&figure.isRange(newStart, newEnd)) {
                groups.add((FigureGroup) figure);
            }
        }

        figures.removeAll(groups);
        for(FigureGroup group: groups) {
            figures.addAll(group.getFigures());
        }
//        System.out.println("[DEBUG] After degroup: " + figures);
    }

    /**
     * 색상을 선택한다.
     * @param color JColorChooser로부터 선택받은 색
     */
    public void setColor(Color color) {
        this.lineColor = color;
    }

    /**
     * 범위내에 있는 도형을 지정된 색으로 채운다.
     * @param p 클릭한 위치
     */
    public void fill(Point p) {
        needSelect = false;
        for(Figure figure: figures) {
            if(figure.contains(p)) {
                figure.fillFigure(lineColor);
            }
        }
    }

    /**
     * start~end 범위 내에 있는 도형을 복사한다
     * 복사된 도형들은 하나의 그룹이 되며 살짝 옆으로 붙여넣어진다.
     * @param start 시작점
     * @param end 종점
     */
    public void copy(Point start, Point end) {
        FigureGroup newGroup = new FigureGroup(start, end);
        for(Figure figure: figures) {
            if(figure.isRange(start, end)) {
                try {
                    newGroup.add((Figure) figure.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        figures.add(newGroup);
        newGroup.moveTo(new Point(20, 0));
    }

    /**
     * start 범위에 있는 도형을 end로 이동한다.
     * @param src 해당 위치에 있는 도형들
     * @param dest 목적지
     */
    public void move(Point src, Point dest) {
        for(Figure figure: figures) {
            if(figure.contains(src)) {
                figure.moveTo(new Point(dest.x - src.x, dest.y - src.y));
            }
        }
    }

    // (x1, y1) <= (x2, y2) 인 경우를 만들기 위해 helper 생성
    private Point getStart(Point start, Point end) {
        return new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
    }

    private Point getEnd(Point start, Point end) {
        return new Point(Math.max(start.x, end.x), Math.min(start.y, end.y));
    }
}

abstract class Figure extends FigureBox implements Serializable{
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
}

class FigureGroup extends Figure implements Cloneable {
    // TODO isRange 처럼 범위를 기반으로 체크하는 메소드들은 언제 True를 주고 언제 false를 줄 것인지?
    private ArrayList<Figure> figures = new ArrayList<>();
    public FigureGroup(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public FigureGroup(Point start, Point end) {
        super(start, end);
    }

    @Override
    public boolean isRange(Point start, Point end) {
        for(Figure figure: figures) {
            if(!figure.isRange(start, end)) return false;
        }
        return true;
    }

    /**
     * figure를 관리 대상에 추가
     * @param f figure
     */
    public void add(Figure f) {
        figures.add(f);
    }

    @Override
    public void moveTo(Point diff) {
        for(Figure figure: figures) {
            figure.moveTo(diff);
        }
    }

    @Override
    public void draw(Graphics g) {
        for(Figure figure: figures) {
            figure.draw(g);
        }
    }

    @Override
    public void fillFigure(Color color) {
        for(Figure figure: figures) {
            figure.fillFigure(color);
        }
    }

    @Override
    public boolean contains(Point p) {
        for(Figure figure: figures) {
            if(!figure.contains(p)) return false;
        }
        return true;
    }

    /**
     * @return FigureGruop이 가지고 있는 Figures
     */
    public ArrayList<Figure> getFigures() {
        return figures;
    }
}
class Rectangle extends Figure implements Cloneable {
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
        g.setColor(lineColor);
        g.drawRect(x, y, width, height);
    }
}
class Oval extends Figure implements Cloneable{
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
        g.setColor(lineColor);
        g.drawOval(x, y, width, height);
    }
}
class Line extends Figure implements Cloneable {
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
        g.drawLine(x, y, width, height);
    }
}
