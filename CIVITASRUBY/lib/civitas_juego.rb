# encoding: utf-8
module Civitas
  require_relative 'jugador'
  require_relative 'gestor_estados'
  require_relative 'estados_juego'
  require_relative 'tablero'
  require_relative 'mazo_sorpresas'
  require_relative 'sorpresa'
  require_relative 'tipo_sorpresa'
  require_relative 'dado'
  
  class Civitas_juego
    attr_accessor :jugadores, :indice_jugador_actual
#    
#   METODOS PUBLICOS
#   ------------------------------------------------
#    
    def initialize(nombres)
      @jugadores = Array.new()
      nombres.each do |nombre|
        @jugadores << Jugador.new_jugador_nombre(nombre)
      end
      
      @tablero = nil
      @gestor_estados = Gestor_estados.new()
      @estado = @gestor_estados.estado_inicial()
      @indice_jugador_actual = Dado.instance.quien_empieza(@jugadores.length())
      @mazo = Mazo_sorpresas.new(false)
      inicializar_tablero(@mazo)
      inicializar_mazo_sorpresas(@tablero)
    end
    
  
    def comprar
      res = false
      
      jugador_actual = @jugadores[@indice_jugador_actual]
      num_casilla_actual = jugador_actual.num_casilla_actual
      casilla = @tablero.get_casilla(num_casilla_actual)
      titulo = casilla.titulo_propiedad
      res = jugador_actual.comprar(titulo)
      
      return res
    end
    
    
    
    def siguiente_paso
      jugador_actual = @jugadores[@indice_jugador_actual]
      operacion = @gestor_estados.operaciones_permitidas(jugador_actual, @estado)
      
      case operacion
      when Operaciones_juego::PASAR_TURNO
        pasar_turno()
        siguiente_paso_completado(operacion)
      when Operaciones_juego::AVANZAR
        avanza_jugador()
        siguiente_paso_completado(operacion)
      end
      
      return operacion
      
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
      return @tablero.get_casilla(@jugadores[@indice_jugador_actual].num_casilla_actual)
    end
    
    def get_jugador_Actual
      return @jugadores[@indice_jugador_actual]
    end
    
    def info_jugador_texto()
      return @jugadores[@indice_jugador_actual].to_s
    end
    
     def ranking_UI
      jugadores = ranking()
      result = Array.new 
      suma = 1
      
      jugadores.each do |j|
        s = suma.to_s + " : " + j.nombre
        result << s
        suma += 1
      end
      
      result
      
    end
    
     def get_propiedades_UI
       result = Array.new
       propiedades = @jugadores[@indice_jugador_actual].propiedades
       
       propiedades.each do |t|
         result << t.nombre
       end
       
       result
     end
    
#    METODOS PARA PRUEBAS
#    -------------------------------------------------------------------------
#   
    def self.prueba_paso_salida
      
    end
    
    def self.prueba_sorpresas
       Dado.instance.set_debug(true)

      nombres = ["Vilma", "Pedro"]
      

      juego = Civitas_juego.new(nombres)
      diario = Diario.instance
      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")
      juego.avanza_jugador
      juego.comprar
      juego.construir_casa(0)
      juego.construir_casa(0)
      juego.construir_casa(0)
      juego.construir_casa(0)
      juego.construir_hotel(0)
      4.times {|i|
        juego.avanza_jugador
      }
  
     20.times {|i|
       juego.avanza_jugador
     }
      while diario.eventos_pendientes do
        puts diario.leer_evento
      end
      
      puts juego.jugadores[0].to_s
      puts juego.jugadores[1].to_s

    end
    def self.vender_propiedad
      Dado.instance.set_debug(true)

      nombres = ["Vilma", "Pedro"]
      

      juego = Civitas_juego.new(nombres)
      diario = Diario.instance
      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")
      juego.avanza_jugador
      
      juego.comprar
      juego.vender(0)
      
      juego.pasar_turno

      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")
      juego.avanza_jugador
      
      while diario.eventos_pendientes do
        puts diario.leer_evento
      end
      
      puts juego.jugadores[0].to_s
    

    end
    def self.prueba_hipotecar
      Dado.instance.set_debug(true)

      nombres = ["Vilma", "Pedro"]
      

      juego = Civitas_juego.new(nombres)
      diario = Diario.instance
      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")
      juego.avanza_jugador

      juego.comprar
      juego.hipotecar(0)
      juego.cancelar_hipoteca(0)
      juego.construir_casa(0)
      juego.construir_hotel(0)
      juego.pasar_turno

      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")
      juego.avanza_jugador
      
      while diario.eventos_pendientes do
        puts diario.leer_evento
      end
      
      puts juego.jugadores[0].to_s
      puts juego.jugadores[1].to_s
    end
    
    def self.prueba_hotel_alquiler
      Dado.instance.set_debug(true)

      nombres = ["Vilma", "Pedro"]
      

      juego = Civitas_juego.new(nombres)
      diario = Diario.instance
      

      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")


     
    
      juego.avanza_jugador

      puts juego.jugadores[juego.indice_jugador_actual].inspect
      juego.comprar
      5.times {|i|
        juego.construir_casa(0) 
      }
      juego.construir_hotel(0)
      juego.construir_hotel(0)
     
  
      puts juego.jugadores[juego.indice_jugador_actual].inspect
      puts juego.jugadores[juego.indice_jugador_actual].to_s
      
  
      juego.pasar_turno
      

      diario.ocurre_evento ("Turno de #{juego.jugadores[juego.indice_jugador_actual].nombre}")
      juego.avanza_jugador
      puts juego.jugadores[juego.indice_jugador_actual].to_s
      juego.pasar_turno
      

      puts juego.jugadores[juego.indice_jugador_actual].to_s
            
      while diario.eventos_pendientes do
        puts diario.leer_evento
      end
      puts juego.jugadores[0].to_s
      puts juego.jugadores[1].to_s
    end 



    def self.main
      nombres = Array.new()
      nombres << "J1"
      nombres << "J2"
      nombres << "J3"
      nombres << "J4"
      civitas = Civitas_juego.new(nombres)
      puts civitas.inspect
    end
    

    
