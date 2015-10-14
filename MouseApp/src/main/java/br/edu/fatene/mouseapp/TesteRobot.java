package br.edu.fatene.mouseapp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author gilmario
 */
public class TesteRobot {

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot b = new Robot();
        //b.mousePress(InputEvent.BUTTON1_MASK);
        System.out.println("Inicinadno em 2 s");
        Thread.sleep(2000);
        System.out.println("click2");
        b.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        b.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//        b.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
