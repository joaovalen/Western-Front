
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LandMine extends Soldier {

    private long armTime;
    private boolean activated;
    
    public LandMine() {
        // Health, Reaload Time, Bullet Speed, Damage, Cost, Image, Bullet Image     
        super(300, 400000000L, 0, 500, 25, new Texture(Gdx.files.internal("landmineOff.png")), null);
        activated = false;
    }
    
    public long getArmTime() {
        return armTime;
    }

    public void setArmTime(long armTime) {
        this.armTime = armTime;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    
}
