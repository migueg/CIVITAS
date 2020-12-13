# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Pagar_cobrar < Sorpresa
    def initialize(tablero,valor,texto)
      super(tablero,valor,texto,nil)
    end
    
    def aplicar_a_jugador (actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].modificar_saldo(@valor)
      end
    end
  end
end

