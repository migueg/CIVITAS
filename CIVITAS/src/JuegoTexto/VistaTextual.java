package JuegoTexto;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.GestionesInmobiliarias;
import civitas.Jugador;
import civitas.TituloPropiedad;
import GUI.Respuestas;

class VistaTextual {
  
  CivitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar() {
    int opcion = menu ("¿Deseas comprar la calle?",
        new ArrayList<> (Arrays.asList("SI","NO")));    

    return (Respuestas.values()[opcion]);
  }

  void gestionar () {
    int opcion = menu ("Elige un tipo de gestión",
        new ArrayList<> (Arrays.asList("VENDER", "HIPOTECAR", "CANCELAR_HIPOTECA",
            "CONSTRUIR_CASA", "CONSTRUIR_HOTEL", "TERMINAR" ))); 
    this.iGestion = opcion;
    
    
    if(GestionesInmobiliarias.values()[opcion] != GestionesInmobiliarias.TERMINAR)
     opcion = menu("Elige una propiedad a gestionar: ", this.juegoModel.getPropiedades_UI());
    
    this.iPropiedad = opcion;
             
  

  }
  
  public int getGestion(){return this.iGestion;}
  
  public int getPropiedad(){return this.iPropiedad;}
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
      System.out.println("La siguiente opeacion que se va a realizar es " + operacion) ;
  }


  void mostrarEventos() {
     while(Diario.getInstance().eventosPendientes()){
         System.out.println(Diario.getInstance().leerEvento() + "\n");
     }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
        this.actualizarVista();

    }
  
  void actualizarVista(){
      System.out.println("\nJugador Actual : \n" + this.juegoModel.getJugadorActual().toString() 
              + "\nCasilla Actual: \n" +this.juegoModel.getCasillaActual().toString());
     
  } 
}
