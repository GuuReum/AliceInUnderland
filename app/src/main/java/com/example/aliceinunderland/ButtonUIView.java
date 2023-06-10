/*
package com.example.aliceinunderland;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class ButtonUIView extends androidx.appcompat.widget.AppCompatImageView {

    private Context mContext;

    private Player player;

    private FrameHelper frameHelper;

    public ButtonUIView(Context context) {
        super(context);
        player = ((MainPlaying) context).getPlayer();
        mContext = context;
    }

    public ButtonUIView(Context context, AttributeSet attrs) {
        super(context, attrs);
        player = ((MainPlaying) context).getPlayer();
        mContext = context;
    }

    public boolean onTouchEvent(MotionEvent event) {
        frameHelper = ((MainPlaying) mContext).getFrameHelper();

        int id = getId();  //눌린 버튼의 id 불러오기

        if (id == R.id.moveLeftBtn) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                player.setPlayerImage(getResources().getDrawable(R.drawable.protaleft));
                frameHelper.setLeftAnimation();
            }
            if (event.getAction() == MotionEvent.ACTION_UP)
                frameHelper.setLeftAnimation();
            return true;
        }  //moveleft

        if (id == R.id.moveRightBtn) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                player.setPlayerImage(getResources().getDrawable(R.drawable.protaright));
                frameHelper.setRightAnimation();
            }
            if (event.getAction() == MotionEvent.ACTION_UP)
                frameHelper.setRightAnimation();
            return true;
        }  //moveright

        if (id == R.id.reloadBtn) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (player.isReloadAble()) {
                    player.prepareReload();
                    ((MainPlaying)mContext).loadBulletImage();

                    reloadloop:
                    for (int i = 0; i < 6; i++) {

                        new Handler().postDelayed(new Runnable() {  // 0.3초마다에 1탄 장전
                            @Override
                            public void run() {

                                if (player.insertBullet()) {
                                    ((MainPlaying)mContext).loadBulletImage(); //이미지
                                    ((MainPlaying) mContext).setRemainBulletText(player.getRemainBullet());
                                } else {
                                    player.setReloadAble(true);
                                    player.setShootAble(true);
                                }
                            }
                        }, 300 * i);

                    }
                    ((MainPlaying)mContext).loadBulletImage();
                }
            }
            return true;
        }  //reload

        return false;
    }

}

 */
