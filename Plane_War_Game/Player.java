package Plane_War_Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

//玩家飞机
public class Player extends Object_Fly {
    private int cnt_fire; //火力值
    private int blood; //血量
    private int index; //标记使用的玩家的飞机
    private BufferedImage[] Player_photos = {};  //英雄机图片
    public int getBlood() {
        return blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }
    public void addBlood() { //血量加1
        blood++;
    }
    public void addDoubleBlood() {
        blood += 2;
    }
    public void subBlood() { //血量减1
        blood--;
    }

    public int getCnt_fire() {
        return cnt_fire;
    }
    public void setCnt_fire(int cnt_fire) {
        this.cnt_fire = cnt_fire;
    }
    public void addCnt_fire() {
        cnt_fire = 2;
    }
    public void addCnt_fireDOUBLE() {
        cnt_fire = 80;
    }


    public Player() {
        blood = 3;
        cnt_fire = 0;
        Player_photos = new BufferedImage[]{Game_BackGround.player0, Game_BackGround.player1};
        photo = Game_BackGround.player0; //初始图片是player0
        longness = photo.getHeight();
        width = photo.getWidth();
        x = 150;
        y = 400;
    }

    public void Flying(int x, int y) { //飞行物移动
        if(Player_photos.length > 0){
            photo = Player_photos[index++ / 10 % Player_photos.length];  //切换图片player0，player1
        }
    }

    public boolean outOfBounds() { //判断是否出游戏界面
        return false;
    }

    public ArrayList<Bullet> shoot() { //射击函数
        int xStep1 = width / 4;    //分成4份
        int xStep2 = width / 8;    //分成6份
        int yStep = 20;  //步
        ArrayList<Bullet> bullets = new ArrayList<>();
        //碰到普通蜜蜂就是三倍火力
        if (cnt_fire == 2) {
            Bullet b0 = new Bullet(x + xStep1, y - yStep);  //y-yStep(子弹距飞机的位置)
            Bullet b1 = new Bullet(x + 3 * xStep1, y - yStep);
            bullets.add(b0);
            bullets.add(b1);
            return bullets;
        } else if (cnt_fire  > 2) {
            Bullet b0 = new Bullet(x + 1 * xStep2, y - yStep);
            Bullet b1 = new Bullet(x + 3 * xStep2, y - yStep);
            Bullet b2 = new Bullet(x + 5 * xStep2, y - yStep);
            Bullet b3 = new Bullet(x + 7 * xStep2, y - yStep);
            bullets.add(b0);
            bullets.add(b1);
            bullets.add(b2);
            bullets.add(b3);
            return bullets;
        } else {
            Bullet b0 = new Bullet(x + 2 * xStep1, y - yStep);
            bullets.add(b0);
            return bullets;
        }
    }

    public void Moving(int x,int y){ //玩家移动
        this.x = x - width / 2;
        this.y = y - longness / 2;
    }


    @Override
    public void Flying() {
        if(Player_photos.length>0){
            photo = Player_photos[index++ / 10 % Player_photos.length];  //切换图片hero0，hero1
        }
    }

    @Override
    public boolean outBackGround() {
        return false;
    }


    public boolean hit(Object_Fly other){ //i

        int x1 = other.x - this.width / 2;                 //x坐标最小距离
        int x2 = other.x + this.width / 2 + other.width;   //x坐标最大距离
        int y1 = other.y - this.longness / 2;                //y坐标最小距离
        int y2 = other.y + this.longness /2 + other.longness; //y坐标最大距离

        int player_x = this.x + this.width / 2;               //英雄机x坐标中心点距离
        int player_y = this.y + this.longness / 2;              //英雄机y坐标中心点距离

        return player_x > x1 && player_x < x2 && player_y > y1 && player_y < y2;   //区间范围内为撞上了
    }
}