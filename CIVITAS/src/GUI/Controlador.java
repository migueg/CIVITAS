/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.CivitasJuego;
import civitas.OperacionesJuego;
import java.util.ArrayList;
import civitas.Jugador;
import GUI.Respuestas;
import civitas.OperacionInmobiliaria;
import civitas.GestionesInmobiliarias;
import civitas.SalidasCarcel;
/**
 *
 * @author Usuario
 */

public class Controlador {
    private CivitasView vista;
    private CivitasJuego civitas;

    Controlador( CivitasJuego juego , CivitasView vista) {
        this.vista = vista;
        this.civitas = juego;
    }
    
    void juega(){
        this.vista.setCivitasJuego(civitas);
        
        while(!this.civitas.finalDelJuego()){
            if (this.vista.getContinuar()){

                OperacionesJuego op = this.civitas.siguientePaso();


                this.vista.mostrarSiguienteOperacion(op);

                if(!op.equals(OperacionesJuego.PASAR_TURNO)){
                    this.vista.mostrarEventos();
                }

                if(this.civitas.finalDelJuego()){


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
                this.vista.setContinuar(false);
            }
        }
    }
    
    
}
