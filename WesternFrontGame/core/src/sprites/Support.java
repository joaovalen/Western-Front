/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Support extends Soldier{
    
    public Support() {
        super(64, 92, 300, 4000000000L, new Texture(Gdx.files.internal("support-removebg-preview (1).png")), 1000, 500, new Texture(Gdx.files.internal("munition.png")));
    }
    
}
