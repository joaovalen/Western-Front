package scenes;

import sprites.Barricade;
import sprites.LandMine;
import sprites.Atirador;
import sprites.Shoot;
import sprites.Sniper;
import sprites.Soldier;
import sprites.Zombie;
import sprites.RegularZombie;
import sprites.TankZombie;
import sprites.RunnerZombie;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import sprites.Munition;
import sprites.Square;
import sprites.Support;

public class GameScreen implements Screen {

    private final WesternFront game;
    // Textures
    private final Texture shootImage;
    private final Texture zombieImage;
    private final Texture soldierImage;
    private final Texture background;
    // Camera and Sprite Batch
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    // Objects
    private final CopyOnWriteArrayList<Shoot> shoots;
    private final CopyOnWriteArrayList<Zombie> zombies;
    private long lastZombieTime;
    private State state;
    private final BitmapFont font;
    private int count_raindrops;
    private int count_zombies;
    private final CopyOnWriteArrayList<Soldier> soldiers;
    private int count_soldier;
    private final ShapeRenderer sR;
    private Actor actor;
    private int currency;
    private final CopyOnWriteArrayList<Munition> munitions;
    private final Texture munitionImage;
    private boolean clickedOnAmmo;
    private String teclaAtual;
    private final Texture barricadeImage;
    private final CopyOnWriteArrayList<Square> squares;
    private final boolean TESTELIVRE = true;
    private final Rectangle botaoMenu;
    private int zombieKills;
    private int waveNumber;
    private final CopyOnWriteArrayList<Rectangle> persons;
    private final Texture sniperImage;
    private final Texture mineImage;
    private final Texture supportImage;
    private int vida;
    private long spawnTime;
    private final long tempoStart = TimeUtils.nanoTime();
    private final CopyOnWriteArrayList<Munition> ammoRandom;
    private long lastRandomTime;
    private final Texture coinImage;
    private final Texture heartImage;
    private final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private final int SCREEN_HEIGHT = Gdx.graphics.getHeight();     
    private final Texture arrowImage;
    private final Texture shovelImage;
    
