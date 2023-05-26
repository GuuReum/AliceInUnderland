package com.example.aliceinunderland;

public class AEntitiyDeadHelper {
    //Enemy의 사망 판정
    public boolean isDeadEnemy(AEnemy mEnemy, int touchX, int touchY) {
        AEnemy enemy = mEnemy;

        //if enemy를 터치했다면 enemy dead
        if ((enemy.getX() < touchX && touchX < (enemy.getX() + enemy.getSize()))
                        || (enemy.getY() < touchY && (touchY < enemy.getY() + enemy.getSize()))){
            enemy.setIsAlive();
            return true;

        }
            return false;
    }

    public boolean isDeadPlayer() {

        return false;
    }
}