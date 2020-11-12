#encoding:utf-8

module Civitas
  require_relative 'vista_textual'
  require_relative '../civitas_juego'
  require_relative '../operaciones_juego'
  require_relative '../operacion_inmobiliaria'
  require_relative '../salidas_carcel'
  class Controlador
    GESTIONES = [Gestiones_Inmobiliarias::VENDER,Gestiones_Inmobiliarias::HIPOTECAR,Gestiones_Inmobiliarias::CANCELAR_HIPOTECA,Gestiones_Inmobiliarias::CONSTRUIR_CASA,Gestiones_Inmobiliarias::CONSTRUIR_HOTEL,Gestiones_Inmobiliarias::TERMINAR]

    def initialize(juego, vista)
      @vista = vista
      @civitas = juego
    end
    
    def juega
      @vista.set_civitas_juego(@civitas)
      
      while @civitas.final_del_juego == false
        @vista.pausa
        op = @civitas.siguiente_paso
        @vista.mostrar_siguiente_operacion(op)
        
        if op != Operaciones_juego::PASAR_TURNO
          @vista.mostrar_eventos
        end
        
        if @civitas.final_del_juego == true
          jugadores = @civitas.ranking_UI
          
          @vista.mostrar_estado("Ranking \n")
          jugadores.each do |j|
            @vista.mostrar_estado(j)
          end
          
        else
          
          case op
          when Operaciones_juego::COMPRAR
            r = @vista.comprar
            if r == Respuestas::SI
              @civitas.comprar
              
            end
            @civitas.siguiente_paso_completado(op)
          when Operaciones_juego::GESTIONAR
            @vista.gestionar
            i_gestion = @vista.get_gestion
            i_propiedad = @vista.get_propiedad
            operacion = Operacion_Inmobiliaria.new(GESTIONES[i_gestion],i_propiedad)
            case operacion.gestion
            when GESTIONES[0]
              @civitas.vender(i_propiedad)
              
            when GESTIONES[1]
              @civitas.hipotecar(i_propiedad)
            when GESTIONES[2]
              @civitas.cancelar_hipoteca(i_propiedad)
            when GESTIONES[3]
              @civitas.construir_casa(i_propiedad)
            when GESTIONES[4]
              @civitas.construir_hotel(i_propiedad)
            when GESTIONES[5]
              @civitas.siguiente_paso_completado(op)
            end
           
          when Operaciones_juego::SALIR_CARCEL
            if @vista.salir_carcel == Salidas_carcel::PAGANDO
              @civitas.salir_carcel_pagando
            else
              @civitas.salir_carcel_tirando
            end
             @civitas.siguiente_paso_completado(op)
          end
        end
        
      end
    end
  end
end

