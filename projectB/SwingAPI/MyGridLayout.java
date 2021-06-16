package SwingPaint.projectB.SwingAPI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class MyGridLayout implements MyLayout {
    private int row, col;
    private int frameWidth, frameHeight;
    private Vector<JButton> buttons;

    public MyGridLayout(int width, int height, int row, int col) {
        buttons = new Vector<>();
        this.frameWidth = width;
        this.frameHeight = height;
        this.col = col;
        this.row = row;
    }

    @Override
    public void addLayoutComponent(ArrayList<JButton> comp) {
        buttons.addAll(comp);
        reArrange();
    }

    private int flatten(int row, int col) {
        return (row * this.col) + col;
    }

    private void reArrange() {
        if (buttons.size() > row * col) {
            col = buttons.size() / row;
        }
        System.out.println(frameHeight);
        int unitWidth = frameWidth / col;
        int unitHeight = frameHeight / row;
        System.out.println(unitHeight);
        int x = 0, y = 0;
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                JButton button = buttons.get(flatten(r, c));
                button.setBounds(x, y, unitWidth, unitHeight);
                x += unitWidth;
            }
            y += unitHeight;
            x = 0;
        }

    }

    @Override
    public void update(int width, int height) {
        this.frameHeight = height;
        this.frameWidth = width;
        reArrange();
    }


}
