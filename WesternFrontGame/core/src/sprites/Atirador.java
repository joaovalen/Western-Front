/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Atirador extends Soldier {

    public Atirador() {
        // Health, Reload Time, Bullet Speed, Damage, Cost, Image, Bullet Image 
        // DPM = 800 
        super(300, 1500000000L, 200, 20, 100, new Texture(Gdx.files.internal("soldier.png")), new Texture(Gdx.files.internal("bullet.png")));
        this.height -= 10;
    }
    
   
}
