/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import sprites.Soldier;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author 05200244
 */
public class Atirador extends Soldier {

    public Atirador() {
        super(64, 92, 300, 10000L, new Texture(Gdx.files.internal("soldier.png")), 200, 100, new Texture(Gdx.files.internal("bullet.png")), 100);
    }
    
    
}
