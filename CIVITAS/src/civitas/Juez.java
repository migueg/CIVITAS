/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import static civitas.Casilla.carcel;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Juez extends Casilla{
     Juez(int numCasillaCarcel, String nombre){
       super(nombre);
       carcel = numCasillaCarcel;
    }
     
    @Override
    public void recibeJugador(int iactual , ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }
    
 

    
}
