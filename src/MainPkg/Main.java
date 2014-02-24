package MainPkg;

import ControllerPkg.Controller;
import ViewPkg.MasterFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Screensize: "+Toolkit.getDefaultToolkit().getScreenSize());

        Controller controller = new Controller(60);
    }
}
