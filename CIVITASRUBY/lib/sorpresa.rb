# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  require_relative 'tablero'
  require_relative 'jugador'
  require_relative 'mazo_sorpresas'
  require_relative 'tipo_sorpresa'

  class Sorpresa

    def initialize(tipo,tablero,mazo,valor,texto)
      @tipo = tipo
      @tablero = tablero
      @mazo = mazo
      @valor = valor
      @texto = texto
        
    end
    
    def self.new_sorpresa_tablero(tipo, tablero)
      new(tipo,tablero,nil,-1,"")
     
    end
    
    def self.new_sorpresa_mazo(tipo, mazo)
      new(tipo,nil,mazo,-1,"")
     
    end
    
    def self.new_sorpresa(tipo, tablero,valor,texto)
      new(tipo,tablero,nil,valor,texto)
     
    end
    def self.new_sorpresa_sin_tablero(tipo, valor,texto)
      new(tipo,nil,nil,valor,texto)
     
    end
   
    private_class_method :new
    
    def aplicar_a_jugador(actual,todos)
      case @tipo
      when Tipo_sorpresa::IRACARCEL
        aplicar_a_jugador_ir_carcel(actual,todos)
      when Tipo_sorpresa::IRACASILLA
        aplicar_a_jugador_ir_a_casilla(actual,todos)
      when Tipo_sorpresa::PAGARCOBRAR
        aplicar_a_jugador_pagar_cobrar(actual,todos)
      when Tipo_sorpresa::PORCASAHOTEL
        aplicar_a_jugador_por_casa_hotel(actual,todos)
      when Tipo_sorpresa::PORJUGADOR
        aplicar_a_jugador_por_jugador(actual,todos)
      when Tipo_sorpresa::SALIRCARCEL
        aplicar_a_jugador_salir_carcel(actual,todos)
      else
        
      end
    end
    
    def salir_del_mazo
      if @tipo == Tipo_sorpresa::SALIRCARCEL
        if @mazo != nil
          @mazo.inhabilitar_carta_especial(self)
        end
      end
    end
    
    def usada
     if @tipo == Tipo_sorpresa::SALIRCARCEL
        if @mazo != nil
          @mazo.habilitar_carta_especial(self)
        end
      end  
    end
    
    def jugador_correcto(actual,todos)
      if actual >= 0 && actual <= todos.length()
        true
      else
        false
      end
    end
    
    def to_s
      "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end
    
    def self.main
     todos = Array.new()
     todos << Jugador.new_jugador_nombre("PEPE")
     todos << Jugador.new_jugador_nombre("PACO")
     todos << Jugador.new_jugador_nombre("Miguel")
     tipo = Tipo_sorpresa::IRACARCEL
     tipo2 = Tipo_sorpresa::SALIRCARCEL
     puts tipo.to_s
    
      s1 = Sorpresa.new_sorpresa_tablero(tipo, Tablero.new(10))
     s2  = Sorpresa.new_sorpresa_tablero(tipo2, Tablero.new(10))
     
     puts todos[0].to_s
     s1.aplicar_a_jugador(0,todos)
     puts todos[0].to_s
     s2.aplicar_a_jugador(0,todos) 
     puts todos[0].to_s
    end
    private 
    def aplicar_a_jugador_ir_a_casilla(actual, todos)
      if jugador_correcto(actual, todos) == true && @tablero != nil
        informe(actual,todos)
        casilla_actual = todos[actual].num_casilla_actual
        tirada = @tablero.calcular_tirada(casilla_actual, @valor)
        nueva = @tablero.nueva_posicion(casilla_actual, tirada)

        if todos[actual].mover_a_casilla(nueva) == true
          @tablero.get_casilla(nueva).recibe_jugador(actual, todos)
        end
      end
    end
    def aplicar_a_jugador_ir_carcel (actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        if @tablero != nil
          todos[actual].encarcelar(@tablero.get_carcel())
        end
      end
    end
    
    def aplicar_a_jugador_pagar_cobrar (actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].modificar_saldo(@valor)
      end
    end
    
    def aplicar_a_jugador_por_casa_hotel (actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].modificar_saldo(@valor.to_f * todos[actual].cantidad_casas_hoteles)
      end
    end
    
    def aplicar_a_jugador_por_jugador (actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        tipo = Tipo_sorpresa::PAGARCOBRAR
        nueva = Sorpresa.new_sorpresa_sin_tablero(tipo,@valor * -1,"")
        
        contador = 0;
        todos.each do |j|
          
          if ! todos[actual].eql?(j)
            contador 
            nueva.aplicar_a_jugador(contador % todos.length(),todos)
          end
          contador += 1
        end
        puts "Valor " + @valor.to_s 
        nueva2 = Sorpresa.new_sorpresa_sin_tablero(tipo,@valor * ( todos.length()-1) ,"")
        nueva2.aplicar_a_jugador(actual,todos)
      end
    end
    
    
    def aplicar_a_jugador_salir_carcel(actual,todos)
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        la_tienen = false
        
        todos.each do |j|
          if j.tiene_salvoconducto()
            la_tienen = true
          end
        end
        

        
        if la_tienen == false
          todos[actual].obtener_salvoconducto(self)
          salir_del_mazo()
        end
      end
    end
    
    def informe(actual,todos)
      if jugador_correcto(actual,todos)
        Diario.instance.ocurre_evento("Se esta aplicando la sorpresa " + "#{@tipo.to_s}" + " al jugador " + "#{todos[actual].nombre}")
      end
    end
    
 
  end
end

  if $0 == __FILE__
  # De esta manera el main de esta clase se llamarÃ¡ cuando se ejecute este archivo explÃ­citamente
  # No se llamarÃ¡ cuando este archivo sea interpretado por ruby debido a que ha sido referenciado desde otro archivo con un require_relative
  Civitas::Sorpresa.main
  end