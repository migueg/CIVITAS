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
        return this.precioCompra + this.precioEdificar * this.cantidadCasasHoteles() * this.factorRevalorizacion;
    }
    
    private Boolean  propietarioEncarcelado(){
       if(this.propietario.encarcelado || ! this.propietario.equals(null)){
           return true;
       }else{
           return false;
       }
    }
    
    public Boolean getHipotecado(){
       return this.hipotecado;
    }
    
    void actualizaPropietarioPorConversion( Jugador jugador){
        this.propietario = jugador;
    }
    Boolean cancelarHipoteca( Jugador jugador) {
        throw new UnsupportedOperationException("No implementado");
    }

    int cantidadCasasHoteles(){
       return this.numCasas +this.numHoteles;
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
        if(this.propietario.equals(jugador) && this.numCasas >= n){
            this.numCasas -= n;
            return true;
        }else{
            return false;
        }
    }
    Boolean esEsteElPropietario(Jugador jugador) {
        if(this.propietario.equals(jugador)){
            return true;
        }else
            return false;
    }
   
    float getImporteCancelarHipoteca(){
        
        float cantidadRecibida= (float)(this.hipotecaBsase*(1+(numCasas*0.5)+(numHoteles*2.5)));

        return cantidadRecibida * factorInteresesHipoteca;
    }

    String getNombre(){
       return this.nombre;
    }
    int getNumCasas(){
       return this.numCasas;
    }
    int getNumHoteles(){
       return this.numHoteles;
    }
  
    float getPrecioCompra(){
       return this.precioCompra;
    }
    float getPrecioEdificar() {
       return this.precioEdificar;
    }
  
    Jugador getPropietario() {
       return this.propietario;
    }
    Boolean hipotecar( Jugador jugador ){
        throw new UnsupportedOperationException("No implementado");
    }
  
    Boolean tienePropietario(){
       return !this.propietario.equals(null);
    }


    void tramitarAlquiler(Jugador jugador ) {
        if(!this.propietario.equals(null) && !this.propietario.equals(jugador)){
            if(jugador.pagaAlquiler(this.getPrecioAlquiler())){
                this.propietario.recibe(this.getPrecioAlquiler());
            }
            
        }
    }

    Boolean vender(Jugador jugador){
       if(this.propietario.equals(jugador) && this.hipotecado == false ){
           this.propietario.recibe(this.getPrecioVenta());
           this.propietario.cancelarPropiedad(this);
           this.propietario = null;
           this.numCasas = 0;
           this.numHoteles = 0;
           return true;
       }else{
           return false;
       }

    }

}
