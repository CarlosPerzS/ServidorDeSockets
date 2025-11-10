/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;
import java.io.IOException;
import sockets.*;
import java.net.Socket;

/**
 *
 * @author Carlos
 */
public class Enviar implements Runnable{
    public Socket[] conexiones;
    public int x;

    Enviar(Socket[] conexiones, int n) {
        this.x = n;
        this.conexiones = conexiones;
    }
    
    @Override
    public void run(){
        try {
            //envio
            System.out.println("Le estamos enviando el mensaje a: " + conexiones[x].getInetAddress());
            conexiones[x].getOutputStream().write(Sockets.msg.getBytes());
            //recepcion
            if(x < conexiones.length){ 
                byte[] buffer = new byte[1024];
                int tamano = conexiones[x].getInputStream().read(buffer);
                Sockets.msg = new String(buffer, 0, tamano);
                System.out.println("Recibiendo: " + Sockets.msg + " - " + conexiones[x].getInetAddress());
            }
        } catch (IOException ex) {
            System.getLogger(Enviar.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
