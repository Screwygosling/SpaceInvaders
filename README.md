# Space Invaders Java [NOT FINISHED]

A simple Space Invaders clone written in Java using Swing.  
This project features a player-controlled ship, alien enemies, and shooting mechanics.

## Features

- Player movement (left, right, up, down)
- Shooting projectiles (with a limit and cooldown)
- Alien enemies that move and can be extended to shoot or interact
- Basic collision logic for player and aliens

## How to Run

1. **Clone or download the repository.**
2. **Compile the Java files:**
   ```sh
   javac src/com/*.java
   ```
3. **Run the main class (replace `MainClass` with your actual main class name):**
   ```sh
   java -cp src com.MainClass
   ```

## Player Controls

- **Arrow keys:** Move the player ship
- **Space bar:** Shoot (one shot per key press)

## Player Class Overview

- **Movement:**  
  The player can move in all four directions using the arrow keys.
- **Shooting:**  
  Pressing space adds a projectile to the game. The player can only shoot once per key press (no holding down for rapid fire).
- **Projectile Limit:**  
  Only a maximum number of projectiles (default: 10) can exist at once; older ones are removed as new ones are fired.
- **Projectile Update:**  
  Projectiles move upward and are removed when they leave the screen.

## Example: Shooting Logic

```java
public void keyPressed (KeyEvent e) {
    switch (e.getKeyCode()) {
        case KeyEvent.VK_SPACE:
            if (canShoot) {
                shoot();
                canShoot = false;
            }
            break;
    }
}

public void KeyReleased (KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        canShoot = true;
    }
}
```

## Customization

- Change `MAX_PROJECTILES` in `Player.java` to allow more or fewer shots.
- Adjust `speed` for faster or slower player movement.
- Modify projectile speed in `updateProjectiles()`.

## License

Nada, zero, zilch! Go crazy!
