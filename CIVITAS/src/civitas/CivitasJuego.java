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
   
    public void pruebaSorpresas(){
       
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("Turno de " + this.getJugadorActual().getNombre());
        this.avanzaJugador();
        this.comprar();
        this.construirCasa(0);
        this.construirCasa(0);
        this.construirCasa(0);
        this.construirCasa(0);
        this.construirHotel(0);
        for(int i = 0 ; i < 4; i++){
            this.avanzaJugador();
        }
        
        while(diario.eventosPendientes()){
            System.out.println(diario.leerEvento());
        }
            
    }
    public CivitasJuego (ArrayList<String> nombres){
        this.jugadores = new ArrayList();
        nombres.forEach((nombre) -> {
            
            jugadores.add(new Jugador(nombre));
        });
        
        this.gestorEstados = new GestorEstados();
        this.estado = this.gestorEstados.estadoInicial();
        this.indiceJugadorActual = Dado.getInstance().quienEmpieza(this.jugadores.size());
        this.mazo = new MazoSorpresas();
        this.inicializarTablero(this.mazo);
        this.inicializarMazoSorpresas(this.tablero);
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        // Dado.getInstance().setDebug(Boolean.TRUE);
        ArrayList<String> nombres  = new ArrayList() ;
        nombres.add("Vilma");
        nombres.add("Pedro");
        
        CivitasJuego juego = new CivitasJuego(nombres);
      // juego.pruebaSorpresas();
       Dado.getInstance().setDebug(Boolean.TRUE);
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       
       for (int i = 0 ; i < 10 ; i++){
           juego.avanzaJugador();
       System.out.print(juego.getJugadorActual().toString()); 
       }
       
    }
    private void inicializarMazoSorpresas(Tablero tablero){
        this.mazo = new MazoSorpresas();

        this.mazo.alMazo(new ConversionEspeculador(tablero));
        this.mazo.alMazo(new IrACarcel(tablero));
        this.mazo.alMazo(new PagarCobrar(tablero,500,"Cobrar"));
        this.mazo.alMazo(new PagarCobrar(tablero,-500,"Pagar"));
        this.mazo.alMazo(new IrACasilla(tablero,14,"Ir a casilla"));
        this.mazo.alMazo(new IrACasilla(tablero,18,"Ir a casilla"));
        this.mazo.alMazo(new IrACasilla(tablero,10,"Ir a casilla")); 
        this.mazo.alMazo(new PorCasaHotel(tablero,200,"Recibe po casa hotel"));
        this.mazo.alMazo(new PorCasaHotel(tablero,-200,"Paga por casa hotel"));
        this.mazo.alMazo(new PorJugador(tablero,100,"Recibe por jugador"));
        this.mazo.alMazo(new PorJugador(tablero,-100,"Paga por jugador"));
        this.mazo.alMazo(new SalirCarcel(tablero));
        this.tablero.updateMazo(mazo);
    }
    private void inicializarTablero(MazoSorpresas mazo){
        this.tablero = new Tablero(10);
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Calle Pedro Antonio Alarcón", 100,(float)0.40,150,150,300) ));
        this.tablero.añadeCasilla(new Calle (new TituloPropiedad("Calle Recogidas", 150,(float)0.35,200,200,400)));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Calle Pavaneras", 200,(float)0.25,250,250,500) ));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Calle Mesones", 300,(float)0.20,400,400,600) ));
        this.tablero.añadeCasilla(new CasillaSorpresa(mazo,"Sorpresa1"));
        this.tablero.añadeCasilla(new Calle (new TituloPropiedad("Plaza de la Trinidad", 400,(float)0.1,500,500,650)));
        this.tablero.añadeJuez();
        this.tablero.añadeCasilla(new Casilla("Parking"));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Camino de Ronda", 500,(float)1,600,600,750) ));
        this.tablero.añadeCasilla(new CasillaSorpresa(mazo,"Sorpresa2"));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Plaza Mariana Pineda", 600,(float)1.5,1000,1000,1500) ));
        this.tablero.añadeCasilla(new Calle (new TituloPropiedad("Calle San Matias", 900,(float)2,1500,1500,2000)));
        this.tablero.añadeCasilla(new CasillaSorpresa(mazo,"Sorpresa3"));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Calle Recogidas", 1200,(float)2.5,2000,2000,2500)));
        this.tablero.añadeCasilla(new Impuesto((float)500,"Impuesto"));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Paseo de Los Tristes", 1500,(float)3,2500,2500,3000) ));
        this.tablero.añadeCasilla(new Calle (new TituloPropiedad("Plaza Birrambla", 2500,(float)3,4000,4000,5000)));
        this.tablero.añadeCasilla(new Calle(new TituloPropiedad("Calle Angel Ganivet", 3000,(float)4,5000,5000,6000) ));
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
    
    public ArrayList<String>ranking_UI(){
        ArrayList<Jugador> jugadores = this.ranking();
        ArrayList<String> result = new ArrayList();
        int suma = 1;
        for(Jugador j : jugadores){
            String s = suma + " : " + j.getNombre();
            result.add(s);
            suma++;
        }
        
        return result;
    }
    
    private void avanzaJugador(){
        Jugador jugadorActual = this.jugadores.get(indiceJugadorActual);
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = Dado.getInstance().tirar();
        
        int posicionNueva = this.tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = this.tablero.getCasilla(posicionNueva);
        this.contabilizarPasosPorSalida(jugadorActual);
        
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(this.indiceJugadorActual, jugadores);
        this.contabilizarPasosPorSalida(jugadorActual);
        
    }
    
    public Boolean comprar(){
        Boolean res = false;
        
        Jugador jugadorActual = this.jugadores.get(indiceJugadorActual);
        int numCasillaActual = jugadorActual.getNumCasillaActual();
        Casilla casilla = this.tablero.getCasilla(numCasillaActual);
        TituloPropiedad titulo = casilla.getTituloPropiedad();
        res = jugadorActual.comprar(titulo);
        


        return res;
    }
    public OperacionesJuego siguientePaso(){
        Jugador jugadorActual = this.jugadores.get(indiceJugadorActual);
        OperacionesJuego operacion = this.gestorEstados.operacionesPermitidas(jugadorActual, estado);
        
        switch(operacion){
            case PASAR_TURNO:
                this.pasarTurno();
                this.siguientePasoCompletado(operacion);
                break;
            case AVANZAR:
                this.avanzaJugador();
                this.siguientePasoCompletado(operacion);
                break;
             
        }
        
        return operacion;
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
    
    public ArrayList<String> getPropiedades_UI(){
        ArrayList<String> result = new ArrayList();
        ArrayList<TituloPropiedad>  propiedades =  this.jugadores.get(indiceJugadorActual).getPropiedades();
        
        for(TituloPropiedad t : propiedades){
            result.add(t.getNombre());
        }
        
        return result;
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
