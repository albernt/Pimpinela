package org.example;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto)) {
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Tú preguntas: ");
            String pregunta = teclado.readLine();
            salida.writeUTF(pregunta);

            while (true) {
                String respuestaServidor = entrada.readUTF();
                System.out.println("Servidor responde: " + respuestaServidor);

                if (pregunta.equals("Porque ahora soy yo la que quiere estar sin ti")) {
                    break;
                }


                System.out.print("Tú preguntas: ");
                pregunta = teclado.readLine();
                salida.writeUTF(pregunta);
            }

            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
