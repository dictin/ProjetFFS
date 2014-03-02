package ModelPkg.Items;

/**
 * Created by Dictin on 14-03-02.
 */
public enum TempBoostEffect implements ItemEffect{

    LOW_HP_BOOST(0, 3),
    MED_HP_BOOST(0, 5),
    HIGH_HP_BOOST(0, 8),

    LOW_SPD_BOOST(1, 3),
    MED_SPD_BOOST(1, 5),
    HIGH_SPD_BOOST(1, 8),

    LOW_ATK_BOOST(2, 1),
    MED_ATK_BOOST(2, 3),
    HIGH_ATK_BOOST(2, 5),

    LOW_SMELLSENS_BOOST(3, 1),
    MED_SMELLSENS_BOOST(3, 2),
    HIGH_SMELLSENS_BOOST(3, 3),

    LOW_SMELLSTR_BOOST(4, 5),
    MED_SMELLSTR_BOOST(4, 10),
    HIGH_SMELLSTR_BOOST(4, 20),
    SUPER_SMELLSTR_BOOST(4, 30),

    LOW_DEF_BOOST(5, 1),
    MED_DEF_BOOST(5, 3),
    HIGH_DEF_BOOST(5, 5),

    LOW_END_BOOST(6, 10),
    MED_END_BOOST(6, 20),
    HIGH_END_BOOST(6, 30),

    LOW_GBQT_BOOST(7, 10),
    MED_GBQT_BOOST(7, 20),
    HIGH_GBQT_BOOST(7, 30),
    SUPER_GBQT_BOOST(7, 50);


    private int boostValue;
    private int statID;
    private final int DURATION = 30000;

    private TempBoostEffect(int statID, int boostValue){
        this.statID = statID;
        this.boostValue = boostValue;
    }

    public void activate(){

    }
}
