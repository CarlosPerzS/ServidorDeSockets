/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Carlos
 * Clase para manejar el envio de mensajes a los clientes
 */
public class ManejadorHilos implements Runnable{
    @Override
    public void run(){
        //Enviamos mensaje para cada miembro que no sea el primero
        for (int i=0; i<=Sockets.sockets.size()-1; i++){
            Socket conexion = Sockets.sockets.elementAt(i);
            Recibir recibirmsg = new Recibir(conexion);
            Thread hilo = new Thread(recibirmsg);
            try {
                hilo.start();
                hilo.join();
            } catch (InterruptedException ex) {
                System.getLogger(ManejadorHilos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}
