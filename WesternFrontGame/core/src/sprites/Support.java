/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Support extends Soldier{
    
    private boolean hasMunition;
    
    public Support() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image                       
        super(300, 5000000000L, 1000, 500, 50, new Texture(Gdx.files.internal("suporte.png")), new Texture(Gdx.files.internal("munition.png")));
        hasMunition = false;
        this.height -= 30;
    }
    
    public boolean getHasMunition() {
        return hasMunition;
    }

    public void setHasMunition(boolean hasMunition) {
        this.hasMunition = hasMunition;
    }  
    
}
