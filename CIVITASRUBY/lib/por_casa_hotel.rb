# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Por_casa_hotel < Sorpresa
    def initialize(tablero,valor,texto)
      super(tablero,valor,texto,nil)
    end
    
    def aplicar_a_jugador(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].modificar_saldo(@valor.to_f * todos[actual].cantidad_casas_hoteles)
      end
    end
    
  end
end

