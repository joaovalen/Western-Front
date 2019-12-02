
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TankZombie extends Zombie {
    
     public TankZombie() {
        // Speed / Health / Damage / Atack speed / Textura
        super(7, 800, 100, 1000000000, new Texture(Gdx.files.internal("zombie_tank.png")));
    }
     
     
}
