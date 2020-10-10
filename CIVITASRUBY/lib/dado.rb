# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "singleton"
require_relative "diario"

module Civitas
  
  class Dado
    include Singleton
    attr_reader :ultimo_resultado
    @@SALIDA_CARCEL = 5
    def initialize
      @ultimo_resultado = -1
      @debug = false
    end
    
    def tirar
      if @debug == true
        @ultimo_resultado = 1
      else
        @ultimo_resultado = rand(6)+1
      end
      return @ultimo_resultado
    end
    
    def salgo_de_la_carcel
      d = tirar
      if(@@SALIDA_CARCEL == d)
        return true;
      else
        return false;
      end
    end
    
    def quien_empieza(n)
      return rand(n)
    end
    
    def set_debug (d)
      @debug = d
      Diario.instance.ocurre_evento("se ha puesto el modo debug a "+ d.to_s)
    end
  end
end