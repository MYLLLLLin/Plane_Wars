package Plane_War_Game;

public class Bullet2 extends Object_Fly {
    int y_move = 2;

    public Bullet2(int x, int y) { //boss子弹的初始位置与boss位置有关
        this.x = x;
        this.y = y;
        this.photo = Game_BackGround.bullet2;
    }
    @Override
    public void Flying() {
        y += y_move;
    }

    @Override
    public boolean outBackGround() {
        return y < longness;
    }
}
