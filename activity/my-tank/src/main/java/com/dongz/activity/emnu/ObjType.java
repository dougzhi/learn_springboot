package com.dongz.activity.emnu;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 类型
 */
@Getter
@AllArgsConstructor
public enum ObjType {
    P1(1,1,"友军", "images/tank/p1tank%s.gif", 5, 1),
    P2(1,1,"友军", "images/tank/p2tank%s.gif", 5, 1),
    ENEMY1(2,2,"敌军", "images/tank/enemy1%s.gif", 8, 1),
    ENEMY2(2,2,"敌军", "images/tank/enemy2%s.gif", 7, 1),
    ENEMY3(2,2,"敌军", "images/tank/enemy3%s.gif", 6, 2),
    ENEMY4(2,2,"敌军", "images/tank/enemy4%s.gif", 5, 2),
    ENEMY5(2,2,"敌军", "images/tank/enemy5%s.gif", 4, 3),
    ENEMY6(2,2,"敌军", "images/tank/enemy6%s.gif", 4, 3),
    BULLET(3,1,"友军子弹", "images/bullet/bullet%s.gif", 10, 1),
    ENEMYBULLET(3,2, "敌军子弹", "images/bullet/bullet%s.gif", 10, 1);

    int code; // 类型 1：玩家， 2： 敌军 ，3：子弹
    int camp; // 阵营
    String name;
    String img; //图片
    int step; // 速递
    int life;// tank：生命值 子弹：伤害

    private static Random r = new Random();

    public static List<ObjType> getP() {
        return Arrays.stream(ObjType.values()).filter(e -> e.getCode() == 1).collect(Collectors.toList());
    }

    public static List<ObjType> getEnemyTank() {
        return Arrays.stream(ObjType.values()).filter(e -> e.getCode() == 2).collect(Collectors.toList());
    }

    public static List<ObjType> getFamily(ObjType type) {
        return Arrays.stream(ObjType.values()).filter(e -> e.getCamp() == type.getCamp() && !e.equals(type)).collect(Collectors.toList());
    }


    public static List<ObjType> getEnemy(ObjType type) {
        return Arrays.stream(ObjType.values()).filter(e -> e.getCamp() != type.getCamp()).collect(Collectors.toList());
    }

    public static List<ObjType> getEnemyUnit() {
        return Arrays.stream(ObjType.values()).filter(e -> e.getCamp() == 2).collect(Collectors.toList());
    }

    public static ObjType getRandomEnemy() {
        return getEnemyTank().get(r.nextInt(getEnemyTank().size()));
    }

}