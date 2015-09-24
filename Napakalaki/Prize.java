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
public class Prize {
    ////////////////////////////////////////////////////////////////////////////
    
    private int treasures;
    private int levels;
    
    ////////////////////////////////////////////////////////////////////////////
    
    public Prize(int t, int l){
        this.treasures = t;
        this.levels = l;
    }
    
    public int getTreasures(){ 
        return this.treasures;
    }
    
    public int getLevels(){
        return this.levels;
    }
    
    @Override
    public String toString(){
        return "Treasures gained: " + Integer.toString(this.treasures) 
                + "\tLevels: " + Integer.toString(this.levels);
    }
}
