/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import java.io.Serializable;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author silviogao
 */
public class Game implements Serializable {

    private Player[] players;
    private Deck deck;
    private int currentPlayer = 1;
    private int currentRound;
    private boolean userStartGuessing = false;
    private boolean playerCanDraw;
    private Tile tileDrew;
    private Tile[] boardTiles = new Tile[Deck.TOTAL_NUMBERS_OF_TILES];
    private int numTilesInBoard = 0;

    public Tile getTileDrew() {
        return tileDrew;
    }

    public void setTileDrew(Tile tileDrew) {
        if (tileDrew != null) {
            //DONE: 3) set the tileDrew as covered passing the right parameter in the following Tile costructor
            Tile t = new Tile(tileDrew.getNumtile(), tileDrew.getColor_tile(), true);
            this.tileDrew = t;
        } else {
            this.tileDrew = null;
        }
    }

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
        //DONE: 4) cover the player2 tiles calling the apposite player method
        players[1].coverAllTiles();
        userStartGuessing = false;
        playerCanDraw = true;
        currentRound = 1;

    }

    public boolean canPlayerDraw() {
        return playerCanDraw;
    }

    public void playerCanDraw(boolean canDraw) {
        this.playerCanDraw = canDraw;
    }

    public boolean canUserStartGuessing() {
        return userStartGuessing;
    }

    public void setUserCanGuess(boolean canGuess) {
        this.userStartGuessing = canGuess;
    }

    void shuffle(Deck mydeck) {
        mydeck.shuffle();
    }

    boolean isPlayer1Turn() {
        return (currentPlayer == 1);
    }

    void distribute() {
        Tile[] array = deck.distribute();
        Tile[] array1 = new Tile[4];
        Tile[] array2 = new Tile[4];

        for (int i = 0; i < 4; i++) {
            array1[i] = array[i];
            array1[i].reveal();
        }

        players[0].setPlayerTiles(array1);
        for (int j = 0; j < 4; j++) {
            array2[j] = array[j + 4];
        }
        players[1].setPlayerTiles(array2);
        return;
    }

    public void doGamePlayer1(int numberoftile, Colore color, JLabel label) {
        if (currentPlayer == 1) {
            System.out.println(label.getName());
            String labelClicked = label.getName();
            String[] strings = labelClicked.split("_");
            int number = Integer.valueOf(strings[1]);
            Tile clickedTile = getPlayers()[1].getPlayerTiles()[number];
            System.out.println(clickedTile.getNumtile());

            String numTile = String.valueOf(clickedTile.getNumtile());
            String colorTile = String.valueOf(clickedTile.getColor_tile());

            if ((numTile.equals(String.valueOf(numberoftile)) && (colorTile.equals(String.valueOf(color))))) {

                addTileToBoard(clickedTile);
                getPlayers()[1].deleteTile(clickedTile);

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogueButton = 0;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Correct!\nWould You Like To Continue?\n", "Correct", dialogueButton);
                if (dialogResult != 0) { //no continue                   
                    Player player1 = getPlayers()[0];
                    Tile drewTile = getTileDrew();
                    player1.addTile(drewTile.getNumtile(), drewTile.getColor_tile(), false);
                    setTileDrew(null);
                    getPlayers()[0].sortPlayerTiles();
                    nextTurn(); //added
                }
            } else {
                //wrong answer
                javax.swing.JOptionPane.showMessageDialog(null, "Opss! Wrong", "Incorrect", javax.swing.JOptionPane.WARNING_MESSAGE);
                addTileToBoard(getTileDrew());
                setTileDrew(null);
                nextTurn();
            }

        }
    }

    public void doGamePlayer2(javax.swing.JTextField messagePlayer2) {

        int numberoftile;
        Colore color;
        JLabel label;
        Random rnd = new Random();
        Player player1 = getPlayers()[0];

        Tile drewTile = draw();
        setTileDrew(drewTile);
        //labelDrew.setIcon(new ImageIcon("./" + drewTile.getFileName())); removed
        //labelDrew.setName("label_" + drewTile.getNumtile()); removed

        int position = rnd.nextInt(player1.getNumTiles() - 1);
        int numberGuess = rnd.nextInt(12);
        Colore colorGuess = Colore.values()[rnd.nextInt(2)];
        Tile chosenTile = player1.getPlayerTiles()[position];

        if (chosenTile.getNumtile() == numberGuess && chosenTile.getColor_tile() == colorGuess) {
            addTileToBoard(chosenTile);
            player1.deleteTile(chosenTile);
            messagePlayer2.setText("Player 2 Guessed Right");
            javax.swing.JOptionPane.showMessageDialog(null, "Player 2 Guessed Right", "Correct", javax.swing.JOptionPane.WARNING_MESSAGE);
            doGamePlayer2(messagePlayer2);

        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Player 2 Guessed Wrong", "Incorrect", javax.swing.JOptionPane.WARNING_MESSAGE);
            messagePlayer2.setText("Player 2 Guessed Wrong");
            addTileToBoard(drewTile);
            setTileDrew(null);
            playerCanDraw(true);
            nextTurn();
        }

        setUserCanGuess(false);
        playerCanDraw(true);
        getPlayers()[1].sortPlayerTiles();
    }

    /*
    Tile draw(Deck mydeck) {
        return mydeck.draw();
    }
     */
    Tile draw() {
        return deck.draw();
    }

    public Player[] getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getCurrentPlayerNum() {
        return currentPlayer;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void nextTurn() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
        if (currentPlayer == 1) {
            currentRound++; //added
        }
    }

    public void addTileToBoard(Tile tile) {
        //DONE: 5) when a tile is added to the board should be uncovered, the tile t should be uncovered, so call the apposite constructor passing the covered parameter as false
        Tile t = new Tile(tile.getNumtile(), tile.getColor_tile(), false);
        boardTiles[numTilesInBoard++] = t;
    }

    public Tile[] getBoardTiles() {
        return boardTiles;
    }

    public int getNumOfBoardTiles() {
        return numTilesInBoard;
    }

}
