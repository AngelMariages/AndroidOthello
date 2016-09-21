package cesarbiker.xyz.othello;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by iam39418281 on 9/20/16.
 */
public class Board {
    int[][] gameBoard = new int[8][8];

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = 0;
            }
        }
        gameBoard[3][3] = 1;
        gameBoard[4][3] = 2;
        gameBoard[3][4] = 2;
        gameBoard[4][4] = 1;
    }

    public boolean addPiece(int x, int y, int player) {
        ArrayList<int[]> posToChange = new ArrayList<>();

        if (checkMove(x, y, player, posToChange)) {
            gameBoard[x][y] = player;
            changePieces(posToChange, player);
            return true;
        }
        else
            return false;
    }

    public void changePieces(ArrayList<int[]> posToChange, int player) {
        for (int[] pos : posToChange) {
            gameBoard[pos[0]][pos[1]] = player;
        }
    }

    public boolean checkMove(int x, int y, int player, ArrayList<int[]> posToChange) {
        int otherPlayer = player == 1 ? 2 : 1;
        boolean yPlus = y + 1 <= 7;
        boolean xPlus = x + 1 <= 7;
        boolean yMinus = y - 1 >= 0;
        boolean xMinus = x - 1 >= 0;

        ArrayList<int[]> tmpPosToChange = new ArrayList<>();

        if(yMinus && gameBoard[x][y - 1] == otherPlayer) {
            for(int i = y - 1; i >= 0; i--) {
                if(gameBoard[x][i] == otherPlayer)
                    tmpPosToChange.add(new int[]{x,i});
                if(gameBoard[x][i] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(yPlus && gameBoard[x][y + 1] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = y; i <= 7; i++) {
                if(gameBoard[x][i] == otherPlayer)
                    tmpPosToChange.add(new int[]{x,i});
                if(gameBoard[x][i] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(xMinus && gameBoard[x - 1][y] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = x - 1; i >= 0; i--) {
                if(gameBoard[i][y] == otherPlayer)
                    tmpPosToChange.add(new int[]{i,y});
                if(gameBoard[i][y] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(xPlus && gameBoard[x + 1][y] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = x + 1; i <= 7 ; i++) {
                if(gameBoard[i][y] == otherPlayer)
                    tmpPosToChange.add(new int[]{i,y});
                if(gameBoard[i][y] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(xPlus && yPlus && gameBoard[x + 1][y + 1] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++,j++) {
                if(gameBoard[i][j] == otherPlayer)
                    tmpPosToChange.add(new int[]{i,j});
                if(gameBoard[i][j] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(xPlus && yMinus && gameBoard[x + 1][y - 1] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++,j--) {
                if(gameBoard[i][j] == otherPlayer)
                    tmpPosToChange.add(new int[]{i,j});
                if(gameBoard[i][j] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(xMinus && yMinus && gameBoard[x - 1][y - 1] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--,j--) {
                if(gameBoard[i][j] == otherPlayer)
                    tmpPosToChange.add(new int[]{i,j});
                if(gameBoard[i][j] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        if(xMinus && yPlus && gameBoard[x - 1][y + 1] == otherPlayer) {
            tmpPosToChange.clear();
            for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--,j++) {
                if(gameBoard[i][j] == otherPlayer)
                    tmpPosToChange.add(new int[]{i,j});
                if(gameBoard[i][j] == player && tmpPosToChange.size() > 0) {
                    posToChange.addAll(tmpPosToChange);
                    break;
                }
            }
        }
        Log.d("TEST", Arrays.deepToString(posToChange.toArray()));
        return posToChange.size() > 0;
    }

    public void debugBoard() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                str.append(gameBoard[j][i]).append(" - ");
            }
            str.append("\n");
        }
        Log.d("TEST", str.toString());
    }
}