package SwingPaint.projectA;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Paint extends JFrame {
    private JMenuBar jMenuBar;
    private JToolBar jToolBar;
    private FigureBox figureBox = new FigureBox();
    private final FileHandler fileHandler = new FileHandler();

    private enum MODE {RECT, OVAL, LINE, COLOR, FILL, GROUP, DEGROUP}
    private MODE type = MODE.RECT;
    public Paint() {
        setLocation(80,0);
        setSize(1920 / 2, 1080 / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        addMouseListener(new myMouseListener());

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
                save.addActionListener(e -> {
                    fileHandler.addObject(figureBox); fileHandler.save(false);
                });
            JMenuItem saveASnewFile = new JMenuItem("다른 이름으로 저장");
                saveASnewFile.addActionListener(e -> {
                    fileHandler.addObject(figureBox); fileHandler.save(true);
                });

            JMenuItem load = new JMenuItem("불러오기");
                load.addActionListener(e -> {
                    figureBox = fileHandler.load(); repaint();
                });

            file.add(save); file.add(load); file.add(saveASnewFile);
        jMenuBar.add(file);

        JMenu selectMenu = new JMenu("선택");
            JMenuItem selectBox = new JMenuItem("그룹 선택");
            JMenuItem groupBox = new JMenuItem("그룹화");
                groupBox.addActionListener(e -> type = MODE.GROUP);
            JMenuItem degroupBox = new JMenuItem("그룹화 해제");
                degroupBox.addActionListener(e -> type = MODE.DEGROUP);
            selectMenu.add(selectBox); selectMenu.add(groupBox); selectMenu.add(degroupBox);
        jMenuBar.add(selectMenu);

        JMenu colorBox = new JMenu("색");
            JColorChooser jColorChooser = new JColorChooser();
            colorBox.add(jColorChooser);
        jMenuBar.add(colorBox);

        // TODO 아이콘으로 변경하기
        JButton rect = new JButton("RECT");
            rect.addActionListener(e -> type = MODE.RECT);
        JButton oval = new JButton("OVAL");
            oval.addActionListener(e -> type = MODE.OVAL);
        JButton line = new JButton("LINE");
            line.addActionListener(e -> type = MODE.LINE);
        JButton painter = new JButton("채우기");
            painter.addActionListener(e -> type = MODE.FILL);

        jToolBar.add(rect); jToolBar.add(oval); jToolBar.add(line);
        jToolBar.add(painter);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        figureBox.draw(g);
    }

    // ================= MouseListener start ========================
    class myMouseListener implements MouseListener {
        private Point start, end;
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            start = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            end = e.getPoint();
            if(!start.equals(end)) {
                switch (type) {
                    case RECT:
                        figureBox.add(new Rectangle(start, end)); break;
                    case OVAL:
                        figureBox.add(new Oval(start, end)); break;
                    case LINE:
                        figureBox.add(new Line(start, end)); break;
                    case GROUP:
                        figureBox.addGroup(start, end); break;
                    case DEGROUP:
                        figureBox.removeGroup(start, end); break;
                    default:
                        System.out.println("[MouseReleased] not supported operator! <" + type + ">");
                }
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        Paint main = new Paint();
    }

}
