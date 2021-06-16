package SwingPaint.projectA;

import java.awt.*;
import java.util.ArrayList;

public class FigureGroup extends Figure {
    private ArrayList<Figure> figures = new ArrayList<>();
    public FigureGroup(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public FigureGroup(Point start, Point end) {
        super(start, end);
    }

    public String toString() {
        return figures.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        FigureGroup clone = new FigureGroup(new Point(0, 0), new Point(0, 0));
        for(Figure figure: figures) {
            clone.add((Figure) figure.clone());
        }
        return clone;
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
            if(figure.contains(p)) return true;
        }
        return false;
    }

    /**
     * @return FigureGruop이 가지고 있는 Figures
     */
    public ArrayList<Figure> getFigures() {
        return figures;
    }
}