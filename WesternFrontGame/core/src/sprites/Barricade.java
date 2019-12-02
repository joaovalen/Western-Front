
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Barricade extends Soldier {
    
    public Barricade() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image     
        super(2600, 0, 0, 0, 50, new Texture(Gdx.files.internal("barricade.png")), null);
    }
    
 
}
