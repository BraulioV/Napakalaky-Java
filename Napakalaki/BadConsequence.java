/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Napakalaki;

import java.util.ArrayList;

/**
 *
 * @author braulio
 */
public class BadConsequence {
    
    ////////////////////////////////////////////////////////////////////////////
    
    private String text;
    private int levels;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificVisibleTreasures;
    private ArrayList<TreasureKind> specificHiddenTreasures;
    
    ////////////////////////////////////////////////////////////////////////////
    
    public boolean isEmpty(){
        boolean isEmpty = false;
        
        if(this.levels == 0 && this.nVisibleTreasures == 0 && 
                this.nHiddenTreasures == 0 && this.specificHiddenTreasures.isEmpty() && 
                this.specificVisibleTreasures.isEmpty() && this.death == false){
            isEmpty = true;
        }
        
        return isEmpty;
            
    }
    
    public String getText(){
        return this.text;
    }
    
    public int getLevels(){
        return this.levels;
    }
    
    public int getNVisibleTreasures(){
        return this.nVisibleTreasures;
    }
    
    public int getNHiddenTreasures(){
        return this.nHiddenTreasures;
    }
    
    public void setNH(int nh){
        this.nHiddenTreasures = nh;
    }
    
    public void setNV (int nv) {
        this.nVisibleTreasures = nv;
    }
    
    public void setL (int l) {
        this.levels = l;
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
        return this.specificHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return this.specificVisibleTreasures;
    }
    
    public void substractVisibleTreasure(Treasure t){
        if (this.nVisibleTreasures != 0)
            --nVisibleTreasures;
        
        else if (!this.specificVisibleTreasures.isEmpty())
            if (specificVisibleTreasures.contains(t.getType()))
                specificVisibleTreasures.remove(t.getType());
    }
    
    public void substractHiddenTreasure(Treasure t){
        if (this.nHiddenTreasures != 0)
            --nHiddenTreasures;
        
        else if (!this.specificHiddenTreasures.isEmpty())
            if (specificHiddenTreasures.contains(t.getType()))
                specificHiddenTreasures.remove(t.getType());
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    
    public BadConsequence(String t, int l, int nVisible, int nHidden){
        this.text = t;
        this.levels = l;
        this.nVisibleTreasures = nVisible;
        this.nHiddenTreasures = nHidden;
        this.death = false;
        this.specificHiddenTreasures = new ArrayList<TreasureKind>();
        this.specificVisibleTreasures = new ArrayList<TreasureKind>();
        
    }
    
    public BadConsequence(String t, int l, ArrayList<TreasureKind> v,
            ArrayList<TreasureKind> h){
        
        this.text = t;
        this.levels = l;
        this.specificVisibleTreasures = v;
        this.specificHiddenTreasures = h;
        this.nHiddenTreasures = 0;
        this.nVisibleTreasures = 0;
        this.death = false;
    }
    
    public BadConsequence(String t, boolean death){
        this.text = t;
        this.levels = 0;
        this.nHiddenTreasures = 0;
        this.nVisibleTreasures = 0;
        this.specificHiddenTreasures = new ArrayList<TreasureKind>();
        this.specificVisibleTreasures = new ArrayList<TreasureKind>();
        this.death = death;
    }
    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v,
            ArrayList<Treasure> h){
        ArrayList<TreasureKind> tesoros_visibles = new ArrayList();
        ArrayList<TreasureKind> tesoros_ocultos = new ArrayList();
        int nv = 0, nh = 0;
        BadConsequence bd = new BadConsequence ("",0,0,0);
        
        // comprobamos de que tipo es el mal rollo que debe cumplir el jugador
        if (this.nHiddenTreasures != 0 || this.nVisibleTreasures != 0) {
            // si debe perder un numero de tesoros especifico, pero no se dice ningun tipo,
            // miramos el size del vector de tesoros del jugador e igualamos nv y nh al
            // menor:
            if (v.size() <= nVisibleTreasures)
                nv = v.size();
            
            else
                nv = nVisibleTreasures;
            
            if (h.size() <= nHiddenTreasures)
                nh = h.size();
            
            else
                nh = nHiddenTreasures;
            
            // por ultimo llamamos al contructor de badconsequence
            bd = new BadConsequence (this.text,this.levels,nv,nh);
        }
        
        else if (!this.specificHiddenTreasures.isEmpty() || !this.specificVisibleTreasures.isEmpty()) {
            // si debe perder un tipo de tesoro determinado hacemos la interseccion de ambos arrays
            tesoros_visibles = InterseccionTesoros (v, specificVisibleTreasures);
            tesoros_ocultos = InterseccionTesoros(h, specificHiddenTreasures);
            bd = new BadConsequence (this.text,this.levels,tesoros_visibles,tesoros_ocultos);
        }
        
        return bd;
    }
    
    private ArrayList<TreasureKind> InterseccionTesoros (ArrayList<Treasure> lista, ArrayList<TreasureKind> tk) {
        // funcion que calcula la interseccion de un conjunto de tipos de tesoros
        // y un conjunto de tesoros.
        ArrayList<TreasureKind> interseccion = new ArrayList();
        
        for (Treasure t : lista)
            interseccion.add(t.getType());
        
        ArrayList<TreasureKind> tipos_comunes = new ArrayList (interseccion);
        
        tipos_comunes.retainAll(tk);
        
        return tipos_comunes;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public boolean myBadConsequenceIsDeath(){
        return this.death;
    }
    
    @Override
    public String toString(){
        String string = "Bad consequence: " + this.text + "\tLevels: " 
                + Integer.toString(this.levels);
        
        if(this.death)
            string += "\tDeath: Yes";
        else
            string += "\tDeath: No";
        
        return string;
    }
}
