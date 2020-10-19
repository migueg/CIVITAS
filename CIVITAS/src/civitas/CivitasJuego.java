/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Usuario
 */
public class CivitasJuego {
    private ArrayList<Jugador> jugadores;
    private int indiceJugadorActual;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    private Tablero tablero;
    private MazoSorpresas mazo;
    
    CivitasJuego (ArrayList<String> nombres){
        this.jugadores = new ArrayList();
        nombres.forEach((nombre) -> {
            System.out.println(nombre);
            jugadores.add(new Jugador(nombre));
        });
        
        this.gestorEstados = new GestorEstados();
        this.estado = this.gestorEstados.estadoInicial();
        this.indiceJugadorActual = Dado.getInstance().quienEmpieza(this.jugadores.size());
        this.mazo = new MazoSorpresas();
        this.inicializarTablero(this.mazo);
        this.inicializarMazoSorpresas(this.tablero);
        
    }
    
    private void inicializarMazoSorpresas(Tablero tablero){
        this.mazo = new MazoSorpresas();
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCARCEL,tablero));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,tablero,500,"Cobrar"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,tablero,-500,"Pagar"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA,tablero,5,"Ir a casilla"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA,tablero,18,"Ir a casilla"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA,tablero,10,"Ir a casilla")); 
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,tablero,200,"Recibe po casa hotel"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,tablero,-200,"Paga por casa hotel"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR,tablero,100,"Recibe por jugador"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR,tablero,-100,"Paga por jugador"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.SALIRCARCEL,tablero));
    }
    private void inicializarTablero(MazoSorpresas mazo){
        this.tablero = new Tablero(10);
        this.tablero.añadeCasilla(new Casilla("Salida" ));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Pedro Antonio Alarcón", 100,(float)0.40,150,150,300) ));
        this.tablero.añadeCasilla(new Casilla (new TituloPropiedad("Calle Recogidas", 150,(float)0.35,200,200,400)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Pavaneras", 200,(float)0.25,250,250,500) ));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Mesones", 300,(float)0.20,400,400,600) ));
        this.tablero.añadeCasilla(new Casilla(mazo,"Sorpresa1"));
        this.tablero.añadeCasilla(new Casilla (new TituloPropiedad("Plaza de la Trinidad", 400,(float)0.1,500,500,650)));
        this.tablero.añadeCasilla(new Casilla(10,"Juez"));
        this.tablero.añadeCasilla(new Casilla("Descanso"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Camino de Ronda", 500,(float)1,600,600,750) ));
        this.tablero.añadeCasilla(new Casilla(mazo,"Sorpresa2"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Plaza Mariana Pineda", 600,(float)1.5,1000,1000,1500) ));
        this.tablero.añadeCasilla(new Casilla (new TituloPropiedad("Calle San Matias", 900,(float)2,1500,1500,2000)));
        this.tablero.añadeCasilla(new Casilla(mazo,"Sorpresa3"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Recogidas", 1200,(float)2.5,2000,2000,2500)));
        this.tablero.añadeCasilla(new Casilla(500,"Impuesto"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Paseo de Los Tristes", 1500,(float)3,2500,2500,3000) ));
        this.tablero.añadeCasilla(new Casilla (new TituloPropiedad("Plaza Birrambla", 2500,(float)3,4000,4000,5000)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Angel Ganivet", 3000,(float)4,5000,5000,6000) ));
    }
    
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
       while(this.tablero.getPorSalida() >0){
           jugadorActual.pasaPorSalida();
       } 
    }
    private void pasarTurno(){
        this.indiceJugadorActual = (this.indiceJugadorActual + 1 ) % this.jugadores.size();
       
    }
    
    private ArrayList<Jugador> ranking(){
        Collections.sort(jugadores); //Ordena la lista con el comparable
        return jugadores;
    }
    
    private void avanzaJugador(){
        
    }
    
    public Boolean comprar(){
        return false;
    }
    public OperacionesJuego siguientePaso(){
        return null;
    }
   public void siguientePasoCompletado(OperacionesJuego operacion){
       this.estado = this.gestorEstados.siguienteEstado(this.jugadores.get(indiceJugadorActual), estado, operacion);
   }
   
   public Boolean construirCasa(int ip){
        return  this.jugadores.get(this.indiceJugadorActual).construirCasa(ip);
   }
   
    public Boolean construirHotel(int ip){
        return  this.jugadores.get(this.indiceJugadorActual).construirHotel(ip);
   }
    
    public Boolean vender(int ip){
        return this.jugadores.get(this.indiceJugadorActual).vender(ip);
    }
    
    public Boolean hipotecar(int ip){
        return this.jugadores.get(this.indiceJugadorActual).hipotecar(ip);
    }
    public Boolean cancelarHipoteca(int ip){
        return this.jugadores.get(this.indiceJugadorActual).cancelarHipoteca(ip);
    }
    public Boolean salirCarcelPagando(){
         return this.jugadores.get(this.indiceJugadorActual).salirCarcelPagando();
    }
    public Boolean salirCarcelTirando(){
         return this.jugadores.get(this.indiceJugadorActual).salirCarcelTirando();
    }
    
    public Boolean finalDelJuego(){
        for(Jugador j : this.jugadores){
            if(j.enBancarrota())
                return true;
        }
        return false;
    }
    
    public Casilla getCasillaActual(){
        return this.tablero.getCasilla(this.jugadores.get(indiceJugadorActual).getNumCasillaActual());
    }
    
    public Jugador getJugadorActual(){
        return this.jugadores.get(indiceJugadorActual);
    }
    
    public String infoJugadorTexto(){
        return this.jugadores.get(indiceJugadorActual).toString();
    }
    /*
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<String> nombres = new ArrayList();
        nombres.add("J1");
        nombres.add("J2");
        nombres.add("J3");
        nombres.add("J4");
        
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
      
        
       
        System.out.println(juego.indiceJugadorActual);
        
        juego.pasarTurno();
           System.out.println(juego.indiceJugadorActual);
            System.out.println(juego.finalDelJuego());
       System.out.print("GG");
    }
*/
    
}
