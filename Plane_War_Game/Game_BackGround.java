package Plane_War_Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class Game_BackGround extends JPanel { //Game_Background相当于一个画板

    //背景图片的大小400*654
    public static final int WIDTH = 400; // 面板宽
    public static final int LONGNESS = 654; // 面板高

    //游戏的当前状态: START RUNNING PAUSE GAME_OVER
    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static final int GAME_OVER = 3;

    private int grades = 0; // 得分
    private int behit_cnt = 0; //boss被击中次数
    private int boss_index = -1; //记录boss的编号
    private int boss_cnt = 0; //控制boss生成个数
    private Timer timer; // 定时器
    private int intervel = 1000 / 100; // 时间间隔(毫秒)
    private  boolean isBossAlive =false;
    public static BufferedImage player0; //英雄机状态0
    public static BufferedImage player1; //英雄机状态1
    public static BufferedImage bee; //小蜜蜂
    public static BufferedImage bigbee; //大蜜蜂
    public static BufferedImage enemyPlane; //敌机
    public static BufferedImage boss; //敌机图片
    public static BufferedImage bullet; //子弹
    public static BufferedImage bullet2; //子弹
    public static BufferedImage background; //背景图片
    public static BufferedImage start; //开始图片
    public static BufferedImage pause; //暂停图片
    public static BufferedImage gameover; //游戏结束

    private Player player = new Player(); // 玩家飞机
    private List<Bullet> bullets = new ArrayList<>(); // 子弹数组
    private List<Bullet2> bullets2 = new ArrayList<>(); // boss子弹数组
    private List<Object_Fly> flyings = new ArrayList<>(); //代替蜜蜂、大蜜蜂及敌机和boss

    //静态块专门加载静态资源
    static{
        try {
            background = ImageIO.read(Game_BackGround.class.getResource("background.png"));
            player0 = ImageIO.read(Game_BackGround.class.getResource("player0.png"));
            player1 = ImageIO.read(Game_BackGround.class.getResource("player1.png"));
            bee = ImageIO.read(Game_BackGround.class.getResource("bee.png"));
            bigbee = ImageIO.read(Game_BackGround.class.getResource("bigbee.png"));
            enemyPlane = ImageIO.read(Game_BackGround.class.getResource("enemy1.png"));
            boss = ImageIO.read(Game_BackGround.class.getResource("boss.png"));
            bullet = ImageIO.read(Game_BackGround.class.getResource("bullet.png"));
            bullet2 = ImageIO.read(Game_BackGround.class.getResource("bullet2.png"));
            start = ImageIO.read(Game_BackGround.class.getResource("start.png"));
            pause = ImageIO.read(Game_BackGround.class.getResource("pause.png"));
            gameover = ImageIO.read(Game_BackGround.class.getResource("gameover.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //Graphics相当于画笔的作用
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null); //x、y的值相当于绘制的起点
        g.drawImage(player.getPhote(), player.x, player.y, null); //玩家飞机
        for (int i = 0; i < flyings.size(); i++) { //蜜蜂和敌机
            g.drawImage(flyings.get(i).getPhote(), flyings.get(i).x, flyings.get(i).y, null);
        }
        for (int i = 0; i < bullets.size(); i++) { //子弹
            g.drawImage(bullets.get(i).getPhote(), bullets.get(i).x - bullets.get(i).getWidth() / 2, bullets.get(i).y, null);
        }
        for (int i = 0; i < bullets2.size(); i++) { //boss子弹
            g.drawImage(bullets2.get(i).getPhote(), bullets2.get(i).x - bullets2.get(i).getWidth() / 2, bullets2.get(i).y, null);
        }
        paintGrades(g);
        paintState(g);
    }

    //分数
    public void paintGrades(Graphics g) {
        int x = 10; // x坐标
        int y = 25; // y坐标
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14); // 字体
        g.setColor(new Color(0x3A3B3B));
        g.setFont(font); // 设置字体
        g.drawString("GRADES:" + grades, x, y); // 画分数
        y += 20; // y坐标增20
        g.drawString("BLOOD:" + player.getBlood(), x, y); // 画命
    }

    //游戏状态
    public void paintState(Graphics g) {
        switch (state) {
            case START: // 启动状态
                g.drawImage(start, 0, 0, null);
                break;
            case PAUSE: // 暂停状态
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER: // 游戏终止状态
                g.drawImage(gameover, 0, 0, null);
                break;
        }
    }

    //启动代码
    public void play() {

        MouseAdapter m = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) { //鼠标移动
                if (state == RUNNING) { // 运行状态下移动英雄机--随鼠标位置
                    int x = e.getX();
                    int y = e.getY();
                    player.Moving(x, y);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) { //鼠标进入
                if (state == PAUSE) {
                    state = RUNNING;
                }
            }
            @Override
            public void mouseExited(MouseEvent e) { // 鼠标退出
                if (state != GAME_OVER && state != START) { // 游戏未结束，则设置其为暂停
                    state = PAUSE;
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) { // 鼠标点击
                switch (state) {
                    case START:
                        state = RUNNING; // 启动状态下运行
                        break;
                    case GAME_OVER: // 游戏结束，清理现场
                        flyings.clear(); // 清空飞行物
                        bullets.clear(); // 清空子弹
                        player = new Player(); // 重新创建英雄机
                        grades = 0; // 清空成绩
                        state = START; // 状态设置为启动
                        break;
                }
            }
        };
        this.addMouseListener(m); //处理鼠标点击操作
        this.addMouseMotionListener(m); //处理鼠标移动操作

        timer = new Timer(); // 主流程控制
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) { //运行状态
                    enterAction(); //飞行物入场
                    stepAction(); //移动
                    shootAction(); //玩家飞机射击
                    bangAction(); //子弹打飞行物
                    outOfBoundsAction(); //删除越界飞行物及子弹
                    checkGameOverAction(); //检查游戏结束
                }
                repaint(); //调用paint()重绘
            }
        }, intervel, intervel);
    }

    int flying_cnt = 0; //记录飞行物个数
    public Object_Fly nextOne() { //生成新的飞行物，蜜蜂和敌机随机生成，但概率不相等
        if (isBossAlive == false && grades % 400 == 0 || grades % 410 == 0 || grades % 420 == 0 || grades % 430 == 0) {
            isBossAlive = true;
            boss_cnt++;
            if (boss_cnt == grades / 400) {
                return new Boss();
            }
            boss_cnt--;
        }
        Random r = new Random();
        int x = r.nextInt(100);
        if (x < 85)    return new enemyPlane();
        else if(x >= 85 && x < 95)   return new Bee();
        else    return new Bigbee();
    }
    public void enterAction() {
        flying_cnt++;
        if (flying_cnt % 40 == 0) {
            Object_Fly obj = nextOne();
            flyings.add(obj);
        }
    }

    public void stepAction() { //移动函数
        for (int i = 0; i < flyings.size(); i++) {
            Object_Fly fly = flyings.get(i);
            fly.Flying();
        }
        for (int i = 0; i < bullets.size(); i++) { // 子弹走一步
            Bullet b = bullets.get(i);
            b.Flying();
        }
        for (int i = 0; i < bullets2.size(); i++) { // boss子弹走一步
            Bullet2 b = bullets2.get(i);
            b.Flying();
        }
        player.Flying();
    }

    int shoot_cnt = 0; //射击次数
    public void shootAction() { //玩家飞机射击
        shoot_cnt++;
        if (shoot_cnt % 30 == 0) {
            ArrayList<Bullet> bs = player.shoot(); // 英雄打出子弹
            for (int i = 0; i < bs.size(); i++) {
                bullets.add(bs.get(i));
            }
        }

        for (int i = 0; i < flyings.size(); i++) {
            if (shoot_cnt % 90 == 0 && flyings.get(i) instanceof Enemy2) {
                ArrayList<Bullet2> bs2 = flyings.get(i).boss_shoot(); // boss打出子弹
                for (int j = 0; j < bs2.size(); j++) {
                    bullets2.add(bs2.get(j));
                }
            }
        }
    }

    //子弹与飞行物碰撞检测
    public void bangAction() {
        for (int i = 0; i < bullets.size(); i++) { // 遍历所有玩家子弹
            Bullet b = bullets.get(i);
            bang(b); // 子弹和飞行物之间的碰撞检查
        }
        for (int i = 0; i < bullets2.size(); i++) { // 遍历所有boss子弹
            Bullet2 b = bullets2.get(i);
            bang2(b); // 子弹和飞行物之间的碰撞检查
        }
    }
    //子弹和飞行物之间的碰撞检查
    public void bang(Bullet bullet) {
        int index = -1; // 击中的飞行物索引
        for (int i = 0; i < flyings.size(); i++) {
            Object_Fly obj = flyings.get(i);
            if (obj.shootBy(bullet)) { // 判断是否击中
                index = i; // 记录被击中的飞行物的索引
                bullets.remove(bullet); //删除子弹
                break;
            }
        }
        if (index != -1) { // 有击中的飞行物
            //删除被击中的飞行物
            Object_Fly one = flyings.get(index); // 记录被击中的飞行物
            if (one instanceof Enemy || one instanceof BeeAward) {
                flyings.remove(index);
                // 检查one的类型(敌人加分，奖励获取)
                if (one instanceof Enemy) { // 检查类型，是敌人，则加分
                    Enemy e = (Enemy) one; // 强制类型转换
                    grades += e.getGrades(); // 加分
                } else if (one instanceof BeeAward) { // 若为奖励，设置奖励
                    BeeAward a = (BeeAward) one;
                    int type = a.getType(); // 获取奖励类型
                    switch (type) {
                        case BeeAward.DOUBLEFIRE:
                            player.addCnt_fire(); // 设置双倍火力
                            break;
                        case BeeAward.LIFE:
                            player.addBlood(); // 设置加命
                            break;
                        case BeeAward.DOUBLEDOUBLEFIRE: //四倍火力
                            player.addCnt_fireDOUBLE();
                            break;
                        case BeeAward.DOUBLELIFE:
                            player.addDoubleBlood();
                            break;
                    }
                }
            } else if (one instanceof Enemy2) {
                if (behit_cnt == 0) boss_index = index;
                if (behit_cnt != 7 && boss_index == index)    behit_cnt++;
                else if (behit_cnt == 7){
                    flyings.remove(boss_index);
                    Enemy2 e = (Enemy2) one;
                    grades += e.getGrades(); // 加分
                    behit_cnt = 0;
                    isBossAlive = false;
                }
            }
        }
    }

    public void bang2 (Bullet2 bullet2) {
        if (player.shootByBoss(bullet2)) {
            player.subBlood();
            player.setCnt_fire(0); // 双倍火力解除
            bullets2.remove(bullet2);
        }
    }

    //删除越界飞行物及子弹
    public void outOfBoundsAction() {
        for (int i = 0; i < flyings.size(); i++) {
            if (flyings.get(i).outBackGround()) {
                flyings.remove(i); // 不越界的留着
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).outBackGround()) {
                bullets.remove(i);
            }
        }
        for (int i = 0; i < bullets2.size(); i++) {
            if (bullets2.get(i).outBackGround()) {
                bullets2.remove(i);
            }
        }
    }

    // 游戏结束函数
    public void checkGameOverAction() {
        if (isGameOver()) {
            state = GAME_OVER; // 改变状态
        }
    }
    //检查游戏是否结束
    public boolean isGameOver() {

        for (int i = 0; i < flyings.size(); i++) {
            int index = -1;
            Object_Fly obj = flyings.get(i);
            if (player.hit(obj)) { // 检查英雄机与飞行物是否碰撞
                player.subBlood(); // 减命
                player.setCnt_fire(0); // 双倍火力解除
                index = i; // 记录碰上的飞行物索引
            }
            if (index != -1) {
                flyings.remove(index);
            }
        }
        return player.getBlood() <= 0;
    }
    public void Music(){               //注意，java只能播放无损音质，如.wav这种格式
        try {
            File f;
            URI uri;
            URL url;
            f = new File("/Users/mayinglin/IdeaProjects/My_Game/src/Game05/fly.wav"); //绝对路径
            uri = f.toURI();
            url = uri.toURL(); //解析路径
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.loop();  //单曲循环
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}