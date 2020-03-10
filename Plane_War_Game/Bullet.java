package Plane_War_Game;

public class Bullet extends Object_Fly {
    int y_move = 2; //每次移动坐标两格

    public Bullet(int x, int y) { //子弹的初始位置与玩家飞机位置有关
        this.x = x;
        this.y = y;
        this.photo = Game_BackGround.bullet;
    }

    @Override
    public void Flying() {
        y -= y_move;

    }

    @Override
    public boolean outBackGround() {
        return y < -longness;
    }

}