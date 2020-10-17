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
public class Jugador {
    protected static int CasasMax =4;
    protected static int CasasPorHotel = 4;
    protected Boolean encarcelado;
    protected static int HotelesMax = 4;
    private String nombre;
    private int numCasillaActual;
    protected static float PasoPorSalida = 1000;
    protected static float PrecioLibertad = 200;
    private Boolean puedeComprar;
    private float saldo;
    private static float SaldoInicial = 7500;
    
    private ArrayList<TituloPropiedad> propiedades;

    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", numCasillaActual=" + numCasillaActual + ", puedeComprar=" + puedeComprar + ", saldo=" + saldo + ", propiedades=" + propiedades + '}';
    }
    
    Boolean pagaAlquiler(float cantidad){
        throw new UnsupportedOperationException("No implementado");

    }
    
    void cancelarPropiedad (TituloPropiedad p){
        this.propiedades.remove(p);
    }
    Boolean recibe(float cantidad){
        throw new UnsupportedOperationException("No implementado");

    }
    
    protected String getNombre(){
        return this.nombre;
    }
    
    Boolean encarcelar(int numCasillaCarcel){
        throw new UnsupportedOperationException("No implementado");
    }
    
    int getNumCasillaActual(){
        return this.numCasillaActual;
    }
    
    Boolean moverACasilla (int numCasilla){
        throw new UnsupportedOperationException("No implementado");
    }
    
    Boolean modificarSaldo (float cantidad){
         throw new UnsupportedOperationException("No implementado");
    }
    int cantidadCasasHoteles(){
        throw new UnsupportedOperationException("No implementado");
    }
    
    Boolean tieneSalvoconducto (){
        throw new UnsupportedOperationException("No implementado");
    }
    
    Boolean obtenerSalvoconducto(Sorpresa sorpresa){
         throw new UnsupportedOperationException("No implementado");
    }
}
