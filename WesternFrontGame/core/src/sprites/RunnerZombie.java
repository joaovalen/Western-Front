
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RunnerZombie extends Zombie{
    
    public RunnerZombie() {
        // Speed / Health / Damage / Atack speed / Textura
        super(26, 200, 100, 1000000000, new Texture(Gdx.files.internal("zombie_runner.png")));
    }
}
