import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class Cliente {
    
    //jk
    Socket cl;
    DataOutputStream enviar;
    DataInputStream recibir;
    public Cliente(){
        iniciarCliente();
    }
    void iniciarCliente(){
        try{
            int pto = 8000,i,aux;
            String dir = "127.0.0.1";//En una aplicación real se pone mi dirección IP
            cl = new Socket(dir,pto);
            System.out.println("Conexion con servidor establecida...");
            enviar = new DataOutputStream(cl.getOutputStream());
            recibir = new DataInputStream(cl.getInputStream());
            /*DataInputStream recibir = new DataInputStream(cl.getInputStream());
            int tamano = recibir.readInt();
            System.out.println("Carpetas y archivos:\n");
            for(i=0;i<tamano;i++){
                System.out.println(recibir.readUTF());
            }
            recibir.close();*/
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
    String[] getCarpetas(){
        return getArray('c');
    }
    //Retorna un arreglo con el nombre de los archivos
    String[] getArchivos(){
        return getArray('a');
    }
    //Retorna un arreglo con nombre, si t='c' se estan solicitando carpetas, si t='a' se estan solicitando archivos
    String[] getArray(char t){
        String[] p = new String[1];
        try{
            int i,tamano;
            boolean seguir = false;
            enviar.writeChar(t);
            enviar.flush();
            while(seguir==false){
                try{
                    seguir = recibir.readBoolean();
                }catch(IOException e){
                    seguir = false;
                }
            }
            tamano = recibir.readInt();
            p = new String[tamano];
            for(i=0;i<tamano;i++){
                p[i] = recibir.readUTF();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return p;
    }
}
