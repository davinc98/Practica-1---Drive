import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
public class Cliente{
    //jk
    Socket cl;
    DataOutputStream enviar;
    DataInputStream recibir;
    public Cliente(){
        iniciarCliente();
    }
    void iniciarCliente(){
        try{
            int pto = 8000;
            String dir = "127.0.0.1";//En una aplicación real se pone mi dirección IP
            cl = new Socket(dir,pto);
            System.out.println("Conexion con servidor establecida...");
            enviar = new DataOutputStream(cl.getOutputStream());
            recibir = new DataInputStream(cl.getInputStream());
            //cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void salir(){
        try{
            enviar.writeChar('t');
            enviar.flush();
            cl.close();
            recibir.close();
            enviar.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //Retorna un arreglo con el nombre de las carpetas
    ArrayList<String> getCarpetas(String dir){
        return getArray('c',dir);
    }
    //Retorna un arreglo con el nombre de los archivos
    ArrayList<String> getArchivos(String dir){
        return getArray('a',dir);
    }
    //Retorna un arreglo con nombre, si t='c' se estan solicitando carpetas, si t='a' se estan solicitando archivos
    ArrayList<String> getArray(char t, String dir){
        ArrayList<String> p = new ArrayList<String>();
        //System.out.println(dir);
        try{
            int i,tamano;
            boolean seguir = false;
            enviar.writeChar(t);
            enviar.flush();
            enviar.writeUTF(dir);
            enviar.flush();
            while(seguir==false){
                try{
                    seguir = recibir.readBoolean();
                }catch(IOException e){
                    seguir = false;
                }
            }
            tamano = recibir.readInt();
            for(i=0;i<tamano;i++){
                p.add(recibir.readUTF());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return p;
    }
    public void subirArchivosyCarpetas(File[] f, String dir){
        
        try{
            
            DataInputStream dis;
            String nombre;
            String path;
            long tam, enviados;
            int i,l,aux,porcentaje=0;
            File arch;
            FileWriter w;
            BufferedWriter bw;
            PrintWriter wr;
            
            boolean seguir = false;
            enviar.writeChar('s');
            enviar.flush(); 
            
            System.out.println("Directorio: "+dir);
            enviar.writeUTF(dir);//Enviar la ruta de los archivos
            enviar.flush();
                        
            aux = f.length;
            enviar.writeInt(aux);
            enviar.flush();
            
            
            for(i=0;i<aux;i++){
                nombre = f[i].getName();
                path = f[i].getAbsolutePath();
                tam = f[i].length();

                if(f[i].isDirectory()){
                    System.out.println("Carpeta "+f[i].getName()+" en espera...");
                    continue;
                }
                
                    System.out.println("\nPreparandose pare enviar archivo "+path+" de "+tam+" bytes");
                    
                    dis = new DataInputStream(new FileInputStream(path));
                    enviar.writeUTF(nombre);
                    enviar.flush();
                    enviar.writeLong(tam);
                    enviar.flush();
                    
                    enviados = 0; l=0;
                    porcentaje=0;
                    
                    while(enviados<tam){
                        byte[] b = new byte[1500];
                        l=dis.read(b);
                        enviar.write(b,0,l);
                        enviar.flush();
                        enviados = enviados + l;
                        porcentaje = (int)((enviados*100)/tam);
                        System.out.println(",enviado el "+porcentaje+" % del archivo "+nombre);
                    }//while
                    System.out.println("Archivo "+nombre+" enviado...");
                    dis.close();
                
                seguir = recibir.readBoolean();
            }
                       
            
            //FUNCION RECURSIVA A ENVIAR ARCHIVOS RESTANTES
            for(i=0;i<aux;i++){                 
                if(f[i].isDirectory()){
                    System.out.println("Subiendo archivos de "+f[i].getName());

                    File[] ff = f[i].listFiles(); 
                    for(int h=0;h<ff.length;h++){
                        if(ff[h].isDirectory())
                            System.out.println("\tCarpeta: "+ff[h].getName());
                        else
                            System.out.println("\tArchivo: "+ff[h].getName());
                    }  
                    subirArchivosyCarpetas(ff, f[i].getName());//Envio nombre de la carpeta
                }
            }            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean eliminarCarpetas(ArrayList<String> carps, String dir){
        return eliminar(carps,dir);
    }
    public boolean eliminarArchivos(ArrayList<String> archs, String dir){
        return eliminar(archs,dir);
    }
    private boolean eliminar(ArrayList<String> elim, String dir){
        boolean b = false;
        try{
            int i, tam=elim.size();
            enviar.writeChar('e');
            enviar.flush();
            enviar.writeUTF(dir);
            enviar.flush();
            enviar.writeInt(elim.size());
            enviar.flush();
            for(i=0;i<tam;i++){
                enviar.writeUTF(elim.get(i));
                enviar.flush();
            }
            b = recibir.readBoolean();
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }
    public void descargarCarpetas(ArrayList<String> carps, String dir){
        descargar(carps,dir);
    }
    public void descargarArchivos(ArrayList<String> archs, String dir){
        descargar(archs,dir);
    }
    private void descargar(ArrayList<String> des, String dir){
        try{
            int i, tam=des.size();
            String nombre;
            long tamano;
            File f = new File("");
            String ruta = f.getAbsolutePath();
            String carpeta="archivos2";
            String ruta_archivos = ruta+"\\"+carpeta+"\\";
            File f2 = new File(ruta_archivos);
            f2.mkdir();
            f2.setWritable(true);
            enviar.writeChar('d');
            enviar.flush();
            enviar.writeUTF(dir);
            enviar.flush();
            enviar.writeInt(des.size());
            enviar.flush();
            for(i=0;i<tam;i++){
                enviar.writeUTF(des.get(i));
                enviar.flush();
                nombre = recibir.readUTF();
                tamano = recibir.readLong();
                if(tamano == 0)
                    recibirDirectorio(nombre,ruta_archivos);
                else
                    recibirArchivo(nombre,tamano,ruta_archivos);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void recibirArchivo(String nomb, long tam, String ruta_archivos){
        try{
            long recibidos;
            int l,porcentaje;
            DataOutputStream dos;
            System.out.println("\nComienza descarga del archivo "+nomb+" de "+tam+" bytes");
            dos = new DataOutputStream(new FileOutputStream(ruta_archivos+"\\"+nomb));
            recibidos=0;
            l=0;
            porcentaje=0;//l es para saber cuandos bytes se leyeron en el Socket
            while(recibidos<tam){
                byte[] b = new byte[1500];
                l = recibir.read(b);
                dos.write(b,0,l);
                System.out.print("\nEnviados: "+l);
                dos.flush();
                recibidos = recibidos + l;
                porcentaje = (int)((recibidos*100)/tam);
                System.out.println(", recibido el "+ porcentaje +" % del archivo");
            }//while
            System.out.println("Archivo recibido..");
            dos.close();
            enviar.writeBoolean(true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void recibirDirectorio(String nomb, String ruta_archivos){
        try{
            int aux = recibir.readInt();
            long tam;
            String nombre;
            //Hacer el directorio
            File f2 = new File(ruta_archivos+"\\"+nomb+"\\");
            f2.mkdir();
            f2.setWritable(true);
            while(aux>0){
                nombre = recibir.readUTF();
                tam = recibir.readLong();
                if(tam==0)
                    recibirDirectorio(nombre,ruta_archivos+"\\"+nomb);
                else
                    recibirArchivo(nombre,tam,ruta_archivos+"\\"+nomb);
                
                aux-=1;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
