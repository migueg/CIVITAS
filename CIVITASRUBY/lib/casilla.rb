# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Civitas
  
  require_relative 'jugador'

  class Casilla
    
   
    attr_reader :nombre, :titulo_propiedad, :carcel
    def initialize (nombre,num_casilla_carcel)
      @nombre = nombre
      @@carcel = num_casilla_carcel
     
    end
    
  

    
    
    def jugador_correcto(actual,todos)
      if actual >= 0 && actual <= todos.length()
        true
      else
        false
      end
    end
    
    def recibe_jugador(actual,todos)
        informe(actual,todos)
    end
    
    def to_s
      result = "Casilla: \nnombre= #{@nombre} \n  "
      result +="\n"
      
      result
    end
       
    def informe(actual,todos)
      Diario.instance.ocurre_evento("El jugador " + "#{todos[actual].nombre}" + "ha caido en la casilla " + "#{@nombre}" )
      Diario.instance.ocurre_evento("Info de la casilla " + self.to_s())
    end
 
  end
end