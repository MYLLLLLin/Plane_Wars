package Plane_War_Game;

import java.util.Random;

public class Bee extends Object_Fly implements BeeAward {
    private int awardType; //3是加血，4是加火力
    private int x_move = 2;
    private int y_move = 2;
    public void Flying() { //大蜜蜂的移动
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
    public Bee() {
        this.photo = Game_BackGround.bee;
        width = photo.getWidth();
        longness = photo.getHeight();
        y = -longness;
        Random r = new Random();
        x = r.nextInt(Game_BackGround.WIDTH - width);
        awardType = r.nextInt(2);   //初始化时给奖励
    }
}