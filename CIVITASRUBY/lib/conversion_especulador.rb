# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  require_relative 'Jugador_especulador'
  class Conversion_especulador < Sorpresa
    def initialize(tablero)
      super(tablero,500,"conversion a especulador", nil)
    end
    
    def aplicar_a_jugador(actual,todos)
      if self.jugador_correcto(actual, todos)
        self.informe(actual, todos)
        especulador =  Jugador_especulador.nuevo_especulador(todos[actual], self.valor)
        Diario.instance.ocurre_evento("El jugador #{todos[actual].nombre} se ha convertido en especulador")

        todos[actual] = especulador

      end
    end
  end
end
