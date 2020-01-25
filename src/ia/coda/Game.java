/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author silviogao
 */
public class Game {

    private Player[] players;
    private Deck deck;
    private int currentPlayer = 1;
    private int currentRound;

    public Game() {
        deck = new Deck();
        deck.inizializeDeck();
        deck.shuffle();
        players = new Player[2];
        players[0] = new Player(1);
        players[1] = new Player(2);
        distribute();
        players[0].sortPlayerTiles();
        players[1].sortPlayerTiles();

    }

    void shuffle(Deck mydeck) {
        mydeck.shuffle();
    }

    void distribute() {
        Tile[] array = deck.distribute();
        Tile[] array1 = new Tile[4];
        Tile[] array2 = new Tile[4];

        for (int i = 0; i < 4; i++) {
            array1[i] = array[i];
        }
        players[0].setPlayerTiles(array1);
        for (int j = 0; j < 4; j++) {
            array2[j] = array[j + 4];
        }
        players[1].setPlayerTiles(array2);
        return;
    }

    Tile draw(Deck mydeck) {
        return mydeck.draw();
    }

    public Player[] getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentRound() {
        return currentRound;
    }

}
