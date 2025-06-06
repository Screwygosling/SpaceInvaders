package SpaceInvaders.src.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class alien extends Rectangle{
    private static final Random random = new Random();
    public LinkedList<Point> projectiles = new LinkedList<>();
    private final int MAX_PROJECTILES = 10;
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
        projectiles.add(new Point(this.x + this.width / 2 - 2, this.y + this.height));
        if (projectiles.size() > MAX_PROJECTILES) {
            projectiles.removeFirst();
        }
    }

    public void updateProjectiles() {
        if (random.nextDouble() < 0.005 && projectiles.size() < MAX_PROJECTILES) {
            shoot();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            Point p = projectiles.get(i);
            p.y += 5;
        }
        projectiles.removeIf(p -> p.y > 1000);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);

        g.setColor(Color.GRAY);
        for (Point p : projectiles) {
            g.fillRect(p.x, p.y, 4, 10);
        }
    }
}
