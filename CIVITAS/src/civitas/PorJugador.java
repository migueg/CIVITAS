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
public class PorJugador  extends Sorpresa{
    
    PorJugador(Tablero tablero , int valor , String texto){
        super(tablero ,valor, texto , null);
    }    
    
    @Override
    void aplicarAJugador(int actual  ,ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
     
            Sorpresa nueva;
            nueva = new PagarCobrar(this.getTablero(), this.getValor() * -1,"");
           int contador = 0;
            for(Jugador j : todos){
                if( ! todos.get(actual).equals(j))
                    nueva.aplicarAJugador(contador % todos.size(), todos);
             contador++;  
            }

            Sorpresa nueva2;
            nueva2 = new PagarCobrar(this.getTablero(), this.getValor() * (todos.size()-1 ), "");
            nueva2.aplicarAJugador(actual, todos);
        }   

    }
}
