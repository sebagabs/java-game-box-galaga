package Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class Images {


    public static BufferedImage titleScreenBackground;
    public static BufferedImage pauseBackground;
    public static BufferedImage selectionBackground;
    public static BufferedImage galagaCopyright;
    public static BufferedImage galagaSelect;
    public static BufferedImage muteIcon;
    public static BufferedImage galagaPlayerLaser;
    public static BufferedImage[] startGameButton;
    public static BufferedImage[] galagaLogo;
    public static BufferedImage[] pauseResumeButton;
    public static BufferedImage[] pauseToTitleButton;
    public static BufferedImage[] pauseOptionsButton;
    public static BufferedImage[] galagaPlayer;
    public static BufferedImage[] galagaPlayerDeath;
    public static BufferedImage[] galagaEnemyDeath;
    public static BufferedImage[] galagaEnemyBee;
    public static BufferedImage[] galagaEnemyOther;
    public static BufferedImage[] galagaRedPlayer;
    public static BufferedImage[] galagaBigPlayer;
    public static BufferedImage[] galagaGreenAlienPlayer;
    public static BufferedImage[] galagaPurpleAlienPlayer;
    public static BufferedImage[] galagaButterflyPlayer;
    public static BufferedImage[] galagaScorpionPlayer;

    public static BufferedImage galagaImageSheet;
    public SpriteSheet galagaSpriteSheet;

    public Images() {

        startGameButton = new BufferedImage[3];
        pauseResumeButton = new BufferedImage[2];
        pauseToTitleButton = new BufferedImage[2];
        pauseOptionsButton = new BufferedImage[2];
        galagaLogo = new BufferedImage[3];
        galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
        galagaPlayerDeath = new BufferedImage[8];
        galagaEnemyDeath = new BufferedImage[5];
        galagaEnemyBee = new BufferedImage[8];
        //added enemy images
        galagaEnemyOther = new BufferedImage[7];
        //red player ship option
        galagaRedPlayer = new BufferedImage[8];
        galagaBigPlayer = new BufferedImage[8];
        galagaGreenAlienPlayer = new BufferedImage[8];
        galagaPurpleAlienPlayer = new BufferedImage[8];
        galagaButterflyPlayer = new BufferedImage[8];
        galagaScorpionPlayer = new BufferedImage[8];



        try {

            startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
            startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
            startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

            titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

            pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

            selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection.png"));

            galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

            galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

            muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));

            galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
            galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
            galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

            pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
            pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

            pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
            pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

            pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
            pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));

            galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
            galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

            galagaPlayer[0] = galagaSpriteSheet.crop(160,55,15,16);

            galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
            galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

            galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
            galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
            galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
            galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
            galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

            galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
            galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
            galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
            galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
            galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
            galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
            galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
            galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);

            //enemy image list
            galagaEnemyOther[0] = galagaSpriteSheet.crop(18,249,12,11);
            galagaEnemyOther[1] = galagaSpriteSheet.crop(42,250,12,11);
            galagaEnemyOther[2] = galagaSpriteSheet.crop(67,250,12,12);
            galagaEnemyOther[3] = galagaSpriteSheet.crop(91,250,11,11);
            galagaEnemyOther[4] = galagaSpriteSheet.crop(115,250,12,12);
            galagaEnemyOther[5] = galagaSpriteSheet.crop(136,250,11,12);
            galagaEnemyOther[6] = galagaSpriteSheet.crop(159,250,11,12);

            //new redPlayerModel
            galagaRedPlayer[0] = galagaSpriteSheet.crop(160,79,15,16);
            //new BigShip
            galagaBigPlayer[0] = galagaSpriteSheet.crop(161,319,13,16);
            //new green alien player
            galagaGreenAlienPlayer[0] = galagaSpriteSheet.crop(161,103,15,16);
            //new purple alien player
            galagaPurpleAlienPlayer[0] = galagaSpriteSheet.crop(161,127,15,16);
            //new butterfly player
            galagaButterflyPlayer[0] = galagaSpriteSheet.crop(162,154,13,10);
            //new scorpion player
            galagaScorpionPlayer[0] = galagaSpriteSheet.crop(162,226,11,11);


            galagaPlayerLaser = galagaSpriteSheet.crop(365 ,219,3,8);



        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
