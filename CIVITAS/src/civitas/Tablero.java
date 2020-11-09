/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Tablero {
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int  porSalida;
    private Boolean tieneJuez;
    private  static final int VALIDA = 1;
    
    Tablero(int indice){
        if(indice >= VALIDA){
            this.numCasillaCarcel = indice;
        }else{
            this.numCasillaCarcel = 1;
        }
        casillas = new ArrayList();
        
        casillas.add(new Casilla("Salida"));
        
        this.porSalida = 0;
        this.tieneJuez = false;
        
        
    }
    
    private Boolean correcto(){
        if((this.casillas.size() > this.numCasillaCarcel )&& tieneJuez){
            return true;
        }else{
            return false;
        }
    }
    
    private Boolean correcto( int numCasilla){
        if(correcto() && (numCasilla >= VALIDA) || (numCasilla == 0) ){
            return true;
        }else{
            return false;
        }
    }
    
    int getCarcel(){
        return this.numCasillaCarcel;
    }
    
    int getPorSalida(){
        if(this.porSalida > 0){
            int aux = this.porSalida;
            this.porSalida -= 1;
            return aux;
         
        }else{
            return this.porSalida;
        }
    }
    void añadeCasilla(Casilla casilla){
     
         if(this.casillas.size()== this.numCasillaCarcel){
            this.casillas.add(new Casilla("Carcel"));
            
        }
            this.casillas.add(casilla);
            
        if(this.casillas.size() == this.numCasillaCarcel){
            this.casillas.add(new Casilla("Carcel"));
            
        }
    }
    
    void añadeJuez(){
        if(!this.tieneJuez){
            this.casillas.add(new Casilla("Juez"));
            this.tieneJuez= true;
            
        }
    }
    
    Casilla getCasilla(int numCasilla){
        if(this.correcto(numCasilla)){
            return this.casillas.get(numCasilla);
            
        }else{
            return null;
        }
    }
    
    int nuevaPosicion(int actual, int tirada){
        if(!correcto()){
            return -1;
        }else{
            int suma = (actual + tirada);
            int numCasilla = suma % this.casillas.size();
            
            
            if(suma != numCasilla){
                this.porSalida++;
            }
            
            return numCasilla;
        }
    }
    
    int calcularTirada(int origen , int destino){
        int diferencia = destino - origen;
        
        if(diferencia < 0){
            return diferencia+this.casillas.size();
        }else{
            return diferencia;
        }
    }
    
    public void updateMazo(MazoSorpresas mazo){
        for(Casilla c : this.casillas){
            c.updateMazo(mazo);
        }
    }
}
