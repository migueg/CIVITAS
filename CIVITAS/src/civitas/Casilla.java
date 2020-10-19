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
public class Casilla {
    private String nombre;
    private float importe;
    private static int carcel;
    private TipoCasilla tipo;
    private TituloPropiedad tituloPropiedad;
    private TipoSorpresa sorpresa;
    private MazoSorpresas mazo;
    
     Casilla (String nombre){
        this.init();
        this.nombre = nombre;
        
        this.tipo = TipoCasilla.DESCANSO;
    }
    
    Casilla (TituloPropiedad titulo){
        this.init();
        this.tituloPropiedad = titulo;
        this.tipo = TipoCasilla.CALLE;
        this.nombre = titulo.getNombre();
    }
    
    Casilla (float cantidad , String nombre){
        this.init();
        this.tipo = TipoCasilla.IMPUESTO;
        this.importe = cantidad;
        this.nombre = nombre;
    }
    
    Casilla(int numCasillaCarcel, String nombre){
        this.init();
        carcel = numCasillaCarcel;
        this.nombre = nombre;
        this.tipo = TipoCasilla.JUEZ;
        
    }
    
    Casilla(MazoSorpresas mazo, String nombre){
        this.init();
        this.mazo = mazo;
       
        this.nombre = nombre;
        this.tipo = TipoCasilla.SORPRESA;
        
    }
    
    private void recibeJugador_calle(int iactual , ArrayList<Jugador> todos){

    }
    private void recibeJugador_impuesto(int iactual , ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }
    private void recibeJugador_juez(int iactual , ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }
    private void recibeJugador_sorpresa(int iactual , ArrayList<Jugador> todos){
        
    }
    
    private void informe(int iactual , ArrayList<Jugador> todos ){
         Diario.getInstance().ocurreEvento("El jugador "+ todos.get(iactual).getNombre() + "ha caido en la casilla " + this.getNombre()); 
         Diario.getInstance().ocurreEvento("Info de la casilla " + this.toString());
         
    }
    private void init(){
        this.tipo = null;
        this.importe = -1;
        this.mazo = null;
        this.nombre = "";
        this.sorpresa = null;
        this.tituloPropiedad = null;
    } 
     String getNombre(){
         return this.nombre;
     }
     
  
    
    TituloPropiedad getTituloPropiedad() {
      
        return this.tituloPropiedad;
        
    }
   
    public Boolean jugadorCorrecto(int iactual , ArrayList<Jugador> todos){
         if(iactual >= 0 && iactual <= todos.size()){
             return true;
         }else
            return false;
    }
    void recibeJugador(int iactual , ArrayList<Jugador> todos){

    }
 

    @Override
    public String toString() {
        return "Casilla{" + "nombre=" + nombre + ", importe=" + importe + ", tipo=" + tipo + ", tituloPropiedad=" + tituloPropiedad + ", sorpresa=" + sorpresa + ", mazo=" + mazo + '}';
    }

}
