/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Carlos
 * Clase para establecer conexiones y enviar mensaje a cada cliente
 */
public class Recibir implements Runnable{
    public Socket[] conexiones;
    public int x;

    Recibir(Socket[] conexiones, int n) {
        this.x = n;
        this.conexiones = conexiones;
    }
    
    @Override
    public void run(){
        try {
            //envio
            System.out.println("Le estamos enviando el mensaje a: " + conexiones[x].getInetAddress());
            conexiones[x].getOutputStream().write(Sockets.msg.getBytes());
        } catch (IOException ex) {
            System.getLogger(Recibir.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
