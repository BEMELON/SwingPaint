package SwingPaint.projectB.Application;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class LayoutDemo extends JFrame {
    private MyLayout layoutMgr;
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private int btnWidth = 80, btnHeight=30;

    public LayoutDemo() {
        setSize(1920 / 2, 1080 / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(layoutMgr != null) layoutMgr.update(getBounds().width, getBounds().height);
            }
        });
        setLocation(80, 0);
        setLayout(null);

        JButton flowlayout = new JButton("FlowLayout");
            flowlayout.setBounds(100, 0, btnWidth * 2, btnHeight);
            flowlayout.addActionListener(e -> setAndValidate(new MyFlowLayout(getBounds().width, getBounds().height)));
        JButton gridLayout = new JButton("GridLayout");
            gridLayout.setBounds(400, 0, btnWidth * 2, btnHeight);
            gridLayout.addActionListener(e -> setAndValidate(new MyGridLayout(getBounds().width, getBounds().height, 3, 3)));
        add(flowlayout); add(gridLayout);

        for(int i =0; i<12; i++) {
            JButton button = new JButton("[" + i +"]");
            button.setBounds((i + 1) * 20, (i+1) * 30, btnWidth, btnHeight);
            add(button); buttons.add(button);
        }

        setVisible(true);
    }

    private void setAndValidate(MyLayout myLayout) {
        layoutMgr = myLayout;
        layoutMgr.addLayoutComponent(buttons);
        repaint();
    }

    public static void main(String[] args) {
        LayoutDemo layoutDemo = new LayoutDemo();
    }
}
