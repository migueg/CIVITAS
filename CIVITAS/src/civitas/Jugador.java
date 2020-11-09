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
public class Jugador implements Comparable <Jugador> {
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
    private Sorpresa salvoconducto;
    
 
    Jugador(String nombre){
        this.nombre = nombre;
        this.encarcelado = false;
        this.numCasillaActual = 0;
        this.puedeComprar = false;
        this.saldo = SaldoInicial;
        this.propiedades = new ArrayList();
        this.salvoconducto = null;
    }
    
    protected Jugador(Jugador otro){
        this.nombre = otro.getNombre();
        this.encarcelado = otro.encarcelado;
        this.numCasillaActual = otro.getNumCasillaActual();
        this.propiedades = otro.getPropiedades();
        this.puedeComprar = otro.getPuedeComprar();
        this.saldo = otro.getSaldo();
        this.salvoconducto = otro.getSalvoconducto();
        
        
    }
    
    public int compareTo(Jugador otro){
       
       return Float.compare(this.saldo,  otro.getSaldo());
    }
    
    protected Boolean debeSerEncarcelado(){
        if(this.encarcelado)
            return false;
        else{
            if(!this.tieneSalvoconducto())
                return true;
            else{
                this.perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador "+ this.nombre + " se libra de la carcel");
                return false;
            }
        }
    }
    
    private void perderSalvoconducto(){
        this.salvoconducto.usada();
        this.salvoconducto = null;
    }
    
    private Boolean puedoGastar(float precio){
        if(this.encarcelado){
            return false;
        }else{
            return this.saldo >= precio;
        }
    }
    
    private  Boolean existeLaPropiedad(int ip){
        return this.propiedades.get(ip ) != null;
    }
    
    private Boolean puedeSalirCarcelPagando(){
       return this.saldo >= PrecioLibertad; 
    }
    
    Boolean paga(float cantidad){
       return this.modificarSaldo(cantidad * -1);
    }
    Boolean pagaAlquiler(float cantidad){
        if(this.encarcelado)
            return false;
        else{
            return this.paga(cantidad);
        }

    }
    
    void cancelarPropiedad (TituloPropiedad p){
        this.propiedades.remove(p);
    }
    Boolean recibe(float cantidad){
        if(this.encarcelado)
            return false;
        else{
            return this.modificarSaldo(cantidad);
        }

    }
    
    
    
    protected String getNombre(){
        return this.nombre;
    }
    
    Boolean encarcelar(int numCasillaCarcel){
        if(this.debeSerEncarcelado()){
      
            this.moverACasilla(numCasillaCarcel);
            this.encarcelado = true;
            Diario.getInstance().ocurreEvento("El jugador "+ this.nombre + " ha sido encarcelado");
        }
        
        return this.encarcelado;
    }
        
    Boolean moverACasilla (int numCasilla){
        if(this.encarcelado)
            return false;
        else{
            this.numCasillaActual = numCasilla;
            this.puedeComprar = false;
            Diario.getInstance().ocurreEvento("El jugador "+ this.nombre + " ha movido a la casilla "+ numCasilla);
            return true;
        }
    }
    int getNumCasillaActual(){
        return this.numCasillaActual;
    }
    
   
    
    Boolean modificarSaldo (float cantidad){
        this.saldo += cantidad;
        Diario.getInstance().ocurreEvento("Se ha modificado el saldo en " + cantidad + " unidades al jugador "+ this.nombre);
        return true;
    }
    int cantidadCasasHoteles(){
        int suma = 0;
        for(TituloPropiedad t : this.propiedades){
            suma += t.cantidadCasasHoteles();
        }
        
        return suma;
    }
    
    Boolean tieneSalvoconducto (){
        if(this.salvoconducto == null)
            return false;
        else 
            return true;
    }
    
    Boolean obtenerSalvoconducto(Sorpresa sorpresa){
        if(this.encarcelado){
            return false;
        }else{
            this.salvoconducto = sorpresa;
            return true;
        }
    }
    
    Boolean puedeComprarCasilla(){
        if(this.encarcelado){
            this.puedeComprar = false;
        }else{
            this.puedeComprar = true;
        }
        
        return this.puedeComprar;
    }
    Boolean pagaImpuesto(float cantidad){
        if(this.encarcelado)
            return false;
        else{
            return this.paga(cantidad);
        }
    }

    Boolean tieneAlgoQueGestionar(){
        return this.propiedades.size()>0;
    }
    
