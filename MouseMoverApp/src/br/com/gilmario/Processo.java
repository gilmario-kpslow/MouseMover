package br.com.gilmario;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilmario
 */
public class Processo implements Runnable {

    private Socket sock;
    private PrintWriter w;
    private final String servidor;
    private final int porta;
    private boolean conectado;

    public Processo(String servidor, int porta) {
        this.servidor = servidor;
        this.porta = porta;
    }

    public void sendTexto(String texto) throws IOException, Exception {
        if (conectado) {

            if (w == null) {
                w = new PrintWriter(sock.getOutputStream());
            }
            w.println(texto);
            w.flush();
        } else {
            throw new Exception("Você não está conectado");
        }
    }

    public boolean isConected() throws IOException {
        Socket so = new Socket(servidor, porta);
        return so.isConnected();
    }

    public void run() {
        try {
            this.sock = new Socket(servidor, porta);
            conectado = true;
        } catch (IOException ex) {
            conectado = false;
        }
    }

    void sendTexto(Float x, Float y) {

    }

}
