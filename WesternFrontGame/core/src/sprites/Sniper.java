
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sniper extends Soldier{
    
    public Sniper() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image     
        // DPM = 1200
        super(300, 2500000000L, 800, 50, 175, new Texture(Gdx.files.internal("sniper.png")), new Texture(Gdx.files.internal("bullet.png")));
    }

    
}
