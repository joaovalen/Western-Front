package scenes;

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
        super(40, 17, 500, 140000000000L, new Texture(Gdx.files.internal("landmine.png")), 0, 0, null);
        activated = false;
    }
    
    

    
}
