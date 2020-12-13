# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Jugador_especulador < Jugador
    @@Factor_especulador = 2
    def self.nuevo_especulador(jugador, fianza)
      
      jugador = super(jugador)
      jugador.set_fianza(fianza)
      
      return jugador
    
    end

   
    def set_fianza(fianza)
      @fianza = fianza
    end
    def get_casas_max()
      return @@Casas_max * @@Factor_especulador
    end
    
    def get_hoteles_max()
      return @@Hoteles_max  * @@Factor_especulador
    end

    
    def debe_ser_encarcelado()
      if(self.encarcelado)
        return false
      else
        if puedo_gastar(@fianza)
          modificar_saldo(-@fianza)
          Diario.instance.ocurre_evento("El jugador " + "#{self.nombre}" + " se libra de la carcel porque ha pagado la fianza" )
          return false
        else if self.tiene_salvoconducto == false
            return true
        else
            perder_salvoconducto()
            Diario.instance.ocurre_evento("El jugador " + "#{self.nombre}" + " se libra de la carcel porque tenia el salvoconducto" )
            return false
        end
        
        end
      end
    end
    
    
   def paga_impuesto(cantidad)
     if self.encarcelado == true
       false
     else
       return paga(cantidad/@@Factor_especulador)
       
       
     end
   end
   
   def to_s
     propiedades = ""
       if @propiedades.length != 0
        @propiedades.each do |prop|
        propiedades += prop.to_s
        
        end
       else
         propiedades += " Aun no tiene propiedades"
        
      end
      
     resultado = "Jugador: Encarcelado = #{self.encarcelado}\n  Nombre = #{self.nombre} ESPECULADOR\n Saldo= #{self.saldo}
      \n Salvoconduct0 = #{self.salvoconducto}\n CasillaActual = #{self.num_casilla_actual}\n"
       
      resultado += "\nPropiedades = "+ propiedades
      return resultado
   end
    
  end
end
