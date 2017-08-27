import java.util.Random;

/**
 * Created by Alex on 5/12/2017.
 */
public class Board {
    static char[][] board = new char[10][10];
    static Random rand = new Random();
    static int mines;


    public static void placeBombs(){
        while (mines < 10){
            board[rand.nextInt(9)][rand.nextInt(9)] = '*';
            mines++;
        }
    }

    public static void fillBoard(){
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (board[x][y] != '*'){
                    board[x][y] = '-';
                }
            }
        }
    }


    public static void main(String[] args) {
        placeBombs();
        fillBoard();

        for (int x = 0; x < 10; x++) {
            System.out.println(board[x][0]);
            for (int y = 0; y < 10; y++) {
                System.out.print(board[x][y]);
            }
        }
    }
}
