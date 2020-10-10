/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.Random;
/**
 *
 * @author Usuario
 */
 class Dado {
    static final private Dado instance = new Dado();
    static  final private int SalidaCarcel = 5;
    
    static public Dado getInstance(){
        return instance;
    }
    
    private Random random;
    private int ultimoResultado;
    private Boolean debug;
    
    private Dado (){
        random = new Random();
        ultimoResultado = -1;
        debug = false;
        
    }
    
    int tirar(){
       
        if(debug){
            ultimoResultado = 1;
        }else{
             ultimoResultado = (int)(random.nextInt(6))+1;
        }
       
        return ultimoResultado;
    }
    
    Boolean salgoDeLaCarcel(){
        int d = this.tirar();
        if(SalidaCarcel == d)
            return true;
      
        else
            return false;
    }
    
    int quienEmpieza(int n){
        return random.nextInt(n);
    }
    
    void setDebug(Boolean d){
        debug = d;
        
        Diario.getInstance().ocurreEvento("Se ha puesto el modo debug a "+d);
    }
    
    int getUltimoResultado(){
        return this.ultimoResultado;
    }
}
