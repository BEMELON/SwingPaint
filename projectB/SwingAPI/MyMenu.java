package SwingPaint.projectB.SwingAPI;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.util.ArrayList;

public class MyMenu {
    protected String name;
    private Boolean isClicked = false;
    private ArrayList<MyMenu> menus = new ArrayList<>();
    protected int x, y, width = 70, height = 20,
                  tx, ty;

    public MyMenu(String name) {
        this.name = name;
        tx = (width - name.length()) / 3;
        ty = 15;
    }

    public MyMenu(String name, int width, int height) {
        this(name);
        this.width = width;
        this.height = height;
    }

    public void add(MyMenu menu) {
        menu.setX(x);
        menus.add(menu);
        menu.setY(y + menus.size() * height);
        menu.setWidth(width);
        menu.setHeight(height);
    }

    private void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
        g.drawString(name, x + tx, y + ty);
        if (isClicked) {
            // 서브메뉴 draw
            for(MyMenu menu: menus) {
                menu.draw(g);
            }
        }
    }

    public boolean contains(Point p) {
        if(isClicked) {
            // 서브 메뉴가 선택되었는지 체크
            for(MyMenu menu: menus) {
                if(menu.contains(p)) {
                    menu.click(); isClicked = false;
                    return false;
                }
            }
        }
        boolean contains = x <= p.x && p.x <= x + width && y <= p.y && p.y <= y + height;
        if(!contains) isClicked = false;
        return contains;
    }

    public void click() {
        isClicked = true;
    }

    public int getTx() {
        return tx;
    }

    public int getTy() {
        return ty;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        for(MyMenu menu: menus) {
            menu.setX(x);
        }
        this.x = x;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

}
