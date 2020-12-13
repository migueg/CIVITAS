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
public class Calle extends Casilla{
    
    private TituloPropiedad tituloPropiedad;
    
    Calle (TituloPropiedad titulo){
        super(titulo.getNombre());
        this.tituloPropiedad = titulo;
    
      
    }
    
   @Override
   public void recibeJugador(int iactual , ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            Jugador jugador = todos.get(iactual);

            if(!this.tituloPropiedad.tienePropietario()){
                jugador.puedeComprarCasilla();
                
            }else{
                this.tituloPropiedad.tramitarAlquiler(jugador);
            }
        }

    } 
   
   @Override
   TituloPropiedad getTituloPropiedad() {
      
        return this.tituloPropiedad;
        
    }
   
    @Override
    public String toString() {
        String result = "\nCasilla{\n" + "nombre=" + this.getNombre();
      
        if(this.tituloPropiedad != null)
            result += this.tituloPropiedad.toString();
        
        result += "\n}";
        return result;
    }

}
