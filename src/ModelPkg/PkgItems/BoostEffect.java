package ModelPkg.PkgItems;


import ModelPkg.PlayerData;

public enum BoostEffect implements ItemEffect {

    LOW_HP_BOOST(0, 3, -1),
    MED_HP_BOOST(0, 5, -1),
    HIGH_HP_BOOST(0, 8, -1),

    LOW_SPD_BOOST(1, 3, -1),
    MED_SPD_BOOST(1, 5, -1),
    HIGH_SPD_BOOST(1, 8, -1),

    LOW_ATK_BOOST(2, 1, -1),
    MED_ATK_BOOST(2, 3, -1),
    HIGH_ATK_BOOST(2, 5, -1),

    LOW_SMELLSENS_BOOST(3, 1, -1),
    MED_SMELLSENS_BOOST(3, 2, -1),
    HIGH_SMELLSENS_BOOST(3, 3, -1),

    LOW_SMELLSTR_BOOST(4, 5, -1),
    MED_SMELLSTR_BOOST(4, 10, -1),
    HIGH_SMELLSTR_BOOST(4, 20, -1),
    SUPER_SMELLSTR_BOOST(4, 30, -1),

    LOW_DEF_BOOST(5, 1, -1),
    MED_DEF_BOOST(5, 3, -1),
    HIGH_DEF_BOOST(5, 5, -1),

    LOW_END_BOOST(6, 10, -1),
    MED_END_BOOST(6, 20, -1),
    HIGH_END_BOOST(6, 30, -1),

    LOW_GBQT_BOOST(7, 10, -1),
    MED_GBQT_BOOST(7, 20, -1),
    HIGH_GBQT_BOOST(7, 30, -1),
    SUPER_GBQT_BOOST(7, 50, -1),

    //TEMP

    TEMP_LOW_HP_BOOST(0, 3, 5),
    TEMP_MED_HP_BOOST(0, 5, 7),
    TEMP_HIGH_HP_BOOST(0, 8, 9),

    TEMP_LOW_SPD_BOOST(1, 3, 5),
    TEMP_MED_SPD_BOOST(1, 5, 7),
    TEMP_HIGH_SPD_BOOST(1, 8, 9),

    TEMP_LOW_ATK_BOOST(2, 1, 5),
    TEMP_MED_ATK_BOOST(2, 3, 7),
    TEMP_HIGH_ATK_BOOST(2, 5, 9),

    TEMP_LOW_SMELLSENS_BOOST(3, 1, 5),
    TEMP_MED_SMELLSENS_BOOST(3, 2, 7),
    TEMP_HIGH_SMELLSENS_BOOST(3, 3, 9),

    TEMP_LOW_SMELLSTR_BOOST(4, 5, 5),
    TEMP_MED_SMELLSTR_BOOST(4, 10, 7),
    TEMP_HIGH_SMELLSTR_BOOST(4, 20, 9),
    TEMP_SUPER_SMELLSTR_BOOST(4, 30, 12),

    TEMP_LOW_DEF_BOOST(5, 1, 5),
    TEMP_MED_DEF_BOOST(5, 3, 7),
    TEMP_HIGH_DEF_BOOST(5, 5, 9),

    TEMP_LOW_END_BOOST(6, 10, 5),
    TEMP_MED_END_BOOST(6, 20, 7),
    TEMP_HIGH_END_BOOST(6, 30, 9),

    TEMP_LOW_GBQT_BOOST(7, 10, 5),
    TEMP_MED_GBQT_BOOST(7, 20, 7),
    TEMP_HIGH_GBQT_BOOST(7, 30, 9),
    TEMP_SUPER_GBQT_BOOST(7, 50, 12);



    private int boostValue;
    private int statID;
    private int duration;

    private BoostEffect(int statID, int boostValue, int duration){
        this.statID = statID;
        this.boostValue = boostValue;
        this.duration = duration;
    }

    @Override
    public void activate(){
        PlayerData.addMod(this.statID, this.boostValue);

    }


    public TempItemInstance getTempInstance(){
        return new TempItemInstance(this);
    }

    public int getDuration() {
        return duration;
    }

    public int getBoostValue() {
        return boostValue;
    }

    public int getStatID() {
        return statID;
    }
}
