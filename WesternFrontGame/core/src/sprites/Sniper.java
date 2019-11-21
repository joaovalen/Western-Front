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
 * @author 05200244
 */
public class Sniper extends Soldier{
    
    public Sniper() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image     
        super(300, 4000000000L, 1000, 500, 150, new Texture(Gdx.files.internal("sniper.png")), new Texture(Gdx.files.internal("bullet.png")));
    }
    
}
