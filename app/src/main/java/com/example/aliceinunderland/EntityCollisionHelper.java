package com.example.aliceinunderland;

import androidx.annotation.NonNull;

public class EntityCollisionHelper {
    //Enemy의 사망 판정
    public boolean isDeadEnemy(@NonNull Enemy mEnemy, int touchX, int touchY) {
        //enemy left, top, right, bottom
        int[] enemyPos = {mEnemy.getX(), mEnemy.getY(),
                mEnemy.getX() + mEnemy.getSize(), mEnemy.getY() + mEnemy.getSize()};

        //if enemy를 터치했다면 enemy dead
        if ((enemyPos[0] <= touchX && touchX <= enemyPos[2])
                && (enemyPos[1] <= touchY && touchY <= enemyPos[3])) {
            mEnemy.setIsAlive();
            return true;

        }
        return false;
    }

    public boolean isDeadPlayer(@NonNull Enemy mEnemy, @NonNull Player mPlayer) {
        //player left, right
        int playerPosHor[] = {mPlayer.getX(), mPlayer.getX() + mPlayer.getSize()};
        //enemy left, right
        int enemyPosHor[] = {mEnemy.getX(), mEnemy.getX() + mEnemy.getSize()};

        //살아 있다면 true, 죽었다면 false를 return
        return !((playerPosHor[0] <= enemyPosHor[1] && enemyPosHor[1] <= playerPosHor[1])
                || (enemyPosHor[0] <= playerPosHor[1] && playerPosHor[1] <= enemyPosHor[1]));
    }

    public boolean playerGetBullet(@NonNull droppedBullet mDroppedBullet ,@NonNull Player mPlayer){
        int playerPosHor[] = {mPlayer.getX(), mPlayer.getX() + mPlayer.getSize()};
        int droppedBulletHor[] = {mDroppedBullet.getX(), mDroppedBullet.getX() + mDroppedBullet.getSize()};

        return !((playerPosHor[0] <= droppedBulletHor[1] && droppedBulletHor[1] <= playerPosHor[1])
                || (droppedBulletHor[0] <= playerPosHor[1] && playerPosHor[1] <= droppedBulletHor[1]));
    }
}