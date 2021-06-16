package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class FigureBox {
    private ArrayList<Figure> figures;
    private Color color;

    public FigureBox() {
        figures = new ArrayList<>();
    }

    public void add(Figure f) {
        f.setColor(color);
        figures.add(f);
    }

    public void draw(Graphics g) {
        for(Figure f: figures) {
            f.draw(g);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void fill(Point p, Color color) {
        for(Figure f: figures) {
            if(f.contains(p)) {
                f.setFigureColor(color);
                f.fill();
            }
        }
    }
}
