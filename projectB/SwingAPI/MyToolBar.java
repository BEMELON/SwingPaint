package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyToolBar {
    // 자신이 관리할 Tool?들
    private final ArrayList<MyButton> buttons;

    // ToolBar의 위치
    // TODO: 드래그
    private final int x = 850;
    private final int y = 60;

    // 관리할 Button의 넓이
    private final int buttonWidth = 40;
    private int height;
    
    // Button들간의 간격
    private final int padding = 5;

    public MyToolBar() {
        this.buttons = new ArrayList<>();
        height = padding;
    }

    /**
     * Main Frame에서 호출되며, 툴바에 버튼를 더한다.
     * 메뉴의 x, y, width, height를 자신에 맞추어 재조정한다.
     * @param button  버튼
     */
    public void add(MyButton button) {
        button.setX(x + padding);
        button.setY(y + padding + buttons.size() * (buttonWidth + padding));
        button.setWidth(buttonWidth);
        button.setHeight(buttonWidth);

        height += buttonWidth + padding;
        buttons.add(button);
    }

    public void clickEvent(Point p) {
        for(MyButton button: buttons) {
            if(button.contains(p)) {
                button.click();
            }
        }
    }

    public void draw(Graphics g) {
        g.drawRect(x, y, buttonWidth + 2 * padding, height);
        for(MyButton button: buttons) {
            button.draw(g);
        }
    }
}
