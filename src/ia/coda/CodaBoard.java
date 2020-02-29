/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.coda;

import java.awt.Component;
import java.awt.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import ia.coda.GuessField.*;
import java.text.FieldPosition;
import java.util.Random;
import javafx.scene.control.Alert;

/**
 *
 * @author silviogao
 */
public class CodaBoard extends javax.swing.JFrame implements IGuessedField {

    private boolean changeismade = false;
    private Game codaGame;
    private boolean isPlayer1Turn = true;

    @Override
    public void onButtonOkGuessedField(int numberoftile, Colore color, JLabel label) {

        if (isPlayer1Turn) {
            //String s = JOptionPane.showInputDialog("Guess the tile number");
            System.out.println(label.getName());
            String labelClicked = label.getName();
            String[] strings = labelClicked.split("_");
            int number = Integer.valueOf(strings[1]);
            Tile clickedTile = codaGame.getPlayers()[1].getPlayerTiles()[number];
            System.out.println(clickedTile.getNumtile());

            String numTile = String.valueOf(clickedTile.getNumtile());
            String colorTile = String.valueOf(clickedTile.getColor_tile());

            if ((numTile.equals(String.valueOf(numberoftile)) && (colorTile.equals(String.valueOf(color))))) {

                //TODO: Remove the clicked tile from the player, reorder the tiles
                fieldPanel.add(label);
                codaGame.addTileToBoard(clickedTile);
                codaGame.getPlayers()[0].deletePlayerTile(clickedTile);
                //TODO: reorder player tiles ?

                changeismade = true;
                pack();
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogueButton = 0;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Correct!\nWould You Like To Continue?\n", "Correct", dialogueButton);
                if (dialogResult != 0) {
                    JLabel tileToAddlbl = new JLabel("");
                    tileToAddlbl.setIcon(labelDrew.getIcon());
                    tileToAddlbl.setName("Label_" + codaGame.getCurrentPlayer().getNumTiles());
                    player1Panel.add(tileToAddlbl);

                    Player player = codaGame.getCurrentPlayer();
                    Tile drewTile = codaGame.getTileDrew();
                    player.addTile(drewTile.getNumtile(), drewTile.getColor_tile());

                    labelDrew.setIcon(null);
                    labelDrew.revalidate();

                    //TODO: Reorder tiles and redraw game
                    pack();
                }
            } else {
                isPlayer1Turn = false;
                javax.swing.JOptionPane.showMessageDialog(null, "Opss! Wrong", "Incorrect", javax.swing.JOptionPane.WARNING_MESSAGE);
                JLabel tileToAddlbl = new JLabel("");
                tileToAddlbl.setIcon(labelDrew.getIcon());
                tileToAddlbl.setName(labelDrew.getName());
                fieldPanel.add(tileToAddlbl);
                labelDrew.setIcon(null);
                labelDrew.revalidate();
                //TODO: Add the tile to the board

                pack();
                codaGame.playerCanDraw(true);
                codaGame.nextTurn();
            }
        }

    }

    /**
     * Creates new form Coda
     */
    public CodaBoard() {
        initComponents();
        codaGame = new Game();
        setMainListeners();
        displayGame();
    }

    void saveGame() {
        ObjectWriter.saveGame(codaGame);
        JOptionPane.showMessageDialog(this, "Game saved successfully", "info", JOptionPane.OK_OPTION);
    }

