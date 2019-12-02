
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Support extends Soldier{
    
    private boolean hasMunition = false;

    public Support() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image                       
        super(300, 16000000000L, 1000, 500, 50, new Texture(Gdx.files.internal("suporte.png")), new Texture(Gdx.files.internal("munition.png")));
        this.height -= 30;
    }
    
    public boolean getHasMunition() {
        return hasMunition;
    }

    public void setHasMunition(boolean hasMunition) {
        this.hasMunition = hasMunition;
    }  
    
    
}
