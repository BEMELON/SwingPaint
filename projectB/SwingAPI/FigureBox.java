package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;


// Main Frame에서 직접적으로 호출되는 인터페이스
public class FigureBox {
    // Figure의 목록을 가지고 있는 ArrayList
    private final ArrayList<Figure> figures;

    /**
     * Main Frame으로부터 색을 전달받으면 저장하는 변수
     * 이후, Figure 타입의 클래스가 추가될 때, 해당 변수를 전달함.
     */
    private Color color;

    public FigureBox() {
        figures = new ArrayList<>();
    }

    /**
     * Main Frame으로부터 호출되는 함수
     * 색을 전달받아 저장한다.
     * @param color color
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Main Frame으로부터 호출되는 함수
     * 도형을 ArrayList에 추가함과 동시에 색을 지정한다. 
     * @param f 추가될 도형
     */
    public void add(Figure f) {
        f.setColor(color);
        figures.add(f);
    }

    /**
     * Main Frame으로부터 호출되는 함수
     * 도형 중에 클릭한 위치(p)가 있으면 해당 도형을 색칠한다.
     * 가장 먼저 추가된 도형이 우선으로 색칠되며 종료된다.
     * @param p 클릭한 위치
     */
    public void fill(Point p) {
        for(Figure f: figures) {
            if(f.contains(p)) {
                f.setFigureColor(color);
                f.fill(); return;
            }
        }
    }
    
    public void draw(Graphics g) {
        for(Figure f: figures) {
            f.draw(g);
        }
    }

    


}
