/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Napakalaki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author braulio
 */
public class Player {
    // Métodos privados
    private boolean dead;
    private String name;
    private int level;
    private ArrayList<Treasure> visibleTreasures;
    private ArrayList<Treasure> hiddenTreasures;
    private BadConsequence pendingBadConsequence;
    
    // Constructor
    public Player(String name) {
        this.name = name;
        this.dead = true;
        this.level = 1;
        this.hiddenTreasures = new ArrayList<Treasure>();
        this.visibleTreasures = new ArrayList<Treasure>();
        this.pendingBadConsequence = new BadConsequence("",0,0,0);
    }
    
    // Constructor de copia
    public Player(Player p){
        this.name = p.name;
        this.dead = p.dead;
        this.level = p.level;
        this.hiddenTreasures = p.hiddenTreasures;
        this.visibleTreasures = p.visibleTreasures;
        this.pendingBadConsequence = p.pendingBadConsequence;
    }
    
    protected int getOponentLevel(Monster m){
        return m.getBasicValue();
    }
    
    protected boolean shouldConvert(){
        Dice dice = Dice.getInstance();
        boolean should = false;
        
        if(dice.nextNumber() == 6)
            should = true;
        
        return should;
    }
    
    private void bringToLife() {
        this.dead = false;
    }
    
    private void discardNecklaceIfVisible(){
        boolean devuelto = false;
        for(int i = 0; i < this.visibleTreasures.size() && !devuelto; i++){
            Treasure t = this.visibleTreasures.get(i);
            if(t.getType() == TreasureKind.NECKLACE){
                CardDealer d = CardDealer.getInstance();
                
                d.giveTreasureBack(t);
                
                this.visibleTreasures.remove(t);
            }
        }
    }
    
    protected int getCombatLevel() {
        int combatLevel = this.level;
        
        boolean collarEquipado = false;
        
        for(int i = 0; i < this.visibleTreasures.size()
            && !collarEquipado; i++)
            if(this.visibleTreasures.get(i).getType() == TreasureKind.NECKLACE)
                collarEquipado = true;
        
        if(collarEquipado)
            for(int i = 0; i < this.visibleTreasures.size(); i++){
                combatLevel += this.visibleTreasures.get(i).getMaxBonus();
            }
                
        else
            for(int i = 0; i < this.visibleTreasures.size(); i++){
                combatLevel += this.visibleTreasures.get(i).getMinBonus();
            }
        
        return combatLevel;
    }
    
    private void incrementLevels(int l) {
        this.level += l;
        
        if(this.level > 10)
            this.level = 10;
    }
    
    private void decrementeLevels(int l) {
        this.level -= l;
        
        if(this.level < 1)
            this.level = 1;
    }
    
    private void setPendingBadConsequence(BadConsequence b) {
        this.pendingBadConsequence = b;
    }
    
    private void dieIfNoTreasures() {
        if(this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty())
            this.die();
    }
    
    private void die() {
        this.level = 1;
        
        CardDealer d = CardDealer.getInstance();
        
        this.dead = true;
        
        for(Treasure t:this.visibleTreasures)
            d.giveTreasureBack(t);
        
        this.visibleTreasures.clear();
        
        for(Treasure t:this.hiddenTreasures)
            d.giveTreasureBack(t);

        this.hiddenTreasures.clear();
    }
    
    protected float computeGoldCoinsValue(ArrayList<Treasure> t) {
        int monedas = 0;
                
        for(Treasure tr : t)
            monedas += tr.getGoldCoins();
        
        return monedas;
    }
    
    private boolean canIBuyLevels(int l) {
        boolean canI = true;
        
        if(this.level + l >= 10)
            canI = false;
        
        return canI;
    }
    
