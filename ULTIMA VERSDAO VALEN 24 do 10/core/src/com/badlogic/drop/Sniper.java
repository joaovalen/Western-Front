/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author 05200244
 */
public class Sniper extends Soldier{
    
    public Sniper() {
        super(64, 92, 300, 1000000000, new Texture(Gdx.files.internal("sniper.png")), 1000, 500, new Texture(Gdx.files.internal("bullet.png")));
    }
    
}
