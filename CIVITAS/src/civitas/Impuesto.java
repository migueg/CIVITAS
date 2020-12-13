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
public class Impuesto extends Casilla{
    
    private float importe;
    
    Impuesto (float cantidad , String nombre){
     super(nombre);
     this.importe = cantidad;

    }
       
       
    @Override
    public void recibeJugador(int iactual , ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }
    
    @Override
    public String toString() {
        String result = "\nCasilla{\n" + "nombre=" + this.getNombre();
        if(this.importe != -1.0){
            result += "\nImporte = "+ this.importe;
        }
      
        
        result += "\n}";
        return result;
    }

}
