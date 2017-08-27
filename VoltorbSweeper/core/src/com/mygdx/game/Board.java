package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

import java.util.Random;

/**
 * Created by Alex on 5/17/2017.
 */
public class Board {
    int size;
    int x;
    int y;
    int[][] board;
    Random rand = new Random();
    int mines;

    boolean isHard =true;

    public Board(int x,int y, int numBombs){
        mines = 0;
        this.x = x;
        this.y = y;
        board = new int[this.x][this.y];
        placeBombs(numBombs,x,y);
        fillBoard();
        isHard = false;
    }

    public void resetBoard(int bombs,int x, int y){
        placeBombs(bombs,x,y);
        fillBoard();
        //mines = 0;
    }

    public void placeBombs(int numBombs, int x, int y){

        while (mines < numBombs + 1) {
            board[rand.nextInt(x)][rand.nextInt(y)] = -1;
            mines++;
        }
    }

    public void fillBoard(){
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (board[x][y] != -1) {
                    board[x][y] = 0;
                }
            }
        }
    }

    public int adjacentMines(int x, int y) {
        int tot = 0;
        if(isMine(x - 1, y - 1)) {
            tot++;
        }
        if(isMine(x, y - 1)) {
            tot++;
        }
        if(isMine(x + 1, y - 1)) {
            tot++;
        }
        if(isMine(x - 1, y)) {
            tot++;
        }
        if(isMine(x + 1, y)) {
            tot++;
        }
        if(isMine(x - 1, y + 1)) {
            tot++;
        }
        if(isMine(x, y + 1)) {
            tot++;
        }
        if(isMine(x + 1, y + 1)) {
            tot++;
        }
        return tot;
    }

    private boolean isMine(int i, int j) {

        if (i >= 0 && i < x && j >= 0 && j < y) {
            if (board[i][j] == -1) {
                return true;
            }
        }
        return false;
    }

    public int getBoard(int x, int y){
        return board[x][y];
    }


}
