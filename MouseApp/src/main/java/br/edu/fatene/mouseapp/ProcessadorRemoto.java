package br.edu.fatene.mouseapp;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilmario
 */
class ProcessadorRemoto implements Runnable {

    private final Robot robo;
    private final Dimension tela;
    private int posicaoX;
    private int posicaoY;
    private int velocidadeX;
    private int velocidadeY;
    private Thread t;

    public ProcessadorRemoto() throws AWTException {
        robo = new Robot();
        tela = Toolkit.getDefaultToolkit().getScreenSize();
    }

    private void correr() {
        while (true) {
            if (posicaoX + velocidadeX < 0 || posicaoX + velocidadeX > tela.width) {
                velocidadeX = 0;
            } else if (posicaoY + velocidadeY < 0 || posicaoY + velocidadeY > tela.height) {
                velocidadeY = 0;
            } else if (velocidadeX != 0 || velocidadeY != 0) {
                posicaoX += velocidadeX;
                posicaoY += velocidadeY;
                robo.mouseMove(posicaoX, posicaoY);
            }

            try {
                t.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcessadorRemoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void processa(Informacao info) {
        switch (info.getTipo()) {
            case "move":
                velocidadeX = info.getVelocidadeX();
                velocidadeY = info.getVelocidadeY();
                break;
            case "click":
                switch (info.getBotao()) {
                    case "E":
                        robo.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robo.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case "D":
                        robo.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                        robo.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                        break;
                }
                break;
            case "comando":
                if (info.getBotao().equals("CENTRALIZAR")) {
                    posicaoX = tela.width / 2;
                    posicaoY = tela.height / 2;
                    robo.mouseMove(posicaoX, posicaoY);
                }
                break;
        }

        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        correr();
    }

}
