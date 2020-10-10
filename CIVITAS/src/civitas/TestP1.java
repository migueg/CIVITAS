/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author Usuario
 */
public class TestP1 {
    static  Dado d = Dado.getInstance();
       public static void main(String[] args) {
        // TODO code application logic here
        /*
        
        //Test quienEmpieza()
        for(int i = 0; i < 100;i++){
            int v = d.quienEmpieza(4);
            
        }
        
        */
        
        /*
        
        //Test modo debug
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
        d.setDebug(Boolean.TRUE);
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       d.setDebug(Boolean.FALSE);
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       
        */
        
       /*
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(d.tirar()); 
       System.out.println(  d.getUltimoResultado()); 
       System.out.println(d.salgoDeLaCarcel());
       */
       /*
       MazoSorpresas m = new MazoSorpresas();
       Sorpresa s1 = new Sorpresa("s1");
       Sorpresa s2 = new Sorpresa("s2");
       m.alMazo(s1);
       m.alMazo(s2);
       
       m.inhabilitarCarteEspecial(s2);
       System.out.println(Diario.getInstance().leerEvento());
       m.habilitarCataEspecial(s2);
       
       System.out.println(Diario.getInstance().leerEvento());
       */
       
       Tablero t = new Tablero(3);
       t.añadeCasilla(new Casilla("f"));
       t.añadeCasilla(new Casilla("f2"));
       t.añadeCasilla(new Casilla("f3"));
       t.añadeCasilla(new Casilla("f5"));
    
    }
    
}
