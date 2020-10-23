# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  require_relative 'jugador'
  require_relative 'gestor_estados'
  require_relative 'estados_juego'
  require_relative 'tablero'
  require_relative 'mazo_sorpresas'
  require_relative 'sorpresa'
  
  class Civitas_juego
    
    def intialize(nombres)
      @jugadores = Array.new()
      nombres.each do |nombre|
        @jugadores << Jugador.new_jugador_nombre(nombre)
      end
      
      @tablero = nil
      @gestor_estados = Gestor_estados.new()
      @estado = @gestor_estados.estado_inicial()
      @indice_jugador_actual = Dado.instance.quien_empieza(@jugdores.length())
      @mazo = Mazo_sorpresas.new()
      inicializar_tablero(@mazo)
      inicializar_mazo_sorpresas(@tablero)
    end
    
    def comprar
      
    end
    
    
    
    def siguiente_paso
      
    end
    
    def siguiente_paso_completado(operacion)
      @estado = @gestor_estados.siguiente_estado(@jugadores[@indice_jugador_actual], @estado, operacion)
    end
    
    def construir_casa(ip)
      return @jugadores[@indice_jugador_actual].construir_casa(ip)
    end
    
    def construir_hotel(ip)
      return @jugadores[@indice_jugador_actual].construir_hotel(ip)
    end
    def vender(ip)
      return @jugadores[@indice_jugador_actual].vender(ip)
    end
    
    def hipotecar(ip)
      return @jugadores[@indice_jugador_actual].hipotecar(ip)
    end
    
    def cancelar_hipoteca(ip)
      return @jugadores[@indice_jugador_actual].cancelar_hipoteca(ip)
    end
    
    def salir_carcel_pagando()
      return @jugadores[@indice_jugador_actual].salir_carcel_pagando()
    end
    
    def salir_carcel_tirando()
      return @jugadores[@indice_jugador_actual].salir_carcel_tirando()
    end
    
    
    def final_del_juego()
      fin = false
      @jugadores.each do |jugador|
        if jugador.en_banca_rota == true
          fin = true
        end
      end
      fin
    end
    
    def get_casilla_actual
      return @tablero.get_casilla(@jugadores[@indice_jugador_actual].casilla_actual)
    end
    
    def get_jugador_Actual
      return @jugadores[@indice_jugador_actual]
    end
    
    def info_jugador_texto()
      return @jugadores[@indice_jugador_actual].to_s
    end

    private 
    def inicializar_mazo_sorpresas(tablero)
      @mazo.al_mazo(Sorpresa.new_sorpresa_tablero(Tipo_sorpresa::IRACARCEL,tablero))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PAGARCOBRAR,tablero,500,"Cobrar"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PAGARCOBRAR,tablero,-500,"Pagar"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::IRCASILLA,tablero,5,"Ir a casilla"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::IRCASILLA,tablero,18,"Ir a casilla"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::IRCASILLA,tablero,10,"Ir a casilla"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORCASAHOTEL,tablero,200,"Recibe por casa hotel"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORCASAHOTEL,tablero,-200,"Paga por casa hotel"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORJUGADOR,tablero,100,"Recibe por jugador"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORCASAHOTEL,tablero,-100,"Paga por jugador"))
      @mazo.al_mazo(Sorpresa.new(Tipo_sorpresa::SALIRCARCEL,tablero))
    end
    
    def incializar_tablero(mazo)
      @tablero = new Tablero(10)
      @tablero.añade_casilla(Casilla.new_casilla_descanso("Salida"))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle Pedro Antonio Alarcón", 100,0.40.to_f,150,150,300)))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle Recogidas", 150,0.35.to_f,200,200,400)))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle Pavaneras", 200,0.25.to_f,250,250,500)))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle Mesones", 300,0.20.to_f,400,400,600)))
      @tablero.añade_casilla(Casilla.new_casilla_sorpresa(mazo, "Sorpresa1"))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Plaza de la Trinidad", 400,0.1.to_f,500,500,650)))
      @tablero.añade_casilla(Casilla.new_casilla_juez(10, "Juez"))
      @tablero.añade_casilla(Casilla.new_casilla_descanso("Descanso"))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Camino de Ronda", 500,1.to_f,600,600,750)))
      @tablero.añade_casilla(Casilla.new_casilla_sorpresa(mazo, "Sorpresa2"))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Plaza Mariana Pineda", 600,1.5.to_f,1000,1000,1500)))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle San Matias", 900,2.to_f,1500,1500,2000)))
      @tablero.añade_casilla(Casilla.new_casilla_sorpresa(mazo, "Sorpresa3"))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle Recogidas", 1200,2.5.to_f,2000,2000,2500)))
      @tablero.añade_casilla(Casilla.new_casilla_impuesto(500.to_f, "Impuesto"))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Paseo de Los Tristes", 1500,3.to_f,2500,2500,3000)))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Plaza Birrambla", 2500,3.to_f,4000,4000,5000)))
      @tablero.añade_casilla(Casilla.new_casilla_calle(Titulo_propiedad.new("Calle Angel Ganivet", 3000,4.to_f,5000,5000,6000)))  
    end
    
    def contabilizar_pasos_por_salida(jugador_actual)
      while @tablero.get_por_salida() > 0 do
        jugador_actual.pasa_por_salida()
      end
    end
    
    def pasar_turno()
      @indice_jugador_actual = (@indice_jugador_actual + 1) % @jugadores.length()
    end
    
    def ranking()
      @jugadores.sort()
      return @jugadores
    end
    
    
    def avanza_jugador
      
    end
  end
end
