# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "casilla"
require_relative "juez"
module Civitas
  class Tablero
    def initialize(indice)
      if indice > 0
        @num_casilla_carcel = indice
      else

         @num_casilla_carcel = 1
      end
      
      @casillas = Array.new
      @casillas << Casilla.new('Salida',@num_casilla_carcel)
      @por_salida = 0
      @tiene_juez = false
      
      
    end
    
    def get_carcel
      return @num_casilla_carcel
    end
    
    def get_por_salida
      if @por_salida > 0
        aux = @por_salida
        @por_salida = -1
        return aux
      else
        return @por_salida
      end
    end
    
    def aniade_casilla (casilla)
      if @casillas.length == @num_casilla_carcel
        @casillas.push(Casilla.new(@num_casilla_carcel,"carcel"))
      end
      
      @casillas.push(casilla)
      
       if @casillas.length == @num_casilla_carcel
         @casillas.push(Casilla.new(@num_casilla_carcel,"carcel"))
      end
      
      
    end
    
    def añade_juez 
      if @tiene_juez == false
        @casillas.push(Juez.new(@num_casilla_carcel, "Juez"))
        @tiene_juez  = true
      end
    end
    
    
    
    def get_casilla (num_casilla)
      
      if correcto(num_casilla) == true
        return @casillas[num_casilla]
      else
        return nil
      end
    end
    
    def nueva_posicion (actual, tirada)
      if correcto() == false
        puts "FALSO"
        return -1
      else
        suma = actual + tirada
        num_casilla = suma % @casillas.length()
        if suma != num_casilla
          @por_salida += 1
        end
        
        return num_casilla
      end
    end
    
    def calcular_tirada (origen, destino)
      diferencia = destino - origen
      if diferencia < 0
        return diferencia + @casillas.length()
      else
        return diferencia
      end
    end
    
    private
    def correcto(*args)
      if args.length() == 0
        if (@casillas.length() > @num_casilla_carcel) and @tiene_juez
          return true
        else
          puts "AQUI "
          return false
        end
      else   

        if correcto() and (args[0] > 0) or  args[0] == 0
          return true
        else 
          puts "AQUI "
          return false
        end
      end
   
    end
    

    
    
  end
end