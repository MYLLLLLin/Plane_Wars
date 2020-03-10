package Plane_War_Game;

import java.util.Random;

public class Bigbee extends Object_Fly implements BeeAward {
    private int awardType; //2是加血，3是加火力
    private int x_move = 3; //滑稽的移动速度快
    private int y_move = 3;
    public void Flying() { //蜜蜂的移动
        x += x_move;
        y += y_move;
        if(x > Game_BackGround.WIDTH - width){
            x_move = -1;
        }
        if(x < 0){
            x_move = 1;
        }
    }

    @Override
    public boolean outBackGround() { //判断是否已经飞出游戏界面
        return y > Game_BackGround.LONGNESS;
    }

    @Override
    public int getType() { //获得奖励类型
        return awardType;
    }
    public Bigbee() {
        this.photo = Game_BackGround.bigbee;
        width = photo.getWidth();
        longness = photo.getHeight();
        y = -longness;
        Random rand = new Random();
        x = rand.nextInt(Game_BackGround.WIDTH - width);
        awardType = (rand.nextInt(2) + 2);   //初始化时给奖励
    }
}