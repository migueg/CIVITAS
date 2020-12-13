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
public abstract class Sorpresa  {
    private String texto ;
    private int valor;
    private MazoSorpresas mazo ;
    private Tablero tablero;

    
 
    Sorpresa( Tablero tablero,int valor, String texto, MazoSorpresas mazo){
        

        this.tablero = tablero;
        this.texto = texto;
        this.mazo = mazo;
        this.valor = valor;
       
    }

    public String getTexto() {
        return texto;
    }

    public int getValor() {
        return valor;
    }

    public MazoSorpresas getMazo() {
        return mazo;
    }

    public Tablero getTablero() {
        return tablero;
    }
    

protected void informe(int actual  ,ArrayList<Jugador> todos) {
    if(this.jugadorCorrecto(actual, todos)){
         Diario.getInstance().ocurreEvento("Se esta aplicando la sorpersa " + this.texto + " a" + todos.get(actual).getNombre());
    }
   
    
}

void aplicarAJugador(int actual  ,ArrayList<Jugador> todos){}

void salirDelMazo() {
    if(this  instanceof SalirCarcel){
        if(this.mazo != null)
            this.mazo.inhabilitarCartaEspecial(this);
    }
}

void usada() {
    if(this  instanceof SalirCarcel){
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
        return "\nSorpresa{" + "texto=" + texto + ", valor=" + valor +  "}\n";
    }


}
