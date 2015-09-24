/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Napakalaki;

/**
 *
 * @author braulio
 */
public class Monster implements Card{
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence bc;
    private int levelChangeAgainstCultistPlayer;
    
    public Monster (String n, int cl, Prize pr, BadConsequence badc){
        this.name = n;
        this.combatLevel = cl;
        this.prize = pr;
        this.bc = badc;
        this.levelChangeAgainstCultistPlayer = 0;
        
    }
    
    public Monster (String n, int cl, Prize pr, BadConsequence badc, int lcagp){
        this.name = n;
        this.combatLevel = cl;
        this.prize = pr;
        this.bc = badc;
        this.levelChangeAgainstCultistPlayer = lcagp;
        
    }
    public String getName (){
	return name;
    }

    //Devuelve el nivel de combate del monstruo
    public int getCombatLevel (){
    	return combatLevel;
    }

    //Devuelve el mal rollo del monstruo
    public BadConsequence getBadConsequence(){
        return bc;
    }
    
    public int getLevelsGained() {
       return prize.getLevels();
    }
    
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    
    public boolean kills(){
        return bc.myBadConsequenceIsDeath();
    }
    
    //Devuelve el buen rollo del monstruo
    public Prize getPrize (){
        return prize;
    }
    
    @Override
    public int getBasicValue(){
        return this.getCombatLevel();
    }
    
    @Override
    public int getSpecialValue(){
        return this.getCombatLevel() + this.levelChangeAgainstCultistPlayer;
    }
    
    @Override
    public String toString(){
        return "Name: " + this.name + "\tLevel: " + 
                Integer.toString(this.combatLevel) + "\n" 
                + this.bc.toString();
    }
}
