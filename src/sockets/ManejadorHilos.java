/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;
import sockets.*;

/**
 *
 * @author Carlos
 */
public class ManejadorHilos implements Runnable{
    @Override
    public void run(){
        while (true){
            if(!Sockets.hilos.isEmpty()){
                try {
                    Thread hilo = Sockets.hilos.remove(0);
                    hilo.start();
                    hilo.join();
                } catch (InterruptedException ex) {
                    System.getLogger(ManejadorHilos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
    }
}
