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
public class Sorpresa {
    private String texto ;
    private int valor;
    private MazoSorpresas mazo ;
    private Tablero tablero;
    private TipoSorpresa tipo;
    
    Sorpresa(TipoSorpresa tipo , Tablero tablero){
        this.init();
        this.tipo = tipo;
        this.tablero = tablero;
        this.texto = "";
       
    }
    
    Sorpresa(TipoSorpresa tipo , Tablero tablero, int valor, String texto){
        this.init();
        this.tipo = tipo;
        this.tablero = tablero;
        this.valor = valor;
        this.texto = texto;
        
    }
    
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        this.init();
        this.tipo = tipo;
        this.valor = valor;
        this.texto = texto;
        
    }
    
    Sorpresa(TipoSorpresa tipo , MazoSorpresas mazo){
        this.init();
        this.tipo = tipo;
        this.texto = "";
        this.mazo = mazo;
    }
    

private void aplicarAJugador_irACasilla(int actual  ,ArrayList<Jugador> todos) {
    if(this.jugadorCorrecto(actual, todos) && this.tablero != null){
        this.informe(actual, todos);
        int casillaActual = todos.get(actual).getNumCasillaActual();
        int tirada = this.tablero.calcularTirada(casillaActual, this.valor);
        int nueva = this.tablero.nuevaPosicion(casillaActual, tirada);
        if(todos.get(actual).moverACasilla(nueva))
           this.tablero.getCasilla(nueva).recibeJugador(actual, todos);
    }
    
}
private void aplicarAJugador_irCarcel(int actual  ,ArrayList<Jugador> todos) {
    if(this.jugadorCorrecto(actual, todos)){
        this.informe(actual, todos);
        if(this.tablero != null)
            todos.get(actual).encarcelar(this.tablero.getCarcel());
    }
}
private void aplicarAJugador_pagarCobrar(int actual  ,ArrayList<Jugador> todos) {
    if(this.jugadorCorrecto(actual, todos)){
        this.informe(actual, todos);
        Jugador j =  todos.get(actual);
        j.modificarSaldo(this.valor);
    }
    
    
}
private void aplicarAJugador_porCasaHotel(int actual  ,ArrayList<Jugador> todos) {
       if(this.jugadorCorrecto(actual, todos)){
        this.informe(actual, todos);
        Jugador j =  todos.get(actual);
        j.modificarSaldo(this.valor * j.cantidadCasasHoteles());
    }
}
private void aplicarAJugador_porJugador(int actual  ,ArrayList<Jugador> todos) {
    if(this.jugadorCorrecto(actual, todos)){
        this.informe(actual, todos);
        TipoSorpresa tipo = TipoSorpresa.PAGARCOBRAR;
        Sorpresa nueva = new Sorpresa(tipo,this.valor * -1,"");
       
        for(Jugador j : todos){
            if( ! todos.get(actual).equals(j))
                nueva.aplicarAJugador(actual, todos);
            
        }
        
        Sorpresa nueva2 = new Sorpresa(tipo,this.valor * todos.size()-1 , "");
        nueva2.aplicarAJugador(actual, todos);
    }   
    
}
private void aplicarAJugador_salirCarcel(int actual  ,ArrayList<Jugador> todos){
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
private void informe(int actual  ,ArrayList<Jugador> todos) {
    if(this.jugadorCorrecto(actual, todos)){
         Diario.getInstance().ocurreEvento("Se esta aplicando las sorpersa " + this.tipo.toString() + " a" + todos.get(actual).getNombre());
    }
   
    
}
private void init() {
    this.valor = -1;
    this.mazo = null;
    this.tablero = null;
}

void aplicarAJugador(int actual  ,ArrayList<Jugador> todos){
    switch(this.tipo){
        case IRCARCEL:
            this.aplicarAJugador_irCarcel(actual, todos);
            break;
        case IRCASILLA:
            this.aplicarAJugador_irACasilla(actual, todos);
            break;
        case PAGARCOBRAR:
            this.aplicarAJugador_pagarCobrar(actual, todos);
            break;
        case PORCASAHOTEL:
            this.aplicarAJugador_porCasaHotel(actual, todos);
            break;
        case PORJUGADOR:
            this.aplicarAJugador_porJugador(actual, todos);
            break;
        case SALIRCARCEL:
            this.aplicarAJugador_salirCarcel(actual, todos);
            break;
        default:
            break;
    }
 }
void salirDelMazo() {
    if(this.tipo.equals(TipoSorpresa.SALIRCARCEL)){
        if(this.mazo != null)
            this.mazo.inhabilitarCartaEspecial(this);
    }
}

void usada() {
    if(this.tipo.equals(TipoSorpresa.SALIRCARCEL)){
        if(this.mazo != null)
            this.mazo.habilitarCataEspecial(this);
    }
}


public Boolean jugadorCorrecto(int actual  ,ArrayList<Jugador> todos) {
    if(actual >= 0 && actual <= todos.size()){
        return true;
    }else
        return false;
}

    @Override
    public String toString() {
        return "Sorpresa{" + "texto=" + texto + ", valor=" + valor + ", mazo=" + mazo + ", tablero=" + tablero + ", tipo=" + tipo + '}';
    }


}
