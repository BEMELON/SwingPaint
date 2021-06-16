package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyToolBar {
    private ArrayList<MyButton> buttons;
    private int x = 850;
    private int y = 60;
    private final int buttonWidth = 40;
    private int height;
    private final int padding = 5;

    public MyToolBar() {
        this.buttons = new ArrayList<>();
        height = padding;
    }

    public void add(MyButton button) {
        button.setX(x + padding);
        button.setY(y + padding + buttons.size() * (buttonWidth + padding));
        button.setWidth(buttonWidth);
        button.setHeight(buttonWidth);

        height += buttonWidth + padding;
        buttons.add(button);
    }

    public void draw(Graphics g) {
        g.drawRect(x, y, buttonWidth + 2 * padding, height);
        for(MyButton button: buttons) {
            button.draw(g);
        }
    }

    public void clickEvent(Point p) {
        for(MyButton button: buttons) {
            if(button.contains(p)) {
                button.click();
            }
        }
    }

}
