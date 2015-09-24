/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Napakalaki;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author braulio
 */
public class CultistPlayer extends Player{
    
    // Contador de jugadores cultist
    private static int totalCultistPlayer = 0;

    static int getTotalCultistPlayer() {
        return totalCultistPlayer;
    }
    private Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        super(p);
        this.myCultistCard = c;
        this.totalCultistPlayer++;
    }
    
    public Cultist getCultist(){
        return this.myCultistCard;
    }
    
    @Override
    protected int getCombatLevel(){
        return super.getCombatLevel() + myCultistCard.getSpecialValue();
    }
    
    protected boolean shouldConvert(){
        return false;
    }
    
    @Override
    protected int getOponentLevel(Monster m){
        return m.getSpecialValue();
    }
    
    @Override
    protected float computeGoldCoinsValue(ArrayList<Treasure> t){
        return super.computeGoldCoinsValue(t) * 2;
    }
}
