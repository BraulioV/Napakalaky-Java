/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javax.swing.JOptionPane;
import Napakalaki.CombatResult;
import Napakalaki.Monster;
import Napakalaki.Napakalaki;

/**
 *
 * @author braulio
 */
public class NapakalakyView extends javax.swing.JFrame {

    /**
     * Creates new form NapakalakyView
     */
    
    private Napakalaki napakalakiModel;
    
    public NapakalakyView() {
        initComponents();
    }
    
    public void setNapakalaki(Napakalaki n){
        this.napakalakiModel = n;
        this.monsterView1.setMonster(n.getCurrentMonster());
        this.monsterView1.setVisible(false);
        this.playerView2.setPlayer(this.napakalakiModel.getCurrentPlayer());
        this.playerView2.setNapakalaki(napakalakiModel);
        this.combatres.setTitle("Combar Result");
        this.nextTurnButton.setEnabled(false);
        this.combatButton.setEnabled(false);
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cantPassTurn = new javax.swing.JDialog();
        combatres = new javax.swing.JDialog();
        meetTheMonsterButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nextTurnButton = new javax.swing.JButton();
        combatButton = new javax.swing.JButton();
        monsterView1 = new GUI.MonsterView();
        playerView2 = new GUI.PlayerView();

        javax.swing.GroupLayout cantPassTurnLayout = new javax.swing.GroupLayout(cantPassTurn.getContentPane());
        cantPassTurn.getContentPane().setLayout(cantPassTurnLayout);
        cantPassTurnLayout.setHorizontalGroup(
            cantPassTurnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        cantPassTurnLayout.setVerticalGroup(
            cantPassTurnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout combatresLayout = new javax.swing.GroupLayout(combatres.getContentPane());
        combatres.getContentPane().setLayout(combatresLayout);
        combatresLayout.setHorizontalGroup(
            combatresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        combatresLayout.setVerticalGroup(
            combatresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        meetTheMonsterButton.setText("Meet the Monster");
        meetTheMonsterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetTheMonsterButtonActionPerformed(evt);
            }
        });
        getContentPane().add(meetTheMonsterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 518, -1, -1));

        jLabel1.setText("Napakalaki Game");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        nextTurnButton.setText("Next Turn");
        nextTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTurnButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nextTurnButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(707, 518, -1, -1));

        combatButton.setText("Combat");
        combatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combatButtonActionPerformed(evt);
            }
        });
        getContentPane().add(combatButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(827, 518, -1, -1));

        monsterView1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(monsterView1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, -1, -1));

        playerView2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(playerView2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meetTheMonsterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetTheMonsterButtonActionPerformed
        // TODO add your handling code here:
        monsterView1.setVisible(true);
        combatButton.setEnabled(true);
        meetTheMonsterButton.setEnabled(false);
        playerView2.DisableBuyLevels();
        playerView2.DisableMakeVisible();
        repaint();
        revalidate();
    }//GEN-LAST:event_meetTheMonsterButtonActionPerformed

    private void nextTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTurnButtonActionPerformed
        // TODO add your handling code here:
        boolean canI = this.napakalakiModel.nextTurn();
        
        if(!canI)
            JOptionPane.showMessageDialog(cantPassTurn, "You're not allowed to use"
                    + " next turn button because you haven't done your bad consequence or you've got 5 hidden treasures or more");
        else{
            playerView2.setPlayer(this.napakalakiModel.getCurrentPlayer());
            monsterView1.setMonster(napakalakiModel.getCurrentMonster());
            monsterView1.setVisible(false);
            combatButton.setEnabled(false);
            meetTheMonsterButton.setEnabled(true);
            nextTurnButton.setEnabled(false);
            repaint();
            revalidate();
        }
    }//GEN-LAST:event_nextTurnButtonActionPerformed

    private void combatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combatButtonActionPerformed
        // TODO add your handling code here:
        CombatResult cr = this.napakalakiModel.developCombat();
        String msg = "";
            
        switch(cr){
            case LoseAndDie:
                msg = "You lost the combat and you died";
            break;
                    
            case LoseAndEscape:
                msg = "You lost the combat but you could escape";
            break;
                    
            case Lose:
                msg = "You lost the combat";
            break;
                 
            case Win:
                msg = "You win this combat";
            break;
                    
            case WinAndWinGame:
                msg = "Win and win game";
                combatButton.setEnabled(false);
                meetTheMonsterButton.setEnabled(false);
                nextTurnButton.setEnabled(false);
                playerView2.DisableAllButtons();
            break;
                
            case LoseAndConvert:
                msg = "You lost and now you're a Cultist Player";
            break;
            
            default:
                    System.exit(0);
            break;
        }
        JOptionPane.showMessageDialog(combatres, msg);
        
        if (cr != CombatResult.WinAndWinGame) {
            playerView2.setPlayer(napakalakiModel.getCurrentPlayer());
            nextTurnButton.setEnabled(true);
            playerView2.EnableBuyLevels();
            combatButton.setEnabled(false);
            playerView2.EnableMakeVisible();
        }
        
    }//GEN-LAST:event_combatButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void showView(){
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog cantPassTurn;
    private javax.swing.JButton combatButton;
    private javax.swing.JDialog combatres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton meetTheMonsterButton;
    private GUI.MonsterView monsterView1;
    private javax.swing.JButton nextTurnButton;
    private GUI.PlayerView playerView2;
    // End of variables declaration//GEN-END:variables
}
