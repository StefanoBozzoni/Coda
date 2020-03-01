/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import static ia.coda.Colore.*;
import java.io.Serializable;

enum Colore {
    BLACK,
    WHITE
}

/**
 *
 * @author silviogao
 */
public class Tile implements Serializable{

    private Colore color_tile;
    private int numtile;
    
    private boolean covered = true;

    Tile(int ptile, Colore pcolor) {
        this(ptile, pcolor, true);
    }

    Tile(int ptile, Colore pcolor, boolean pcovered) {
        numtile = ptile;
        color_tile = pcolor;
        covered = pcovered;
    }

    void reveal() {
        covered = false;
    }

    void cover() {
        covered = true;
    }

    public Colore getColor_tile() {
        return color_tile;
    }

    public int getNumtile() {
        return numtile;
    }
    
    public String getFileName(){
        if (color_tile == BLACK){
            return "/images/Tile" + numtile + ".png";
        }
        else{
            return "/images/Tile" + String.valueOf(numtile+12) + ".png";
        }
    }
}
