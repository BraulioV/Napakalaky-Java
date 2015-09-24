/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Napakalaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author braulio
 */
public class CardDealer {
    private static final CardDealer instance = new CardDealer();
    private ArrayList <Monster> unusedMonsters;
    private ArrayList <Monster> usedMonsters;
    private ArrayList <Treasure> unusedTreasures;
    private ArrayList <Treasure> usedTreasures;
    private ArrayList <Cultist> unusedCultists;
    private ArrayList <Cultist> usedCultists;
    
    private CardDealer(){
        this.unusedMonsters = new ArrayList<Monster>();
        this.unusedTreasures = new ArrayList<Treasure>();
        this.usedMonsters = new ArrayList<Monster>();
        this.usedTreasures = new ArrayList<Treasure>();
        this.unusedCultists = new ArrayList<Cultist>();
        this.usedCultists = new ArrayList<Cultist>();
    }
    
    private void initTreasureCardDeck(){
        
        unusedTreasures.add(new Treasure("¡Sí mi amo!",0,4,7,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigación",600,3,4,TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu",500,3,5,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas",400,2,5,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida",800,1,1,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Casco minero",400,2,4,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora Thompson",600,4,8,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario",400,3,6,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano",300,2,3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo",700,3,5,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica",500,2,5,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla",900,4,8,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita",500,4,5,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Insecticida",300,2,3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de tres cañones",700,4,6,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Garabato místico",300,2,2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La fuerza de Mr. T",1000,-1,-1,TreasureKind.NECKLACE));
        unusedTreasures.add(new Treasure("La rebeca metálica",400,2,3,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Mazo de los antiguos",200,3,4,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necroplayboycón",300,3,5,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Lanzallamas",800,4,8,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrocomicón",100,1,1,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón",800,5,7,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Linterna a dos manos",400,3,6,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrognomicón",200,2,4,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom",300,2,3,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Porra preternatural",200,2,3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentáculo de pega",200,0,1,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos",500,0,1,TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Shogulador",600,1,1,TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Varita de atizamiento",400,3,4,TreasureKind.ONEHAND));
        
        shuffleTreasures ();
    }
    
    private void initMonsterCardDeck(){
        
        BadConsequence badConsequence = new BadConsequence ("Pierdes tu armadura visible y"
                + " otra oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
        new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        Prize prize = new Prize (2,1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, prize, badConsequence));

        badConsequence = new BadConsequence ("Embobados con el lindo primigenio "
                + "te descartas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)),
        new ArrayList(Arrays.asList()));
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Chibithulhu", 2, prize, badConsequence));

        badConsequence = new BadConsequence ("El primordial bostezo contagioso. "
                + "Pierdes el calzado visible", 0, new ArrayList(Arrays.asList(TreasureKind.SHOE)),
        new ArrayList(Arrays.asList()));
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, prize, badConsequence));

        badConsequence = new BadConsequence ("Te atrapan para llevarte de fiesta "
                + "y te dejan caer en mitad del vuelo.\n "
                + "Descarta una mano visible y 1 mano oculta",0,
        new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
        new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize (4,1);
        unusedMonsters.add(new Monster("Angeles de la noche ibicenca", 0, prize, badConsequence));

        badConsequence = new BadConsequence ("Pierdes todos tus tesoros visibles", 0,
        Integer.MAX_VALUE, 0);
        prize = new Prize (3,1);
        unusedMonsters.add(new Monster ("El gorron en el umbral", 10, prize, badConsequence));

        badConsequence = new BadConsequence ("Pierdes la armadura visible", 0,
        new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList()));
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, prize, badConsequence));

        badConsequence = new BadConsequence ("Sientes bichos bajo la ropa. Descarta "
                + "la armadura visible", 0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
        new ArrayList(Arrays.asList()));
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Bichgooth", 2, prize, badConsequence));

        badConsequence = new BadConsequence ("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        prize = new Prize (4,2);
        unusedMonsters.add(new Monster("El rey rosa", 13, prize, badConsequence));

        badConsequence = new BadConsequence ("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas", 2, prize, badConsequence));

        badConsequence = new BadConsequence ("Te faltan manos para tanta cabeza. Pierdes \n"
                + " 3 niveles y tus tesoros visibles de las manos", 3, Integer.MAX_VALUE, 0);

        // Monstruo Los hondos
        badConsequence = new BadConsequence("Estos monstruos "
                + "resultan bastante superficiales y te \n aburren mortalmente. "
                + "Estas muerto.", true);
        new ArrayList(Arrays.asList());
        new ArrayList(Arrays.asList());
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Los hondos", 8, prize, badConsequence));

        // Semillas Cthulhu
        badConsequence = new BadConsequence("Pierdes 2 niveles "
                + "y 2 tesoros ocultos", 2, 0, 2);
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, prize, badConsequence));

        //Dameargo
        badConsequence = new BadConsequence("Te intentas escaquear. "
                + "Pierdes una mano visible", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                new ArrayList(Arrays.asList()));
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Dameargo", 1, prize, badConsequence));

        //Pollipólipo volante
        badConsequence = new BadConsequence("Da mucho asquito. Pierdes 3 niveles.", 3, 0, 0);
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, prize, badConsequence));

        //Yskhtihyssg-Goth

        badConsequence = new BadConsequence("No le hace gracia que pronuncien mal\n "
                + "su nombre. Estas muerto", true);
        prize = new Prize(3,1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 12, prize, badConsequence));

        //Familia feliz
        badConsequence = new BadConsequence("La familia te atrapa. Estas muerto", true);
        prize = new Prize(4,1);
        unusedMonsters.add(new Monster("Familia feliz", 1, prize, badConsequence));

        //Roboggoth
        badConsequence = new BadConsequence("La quinta directiva primaria te obliga"
                + " \na perder 2 niveles y un tesoro 2 manos visible", 2,
                new ArrayList(Arrays.asList(TreasureKind.BOTHHAND)),
                new ArrayList(Arrays.asList()));
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Roboggoth", 8, prize, badConsequence));

        //El espia
        badConsequence = new BadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0,
                new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                new ArrayList(Arrays.asList()));
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("El espia", 5, prize, badConsequence));

        //El Lenguas
        badConsequence = new BadConsequence("Menudo susto te llevas. Pierdes 2 niveles "
                + "y 5 tesoros visibles", 2, 5, 0);
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("El Lenguas", 20, prize, badConsequence));
        
        /***********************************************************************************************/
        /*                              MONSTRUOS CON SECTARIOS                                        */
        /***********************************************************************************************/
        
        badConsequence = new BadConsequence("Pierdes una mano visible", 0, 
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                new ArrayList(Arrays.asList()));
        prize = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable", 10, prize, badConsequence, -2));
        
        badConsequence = new BadConsequence("Pierdes tus tesoros visibles. Ja ja ja", 0, 
                Integer.MAX_VALUE, 0);
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Testigos oculares", 6, prize, badConsequence, +2));
        
        badConsequence = new BadConsequence("Hoy no es tu día de suerte. Mueres", true);
        prize = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, prize, badConsequence, +4));
        
        badConsequence = new BadConsequence("Tu gobierno te recorta 2 niveles", 2, 0, 0);
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Serpiente político", 8, prize, badConsequence, -2));
        
        badConsequence = new BadConsequence("Pierdes tu casco y tu armadura visible."
                + "Pierdes tus manos ocultas", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET, TreasureKind.ARMOR)),
        new ArrayList(Arrays.asList(TreasureKind.BOTHHAND, TreasureKind.ONEHAND, TreasureKind.ONEHAND)));
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Felpuggoth", 2, prize, badConsequence, +5));
        
        badConsequence = new BadConsequence("Pierdes dos niveles", 2, 0, 0);
        prize = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth", 16, prize, badConsequence, -4));
        
        badConsequence = new BadConsequence("Pintalabios negro. Pierdes dos niveles.", 2, 0, 0);
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, prize, badConsequence, +3));
        
        shuffleMonsters();
    }
    
    private void initCultistCardDeck(){

        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n"
                + "No puedes dejar de ser sectario", 1));
        unusedCultists.add(new Cultist("Sectario: +2 por cada sectari en juego\n"
                + "No puedes dejar de ser sectario", 2));
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n"
                + "No puedes dejar de ser sectario", 1));
        unusedCultists.add(new Cultist("Sectario: +2 por cada sectari en juego\n"
                + "No puedes dejar de ser sectario", 2));
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n"
                + "No puedes dejar de ser sectario", 1));
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n"
                + "No puedes dejar de ser sectario", 1));
        
        this.shuffleCultist();
        
    }
    
    private void shuffleCultist(){
        Collections.shuffle(unusedCultists);
    }
    
    private void shuffleTreasures(){
        Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonsters(){
        Collections.shuffle(unusedMonsters);
    }
    
    public static CardDealer getInstance(){  
        return instance;
    }
    
    public void giveTreasureBack(Treasure t){
        if (!usedTreasures.contains(t))
            usedTreasures.add(t);
    }
    
    public void giveMonsterBack(Monster m){
        if (!usedMonsters.contains(m))
            usedMonsters.add(m);
    }
    
    public void initCards(){
        this.initMonsterCardDeck();
        this.initTreasureCardDeck();
        this.initCultistCardDeck();
    }
    
    public Treasure nextTreaure(){
        if(this.unusedTreasures.isEmpty()){
            for(Treasure t:this.unusedTreasures)
                this.unusedTreasures.add(t);
            
            this.shuffleTreasures();
            
            this.unusedTreasures.clear();
        }
        
        Treasure next = this.unusedTreasures.get(0);
        this.unusedTreasures.remove(next);
        this.usedTreasures.add(next);
        return next;
    }
    
    public Monster nextMonster(){
        if(this.unusedMonsters.isEmpty()){
            for(Monster m:this.usedMonsters){
                this.unusedMonsters.add(m);
            }
            this.shuffleMonsters();
            this.usedMonsters.clear();
        }
        
        Monster m = this.unusedMonsters.get(0);
        this.unusedMonsters.remove(m);
        this.usedMonsters.add(m);
        
        return m;
    }

    public Cultist nextCultist() {
        if(this.unusedCultists.isEmpty()){
            for(Cultist c:this.usedCultists){
                this.unusedCultists.add(c);
            }
            this.shuffleCultist();
            this.usedCultists.clear();
        }
        
        Cultist c = this.unusedCultists.get(0);
        this.unusedCultists.remove(c);
        this.usedCultists.add(c);
        
        return c;
    }
}
