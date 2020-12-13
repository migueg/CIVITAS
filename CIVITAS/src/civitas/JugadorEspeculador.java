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

import java.util.ArrayList;
        
public class JugadorEspeculador extends Jugador{
    
    protected static int FactorEspeculador = 2;
    private final int fianza;
    
    JugadorEspeculador(Jugador otro, int fianza){
        super(otro);
        actualizaPropietarioPorConversion();
        this.fianza = fianza;
    }
    
    
    
    private void actualizaPropietarioPorConversion(){
        ArrayList<TituloPropiedad> p = this.getPropiedades();
        
        for( TituloPropiedad t : p ){
            t.actualizaPropietarioPorConversion(this);
        }
    }
    
    @Override
    int getCasasMax(){return CasasMax * FactorEspeculador;}
    
    @Override 
    int getHotelesMax(){return HotelesMax * FactorEspeculador;}
    
    @Override
      protected Boolean debeSerEncarcelado(){
        if(this.encarcelado)
            return false;
        else{
            if(this.puedoGastar(fianza)){
                this.modificarSaldo(-fianza);
                Diario.getInstance().ocurreEvento("El jugador "+ this.getNombre() + " se libra de la carcel porque ha pagado la fianza");
                return false;
            }else if(!this.tieneSalvoconducto())
                return true;
            else{
                this.perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador "+ this.getNombre() + " se libra de la carcel con el salvoconducto");
                return false;
            }
        }
    }
    
    @Override
    Boolean pagaImpuesto(float cantidad){
        if(this.encarcelado)
            return false;
        else{
            return this.paga(cantidad / FactorEspeculador);
        }
    }
    
    @Override
    public String toString() {
        String result = "Jugador{" + " \nnombre=" + this.getNombre() + " ESPECULADOR" + "\nfianza="+ this.fianza + "\nencarcelado=" + encarcelado  + ", \nnumCasillaActual=" + this.getNumCasillaActual() + ", \npuedeComprar=" + this.getPuedeComprar() + ", \nsaldo=" + this.getSaldo() + ", ";
    
        if(this.getPropiedades() != null){
            for(TituloPropiedad t : this.getPropiedades()){
                result += "\n" + t.toString() + "\n";
            }
        }
        
        result += "\n}\n";
        
        return result;
    }
}

