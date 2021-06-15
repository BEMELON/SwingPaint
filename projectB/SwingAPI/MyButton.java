package SwingPaint.projectB.SwingAPI;

import java.awt.*;

/**
 * 중단, Bottom-UP 하기에는 모르는 정보가 너무 많음.
 */
public class MyButton {
    // Button 이름이 들어가 있는 경우
    private String name;

    // Button (x, y), 넓이와 높이
    private int x, y, width, height;

    public MyButton(String name) {
        this.name = name;
    }

    public MyButton(Point start, Point end) {
        this.x = Math.min(start.x, end.x);
        this.y = Math.min(start.y, end.y);
        this.width = Math.max(start.x, end.x) - this.x;
        this.height = Math.max(start.y, end.y) - this.y;
    }

    public MyButton(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
