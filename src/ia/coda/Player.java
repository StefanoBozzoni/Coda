/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import static java.util.Arrays.sort;
import static ia.coda.colore.*;

/**
 *
 * @author silviogao
 */
public class Player {

    private final int playernum;
    private Tile[] playerTiles = new Tile[Deck.TOTAL_NUMBERS_OF_TILES];
    private int numTiles = 0;

    public void setPlayerTiles(Tile[] tiles) {
        for(int i = 0; i < tiles.length; i++){
            playerTiles[i]= tiles[i];
        }
        numTiles = tiles.length;
    }

    public Player(int num) {
        playernum = num;
    }

    String getPlayerName() {
        return "Player: " + playernum;
    }

    void draw(Deck mydeck) {
        Tile drawedTile = mydeck.draw();
        playerTiles[numTiles++] = drawedTile;
    }

    Tile[] sortPlayerTiles() {
        Tile[] myTiles = playerTiles;

        boolean finito = false;
        while (!finito) {
            finito = true;
            for (int j = 0; j < numTiles - 1; j++) {
                int color1 = myTiles[j].getColor_tile().ordinal();
                int color2 = myTiles[j+1].getColor_tile().ordinal();
                
                if (color1 >= color2) {
                    if (color1 == color2) {
                        if (myTiles[j].getNumtile() > myTiles[j + 1].getNumtile()) {
                            Tile temp;
                            temp = myTiles[j];
                            myTiles[j] = myTiles[j + 1];
                            myTiles[j + 1] = temp;
                            finito = false;
                        }
                    } else {
                        Tile temp;
                        temp = myTiles[j];
                        myTiles[j] = myTiles[j + 1];
                        myTiles[j + 1] = temp;
                        finito = false;
                    }
                }

            }
        }
        return myTiles;
    }

    public int getPlayernum() {
        return playernum;
    }

    public Tile[] getPlayerTiles() {
        return playerTiles;
    }

    public int getNumTiles() {
        return numTiles;
    }
}
