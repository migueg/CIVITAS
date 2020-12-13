# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  require_relative 'titulo_propiedad'
  class Calle < Casilla
    attr_reader :titulo_propiedad
    def initialize(carcel,titulo)
      super(titulo.nombre,carcel)
      @titulo_propiedad = titulo
    end
    
     def recibe_jugador(actual,todos)
      if jugador_correcto(actual,todos) == true
        informe(actual,todos)
        jugador = todos[actual]
       
        if @titulo_propiedad.tiene_propietario() == false
          return jugador.puede_comprar_casilla()
        else
          return @titulo_propiedad.tramitar_alquiler(jugador)
        end
      end
    end
    
     def to_s
      result = "Casilla: \nnombre= #{@nombre} \n tipo= #{@tipo} "
      if @titulo_propiedad != nil
        result +="\n"
        result += @titulo_propiedad.to_s
      end

      result +="\n"
      
      result
     end
  end
end
