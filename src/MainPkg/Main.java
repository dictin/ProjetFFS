package MainPkg;

import ViewPkg.MasterFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        MasterFrame mF=new MasterFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
    }
}
