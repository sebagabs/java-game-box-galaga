package Game.Galaga.Entities;

import Game.GameStates.PauseState;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.Random;

/**
 * Created by AlexVR on 1/25/2020
 */
public class PlayerShip extends BaseEntity {

    private int health = 3, attackCooldown = 30, speed = 6, destroyedCoolDown = 60 * 7;
    private boolean attacking = false, destroyed = false, godmode = false; //godmode = new debug variable
    private Animation deathAnimation;
    // formation area check
    boolean[][] canSpawn = new boolean[5][8];
    boolean firstFormation = true;
    int count = 0;
    int spawnTimer = random.nextInt(60 * 7);


    public PlayerShip(int x, int y, int width, int height, BufferedImage sprite, Handler handler) {
        super(x, y, width, height, sprite, handler);
        deathAnimation = new Animation(256, Images.galagaPlayerDeath);

    }

    @Override
    public void tick() {

        super.tick();
        //creates formation at start
        while (firstFormation) {
            if (count < 32) {
                spawnTimer = 0;
                enemySpawn("bee");
                enemySpawn("other");
                count++;

            } else {
                firstFormation = false;

            }
//        spawnTimer = random.nextInt(60*7);
        }
        spawnTimer--;


        //TODO this is a pseudo spawner, not sure if valid


        if (destroyed) {
            handler.getScoreManager().setGalagaCurrentScore(0);
            if (health <= 0) {
                handler.getGalagaState().Mode = "GameOver";
            }
            if (destroyedCoolDown <= 0) {
                destroyedCoolDown = 60 * 7;
                destroyed = false;
                deathAnimation.reset();
                bounds.x = x;
            } else {
                deathAnimation.tick();
                destroyedCoolDown--;
            }
        } else {
            if (attacking) {
                if (attackCooldown <= 0) {
                    attacking = false;
                } else {
                    attackCooldown--;
                }
            }
            //added the spacebar as a button to attack with
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !attacking ||
                    handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !attacking) {
                handler.getMusicHandler().playEffect("laser.wav");
                attackCooldown = 30;
                attacking = true;
                handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2), this.y - 3, width / 5, height / 2, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));

            }

            if (handler.getKeyManager().left && x > (handler.getWidth() / 4.92307692) + handler.getWidth() / 2 / 10) {
                x -= speed;
            }
            if (handler.getKeyManager().right && x < (handler.getWidth() / 1.8795889999999999999 + handler.getWidth() / 2 / 3)) {
                x += (speed);
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
                State.setState(handler.getPauseState());
            }

            //Written by Xavier
            //implementing "n" to kill ship; "M" to add a life
            if (handler.DEBUG) {
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
                    health--;
                    destroyed = true;
                    handler.getMusicHandler().playEffect("explosion.wav");
                    bounds.x = -10;
                }
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)) {
                    if (health < 3) {
                        health++;
                    }
                }


                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
                    int row = random.nextInt(2) + 3, column = random.nextInt(8);
                    if (canSpawn[row][column] == false) {
                        handler.getGalagaState().entityManager.entities.add(new EnemyBee(x, y, 32, 32, handler, row, column));
                        canSpawn[row][column] = true;
                    } else {
                        return;
                    }


                }

                if (handler.getKeyManager().keyJustPressed((KeyEvent.VK_O))) {
                    int row = random.nextInt(2) + 1, column = random.nextInt(8);
                    if (canSpawn[row][column] == false) {
                        handler.getGalagaState().entityManager.entities.add(new EnemyOther(x, y, 32, 32, handler, row, column));
                        canSpawn[row][column] = true;
                    } else {
                        return;
                    }
                }

                // enables godmode, granting the player immortality from enemies
                // ONLY WORKS IN DEBUG MODE; IT IS NOT A CHEAT
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)) {
                    godmode = !godmode;
                }
            } else {
                godmode = false;
            }
            bounds.x = x;
        }

    }

    @Override
    public void render(Graphics g) {
        if (godmode) { // Adds an indicator for when in GOD MODE.
            g.setColor(Color.GREEN);
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g.drawString(":GOD MODE:", handler.getWidth() / 4, handler.getHeight() / 10);
        }
        if (destroyed) {
            if (deathAnimation.end) {
                g.drawString("READY", handler.getWidth() / 2 - handler.getWidth() / 12, handler.getHeight() / 2);
            } else {
                g.drawImage(deathAnimation.getCurrentFrame(), x, y, width, height, null);
            }
        } else {
            super.render(g);
        }
    }

    @Override
    public void damage(BaseEntity damageSource) {
        if (damageSource instanceof PlayerLaser || godmode) { // implementing a god-mode enabled by debugging mode(g)
            return;
        }
        health--;
        destroyed = true;
        handler.getMusicHandler().playEffect("explosion.wav");

        bounds.x = -10;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }



    public void afterdeathSpawn(String typeOfEnemy,int row, int col) {
        int spawnTimer = random.nextInt(60*10);
        while(spawnTimer!=0) {
            spawnTimer--;
        }
        if(spawnTimer == 0) {
            if (typeOfEnemy.equals("bee")) {
                handler.getGalagaState().entityManager.lasers.add(new EnemyBee(x, y, 32, 32, handler, row, col));
            } else {
                handler.getGalagaState().entityManager.lasers.add(new EnemyOther(x, y, 32, 32, handler, row, col));
            }
        }

    }





    //TODO made this method to see if it would allow for spawning constantly on it's own
    public void enemySpawn(String typeofEnemy) {
        if (spawnTimer == 0) {
            if (typeofEnemy.equals("bee")) {
                outerloop:
//label to later break when enemy is spawned
                for (int row = 3; row < 5; row++) {
                    for (int column = 0; column < 8; column++) {
                        if (canSpawn[row][column] == false) {
                            handler.getGalagaState().entityManager.entities.add(new EnemyBee(x, y, 32, 32, handler, row, column));
                            canSpawn[row][column] = true;
                            spawnTimer = random.nextInt(60 * 7);
                            break outerloop;
                        } else continue;
                    }
                }
            } else {//ENEMYOTHER
                outerloop:
                for (int row = 1; row < 3; row++) {
                    for (int column = 0; column < 8; column++) {
                        if (canSpawn[row][column] == false) {
                            handler.getGalagaState().entityManager.entities.add(new EnemyOther(x, y, 32, 32, handler, row, column));
                            canSpawn[row][column] = true;
                            spawnTimer = random.nextInt(60 * 7);
                            break outerloop;
                        } else continue;
                    }
                }

            }
        }
        else {
            spawnTimer--;
        }

//        int enemyType = random.nextInt(2);
//        spawnTimer--;
//        if (spawnTimer == 0) {
//            if (typeofEnemy.equals("bee")) {
//                int row = random.nextInt(2) + 3, column = random.nextInt(8);
//                if (canSpawn[row][column] == false) {
//                    handler.getGalagaState().entityManager.entities.add(new EnemyBee(x, y, 32, 32, handler, row, column));
//                    canSpawn[row][column] = true;
//                } else {
//                    return;
//                }
//            } else {
//                int row = random.nextInt(2) + 1, column = random.nextInt(8);
//                if (canSpawn[row][column] == false) {
//                    handler.getGalagaState().entityManager.entities.add(new EnemyOther(x, y, 32, 32, handler, row, column));
//                    canSpawn[row][column] = true;
//                }
//            }
//        }
//        else{
//            spawnTimer--;
//        }




    }
}