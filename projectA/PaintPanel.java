package SwingPaint.projectA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintPanel extends JPanel {
    private final MyMenuBar myMenuBar;
    private final MyToolBar jToolBar;
    private FigureBox figureBox = new FigureBox();
    private final FileHandler fileHandler = new FileHandler();
    private enum MODE {RECT, OVAL, LINE, GROUP, DEGROUP, FILL, COPY, MOVE}
    private MODE type = MODE.RECT;

    public PaintPanel() {
        setLayout(new BorderLayout(0, 0));
        addMouseListener(new myMouseListener());

        myMenuBar = new MyMenuBar(211, 211, 211);
        add(myMenuBar, BorderLayout.NORTH);

        jToolBar = new MyToolBar(null, JToolBar.VERTICAL);
        add(jToolBar, BorderLayout.WEST);

        initMenus();
    }
    private void initMenus() {
        CustomMenu file = new CustomMenu("파일");
        JMenuItem save = new JMenuItem("저장");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        save.addActionListener(e -> {
            fileHandler.addObject(figureBox); fileHandler.save(false);
        });
        JMenuItem saveASnewFile = new JMenuItem("다른 이름으로 저장");
        saveASnewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        saveASnewFile.addActionListener(e -> {
            fileHandler.addObject(figureBox); fileHandler.save(true);
        });

        JMenuItem load = new JMenuItem("불러오기");
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        load.addActionListener(e -> {
            figureBox = fileHandler.load(); repaint();
        });

        file.add(save); file.add(load); file.add(saveASnewFile);
        myMenuBar.add(file);

        CustomMenu selectMenu = new CustomMenu("그룹");
        JMenuItem groupBox = new JMenuItem("그룹화");
        groupBox.addActionListener(e -> type = MODE.GROUP);
        JMenuItem degroupBox = new JMenuItem("그룹화 해제");
        degroupBox.addActionListener(e -> type = MODE.DEGROUP);
        selectMenu.add(groupBox); selectMenu.add(degroupBox);
        myMenuBar.add(selectMenu);

        CustomMenu colorBox = new CustomMenu("색");
        JColorChooser jColorChooser = new JColorChooser();
        jColorChooser.getSelectionModel().addChangeListener(e -> figureBox.setColor(jColorChooser.getColor()));
        colorBox.add(jColorChooser);
        myMenuBar.add(colorBox);

        CustomMenu stroke = new CustomMenu("선 굵기");
        StrokeSlider strokeSlider = new StrokeSlider(1, 20, 1);
        strokeSlider.addChangeListener(e -> figureBox.setStroke(strokeSlider.getValue()));
        stroke.add(strokeSlider);
        myMenuBar.add(stroke);

        // TODO 배포전, path 수정
        // Image파일은 모두 25pix, 22pix을 통일
        String basePath = "src\\SwingPaint\\projectA\\resources\\";
        JButton move = new JButton(new ImageIcon(basePath + "move.png"));
        move.addActionListener(e -> setMode(MODE.MOVE, Cursor.MOVE_CURSOR));
        JButton rect = new JButton(new ImageIcon(basePath + "rect.png"));
        rect.addActionListener(e -> setMode(MODE.RECT, Cursor.CROSSHAIR_CURSOR));
        JButton oval = new JButton(new ImageIcon(basePath + "oval.png"));
        oval.addActionListener(e -> setMode(MODE.OVAL, Cursor.CROSSHAIR_CURSOR));
        JButton line = new JButton(new ImageIcon(basePath + "line.png"));
        line.addActionListener(e -> setMode(MODE.LINE, Cursor.CROSSHAIR_CURSOR));
        JButton painter = new JButton(new ImageIcon(basePath + "paint.png"));
        painter.addActionListener(e -> setMode(MODE.FILL, Cursor.HAND_CURSOR));
        JButton copy = new JButton(new ImageIcon(basePath + "copy.png"));
        copy.setMnemonic('c');
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
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
