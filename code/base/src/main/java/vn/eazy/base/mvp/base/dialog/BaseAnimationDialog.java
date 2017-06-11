package vn.eazy.base.mvp.base.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

/**
 * Created by Harry on 2/16/17.
 */

public abstract class BaseAnimationDialog extends BaseDialog implements View.OnClickListener {
    private int time_start_animation = 200;
    private int time_end_animation = 200;

    private static final double TENSION = 800;
    private static final double DAMPER = 40;

    private boolean enableAnimation = true;

    private SpringSystem springSystem;
    private Spring mSpring;

    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
    }

    public BaseAnimationDialog(Context context) {
        super(context);
        init(time_start_animation, time_end_animation);
    }

    public BaseAnimationDialog(Context context, int startAnim, int endAnim) {
        super(context);
        init(startAnim, endAnim);
    }

    public BaseAnimationDialog(Context context, int themeResId, int startAnim, int endAnim) {
        super(context, themeResId);
        init(startAnim, endAnim);
    }

    private void init(int startAnim, int endAnim) {
        this.time_start_animation = startAnim;
        this.time_end_animation = endAnim;
        springSystem = SpringSystem.create();
        mSpring = springSystem.createSpring();

        SpringConfig config = new SpringConfig(TENSION, DAMPER);
        mSpring.setSpringConfig(config);
    }

    @Override
    public void show() {
        super.show();
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = displayMetrics.widthPixels;
        lp.height = displayMetrics.heightPixels;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.CENTER);

        if (isEnableAnimation()) {
            mSpring.setEndValue(-dialogContent.getHeight() * 1.1);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialogContent != null && mSpring != null) {
                        dialogContent.setVisibility(View.VISIBLE);
                        mSpring.setEndValue(dialogContent.getY());
                    }
                }
            }, time_start_animation);

            mSpring.addListener(new SpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    float value = (float) spring.getCurrentValue();
                    dialogContent.setY(value);
                }

                @Override
                public void onSpringAtRest(Spring spring) {

                }

                @Override
                public void onSpringActivate(Spring spring) {

                }

                @Override
                public void onSpringEndStateChange(Spring spring) {

                }
            });
        } else {
            dialogContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(layoutResID, null, false);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() > 0) {
            dialogContent = viewGroup.getChildAt(0);
            if (dialogContent != null) {
                dialogContent.setVisibility(View.INVISIBLE);
            }
        }
        view.setOnClickListener(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() > 0) {
            dialogContent = viewGroup.getChildAt(0);
            if (dialogContent != null) {
                dialogContent.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void dismissWithAnimation() {
        if (isEnableAnimation()) {
            mSpring.setEndValue(-dialogContent.getHeight() * 1.1);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, time_end_animation);
        }else{
            dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        dismissWithAnimation();
    }

    public boolean isEnableAnimation() {
        return enableAnimation;
    }

    public void enableAnimation(boolean enableAnimation) {
        this.enableAnimation = enableAnimation;
    }
}
