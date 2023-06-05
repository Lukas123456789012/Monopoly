/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcv.Monopoly;

/**
 *
 * @author student
 */
public class Cords {
    int Xposition;
    int Yposition;
    int Player;
    Card[][] card;
    Player[] List;
    public Cords (int X, int Y, int P, Card[][] C, Player[] L) {
        this.Xposition = X;
        this.Yposition = Y;
        this.Player = P;
        this.card = C;
        this.List = L;
        
    }
    public int getX () {
        return this.Xposition;
    }
    public int getY () {
        return this.Yposition;
    }
    
    public int getP () {
        return this.Player;
    }
    
    public void setX (int X) {
        this.Xposition = X;
    }
    public void setY (int Y) {
        this.Yposition = Y;
    }
    public void setP (int P) {
        this.Player = P;
    }
    public Card[][] getC () {
        return this.card;
    }
    public Player[] getL () {
        return this.List;
    }
}
