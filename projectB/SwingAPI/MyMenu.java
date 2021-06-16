package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyMenu {
    // 메뉴에 표시될 이름
    protected String name;
    
    // MenuItem을 표시할지 체크하는 변수
    private Boolean isClicked = false;
    
    // MenuItem들을 관리
    private final ArrayList<MyMenu> menus = new ArrayList<>();

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

    /**
     * Main Frame에서 호출되며, 메뉴바에 메뉴를 더한다.
     * TODO: 메뉴에 메뉴를 가질 수 있는 여러 계층의 메뉴를 지원한다.
     * 메뉴의 x, y, width, height를 자신에 맞추어 재조정한다.
     * @param menu 메뉴
     */
    public void add(MyMenu menu) {
        menu.setX(x);
        menus.add(menu);
        menu.setY(y + menus.size() * height);
        menu.setWidth(width);
        menu.setHeight(height);
    }

    /**
     * 마우스가 클릭되면 항상 실행되는 함수
     * 자신이 클릭되었는지, 자신이 클릭되었다면 자신의 메뉴가 클릭되었는지 검사
     * @param p 클릭한 위치
     * @return 자신이 클릭된 경우만 True
     */
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

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

}
