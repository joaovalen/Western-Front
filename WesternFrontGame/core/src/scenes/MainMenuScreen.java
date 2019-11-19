package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    private final WesternFront game;
    private OrthographicCamera camera;
    private final Texture logoSoldier;
    private final Texture logoWesternFront;
    private final Texture logoLastStand;
    public int stageNumber;

    public MainMenuScreen(WesternFront game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        logoSoldier = new Texture(Gdx.files.internal("logoSoldier.png"));
        logoWesternFront = new Texture (Gdx.files.internal("logoWesternFront.png"));
        logoLastStand = new Texture (Gdx.files.internal("logoLastStand.png"));
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(logoSoldier, (Gdx.graphics.getWidth() / 2) - (logoSoldier.getWidth() /2), 300);
        game.batch.draw(logoWesternFront, (Gdx.graphics.getWidth() / 2) - (logoWesternFront.getWidth() /2), 270);
        game.batch.draw(logoLastStand, (Gdx.graphics.getWidth() / 2) - (logoLastStand.getWidth() /2), 180);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            if (stageNumber == 0) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
            if (stageNumber == 1) {
                System.out.println("aaaaaaaaaa");
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
