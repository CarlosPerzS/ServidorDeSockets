/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Carlos
 * Clase para establecer conexiones y enviar mensaje a cada cliente
 */
public class Recibir implements Runnable{
    public Socket conexion;

    Recibir(Socket conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void run(){
        try {
            //envio
            System.out.println("Le estamos enviando el mensaje a: " + conexion.getInetAddress());
            conexion.getOutputStream().write(Sockets.msg.getBytes());
        } catch (SocketException exe){
            System.out.println("Se desconecto: " + conexion.getInetAddress());
        }catch (IOException ex) {
            System.getLogger(Recibir.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
    }
}
