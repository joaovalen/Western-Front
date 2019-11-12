package scenes;

import sprites.Atirador;
import sprites.Shoot;
import sprites.Sniper;
import sprites.Soldier;
import sprites.Zombie;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;
import sprites.Munition;
import sprites.Support;

public class GameScreen implements Screen {

    private final WesternFront game;
    // Textures
    private final Texture shootImage;
    private final Texture zombieImage;
    private final Texture soldierImage;
    private final Texture background;
    // Camera and Sprite Batch
    private OrthographicCamera camera;
    private SpriteBatch batch;
    // Objects
    private Array<Shoot> shoots;
    private long lastDropTime;
    private Array<Zombie> zombies;
    private long lastZombieTime;
    private State state;
    private BitmapFont font;
    private int count_raindrops;
    private int count_zombies;
    private final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private Array<Soldier> soldiers;
    private int count_soldier;
    private ShapeRenderer sR;
    private Actor actor;
    private int currency;
    private Array<Munition> munitions;
    private Texture munitionImage;
    private boolean clickedOnAmmo;
    private String teclaAtual;
    private Texture barricadeImage;

    public GameScreen(final WesternFront game) {
        this.game = game;
	//set initial state
	state = State.RUN;
        // Creating Textures
        shootImage = new Texture(Gdx.files.internal("bullet.png"));
        zombieImage = new Texture(Gdx.files.internal("zombie.png"));
        soldierImage = new Texture(Gdx.files.internal("soldier.png"));
        background = new Texture(Gdx.files.internal("mapapronto.png"));
        munitionImage = new Texture(Gdx.files.internal("munition.png"));

        // Loading Music and Effects 
//        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("Akatsuki.mp3"));
        

        // start the playback of the background music immediately
//        rainMusic.setLooping(true);
//        rainMusic.play();

        //create the Camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        sR = new ShapeRenderer();

        //creating the bucket
//        bucket = new Rectangle();
//        bucket.x = 800 / 2 - 64 / 2;
//        bucket.y = 20;
//        bucket.width = 64;
//        bucket.height = 64;
        font = new BitmapFont();
        shoots = new Array<>();
        count_raindrops = 0;
        zombies = new Array<>();
        munitions = new Array<>();
        count_zombies = 0;
        currency = 0;
        soldiers = new Array<>();
        teclaAtual = "";

    }

    private void spawnAtirador(Vector3 pos) {
        Atirador soldier = new Atirador();
        soldier.x = pos.x - soldier.width/2;
        soldier.y = pos.y - soldier.height/2;
        soldiers.add(soldier);
    }
    
    private void spawnSniper(Vector3 pos) {
        Sniper soldier = new Sniper();
        soldier.x = pos.x - soldier.width/2;
        soldier.y = pos.y - soldier.height/2;
        soldiers.add(soldier);
    }
    
    private void spawnZombie() {
        Zombie zombie = new Zombie();
        zombie.x = Gdx.graphics.getWidth();
        zombie.y = MathUtils.random(0, Gdx.graphics.getHeight() - 97);
        zombies.add(zombie);
        lastZombieTime = TimeUtils.nanoTime();
    }
    
    private void spawnShoot(Soldier soldier) {
        Shoot shoot = new Shoot();
        shoot.x = soldier.x + soldier.width/2;
        shoot.y = soldier.y + soldier.height/2 - 9;
        shoot.setDamage(soldier.getDamage());
        shoot.setSpeed(soldier.getBulletSpeed());
        shoot.setImagem(soldier.getImagemBala(  ));
        shoots.add(shoot);
        soldier.setLastShotTime(TimeUtils.nanoTime());
    }
    
    private void spawnSupport(Vector3 pos){
        Support soldier = new Support();
        soldier.x = pos.x - soldier.width/2;
        soldier.y = pos.y - soldier.height/2;
        soldiers.add(soldier);
        
    }
    
    private void spawnMunition(Support soldier){
        Munition munition = new Munition(soldier);
        soldier.setHasMunition(true);
        soldier.setLastShotTime(TimeUtils.nanoTime());
        munitions.add(munition);
    }
    