    public GameScreen(final WesternFront game) {
        this.game = game;
	//set initial state
	state = State.RUN;
        // Creating Textures
        shootImage = new Texture(Gdx.files.internal("bullet.png"));
        zombieImage = new Texture(Gdx.files.internal("zombie.png"));
        soldierImage = new Texture(Gdx.files.internal("soldier.png"));
        background = new Texture(Gdx.files.internal("mapapronto.png"));
        munitionImage = new Texture(Gdx.files.internal("moeda.png"));
        barricadeImage = new Texture(Gdx.files.internal("barricade.png"));
        sniperImage = new Texture(Gdx.files.internal("sniper.png"));
        mineImage = new Texture(Gdx.files.internal("landmineOff.png"));
        supportImage = new Texture(Gdx.files.internal("suporte.png"));
        coinImage = new Texture(Gdx.files.internal("moeda.png"));
        heartImage = new Texture(Gdx.files.internal("heart.png"));
        arrowImage = new Texture(Gdx.files.internal("seta.png"));
        shovelImage = new Texture(Gdx.files.internal("shovel.png"));

        // Loading Music and Effects 
//        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("Akatsuki.mp3"));
        

        // start the playback of the background music immediately
//        rainMusic.setLooping(true);
//        rainMusic.play();

        //create the Camera and the SpriteBatch
        teclaAtual = "1";
        lastRandomTime = TimeUtils.nanoTime();
        vida = 100;
        waveNumber = 1;
        zombieKills = 0;
        botaoMenu = new Rectangle();
        botaoMenu.x = 680;
        botaoMenu.y = 430;
        botaoMenu.width = 150;
        botaoMenu.height = 50;
        spawnTime = 1000000000L;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        sR = new ShapeRenderer();
        font = new BitmapFont();
        currency = 50; 
        ammoRandom = new CopyOnWriteArrayList<>();
        shoots = new CopyOnWriteArrayList<>();
        zombies = new CopyOnWriteArrayList<>();
        munitions = new CopyOnWriteArrayList<>();
        soldiers = new CopyOnWriteArrayList<>();
        squares = new CopyOnWriteArrayList<>();
        persons = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 5; i++) {
            int y = 56 * i;
            for (int j = 0; j < 9; j++) {
                int x = 59 * j;
                Square quad = new Square(225, 313);
                quad.x += x;
                quad.y -= y;
                quad.width = 56;
                quad.height = 49;
                squares.add(quad);
            }
        }
        for (int i = 0; i < 6; i++) {
            Rectangle rect = new Rectangle();
            rect.x = 205 + 77 * i;
            rect.y = 429;
            rect.width = 50;
            rect.height = 50;
            persons.add(rect);
        }
    }

    private void pintartemp(String i){
        if(false){
            sR.begin(ShapeType.Filled);
            sR.setColor(Color.BLUE);
            sR.rect(200, 68, 20, 20);
//            for (Shoot shoot : shoots) {
//                sR.rect(shoot.x, shoot.y, shoot.width, shoot.height);
//            }
//
//            sR.rect(botaoMenu.x, botaoMenu.y, botaoMenu.width, botaoMenu.height);
            sR.end();
        }
    }
    
    private void spawnAtirador(float x, float y, Square square) {
        Atirador soldier = new Atirador();
        if (currency >= soldier.getCost() || TESTELIVRE) {
            soldier.x = x;
            soldier.y = y;
            soldier.setSquare(square);
            soldiers.add(soldier);
            square.setOccupied(true);
            square.setSoldier(soldier);
            currency -= soldier.getCost();
        }
    }
    
    private void spawnSniper(float x, float y, Square square) {
        Sniper soldier = new Sniper();
        if (currency >= soldier.getCost() || TESTELIVRE) {
            soldier.x = x;
            soldier.y = y;
            soldier.setSquare(square);
            soldiers.add(soldier);
            square.setOccupied(true);
            square.setSoldier(soldier);
            currency -= soldier.getCost();
        }
    }
    
    private void spawnZombie() {
        int temppos = random(0,44);
        int tempspawn = random(0,100);
        if(tempspawn < 100 - waveNumber * 10){
            float posSpawn = squares.get(temppos).getY();
            Zombie zombie = new RegularZombie();
            zombie.x = Gdx.graphics.getWidth();
            zombie.y = posSpawn + 5;
            zombies.add(zombie);
            lastZombieTime = TimeUtils.nanoTime();
        }else{
            int especial_zombie_type = random(1,2);
            if (especial_zombie_type == 1){
                float posSpawn = squares.get(temppos).getY();
                Zombie zombie = new TankZombie();
                zombie.x = Gdx.graphics.getWidth();
                zombie.y = posSpawn + 5;
                zombies.add(zombie);
                lastZombieTime = TimeUtils.nanoTime();
            } else{
                float posSpawn = squares.get(temppos).getY();
                Zombie zombie = new RunnerZombie();
                zombie.x = Gdx.graphics.getWidth();
                zombie.y = posSpawn + 5;
                zombies.add(zombie);
                lastZombieTime = TimeUtils.nanoTime();
            }
        }

    }
    
    private void spawnShoot(Soldier soldier) {
        Shoot shoot = new Shoot();
        shoot.x = soldier.x + 25;
        shoot.y = soldier.y + soldier.width - 10;
        shoot.setDamage(soldier.getDamage());
        shoot.setSpeed(soldier.getBulletSpeed());
        shoot.setImagem(soldier.getImagemBala());
        shoots.add(shoot);
        soldier.setLastShotTime(TimeUtils.nanoTime());
    }
    
    private void spawnSupport(float x, float y, Square square){
        Support soldier = new Support();
        if (currency >= soldier.getCost() || TESTELIVRE) {
            soldier.x = x;
            soldier.y = y;
            soldier.setSquare(square);
            soldiers.add(soldier);
            square.setOccupied(true);
            square.setSoldier(soldier);
            currency -= soldier.getCost();
            soldier.setLastShotTime(TimeUtils.nanoTime());
        }
    }
    
    private void spawnMunition(Support soldier){
        Munition munition = new Munition(soldier);
        soldier.setHasMunition(true);
        soldier.setLastShotTime(TimeUtils.nanoTime());
        munitions.add(munition);
    }
    
    private void spawnRandomAmmo(){
        Munition munition = new Munition();
        ammoRandom.add(munition);
        lastRandomTime = TimeUtils.nanoTime();
    }
    
    private void spawnBarricade(float x, float y, Square square){
        Barricade soldier = new Barricade();
        if (currency >= soldier.getCost() || TESTELIVRE) {
            soldier.x = x;
            soldier.y = y;
            soldier.setSquare(square);
            soldiers.add(soldier);
            square.setOccupied(true);
            square.setSoldier(soldier);
            currency -= soldier.getCost();
        }
    }
    
    private void spawnLandMine(float x, float y, Square square){
        LandMine soldier = new LandMine();
        if (currency >= soldier.getCost() || TESTELIVRE) {
            soldier.setArmTime(TimeUtils.nanoTime());
            soldier.x = x;
            soldier.y = y;
            soldier.setSquare(square);
            soldiers.add(soldier);
            square.setOccupied(true);
            square.setSoldier(soldier);
            currency -= soldier.getCost();
        }      
    }
    
    private void breakSoldier(Square square){
        Soldier soldier = square.getSoldier();
        soldiers.remove(soldier);
        square.setOccupied(false);
        currency += soldier.getCost()/2;
    }
    
    private void breakSoldier(Soldier soldier){
        soldier.getSquare().setOccupied(false);
        soldiers.remove(soldier);
        currency += soldier.getCost()/2;
    }
    
    private void updateZombies(){
        for (Iterator<Zombie> zb = zombies.iterator(); zb.hasNext();) {
            Zombie zombie = zb.next();
            zombie.x -= zombie.getSpeed() * Gdx.graphics.getDeltaTime();
            if (zombie.x < 0 - zombie.width){
                zombies.remove(zombie);
                vida -= 10;
            }
            for (Iterator<Soldier> it = soldiers.iterator(); it.hasNext();){
                Soldier soldier = it.next();
                if(zombie.overlaps(soldier)){
                    zombie.x += zombie.getSpeed() * Gdx.graphics.getDeltaTime();
                    if(soldier.getClass().getSimpleName().equals("LandMine")){
                        LandMine land = (LandMine) soldier;
                        if (land.isActivated() == true) {
                            zombie.setHealth(zombie.getHealth() - land.getDamage());  
                            soldiers.remove(soldier);
                            soldier.getSquare().setOccupied(false);
                        }    
                    }
                    if (TimeUtils.nanoTime() - zombie.getLastAttackTime() > zombie.getReloadTime()) {
                        if(zombie.getFirstAttack() == true)
                            soldier.setHealth(soldier.getHealth() - zombie.getDamage());
                            zombie.setLastAttackTime(TimeUtils.nanoTime());  
                            if (soldier.getHealth() <= 0) { 
                                soldiers.remove(soldier);
                                soldier.getSquare().setOccupied(false);
                                zombie.setFirstAttack(false);
                            }
                        zombie.setFirstAttack(true);
                    }
                }
            }
            if (zombie.getHealth() <= 0) {
                zombies.remove(zombie);
                zombieKills++;
            }
            font.draw(batch, Integer.toString(zombie.getHealth()), zombie.x + 20, zombie.y + zombie.getHeight() + 15);
        }        
    }
    
    private void updateShoots(){
        for (Iterator<Shoot> it = shoots.iterator(); it.hasNext();) {
            Shoot shoot = it.next();
            shoot.x += shoot.getSpeed() * Gdx.graphics.getDeltaTime();
            for (Iterator<Zombie> zb = zombies.iterator(); zb.hasNext();) {
                Zombie zombie = zb.next();
                if(zombie.overlaps(shoot)){
                    zombie.setHealth(zombie.getHealth() - shoot.getDamage());
                    shoots.remove(shoot);
                }
            }
            if (shoot.x >= Gdx.graphics.getWidth()){
                shoots.remove(shoot);
            }
        }    
    }
    
    private void updateSoldier(){
        for (Soldier soldier : soldiers) {
            if(soldier.getClass().getSimpleName().equals("Sniper") || soldier.getClass().getSimpleName().equals("Atirador")){
                if (TimeUtils.nanoTime() - soldier.getLastShotTime() > soldier.getReloadTime()) {
                    spawnShoot(soldier);
                }
            } 
            
            if(soldier.getClass().getSimpleName().equals("Support")){
                if (TimeUtils.nanoTime() - soldier.getLastShotTime() > soldier.getReloadTime()) { 
                    Support supp = (Support) soldier;
                    if(supp.isFirstAttack() == true)
                        System.out.println("11111111");
                        if(supp.getHasMunition() == false){
                            System.out.println("22222222222");
                            spawnMunition(supp);
                        }   
                soldier.setFirstAttack(true); 
                }
            }
            
            if(soldier.getClass().getSimpleName().equals("LandMine")){
                LandMine land = (LandMine) soldier;
                if (TimeUtils.nanoTime() - land.getArmTime() > land.getReloadTime() && land.isActivated() == false) {
                    land.setActivated(true);
                    land.setImagem(new Texture(Gdx.files.internal("landmineOn.png")));
                }
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
                int temp = 0;
                for (Rectangle rect : persons) {
                    if (temp == 0) {
                        batch.draw(soldierImage, rect.x, rect.y);
                        Atirador soldier = new Atirador();
                        font.draw(batch, String.valueOf(soldier.getCost()), rect.x, rect.y - 5);
                    }
                    if (temp == 1) {
                        batch.draw(sniperImage, rect.x, rect.y);
                        Sniper soldier = new Sniper();
                        font.draw(batch, String.valueOf(soldier.getCost()), rect.x, rect.y - 5);
                    }
                    if (temp == 2) {
                        rect.y = 424;
                        rect.x = 272 + 77;
                        batch.draw(supportImage, rect.x, rect.y);
                        Support soldier = new Support();
                        font.draw(batch, String.valueOf(soldier.getCost()), rect.x + 15, rect.y - 5);
                    }
                    if (temp == 3) {
                        batch.draw(barricadeImage, rect.x, rect.y);
                        Barricade soldier = new Barricade();
                        font.draw(batch, String.valueOf(soldier.getCost()), rect.x, rect.y - 5);
                    }
                    if (temp == 4) {
                        batch.draw(mineImage, rect.x, rect.y);
                        LandMine soldier = new LandMine();
                        font.draw(batch, String.valueOf(soldier.getCost()), rect.x, rect.y - 5);
                    }
                    if (temp == 5) {
                        batch.draw(shovelImage, rect.x - 10, rect.y - 10);
                    }
                    temp++;
                    
                }
        Rectangle temprect = persons.get(Integer.parseInt(teclaAtual) - 1);        
        batch.draw(arrowImage, temprect.x - 5, temprect.y - 60);
        font.draw(batch, String.valueOf(currency), 48, 461);
        batch.draw(coinImage, 4, 438);
        font.draw(batch, "Wave: " + Integer.toString(waveNumber), 16, 18);
        batch.draw(heartImage, SCREEN_WIDTH - heartImage.getWidth() - 10, 5);
        font.draw(batch, String.valueOf(vida), SCREEN_WIDTH - heartImage.getWidth() + 9, 35);
        for (Munition munition : ammoRandom){
            batch.draw(munitionImage, munition.x, munition.y);
        }
        batch.end();

	switch (state) {
            case RUN:
                
                if(vida <= 0){
                    GameOverScreen voltar = new GameOverScreen(game);
                    game.setScreen(voltar);
                    dispose();               
                }
                
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
                if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6))
                    teclaAtual = "6";  
                
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                    clickedOnAmmo = true;
                    Vector3 touchPos = new Vector3();
                    camera.unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                    for(Iterator<Munition> mu = munitions.iterator(); mu.hasNext();){
                        Munition ammo = mu.next();
                        if(ammo.contains(touchPos.x, touchPos.y)){
                            munitions.remove(ammo);
                            ammo.getSupport().setHasMunition(false);
                            currency += 25;
                            clickedOnAmmo = false;
                        }
                    }
                    for(Iterator<Munition> mu = ammoRandom.iterator(); mu.hasNext();){
                        Munition ammo = mu.next();
                        if(ammo.contains(touchPos.x, touchPos.y)){
                            ammoRandom.remove(ammo);
                            currency += 25;
                            clickedOnAmmo = false;
                        }
                    }
                }
                for(Iterator<Munition> mu = ammoRandom.iterator(); mu.hasNext();){
                    Munition ammo = mu.next();
                    if (ammo.getStopY() < ammo.y) {
                        ammo.y -= 30 * Gdx.graphics.getDeltaTime();
                    }
                }
                
                if (teclaAtual.equals("1")) {
                    pintartemp("1");
                }
                if (teclaAtual.equals("2")) {
                    pintartemp("2");
                }
                if (teclaAtual.equals("3")) {
                    pintartemp("3");
                }
                if (teclaAtual.equals("4")) {
                    pintartemp("4");
                }
                if (teclaAtual.equals("5")) {
                    pintartemp("5");
                }
                if (teclaAtual.equals("6")) {
                    pintartemp("6");
                }
                    
                if(clickedOnAmmo){    
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){ 
                        Vector3 touchPos = new Vector3();
                        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                        System.out.println(touchPos.x +" "+ touchPos.y);
                        camera.unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                        for(Square square : squares){
                            if (square.contains(touchPos.x, touchPos.y)) {
                                if (square.isOccupied() == false) {
                                    float posx = square.x;
                                    float posy = square.y + 5;
                                    switch (teclaAtual) {
                                        case "1":
                                            spawnAtirador(posx, posy, square);
                                            break;
                                        case "2":
                                            spawnSniper(posx, posy, square);
                                            break;
                                        case "3":
                                            spawnSupport(posx, posy, square);
                                            break;
                                        case "4":
                                            spawnBarricade(posx, posy, square);
                                            break;
                                        case "5":
                                            spawnLandMine(posx, posy, square);
                                            break;
                                        case "6":
                                            System.out.println("asdasdasda");
                                            if (square.isOccupied()) {
                                                System.out.println("222222222");
                                                breakSoldier(square);
                                            }
                                            
                                        case "":
                                            System.out.println("SELECIONE UMA TECLA");
                                    }
                                }
                            }
                        }
                        if(teclaAtual.equals("6")){
                            for(Soldier soldier : soldiers){
                                if (soldier.contains(touchPos.x, touchPos.y)) {
                                    breakSoldier(soldier);
                                }
                            }
                        }
                    }
                }
                
                if (TimeUtils.nanoTime() - lastZombieTime > spawnTime && TimeUtils.nanoTime() - tempoStart >= 30000000000L){    
                    spawnZombie();
                }
                if (TimeUtils.nanoTime() - lastRandomTime > 6000000000L) {
                    spawnRandomAmmo();
                }
                if (zombieKills >= 20 * waveNumber){
                      waveNumber++;
                      spawnTime -= 20000000L;
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
        shoots.clear();
        zombies.clear();
        soldiers.clear();
        munitions.clear();
        squares.clear();
        persons.clear();
        ammoRandom.clear();
    }

    public enum State {
        PAUSE,
        RUN,
    }
}
