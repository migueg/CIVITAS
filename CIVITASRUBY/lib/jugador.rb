# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Jugador
    include Comparable
    
    require_relative 'dado'
    require_relative 'diario'
    require_relative 'titulo_propiedad'
    attr_reader :nombre, :num_casilla_actual, :encarcelado , :saldo , :puede_comprar ,:propiedades ,:salvoconducto
    
    @@Casas_max = 4
    @@Casas_Por_Hotel = 4
    @@Hoteles_max = 4
    @@Paso_por_salida = 1000
    @@Precio_por_libertad = 200
    @@Saldo_inicial = 7500
    
    def initialize (encarcelado, nombre , num_casilla_actual , puede_comprar, saldo, propiedades , salvoconducto)
     @encarcelado = encarcelado
     @nombre = nombre
     @num_casilla_actual = num_casilla_actual
     @puede_comprar = puede_comprar
     @saldo = saldo
     if(propiedades != nil)
       @propiedades = propiedades
     else
       @propiedades = Array.new()
       
     end
     
     @salvoconducto = salvoconducto
     
     
    end
    
   
    
    def self.new_jugador_copia(jugador)
      new(jugador.encarcelado,jugador.nombre,jugador.num_casilla_actual,jugador.puede_comprar,jugador.saldo,jugador.propiedades , jugador.salvoconducto)
    end
    def self.new_jugador_nombre(nombre)
      new(false,nombre,0,true,@@Saldo_inicial,nil,nil)
    end
    private_class_method :new
    
    def <=> (otro)