#    METODOS PRIVADOS
#    -----------------------------------------------------------------------------------------
#    
    private 
    def inicializar_mazo_sorpresas(tablero)
     

        
      @mazo.al_mazo(Sorpresa.new_sorpresa_tablero(Tipo_sorpresa::IRACARCEL,tablero))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PAGARCOBRAR,tablero,500,"Cobrar"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PAGARCOBRAR,tablero,-500,"Pagar"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::IRACASILLA,tablero,14,"Ir a casilla"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::IRACASILLA,tablero,18,"Ir a casilla"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::IRACASILLA,tablero,10,"Ir a casilla"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORCASAHOTEL,tablero,200,"Recibe por casa hotel"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORCASAHOTEL,tablero,-200,"Paga por casa hotel"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORJUGADOR,tablero,100,"Recibe por jugador"))
      @mazo.al_mazo(Sorpresa.new_sorpresa(Tipo_sorpresa::PORJUGADOR,tablero,-100,"Paga por jugador"))
      @mazo.al_mazo(Sorpresa.new_sorpresa_tablero(Tipo_sorpresa::SALIRCARCEL, tablero))
    end
    
    def inicializar_tablero(mazo)
      carcel = 10
      @tablero = Tablero.new(carcel)
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle Pedro Antonio Alarcon", 100,0.40.to_f,150,150,300)))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle Recogidas", 150,0.35.to_f,200,200,400)))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle Pavaneras", 200,0.25.to_f,250,250,500)))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle Mesones", 300,0.20.to_f,400,400,600)))
      @tablero.aniade_casilla(Casilla.new_casilla_sorpresa(carcel,mazo, "Sorpresa1"))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Plaza de la Trinidad", 400,0.1.to_f,500,500,650)))
      @tablero.añade_juez()
      @tablero.aniade_casilla(Casilla.new_casilla_descanso(carcel,"Descanso"))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Camino de Ronda", 500,1.to_f,600,600,750)))
      @tablero.aniade_casilla(Casilla.new_casilla_sorpresa(carcel,mazo, "Sorpresa2"))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Plaza Mariana Pineda", 600,1.5.to_f,1000,1000,1500)))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle San Matias", 900,2.to_f,1500,1500,2000)))
      @tablero.aniade_casilla(Casilla.new_casilla_sorpresa(carcel,mazo, "Sorpresa3"))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle Recogidas", 1200,2.5.to_f,2000,2000,2500)))
      @tablero.aniade_casilla(Casilla.new_casilla_impuesto(carcel,500.to_f, "Impuesto"))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Paseo de Los Tristes", 1500,3.to_f,2500,2500,3000)))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Plaza Birrambla", 2500,3.to_f,4000,4000,5000)))
      @tablero.aniade_casilla(Casilla.new_casilla_calle(carcel,Titulo_propiedad.new("Calle Angel Ganivet", 3000,4.to_f,5000,5000,6000)))  
    end
      
      def avanza_jugador
        jugador_actual = @jugadores[@indice_jugador_actual]
        posicion_actual = jugador_actual.num_casilla_actual

        tirada = Dado.instance.tirar()
        posicion_nueva = @tablero.nueva_posicion(posicion_actual, tirada)

       
        casilla = @tablero.get_casilla(posicion_nueva)
        contabilizar_pasos_por_salida(jugador_actual)

        jugador_actual.mover_a_casilla(posicion_nueva)
        res = casilla.recibe_jugador(@indice_jugador_actual, @jugadores)


        contabilizar_pasos_por_salida(jugador_actual)

      
      end

    def pasar_turno()
      @indice_jugador_actual = (@indice_jugador_actual + 1) % @jugadores.length()
    end
    
    
    def contabilizar_pasos_por_salida(jugador_actual)
      while @tablero.get_por_salida() > 0 do
        jugador_actual.paso_por_salida()
      end
    end
    
  
    def ranking()
      @jugadores.sort()
      return @jugadores
    end
    
   
    
  
  end
end

if $0 == __FILE__
  # De esta manera el main de esta clase se llamarÃ¡ cuando se ejecute este archivo explÃ­citamente
  # No se llamarÃ¡ cuando este archivo sea interpretado por ruby debido a que ha sido referenciado desde otro archivo con un require_relative
  Civitas::Civitas_juego.prueba_hipotecar
  end