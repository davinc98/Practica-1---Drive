import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IniciarServidor {
    public static void main(String[] args){
      try{
          int pto = 8000;
          ServerSocket s = new ServerSocket(pto);
          s.setReuseAddress(true);
          System.out.println("Servidor iniciado esperando por archivos..");
          File f = new File("");
          
          String ruta = f.getAbsolutePath();
          String carpeta="archivos";
          
          String ruta_archivos = ruta+"\\"+carpeta+"\\";
          System.out.println("Ruta:"+ruta_archivos);
          
          File f2 = new File(ruta_archivos);
          
          f2.mkdirs();//Haces la dirección, mkdir te hace un nivel de directorio, el mkdirs te hace todas las estructuras de directorio, aquí se pudo haber ocupado solo mkdir
          f2.setWritable(true);//Das permisos de escritura
          
          for(;;){
              Socket cl = s.accept();
              System.out.println("Cliente conectado desde "+cl.getInetAddress()+":"+cl.getPort());
              boolean terminar=false;
              char opcion=0;
              String rutaActualizable = "";
              DataInputStream recibir = new DataInputStream(cl.getInputStream());
              DataOutputStream enviar = new DataOutputStream(cl.getOutputStream());
                          
              while(terminar==false){
                opcion = recibir.readChar();                
                //System.out.println(opcion);
                switch(opcion){
                    case 'c':
                        enviarNombres(cl,dirCliente(ruta_archivos,recibir),opcion,enviar);
                        break;
                    case 'a':
                        enviarNombres(cl,dirCliente(ruta_archivos,recibir),opcion,enviar);
                        break;
                    case 's'://Subir Archivos
                        recibirArchivos(dirCliente(ruta_archivos,recibir),recibir,enviar);
                        break;
                    case 'e':
                        eliminar(dirCliente(ruta_archivos,recibir),recibir,enviar);
                        break;
                    case 'd':
                        enviarArchivos(dirCliente(ruta_archivos,recibir),recibir,enviar);
                        break;
                    case 't':
                        terminar = true;
                        recibir.close();
                        enviar.close();
                        cl.close();
                        System.out.println("Conexion cerrada.");
                        break;
                }
              }
          }//for
      }catch(Exception e){
          e.printStackTrace();
      }  
    }//main
    

    //Nos retorna la ruta en que se encuentra el cliente
    static private String dirCliente(String ruta_archivos, DataInputStream recibir){
        String rutaActualizable = "";
        try {
            
            rutaActualizable = recibir.readUTF();
            System.out.println("Direccion recuperada: "+rutaActualizable);
            
            //AQUI CREAR LA NUEVA CARPETA DENTRO DEL SERVIDOR
            
            rutaActualizable = ruta_archivos+rutaActualizable;
            //System.out.println("Direccion del recurso solicitado: "+rutaActualizable);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la ruta nueva.");
        }
        return rutaActualizable;
    }
    static void enviarArchivos(String rutaActualizable, DataInputStream recibir, DataOutputStream enviar){
        try{
            int total = recibir.readInt();
            File f;
            for(int i=0;i<total;i++){
                f = new File(rutaActualizable+recibir.readUTF());
                if(f.isDirectory()==false){
                    enviar(f,recibir,enviar);
                }else{
                    enviarDirectorio(f,recibir,enviar);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    static void enviarDirectorio(File dir, DataInputStream recibir, DataOutputStream enviar){
        try {
            File[] f = dir.listFiles();
            int i,aux = f.length;
            enviar.writeUTF(dir.getName());
            enviar.flush();
            enviar.writeLong(0);
            enviar.flush();
            enviar.writeInt(aux);
            for(i=0;i<aux;i++){
                if(f[i].isDirectory()){
                    enviarDirectorio(f[i],recibir,enviar);
                }else{
                    enviar(f[i],recibir,enviar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void enviar(File f, DataInputStream recibir, DataOutputStream enviar){
        String nombre,path;
        Long tam;
        DataInputStream dis;
        int enviados,l,porcentaje;
        boolean seguir;
        try{
            nombre = f.getName();
            path = f.getAbsolutePath();
            tam = f.length();
            System.out.println("\nPreparandose pare enviar archivo "+path+" de "+tam+" bytes");
            dis = new DataInputStream(new FileInputStream(path));
            enviar.writeUTF(nombre);
            enviar.flush();
            enviar.writeLong(tam);
            enviar.flush();
            enviados = 0;
            l=0;
            porcentaje=0;
            while(enviados<tam){
                byte[] b = new byte[1500];
                l=dis.read(b);
                System.out.print("\nEnviados: "+l);
                enviar.write(b,0,l);
                enviar.flush();
                enviados = enviados + l;
                porcentaje = (int)((enviados*100)/tam);
                System.out.println(", enviado el "+porcentaje+" % del archivo "+nombre);
            }//while
            System.out.println("Archivo "+nombre+" enviado...");
            dis.close();//Aquí usamos flujos y Sockets bloqueantes, por ello los tenemos que cerrar
            seguir = recibir.readBoolean();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    static void recibirArchivos(String rutaActualizable, DataInputStream recibir, DataOutputStream enviar){
        try{
            int aux = recibir.readInt();
            long recibidos;
            int l,porcentaje;
            DataOutputStream dos;
                        
            while(aux>0){
                String nombre = recibir.readUTF();
                long tam = recibir.readLong();
                System.out.println("\nComienza descarga del archivo "+nombre+" de "+tam+" bytes");
               
                dos = new DataOutputStream(new FileOutputStream(rutaActualizable+nombre));
                recibidos=0;
                l=0;
                porcentaje=0;//l es para saber cuandos bytes se leyeron en el Socket
                while(recibidos<tam){
                    byte[] b = new byte[1500];
                    
                    l = recibir.read(b);
                    //System.out.print("\nLeidos: "+l);
                    dos.write(b,0,l);
                    dos.flush();
                    recibidos = recibidos + l;
                    porcentaje = (int)((recibidos*100)/tam);
                    //System.out.print(", recibido el "+ porcentaje +" % del archivo");
                }//while
                System.out.println("Archivo recibido..");
                dos.close();
                aux -= 1;
                enviar.writeBoolean(true);
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    static void enviarNombres(Socket so, String ruta, char t, DataOutputStream enviar){
        try{
            int i=0,tamano;
            boolean b = false;
            File root;
            File[] archivos;
            ArrayList<String> nombres = new ArrayList<String>();
            if(t == 'c')
                b = true;
            //Envio de las carpetas de la carpeta raiz
            root = new File(ruta);
            archivos = root.listFiles();
            tamano = archivos.length;
            while(i<tamano){
                if(archivos[i].isDirectory()==b)
                    nombres.add(archivos[i].getName());
                i+=1;
            }
            tamano=nombres.size();
            enviar.writeBoolean(true);
            enviar.flush();
            enviar.writeInt(tamano);
            enviar.flush();
            for(i=0;i<tamano;i++){
                enviar.writeUTF(nombres.get(i));
                enviar.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static void eliminar(String dir, DataInputStream recibir, DataOutputStream enviar){
        try{
            int i,total = recibir.readInt();
            boolean b,seguir;
            b=true;
            seguir = true;
            File f;
            for(i=0;i<total;i++){
                f = new File(dir+recibir.readUTF());
                if(f.isDirectory()==false){
                    if(f.delete()==false){
                        b = false;
                    }
                }else{
                    borrarDirectorio(f);
                    f.delete();
                }
            }
            enviar.writeBoolean(b);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void borrarDirectorio (File dir){
        File[] f = dir.listFiles();
        int i,aux = f.length;
        for(i=0;i<aux;i++){
            if(f[i].isDirectory()){
                borrarDirectorio(f[i]);
            }
            f[i].delete();
        }
    }
}