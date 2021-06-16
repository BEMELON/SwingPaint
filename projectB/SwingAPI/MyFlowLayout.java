package SwingPaint.projectB.SwingAPI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyFlowLayout implements MyLayout {
    private Vector<Component> components = new Vector<>();
    private int frameWidth, frameHeight, lastButtonWidth;
    private int componentX, componentY = 30;
    private final int padding = 10;

    public MyFlowLayout(int width, int height) {
        this.frameWidth = width;
        this.frameHeight = height;
    }

    @Override
    public void addLayoutComponent(ArrayList<JButton> comp) {
        components.addAll(comp);
        reArrange();
    }

    private void reArrange() {
        int calculatedWidth = 0;
        int maxHeight = 0;
        // 동시성 문제 아니 이게 무슨?
        CopyOnWriteArrayList<Component> lineComponents = new CopyOnWriteArrayList<>();
        for (Component comp : components) {
            if (calculatedWidth + comp.getWidth() >= frameWidth) {
                // 다음 Component가 width를 넘는 경우
                int offset = (frameWidth - calculatedWidth) / 2;
                for (Component target : lineComponents) {
                    target.setLocation(offset, componentY);
                    offset += target.getWidth() + padding;
                }
                this.componentY += maxHeight + padding;
                maxHeight = 0; calculatedWidth = 0;
                lineComponents.clear();
            }
            calculatedWidth += comp.getWidth() + padding;
            maxHeight = Math.max(comp.getHeight(), maxHeight);
            lineComponents.add(comp);
        }
        // 남은 case 처리
        if(lineComponents.size() != 0) {
            int offset = (frameWidth - calculatedWidth) / 2;
            for (Component target : lineComponents) {
                target.setLocation(offset, componentY);
                offset += target.getWidth() + padding;
                lineComponents.clear();
            }
        }
        componentY = 30;
    }

    @Override
    public void update(int width, int height) {
        this.frameWidth = width;
        this.frameHeight = height;
        reArrange();
    }
}
