package SpaceInvaders.src.com;
import java.awt.Color;
import javax.swing.*;

public class invadersframe extends JFrame {
    invaderspanel panel = new invaderspanel();
    invadersframe() {
        this.add(panel);
        this.setTitle("Space Invaders");
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