    Boolean salirCarcelPagando (){
        if(this.encarcelado && this.puedeSalirCarcelPagando()){
            this.paga(PrecioLibertad);
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador " +this.nombre + " sale de la cárcel porque ha pagado");
            return true;
        }else
            return false;
    }
    Boolean salirCarcelTirando (){
        Boolean salgo = Dado.getInstance().salgoDeLaCarcel();
        if(salgo){
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador " +this.nombre + " sale de la cárcel porque le ha salido en el dado");
        }
            
        return salgo;
      
    }
    Boolean pasaPorSalida(){
        this.modificarSaldo(PasoPorSalida);
        Diario.getInstance().ocurreEvento("El jugador "+ this.nombre + " ha pasado por la casilla de salida");
        return true;
    }
    Boolean cancelarHipoteca(int ip){
       Boolean result = false;
       if(this.encarcelado)
           return false;
       
       if(this.existeLaPropiedad(ip)){
           TituloPropiedad propiedad = this.propiedades.get(ip);
           float cantidad = propiedad.getImporteCancelarHipoteca();
           if(this.puedoGastar(cantidad)){
               result = propiedad.cancelarHipoteca(this);
               if(result)
                   Diario.getInstance().ocurreEvento("El jugador " + this.nombre + " cancela la hipoteca de la propiedad " + ip);
               
           }
           
           
       }
       return result;
              
            
    }
    Boolean comprar(TituloPropiedad titulo){
      Boolean result  = false;
      if(this.encarcelado)
          return result;
      
      if(this.puedeComprar){
          float precio = titulo.getPrecioCompra();
          if(this.puedoGastar(precio)){
              result = titulo.comprar(this);
              
              if(result){
                  this.propiedades.add(titulo);
                  Diario.getInstance().ocurreEvento("El jugador"+ this.nombre + " compra la propiedad "+titulo.toString());
              }
              
              this.puedeComprar = false;
          }
      }
          
      return result;
    }
    
    Boolean construirCasa(int ip){
         Boolean result = false;
         Boolean puedoEdificarCasa = false;
         
         if(this.encarcelado)
             return result;
         else{
             if(this.existeLaPropiedad(ip)){
                 TituloPropiedad propiedad = this.propiedades.get(ip);
                 puedoEdificarCasa = this.puedoEdificarCasa(propiedad);
                 if(puedoEdificarCasa)
                     result = propiedad.construirCasa(this);
             }
         }
         
         
         return result;
    }
    Boolean construirHotel(int ip){
         Boolean  result = false;
         if(this.encarcelado)
             return result;
         
         if(this.existeLaPropiedad(ip)){
             TituloPropiedad propiedad = this.propiedades.get(ip);
             Boolean puedoEdificarHotel = this.puedoEdificarHotel(propiedad);
             if(puedoEdificarHotel){
                 result = propiedad.construirHotel(this);
                 int casasPorHotel = this.getCasasPorHotel();
                 propiedad.derruirCasas(casasPorHotel, this);
                 Diario.getInstance().ocurreEvento("El jugador "+ nombre+ " construye hotel en la propiedad "+ip);
             }
         }
         
         return result;
    }
    
    Boolean enBancarrota(){
        return this.saldo <= 0;
    }

  
    
    Boolean hipotecar(int ip){
        Boolean  result = false;
         if(this.encarcelado)
             return result;
         
         if(this.existeLaPropiedad(ip)){
             TituloPropiedad propiedad = this.propiedades.get(ip);
             result = propiedad.hipotecar(this);
             
         }
         
         if(result)
             Diario.getInstance().ocurreEvento("El jugador "+nombre+ " hipoteca la propiedad "+ip);
         
         return result;
    }
    
    private Boolean puedoEdificarCasa(TituloPropiedad titulo){
        if(this.puedoGastar(titulo.getPrecioEdificar()) && titulo.getNumCasas()< this.getCasasMax())
            return true;
        else
            return false;
    }
    
    private Boolean puedoEdificarHotel(TituloPropiedad titulo){
        Boolean puedoEdificarHotel = false;
        
        float precio = titulo.getPrecioEdificar();
        
        if(this.puedoGastar(precio) && titulo.getNumHoteles()< this.getHotelesMax() 
                && titulo.getNumCasas()>= this.getCasasPorHotel() ){
            puedoEdificarHotel = true;
        }
        
        return puedoEdificarHotel;
    }
 
 
    Boolean vender(int ip){
       if(this.encarcelado)
           return false;
       else{
           if(this.existeLaPropiedad(ip)){
              
              if(this.propiedades.get(ip).vender(this)){
                  
                  Diario.getInstance().ocurreEvento("Se ha eliminado la propiedad "+ ip + " del jugador "+ this.nombre );
                  return true;
              }else
                  return false;
                
           }else
               return false;
       }
    }
    
    

    Boolean getPuedeComprar() {
        return puedeComprar;
    }

    protected float getSaldo() {
        return saldo;
    }

   protected ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }
    
    int getCasasMax(){
        return CasasMax;
    }
    
    int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    int getHotelesMax(){
        return HotelesMax;
    }
    
    Sorpresa getSalvoconducto(){
        return this.salvoconducto;
    }
    
    float getPremioPasoSalida() {
        return PasoPorSalida;
    }

   float getPrecioLibertad() {
        return PrecioLibertad;
    }
   public Boolean isEncarcelado (){
       return this.encarcelado;
   }
   
      @Override
    public String toString() {
        String result = "Jugador{" + " \nnombre=" + nombre  + "\nencarcelado=" + encarcelado  + ", \nnumCasillaActual=" + numCasillaActual + ", \npuedeComprar=" + puedeComprar + ", \nsaldo=" + saldo + ", ";
    
        if(this.propiedades != null){
            for(TituloPropiedad t : this.propiedades){
                result += "\n" + t.toString() + "\n";
            }
        }
        
        result += "\n}\n";
        
        return result;
    }
    
}
