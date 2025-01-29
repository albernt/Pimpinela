package org.example;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexión en el puerto " + puerto + "...");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado!");


            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String mensaje = entrada.readUTF();
                System.out.println("Cliente pregunta: " + mensaje);


                String respuesta;
                switch (mensaje) {
                    case "¿Quién es?":
                        respuesta = "Soy yo";
                        break;
                    case "¿Qué vienes a buscar?":
                        respuesta = "A ti";
                        break;
                    case "Ya es tarde":
                        respuesta = "¿Por qué?";
                        break;
                    case "Porque ahora soy yo la que quiere estar sin ti":
                        respuesta = "Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta";
                        break;
                    default:
                        respuesta = "Error";
                }

                salida.writeUTF(respuesta);
                if (mensaje.equals("Porque ahora soy yo la que quiere estar sin ti")) {
                    break;
                }
            }

            System.out.println("Finalizando conexión...");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
