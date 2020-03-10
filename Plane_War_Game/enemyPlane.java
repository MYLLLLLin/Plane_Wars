package Plane_War_Game;

import java.util.Random;

//敌机
public class enemyPlane extends Object_Fly implements Enemy {
    private int grades = 10; //每个敌机的分数
    private int y_move = 2; //每次移动坐标两格
    public int getGrades() { //对应接口，获得击落的敌机所带的分数
        return grades;
    }

    public  boolean outOfBounds() { //判断敌机是否已经飞出游戏界面
        return y > Game_BackGround.LONGNESS;
    }

    public void Flying() { //敌机没次移动
        y += y_move;
    }

    @Override
    public boolean outBackGround() {
        return false;
    }

    public enemyPlane() {
        this.photo = Game_BackGround.enemyPlane;
        longness = photo.getHeight();
        width = photo.getWidth();
        y = -longness;
        Random r = new Random();
        x = r.nextInt(Game_BackGround.WIDTH - width);
    }

}