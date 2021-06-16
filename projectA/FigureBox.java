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
    protected int strokeSize = 1;
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
        f.setStroke(strokeSize);
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
        Point newEnd = getEnd(start, end);
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
            if(figure instanceof FigureGroup && figure.isRange(newStart, newEnd)) {
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
                break;
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
//        System.out.println("[DEBUG] beforeCopy : " + figures);
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
//        System.out.println("[DEBUG] afterCopy : " + figures);
        newGroup.moveTo(new Point(20, 20));
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

    /**
     * JSlider로부터 결정된 선 굵기를 저장
     * @param value 선 굵기
     */
    public void setStroke(int value) {
        this.strokeSize = value;
    }
    // (x1, y1) <= (x2, y2) 인 경우를 만들기 위해 helper 생성
    private Point getStart(Point start, Point end) {
        return new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
    }

    private Point getEnd(Point start, Point end) {
        return new Point(Math.max(start.x, end.x), Math.max(start.y, end.y));
    }
}
