package ViewPkg.Menus;

import ControllerPkg.ShopInfoController;
import ObserverPkg.Observer;

import javax.swing.*;
import java.awt.*;

public class ShopMenuInfo extends JComponent implements Observer {

    private JLabel itemName = new JLabel("Nom: ---");
    private JLabel itemCost = new JLabel("Co\u00FBt: ---");
    private JLabel boost = new JLabel("Bonus: ---");
    private JLabel permanant = new JLabel("Permanant: ---");

    private ShopInfoController shopInfoController;

    public ShopMenuInfo(ShopInfoController shopInfoController){
        this.shopInfoController = shopInfoController;
        this.shopInfoController.addObserver(this);

        this.setSize(new Dimension(320, 50));
        this.setBackground(Color.ORANGE);

        this.itemName.setLocation(2,2);
        this.itemName.setSize(new Dimension(this.getWidth()-2, 15));
        this.itemName.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        this.add(this.itemName);


        this.itemCost.setLocation(2, 21);
        this.itemCost.setSize(new Dimension(200, 10));
        this.add(this.itemCost);

        this.permanant.setLocation(204, 21);
        this.permanant.setSize(new Dimension(200, 10));
        this.add(this.permanant);

        this.boost.setLocation(2,37);
        this.boost.setSize(new Dimension(200, 10));
        this.add(this.boost);


        this.setBorder(BorderFactory.createMatteBorder(2,0,0,0, Color.BLACK));
    }

    public void paintComponent(Graphics graphics){
        graphics.setColor(Color.ORANGE);
        graphics.drawImage(Toolkit.getDefaultToolkit().getImage("IMG/DESCRIPTION_BACKGROUND.jpg"),0,0,this);
    }

    /**
     * Méthode qui détermine le nom de la statistique
     * @param statID statistique dont ont cherche le nom
     * @return le nom de la statistique
     */
    private String getStatTitle(int statID){
        String returnValue = "";
        switch (statID){
            case 0: returnValue ="HP";
                break;
            case 1: returnValue = "SPD";
                break;
            case 2: returnValue = "ATK";
                break;
            case 3: returnValue = "SMELLSENS";
                break;
            case 4: returnValue = "SMELLSTR";
                break;
            case 5: returnValue = "DEF";
                break;
            case 6: returnValue = "END";
                break;
            case 7: returnValue = "GBQT";
                break;
            default: returnValue = "---";
        }

        return returnValue;
    }

    /**
     * Cette méthode met à jour les informations relatives aux joueurs selon celles récupérées du modèle
     */
    @Override
    public void update() {

        this.itemName.setText("Nom: "+this.shopInfoController.getName());
        this.itemCost.setText("Co\u00FBt: "+this.shopInfoController.getCost());
        this.boost.setText("Bonus: "+this.getStatTitle(this.shopInfoController.getStatID())+" +"+this.shopInfoController.getModValue());
        if (this.shopInfoController.isPermanent()){
            this.permanant.setText("Permanant: oui");
        }else{
            this.permanant.setText("Permanant: non");
        }

    }
}
