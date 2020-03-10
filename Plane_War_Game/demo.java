package Plane_War_Game;

        import javax.swing.*;

        import static Plane_War_Game.Game_BackGround.LONGNESS;
        import static Plane_War_Game.Game_BackGround.WIDTH;

public class demo {
    public static void main(String[] args) {
        JFrame j = new JFrame("雷霆战机"); //画框
        Game_BackGround game = new Game_BackGround(); // 画板
        j.add(game); // 将面板添加到JFrame中
        j.setSize(WIDTH, LONGNESS); // 设置大小
        j.setAlwaysOnTop(true); // 设置其总在最上
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
        j.setLocationRelativeTo(null); // 设置窗体初始位置，null相当于默认在最中间,同时在底层调用JFrame的paint()方法
        j.setVisible(true); //显示窗口
        game.play(); // 启动执行
        game.Music();
    }
}