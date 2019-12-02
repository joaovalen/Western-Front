
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RegularZombie extends Zombie {
    
    public RegularZombie() {
        // Speed / Health / Damage / Atack speed / Textura
        super(12, 200, 100, 1000000000, new Texture(Gdx.files.internal("zombie_soldier.png")));
    }
}
