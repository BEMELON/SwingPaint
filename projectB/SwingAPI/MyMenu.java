package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyMenu {
    protected String name;
    private ArrayList<MyMenu> menus;

    public MyMenu(String name) {
        menus = new ArrayList<>();
        this.name = name;
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
