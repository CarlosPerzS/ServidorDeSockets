/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sockets;
import sockets.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Carlos
 */
public class Sockets {
    public static String msg = "Envie un texto...";
    public static Vector<Thread> hilos = new Vector<>();
    
    public static void main(String[] args) {
        try {
            // empezamos con las inicializaciones, lo de diario
            ServerSocket ss;
            ss = new ServerSocket(1234);
            Socket [] conexiones = new Socket[10];
            int n=0;
            
            //Hilo para el arbitro
            ManejadorHilos arbitro = new ManejadorHilos();
            Thread manejadorT = new Thread(arbitro);
            manejadorT.start();
            
            while(true){
                //hay que obtener los sockets
                System.out.println("Esperando conexion "+ (n+1) + "...");
                Socket conexion = ss.accept();
                conexiones[n] = conexion;
                System.out.println("Cliente " + (n + 1) + " conectado desde: " + conexion.getInetAddress());
                
                //Esta parte es cuando se hace el hilo
                Enviar c1;
                c1 = new Enviar(conexiones, n);
                Thread t;
                t = new Thread(c1);
                hilos.add(t);
     
                n++;
            }
        } catch (IOException ex) {
            System.getLogger(Sockets.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}

