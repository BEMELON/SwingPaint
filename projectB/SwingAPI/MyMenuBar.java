package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyMenuBar {
    // 자신이 관리하는 Menu들
    private final ArrayList<MyMenu> menus = new ArrayList<>();

    private int height = 20, width;

    public MyMenuBar() {}

    public MyMenuBar(int height) {
        this.height = height;
    }

    /**
     * Main Frame에서 호출되며, 메뉴바에 메뉴를 더한다.
     * TODO: 메뉴에 메뉴를 가질 수 있는 여러 계층의 메뉴를 지원한다.
     * 메뉴의 x, y, width, height를 자신에 맞추어 재조정한다.
     * @param menu 메뉴
     */
    public void add(MyMenu menu) {
        menu.setX(width);
        width += menu.getWidth();
        menu.setHeight(height);
        menus.add(menu);
    }

    /**
     * 마우스가 클릭되면 항상 실행되는 함수
     * 자신의 메뉴들 중 클릭된 메뉴가 있는지 확인하고, 클릭되었다면 이벤트 실행
     * @param p 클릭한 위치
     */
    public void clickEvent(Point p) {
        for(MyMenu menu: menus) {
            if(menu.contains(p)) {
                menu.click();
            }
        }
    }
    
    public void draw(Graphics g) {
        g.drawRect(0, 0, width, height);
        for(MyMenu menu: menus) {
            menu.draw(g);
        }
    }
}
