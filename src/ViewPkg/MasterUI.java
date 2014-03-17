package ViewPkg;

import ControllerPkg.Controller;
import ViewPkg.Menus.*;

import javax.swing.*;
import java.awt.*;

public class MasterUI extends JLayeredPane{


    private int gridEndPointX;
    private Controller controller;
    private GotoMenuButton quitIcon;

    private MenuTriggerZone menuTriggerZone;
    private ContextualMenu selectedMenu;
    private ContextualMenu mainMenu;
    private ContextualMenu shopMenu;

    private int getGridEndPointX;

    public MasterUI(final Controller controller){
        this.controller=controller;

        mainMenu= new MainMenu(controller);
        shopMenu=new ShopMenu(controller);

        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setLocation(0,0);
        this.setLayout(null);

        int xGridSize=30;
        int tailleYGrille=30;
        VisualCase[][] visualCasesGrid=new VisualCase[xGridSize][tailleYGrille];
        Point visualCasesGridOrigin= new Point(25, (MasterFrame.GAME_FRAME_SIZE.height-tailleYGrille*VisualCase.CASE_SIDE_PIXEL_SIZE)/2);

        for (int i=0; i<xGridSize; i++){
            for (int j=0; j<tailleYGrille; j++){
                visualCasesGrid[i][j]=new VisualCase(i, j, visualCasesGridOrigin, controller);
                this.add(visualCasesGrid[i][j]);
            }
        }
        quitIcon = new GotoMenuButton(controller, "quit_button", new Dimension(25,25));
        this.add(quitIcon);
        quitIcon.setLocation(0, 0);

        gridEndPointX=25+xGridSize*VisualCase.CASE_SIDE_PIXEL_SIZE;

        selectedMenu=mainMenu;
        this.add(selectedMenu);
        selectedMenu.setLocation(gridEndPointX+25, 25);
        selectedMenu.setVisible(true);

        menuTriggerZone =new MenuTriggerZone(controller);
//        gridTriggerZone= new GridTriggerZone(controller, xGridSize);

        this.add(menuTriggerZone);
        menuTriggerZone.setLocation(gridEndPointX, 25);
//TODO kill gridTriggerZone from all of existence.
//        this.add(gridTriggerZone);
//        gridTriggerZone.setLocation(25, 25);

        //TODO Ajouter éléments visuels d'un niveau de jeu.
    }

    public void popMenu(String menuName){
        if (menuName.equals("main_menu")){
            selectedMenu=mainMenu;
            selectedMenu.setVisible(true);
        }
        else if (menuName.equals("shop_menu")){
            System.out.println("where is shop?");
            selectedMenu.setVisible(false);
            selectedMenu=null;
            selectedMenu=shopMenu;
            selectedMenu.setVisible(true);
        }
        selectedMenu.invalidate();
        selectedMenu.repaint();
        menuTriggerZone.setVisible(false);
    }

    public void setGridToActive(){
        closeMenus();
        menuTriggerZone.setVisible(true);
    }

    public void closeMenus(){
        if (selectedMenu!=null){
        selectedMenu.setVisible(false);
        selectedMenu=null;
        }
    }

    public void actualiser(){
        quitIcon.actualiser();
        if (selectedMenu!=null){
            selectedMenu.actualiser();
        }
        this.invalidate();
        this.repaint();
    }
}