    private void applyPrize(Monster currentMonster){
        int nLevels = currentMonster.getLevelsGained();
        this.incrementLevels(nLevels);
        int nTreasures = currentMonster.getTreasuresGained();
        
        if(nTreasures > 0){
            CardDealer dealer = CardDealer.getInstance();
            
            for(int i = 0; i < nTreasures; i++){
                Treasure treasure = dealer.nextTreaure();
                this.hiddenTreasures.add(treasure);
            }
        }
    }
    
    private void applyBadConsequence(BadConsequence bad){
        int nLevels = bad.getLevels();
        this.decrementeLevels(nLevels);
        BadConsequence pendingBad = bad.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        this.setPendingBadConsequence(pendingBad);
        pendingBadConsequence.setL(0);
        CardDealer dealer = CardDealer.getInstance();
        if (pendingBadConsequence.getNHiddenTreasures() >= hiddenTreasures.size()) {
            for(Treasure t:this.hiddenTreasures){
                dealer.giveTreasureBack(t);
            }
            hiddenTreasures.clear();
            pendingBadConsequence.setNH(0);
        }
        
        if (pendingBadConsequence.getNVisibleTreasures() >= visibleTreasures.size()) {
            
            for(Treasure t:this.visibleTreasures){
                dealer.giveTreasureBack(t);
            }
            visibleTreasures.clear();
            pendingBadConsequence.setNV(0);
        }
        
        if (!pendingBadConsequence.getSpecificHiddenTreasures().isEmpty()) {
            for (TreasureKind tk : pendingBadConsequence.getSpecificHiddenTreasures()) {
                for (Treasure t : hiddenTreasures) {
                    if (t.getType() == tk) {
                        dealer.giveTreasureBack(t);
                        hiddenTreasures.remove(t);
                        pendingBadConsequence.getSpecificHiddenTreasures().remove(tk);
                    }
                }
            }
        }
        
        if (!pendingBadConsequence.getSpecificVisibleTreasures().isEmpty()) {
            for (TreasureKind tk : pendingBadConsequence.getSpecificVisibleTreasures()) {
                for (Treasure t : visibleTreasures) {
                    if (t.getType() == tk) {
                        dealer.giveTreasureBack(t);
                        visibleTreasures.remove(t);
                        pendingBadConsequence.getSpecificVisibleTreasures().remove(tk);
                    }
                }
            }
        }
    }
    
    private boolean canMakeTreasureVisible(Treasure t){ 
        int bothH = 0, oneH = 0, armor = 0, sh = 0, helm = 0, neck = 0;
        
        boolean canI = false;
        
        for(Treasure tr : this.visibleTreasures){
            if(tr.getType() == TreasureKind.ARMOR)
                armor++;
            else if(tr.getType() == TreasureKind.BOTHHAND)
                bothH++;
            else if(tr.getType() == TreasureKind.HELMET)
                helm++;
            else if(tr.getType() == TreasureKind.NECKLACE)
                neck++;
            else if(tr.getType() == TreasureKind.ONEHAND)
                oneH++;
            else if(tr.getType() == TreasureKind.SHOE)
                sh++;
        }
        
        switch(t.getType()){
            case ARMOR:
                if(armor == 0)
                    canI = true;
                break;
            case ONEHAND:
                if(oneH < 2 && bothH == 0)
                    canI = true;
                break;
            case BOTHHAND:
                if(oneH == 0 && bothH == 0)
                    canI = true;
                break;
            case HELMET:
                if(helm == 0)
                    canI = true;
                break;
            case SHOE:
                if(sh == 0)
                    canI = true;
                break;
            case NECKLACE:
                if(neck == 0)
                    canI = true;
                break;
        }
        
        return canI;
    }
    
    private int howManyHiddenTreasures(TreasureKind tKind){
        int howMany = 0;
        
        for(Treasure t:this.hiddenTreasures)
            if(t.getType() == tKind)
                howMany += 1;
        
        return howMany;
    }
    
    private int howManyVisibleTreasures(TreasureKind tKind){
        int howMany = 0;
        
        for(Treasure t:this.visibleTreasures)
            if(t.getType() == tKind)
                howMany += 1;
        
        return howMany;
    }
    
