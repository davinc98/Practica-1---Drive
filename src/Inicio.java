
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Border;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Inicio extends javax.swing.JFrame {
    Cliente clt;
    private ArrayList<String> carpetas;
    private ArrayList<String> archivos;
    private ArrayList<String> direccion;
    
    
    public Inicio() {
        clt = new Cliente();
        //A direccion se anadira un nombre de carpeta cuando piquemos sobre cualquier carpeta
        //A direccion se eliminara a su ultimo elemento cuando se pique el botón regresar, en donde si direccion esta vacia no hara nada
        direccion = new ArrayList<String>();
        carpetas = new ArrayList<String>();
        archivos = new ArrayList<String>();
        
        carpetas = clt.getCarpetas("");
        archivos = clt.getArchivos("");
        initComponents();
        
        //
        pbProgreso.setValue(20); //Este es un valor de muestra
        pbProgreso.setStringPainted(true);
        
        cargarArchivosyCarpetas();
    }
    
    
    private void cargarArchivosyCarpetas(){
        //Limpiar Panel
        MainPanel.removeAll();
        
        //CARGAR ARCHIVOS
        for(String carpeta: carpetas){
            
            //Contenerdor General
            JPanel contenedor = new JPanel();
            BorderLayout layout = new BorderLayout();
            layout.setVgap(5);//Separacion Vert. entre elementos del contenedor
            contenedor.setLayout(layout);        


            //Contenedor de BOTON  
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(200,160));
            boton.setBackground(Color.LIGHT_GRAY);

            try {
                //NO SE CARGA LA IMAGEN AIUDAAAAA https://www.youtube.com/watch?v=Zb6sAQJ-l8o
                ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/folder.png"));
                int ancho = boton.getWidth();
                int alto = boton.getHeight();
                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                boton.setIcon(icono);
            } catch (Exception ex) {
                System.out.println(ex);
            }

            JPanel contenedorBoton = new JPanel();
            FlowLayout  layoutBtn = new FlowLayout();layoutBtn.setVgap(2);
            contenedorBoton.setLayout(layoutBtn);
            contenedorBoton.add(boton);


            //Contendor CHECKBOX
            JCheckBoxMenuItem checkRadio = new JCheckBoxMenuItem(carpeta);//NOMBRE CARPETA
            checkRadio.setFont(new Font("Dialog",1,15));

            JPanel contenedorCB = new JPanel();
            FlowLayout  layoutCB = new FlowLayout();layoutBtn.setVgap(2);
            contenedorCB.setLayout(layoutCB);
            contenedorCB.add(checkRadio);


            contenedor.add(contenedorBoton, BorderLayout.CENTER);
            contenedor.add(contenedorCB, BorderLayout.SOUTH); 

            MainPanel.add(contenedor);
        }
        
        //CARGAR ARCHIVOS
        for(String archivo: archivos){
            
            //Contenerdor General
            JPanel contenedor = new JPanel();
            BorderLayout layout = new BorderLayout();
            layout.setVgap(5);//Separacion Vert. entre elementos del contenedor
            contenedor.setLayout(layout);        


            //Contenedor de BOTON  
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(200,160));
            boton.setBackground(Color.WHITE);

            try {
                //NO SE CARGA LA IMAGEN AIUDAAAAA https://www.youtube.com/watch?v=Zb6sAQJ-l8o
                ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/file.png"));
                int ancho = boton.getWidth();
                int alto = boton.getHeight();
                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                boton.setIcon(icono);
            } catch (Exception ex) {
                System.out.println(ex);
            }

            JPanel contenedorBoton = new JPanel();
            FlowLayout  layoutBtn = new FlowLayout();layoutBtn.setVgap(2);
            contenedorBoton.setLayout(layoutBtn);
            contenedorBoton.add(boton);


            //Contendor CHECKBOX
            JCheckBoxMenuItem checkRadio = new JCheckBoxMenuItem(archivo);//NOMBRE CARPETA
            checkRadio.setFont(new Font("Dialog",1,15));

            JPanel contenedorCB = new JPanel();
            FlowLayout  layoutCB = new FlowLayout();layoutBtn.setVgap(2);
            contenedorCB.setLayout(layoutCB);
            contenedorCB.add(checkRadio);


            contenedor.add(contenedorBoton, BorderLayout.CENTER);
            contenedor.add(contenedorCB, BorderLayout.SOUTH); 

            MainPanel.add(contenedor);
        }        
        MainPanel.updateUI();
    }
    
    private void getCarpyAr(){
        String dir = "";
        int i,tam;
        tam = direccion.size();
        i = 0;
        while(i<tam)
            dir = dir+direccion.get(i)+"\\";
        carpetas.clear();
        archivos.clear();
        carpetas = clt.getCarpetas(dir);
        archivos = clt.getArchivos(dir);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        subirArchivos = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        subiendoArchivo = new javax.swing.JLabel();
        btnDescargar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pbProgreso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mi Unidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 30))); // NOI18N

        MainPanel.setLayout(new java.awt.GridLayout(3, 0, 30, 30));
        jScrollPane1.setViewportView(MainPanel);

        subirArchivos.setText("Subir Archivos");
        subirArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirArchivosActionPerformed(evt);
            }
        });

        regresar.setText("Regresar");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("J-DRIVE");

        subiendoArchivo.setText("Nombre Archivo.ext");

        btnDescargar.setText("Descargar");
        btnDescargar.setToolTipText("");

        btnEliminar.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subiendoArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(regresar)
                                .addGap(85, 85, 85)
                                .addComponent(jLabel1)
                                .addGap(543, 543, 543)
                                .addComponent(btnDescargar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar)
                                .addGap(69, 69, 69)
                                .addComponent(subirArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1591, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subirArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDescargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subiendoArchivo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        clt.salir();
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void subirArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirArchivosActionPerformed
        // TODO add your handling code here:
        
        cargarArchivosyCarpetas();
                
    }//GEN-LAST:event_subirArchivosActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar pbProgreso;
    private javax.swing.JButton regresar;
    private javax.swing.JButton salir;
    private javax.swing.JLabel subiendoArchivo;
    private javax.swing.JButton subirArchivos;
    // End of variables declaration//GEN-END:variables
}



        
        /*EJEMPLO
        ImageIcon imagen = new ImageIcon("folder.png");
        JButton bt_Votar = new JButton("Votar");
	JTextField campoVotos =new JTextField();
	JTextField campoPorcentaje = new JTextField();
        
        JPanel subPanel = new JPanel();
        
	subPanel.setPreferredSize(new Dimension(30, 110));
	GridLayout layoutGrid = new GridLayout(4, 1);
	layoutGrid.setVgap(5);        
	subPanel.setLayout(layoutGrid);
        
	subPanel.add(bt_Votar);
	subPanel.add(campoVotos);
	subPanel.add(campoPorcentaje);

	JPanel panelImagen = new JPanel();
	panelImagen.add(new JLabel(imagen));
	subPanel.add(panelImagen);   
        
        MainPanel.add(subPanel);*/
