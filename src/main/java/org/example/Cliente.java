package org.example;

import java.io.*;
import java.net.*;

/**
 * Cliente que se conecta a un servidor y mantiene una conversación con este, basada en la canción de Pimpinela.
 * Envía preguntas y recibe respuestas del sevidor hasta completar el diálogo.
 *
 * @author Alberto Bernet
 */
public class Cliente {

    /**
     * Inicia la conexión con el servidor y gestiona la conversación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        /**
         * Dirección del servidor.
         */
        String host = "localhost";
        /**
         * Puerto en el que el servidor espera nuestra pregunta
         */
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
