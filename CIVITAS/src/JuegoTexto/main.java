/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoTexto;
import civitas.CivitasJuego;
import java.util.ArrayList;
import civitas.Dado;
/**
 *
 * @author Usuario
 */
public class main {
    public static void main(String[] args) {
      
        VistaTextual vista = new VistaTextual();
        ArrayList<String> nombres = new ArrayList();
        nombres.add("J1");
        nombres.add("J2");
        nombres.add("J3");
        nombres.add("J4");
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        Controlador controlador = new Controlador(juego,vista);
       // Dado.getInstance().setDebug(Boolean.TRUE);
        controlador.juega();
        int i = 0;
    }
    
}
