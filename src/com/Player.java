package SpaceInvaders.src.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Player extends Rectangle{

    private LinkedList<Point> projectiles = new LinkedList<>();
    private int speed = 20;
    private int health = 3;
    private boolean canShoot = true;
    int yVelocity;
    int xVelocity;
    private final int MAX_PROJECTILES = 10;

    Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public int getHealth() {
        return health;
    }

    public void setPosition() {
        this.x = 0;
        this.y = 0;
    }

    public void moveLeft() {
        this.x -= speed;
    }

    public void moveRight() {
        this.x += speed;
    }

    public void moveUp() {
        this.y -= speed;
    }

    public void moveDown() {
        this.y += speed;
    }

    public void setYDirection(int yDirection) {
        this.yVelocity = yDirection;
    }

    public void setXDirection(int xDirection) {
        this.xVelocity = xDirection;
    }

    public void move () {
        y = y + yVelocity;
        x = x + xVelocity;
    }

    public void shoot() {
        projectiles.add(new Point((int)this.x, (int)this.y));
        if (projectiles.size() > MAX_PROJECTILES) {
            projectiles.removeFirst();
        }
    }

    public void updateProjectiles() {
        for (int i = 0; i < projectiles.size(); i++) {
            Point p = projectiles.get(i);
            p.y -= 10;
        }
        projectiles.removeIf(p -> p.y < 0);
    }

    public void keyPressed (KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_SPACE:
                if (canShoot) {
                    shoot();
                    canShoot = false;
                }
                break;
        }
    }

    public void KeyReleased (KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                setYDirection(0);
                break;
            case KeyEvent.VK_DOWN:
                setYDirection(0);
                break;
            case KeyEvent.VK_LEFT:
                setXDirection(0);
                break;
            case KeyEvent.VK_RIGHT:
                setXDirection(0);
                break;
            case KeyEvent.VK_SPACE:
                canShoot = true;
                break;
        }
    }
 
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.width, this.height);

        g.setColor(Color.YELLOW);
        for (Point p : projectiles) {
            g.fillRect(p.x + this.width / 2 - 2, p.y, 4, 10);
        }
    }
}
