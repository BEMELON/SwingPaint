import javax.swing.*;

public class Paint extends JFrame {
    public Paint() {
        setLocation(0,0);
        setSize(1920 / 2, 1080 / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Paint main = new Paint();
    }
}
