/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

/**
 *
 * @author silviogao
 */
import static ia.coda.Colore.*;
import java.io.Serializable;
import java.util.Random;

public class Deck implements Serializable {

    final static int TOTAL_NUMBERS_OF_TILES = 24;
    Tile[] tiles = new Tile[TOTAL_NUMBERS_OF_TILES];
    int numberoftiles = TOTAL_NUMBERS_OF_TILES;

    Tile draw() {
        numberoftiles--;
        return tiles[numberoftiles];
        
    }

    void shuffle() {
        Tile temp;
        int i;
        Random r = new Random();
        //System.out.println(r);
        for (i = 0; i < TOTAL_NUMBERS_OF_TILES; i++) {
            int randomPosition = r.nextInt(TOTAL_NUMBERS_OF_TILES);
            temp = tiles[randomPosition];
            tiles[randomPosition] = tiles[i];
            tiles[i] = temp;
        }
        //for (i=0; i<TOTAL_NUMBERS_OF_TILES;i++) {
        //   System.out.print("{"+tiles[i].getNumtile()+","+tiles[i].getColor_tile()+"}");
        //}
    }

    Tile[] distribute() {
        Tile[] returnarray = new Tile[8];
        for (int i = 0; i <= 7; i++) {
            returnarray[i] = tiles[numberoftiles - 1 - i];
            tiles[numberoftiles - 1 - i] = null;
            //System.out.println("[" + returnarray[i].getNumtile() + "," + returnarray[i].getColor_tile()+"]");
        } 
        numberoftiles=numberoftiles-8;
        return returnarray;
    }

    void inizializeDeck() {
        int i;
        for (i = 0; i < (TOTAL_NUMBERS_OF_TILES) / 2; i++) {
            tiles[i] = new Tile(i, BLACK);
        }
        int j = 0;
        for (i = 12; i < TOTAL_NUMBERS_OF_TILES; i++) {
            tiles[i] = new Tile(j, WHITE);
            j++;
        }
    }

    @Override
    public String toString() {
        String retString = "";
        for (int i = 0; i <= TOTAL_NUMBERS_OF_TILES - 1; i++) {
            retString = retString + "[" + tiles[i].getNumtile() + "," + tiles[i].getColor_tile() + "]";
        }
        return retString;
    }
}
