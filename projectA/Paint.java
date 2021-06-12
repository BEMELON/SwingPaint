import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {
    private JMenuBar jMenuBar;
    private JToolBar jToolBar;
    public Paint() {
        setLocation(40,0);
        setSize(1920 / 2, 1080 / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));

        initMenuBar();
        initToolBar();
        initMenus();

        setVisible(true);
    }

    private void initToolBar() {
        jToolBar = new JToolBar(null, JToolBar.VERTICAL);
        add(jToolBar, BorderLayout.WEST);
    }

    private void initMenuBar() {
        jMenuBar = new JMenuBar();
        add(jMenuBar, BorderLayout.NORTH);
    }

    private void initMenus() {
        JMenu file = new JMenu("파일");
            JMenuItem save = new JMenuItem("저장");
            JMenuItem load = new JMenuItem("불러오기");
            JMenuItem saveASnewFile = new JMenuItem("다른 이름으로 저장");
            file.add(save); file.add(load); file.add(saveASnewFile);
        jMenuBar.add(file);

        JMenu selectMenu = new JMenu("선택");
            JMenuItem selectBox = new JMenuItem("그룹 선택");
            JMenuItem groupBox = new JMenuItem("그룹화");
            JMenuItem degroupBox = new JMenuItem("그룹화 해제");
            selectMenu.add(selectBox); selectMenu.add(groupBox); selectMenu.add(degroupBox);
        jMenuBar.add(selectMenu);

        JMenu colorBox = new JMenu("색");
            JColorChooser jColorChooser = new JColorChooser();
            colorBox.add(jColorChooser);
        jMenuBar.add(colorBox);

        // TODO 아이콘으로 변경하기
        JButton rect = new JButton("RECT");
        JButton oval = new JButton("OVAL");
        JButton line = new JButton("LINE");
        JButton painter = new JButton("채우기");
        jToolBar.add(rect); jToolBar.add(oval); jToolBar.add(line);
        jToolBar.add(painter);
    }


    public static void main(String[] args) {
        Paint main = new Paint();
    }
}
