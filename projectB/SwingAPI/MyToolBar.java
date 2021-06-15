package SwingPaint.projectB.SwingAPI;

import java.awt.*;
import java.util.ArrayList;

public class MyToolBar {
    private ArrayList<MyButton> buttons;

    public MyToolBar() {
        this.buttons = new ArrayList<>();
    }

    public void add(MyButton button) {
        buttons.add(button);
    }

    public void draw(Graphics g) {
        for(MyButton button: buttons) {
            button.draw(g);
        }
    }
}
