import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
    JFrame frame = new JFrame();

    public Game() {
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    void render() {
        
}
