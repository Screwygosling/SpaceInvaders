package SpaceInvaders.src.com;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Item extends Rectangle{
    int speed = 5;
    
    Item(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move() {
        this.y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }
}
