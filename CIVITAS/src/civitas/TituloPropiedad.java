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
public class TituloPropiedad {
    
    private float alquilerBase;
    private static float factorInteresesHipoteca = (float)1.1;
    private float factorRevalorizacion;
    private float hipotecaBsase;
    private Boolean hipotecado;
    private String nombre ;
    private int numCasas;
    private int numHoteles ;
    private float precioCompra;
    private float precioEdificar;
    
    private Jugador propietario;
    
    
    TituloPropiedad(String nombre, float alquilerBase , float factorRevalorizacion, float hipotecaBsase, float precioCompra, float  precioEdificar ){
        this.nombre = nombre;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBsase =  hipotecaBsase;
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        
        this.propietario = null;
        this.numCasas = 0;
        this.numHoteles=0;
        this.hipotecado=false;
    }

    @Override
    public String toString() {
        return "TituloPropiedad{" + "alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBsase=" + 
                    hipotecaBsase + ", hipotecado=" + hipotecado + ", nombre=" + nombre + ", numCasas=" + numCasas + ", numHoteles=" + numHoteles 
                    + ", precioCompra=" + precioCompra + ", precioEdificar=" + precioEdificar + ", propietario=" + propietario.toString() + '}';
    }

    private float getImporteHipoteca() {
        throw new UnsupportedOperationException("No implementado");
    }
    
    private float getPrecioAlquiler(){
        
        float precioAlquiler ;
        if(this.propietarioEncarcelado() || this.hipotecado){
            precioAlquiler = 0;
  
        }else{
           precioAlquiler = (float)(this.alquilerBase*(1+(this.numCasas*0.5)+(this.numHoteles*2.5)));
        }
        
        return precioAlquiler;
    }
    
    private float getPrecioVenta(){
        throw new UnsupportedOperationException("No implementado");
    }
    
    private Boolean  propietarioEncarcelado(){
        throw new UnsupportedOperationException("No implementado");
    }
    
    public Boolean getHipotecado(){
        throw new UnsupportedOperationException("No implementado");
    }
    
    void actualizaPropietarioPorConversion( Jugador jugador){

    }
    Boolean cancelarHipoteca( Jugador jugador) {
        throw new UnsupportedOperationException("No implementado");
    }

    int cantidadCasasHoteles(){
       throw new UnsupportedOperationException("No implementado"); 
    }
    Boolean comprar( Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    Boolean construirCasa( Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    Boolean construirHotel(Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    Boolean derruirCasas(int n,  Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    Boolean esEsteElPropietario(Jugador jugador) {
        throw new UnsupportedOperationException("No implementado");
    }
   
    float getImporteCancelarHipoteca(){
        throw new UnsupportedOperationException("No implementado");
    }

    String getNombre(){
        throw new UnsupportedOperationException("No implementado");
    }
    int getNumCasas(){
        throw new UnsupportedOperationException("No implementado");
    }
    int getNumHoteles(){
       throw new UnsupportedOperationException("No implementado"); 
    }
  
    float getPrecioCompra(){
       throw new UnsupportedOperationException("No implementado"); 
    }
    float getPrecioEdificar() {
        throw new UnsupportedOperationException("No implementado");
    }
  
    Jugador getPropietario() {
        throw new UnsupportedOperationException("No implementado");
    }
    Boolean hipotecar( Jugador jugador ){
        throw new UnsupportedOperationException("No implementado");
    }
  
    Boolean tienePropietario(){
        throw new UnsupportedOperationException("No implementado");
    }


    void tramitarAlquiler(Jugador jugador ) {
        throw new UnsupportedOperationException("No implementado");
    }

    Boolean vender(Jugador jugador){
        throw new UnsupportedOperationException("No implementado");

    }

}
