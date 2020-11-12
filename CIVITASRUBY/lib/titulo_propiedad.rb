# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  require_relative  'jugador'
  class Titulo_propiedad
   
    attr_reader :hipotecado, :nombre , :num_casas, :num_hoteles, :precio_compra, :precio_edificar, :propietario
    @@factor_intereses_hipoteca = 1.1
    def initialize( nombre,  alquiler_base ,  factor_revalorizacion,  hipoteca_base,  precio_compra,   precio_edificar)
    
      @nombre = nombre
      @alquiler_base = alquiler_base
      @factor_revalorizacion =  factor_revalorizacion
      @hipoteca_base = hipoteca_base
      @precio_compra =  precio_compra
      @precio_edificar = precio_edificar
      
      @propietario = nil
      @num_casas = 0
      @num_hoteles = 0
      @hipotecado = false
      
    end
    
    def get_importe_hipoteca
      return (@hipoteca_base.to_f * (1.0 + (@num_casas *0.5) + (@num_hoteles *2.5))).to_f
    end
    
    def get_precio_alquiler
      precio_alquiler = -1;
      if propietario_encarcelado() == true or @hipotecado == true
        precio_alquiler = 0
      else
        precio_alquiler = @alquiler_base.to_f * (1.to_f + (@num_casas.to_f * 0.5) + (@num_hoteles.to_f * 2.5))

      end
    
      return precio_alquiler
      
    end
    
    def get_precio_venta
      return @precio_compra.to_f + @precio_edificar.to_f * cantidad_casas_hoteles().to_f * @factor_revalorizacion.to_f
    end
    
    def propietario_encarcelado()
      if @propietario.encarcelado() == true or @propietario == nil
        return true
      else
        return false
      end
      
    end
    
    def actualizar_propietario_por_conversion (jugador)
      @propietario = jugador
    end
    
    def cancelar_hipoteca (jugador)
      result = false
      if @hipotecado
        if es_este_el_propietario(jugador)
          @propietario.paga(self.get_importe_cancelar_hipoteca())
          @hipotecado = false
          result = true
        end
      end
      
      result
    end
    
    def cantidad_casas_hoteles
      return @num_casas.to_f + @num_hoteles.to_f
    end
    
    def comprar (jugador)
      result = false
      if tiene_propietario() == false
        
        @propietario = jugador
        result = true
        @propietario.paga(@precio_compra)
      end
      result
    end
    
    def construir_casa (jugador)
      resul = false
  
      if es_este_el_propietario(jugador) == true
     
        @propietario.paga(@precio_edificar)
        @num_casas += 1
        result = true
      end
      
      result     
      
    end
    
    def construir_hotel (jugador)
      result = false
      
      if es_este_el_propietario(jugador) == true
        @propietario.paga(@precio_edificar)
        @num_hoteles += 1
        result = true
      end
      
      result
    end
    
    def derruir_casa (n,jugador)
      if @propietario.eql?(jugador)  && @num_casas >= n
        @num_casas -= n
        return true
      else
        return false
      end
    end
    
    def es_este_el_propietario (jugador)
      if @propietario.eql?(jugador)
        return true
      else
        return false
      end
    end
    
    def get_importe_cancelar_hipoteca
      cantidad_recibida = @hipoteca_base.to_f * (1.to_f + (@num_casas.to_f * 0.5)+ (@num_hoteles.to_f * 2.5))
      
      return cantidad_recibida.to_f * @@factor_intereses_hipoteca.to_f
    end
    
    def hipotecar (jugador)
      salida = false
      
      if @hipotecado == false && es_este_el_propietario(jugador) == true
        @propietario.recibe(get_importe_hipoteca())
        @hipotecado = true
        salida = true
      end
      
      salida
    end
    

    
    def tiene_propietario
      
      return @propietario != nil
    end
    
    def tramitar_alquiler (jugador)
     
      if tiene_propietario()== true && es_este_el_propietario(jugador) == false
        precio = get_precio_alquiler()
        jugador.paga_alquiler(precio)
        @propietario.recibe(precio)
      end
    end
    
    def vender (jugador)
      
      if @propietario.eql?(jugador)  && @hipotecado == false
        @propietario.recibe(get_precio_venta())
        @propietario.cancelar_propiedad(self)
        @propietario = nil
        @num_casas = 0
        @num_hoteles = 0
        true
      else
        false
      end
    end
    def to_s
      return  "TituloPropiedad{" + "nombre=" + "#{@nombre}\n" + 
                ", hipotecada=" + "#{@hipotecado}\n" + ", precioCompra=" + "#{@precio_compra}\n" + ", alquilerBase=" + 
                "#{@alquiler_base}\n" + ", factorRevalorizacion=" + "#{@factor_revalorizacion}\n" + ", hipotecaBase=" + 
                "#{@hipoteca_base}\n" + ", numHoteles=" + "#{@num_hoteles}\n" + ", numCasas=" + "#{@num_casas}\n" + '}'
    end
  
    def self.main
      titulo = Titulo_propiedad.new("T1",  "1000" ,  "0.1",  "500",  "2000",   "600")
      j1 = Jugador.new_jugador_nombre("PACO")
      
      titulo.actualizar_propietario_por_conversion(j1)
      puts "Tiene propietario " + titulo.tiene_propietario().to_s
      j2 = Jugador.new_jugador_nombre("PEPE")
      
      puts titulo.tramitar_alquiler(j2)
      puts titulo.to_s
      
    end
  end
  


end

  if $0 == __FILE__
  # De esta manera el main de esta clase se llamarÃ¡ cuando se ejecute este archivo explÃ­citamente
  # No se llamarÃ¡ cuando este archivo sea interpretado por ruby debido a que ha sido referenciado desde otro archivo con un require_relative
  Civitas::Titulo_propiedad.main
  end