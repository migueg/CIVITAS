/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author Usuario
 */
public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInmobiliarias gestion;

    public OperacionInmobiliaria( GestionesInmobiliarias gestion , int numPropiedad) {
        this.numPropiedad = numPropiedad;
        this.gestion = gestion;
    }

    public int getNumPropiedad() {
        return numPropiedad;
    }

    public GestionesInmobiliarias getGestion() {
        return gestion;
    }
    
    
}
