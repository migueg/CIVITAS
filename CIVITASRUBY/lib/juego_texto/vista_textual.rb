#encoding:utf-8
require_relative '../operaciones_juego'
require_relative '../respuestas'
require_relative '../salidas_carcel'
require_relative '../gestiones_inmobiliarias'
require_relative '../civitas_juego'
require 'io/console'

module Civitas

  class Vista_textual
    RESPUESTAS =[Respuestas::NO,Respuestas::SI]
    SALIDAS = [Salidas_carcel::PAGANDO,Salidas_carcel::TIRANDO]
    GESTIONES = [Gestiones_Inmobiliarias::VENDER,Gestiones_Inmobiliarias::HIPOTECAR,Gestiones_Inmobiliarias::CANCELAR_HIPOTECA,Gestiones_Inmobiliarias::CONSTRUIR_CASA,Gestiones_Inmobiliarias::CONSTRUIR_HOTEL,Gestiones_Inmobiliarias::TERMINAR]

    def initialize
      @juego_model = nil
      @i_gestion = -1
      @i_propiedad = -1
    end
    def mostrar_estado(estado)
      puts estado
    end

    
    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end



    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l
        index += 1
      }

      opcion = lee_entero(lista.length,
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo")
      return opcion
    end

    def salir_carcel
      opcion = menu("Elige la forma para intentar salir de la carcel",["Pagando","Tirando el dado"])
      return SALIDAS[opcion]
    end
    def comprar
      array = ["NO","SI"]
      opcion = menu("¿Deseas comprar la calle?", array)
      return RESPUESTAS[opcion]
    end

    def gestionar
      opcion = menu("Elige un tipo de gestión",["VENDER", "HIPOTECAR", "CANCELAR_HIPOTECA",
            "CONSTRUIR_CASA", "CONSTRUIR_HOTEL", "TERMINAR"])
      @i_gestion = opcion
      if GESTIONES[opcion] != Gestiones_Inmobiliarias::TERMINAR
          opcion = menu("Elige una propiedad a gestionar: ", @juego_model.get_propiedades_UI());
          @i_propiedad = opcion
      end
      
    end

    def get_gestion
      @i_gestion
    end

    def get_propiedad
      @i_propiedad
    end

    def mostrar_siguiente_operacion(operacion)
      puts "La siguiente opeacion que se va a realizar es " + operacion.to_s
    end

    def mostrar_eventos
      diario = Diario.instance
      while diario.eventos_pendientes == true
        puts diario.leer_evento + "\n"
      end
    end

    def set_civitas_juego(civitas)
         @juego_model=civitas
         self.actualizar_vista
    end

    def actualizar_vista
      puts "\nJugador Actual : \n" + @juego_model.get_jugador_Actual().to_s + "\nCasilla Actual: \n" + @juego_model.get_casilla_actual().to_s
    end

    
  end

end
