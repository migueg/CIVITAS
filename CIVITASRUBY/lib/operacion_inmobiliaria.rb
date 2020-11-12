# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


module Civitas
  class Operacion_Inmobiliaria
    attr_reader :gestion, :num_propiedad
    def initialize(gestion,num_propiedad)
      @num_propiedad = num_propiedad
      @gestion = gestion
    end
    
  end
end