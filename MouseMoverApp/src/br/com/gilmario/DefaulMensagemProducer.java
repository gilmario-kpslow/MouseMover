package br.com.gilmario;

/**
 *
 * @author gilmario
 */
public class DefaulMensagemProducer {

    private static final String move = "move";
    private static final String click = "click";
    private static final String comando = "comando";
    private String velocidade = "0";

    public DefaulMensagemProducer() {
    }

    public DefaulMensagemProducer(String velocidade) {
        this.velocidade = velocidade;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    private String geraString(String tipo, String x, String y) {
        return tipo + "|" + x + "|" + y + "|" + "0";
    }

    private String geraString(String tipo, String x, String y, String botao) {
        return tipo + "|" + x + "|" + y + "|" + botao;
    }

    public String mensSubir() {
        return geraString(move, "0", "-" + velocidade);
    }

    public String mensSubirEsquerda() {
        return geraString(move, "-" + velocidade, "-" + velocidade);
    }

    public String mensSubirDireita() {
        return geraString(move, velocidade, "-" + velocidade);

    }

    public String mensStop() {
        return geraString(move, "0", "0");
    }

    public String mensDescer() {
        return geraString(move, "0", velocidade);

    }

    public String mensDescerEsquerda() {
        return geraString(move, "-" + velocidade, velocidade);
    }

    public String mensDescerDireita() {
        return geraString(move, velocidade, velocidade);
    }

    public String mensEsquerda() {
        return geraString(move, "-" + velocidade, "0");

    }

    public String mensDireita() {
        return geraString(move, velocidade, "0");

    }

    public String mensClickDireita() {
        return geraString(click, "0", "0", "D");

    }

    public String mensClickEsquerda() {
        return geraString(click, "0", "0", "E");
    }

    public String mensCentralizar() {
        return geraString(comando, "0", "0", "CENTRALIZAR");
    }

    public String moveTo(Float x, Float y) {
        return geraString(comando, Integer.toString(x.intValue()), Integer.toString(y.intValue()), "CENTRALIZAR");
    }
}
