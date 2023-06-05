package mcv.Monopoly;
import com.mrjaffesclass.apcs.messenger.*;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * MVC Template
 * This is a template of an MVC framework used by APCS for the 
 * LandMine project (and others)
 * @author Roger Jaffe
 * @version 1.0
 * 
 */
public class View extends javax.swing.JFrame implements MessageHandler {

  private final Messenger mvcMessaging;
  
  /**
   * Creates a new view
   * @param messages mvcMessaging object
   */
  public View(Messenger messages) {
    mvcMessaging = messages;   // Save the calling controller instance
    initComponents();           // Create and init the GUI components
  }
  
  /**
   * Initialize the model here and subscribe
   * to any required messages
   */
  public void init() {
    // Subscribe to messages here
    mvcMessaging.subscribe("model:variable1Changed", this);
    mvcMessaging.subscribe("model:variable2Changed", this);
    this.mvcMessaging.subscribe("boardChange", this);
    this.mvcMessaging.subscribe("turn", this);
    this.mvcMessaging.subscribe("gameOver", this);
    this.mvcMessaging.subscribe("money", this);
    this.mvcMessaging.subscribe("Moved", this);
    this.mvcMessaging.subscribe("roll", this);
    this.mvcMessaging.subscribe("null", this);
    this.mvcMessaging.subscribe("reset", this);
    this.mvcMessaging.subscribe("Buy", this);
    this.mvcMessaging.subscribe("Send to Reset", this);
    this.mvcMessaging.subscribe("House", this);
    this.mvcMessaging.subscribe("bankrupt", this);
    this.mvcMessaging.subscribe("Railroad", this);
    this.mvcMessaging.subscribe("chance", this);
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by view: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by view: "+messageName+" | No data sent");
    }
    if (messageName.equals("boardChange")) {
      // Get the message payload and cast it as a 2D string array since we
      // know that the model is sending out the board data with the message
      String[][] board = (String[][])messagePayload;
    }
    if (messageName.equals("turn")) {
        int c = ((Integer) messagePayload);
        jLabel3.setText("It is Player " + c + "'s turn.");
        jButton4.setVisible(false);
        jButton3.setVisible(false);
        jButton2.setVisible(true);
        jButton7.setVisible(false);
        jButton6.setVisible(false);
        
    }
    if(messageName.equals("money")) {
        int c = ((Integer) messagePayload);
        jLabel4.setText("You have " + c + "$.");
        jLabel5.setText("");
    }
    if ( messageName.equals("Moved")) {
        Cords a = ((Cords) messagePayload);
        int x = a.getX();
        int y = a.getY();
        Player[] l = a.getL();
        jLabel7.setText("You have moved to " + x + "," +y);
        Card[][] g = a.getC();
        Card c = g[x][y];
        String n = "";
        String t = "";
        if (x == 0 && y ==0){
            n = "Free Parking";
            t = "Nothing Happens";
        } else if (x == 0 && y == 2) {
            n = "Chance Card";
        } else if (x == 0 && y == 5) {
            n = "B. & O. Railroad";
        } else if (x == 0 && y == 8) {
            n = "Water Works";
        } else if (y == 10 && x == 0) {
            n = "go to jail";
        } else if (y == 10 && x == 3) {
            n = "Community Chest";
        } else if (y==10 && x == 5) {
            n = "Short Line";
        } else if (y==10 && x == 6) {
            n = "chance";
        } else if (y == 10 && x == 8) {
            n = "Luxury Tax";
        } else if (x == 10 && y == 10) {
            n = "Go";
        } else if (x == 10 && y == 3) {
            n = "Chance";
        } else if (x== 10 && y == 5) {
            n = "Reading RailRoad";
        } else if (x== 10 && y == 8) {
            n = "Community Chest";
        } else if (x == 10 && y == 6) {
            n = "Income Tax";
        } else if (y == 0 && x == 10){
            n = "Jail";
        } else if (y == 0 && x == 8) {
            n = "Electric Company";
        } else if (y == 0 && x == 5) {
            n = "Pennsylvania RailRoad";
        } else if (y == 0 && x == 3) {
            n = "Community Chest";
        } else {
            n = c.getName();
        }
        jLabel8.setText("You landed on " + n + ".");
        try {jTable1.setValueAt(c.getOwner(),2, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",2, 1);
        }
        jTable1.setValueAt(n,0, 1);
        try {jTable1.setValueAt(c.getOGvalue(),3, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",3, 1);
        }
        try {jTable1.setValueAt(c.getColorPrice(),4, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",4, 1);
        }
        try {jTable1.setValueAt(c.getPropertyCost(),1, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",1, 1);
        }
        try {jTable1.setValueAt(c.getHouse1(),5, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",5, 1);
        }
        try {jTable1.setValueAt(c.getHouse2(),6, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",6, 1);
        }
        try {jTable1.setValueAt(c.getHouse3(),7, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",7, 1);
        }
        try {jTable1.setValueAt(c.getHouse4(),8, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",8, 1);
        }
        try {jTable1.setValueAt(c.getHotel(),9, 1);
        } catch (NullPointerException e) {
            jTable1.setValueAt("...",9, 1);
        }
        
        Player one = l[0];
        Player two = l[1];
        Player three = l[2];
        Player four = l[3];
        jTable2.setValueAt((int)one.getMoney(),0,1);
        jTable2.setValueAt((int)two.getMoney(),0,2);
        jTable2.setValueAt((int)three.getMoney(),0,3);
        jTable2.setValueAt((int)four.getMoney(),0,4);
        jTable2.setValueAt((int)one.getXPosition()+","+one.getYPosition(),1,1);
        jTable2.setValueAt((int)two.getXPosition()+","+two.getYPosition(),1,2);
        jTable2.setValueAt((int)three.getXPosition()+","+three.getYPosition(),1,3);
        jTable2.setValueAt((int)four.getXPosition()+","+four.getYPosition(),1,4);
        
        if (c.getOwner() == 0) {
            this.mvcMessaging.notify("notOwned", 0);
        } else if(c.getOwner() == 1 && c.getOwner() == 2 && c.getOwner() == 3 && c.getOwner() == 4){
            this.mvcMessaging.notify("owned", 0);
        }  else {
            this.mvcMessaging.notify("special", 0);
        }
    }
    if (messageName.equals("null")) {
        jLabel10.setText("you have been fined 150$.");
        
    }
    if (messageName.equals("reset")) {
        jLabel10.setText("");
        jLabel8.setText("");
        jLabel7.setText("");
        jLabel9.setText("");
        
    }
    if (messageName.equals("Buy")) {
        jButton4.setVisible(true);
        jButton3.setVisible(true);
        int c = ((Integer) messagePayload);
        jLabel10.setText("Do you wish to buy this property? it will cost you " + c + "$.");
    }
    if (messageName.equals("Send to Reset")) {
        this.mvcMessaging.notify("end turn", 0);
    }
    if (messageName.equals("House")) {
        jLabel10.setText("Do you Want to Buy a House");
    }
    if (messageName.equals("bankrupt")) {
        jLabel10.setText("You cannot move as you are bankrupt");
    }
    if (messageName.equals("Railroad")) {
        int c = ((Integer) messagePayload);
        jLabel10.setText("RailRoad are not in the game yet, so you will be fined " + c+"$.");
    }
    if (messageName.equals("chance")) {
        int roll=(int)(Math.random()*5+1);
        if (roll == 1) {
            jLabel10.setText("You Landed on chance and drew, Advance to Boardwalk");
            this.mvcMessaging.notify("boardwalk",0);
        } else if (roll == 2) {
            jLabel10.setText("You Landed on chance and drew, Advance to Go");
            this.mvcMessaging.notify("Go",0);
        } else if (roll == 3) {
            jLabel10.setText("You Landed on chance and drew, Bank Pays you 50$ Dividend");
            this.mvcMessaging.notify("Dividend",0);
        } else if (roll == 4) {
            jLabel10.setText("You Landed on chance and drew, Speeding Fine 15$");
            this.mvcMessaging.notify("Speeding",0);
        } else if (roll == 5) {
            jLabel10.setText("You Landed on chance and drew, Building loan Matures. Collect 150$");
            this.mvcMessaging.notify("loan",0);
        }
    }
  }


  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jButton1.setText("New Game?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.getAccessibleContext().setAccessibleDescription("restart");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Board");
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(1000, 1000));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 1000));

        jLayeredPane1.setBackground(new java.awt.Color(255, 51, 102));
        jLayeredPane1.setForeground(new java.awt.Color(255, 153, 102));
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(555, 555));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(555, 555));
        jLayeredPane1.setRequestFocusEnabled(false);
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mcv/Monopoly/monopoly Board.png"))); // NOI18N
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mcv/Monopoly/monopoly hat.png"))); // NOI18N
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DRAG_LAYER);
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 525, 40, 28));
        jLabel2.getAccessibleContext().setAccessibleParent(jLabel2);

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        jButton2.setText("Roll");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("You Haven't moved yet");

        jLabel8.setText("You Haven't moved yet");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Name:", "..."},
                {"Cost:", "..."},
                {"Owner:", "..."},
                {"Regular Payment:", "..."},
                {"Color Payment:", "..."},
                {"1 House:", "..."},
                {"2 Houses:", "..."},
                {"3 Houses:", "..."},
                {"4 Houses:", "..."},
                {"Hotel:", null}
            },
            new String [] {
                "Card", "Properties"
            }
        ));

        jButton3.setText("BUY");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("PASS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("End Turn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Money", "1500", "1500", "1500", "1500"},
                {"Position", "0,0", "0,0", "0,0", "0,0"},
                {null, null, null, null, null}
            },
            new String [] {
                "Test states", "P1", "P2", "P3", "P4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jButton6.setText("BUY");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("PASS");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton5)))
                                .addGap(0, 251, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(415, 415, 415))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3)
                                .addComponent(jLabel10))
                            .addComponent(jButton6))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7)
                            .addComponent(jButton4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(15, 15, 15)
                        .addComponent(jTable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );

        getAccessibleContext().setAccessibleName("15");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    JButton button = (JButton)evt.getSource();
    String a = button.getAccessibleContext().getAccessibleName();
    this.mvcMessaging.notify("newGame", a);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dice1=(int)(Math.random()*6+1);
        int dice2=(int)(Math.random()*6+1);
        int sum= dice1 + dice2;
        jLabel9.setText("You Rolled a " + sum + ".");
        jButton2.setVisible(false);
        jButton5.setVisible(true);
        this.mvcMessaging.notify("roll", sum);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton4.setVisible(false);
        jButton3.setVisible(false);
        jLabel10.setText("You Bought the property.");
        this.mvcMessaging.notify("yes", 0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jButton4.setVisible(false);
        jButton3.setVisible(false);
        jLabel10.setText("You passed on the property.");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton5.setVisible(false);
        this.mvcMessaging.notify("end turn", 0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jButton4.setVisible(false);
        jButton3.setVisible(false);
        jLabel10.setText("You Bought the House.");
        this.mvcMessaging.notify("Hyes", 0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jButton4.setVisible(false);
        jButton3.setVisible(false);
        jLabel10.setText("You passed on the House.");
    }//GEN-LAST:event_jButton7ActionPerformed

    
    
  /**Controller.java:78
   * @param args the command line arguments
   */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
