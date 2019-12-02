
package sprites;

import com.badlogic.gdx.math.Rectangle;

public class Square extends Rectangle {
    
    private boolean occupied;
    private Soldier soldier;
    
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        occupied = false;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    

}
