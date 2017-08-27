package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Alex on 5/18/2017.
 */
public class LoseScreen implements Screen {
    final Game game;

    Stage stage;
    TextButton resetButton;
    TextButton menuButton;
    Skin skin;
    static Board board;

    public LoseScreen(final Game game){
        this.game = game;
        //board = new Board(9);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        resetButton = new TextButton("Reset?", skin);
        menuButton = new TextButton("Return to Menu?", skin);


        resetButton.setBounds(100,300,200,50);
        menuButton.setBounds(100,100,200,50);

       // stage.addActor(resetButton);
        stage.addActor(menuButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.font.draw(game.batch, "You fainted!", 100, 450);
        game.batch.end();

        if (resetButton.isPressed()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        if (menuButton.isPressed()){
            game.setScreen(new MainMenuScreen(game));
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
