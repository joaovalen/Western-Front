/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Barricade extends Soldier {
    
    public Barricade() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image     
        super(4000, 0, 0, 0, 50, new Texture(Gdx.files.internal("barricade.png")), null);
    }
    
 
}
