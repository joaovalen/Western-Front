package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameOverScreen implements Screen {

    private final WesternFront game;
    private OrthographicCamera camera;
    private final Texture imagem;
    public Rectangle rect;

    public GameOverScreen(WesternFront game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imagem = new Texture(Gdx.files.internal("gameOver.png"));
        rect = new Rectangle();
        rect.x = 166;
        rect.y = 402;
        rect.width = 629-166;
        rect.height = 467 - 402;
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
        game.batch.draw(imagem, -20, 0);
        game.batch.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(touchPos.x +" "+" "+ touchPos.y);
            if (rect.contains(touchPos.x, touchPos.y)) {
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

