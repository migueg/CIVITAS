# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'sorpresa'
  class Salir_carcel < Sorpresa
    
    def initialize(tablero)
      super(tablero ,-1, "salir carcel" , nil)
    end
    
      def aplicar_a_jugador(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        la_tienen = false
        
        todos.each do |j|
          if j.tiene_salvoconducto()
            la_tienen = true
          end
        end
        

        
        if la_tienen == false
          todos[actual].obtener_salvoconducto(self)
          salir_del_mazo()
        end
      end
    end
    
  end
end
