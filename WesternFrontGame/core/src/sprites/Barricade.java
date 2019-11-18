package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import sprites.Soldier;


public class Barricade extends Soldier {
    
    public Barricade() {
        super(64, 81, 4000, 0, new Texture(Gdx.files.internal("barricade.png")), 0, 0, null, 50);
    }
    
    
    
}
