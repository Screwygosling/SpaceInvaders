package SpaceInvaders.src.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JPanel;

public class invaderspanel extends JPanel implements Runnable {
    private final int WIN_WIDTH = 1000;
    private final int WIN_HEIGHT = WIN_WIDTH;
    private final Dimension WINDOW = new Dimension(WIN_HEIGHT, WIN_WIDTH);
    
    private java.util.List<alien> aliens = new java.util.ArrayList<>();

    Player player;
    
    
    Thread thread;
    Graphics g;
    private Image ship;
    private Image background;

    invaderspanel() {
        this.setPreferredSize(WINDOW);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        
        createObjects();

        this.addKeyListener(new ActionListener());

        thread = new Thread(this);
        thread.start();
    }
    
    public void createObjects() {
        player = new Player(500, 750, 50, 50);

        //first row of aliens
        aliens.add(new alien(100, 100, 50, 50));
        aliens.add(new alien(200, 100, 50, 50));
        aliens. add(new alien(300, 100, 50, 50));
        aliens.add(new alien(400, 100, 50, 50));
        aliens.add(new alien(500, 100, 50, 50));
        aliens.add(new alien(600, 100, 50, 50));
        aliens.add(new alien(700, 100, 50, 50));
        aliens.add(new alien(800, 100, 50, 50));
        aliens.add(new alien(900, 100, 50, 50));

        //second row of aliens
        
    }

    public void  update() {
        player.move();
        player.updateProjectiles();
        aliens.forEach(alien -> {
            alien.move();
        });
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
        if (player.x + player.width <= 0) {
            player.x = WIN_WIDTH - player.width;
        }
        if (player.x >= WIN_WIDTH) {
            player.x = -player.width;
        }
        aliens.forEach(alien -> {
            if (alien.x + alien.width <= 0) {
                alien.x = WIN_WIDTH - alien.width;
            }
            if (alien.x >= WIN_WIDTH) {
                alien.x = -alien.width;
            }
        });

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
