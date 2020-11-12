# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Civitas
  require_relative 'titulo_propiedad'
  require_relative 'jugador'
  require_relative 'tipo_casilla'
  require_relative 'Sorpresa'
  class Casilla
    
   
    attr_reader :nombre, :titulo_propiedad
    def initialize (nombre,titulo,num_casilla_carcel,mazo,cantidad,tipo)
      @nombre = nombre
      @titulo_propiedad = titulo
      @@carcel = num_casilla_carcel
      @mazo = mazo
      @importe = cantidad
      @tipo = tipo
      @sorpresa = nil
      
    end
    
    def self.new_casilla_descanso(carcel,nombre)
      new(nombre,nil,carcel,nil,-1,Tipo_casilla::DESCANSO)
    end
    def self.new_casilla_calle(carcel,titulo)
      new("",titulo,carcel,nil,-1,Tipo_casilla::CALLE)
    end
    
    def self.new_casilla_juez(carcel,nombre)
      new(nombre,nil,carcel,nil,-1,Tipo_casilla::JUEZ)
    end
    
    def self.new_casilla_impuesto(carcel,cantidad,nombre)
      new(nombre,nil,carcel,nil,cantidad,Tipo_casilla::IMPUESTO)
    end
    
    def self.new_casilla_sorpresa(carcel,mazo,nombre)
      new(nombre,nil,carcel,mazo,-1,Tipo_casilla::SORPRESA)
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

      case @tipo
      when Tipo_casilla::CALLE
        recibe_jugador_calle(actual,todos)
      when Tipo_casilla::IMPUESTO
        recibe_jugador_impuesto(actual,todos)
      when Tipo_casilla::JUEZ
        recibe_jugador_juez(actual,todos)
      when Tipo_casilla::SORPRESA
        recibe_jugador_sorpresa(actual,todos)
      else
        informe(actual,todos)
      end
    end
    
    def to_s
      result = "Casilla: \nnombre= #{@nombre} \n tipo= #{@tipo} "
      
      if @importe != -1
        result +="\n"
        result += "\nimporte = #{@importe}"
      end
      
      if  @sorpresa != nil
        result +="\n"
        result += @sorpresa.to_s
      end
      
      if @titulo_propiedad != nil
        result +="\n"
        result += @titulo_propiedad.to_s
      end
      result +="\n"
      
      result
    end
    private
    def recibe_jugador_calle(actual,todos)
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
    
    def recibe_jugador_impuesto(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].paga_impuesto(@importe)
      end
    end
    
    def recibe_jugador_juez(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].encarcelar(@@carcel)
        
      end
    end
    
    
    def recibe_jugador_sorpresa(actual,todos)
      if jugador_correcto(actual,todos) == true
        puts "SIGUIENTE" 
        @sorpresa = @mazo.siguiente()
        informe(actual,todos)
        @sorpresa.aplicar_a_jugador(actual, todos)
      end
      
    end
    
    def informe(actual,todos)
      Diario.instance.ocurre_evento("El jugador " + "#{todos[actual].nombre}" + "ha caido en la casilla " + "#{@nombre}" )
      Diario.instance.ocurre_evento("Info de la casilla " + self.to_s())
    end
  end
end