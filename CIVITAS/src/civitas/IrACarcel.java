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
public class IrACarcel  extends Sorpresa{
    
    IrACarcel(Tablero tablero){
        super(tablero,-1,"Ir a carcel", null);
    }
    
   @Override
    void aplicarAJugador(int actual  ,ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            if(this.getTablero() != null)
                todos.get(actual).encarcelar(this.getTablero().getCarcel());
        }
    }
}
