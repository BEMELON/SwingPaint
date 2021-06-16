package SwingPaint.projectB.SwingAPI;

import javax.swing.*;
import java.util.ArrayList;

public interface MyLayout {
    void addLayoutComponent(ArrayList<JButton> comp);
    void update(int width, int height);
}
