package br.edu.fatene.mouseapp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author gilmario
 */
public class HostUtil {

    /**
     * extrair o numero do IP local da placa de rede
     *
     * @return
     */
    public String numeroIP() {
        String ip = "";
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) ias.nextElement();
                    if (!ni.getName().equals("lo") && ni.isUp()) {
                        if (!ia.getHostAddress().contains(":")) {
                            ip = ia.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

}
