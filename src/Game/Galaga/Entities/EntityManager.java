package Game.Galaga.Entities;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexVR on 1/25/2020
 */
public class EntityManager {

    public ArrayList<BaseEntity> entities;
    //new array list for the enemy lasers
    public ArrayList<BaseEntity> lasers;
    public PlayerShip playerShip;
    public EnemyLaser enemyLaser;

    public EntityManager(PlayerShip playerShip) {
        entities = new ArrayList<>();
        // new array list for enemylasers
        lasers = new ArrayList<>();
        this.playerShip = playerShip;
    }

    public void tick(){
        playerShip.tick();
        ArrayList<BaseEntity> toRemove = new ArrayList<>();
        for (BaseEntity laser: lasers) {
            //first add the lasers generated onto the laser array
            //then move them into the entity list
            entities.add(laser);
        }
        lasers.remove(enemyLaser);
        for (BaseEntity entity: entities){
            if (entity.remove){
                toRemove.add(entity);
                continue;
            }
            entity.tick();
            if (entity.bounds.intersects(playerShip.bounds)){
                playerShip.damage(entity);
            }
        }
        for (BaseEntity toErase:toRemove){
            entities.remove(toErase);
            //TODO verify if the lasers are being removed properly
            lasers.remove(toErase);
            lasers.clear();
        }

    }

    public void render(Graphics g){
        for (BaseEntity entity: entities){
            entity.render(g);
        }
        playerShip.render(g);

    }

}
