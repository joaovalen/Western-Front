/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author estagiario.dti
 */
public class TankZombie extends Zombie {
    
     public TankZombie() {
        super(20, 500, 100, 1000000000, new Texture(Gdx.files.internal("zombie_tank.png")));
    }
}
