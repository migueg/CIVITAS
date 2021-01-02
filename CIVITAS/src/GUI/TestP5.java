/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.ArrayList;
import civitas.CivitasJuego;

/**
 *
 * @author Usuario
 */
public class TestP5 {
   
    
    public static void main(String[] args) {
        CivitasView view = new CivitasView();
        Dado.createInstance(view);
        Dado.getInstance().setDebug(true);
        
        CapturaNombres capturador = new CapturaNombres(view,true);
        ArrayList<String> nombres = new ArrayList();
        
        nombres = capturador.getNombres();
        
        CivitasJuego civitas = new CivitasJuego(nombres);
        
        Controlador controlador = new Controlador(civitas,view);
        
        view.setCivitasJuego(civitas);
        controlador.juega();
    }
}
