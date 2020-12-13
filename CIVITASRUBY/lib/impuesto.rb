# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Impuesto < Casilla
     def initialize(carcel,cantidad,nombre)
      super(nombre,carcel)
      @importe = cantidad
    end
    
    def recibe_jugador(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].paga_impuesto(@importe)
      end
    end
    
  
    
    def to_s
      result = "Casilla: \nnombre= #{@nombre} \n tipo= #{@tipo} "
      
      if @importe != -1
        result +="\n"
        result += "\nimporte = #{@importe}"
      end
      result +="\n"
      
      result
      
    end
  end
end

