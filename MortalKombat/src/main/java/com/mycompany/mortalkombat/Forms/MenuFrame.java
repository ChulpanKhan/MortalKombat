/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mortalkombat.Forms;

import com.mycompany.mortalkombat.WTF.Fight;


/**
 *
 * @author MyHuawei
 */
public class MenuFrame extends javax.swing.JFrame {
    //private Game game;
    private Fight fight;
    /**
     * Creates new form MenuFrame
     */
    public MenuFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WelcomePanel = new javax.swing.JPanel();
        MortalKombatLabel = new javax.swing.JLabel();
        newGameButton = new javax.swing.JButton();
        showTableButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        WelcomePanel.setBackground(new java.awt.Color(255, 255, 255));

        MortalKombatLabel.setFont(new java.awt.Font("Comic Sans MS", 2, 24)); // NOI18N
        MortalKombatLabel.setForeground(new java.awt.Color(204, 204, 0));
        MortalKombatLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MortalKombatLabel.setText("Mortal Kombat");

        newGameButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        newGameButton.setText("Начать новую игру");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        showTableButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        showTableButton.setText("Посмотреть таблицу \nрезультатов");
        showTableButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        showTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WelcomePanelLayout = new javax.swing.GroupLayout(WelcomePanel);
        WelcomePanel.setLayout(WelcomePanelLayout);
        WelcomePanelLayout.setHorizontalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WelcomePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(showTableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(WelcomePanelLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(MortalKombatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        WelcomePanelLayout.setVerticalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WelcomePanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(MortalKombatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showTableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(WelcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(WelcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        fight = new Fight();

        LocationDialog locationDialog = new LocationDialog(this, fight);
        locationDialog.setVisible(true);
        locationDialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void showTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTableButtonActionPerformed
        RecordTabDialog recordTab = new RecordTabDialog(this, rootPaneCheckingEnabled);
        recordTab.setVisible(true);
    }//GEN-LAST:event_showTableButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MortalKombatLabel;
    private javax.swing.JPanel WelcomePanel;
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton showTableButton;
    // End of variables declaration//GEN-END:variables
}
