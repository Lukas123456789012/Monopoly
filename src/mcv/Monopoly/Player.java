/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcv.Monopoly;


public class Player {
    int Money;
    int Xposition;
    int Yposition;
    Card[] properties;
    int i;
    boolean Bankrupt;
    public Player (int Money, int Xposition, int Yposition) {
        this.Money = Money;
        this.Xposition = Xposition;
        this.Yposition = Yposition;
        i = 0;
        Bankrupt = false;
        
    }
    public void addproperties(Card card) {
        this.properties[i] = card;
        i++;
    }
    public boolean findCard(Card card) {
        for (Card propertie : this.properties) {
            if (propertie == card) {
                return true;
            }
        }
        return false;
    }
    public int getMoney() {
        return this.Money;
    }
    public int getXPosition() {
        return this.Xposition;
    }
    public int getYPosition() {
        return this.Yposition;
    }
    public void setYPosition(int i) {
        this.Yposition = i;
    }
    public void setXPosition(int i) {
        this.Xposition = i;
    }
    public void subMoney(int i) {
        this.Money -= i;
    }
    public void addMoney(int i) {
        this.Money += i;
    }
    public boolean getBankrupt() {
        return this.Bankrupt;
    }
    public void setBankrupt() {
        this.Bankrupt = true;
    }
}
