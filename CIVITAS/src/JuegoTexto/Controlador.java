/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoTexto;

import civitas.CivitasJuego;
import civitas.OperacionesJuego;
import java.util.ArrayList;
import civitas.Jugador;
import civitas.Respuestas;
import civitas.OperacionInmobiliaria;
import civitas.GestionesInmobiliarias;
import civitas.SalidasCarcel;
/**
 *
 * @author Usuario
 */

public class Controlador {
    private VistaTextual vista;
    private CivitasJuego civitas;

    Controlador( CivitasJuego juego , VistaTextual vista) {
        this.vista = vista;
        this.civitas = juego;
    }
    
    void juega(){
        this.vista.setCivitasJuego(civitas);
        
        while(!this.civitas.finalDelJuego()){
            this.vista.pausa();
            OperacionesJuego op = this.civitas.siguientePaso();
            
            
            this.vista.mostrarSiguienteOperacion(op);
            
            if(!op.equals(OperacionesJuego.PASAR_TURNO)){
                this.vista.mostrarEventos();
            }
            
            if(this.civitas.finalDelJuego()){
                ArrayList<String> jugadores = this.civitas.ranking_UI();
                this.vista.mostrarEstado("Ranking: \n");
                for(String j : jugadores){
                    this.vista.mostrarEstado(j);
                }
             
            }else{
                switch(op){
                    case COMPRAR:
                        Respuestas r = this.vista.comprar();
                        if(r.equals(Respuestas.SI))
                            this.civitas.comprar();
                        
                        this.civitas.siguientePasoCompletado(op);
                    
                        
                        break;
                        
                    case GESTIONAR:
                      
                        this.vista.gestionar();
                        int igestion = this.vista.getGestion();
                        int ipropiedad = this.vista.getPropiedad();
                        OperacionInmobiliaria operacion = new OperacionInmobiliaria(GestionesInmobiliarias.values()[igestion],ipropiedad);
                        switch(operacion.getGestion()){
                            case VENDER:
                                this.civitas.vender(ipropiedad);
                                break;
                            case HIPOTECAR:
                                this.civitas.hipotecar(ipropiedad);
                                break;
                            case CANCELAR_HIPOTECA:
                                this.civitas.cancelarHipoteca(ipropiedad);
                                break;
                            case CONSTRUIR_CASA:
                                this.civitas.construirCasa(ipropiedad);
                                break;
                            case CONSTRUIR_HOTEL:
                                this.civitas.construirHotel(ipropiedad);
                                break;
                            case TERMINAR:
                                this.civitas.siguientePasoCompletado(op);
                                break;
                        }
                       
                        break;
                    case SALIR_CARCEL:
                        if(this.vista.salirCarcel().equals(SalidasCarcel.PAGANDO)){
                            this.civitas.salirCarcelPagando();
                        }else{
                            this.civitas.salirCarcelTirando();
                        }
                        this.civitas.siguientePasoCompletado(op);
                        break;
                        
                }
            }
        }
    }
    
    
    
}
