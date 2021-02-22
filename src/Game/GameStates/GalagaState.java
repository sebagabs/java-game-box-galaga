package Game.GameStates;

import Game.Galaga.Entities.EnemyBee;
import Game.Galaga.Entities.EntityManager;
import Game.Galaga.Entities.PlayerShip;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.Random;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GalagaState extends State {

    public EntityManager entityManager;
    public String Mode = "Menu";
    private Animation titleAnimation;
    public int selectPlayers = 1, shipSwitcher = 0, startCooldown = 60*7;
    public BufferedImage shipOptions;

    public GalagaState(Handler handler) {
        super(handler);
        refresh();

        titleAnimation = new Animation(256,Images.galagaLogo);
    }


    @Override
    public void tick() {
        if (Mode.equals("Stage")){
            if (startCooldown<=0) {
                entityManager.tick();
                //implementing the permanent high score manager
                if (handler.getScoreManager().getGalagaCurrentScore() > handler.getScoreManager().getGalagaHighScore()) {
                    handler.getScoreManager().setGalagaHighScore(handler.getScoreManager().getGalagaCurrentScore());
                }
                else {
                }

            }else{
                startCooldown--;
            }
        }else{
            titleAnimation.tick();
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selectPlayers=1;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selectPlayers=2;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                    Mode = "Stage";
                    handler.getMusicHandler().playEffect("Galaga.wav");
            }
            switch (shipSwitcher) {
                case 0:
                    shipOptions = Images.galagaPlayer[0];
                    break;
                case 1:
                    shipOptions = Images.galagaRedPlayer[0];
                    break;
                case 2:
                    shipOptions = Images.galagaBigPlayer[0];
                    break;
                case 3:
                    shipOptions = Images.galagaGreenAlienPlayer[0];
                    break;
                case 4:
                    shipOptions = Images.galagaPurpleAlienPlayer[0];
                    break;
                case 5:
                    shipOptions = Images.galagaButterflyPlayer[0];
                    break;
                case 6:
                    shipOptions = Images.galagaScorpionPlayer[0];
                    break;
            }
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
                shipSwitcher++;
                if(shipSwitcher > 6) shipSwitcher = 0;

            }

        }

    }

    @Override
    public void render(Graphics g) {
        //changes colors of edges/borders
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        //changes color of actual stage/where the ship is
        g.setColor(Color.black);
        g.fillRect(handler.getWidth() / 4, 0, handler.getWidth() / 2, handler.getHeight());
        Random random = new Random(System.nanoTime());
        // takes care of the random dots in the background
        for (int j = 1; j < random.nextInt(15) + 60; j++) {
            switch (random.nextInt(6)) {
                case 0:
                    g.setColor(Color.RED);
                    break;
                case 1:
                    g.setColor(Color.BLUE);
                    break;
                case 2:
                    g.setColor(Color.YELLOW);
                    break;
                case 3:
                    g.setColor(Color.GREEN);
                    break;
                //added white and magenta to background
                case 4:
                    g.setColor(Color.WHITE);
                    break;
                case 5:
                    g.setColor(Color.MAGENTA);
            }
            int randX = random.nextInt(handler.getWidth() - handler.getWidth() / 2) + handler.getWidth() / 4;
            int randY = random.nextInt(handler.getHeight());
            g.fillRect(randX, randY, 2, 2);

        }

        //TESTING GAME OVER MODE
        //TODO TODO TODO
        if (Mode.equals("GameOver")) {
            entityManager.render(g);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 62));
            g.drawString("HIGH", handler.getWidth() - handler.getWidth() / 4, handler.getHeight() / 16);
            g.setColor(Color.MAGENTA);
            g.drawString("SCORE", handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48, handler.getHeight() / 8);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()), handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48, handler.getHeight() / 5);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.setColor(Color.MAGENTA);
            g.drawString("SCORE:",handler.getWidth()/2-handler.getWidth()/18,32);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaCurrentScore()),handler.getWidth()/2-32,64);
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("GAME OVER",handler.getWidth() / 4,  handler.getHeight() / 2);
            g.drawString("Press 'Y' to play again",handler.getWidth()/4,handler.getHeight()/2+handler.getHeight()/12);
            if( handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)) {
                Mode = "Menu";
            }


        }

        //changes background color as well as text in game
        if (Mode.equals("Stage")) {
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 62));
            g.drawString("HIGH", handler.getWidth() - handler.getWidth() / 4, handler.getHeight() / 16);
            g.setColor(Color.MAGENTA);
            g.drawString("SCORE", handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48, handler.getHeight() / 8);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()), handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48, handler.getHeight() / 5);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.setColor(Color.MAGENTA);
            g.drawString("SCORE:",handler.getWidth()/2-handler.getWidth()/18,32);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaCurrentScore()),handler.getWidth()/2-32,64);
            //Written by Xavier
            //debug mode switcher
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SLASH) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_BACK_SLASH)) {
                    handler.DEBUG =! handler.DEBUG;
            }
            if (handler.DEBUG) { // tells user the game is in debug mode
                g.setColor(Color.YELLOW);
                g.setFont(new Font("TimesRoman", Font.BOLD, 30));
                g.drawString("DEBUG MODE", handler.getWidth() / 4, handler.getHeight() / 16);
            }

        for (int i = 0; i < entityManager.playerShip.getHealth(); i++) {
            g.drawImage(shipOptions, (handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48) + ((entityManager.playerShip.width * 2) * i), handler.getHeight() - handler.getHeight() / 4, handler.getWidth() / 18, handler.getHeight() / 18, null);

        }
        if (startCooldown <= 0) {
            entityManager.render(g);
        } else {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
            g.setColor(Color.MAGENTA);
            g.drawString("Start", handler.getWidth() / 2 - handler.getWidth() / 18, handler.getHeight() / 2);
            g.setColor(Color.MAGENTA);
            g.drawString("Have fun!", handler.getWidth() / 2 - handler.getWidth() / 17, (int) (handler.getHeight() / 1.5));
        }
        }else if (Mode.equals("Menu")) {
            // this is the menu
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.setColor(Color.MAGENTA);
            g.drawString("HIGH-SCORE:",handler.getWidth()/2-handler.getWidth()/18,32);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()),handler.getWidth()/2-32,64);

            g.drawImage(titleAnimation.getCurrentFrame(),handler.getWidth()/2-(handler.getWidth()/12),handler.getHeight()/2-handler.getHeight()/3,handler.getWidth()/6,handler.getHeight()/7,null);

            g.drawImage(Images.galagaCopyright,handler.getWidth()/2-(handler.getWidth()/8),handler.getHeight()/2 + handler.getHeight()/3,handler.getWidth()/4,handler.getHeight()/8,null);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
            g.drawString("1   PLAYER",handler.getWidth()/2-handler.getWidth()/16,handler.getHeight()/2);
            g.drawString("2   PLAYER",handler.getWidth()/2-handler.getWidth()/16,handler.getHeight()/2+handler.getHeight()/12);
            g.drawImage(shipOptions,handler.getWidth()/2+handler.getWidth()/6,handler.getHeight()/2,50,50,null);
            entityManager = new EntityManager(new PlayerShip(handler.getWidth() / 2 - 64, handler.getHeight() - handler.getHeight() / 7, 64, 64, shipOptions, handler));

            if (selectPlayers == 1){
                g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2-handler.getHeight()/32,32,32,null);

            }else{
                g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2+handler.getHeight()/18,32,32,null);
            }


        }
    }

    @Override
    public void refresh() {



    }
}
