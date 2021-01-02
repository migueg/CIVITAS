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
 
    protected static int carcel;
  
    Casilla (String nombre ){
        this.nombre = nombre;
    }
    void informe(int iactual , ArrayList<Jugador> todos ){
         Diario.getInstance().ocurreEvento("El jugador "+ todos.get(iactual).getNombre() + "ha caido en la casilla " + this.getNombre()); 
         Diario.getInstance().ocurreEvento("Info de la casilla " + this.toString());
         
    }
  
    public String getNombre(){
         return this.nombre;
     }
     
    public Boolean jugadorCorrecto(int iactual , ArrayList<Jugador> todos){
         if(iactual >= 0 && iactual <= todos.size()){
             return true;
         }else
            return false;
    }
    void recibeJugador(int iactual , ArrayList<Jugador> todos){
        
        this.informe(iactual, todos);
    }
 
 
    @Override
    public String toString() {
        String result = "\nCasilla{\n" + "nombre=" + nombre;

        result += "\n}";
        return result;
    }
    
    
     public void updateMazo(MazoSorpresas mazo){
    }
    
    TituloPropiedad getTituloPropiedad() {return null;}
}
