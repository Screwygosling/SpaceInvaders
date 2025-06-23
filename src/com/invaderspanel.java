package SpaceInvaders.src.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;

import javax.swing.JPanel;


public class invaderspanel extends JPanel implements Runnable {
    private final int WIN_WIDTH = 1000;
    private final int WIN_HEIGHT = WIN_WIDTH;
    private final Dimension WINDOW = new Dimension(WIN_HEIGHT, WIN_WIDTH);
    
    private java.util.List<alien> aliens = new java.util.ArrayList<>();

    Player player;
    health health;
    Item item;
    Random Random = new Random();

    private int itemNum = 3;


    private long startTime;
    
    Thread thread;
    Graphics g;


    invaderspanel() {
        this.setPreferredSize(WINDOW);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);

        startTime = System.currentTimeMillis();
        
        createObjects();
        health = new health(WIN_WIDTH, WIN_HEIGHT);
  
        this.addKeyListener(new ActionListener());

        thread = new Thread(this);
        thread.start();
    }
    
    public double elapsedTime() {
        long elapsedMillis = System.currentTimeMillis() - startTime;
        double elapsedSeconds = elapsedMillis / 1000.0;
        return elapsedSeconds;
    }

    public void createObjects() {
        

        
        player = new Player(500, 750, 50, 50);

        //first row of aliens
        aliens.add(new alien(100, 100, 50, 50));
        aliens.add(new alien(200, 100, 50, 50));
        aliens.add(new alien(300, 100, 50, 50));
        aliens.add(new alien(400, 100, 50, 50));
        aliens.add(new alien(500, 100, 50, 50));
        aliens.add(new alien(600, 100, 50, 50));
        aliens.add(new alien(700, 100, 50, 50));
        aliens.add(new alien(800, 100, 50, 50));
        aliens.add(new alien(900, 100, 50, 50));

        //second row of aliens
        aliens.add(new alien(100, 200, 50, 50));
        aliens.add(new alien(200, 200, 50, 50));
        aliens.add(new alien(300, 200, 50, 50));
        aliens.add(new alien(400, 200, 50, 50));
        aliens.add(new alien(500, 200, 50, 50));

        loadAlienImages();
        loadPlayerImage();
    }

    public void loadAlienImages() {
        aliens.forEach(alien -> {
            alien.loadImages();
        });
    }

    public void loadPlayerImage() {
        player.loadImage();
    }

    public void checkHealth() {
        if (health.playerhealth <= 0) {
            player.setPosition();
            health.playerhealth = 3;
            aliens.clear();
            createObjects();
        }
    }

    public void  update() {
        player.move();
        player.updateProjectiles();
        aliens.forEach(alien -> {
            alien.move();
        });
        aliens.forEach(alien -> {
            alien.updateProjectiles();
        });
        if (elapsedTime() > 3 && item == null && itemNum > 0) {
            itemNum--;
            item = new Item(Random.nextInt(WIN_WIDTH - 50), Random.nextInt((WIN_HEIGHT / 2) - 50), 10, 10);
        }
        checkHealth();
        if (item != null) item.move();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (player != null) {
            player.draw(g);
        }
        aliens.forEach(alien -> {
            alien.draw(g);
        });
        health.draw(g);
        if (item != null) item.draw(g);
    }
    
    public void run(){
        long lastTime = System.nanoTime();
        double targetFPS = 60.0;
        double ns = 1_000_000_000 / targetFPS;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public void checkCollision() {
        // collision detection for player and walls
        if (player.y + player.height <= 0) {
            player.y = WIN_HEIGHT - player.height;
        }
        if (player.y >= WIN_HEIGHT) {
            player.y = -player.height;
        }
        if (player.x + player.width <= 0) {
            player.x = WIN_WIDTH - player.width;
        }
        if (player.x >= WIN_WIDTH) {
            player.x = -player.width;
        }

        // collision detection for aliens and walls
        aliens.forEach(alien -> {
            if (alien.x + alien.width <= 0) {
                alien.x = alien.x * 2;
            }
            if (alien.x >= WIN_WIDTH) {
                alien.x = alien.x / 2;
            }
        });

        // collision detection between player and aliens
        for (int i = 0; i < aliens.size(); i++) {
            for (int j = i + 1; j < aliens.size(); j++) {
                alien a1 = aliens.get(i);
                alien a2 = aliens.get(j);
                if (a1.intersects(a2)) {
                    if (a1.x < a2.x) {
                        a1.x -= a1.width / 2;
                        a2.x += a2.width / 2;
                    } else {
                        a1.x += a1.width / 2;
                        a2.x -= a2.width / 2;
                    }
                }
            }
        }

        // collision detection between player and alien projectiles
        for (alien a : aliens) {
            for (int i = 0; i < a.projectiles.size(); i++) {
                Point p = a.projectiles.get(i);
                if (player.intersects(new Rectangle(p.x, p.y, 4, 10))) {
                    player.x = WIN_WIDTH / 2 - player.width / 2;
                    player.y = WIN_HEIGHT - player.height - 10;
                    a.projectiles.remove(i);
                    i--;
                    health.playerhealth--;
                }
            }
        }

        // collision between player projectiles and aliens
        for (int i = 0; i < player.projectiles.size(); i++) {
            Point p = player.projectiles.get(i);
            Rectangle projectileRect = new Rectangle(p.x, p.y, 4, 10);
            for (int j = 0; j < aliens.size(); j++) {
                alien a = aliens.get(j);
                if (a.intersects(projectileRect)) {
                    aliens.remove(j);
                    player.projectiles.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public class ActionListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.KeyReleased(e);
        }
    }
}
