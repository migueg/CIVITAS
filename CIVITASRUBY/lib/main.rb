#encoding:utf-8

module Civitas
  require_relative './juego_texto/vista_textual'
  require_relative './juego_texto/controlador'
  require_relative 'dado'
  
  class Main
    def self.main
      vista = Vista_textual.new
      nombres = Array.new
      nombres << "J1"
      nombres << "J2"

      juego = Civitas_juego.new(nombres)
      
      controlador = Controlador.new(juego, vista)

      controlador.juega()
      
    end
  end
end

Civitas::Main.main