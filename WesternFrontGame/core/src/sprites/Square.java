
package sprites;

import com.badlogic.gdx.math.Rectangle;

public class Square extends Rectangle {
    
    private boolean occupied;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        occupied = false;
    }
    

}
