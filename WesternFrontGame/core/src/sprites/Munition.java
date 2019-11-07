/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Munition extends Rectangle {
    
    private Texture image;
    private Support support;

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
        this.x = support.x + 12;
        this.y = support.y + support.height - 10;
        this.width = support.getImagemBala().getWidth();
        this.height = support.getImagemBala().getHeight();
    }
    
    
}