    public boolean isDead(){
        return this.dead;
    }
    
    public ArrayList<Treasure> getHiddenTreasures(){
        return this.hiddenTreasures;
    }
    
    public ArrayList<Treasure> getVisibleTreasures(){
        return this.visibleTreasures;
    }
    
    public CombatResult combat(Monster m){
       int myLevel = this.getCombatLevel();
       
       CombatResult combatResult;
       
       int monsterLevel = this.getOponentLevel(m);
       
       if(myLevel > monsterLevel){
           this.applyPrize(m);
           if(this.level >= 10)
               combatResult = CombatResult.WinAndWinGame;
           else
               combatResult = CombatResult.Win;
       }
       else{
           Dice dice = Dice.getInstance();
           
           int escape = dice.nextNumber();
           
           if(escape > 5){ 
               boolean amIDead = m.kills();
               
               if(amIDead){
                   this.die();
                   combatResult = CombatResult.LoseAndDie;
               }
               else {
                   BadConsequence bad = m.getBadConsequence();
                   this.applyBadConsequence(bad);
                   combatResult = CombatResult.Lose;
                   if(this.shouldConvert())
                       combatResult = CombatResult.LoseAndConvert;
               }
           }
           else {
               combatResult = CombatResult.LoseAndEscape;
           }
       }
       
       this.discardNecklaceIfVisible();
       
       return combatResult;
    }
    
    public void makeTreasureVisible(Treasure t){
        boolean canI = this.canMakeTreasureVisible(t);
        if(canI){
            this.visibleTreasures.add(t);
            this.hiddenTreasures.remove(t);
        }
    }
    
    public void discardVisibleTreasure(Treasure t){
        this.visibleTreasures.remove(t);
        if(this.pendingBadConsequence != null &&
                !this.pendingBadConsequence.isEmpty()){
            this.pendingBadConsequence.substractVisibleTreasure(t);
        }
        
        this.dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t){
        this.hiddenTreasures.remove(t);
        if(this.pendingBadConsequence != null &&
                !this.pendingBadConsequence.isEmpty()){
            this.pendingBadConsequence.substractHiddenTreasure(t);
        }
        
        this.dieIfNoTreasures();
    }
    
    public boolean buyLevels(ArrayList<Treasure> visible, 
            ArrayList<Treasure> hidden){
        float levelsMayBought = this.computeGoldCoinsValue(visible);
        levelsMayBought += this.computeGoldCoinsValue(hidden);
        
        boolean canI = this.canIBuyLevels((int)(levelsMayBought/1000));
        
        if(canI)
            this.incrementLevels(level);
        
        this.visibleTreasures.removeAll(visible);
        this.hiddenTreasures.removeAll(hidden);
        
        CardDealer dealer = CardDealer.getInstance();
        
        for(Treasure t:visible){
            dealer.giveTreasureBack(t);
        }
        
        for(Treasure t:hidden){
            dealer.giveTreasureBack(t);
        }
        
        return canI;
    }
    
    public boolean validState(){
        boolean valid = false;
        
        if(this.pendingBadConsequence.isEmpty() && this.hiddenTreasures.size() <= 4)
            valid = true;
        
        return valid;
    }
    
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstance();
        
        Dice dice = Dice.getInstance();
        
        this.bringToLife();
        
        Treasure treasure = dealer.nextTreaure();
        
        this.hiddenTreasures.add(treasure);
        
        int number = dice.nextNumber();
        
        if(number > 1){
            treasure = dealer.nextTreaure();
            this.hiddenTreasures.add(treasure);
        }
        if(number == 6){
            treasure = dealer.nextTreaure();
            this.hiddenTreasures.add(treasure);
        }
    }
    
    public boolean hasVisibleTreasures(){
        return !this.visibleTreasures.isEmpty();
    }
    
    public int getLevels(){
        return this.level;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String toString(){
        return "Name: " + this.name + "\tLevel: " + this.level;
    }
}
