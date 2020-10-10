#encoding: utf-8
#  To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "../lib/dado.rb"
require_relative "mazo_sorpresas"
require_relative "sorpresa"
require_relative "diario"
require_relative "tablero"
require_relative "casilla"

module Civitas
 
  class Test
 
  def self.main
     d = Dado.instance 
    
    
=begin  
   
    for i in(0..100)
      v = d.quien_empieza(4)
      puts v
    end
=end

=begin
  puts d.tirar()
  puts d.tirar()
  puts d.tirar()
  puts d.tirar()
  d.set_debug(true)
  puts d.tirar()
  puts d.tirar()
  puts d.tirar()
  d.set_debug(false)
  puts d.tirar()
  puts d.tirar()
  puts d.tirar()
  puts d.tirar()
=end   
    
=begin
  puts d.tirar()
  puts d.tirar()
  puts d.tirar()
  puts d.ultimo_resultado
  puts d.salgo_de_la_carcel()
=end 
    
    
=begin
    m = Mazo_sorpresas.new()
    puts m.inspect
  s1 = Sorpresa.new("s1")
  puts s1.inspect
  s2 = Sorpresa.new("s2")
  m.al_mazo(s1)
  m.al_mazo(s2)
  
  m.inhabilitar_carta_especial(s2)
  puts Diario.instance.leer_evento()
  m.habilitar_carta_especial(s2)
  puts Diario.instance.leer_evento()
  puts m.inspect
  
=end
    
    t = Tablero.new(3)
    t.añade_casilla(Casilla.new("f1"))
    t.añade_casilla(Casilla.new("f2"))
    t.añade_casilla(Casilla.new("f3"))
    t.añade_casilla(Casilla.new("f4"))
    
    puts t.inspect
  end

  end
  
Test.main
end
