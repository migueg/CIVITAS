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
public class CasillaSorpresa extends Casilla {
     
    private static MazoSorpresas mazo;
    private  Sorpresa sorpresa;
    
    CasillaSorpresa(MazoSorpresas mazo, String nombre){
        super(nombre);
        CasillaSorpresa.mazo = mazo;
        
    }

    public Sorpresa getSorpresa() {
        return sorpresa;
    }
    
    @Override
    public void updateMazo(MazoSorpresas mazo){
        if(!(CasillaSorpresa.mazo == null))
            CasillaSorpresa.mazo = mazo;
    }

    
    @Override
    public void recibeJugador(int iactual , ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
           sorpresa = CasillaSorpresa.mazo.siguiente();
           this.informe(iactual, todos);
           sorpresa.aplicarAJugador(iactual, todos);
        }
            
    }
    
    
      
    @Override
     public String toString() {
        String result = "\nCasilla{\n" + "nombre=" + this.getNombre();
      
        if(this.sorpresa != null){
            result += this.sorpresa.toString();
        }
        result += "\n}";
        return result;
    }


}
