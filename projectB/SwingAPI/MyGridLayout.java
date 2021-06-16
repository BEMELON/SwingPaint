package SwingPaint.projectB.SwingAPI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class MyGridLayout implements MyLayout {
    private int row, col;
    private int frameWidth, frameHeight;
    private final Vector<JButton> buttons;

    /**
     * Window의 초기 width, height를 전달 받는다.
     * 추가로 사용할 Row, Col을 입력 받는데, Component가 Row*Col 보다 많은 경우 Col을 재조정한다.
     * @param width 넓이
     * @param height 높이
     * @param row 행
     * @param col 열
     */
    public MyGridLayout(int width, int height, int row, int col) {
        buttons = new Vector<>();
        this.frameWidth = width;
        this.frameHeight = height;
        this.col = col;
        this.row = row;
    }

    /**
     * Frame에서 관리되는 Component의 리스트를 전달받고, 재조정한다.
     * @param comp component들
     */
    @Override
    public void addLayoutComponent(ArrayList<JButton> comp) {
        buttons.addAll(comp);
        reArrange();
    }

    /**
     * reArrange() 함수의 helper 메소드이다.
     * 2차원배열을 1차원으로 변경한다. 
     * @param row 행
     * @param col 열
     * @return 1차원으로 flatten한 값
     */
    private int flatten(int row, int col) {
        return (row * this.col) + col;
    }

    /**
     * Component들을 재정렬하는 함수
     */
    private void reArrange() {
        if (buttons.size() > row * col) {
            col = buttons.size() / row;
        }
        int unitWidth = frameWidth / col;
        int unitHeight = frameHeight / row;
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

    /**
     * Window size가 재조정될 때 해당 함수가 호출된다.
     * 조정된 값을 기반으로 버튼을 재정렬한다.
     * @param width 재조정된 Width
     * @param height 재조정된 Height
     */
    @Override
    public void update(int width, int height) {
        this.frameHeight = height;
        this.frameWidth = width;
        reArrange();
    }


}
