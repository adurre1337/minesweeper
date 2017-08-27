package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Alex on 5/16/2017.
 */
public class MainMenuScreen implements Screen {
    final Game game;

    Stage stage;
    TextButton easyButton;
    TextButton mediumButton;
    TextButton hardButton;
    Skin skin;



    public MainMenuScreen(final Game game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        easyButton = new TextButton("Easy (9x9) 10 bombs", skin);
        mediumButton = new TextButton("Medium (16x16) 40 bombs",skin);
        hardButton = new TextButton("Hard (30x16) 99 bombs",skin);

        easyButton.setBounds(100,300,200,50);
        mediumButton.setBounds(100,250,200,50);
        hardButton.setBounds(100,200,200,50);

        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Voltorb Sweeper!", 100, 450);
        game.font.draw(game.batch, "Select a difficulty.", 100, 100);
        game.batch.end();

        if (easyButton.isPressed()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if (mediumButton.isPressed()){
            game.setScreen(new MediumScreen(game));
            dispose();
        }
        if (hardButton.isPressed()){
            game.setScreen(new HardScreen(game));
            dispose();
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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
