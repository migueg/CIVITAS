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
public class ConversionEspeculador extends Sorpresa {
    
    ConversionEspeculador(Tablero tablero){
        super(tablero,500,"conversion a especulador",null);
    }
    
     
   @Override
    void aplicarAJugador(int actual  ,ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador especulador = new JugadorEspeculador(todos.get(actual),this.getValor());
            todos.set(actual, especulador);
        }
    }
}
