package Plane_War_Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Object_Fly {
    protected int x; //横轴坐标
    protected int y; //纵轴坐标
    protected int longness; //图片长度
    protected int width; //图片宽度
    protected BufferedImage photo;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return longness;
    }
    public void setHeight(int longness) {
        this.longness = longness;
    }

    public BufferedImage getPhote() {
        return photo;
    }
    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    public abstract void Flying(); //飞行函数
    public abstract boolean outBackGround(); //判断是否出游戏界面
//    public static boolean boom(Object_Fly f1, Object_Fly f2){ //判断是否碰撞
//        //求出两个矩形的中心点
//        int f1x = f1.x + f1.width / 2;
//        int f1y = f1.y + f1.longness / 2;
//        int f2x = f2.x + f2.width / 2;
//        int f2y = f2.y + f2.longness / 2;
//
//        //横向和纵向碰撞检测
//        boolean H = Math.abs(f1x - f2x) < (f1.width + f2.width) / 2;
//        boolean V = Math.abs(f1y -f2y) < (f1.longness + f2.longness) / 2;
//
//        //必须两个方向同时碰撞
//        return H & V;
//    }
    public boolean shootBy(Bullet bullet){ //检测敌机是否被子弹击中
        int x = bullet.x;  //子弹横坐标
        int y = bullet.y;  //子弹纵坐标
        return this.x < x && x < this.x + width && this.y < y && y < this.y + longness;
    }

    public boolean shootByBoss(Bullet2 bullet){ //检测玩家是否被Boss的子弹击中
        int x = bullet.x;  //子弹横坐标
        int y = bullet.y;  //子弹纵坐标
        return this.x < x && x < this.x + width && this.y < y && y < this.y + longness;
    }

    public ArrayList<Bullet2> boss_shoot(){
        int xStep1 = width / 4;    //分成4份
        int yStep = 20;  //步
        ArrayList<Bullet2> bullets2 = new ArrayList<>();
        Bullet2 b0 = new Bullet2(x + xStep1, y - yStep);  //y-yStep(子弹距飞机的位置)
        Bullet2 b1 = new Bullet2(x + 3 * xStep1, y - yStep);
        bullets2.add(b0);
        bullets2.add(b1);
        return bullets2;
    }
}