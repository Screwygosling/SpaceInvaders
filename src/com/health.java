package SpaceInvaders.src.com;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;


public class health extends Rectangle {
    static int WIN_HEIGHT;
    static int WIN_WIDTH;
    int playerhealth = 3;;

    health(int WIN_HEIGHT, int WIN_WIDTH) {
        health.WIN_HEIGHT = WIN_HEIGHT;
        health.WIN_WIDTH = WIN_WIDTH;
        this.playerhealth = 3;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        g.drawString("Health: ", WIN_WIDTH - 200, 50);
        g.setColor(Color.RED);
        g.drawString(String.valueOf(playerhealth), WIN_WIDTH - 100, 50);
    }
}
