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


/**
 *
 * @author silviogao
 */
public class Game implements Serializable{

    private Player[] players;
    private Deck deck;
    private int currentPlayer = 1;
    private int currentRound;
    private boolean userStartGuessing = false;
    private boolean playerCanDraw;
    private Tile tileDrew; 
    private Tile[] boardTiles = new Tile[Deck.TOTAL_NUMBERS_OF_TILES];
    private int numTilesInBoard=0;


    public Tile getTileDrew() {
        return tileDrew;
    }

    public void setTileDrew(Tile tileDrew) {
        if (tileDrew!=null) {
            //TODO: 3) set the tileDrew as covered passing the right parameter in the following Tile costructor
            Tile t = new Tile(tileDrew.getNumtile(),tileDrew.getColor_tile());
            this.tileDrew = t;
        }
        else
            this.tileDrew=null;
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
        //TODO: 4) cover the player2 tiles calling the apposite player method
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
        return (currentPlayer==1);
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
    public void nextTurn(){
        if(currentPlayer==1){
            currentPlayer=2;
        }else{
            currentPlayer=1;
        }
        currentRound++;
    }
    public void addTileToBoard(Tile tile) {
        //TODO: 5) when a tile is added to the board should be uncovered, the tile t should be covered, so call the apposite constructor passing the covered parameter as true
        Tile t = new Tile(tile.getNumtile(),tile.getColor_tile());
        boardTiles[numTilesInBoard++]=t;
    }
   
    public Tile[] getBoardTiles() {
        return boardTiles;
    }
    
    public int getNumOfBoardTiles() {
        return numTilesInBoard;
    }

}
