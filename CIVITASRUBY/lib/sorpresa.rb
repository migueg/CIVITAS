# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  require_relative 'tablero'
  require_relative 'jugador'
  require_relative 'mazo_sorpresas'


  class Sorpresa

    attr_reader :valor
    def initialize(tablero,valor,texto,mazo)
      @tablero = tablero
      @mazo = mazo
      @valor = valor
      @texto = texto
        
    end
    
   
    
    def aplicar_a_jugador(actual,todos)
      
    end
    
    def salir_del_mazo
      if self.instance_of?(Salir_carcel)
        if @mazo != nil
          @mazo.inhabilitar_carta_especial(self)
        end
      end
    end
    
    def usada
      if self.is_a?(Salir_carcel)
        if @mazo != nil
          @mazo.habilitar_carta_especial(self)
        end
      end  
    end
    
    def jugador_correcto(actual,todos)
      if actual >= 0 && actual <= todos.length()
        true
      else
        false
      end
    end
    
    def informe(actual,todos)
      if jugador_correcto(actual,todos)
        Diario.instance.ocurre_evento("Se esta aplicando la sorpresa " + "#{@tipo.to_s}" + " al jugador " + "#{todos[actual].nombre}")
      end
    end
    def to_s
      "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end
    
    def self.main
     todos = Array.new()
     todos << Jugador.new_jugador_nombre("PEPE")
     todos << Jugador.new_jugador_nombre("PACO")
     todos << Jugador.new_jugador_nombre("Miguel")
     tipo = Tipo_sorpresa::IRACARCEL
     tipo2 = Tipo_sorpresa::SALIRCARCEL
     puts tipo.to_s
    
      s1 = Sorpresa.new_sorpresa_tablero(tipo, Tablero.new(10))
     s2  = Sorpresa.new_sorpresa_tablero(tipo2, Tablero.new(10))
     
     puts todos[0].to_s
     s1.aplicar_a_jugador(0,todos)
     puts todos[0].to_s
     s2.aplicar_a_jugador(0,todos) 
     puts todos[0].to_s
    end
    
    
  
    
 
  end
end

  if $0 == __FILE__
  # De esta manera el main de esta clase se llamarÃ¡ cuando se ejecute este archivo explÃ­citamente
  # No se llamarÃ¡ cuando este archivo sea interpretado por ruby debido a que ha sido referenciado desde otro archivo con un require_relative
  Civitas::Sorpresa.main
  end