package br.com.gilmario;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author gilmario
 */
public class Processo implements Runnable {

    private Socket sock;
    private final String servidor;
    private final int porta;

    public Processo(String servidor, int porta) {
        this.servidor = servidor;
        this.porta = porta;
    }

    public void sendTexto(String texto) throws IOException, Exception {
        if (sock.isConnected() && !sock.isClosed()) {
            PrintWriter w = new PrintWriter(sock.getOutputStream());
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
        } catch (IOException ex) {
        }
    }
}
