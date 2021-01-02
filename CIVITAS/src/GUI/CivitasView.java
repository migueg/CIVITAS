/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.Calle;
import civitas.Casilla;
import civitas.CasillaSorpresa;
import civitas.CivitasJuego;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import civitas.Sorpresa;
import java.awt.Color;
import javax.swing.*;
/**
 *
 * @author Miguel Gacría Tenorio
 */
public class CivitasView extends javax.swing.JFrame {

    private CivitasJuego juego;
    private JugadorPanel jugadorPanel;
    private GestionarDialog gestionarD;
    private Boolean continuar = false;
    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
        jugadorPanel = new JugadorPanel();
        this.contendorVistaJugador.add (jugadorPanel);
        this.calle1.setVisible(false);
        this.paneldescanso1.setVisible(false);
        this.jRanking.setVisible(false);
        this.ranking.setVisible(false);
        this.panelRanking.setVisible(false);
        
        this.setLocationRelativeTo(null); //Posiciona la ventana en el centro
        
        repaint();
        revalidate();
    }

    private void setCalle(Calle casilla){
        this.nombre1.setText(casilla.getNombre());
        this.nombre1.setBackground(Color.red);
        this.alquiler1.setText(""+casilla.getTituloPropiedad().getAlquilerBase());
        this.compra1.setText(""+casilla.getTituloPropiedad().getPrecioCompra());
        this.hipoteca1.setText(""+casilla.getTituloPropiedad().getHipotecaBsase());
        if(casilla.getTituloPropiedad().getPropietario() != null)
            this.propietario1.setText(casilla.getTituloPropiedad().getPropietario().getNombre());
       else
            this.propietario1.setText("Sin propietario");
    }
    
    public Boolean getContinuar(){
        return this.continuar;
    }
    
    public void  setContinuar(Boolean b){this.continuar=false;}
    
    void actualizarVista(){
      
        this.jugadorPanel.setJugador(this.juego.getJugadorActual());
        Casilla casilla = this.juego.getCasillaActual();
        this.calle1.setVisible(false);
        this.paneldescanso1.setVisible(false);
        this.sorpresa.setVisible(false);
        this.jRanking.setVisible(false);
        this.ranking.setVisible(false);
        this.panelRanking.setVisible(false);
        
        if (casilla instanceof Calle){
            this.setCalle((Calle)casilla);
            this.calle1.setVisible(true);
            this.panelNombre.setBackground(Color.blue);

        }else if ( casilla instanceof CasillaSorpresa){
       
            this.sorpresa.setVisible(true);
            this.panelNombre.setBackground(Color.green);
            Sorpresa sorpresa = ((CasillaSorpresa) casilla).getSorpresa();
            
            this.nombre1.setText(casilla.getNombre());
            this.tipo.setText(sorpresa.getTexto());
           
            if(sorpresa.getValor() != -1)
                this.valor.setText(""+sorpresa.getValor());
            else
                this.valor.setText("");
        }else if ( casilla instanceof Casilla){
            this.paneldescanso1.setVisible(true);
            this.panelNombre.setBackground(Color.RED);
            this.nombre1.setText(casilla.getNombre());
        }
        
        if(this.juego.finalDelJuego()){
            this.panelRanking.setVisible(true);
            this.jRanking.setVisible(true);
            this.ranking.setVisible(true);
            
          
            String cadena = "";
            int index = 0;
            for (String nombre :   this.juego.ranking_UI() ){
                cadena += index+". "+nombre+"\n" ;
            }
            
            this.ranking.setText(cadena);
        }
        
        repaint();
        revalidate();
    }
    public void setCivitasJuego(CivitasJuego otro){
        this.juego = otro;
        this.setVisible(true);
    }
    
    void mostrarSiguienteOperacion(OperacionesJuego operacion){
        this.siguienteOperacion.setText(""+operacion);
        this.actualizarVista();
    }
    
    void mostrarEventos() {
        DiarioDialog diarioD= new DiarioDialog(this); //crea la ventana del diario
        diarioD.repaint();
        diarioD.revalidate();
    }
    
    Respuestas comprar(){
        int opcion= JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?",
        "Compra", JOptionPane.YES_NO_OPTION);
        
        if (opcion== 0){
            return Respuestas.SI;
        }else
            return Respuestas.NO;
    }

   void gestionar(){
       gestionarD = new GestionarDialog(this);
       gestionarD.gestionar(this.juego.getJugadorActual());
       gestionarD.pack();
       gestionarD.repaint();
       gestionarD.revalidate();
       gestionarD.setVisible(true);
       
   }
   
    SalidasCarcel  salirCarcel(){
       String[] opciones= {"PAGANDO", "TIRANDO"};
        int respuesta= JOptionPane.showOptionDialog(null, "¿Cómo quieres salir de la cárcel?",
       "Salir de la cárcel", JOptionPane.DEFAULT_OPTION,
       JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0] );
       
        if (respuesta == 0){
            return SalidasCarcel.PAGANDO;
        }else
            return SalidasCarcel.TIRANDO;
 
   }
   
   public int getGestion(){
       return this.gestionarD.getGestionElegida();
   }
   
   public int getPropiedad(){
       return this.gestionarD.getPropiedadElegida();
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        casilla1 = new javax.swing.JPanel();
        sorpresa = new javax.swing.JPanel();
        jTipo = new javax.swing.JLabel();
        jValor = new javax.swing.JLabel();
        tipo = new java.awt.TextField();
        valor = new java.awt.TextField();
        panelNombre = new javax.swing.JPanel();
        nombre1 = new javax.swing.JLabel();
        calle1 = new javax.swing.JPanel();
        jcompra1 = new javax.swing.JLabel();
        jalquiler1 = new javax.swing.JLabel();
        jPropietario1 = new javax.swing.JLabel();
        jHipoteca1 = new javax.swing.JLabel();
        hipoteca1 = new java.awt.TextField();
        compra1 = new java.awt.TextField();
        alquiler1 = new java.awt.TextField();
        propietario1 = new java.awt.TextField();
        paneldescanso1 = new javax.swing.JPanel();
        descanso1 = new javax.swing.JLabel();
        contendorVistaJugador = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        casillaActual = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        siguienteOperacion = new javax.swing.JTextField();
        panelRanking = new javax.swing.JPanel();
        jRanking = new javax.swing.JLabel();
        rankingScrollPanel = new javax.swing.JScrollPane();
        ranking = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));

        casilla1.setBackground(new java.awt.Color(255, 255, 255));
        casilla1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        casilla1.setForeground(new java.awt.Color(255, 255, 255));
        casilla1.setEnabled(false);
        casilla1.setMaximumSize(new java.awt.Dimension(250, 300));
        casilla1.setMinimumSize(new java.awt.Dimension(250, 300));
        casilla1.setPreferredSize(new java.awt.Dimension(250, 300));

        sorpresa.setBackground(new java.awt.Color(60, 92, 225));

        jTipo.setText("Tipo:");

        jValor.setText("Valor");

        tipo.setEnabled(false);

        valor.setEnabled(false);
        valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sorpresaLayout = new javax.swing.GroupLayout(sorpresa);
        sorpresa.setLayout(sorpresaLayout);
        sorpresaLayout.setHorizontalGroup(
            sorpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sorpresaLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(sorpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jValor)
                    .addComponent(jTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(sorpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        sorpresaLayout.setVerticalGroup(
            sorpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sorpresaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(sorpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTipo))
                .addGap(18, 18, 18)
                .addGroup(sorpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jValor))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        panelNombre.setMaximumSize(new java.awt.Dimension(157, 98));
        panelNombre.setMinimumSize(new java.awt.Dimension(250, 98));
        panelNombre.setLayout(new javax.swing.BoxLayout(panelNombre, javax.swing.BoxLayout.LINE_AXIS));

        nombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre1.setText("Nombre");
        nombre1.setToolTipText("");
        nombre1.setEnabled(false);
        panelNombre.add(nombre1);

        calle1.setBackground(new java.awt.Color(102, 255, 204));

        jcompra1.setText("Precio Compra:");
        jcompra1.setEnabled(false);

        jalquiler1.setText("Precio Alquiler:");
        jalquiler1.setEnabled(false);

        jPropietario1.setText("Propietario");
        jPropietario1.setEnabled(false);

        jHipoteca1.setText("Hipoteca:");
        jHipoteca1.setEnabled(false);

        hipoteca1.setEnabled(false);
        hipoteca1.setText("textField1");

        compra1.setEnabled(false);
        compra1.setText("textField1");

        alquiler1.setEnabled(false);
        alquiler1.setText("textField1");

        propietario1.setEnabled(false);
        propietario1.setText("textField1");

        javax.swing.GroupLayout calle1Layout = new javax.swing.GroupLayout(calle1);
        calle1.setLayout(calle1Layout);
        calle1Layout.setHorizontalGroup(
            calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calle1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcompra1)
                    .addComponent(jalquiler1)
                    .addComponent(jPropietario1)
                    .addComponent(jHipoteca1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 29, Short.MAX_VALUE)
                .addGroup(calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calle1Layout.createSequentialGroup()
                        .addComponent(hipoteca1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(alquiler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(propietario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(compra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        calle1Layout.setVerticalGroup(
            calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calle1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcompra1)
                    .addComponent(compra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jalquiler1)
                    .addComponent(alquiler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(calle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(calle1Layout.createSequentialGroup()
                        .addComponent(jPropietario1)
                        .addGap(22, 22, 22)
                        .addComponent(jHipoteca1))
                    .addGroup(calle1Layout.createSequentialGroup()
                        .addComponent(propietario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hipoteca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        paneldescanso1.setBackground(new java.awt.Color(153, 255, 153));

        descanso1.setText("CASILLA DE DESCANSO");

        javax.swing.GroupLayout paneldescanso1Layout = new javax.swing.GroupLayout(paneldescanso1);
        paneldescanso1.setLayout(paneldescanso1Layout);
        paneldescanso1Layout.setHorizontalGroup(
            paneldescanso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldescanso1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(descanso1)
                .addGap(53, 53, 53))
        );
        paneldescanso1Layout.setVerticalGroup(
            paneldescanso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldescanso1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(descanso1)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout casilla1Layout = new javax.swing.GroupLayout(casilla1);
        casilla1.setLayout(casilla1Layout);
        casilla1Layout.setHorizontalGroup(
            casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(casilla1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(casilla1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(paneldescanso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(2, Short.MAX_VALUE)))
            .addGroup(casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(casilla1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sorpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        casilla1Layout.setVerticalGroup(
            casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(casilla1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, casilla1Layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(paneldescanso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(casilla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, casilla1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(sorpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        titulo.setFont(new java.awt.Font("Felix Titling", 1, 36)); // NOI18N
        titulo.setText("CIVITAS JUEGO");

        casillaActual.setFont(new java.awt.Font("Felix Titling", 1, 18)); // NOI18N
        casillaActual.setText("CASILLA ACTUAL:");

        jLabel1.setFont(new java.awt.Font("Felix Titling", 1, 12)); // NOI18N
        jLabel1.setText("SIGUIENTE OPERACIÓN:");
        jLabel1.setEnabled(false);

        siguienteOperacion.setEnabled(false);
        siguienteOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteOperacionActionPerformed(evt);
            }
        });

        jRanking.setFont(new java.awt.Font("Felix Titling", 1, 12)); // NOI18N
        jRanking.setText("RANKING");
        jRanking.setEnabled(false);

        ranking.setColumns(20);
        ranking.setRows(5);
        ranking.setEnabled(false);
        rankingScrollPanel.setViewportView(ranking);

        javax.swing.GroupLayout panelRankingLayout = new javax.swing.GroupLayout(panelRanking);
        panelRanking.setLayout(panelRankingLayout);
        panelRankingLayout.setHorizontalGroup(
            panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
            .addGroup(panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRankingLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jRanking)
                    .addContainerGap(336, Short.MAX_VALUE))
                .addComponent(rankingScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
        );
        panelRankingLayout.setVerticalGroup(
            panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
            .addGroup(panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRankingLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jRanking)
                    .addGap(18, 18, 18)
                    .addComponent(rankingScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jButton1.setBackground(new java.awt.Color(119, 221, 190));
        jButton1.setText("CONTINUAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(contendorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(titulo)
                                .addGap(100, 100, 100))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(casillaActual)
                                .addGap(161, 161, 161)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(siguienteOperacion))
                    .addComponent(panelRanking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(siguienteOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contendorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(casillaActual)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(panelRanking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorActionPerformed

    private void siguienteOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siguienteOperacionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.continuar = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField alquiler1;
    private javax.swing.JPanel calle1;
    private javax.swing.JPanel casilla1;
    private javax.swing.JLabel casillaActual;
    private java.awt.TextField compra1;
    private javax.swing.JPanel contendorVistaJugador;
    private javax.swing.JLabel descanso1;
    private java.awt.TextField hipoteca1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jHipoteca1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jPropietario1;
    private javax.swing.JLabel jRanking;
    private javax.swing.JLabel jTipo;
    private javax.swing.JLabel jValor;
    private javax.swing.JLabel jalquiler1;
    private javax.swing.JLabel jcompra1;
    private javax.swing.JLabel nombre1;
    private javax.swing.JPanel panelNombre;
    private javax.swing.JPanel panelRanking;
    private javax.swing.JPanel paneldescanso1;
    private java.awt.TextField propietario1;
    private javax.swing.JTextArea ranking;
    private javax.swing.JScrollPane rankingScrollPanel;
    private javax.swing.JTextField siguienteOperacion;
    private javax.swing.JPanel sorpresa;
    private java.awt.TextField tipo;
    private javax.swing.JLabel titulo;
    private java.awt.TextField valor;
    // End of variables declaration//GEN-END:variables
}
