import java.net.*;
import java.io.*;
import java.util.ArrayList;

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
          System.out.println("ruta:"+ruta_archivos);
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
                while(terminar==false){
                    try{
                        opcion = recibir.readChar();
                        terminar = true;
                        System.out.println(opcion);
                        rutaActualizable = recibir.readUTF();
                        System.out.println(rutaActualizable);
                        rutaActualizable = ruta_archivos+rutaActualizable;
                        System.out.println(rutaActualizable);
                    }catch(Exception e){
                        terminar = false;
                    }
                }
                terminar = false;
                switch(opcion){
                    case 'c':
                        enviarNombres(cl,rutaActualizable,opcion,enviar);
                        break;
                    case 'a':
                        enviarNombres(cl,rutaActualizable,opcion,enviar);
                        break;
                    case 't':
                        terminar = true;
                        recibir.close();
                        enviar.close();
                        cl.close();
                        break;
                }
                opcion = 0;
              }
              //
              /*int aux = recibir.readInt();
              long recibidos;
              int l,porcentaje;
              while(aux>0){
                enviar.writeBoolean(false);
                String nombre = dis.readUTF();
                long tam = dis.readLong();
                System.out.println("\nComienza descarga del archivo "+nombre+" de "+tam+" bytes");
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta_archivos+nombre));
                recibidos=0;
                l=0;
                porcentaje=0;//l es para saber cuandos bytes se leyeron en el Socket
                while(recibidos<tam){
                    byte[] b = new byte[1500];
                    l = dis.read(b);
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
              dis.close();
              enviar.close();*/
          }//for
          
      }catch(Exception e){
          e.printStackTrace();
      }  
    }//main
    static void enviarNombres(Socket so, String ruta, char t, DataOutputStream enviar){
        try{
            int i=0,tamano;
            boolean b = false;
            File root;
            File[] archivos;
            ArrayList<String> nombres = new ArrayList<String>();;
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
}