package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class FigureBox {
    private ArrayList<Figure> figures;

    public FigureBox() {
        figures = new ArrayList<>();
    }

    public void add(Figure f) {
        figures.add(f);
    }

    public void draw(Graphics g) {
        for(Figure f: figures) {
            f.draw(g);
        }
    }
}