    private void spawnBarricade(Vector3 pos){
        Barricade soldier = new Barricade();
        soldier.x = pos.x - soldier.width/2;
        soldier.y = pos.y - soldier.height/2;
        soldiers.add(soldier);
    }
    
    private void spawnLandMine(Vector3 pos){
        LandMine soldier = new LandMine();
        soldier.setArmTime(TimeUtils.nanoTime());
        soldier.x = pos.x - soldier.width/2;
        soldier.y = pos.y - soldier.height/2;
        soldiers.add(soldier);        
    }
    
    private void updateZombies(){
        for (Iterator<Zombie> zb = zombies.iterator(); zb.hasNext(); ) {
            Zombie zombie = zb.next();
            zombie.x += -zombie.getSpeed() * Gdx.graphics.getDeltaTime();
            if (zombie.x < 0 - zombie.width){
                zb.remove();
            }
            for (Iterator<Soldier> it = soldiers.iterator(); it.hasNext(); ){
                Soldier soldier = it.next();

                if(zombie.overlaps(soldier)){
                    zombie.x += zombie.getSpeed() * Gdx.graphics.getDeltaTime();
                    
                    if(soldier.getClass().getSimpleName().equals("LandMine")){
                        LandMine land = (LandMine) soldier;
                        if (land.isActivated() == true) {
                            zombie.setHealth(zombie.getHealth() - land.getDamage());
                            it.remove();
                        }
                    }else if (TimeUtils.nanoTime() - zombie.getLastAttackTime() > zombie.getReloadTime()) {
                        if(zombie.getFirstAttack() == true)
                            soldier.setHealth(soldier.getHealth() - zombie.getDamage());
                            zombie.setLastAttackTime(TimeUtils.nanoTime());  
                            if (soldier.getHealth() <= 0) {
                                it.remove();
                                zombie.setFirstAttack(false);
                            }
                        zombie.setFirstAttack(true);
                    }
                }
            }
            font.draw(batch, Integer.toString(zombie.getHealth()), zombie.x + 20, zombie.y + zombie.getHeight() + 15);
        }        
    }
    
    private void updateShoots(){
        for (Iterator<Shoot> it = shoots.iterator(); it.hasNext(); ) {
            Shoot shoot = it.next();
            shoot.x += shoot.getSpeed() * Gdx.graphics.getDeltaTime();
            //check if it is beyond the screen
            if (shoot.x > Gdx.graphics.getWidth())
                it.remove();
            for (Iterator<Zombie> zb = zombies.iterator(); zb.hasNext(); ) {
                Zombie zombie = zb.next();
                if(zombie.overlaps(shoot)){
                    it.remove();
                    zombie.setHealth(zombie.getHealth() - shoot.getDamage());
                    if (zombie.getHealth() <= 0) {
                        zb.remove();
                    }
                }
            }
        }    
    }
    
    void updateSoldier(){
        for (Soldier soldier : soldiers) {
            
            if(soldier.getClass().getSimpleName().equals("Sniper") || soldier.getClass().getSimpleName().equals("Atirador")){
                if (TimeUtils.nanoTime() - soldier.getLastShotTime() > soldier.getReloadTime()) {
                    spawnShoot(soldier);
                }
            } 
            
            if(soldier.getClass().getSimpleName().equals("Support")){
                if (TimeUtils.nanoTime() - soldier.getLastShotTime() > soldier.getReloadTime()) { 
                    Support supp = (Support) soldier;
                    if(supp.getHasMunition() == false){
                        spawnMunition(supp);
                    }
                }
            }
            
            if(soldier.getClass().getSimpleName().equals("LandMine")){
                LandMine land = (LandMine) soldier;
                if (TimeUtils.nanoTime() - land.getArmTime() > land.getReloadTime() && land.isActivated() == false) {
                    land.setActivated(true);
                }
                font.draw(batch, Boolean.toString(land.isActivated()), soldier.x + 20, soldier.y + soldier.getHeight() + 15);
            }
            if (!soldier.getClass().getSimpleName().equals("LandMine")) {
                font.draw(batch, Integer.toString(soldier.getHealth()), soldier.x + 20, soldier.y + soldier.getHeight() + 15);
            }
        }    
    }

