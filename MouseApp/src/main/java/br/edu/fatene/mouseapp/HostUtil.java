package br.edu.fatene.mouseapp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author gilmario
 */
public class HostUtil {

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
                        byte[] b = ia.getAddress();
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

    public static void main(String[] args) {
        HostUtil util = new HostUtil();
        System.out.println(util.numeroIP());
    }

}
