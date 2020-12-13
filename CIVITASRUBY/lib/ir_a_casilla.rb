# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Ir_a_casilla < Sorpresa
    def initialize(tablero,valor,texto)
      super(tablero,valor,texto,nil)
    end
    
    def aplicar_a_jugador(actual, todos)
      if jugador_correcto(actual, todos) == true && @tablero != nil
        informe(actual,todos)
        casilla_actual = todos[actual].num_casilla_actual
        tirada = @tablero.calcular_tirada(casilla_actual, @valor)
        nueva = @tablero.nueva_posicion(casilla_actual, tirada)

        if todos[actual].mover_a_casilla(nueva) == true
          @tablero.get_casilla(nueva).recibe_jugador(actual, todos)
        end
      end
    end
    
  end
end

