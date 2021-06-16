package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyMenuBar {
    private ArrayList<MyMenu> menus = new ArrayList<>();
    private int x = 0, y= 0, height = 20, width;

    public MyMenuBar() {
    }

    public MyMenuBar(int height) {
        this.height = height;
    }

    public void add(MyMenu menu) {
        menu.setX(width);
        width += menu.getWidth();
        menu.setHeight(height);
        menus.add(menu);
    }

    public void draw(Graphics g) {
        g.drawRect(x,y, width, height);
        for(MyMenu menu: menus) {
            menu.draw(g);
        }
    }

    public void clickEvent(Point p) {
        for(MyMenu menu: menus) {
            if(menu.contains(p)) {
                menu.click();
            }
        }
    }
}