    @Override
    public void render(float delta) {
        
        
		if (Gdx.input.isKeyPressed(Input.Keys.P))
			pause();
		if (Gdx.input.isKeyPressed(Input.Keys.R))
			resume();

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        sR.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                
		for (Shoot shoot : shoots) {
                    batch.draw(shoot.getImagem(), shoot.x, shoot.y);
		}
                for (Zombie zombie : zombies) {
                    batch.draw(zombie.getImagem(), zombie.x, zombie.y);
                }
                for (Soldier soldier : soldiers){
                    batch.draw(soldier.getImagem(), soldier.x, soldier.y);
                }
                for (Munition munition : munitions){
                    batch.draw(munitionImage, munition.x, munition.y);
                }
        font.draw(batch, String.valueOf(currency), 10, 470);
        batch.end();
        
//        sR.begin(ShapeType.Filled);
//        sR.setColor(Color.BLUE);
//        for (Shoot shoot : shoots) {
//            sR.rect(shoot.x, shoot.y, shoot.width, shoot.height);
//        }
//        for (Zombie zombie : zombies) {
//            sR.rect(zombie.x, zombie.y, zombie.width, zombie.height);
//
//        }
//        for (Soldier soldier : soldiers){
//            sR.rect(soldier.x, soldier.y, soldier.width, soldier.height);
//        }        
//        sR.end();
        
	switch (state) {
            case RUN:
                // VAI JOGAR IFS DE INPUT AQUIIIIIIIIIIIIIIIIIIIII AAAAAAAAAAAAAA
                
            	if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
                    teclaAtual = "1";
                if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
                    teclaAtual = "2";
                if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
                    teclaAtual = "3";
                if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4))
                    teclaAtual = "4";
                if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5))
                    teclaAtual = "5";
                
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                    clickedOnAmmo = true;
                    Vector3 touchPos = new Vector3();
                    camera.unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                    for(Iterator<Munition> mu = munitions.iterator(); mu.hasNext();){
                        Munition ammo = mu.next();
//                        System.out.println(ammoAJENI.x);
//                        System.out.println(ammoAJENI.y);
                        if(touchPos.x > ammo.x && touchPos.x < ammo.x + ammo.getWidth() && touchPos.y > ammo.y && touchPos.y < ammo.y + ammo.getHeight()){
                            mu.remove();
                            ammo.getSupport().setHasMunition(false);
                            currency += 50;
                            clickedOnAmmo = false;
                        }
                    }
                }
                    
                if(clickedOnAmmo){    
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){ 
                        Vector3 touchPos = new Vector3();
                        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                        camera.unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0));

                            switch (teclaAtual) {
                                case "1":
                                    spawnAtirador(touchPos);
                                    break;
                                case "2":
                                    spawnSniper(touchPos);
                                    break;
                                case "3":
                                    spawnSupport(touchPos);
                                    break;
                                case "4":
                                    spawnBarricade(touchPos);
                                    break;
                                case "5":
                                    spawnLandMine(touchPos);
                                    break;
                                case "":
                                    System.out.println("aperta uma tecla ô mongol ");
                            }

                    }
                }
                
                if (TimeUtils.nanoTime() - lastZombieTime > 1000000000){    
                    spawnZombie();
                }
                
                batch.begin();
                updateSoldier();
                updateZombies();
                updateShoots();
                batch.end();
                break;
                
            case PAUSE:
            	batch.begin();
		font.draw(batch, "PAUSED", 380, 250);
                batch.end();
		break;
        }


    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
//        rainMusic.play();
    }

    @Override
    public void resize(int width, int height) {

    }
    

    @Override
    public void pause() {
        this.state = State.PAUSE;
    }

    @Override
    public void resume() {
        this.state = State.RUN;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        soldierImage.dispose();
        shootImage.dispose();
        zombieImage.dispose();
//        dropSound.dispose();
//        rainMusic.dispose();
        batch.dispose();
    }

    public enum State {
        PAUSE,
        RUN,
    }
}
