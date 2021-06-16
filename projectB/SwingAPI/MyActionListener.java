package SwingPaint.projectB.SwingAPI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@FunctionalInterface
public interface MyActionListener extends ActionListener {
    @Override
    void actionPerformed(ActionEvent e);
}
