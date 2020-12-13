# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  require_relative 'Sorpresa'
  class Casilla_sorpresa < Casilla
    
    def initialize(carcel,mazo, nombre)
      @mazo = mazo
      @sorpresa = nil
      super(nombre,carcel)
    end
    
     def recibe_jugador(actual,todos)
        if jugador_correcto(actual,todos) == true

          @sorpresa = @mazo.siguiente()
          informe(actual,todos)
          @sorpresa.aplicar_a_jugador(actual, todos)
      end
     end
     
     def to_s
      result = "Casilla: \nnombre= #{@nombre} \n tipo= #{@tipo} "
       if  @sorpresa != nil
         result +="\n"
         result += @sorpresa.to_s
       end
      result +="\n"
      
      result
     end
  end
end

