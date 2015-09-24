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
public class Napakalaki {
    
    // Variables privadas
    
    private static final Napakalaki instance = new Napakalaki();
    private Player currentPlayer;
    private ArrayList<Player> players;
    private CardDealer dealer;
    private Monster currentMonster;
    private int currentPlayerIndex;
    private Dice dice;
    
    // Constructor
    private Napakalaki() {
        this.dealer = CardDealer.getInstance();
        this.currentPlayerIndex = -1;
        this.dice.createInstance(null);
    }
    
    private void initPlayers(ArrayList<String> names) {
        this.players = new ArrayList<Player>();
        for(String name : names){
            Player p = new Player(name);
            this.players.add(p);
        }
    }
    
    private Player nextPlayer() {
        Player p;
        if(this.currentPlayerIndex == -1){
            Random r = new Random();
            this.currentPlayerIndex = r.nextInt(this.players.size());
            p = this.players.get(this.currentPlayerIndex);
        }
        else{
            this.currentPlayerIndex = (this.currentPlayerIndex + 1)%this.players.size();
            p = this.players.get(this.currentPlayerIndex);
        }
        
        this.currentPlayer = p;
        return this.currentPlayer;
    }
    
    private boolean nextTurnIsAllowed() {
        
        boolean isAllowed;
        
        if(this.currentPlayer == null)
            isAllowed = true;
        else
            isAllowed = this.currentPlayer.validState();
        
        return isAllowed;
    }
    
    public static Napakalaki getInstance() {
        return instance;
    }
    
    public CombatResult developCombat() {
        CombatResult result = this.currentPlayer.combat(this.currentMonster);
        if(result == CombatResult.LoseAndConvert){
            Cultist nextCard = dealer.nextCultist();
            CultistPlayer newCultist = new CultistPlayer(currentPlayer, nextCard);
            this.players.set(currentPlayerIndex, newCultist);
            this.currentPlayer = newCultist;
                
        }
        this.dealer.giveMonsterBack(this.currentMonster);
        return result;
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures) {
        for(Treasure t:treasures){
            this.currentPlayer.discardVisibleTreasure(t);
            this.dealer.giveTreasureBack(t);
        }
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures) {
        for(Treasure t:treasures){
            this.currentPlayer.discardHiddenTreasure(t);
            this.dealer.giveTreasureBack(t);
        }
    }
    
    public void makeTreasuresVisibles(ArrayList<Treasure> treasures) {
        for(Treasure t:treasures){
            this.currentPlayer.makeTreasureVisible(t);
        }
    }
    
    public boolean buyLevels(ArrayList<Treasure> visible, 
            ArrayList<Treasure> hidden) {
        boolean canI = this.currentPlayer.buyLevels(visible, hidden);
        return canI;
    }
    
    public void initGame(ArrayList<String> players) {
        this.initPlayers(players);
        dealer.initCards();
        this.nextTurn();
    }
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    public Monster getCurrentMonster() {
        return this.currentMonster;
    }
    
    public boolean nextTurn() {
        boolean stateOK = this.nextTurnIsAllowed();
        
        if(stateOK){
            this.currentMonster = this.dealer.nextMonster();
            this.currentPlayer = this.nextPlayer();
            boolean dead = this.currentPlayer.isDead();
            
            if(dead){
                this.currentPlayer.initTreasures();
            }
        }
        
        return stateOK;
    }
    
    public boolean endOfGame(CombatResult result) {
        return result == CombatResult.WinAndWinGame;
    }
    
}
