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
public class PorCasaHotel  extends Sorpresa{
    PorCasaHotel(Tablero tablero , int valor , String texto){
        super(tablero ,valor, texto , null);
    }
    
    @Override
    void aplicarAJugador(int actual  ,ArrayList<Jugador> todos) {
       if(this.jugadorCorrecto(actual, todos)){
        this.informe(actual, todos);
        Jugador j =  todos.get(actual);
        j.modificarSaldo(this.getValor() * j.cantidadCasasHoteles());
    }
    }
}
