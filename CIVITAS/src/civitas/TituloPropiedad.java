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
        String result = "\nTituloPropiedad{" + "\nnombre=" + nombre + "\nalquilerBase=" + alquilerBase + ", \nfactorRevalorizacion=" + factorRevalorizacion + ", hipotecaBsase=" + 
                    hipotecaBsase + ", \nhipotecado=" + hipotecado + ", \numCasas=" + numCasas + ", \numHoteles=" + numHoteles 
                    + ", \nprecioCompra=" + precioCompra + ", \nprecioEdificar=" + precioEdificar ;
        
        
        
        result += " \n}\n";
        
        return result;
        
    }

    private float getImporteHipoteca() {
        return (float)(this.hipotecaBsase* (1+(this.numCasas*0.5)+(this.numHoteles*2.5)));
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
       if(this.propietario.encarcelado || this.propietario == null){
           return true;
       }else{
           return false;
       }
    }

    public float getAlquilerBase() {
        return alquilerBase;
    }

    public float getHipotecaBsase() {
        return hipotecaBsase;
    }
    
    public Boolean getHipotecado(){
       return this.hipotecado;
    }
    
    void actualizaPropietarioPorConversion( Jugador jugador){
        this.propietario = jugador;
    }
    Boolean cancelarHipoteca( Jugador jugador) {
       Boolean result = false;
       if(this.hipotecado){
           if(this.esEsteElPropietario(jugador)){
               this.propietario.paga(this.getImporteCancelarHipoteca());
               this.hipotecado = false;
               result = true;
                      
           }
       }
       
       return result;
    }

    int cantidadCasasHoteles(){
       return this.numCasas +this.numHoteles;
    }
    Boolean comprar( Jugador jugador){
        Boolean result = false;
        
        if(!this.tienePropietario()){
            this.propietario = jugador;
            result = true;
            this.propietario.paga(this.precioCompra);
        }
        return result;
    }
    Boolean construirCasa( Jugador jugador){
       Boolean result = false;
        
        if(this.esEsteElPropietario(jugador)){
           this.propietario.paga(this.precioEdificar);
           this.numCasas++;
           result = true;
        }
        
        return result;
    }
    Boolean construirHotel(Jugador jugador){
        Boolean result = false;
        
        if(this.esEsteElPropietario(jugador)){
           this.propietario.paga(this.precioEdificar);
           this.numHoteles++;
           result = true;
        }
        
        return result;
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
        if(this.propietario == jugador){
            return true;
        }else
            return false;
    }
   
    float getImporteCancelarHipoteca(){
        
        float cantidadRecibida= (float)(this.hipotecaBsase*(1+(numCasas*0.5)+(numHoteles*2.5)));

        return cantidadRecibida * factorInteresesHipoteca;
    }

    public String getNombre(){
       return this.nombre;
    }
    public int getNumCasas(){
       return this.numCasas;
    }
    public int getNumHoteles(){
       return this.numHoteles;
    }
  
    public float getPrecioCompra(){
       return this.precioCompra;
    }
    public float getPrecioEdificar() {
       return this.precioEdificar;
    }
  
    public Jugador getPropietario() {
       return this.propietario;
    }
    Boolean hipotecar( Jugador jugador ){
       Boolean salida = false;
       
       if( ! hipotecado && this.esEsteElPropietario(jugador)){
          
           this.propietario.recibe(this.getImporteHipoteca());
           this.hipotecado = true;
           salida = true;
       }
       
       return salida;
    }
  
    Boolean tienePropietario(){
       return this.propietario != null;
    }


    void tramitarAlquiler(Jugador jugador ) {
        if(this.tienePropietario() && !this.esEsteElPropietario(jugador)){
           float precio = this.getPrecioAlquiler();
           jugador.pagaAlquiler(precio);
           this.propietario.recibe(precio);
            
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
