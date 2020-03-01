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
import java.util.Random;
import ia.coda.StartFrame.*;
import java.io.Serializable;

/**
 *
 * @author silviogao
 */
public class CodaBoard extends javax.swing.JFrame implements IGuessedField {

    private boolean changeismade = false;
    private Game codaGame;
    private boolean isPlayer1Turn = true;
    GuessField mGuessField=null; //added

    @Override
    public void onButtonOkGuessedField(int numberoftile, Colore color, JLabel label) {
        mGuessField=null; //added

        if (isPlayer1Turn) {
            //String s = JOptionPane.showInputDialog("Guess the t3ile number");
            System.out.println(label.getName());
            String labelClicked = label.getName();
            String[] strings = labelClicked.split("_");
            int number = Integer.valueOf(strings[1]);
            Tile clickedTile = codaGame.getPlayers()[1].getPlayerTiles()[number];
            System.out.println(clickedTile.getNumtile());

            String numTile = String.valueOf(clickedTile.getNumtile());
            String colorTile = String.valueOf(clickedTile.getColor_tile());

            if ((numTile.equals(String.valueOf(numberoftile)) && (colorTile.equals(String.valueOf(color))))) {

                //removed: fieldPanel.add(label);
                codaGame.addTileToBoard(clickedTile);
                codaGame.getPlayers()[1].deleteTile(clickedTile);

                changeismade = true;
                pack();
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogueButton = 0;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Correct!\nWould You Like To Continue?\n", "Correct", dialogueButton);
                if (dialogResult != 0) { //no continue                   
                    //removed: JLabel tileToAddlbl = new JLabel("");
                    //removed: tileToAddlbl.setIcon(labelDrew.getIcon());
                    //removed: tileToAddlbl.setName("Label_" + codaGame.getPlayers()[0].getNumTiles());
                    //removed: player1Panel.add(tileToAddlbl);                   
                    Player player1 = codaGame.getPlayers()[0];
                    Tile drewTile = codaGame.getTileDrew();
                    player1.addTile(drewTile.getNumtile(), drewTile.getColor_tile());
                    //removed: labelDrew.setIcon(null);
                    //removed: labelDrew.revalidate();
                    codaGame.setTileDrew(null);
                    codaGame.getPlayers()[0].sortPlayerTiles(); //added
                    doGamePlayer2();
                }
            } else {
                //wrong answer
                isPlayer1Turn = false;
                javax.swing.JOptionPane.showMessageDialog(null, "Opss! Wrong", "Incorrect", javax.swing.JOptionPane.WARNING_MESSAGE);
                //removed: JLabel tileToAddlbl = new JLabel("");
                //removed: tileToAddlbl.setIcon(labelDrew.getIcon());
                //removed: tileToAddlbl.setName(labelDrew.getName());
                //removed: fieldPanel.add(tileToAddlbl);
                codaGame.addTileToBoard(codaGame.getTileDrew()); //added

                //removed: labelDrew.setIcon(null);
                //removed: labelDrew.revalidate();
                codaGame.setTileDrew(null);
                codaGame.nextTurn();
                doGamePlayer2();
            }
        }

        clearGameBoard();
        displayGameUno();

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

    /**
     * Creates new form Coda
     */
    public CodaBoard() {
        //StartFrame startframe = new StartFrame();
        initComponents();
        initGame(); //added
        addMainListeners();
        //removed: codaGame = new Game();
        //removed: addMainListeners();
        //removed: displayGameUno();
    }

    void saveGame() {
        ObjectWriter.saveGame(codaGame);
        JOptionPane.showMessageDialog(this, "Game saved successfully", "info", JOptionPane.OK_OPTION);
    }

    void loadGame() {
        clearGameBoard();
        Game loadedGame = ObjectReader.loadGame();
        if (loadedGame!=null) codaGame=loadedGame; else JOptionPane.showMessageDialog(this, "Game wasn't saved", "info", JOptionPane.OK_OPTION);   
        displayGameUno();
    }

    void doGamePlayer2() {
        clearGameBoard(); //added to display game before player2 play
        displayGameUno(); //added
        
        int numberoftile;
        Colore color;
        JLabel label;
        Random rnd = new Random();
        Player player1 = codaGame.getPlayers()[0];

        Tile drewTile = codaGame.draw();
        codaGame.setTileDrew(drewTile);
        labelDrew.setIcon(new ImageIcon("./" + drewTile.getFileName()));
        labelDrew.setName("label_" + drewTile.getNumtile());

        int position = rnd.nextInt(player1.getNumTiles() - 1);
        int numberGuess = rnd.nextInt(12);
        Colore colorGuess = Colore.values()[rnd.nextInt(2)];
        Tile chosenTile = player1.getPlayerTiles()[position];

        //removed: position = 0;
        //removed: colorGuess = Colore.BLACK;

        if (chosenTile.getNumtile() == numberGuess && chosenTile.getColor_tile() == colorGuess) {

            //removed: JLabel lbl = (JLabel) player1Panel.getComponent(position);
            //removed: fieldPanel.add(lbl);
            codaGame.addTileToBoard(chosenTile);
            player1.deleteTile(chosenTile);
            messagePlayer2.setText("Player 2 Guessed Right");
            javax.swing.JOptionPane.showMessageDialog(null, "Player 2 Guessed Right", "Correct", javax.swing.JOptionPane.WARNING_MESSAGE); //Added
            //pack();
            doGamePlayer2();

        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Player 2 Guessed Wrong", "Incorrect", javax.swing.JOptionPane.WARNING_MESSAGE); //Added
            messagePlayer2.setText("Player 2 Guessed Wrong");
            //removed: JLabel tileToAddlbl = new JLabel("");
            //removed: tileToAddlbl.setIcon(labelDrew.getIcon());
            //removed: tileToAddlbl.setName(labelDrew.getName());
            //removed: fieldPanel.add(tileToAddlbl);
            codaGame.addTileToBoard(drewTile);
            //removed: labelDrew.setIcon(null);
            //removed: labelDrew.revalidate();
            codaGame.setTileDrew(null);
            //pack();
            codaGame.playerCanDraw(true);
            codaGame.nextTurn();

            isPlayer1Turn = true;
        }
 
        displayTurnRound();
        codaGame.setUserCanGuess(false);
        codaGame.playerCanDraw(true); //moved
        codaGame.getPlayers()[1].sortPlayerTiles(); //added

    }

    private void displayTurnRound() {
        numberOfRound.setText(Integer.toString(codaGame.getCurrentRound()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Draw = new javax.swing.JButton();
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
        numberOfRound = new javax.swing.JLabel();
        labelDrew = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        messagePlayer2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        Instructions = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Draw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ia/coda/cover.png"))); // NOI18N
        Draw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrawActionPerformed(evt);
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

        numberOfRound.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setText("Deck");

        messagePlayer2.setEnabled(false);
        messagePlayer2.setFocusable(false);
        messagePlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagePlayer2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Player2 Board");

        jLabel7.setText("Player 1 Board");

        File.setText("File");

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        File.add(New);

        loadMenuItem.setText("Load Game");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        File.add(loadMenuItem);

        saveMenuItem.setText("Save Game");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        File.add(saveMenuItem);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDrew, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Draw, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numberOfRound, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(messagePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(numberOfRound, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Draw, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDrew, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(messagePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void initGame() {
        clearGameBoard();
        codaGame = new Game();
        displayGameUno();
    }

    void addMainListeners() {
        Draw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (codaGame.canPlayerDraw()) {
                    super.mouseClicked(e);
                    codaGame.playerCanDraw(false);
                    JLabel label = new JLabel();
                    Tile clickedTile = codaGame.getDeck().draw();
                    codaGame.setTileDrew(clickedTile);
                    labelDrew.setIcon(new ImageIcon("./" + clickedTile.getFileName()));
                    labelDrew.setName("label_" + clickedTile.getNumtile());
                    //Player1Draw.add(label);
                    changeismade = true;
                    pack();
                    codaGame.setUserCanGuess(true);
                }
            }
        }
        );
    }

    void displayGameUno() {
        displayTurnRound();
        //Problem with displaying Tiles
        Player player1 = codaGame.getPlayers()[0];
        Player player2 = codaGame.getPlayers()[1];

        for (int i = 0; i < player1.getNumTiles(); i++) {
            JLabel label = new JLabel();
            Tile currentTile = player1.getPlayerTiles()[i];
            label.setIcon(new ImageIcon("./" + currentTile.getFileName()));
            label.setName("Label_" + i);
            player1Panel.add(label);

        }

        for (int k = 0; k < codaGame.getNumOfBoardTiles(); k++) {
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
                                if (mGuessField==null) mGuessField=new GuessField(); //added
                                if (!mGuessField.isShowing())  //added
                                    mGuessField.show(thisForm, label);
                            }

                        }
                    });
                }
            });
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

    private void DrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrawActionPerformed
    }//GEN-LAST:event_DrawActionPerformed

    private void messagePlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagePlayer2ActionPerformed
    }//GEN-LAST:event_messagePlayer2ActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        loadGame();
    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveGame();
    }//GEN-LAST:event_saveMenuItemActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodaBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Draw;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JMenu Help;
    private javax.swing.JMenuItem Instructions;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem New;
    private javax.swing.JPanel fieldPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelDrew;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JTextField messagePlayer2;
    private javax.swing.JLabel numberOfRound;
    private javax.swing.JPanel player1Panel;
    private javax.swing.JPanel player2Panel;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
