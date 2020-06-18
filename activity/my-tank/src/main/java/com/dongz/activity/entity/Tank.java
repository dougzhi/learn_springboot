package com.dongz.activity.entity;

import com.dongz.activity.emnu.Direction;
import com.dongz.activity.emnu.ObjType;
import com.dongz.activity.frame.TankFrame;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

@Data
public class Tank extends BaseEntity {
    private boolean Dl, Dr, Du, Dd;

    private Bullet bullet;

    private static Random r = new Random();

    public Tank(int x, int y, Direction dir, ObjType type) {
        super(x, y, dir, type);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void keyPressed(KeyEvent e) {
        clearDir();
        switchPo(e, true);
        switchDir();
    }

    public void keyReleased(KeyEvent e) {
        switchPo(e, false);
        switchDir();
    }

    private void switchPo(KeyEvent e, boolean move) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                Dl = move;
                break;
            case KeyEvent.VK_RIGHT:
                Dr = move;
                break;
            case KeyEvent.VK_UP:
                Du = move;
                break;
            case KeyEvent.VK_DOWN:
                Dd = move;
                break;
            case KeyEvent.VK_CONTROL:
                if (move) {
                    this.fire();
                }
                break;
            default:
                break;
        }
    }

    private void fire() {
        TankFrame.me.objs.add(new Bullet(this.x , this.y, this.dir, ObjType.BULLET));
    }

    private void fire(ObjType type) {
        TankFrame.me.objs.add(new Bullet(this.x , this.y, this.dir, type));
    }

    private void switchDir() {
        moving = Dl || Dr || Du || Dd;

        if (Dl && !Dr && !Du && !Dd) {
            dir = Direction.Left;
        }

        if (!Dl && Dr && !Du && !Dd) {
            dir = Direction.Right;
        }

        if (!Dl && !Dr && Du && !Dd) {
            dir = Direction.Up;
        }

        if (!Dl && !Dr && !Du && Dd) {
            dir = Direction.Down;
        }
    }

    private void clearDir() {
        Dl = false;
        Dr = false;
        Du = false;
        Dd = false;
    }

    /**
     * 判断是否越界
     */
    public void move() {
        switch (dir) {
            case Left:
                x -= this.type.getStep();
                if (x < width / 2) x = width / 2;
                break;
            case Right:
                x += this.type.getStep();
                if (x > TankFrame.me.sizeX - width / 2) x = TankFrame.me.sizeX - width / 2;
                break;
            case Up:
                y -= this.type.getStep();
                if (y < height / 2) y = height / 2;
                break;
            case Down:
                y += this.type.getStep();
                if (y > TankFrame.me.sizeY - height / 2) y = TankFrame.me.sizeY - height / 2;
                break;
            default:
                break;
        }
        // 地方tank 随机给定方向
        if (ObjType.getEnemyUnit().contains(type)) {
            if (random()) dir = Direction.getRandomDir();
            if (random()) fire(ObjType.ENEMYBULLET);
        }
    }

    private  boolean random() {
        return r.nextInt(100) > 95;
    }
}