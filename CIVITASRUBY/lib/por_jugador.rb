# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  require_relative 'casilla'
  class Por_jugador < Sorpresa
    
    def initialize(tablero,valor,texto)
      super(tablero ,valor, texto , nil)
    end
    
    def aplicar_a_jugador (actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        tipo = Tipo_sorpresa::PAGARCOBRAR
        nueva = Pagar_cobrar.new(tipo,@valor * -1,"")
        
        contador = 0;
        todos.each do |j|
          
          if ! todos[actual].eql?(j)
            contador 
            nueva.aplicar_a_jugador(contador % todos.length(),todos)
          end
          contador += 1
        end
        puts "Valor " + @valor.to_s 
        nueva2 = Pagar_cobrar.new(tipo,@valor * ( todos.length()-1) ,"")
        nueva2.aplicar_a_jugador(actual,todos)
      end
    end
    
  end
end

