# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Ir_a_carcel < Sorpresa
    def initialize(tablero,valor,texto)
      super(tablero,valor,texto,nil)
    end
    
    def aplicar_a_jugador(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        if @tablero != nil
          todos[actual].encarcelar(@tablero.get_carcel())
        end
      end
    end
  end
end
