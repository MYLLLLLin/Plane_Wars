package Plane_War_Game;

public interface BeeAward { //击中小蜜蜂的奖励
    public int LIFE = 0; //生命值增加
    public int DOUBLEFIRE = 1; //双倍火力
    public int DOUBLELIFE = 2; //大蜜蜂时生命值加倍
    public int DOUBLEDOUBLEFIRE = 3; //大蜜蜂时火力*3
    public int getType();
}
