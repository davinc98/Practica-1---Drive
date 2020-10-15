
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Inicio extends javax.swing.JFrame {
    Cliente clt;
    private ArrayList<String> carpetas;
    private ArrayList<String> archivos;
    private ArrayList<String> direccion;
    
    //Estructura de archivos
    private List<JPanel> files;
    private List<JPanel> folders;
    
    private int contFiles;
    private int contFolders;
    
    
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
        files = new ArrayList<>();
        folders = new ArrayList<>();
        contFiles = 0;
        contFolders = 0;
        
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
        porcentajeSubido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mi Unidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 30))); // NOI18N

        MainPanel.setLayout(new java.awt.GridLayout(0, 5));
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

        subiendoArchivo.setText("Nombre Archivo");

        porcentajeSubido.setText("Subiendo: 98%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subiendoArchivo)
                                .addGap(30, 30, 30)
                                .addComponent(porcentajeSubido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subirArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subirArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(regresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(subiendoArchivo)
                        .addComponent(porcentajeSubido)))
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
        
        JPanel contenedor = new JPanel();
        
        contenedor.setBackground(Color.WHITE);
        contenedor.setPreferredSize(new Dimension(100,100));
        
        JButton boton = new JButton("Carpeta "+ contFolders);
        boton.setPreferredSize(new Dimension(100,100));        
        //Condicion si carpeta o archivo
        
        JLabel nombre = new JLabel("Carpeta "+contFolders);
        
        
        contenedor.add(boton);
        contenedor.add(nombre);
        
        MainPanel.add(contenedor);
        MainPanel.updateUI();
        
        contFolders++;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel porcentajeSubido;
    private javax.swing.JButton regresar;
    private javax.swing.JButton salir;
    private javax.swing.JLabel subiendoArchivo;
    private javax.swing.JButton subirArchivos;
    // End of variables declaration//GEN-END:variables
}
