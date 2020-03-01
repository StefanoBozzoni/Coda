/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author stefa
 */
public class ObjectWriter {  
    
        public static void saveGame(Game codaGame) {
            ObjectWriter obj = new ObjectWriter();
            obj.serializeGame(codaGame);
        }

        public void serializeGame(Game game) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream(".\\codagame.ser");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(game);
			System.out.println("Done");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        }
}
