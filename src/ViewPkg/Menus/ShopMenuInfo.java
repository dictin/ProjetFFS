package ViewPkg.Menus;

import javax.swing.*;
import java.awt.*;

public class ShopMenuInfo extends JComponent {

    private JLabel itemName = new JLabel("Nom: ---");
    private JLabel itemCost = new JLabel("Co\u00FBt: ---");
    private JLabel boost = new JLabel("Bonus: ---");
    private JLabel permanant = new JLabel("Permanant: ---");

    public ShopMenuInfo(){
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
        graphics.fillRect(0,0,this.getWidth(), this.getHeight());

    }
}
