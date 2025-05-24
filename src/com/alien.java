package SpaceInvaders.src.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class alien extends Rectangle{
    private static final Random random = new Random();
    int xVelocity;

    alien(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public  void move() {
        int randomXDirection = random.nextBoolean() ? 1 : -1;
        this.x += randomXDirection * 2;
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void shoot() {
        
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
