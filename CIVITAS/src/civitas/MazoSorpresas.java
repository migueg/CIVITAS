/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Migue
 */
public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas;
    private Boolean barajada;
    private int usadas;
    private Boolean debug;
    private  ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    private void init(){
        this.sorpresas = new ArrayList();
        this.cartasEspeciales = new ArrayList();
        this.barajada = false;
        this.usadas = 0;
        
    }
    
    MazoSorpresas (Boolean d){
        this.init();
        this.debug = d;
        if(d)
          Diario.getInstance().ocurreEvento("Se ha puesto el modo debug a "+d);
    }
    
    MazoSorpresas (){
        this.init();
        debug = false;
        
    }
    
    
    void alMazo(Sorpresa s){
        if(!barajada){
            this.sorpresas.add(s);
        }
    }
    
    Sorpresa siguiente(){
        if(!this.barajada || this.usadas == this.sorpresas.size()){
            if(!debug){
                this.usadas = 0;
                this.barajada = true; 
                Collections.shuffle(this.sorpresas); // Baraja el mazo
            }
           
        }
        
        this.usadas++;
        Sorpresa temp = this.sorpresas.get(0);
        this.sorpresas.remove(temp);
        this.sorpresas.add(temp);
        
        this.ultimaSorpresa = temp;
        
        return this.ultimaSorpresa;
        
    }
    
    void inhabilitarCartaEspecial (Sorpresa sorpresa){
        if(this.sorpresas.contains(sorpresa)){
            int index = this.sorpresas.indexOf(sorpresa);
            this.sorpresas.remove(index);
            this.cartasEspeciales.add(sorpresa);
            Diario.getInstance().ocurreEvento("Se ha cambiado una carta especial al mazo de especiales");
        }
    }
    
    void habilitarCataEspecial(Sorpresa sorpresa){
        if(this.cartasEspeciales.contains(sorpresa)){
            int index  = this.cartasEspeciales.indexOf(sorpresa);
            this.cartasEspeciales.remove(index);
            this.sorpresas.add(sorpresa);
             Diario.getInstance().ocurreEvento("Se ha cambiado una carta especial al mazo de sorpresas");
        }
        
    }
    
    
}
