package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import sprites.Soldier;


public class LandMine extends Soldier {

    private long armTime;
    private boolean activated;

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
    
    public LandMine() {
        super(40, 17, 500, 1000000000, new Texture(Gdx.files.internal("landmineOff.png")), 0, 500, null);
        activated = false;
    }
    
    

    
}
