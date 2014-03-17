package ViewPkg.Menus;

import ControllerPkg.Controller;

import java.awt.*;

/**
 * Created by Xav on 03/03/14.
 */
public class MainMenu extends ContextualMenu{

    private GotoMenuButton shopIcon;

    public MainMenu(final Controller controller){
        super(controller, "main_menu");

        shopIcon = new GotoMenuButton(controller, "shop_button", new Dimension(250,100));
        this.add(shopIcon);
        shopIcon.setLocation(0, 25);

    }

    public void actualiser(){
        shopIcon.actualiser();
        this.invalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(new Color(Integer.parseInt("271828", 16)));
        graphics.fillRect(0,0,300,300);

    }
}