    void doGamePlayer2() {
        /*int numberoftile;
        Colore color;
        JLabel label;
        Random x = new Random();
        Random y = new Random();
        int position = y.nextInt(3);
        int numberGuess = x.nextInt(11);
        int number = Integer.valueOf(position);
        Tile ChosenTile = codaGame.getPlayers()[0].getPlayerTiles()[number];
        int guess = numberGuess;
        String numTile = String.valueOf(ChosenTile.getNumtile());
        if (numTile.equals(String.valueOf(guess))) {
            fieldPanel.add(label);
            changeismade = true;
            
            pack();
        } else {
            isPlayer1Turn = true;
        }*/
        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        draw = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        player1Panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        player2Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fieldPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        NumberOfRounds = new javax.swing.JLabel();
        labelDrew = new javax.swing.JLabel();
        btnCelarBoard = new javax.swing.JButton();
        btnNewGame = new javax.swing.JButton();
        btnSaveGame = new javax.swing.JButton();
        btnLoadGame = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        Instructions = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        draw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ia/coda/cover.png"))); // NOI18N
        draw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawActionPerformed(evt);
            }
        });

        player1Panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane1.setViewportView(player1Panel);

        player2Panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        player2Panel.add(jLabel1);

        jScrollPane2.setViewportView(player2Panel);

        jLabel2.setText("TILE DREW");

        fieldPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane3.setViewportView(fieldPanel);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel5.setText("Round:");

        NumberOfRounds.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        NumberOfRounds.setText("N");

        btnCelarBoard.setText("clear game");
        btnCelarBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCelarBoardActionPerformed(evt);
            }
        });

        btnNewGame.setText("new game");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        btnSaveGame.setText("save game");
        btnSaveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGameActionPerformed(evt);
            }
        });

        btnLoadGame.setText("load game");
        btnLoadGame.setToolTipText("");
        btnLoadGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadGameActionPerformed(evt);
            }
        });

        File.setText("File");

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        File.add(New);

        Save.setText("Save");
        File.add(Save);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        File.add(Exit);

        MenuBar.add(File);

        Help.setText("Help");

        Instructions.setText("Instruction");
        Instructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstructionsActionPerformed(evt);
            }
        });
        Help.add(Instructions);

        MenuBar.add(Help);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NumberOfRounds, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(labelDrew, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(117, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLoadGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSaveGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCelarBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(NumberOfRounds, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCelarBoard)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNewGame)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSaveGame)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLoadGame)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel3)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDrew, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /*
    void drawTiles() {
        int currentPlayer = 1;
        if (currentPlayer == 1) {
            for (int i = 0; i < 16; i++) {
                JLabel label = new JLabel();
                Tile currentDraw = codaGame.getPlayers()[0].getPlayerTiles()[i];
                label.setIcon(new ImageIcon("./" + currentDraw.getFileName()));
                Player1Draw.add(label);
                currentPlayer = currentPlayer - 1;
            }
        }
        if (currentPlayer == 0) {
            for (int j = 1; j < 16; j++) {
                JLabel label = new JLabel();
                Tile currentDraw = codaGame.getPlayers()[1].getPlayerTiles()[j];
                label.setIcon(new ImageIcon("./" + currentDraw.getFileName()));
                Player2Draw.add(label);
                currentPlayer = currentPlayer + 1;
            }

        }
        pack();
    }
     */
    void initGame() {

        codaGame = new Game();
        pack();
        setVisible(true);
    }

    void setMainListeners() {
        //Problem with displaying Tiles
        draw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (codaGame.canPlayerDraw()) {
                    super.mouseClicked(e);
                    codaGame.playerCanDraw(false);
                    JLabel label = new JLabel();
                    //Tile tile = codaGame.draw();
                    //String labelClicked = label.getName();
                    //String[] strings = labelClicked.split("_");
                    //int number = Integer.valueOf(strings[1]);

                    Tile clickedTile = codaGame.getDeck().draw();
                    codaGame.setTileDrew(clickedTile);

                    //String numTile = String.valueOf(clickedTile.getNumtile());
                    labelDrew.setIcon(new ImageIcon("./" + clickedTile.getFileName()));
                    labelDrew.setName("label_" + clickedTile.getNumtile());
                    //Player1Draw.add(label);

                    changeismade = true;
                    pack();

                    //To change body of generated methods, choose Tools | Templates.
                    codaGame.setUserCanGuess();
                }
            }
        }
        );

    }

    
    
    void displayGame() {

        Player player1 = codaGame.getPlayers()[0];
        Player player2 = codaGame.getPlayers()[1];

        for (int i = 0; i < player1.getNumTiles(); i++) {
            JLabel label = new JLabel();
            Tile currentTile = player1.getPlayerTiles()[i];
            label.setIcon(new ImageIcon("./" + currentTile.getFileName()));
            player1Panel.add(label);
        }

        for (int j = 0; j < player2.getNumTiles(); j++) {
            JLabel label = new JLabel();
            Tile currentTile = player2.getPlayerTiles()[j];
            label.setIcon(new ImageIcon("./" + currentTile.getFileName()));
            label.setName("Label_" + j);
            player2Panel.add(label);

            IGuessedField thisForm = this;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            if (codaGame.canUserStartGuessing()) {
                                new GuessField().show(thisForm, label);
                            }
                        }
                    });

                }
            });
        }

        //TODO: Display all the board tiles here
        for (int k = 0 ; k<codaGame.getNumOfBoardTiles();k++) {
            JLabel label = new JLabel();
            Tile currentTile = codaGame.getBoardTiles()[k];
            label.setIcon(new ImageIcon("./" + currentTile.getFileName()));
            fieldPanel.add(label);
        }
        
        if (codaGame.getTileDrew() != null) {
            Tile tileDrew = codaGame.getTileDrew();
            labelDrew.setIcon(new ImageIcon("./" + tileDrew.getFileName()));
            labelDrew.setName("label_" + tileDrew.getNumtile());
        }

        pack();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        endGame();
    }//GEN-LAST:event_formWindowClosing

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        initGame();
    }//GEN-LAST:event_NewActionPerformed

    private void endGame() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogueButton = 0;
        int dialogueInput = 0;
        int input = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm message", dialogueInput);
        if (input == 0) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like To Save Your Game First?", "Warning", dialogueButton);
            if (dialogResult == 0) {
                saveGame();
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        endGame();
    }//GEN-LAST:event_ExitActionPerformed

    private void InstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstructionsActionPerformed
        Component Menu = null;
        JOptionPane.showMessageDialog(Menu,
                "The last player with tiles up wins the game\n"
                + "Play any number of rounds\n"
                + "----------------------------------------------\n"
                + "1. Press the START button\n"
                + "2. The Round is displayed at the top\n"
                + "3. Four tiles are randomly distributed to each player\n"
                + "4. Draw a tile from the deck\n"
                + "5. Choose an opponent's tile and guess the tile\n"
                + "6. If it is Correct:"
                + " Guess another tile OR Add the tile drew into your hand\n"
                + "7. If it is Incorrect:"
                + " Reveal the tile drew\n"
                + "8. If there aren't any more tiles to draw:"
                + " Keep guessing without drawing tiles");
    }//GEN-LAST:event_InstructionsActionPerformed

    private void drawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drawActionPerformed

    private void btnCelarBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCelarBoardActionPerformed
        clearGameBoard();

    }//GEN-LAST:event_btnCelarBoardActionPerformed

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        clearGameBoard();
        codaGame = new Game();
        displayGame();
    }//GEN-LAST:event_btnNewGameActionPerformed

    private void btnSaveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGameActionPerformed

        saveGame();
    }//GEN-LAST:event_btnSaveGameActionPerformed

    private void btnLoadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadGameActionPerformed
        clearGameBoard();
        Game codaGame = ObjectReader.loadGame();
        displayGame();
    }//GEN-LAST:event_btnLoadGameActionPerformed

    void closingMethod() {
        // check flag status for changes made
        int roundResult = 0;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        if (changeismade) {
            roundResult = JOptionPane.showConfirmDialog(null, "Would You Like To Save Your Game First?", "Warning", dialogButton);
            // Saving code here
        } else {
            System.exit(0);
        }
        if (roundResult == 1) {
            System.exit(0);
        }
        if (roundResult == 0) {
            System.out.println("saving"); //call save method and then quit
            saveGame();
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CodaBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodaBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodaBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodaBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodaBoard().setVisible(true);
            }
        });
    }

    private void clearGameBoard() {
        player1Panel.removeAll();
        player1Panel.revalidate();
        player1Panel.repaint();
        player2Panel.removeAll();
        player2Panel.revalidate();
        player2Panel.repaint();
        labelDrew.setIcon(null);
        fieldPanel.removeAll();
        fieldPanel.revalidate();
        fieldPanel.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JMenu Help;
    private javax.swing.JMenuItem Instructions;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem New;
    private javax.swing.JLabel NumberOfRounds;
    private javax.swing.JMenuItem Save;
    private javax.swing.JButton btnCelarBoard;
    private javax.swing.JButton btnLoadGame;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnSaveGame;
    private javax.swing.JButton draw;
    private javax.swing.JPanel fieldPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelDrew;
    private javax.swing.JPanel player1Panel;
    private javax.swing.JPanel player2Panel;
    // End of variables declaration//GEN-END:variables
}
