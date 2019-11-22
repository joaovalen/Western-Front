/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.math.Rectangle;

public class Munition extends Rectangle {
    
    private Texture image;
    private Support support;
    private int stopY;

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Munition(Support support) {
        this.image = support.getImagemBala();
        this.support = support;
        this.x = support.x + 6;
        this.y = support.y + support.height + 30;
        this.width = support.getImagemBala().getWidth();
        this.height = support.getImagemBala().getHeight();
    }
    
    public Munition(){
        this.image = new Texture(Gdx.files.internal("munition.png"));
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = random(230,700);
        this.y = Gdx.graphics.getWidth();
        this.stopY = random(70, 350);
    }

    public int getStopY() {
        return stopY;
    }

    public void setStopY(int stopY) {
        this.stopY = stopY;
    }
    
    
}
