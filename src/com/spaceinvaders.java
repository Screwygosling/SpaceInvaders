package SpaceInvaders.src.com;

import java.awt.EventQueue;

public class spaceinvaders {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new invadersframe();
            frame.setVisible(true);
        });
    }
}