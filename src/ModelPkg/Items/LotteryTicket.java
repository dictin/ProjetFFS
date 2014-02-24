package ModelPkg.Items;

import java.util.Random;

public class LotteryTicket extends Item {
    // 0 signifie que le joueur a rien gagné (Meilleur chance la prochaine fois)
    // 1 signifie que le joueur remporte 10 nourritures
    // 2 signifie que le joueur remporte 50 nourritures
    // 3 signifie que le joueur remporte 100 nourritures
    // 4 signifie que le joueur remporte 100 000 nourritures
    // 5 signifie que le joueur remporte la partie instantanément
    int numberBonty;


    public int getnumberBonty() {
        return numberBonty;
    }

    public void setnumberBonty(int numberBonty) {
        //TODO à modifier les quatités de nourritures dans l'inventaire
        this.numberBonty = numberBonty;
    }
    public void activation (){
        //Donne un nombre aléatoire de 0 à 99
        Random random = new Random(100);
        int chance = random.nextInt();
        if(chance < 49){
            setnumberBonty(0);
        }
        else if (49<= chance && chance < 74){
            setnumberBonty(1);
        }
        else if(74 <= chance && chance < 89){
            setnumberBonty(2);
        }
        else if(89 <= chance && chance < 94){
            setnumberBonty(3);
        }
        else if(94 <= chance && chance < 98){
            setnumberBonty(4);
        }
        else if(98 <= chance && chance < 99){
            setnumberBonty(5);
        }
    }


}
