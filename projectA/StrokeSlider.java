package SwingPaint.projectA;

import javax.swing.*;
import java.util.Hashtable;

public class StrokeSlider extends JSlider {
    public StrokeSlider(int min, int max, int value) {
        super(min, max, value);
        setMinorTickSpacing(1);
        setMajorTickSpacing(5);
        setPaintTicks(true);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1, new JLabel("1"));
        labels.put(5, new JLabel("5"));
        labels.put(10, new JLabel("10"));
        labels.put(15, new JLabel("15"));
        labels.put(20, new JLabel("20"));
        setLabelTable(labels);
        setPaintLabels(true);
    }

}
