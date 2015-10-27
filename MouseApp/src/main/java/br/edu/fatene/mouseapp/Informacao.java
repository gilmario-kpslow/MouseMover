package br.edu.fatene.mouseapp;

/**
 * Classe responsavel por montar as mensagens
 *
 * @author gilmario
 */
public class Informacao {

    /**
     * Esse construtor recebe uma string padrao e formada de acordo com a
     * mesagem recebida
     *
     * @param menssagem
     */
    public Informacao(String menssagem) {
        try {
            if (menssagem.contains("|")) {
                String[] partes = menssagem.split("\\|");
                tipo = partes[0];
                velocidadeX = Integer.parseInt(partes[1]);
                velocidadeY = Integer.parseInt(partes[2]);
                botao = partes[3];
            } else {
                tipo = "move";
                velocidadeX = 5;
                velocidadeY = 5;
                botao = "";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private String tipo;
    private int velocidadeX;
    private int velocidadeY;
    private String botao;

    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }

    public int getVelocidadeX() {
        return velocidadeX;
    }

    public void setVelocidadeX(int velocidadeX) {
        this.velocidadeX = velocidadeX;
    }

    public int getVelocidadeY() {
        return velocidadeY;
    }

    public void setVelocidadeY(int velocidadeY) {
        this.velocidadeY = velocidadeY;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
