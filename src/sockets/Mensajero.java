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
 * Clase para el manejo del hilo del cliente que envia mensajes
 */
public class Mensajero implements Runnable{ 
    public Socket cliente1;
    
    Mensajero(Socket conexion) {
        this.cliente1 = conexion;
    }
    
    @Override
    public void run(){
        try {
            while(true){
                //envio
                System.out.println("Nuestro cliente 1 es:: " + cliente1.getInetAddress());
                cliente1.getOutputStream().write("Envie un mensaje: ".getBytes());
                //recepcion
                byte[] buffer = new byte[1024];
                int tamano = cliente1.getInputStream().read(buffer);
                Sockets.msg = new String(buffer, 0, tamano);
                System.out.println("Recibiendo: " + Sockets.msg + " - del Cliente origen");
                 //Hilo para el arbitro
                ManejadorHilos arbitro = new ManejadorHilos();
                Thread manejadorT = new Thread(arbitro);
                //Ejecutamos el hilo para enviar mensajes
                manejadorT.start();
                try {
                    manejadorT.join();
                } catch (InterruptedException ex) {
                    System.getLogger(Mensajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        } catch (IOException ex) {
            System.getLogger(Enviar.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
