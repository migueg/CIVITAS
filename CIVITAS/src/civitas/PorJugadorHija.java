/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class PorJugadorHija extends PorJugador {
    private int pos;
    private Jugador j;
    private Casilla descanso;
    
    PorJugadorHija(Tablero tablero , int valor , String texto){
        super(tablero,valor,texto);
        this.pos  = (int) Math.random()*6+ 30;
        
        if (this.pos < 1){
            this.pos = 1;
        }
        
        if(this.pos > 20 ){
            this.pos = 20;
        }
        
        this.j = new Jugador("EXAMEN");
        this.descanso = new Casilla("CASILLA EXAMEN");
    }

    public int getPos() {
        return pos;
    }
    
   @Override
    void aplicarAJugador(int actual  ,ArrayList<Jugador> todos) {
        super.aplicarAJugador(actual, todos);
        
        int pos = this.getPos();
        System.out.println("\nPOSICION: " + pos);
        System.out.println("\n\n------------Aplicando sorpresa del examen y tiro porque me toca\n");
        
        Jugador temp=  todos.get(actual);
        if(temp.getNumCasillaActual() < pos){
            temp.moverACasilla(this.pos);
        }
    }
    @Override
    public String toString() {
        return  "PorJugadorHija{" +
                "\ntexto=" + this.getTexto() + ", \nvalor=" + this.getValor() + 
                "\npos=" + pos + ", \njugador=" + j.toString() + ", \ndescanso=" + descanso.toString() + '}';
    }
    
    
    
}
