# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "diario"
require_relative "sorpresa"

module Civitas
  class Mazo_sorpresas
    def initialize (d=false)
    
      init()
      @ultima_sorpresa = nil 
      @debug = d
      if(d)
        Diario.instance.ocurre_evento("se ha puesto el  modo debug del mazo a "+ d.to_s)
      end
      
    end
    
    def al_mazo(s)
      if (@barajada == false)
        @sorpresas.push(s)
      end
    end
    
    def siguiente
      if @barajada == false or @usadas == @sorpresas.length() 
        if @debug == false
          @usadas = 0
          @barajada = true

          @sorpresas = @sorpresas.shuffle
       
        end
      end
      
     @usadas += 1
     temp = @sorpresas[0]
     @sorpresas.delete(temp)
     @sorpresas.push(temp)
     
     @ultima_sorpresa = temp
     return @ultima_sorpresa
     
    end
    
    def inhabilitar_carta_especial (sorpresa)
     
      if @sorpresas.include? (sorpresa)
        @sorpresas.delete(sorpresa)
        @cartas_especiales.push(sorpresa)
            Diario.instance.ocurre_evento("Se ha cambiado una carta especial al mazo de especiales")
      end
    end
    
    def habilitar_carta_especial (sorpresa)
      if @cartas_especiales.include? (sorpresa)
        @cartas_especiales.delete(sorpresa)
        @sorpresas.push(sorpresa)
            Diario.instance.ocurre_evento("Se ha cambiado una carta especial al mazo de sorpresas")
      end
    end
    
    
    
    private 
    def init
 
      @sorpresas = Array.new
      @cartas_especiales = Array.new
      @barajada = false
      @usadas = 0
    end
  end
end