package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import javax.xml.soap.Text;
import java.util.Random;

/**
 * Created by Alex on 5/16/2017.
 */
public class MediumScreen implements Screen {
    public final int HEIGHT = 480;

    final Game game;
    Rectangle tile;
    int tileDimension;
    int[][] clicked = new int[16][16];
    SpriteBatch batch;
    Vector3 vector;
    Rectangle[][] tiles;
    int numBombs;
    int flags;

    float timer;
    float buffer;

    Texture blank;
    Texture flag;
    Texture one;
    Texture two;
    Texture three;
    Texture four;
    Texture five;
    Texture six;
    Texture seven;
    Texture eight;
    Texture voltorb;

    Board board;


    public MediumScreen(final Game game){
        this.game = game;
        tile = new Rectangle();
        tileDimension = 25;
        batch = new SpriteBatch();
        tiles = new Rectangle[16][16];
        vector = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        numBombs = 40;
        timer = 0.0f;
        flags = 0;



        flag = new Texture(Gdx.files.internal("flag.png"));
        blank = new Texture(Gdx.files.internal("blank.png"));
        one = new Texture(Gdx.files.internal("one.png"));
        two = new Texture(Gdx.files.internal("two.png"));
        three = new Texture(Gdx.files.internal("three.png"));
        four = new Texture(Gdx.files.internal("four.png"));
        five = new Texture(Gdx.files.internal("five.png"));
        six = new Texture(Gdx.files.internal("six.png"));
        seven = new Texture(Gdx.files.internal("seven.png"));
        eight = new Texture(Gdx.files.internal("eight.png"));
        voltorb = new Texture(Gdx.files.internal("voltorb.png"));

        board = new Board(16,16,40);
        board.resetBoard(numBombs,0,0);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == 0) {
                    handlePrimaryClick(screenX, HEIGHT - screenY);
                }
                else if (button == 1) {
                    handleSecondaryClick(screenX, HEIGHT - screenY);
                }
                return true;
            }
        });

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                tiles[x][y] = new Rectangle();
                tiles[x][y].set(x * (tileDimension + 1) + 130, y * (tileDimension + 1) + 30,tileDimension,tileDimension);
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                clicked[i][j] = -1;
            }
        }
    }

    private void handleSecondaryClick(int x1, int y1) {
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                if (tiles[x][y].contains(x1,y1)){
                    clicked[x][y] = 1;
                }
            }
        }
    }

    private void handlePrimaryClick(int x1, int y1) {
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                if (tiles[x][y].contains(x1,y1)){
                    clicked[x][y] = 0;
                }
            }
        }
    }


    @Override
    public void show() {

    }

    public Board getBoard(){
        return board;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1f, .5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer += delta;
        game.batch.begin();
        game.font.draw(game.batch, "Timer:", 0, 475);
        game.font.draw(game.batch, ""+(int)timer, 0, 440);
        game.batch.end();

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (getClicked(i,j) >= 0) {
                    if (getClicked(i, j) == 0) {
                        if (getBoard().getBoard(i,j) > -1){
                            if (getBoard().adjacentMines(i,j) == 0){
                                clicked[i][j] = -1;
                                chord(i,j);
                                //clicked[i][j] = 0;
                            }
                            if (getBoard().adjacentMines(i,j) == 1){
                                placeRectangles(one, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 2){
                                placeRectangles(two, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 3){
                                placeRectangles(three, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 4){
                                placeRectangles(four, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 5){
                                placeRectangles(five, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 6){
                                placeRectangles(six, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 7){
                                placeRectangles(seven, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                            if (getBoard().adjacentMines(i,j) == 8){
                                placeRectangles(eight, tiles[i][j].getX(), tiles[i][j].getY());
                            }
                        }
                        if (getBoard().getBoard(i,j) == -1){
                            for (int x = 0; x < 16; x++) {
                                for (int y = 0; y < 16; y++) {
                                    if (getBoard().getBoard(x,y) == -1){
                                        placeRectangles(voltorb, tiles[x][y].getX(), tiles[x][y].getY());
                                    }
                                }
                            }
                            buffer+= delta;
                            if (buffer > 2) {
                                game.setScreen(new LoseScreen(game));
                                getBoard().resetBoard(numBombs,0,0);
                                dispose();
                            }
                        }
                    }
                    if (getClicked(i, j) == 1) {
                        placeRectangles(flag, tiles[i][j].getX(), tiles[i][j].getY());
                    }
                }
                else {
                    placeRectangles(blank,
                            i * (tileDimension + 1) + 130,
                            j * (tileDimension + 1) + 30);
                }
            }
        }

        if (win()){
            game.setScreen(new WinScreen(game));
            getBoard().resetBoard(numBombs,0,0);
            dispose();
        }
    }

    public boolean win(){
        int correctFlags = 0;
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                if (getBoard().getBoard(x,y) == -1 && getClicked(x,y) == 1){
                    correctFlags++;
                }
            }
        }
        if (correctFlags == 40){
            return true;
        }
        return  false;
    }

    public void chord(int x, int y){
        if(x < 0 || x >= 16 || y < 0 || y >= 16) {
            return;
        }
        if(clicked[x][y] > -1) {
            return;
        }
        clicked[x][y] = 0;
        if (getBoard().getBoard(x,y) == -1){
            return;
        }
        if (getBoard().adjacentMines(x,y)> 0){
            return;
        }
        chord(x - 1, y - 1);
        chord(x, y - 1);
        chord(x + 1, y - 1);
        chord(x - 1, y);
        chord(x + 1, y);
        chord(x - 1, y + 1);
        chord(x, y + 1);
        chord(x + 1, y + 1);
    }

    private int getClicked(int x, int y) {
        return clicked[x][y];
    }


    public void placeRectangles(Texture texture, float x, float y){
        batch.begin();
        batch.draw(texture,x,y,tileDimension,tileDimension);
        batch.end();
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
