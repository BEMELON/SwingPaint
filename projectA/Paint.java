package SwingPaint.projectA;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

public class Paint extends JFrame {
    private JMenuBar jMenuBar;
    private JToolBar jToolBar;
    private FigureBox figureBox = new FigureBox();
    private final FileHandler fileHandler = new FileHandler();

    private enum MODE {RECT, OVAL, LINE, GROUP, DEGROUP, FILL, COPY, MOVE}
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

        JMenu selectMenu = new JMenu("그룹");
            JMenuItem groupBox = new JMenuItem("그룹화");
                groupBox.addActionListener(e -> type = MODE.GROUP);
            JMenuItem degroupBox = new JMenuItem("그룹화 해제");
                degroupBox.addActionListener(e -> type = MODE.DEGROUP);
            selectMenu.add(groupBox); selectMenu.add(degroupBox);
        jMenuBar.add(selectMenu);

        JMenu colorBox = new JMenu("색");
            JColorChooser jColorChooser = new JColorChooser();
            jColorChooser.getSelectionModel().addChangeListener(e -> figureBox.setColor(jColorChooser.getColor()));
            colorBox.add(jColorChooser);
        jMenuBar.add(colorBox);

        JMenu stroke = new JMenu("선 굵기");
            StrokeSlider strokeSlider = new StrokeSlider(1, 20, 1);
            strokeSlider.addChangeListener(e -> figureBox.setStroke(strokeSlider.getValue()));
            stroke.add(strokeSlider);
        jMenuBar.add(stroke);
        // TODO 아이콘으로 변경하기
        JButton move = new JButton("MOVE");
            move.addActionListener(e -> setMode(MODE.MOVE, Cursor.MOVE_CURSOR));
        JButton rect = new JButton("RECT");
            rect.addActionListener(e -> setMode(MODE.RECT, Cursor.CROSSHAIR_CURSOR));
        JButton oval = new JButton("OVAL");
            oval.addActionListener(e -> setMode(MODE.OVAL, Cursor.CROSSHAIR_CURSOR));
        JButton line = new JButton("LINE");
            line.addActionListener(e -> setMode(MODE.LINE, Cursor.CROSSHAIR_CURSOR));
        JButton painter = new JButton("채우기");
            painter.addActionListener(e -> setMode(MODE.FILL, Cursor.HAND_CURSOR));
        JButton copy = new JButton("복사");
            copy.addActionListener(e -> setMode(MODE.COPY, Cursor.CROSSHAIR_CURSOR));

        jToolBar.add(rect); jToolBar.add(oval); jToolBar.add(line);
        jToolBar.add(painter); jToolBar.add(move); jToolBar.add(copy);
    }

    private void setMode(MODE mode, int cursorMode) {
        type = mode;
        setCursor(new Cursor(cursorMode));
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
            if(type == MODE.FILL) {
                figureBox.fill(e.getPoint());
            }
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
                    case COPY:
                        figureBox.copy(start, end); break;
                    case MOVE:
                        figureBox.move(start, end); break;
                    default:
                        System.out.println("[MouseReleased] not supported operator! <" + type + ">");
                }
            }
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
