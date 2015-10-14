package br.edu.fatene.mouseapp;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gilmario
 */
public class MouseMoveServer implements Runnable {

    private final ServerSocket servidor;
    private final ProcessadorRemoto remoto;
    private final Log log;

    public MouseMoveServer(int porta, Log log) throws IOException, AWTException {
        this.servidor = new ServerSocket(porta);
        remoto = new ProcessadorRemoto();
        this.log = log;
    }

    private void initConversa(Socket conexao) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        receberMensagem(reader, conexao);
        log.adicionarLog(conexao.getInetAddress().toString());
        log.adicionarLog(Integer.toString(conexao.getPort()));
    }

    private void receberMensagem(final BufferedReader reader, Socket s) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        if (reader.ready()) {
                            String mensagem = reader.readLine();
                            log.adicionarLog(mensagem);
                            processaMensagem(mensagem);
                        }
                    }
                } catch (Exception e) {
                    log.adicionarLog(e.getMessage());
                }
            }

        }).start();
    }

    @Override
    public void run() {
        try {
            log.adicionarLog("Sistema Iniciado");
            while (true) {
                Socket cliente = servidor.accept();
                initConversa(cliente);
            }
        } catch (IOException ex) {
            log.adicionarLog(ex.getMessage());
        }
    }

    private void processaMensagem(String mensagem) {
        this.remoto.processa(new Informacao(mensagem));
    }
}
