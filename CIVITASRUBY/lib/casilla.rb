# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Civitas
  require_relative 'titulo_propiedad'
  require_relative 'jugador'
  class Casilla
    
   
    attr_reader :nombre, :titulo_propiedad
    def initialize (nombre,titulo,num_casilla_carcel,mazo,cantidad,tipo)
      @nombre = nombre
      @titulo_propiedad = titulo
      @@carcel = num_casilla_carcel
      @mazo = mazo
      @importe = cantidad
      @tipo = tipo
      
    end
    
    def self.new_casilla_descanso(nombre)
      new(nombre,nil,-1,nil,-1,Tipo_casilla::DESCANSO)
    end
    def self.new_casilla_calle(titulo)
      new("",titulo,-1,nil,-1,Tipo_casilla::CALLE)
    end
    
    def self.new_casilla_juez(num__casilla_carcel,nombre)
      new(nombre,nil,num__casilla_carcel,nil,-1,Tipo_casilla::JUEZ)
    end
    
    def self.new_casilla_impuesto(cantidad,nombre)
      new(nombre,nil,-1,nil,cantidad,Tipo_casilla::IMPUESTO)
    end
    
    def self.new_casilla_sorpresa(mazo,nombre)
      new(nombre,nil,-1,mazo,-1,Tipo_CASILLA::SORPRESA)
    end
    private_class_method :new
    
    
    def jugador_correcto(actual,todos)
      if actual >= 0 && actual <= todos.length()
        true
      else
        false
      end
    end
    
    def recibe_jugador(actual,todos)
      
    end
    
    def to_s
      "Casilla:\n nombre= #{@nombre}\n titulo= #{@titulo_propiedad.to_s}\n importe= #{@importe}\n tipo= #{@tipo}\n"
    end
    private
    def recibe_jugador_calle(actual,todos)
      
    end
    
    def recibe_jugador_impuesto(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].pagar_impuesto(@importe)
      end
    end
    
    def recibe_jugador_juez(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].encarcelar(@@carcel)
      end
    end
    
    
    def recibe_jugador_sorpresa(actual,todos)
      
    end
    
    def informe(actual,todos)
      Diario.instance.ocurre_evento("El jugador " + "#{todos[actual].nombre}" + "ha caido en la casilla " + "#{@nombre}" )
      Diario.include.ocurre_evento("Info de la casilla " + self.to_s())
    end
  end
end