#      if @saldo < otro.saldo
#        -1
#      elseif @saldo > otro.saldo
#        1
#      else
#        0
#      end

      return @saldo <=> otro.saldo
    end
  

    

    
    def paga (cantidad)
      
      saldo = - cantidad.to_f 
      return modificar_saldo(saldo)
    end
    
    def paga_alquiler (cantidad)
      if @encarcelado == true
        false
      else
        return paga(cantidad)
      end
      
    end
    
    def cancelar_propiedad (p)
      @propiedades.delete(p)
      
    end
    
    def recibe (cantidad)
      if @encarcelado == true
        false
      else
        return modificar_saldo(cantidad)
      end
    end
    
    def encarcelar(num_casilla_carcel)
      
      if debe_ser_encarcelado()
        mover_a_casilla(num_casilla_carcel)
        @encarcelado = true
        Diario.instance.ocurre_evento("El jugador "+ "#{@nombre}" + " ha sido encarcelado")
      end
      @encarcelado
    end
    
    def mover_a_casilla (num_casilla)
      if @encarcelado == true
        false
      else
        @num_casilla_actual = num_casilla
        @puede_comprar = false
        Diario.instance.ocurre_evento("El jugador " + "#{@nombre}" + " ha movido a la casilla " + "#{num_casilla}")
        true
      end
    end
    
    def modificar_saldo(cantidad)
      @saldo += cantidad
      Diario.instance.ocurre_evento("Al jugador "+ "#{@nombre}" + " se le ha modificado en " + "#{cantidad}" + " unidades")
      true
    end
    
    def cantidad_casas_hoteles()
      suma = 0
      
      @propiedades.each do |p|
        suma += p.cantidad_casas_hoteles()
      end
      suma
    end
    
    def tiene_salvoconducto ()
   
      if @salvoconducto == nil
        false
      else
        true
      end
    end
    
    def obtener_salvoconducto (sorpresa)
      if @encarcelado == true
        false
      else
        @salvoconducto = sorpresa
        true
      end
    end
    
    def puede_comprar_casilla
      if @encarcelado == true
        @puede_comprar = false
      else
        @puede_comprar =true
      end
      @puede_comprar
    end
    
    def paga_impuesto(cantidad)
      if @encarcelado == true
        return false
      else
        paga(cantidad)
      end
    end
    
    def tiene_algo_que_gestionar
      return @propiedades.length()>0
    end
    
    def salir_carcel_pagando
      if @encarcelado == true && puede_salir_carcel_pagando() == true
        paga(@@Precio_por_libertad)
        @encarcelado = false
        Diario.instance.ocurre_evento("El jugador " + "#{@nombre}" + " ha pagado por su libertad")
        true
      else
        false
      end
    end
    
    def salir_carcel_tirando
      salgo = Dado.instance.salgo_de_la_carcel()
    
      if salgo == true
        @encarcelado = false
        Diario.instance.ocurre_evento("El jugador " + "#{@nombre}" + " ha salido de la carcel tirando")
      end
      salgo
    end
    
    def paso_por_salida
      modificar_saldo(@@Paso_por_salida)
      Diario.instance.ocurre_evento("El jugador " + "#{@nombre}" + " ha pasado por la casilla de Salida")
      true
 
    end
    
    def cancelar_hipoteca(ip)
      result = false
      if @encarcelado == true
        return false
      end
      if existe_la_propiedad(ip)
        propiedad = @propiedades[ip]
        cantidad = propiedad.get_importe_cancelar_hipoteca()
        if(puedo_gastar(cantidad))
          result = propiedad.cancelar_hipoteca(self)
          if result == true
            Diario.instance.ocurre_evento("El jugador #{@nombre}  cancela la hipoteca de la propiedad " + ip.to_s)
          end
          
        end
      end
      
      result
      
    end
    
    def comprar (titulo)
      result = false
      if @encarcelado == true
        return false
        
      end
     
      if puede_comprar == true
        precio = titulo.precio_compra
        if puedo_gastar(precio) == true
          result = titulo.comprar(self)
          

          if result == true
            @propiedades << titulo
            Diario.instance.ocurre_evento("El jugador #{@nombre}  compra la propiedad  "+titulo.to_s)
          end
          
          @puede_comprar = false
        end
        
        return result
      end
    end
    def construir_casa (ip)
      result = false
      puedo_edificar_casa = false
      
      if @encarcelado == true
        return result
      else
        if existe_la_propiedad(ip) == true
          propiedad = @propiedades[ip]
          puedo_edificar_casa = puedo_edificar_casa(propiedad)
          if puedo_edificar_casa == true
            result = propiedad.construir_casa(self)
            if result == true
              Diario.instance.ocurre_evento("El jugador #{@nombre} contruye casa en la propiedad " +ip.to_s)
            end
          end
        end
      end
      result
    end
    
    def construir_hotel(ip)
      result = false
      if @encarcelado == true
        return result
      end
      
      if existe_la_propiedad(ip)
        propiedad = @propiedades[ip]
        puedo_edificar_hotel = puedo_edificar_hotel(propiedad)
        if puedo_edificar_hotel == true
          result = propiedad.construir_hotel(self)
          propiedad.derruir_casa(@@Casas_Por_Hotel, self)
          Diario.instance.ocurre_evento("El jugador #{@nombre} construye hotel en la propiedad ")
        end
      end
      
      result
    end
    
    def en_banca_rota
      return @saldo <= 0
    end
    
    def hipotecar (ip)
      result = false
      if @encarcelado == true
        return result
      end
      
      if existe_la_propiedad(ip) == true
        propiedad = @propiedades[ip]
        result = propiedad.hipotecar(self)
      end
      
      if result == true
        Diario.instance.ocurre_evento("El jugador #{@nombre} hipoteca la propiedad " + ip.to_s)
      end
      result
    end
    
    def vender (ip)
      if  @encarcelado == true
        false
      else
        if existe_la_propiedad(ip)
          if @propiedades[ip].vender(self) == true
            propiedades.delete_at(ip)
            Diario.instance.ocurre_evento("El jugador " + "#{@nombre} " + "ha vendido su propiedad numero " + "#{ip}")
            true
          else
            false
          end
        else
          false
        end
      end
    end
    
     def to_s
       propiedades = ""
       if @propiedades.length != 0
        @propiedades.each do |prop|
        propiedades += prop.to_s
        
        end
       else
         propiedades += " Aun no tiene propiedades"
        
      end
      
      resultado = "Jugador: Encarcelado = #{@encarcelado}\n  Nombre = #{@nombre}\n Saldo= #{@saldo}
      \n Salvoconduct0 = #{@salvoconducto}\n CasillaActual = #{@num_casilla_actual}\n"
       
      resultado += "\nPropiedades = "+ propiedades
      return resultado
    end
    
     def self.main
       j1 = Jugador.new_jugador_nombre("PEPE")
       j2 =  Jugador.new_jugador_nombre("PACO")
       j1.recibe(299)
       puts j1<=>j2
       j1.encarcelar(10)
       puts j1.salir_carcel_tirando
       puts j2.salir_carcel_pagando
       puts j1.to_s
       puts j2.to_s
     end
    protected 
    attr_accessor :Casas_max , :Casas_Por_Hotel ,:Hoteles_max, :Paso_por_salida, :Precio_por_libertad , :Saldo_inicial
    
    def debe_ser_encarcelado()
      if @encarcelado == true
        false
      else
        if tiene_salvoconducto() == false
          true
        else
          perder_salvoconducto()
          Diario.instance.ocurre_evento("El jugador " + "#{@nombre}" + " se libra de la carcel" )
          false
        end
      end
    end
    
    
    private
    
    def perder_salvoconducto()
      @salvoconducto.usada()
      @salvoconducto = nil
    end
    
    def puedo_gastar(precio)

      if @encarcelado == true
        false
      else
        if @saldo >= precio
        
          return true
        else
        
          return false
        end
      end
      
    end
    
    def existe_la_propiedad (ip)
      return @propiedades[ip] != nil
    end
    
    def puede_salir_carcel_pagando 
      return @saldo >= @@Precio_por_libertad
    end
    
    def puedo_edificar_casa(titulo)
      if puedo_gastar(titulo.precio_edificar) == true && titulo.num_casas < @@Casas_max
       
        true
      else
        false
      end
    end
    
    def puedo_edificar_hotel(titulo)
      puedo = false
      
      precio = titulo.precio_edificar
      
      if puedo_gastar(precio) && titulo.num_hoteles < @@Hoteles_max && titulo.num_casas >= @@Casas_Por_Hotel
        puedo = true

      end
      puedo
    end
    
    
  end
end

if $0 == __FILE__
  # De esta manera el main de esta clase se llamarÃ¡ cuando se ejecute este archivo explÃ­citamente
  # No se llamarÃ¡ cuando este archivo sea interpretado por ruby debido a que ha sido referenciado desde otro archivo con un require_relative
  Civitas::Jugador.main
  end