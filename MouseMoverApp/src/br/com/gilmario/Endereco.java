package br.com.gilmario;

/**
 *
 * @author gilmario
 */
public class Endereco {

    private Integer porta;
    private String nome;
    private String ip;

    public Endereco() {
    }

    public Endereco(String base) {
        String[] vars = base.split("\\|");
        this.nome = vars[0];
        this.ip = vars[1];
        this.porta = Integer.parseInt(vars[2]);
    }

    public Endereco(String nome, String servidor, String porta) {
        this.nome = nome;
        this.ip = servidor;
        this.porta = Integer.parseInt(porta);
    }

    public String getValue() {
        return nome + "|" + ip + "|" + porta.toString();
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return this.nome + " : " + this.ip;
    }

}
