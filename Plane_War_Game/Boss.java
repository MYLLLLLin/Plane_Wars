package Plane_War_Game;

import java.util.ArrayList;
import java.util.Random;

public class Boss extends Object_Fly implements Enemy2 {
    private int grades = 80; //每个敌机的分数
    private int x_move = 1; //每次移动坐标两格
    private int y_move = 2; //每次y轴移动
    public int getGrades() { //对应接口，获得击落的敌机所带的分数
        return grades;
    }

    public  boolean outOfBounds() { //判断敌机是否已经飞出游戏界面
        return y > Game_BackGround.LONGNESS;
    }

    public void Flying() { //敌机没次移动
        if (y < 70) y += y_move;
        x += x_move;
        if(x > Game_BackGround.WIDTH - width){
            x_move = -1;
        }
        if(x < 0){
            x_move = 1;
        }
    }

    @Override
    public boolean outBackGround() {
        return false;
    }

    public Boss() {
        this.photo = Game_BackGround.boss;
        longness = photo.getHeight();
        width = photo.getWidth();
        y = -longness;
        Random r = new Random();
        x = r.nextInt(Game_BackGround.WIDTH - width);
    }

    public ArrayList<Bullet2> boss_shoot() { //射击函数
        int xStep1 = width / 4;    //分成4份
        int yStep = 20;  //步
        ArrayList<Bullet2> bullets2 = new ArrayList<>();
        Bullet2 b0 = new Bullet2(x + xStep1, y + yStep);  //y-yStep(子弹距飞机的位置)
        Bullet2 b1 = new Bullet2(x + 3 * xStep1, y + yStep);
        bullets2.add(b0);
        bullets2.add(b1);
        return bullets2;

    }
}
