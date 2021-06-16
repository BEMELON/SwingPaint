package SwingPaint.projectB.SwingAPI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyFlowLayout implements MyLayout {
    private JFrame main;
    private int padding;
    private ArrayList<Component> components;

    public MyFlowLayout() {
        components = new ArrayList<>();
        padding = 10;
    }

    @Override
    public void addLayoutComponent(Component comp) {

        components.add(comp);
    }

    @Override
    public void setInfo(JFrame main) {
        this.main = main;
    }
}
