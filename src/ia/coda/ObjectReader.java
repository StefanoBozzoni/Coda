/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;

public class ObjectReader {

    public static Game loadGame() {

        ObjectReader obj = new ObjectReader();
        
        File f = new File(".\\codagame.ser");
        Game codaGame=null;
        if (f.exists())
           codaGame = obj.deserialzeGame(f.getPath());
        
        return codaGame;

    }

    public Game deserialzeGame(String filename) {

        Game game = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {

            fin = new FileInputStream(filename);
            ois = new ObjectInputStream(fin);
            game = (Game) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return game;
    }

}
