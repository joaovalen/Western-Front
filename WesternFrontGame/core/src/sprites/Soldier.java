package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Soldier extends Rectangle {

    private int health;
    private long reloadTime;
    private int bulletSpeed;
    private int damage;
    private int cost;
    private Texture imagem;
    private Texture imagemBala;
    private long lastShotTime;
    private Square square;
    private boolean firstAttack;
    
    public Soldier(int health, long reloadTime, int bulletSpeed, int damage, int cost, Texture imagem, Texture imagemBala) {
            this.imagem = imagem;
            this.width = imagem.getWidth(); 
            this.height = imagem.getHeight();
            this.imagem = imagem;
            this.health = health;
            this.reloadTime = reloadTime;
            this.bulletSpeed = bulletSpeed;
            this.damage = damage;
            this.imagemBala = imagemBala;
            this.cost = cost;
            firstAttack = false;
            lastShotTime = TimeUtils.nanoTime();
        }
    
    public Soldier(){
    
    }
   
    public boolean isFirstAttack() {
        return firstAttack;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public long getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(long reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getHealth() {
        return health;
    }

    public Texture getImagem() {
        return imagem;
    }

    public void setImagem(Texture imagem) {
        this.imagem = imagem;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Texture getImagemBala() {
        return imagemBala;
    }

    public void setImagemBala(Texture imagemBala) {
        this.imagemBala = imagemBala;
    }
    
    
}
