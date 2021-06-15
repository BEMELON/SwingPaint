package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyMenuBar {
    private ArrayList<MyMenu> menus;
    private int height, width;

    public MyMenuBar() {
        menus = new ArrayList<>();
        height = 20;
    }

    public MyMenuBar(int height) {
        super();
        this.height = height;
    }

    public void add(MyMenu menu) {
        menus.add(menu);
    }

    public void draw(Graphics g) {
        for(MyMenu menu: menus) {
            menu.draw(g);
        }
    }
}
