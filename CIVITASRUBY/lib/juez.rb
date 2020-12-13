# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Juez < Casilla
    def initialize(carcel,nombre)
      super(nombre,carcel)
    end
    
    def recibe_jugador(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].encarcelar(@@carcel)
        
      end
    end
    
  end
end
