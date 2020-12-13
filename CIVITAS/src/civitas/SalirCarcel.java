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
public class SalirCarcel extends Sorpresa {

    SalirCarcel(Tablero tablero){
        super(tablero ,-1, "salir carcel" , null);
    }    
    
    
    @Override
     void aplicarAJugador(int actual  ,ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Boolean latienen = false;
            for(Jugador j : todos){
                 if( j.tieneSalvoconducto()){
                     latienen = true;
                 }
            }

            if(!latienen){
                todos.get(actual).obtenerSalvoconducto(this);
                this.salirDelMazo();
            }

        }

    }
}
