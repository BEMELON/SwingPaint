package SwingPaint.projectB.SwingAPI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyFlowLayout implements MyLayout {
    // FlowLayout에서 관리할 Component의 리스트
    private final Vector<Component> components = new Vector<>();

    // Frame의 Width, Height
    private int frameWidth, frameHeight;

    // Component가 재조정될 때 x, y값
    private int componentX, componentY = 30;

    // Component 사이의 간격
    private final int padding = 10;
    /**
     * Window 초기 넓이와 높이를 받아서 저장
     * @param width 넓이
     * @param height 높이
     */
    public MyFlowLayout(int width, int height) {
        this.frameWidth = width;
        this.frameHeight = height;
    }

    /**
     * MyFlowLayout 버튼이 클릭되면 해당 함수가 실행된다.
     * 실행된 순간, Component들을 전달받고, 이를 재정렬한다.
     * @param comp panel에서 관리되는 buttons
     */
    @Override
    public void addLayoutComponent(ArrayList<JButton> comp) {
        components.addAll(comp);
        reArrange();
    }

    /**
     * Component들을 재정렬하는 함수
     */
    private void reArrange() {
        // 해당 줄에서 계산되는 넓이
        int calculatedWidth = 0;

        // Component들의 최대 높이
        int maxHeight = 0;

        // 쓰레딩 문제가 있어 아래의 자료형 사용
        // https://m.blog.naver.com/tmondev/220393974518 참고
        CopyOnWriteArrayList<Component> componentsOnLine = new CopyOnWriteArrayList<>();
        for (Component comp : components) {
            // 다음 Component가 Frame width를 넘는 경우
            if (calculatedWidth + comp.getWidth() >= frameWidth) {
                setComponentsLocations(calculatedWidth, componentsOnLine);
                this.componentY += maxHeight + padding;
                maxHeight = 0; calculatedWidth = 0;
            }
            
            calculatedWidth += comp.getWidth() + padding;
            maxHeight = Math.max(comp.getHeight(), maxHeight);
            componentsOnLine.add(comp);
        }
        // 남은 Component 처리
        if(componentsOnLine.size() != 0) {
            setComponentsLocations(calculatedWidth, componentsOnLine);
        }
        componentY = 30;
    }

    /**
     * reArrange 함수 helper 메소드
     * @param calculatedWidth 현재줄에서 계산된 넓이
     * @param components 현재줄에 있는 Components
     */
    private void setComponentsLocations(int calculatedWidth, CopyOnWriteArrayList<Component> components) {
        int offset = (frameWidth - calculatedWidth) / 2;
        for (Component target : components) {
            target.setLocation(offset, componentY);
            offset += target.getWidth() + padding;
        }
        components.clear();
    }

    /**
     * Window size가 재조정될 때 해당 함수가 호출된다.
     * 조정된 값을 기반으로 버튼을 재정렬한다.
     * @param width 재조정된 Width
     * @param height 재조정된 Height
     */
    @Override
    public void update(int width, int height) {
        this.frameWidth = width;
        this.frameHeight = height;
        reArrange();
    }
}
