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
public class IrACasilla extends Sorpresa{
    IrACasilla(Tablero tablero , int valor , String texto){
        super(tablero ,valor, texto , null);
    }
    
    @Override
     void aplicarAJugador(int actual  ,ArrayList<Jugador> todos) {
        Tablero t = this.getTablero();
        if(this.jugadorCorrecto(actual, todos) && t != null){
            this.informe(actual, todos);
            int casillaActual = todos.get(actual).getNumCasillaActual();
            int tirada = t.calcularTirada(casillaActual, this.getValor());
            int nueva = t.nuevaPosicion(casillaActual, tirada);
            if(todos.get(actual).moverACasilla(nueva))
               t.getCasilla(nueva).recibeJugador(actual, todos);
        }
    
    }
}
