package com.cxp.custompasswordview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/21.
 */

public class PassWordView extends EditText {

    private boolean isAdd;
    private Paint bgPaint;
    private Paint arcPaint;
    private Paint contentPaint;
    private int mPadding = 1;
    private int radius;
    private int textLength;

    public PassWordView(Context context) {
        this(context, null);
    }

    public PassWordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassWordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (getText().toString().length() > textLength) {
            isAdd = true;
        } else {
            isAdd = false;
        }
        textLength = text.length();
        if(textLength==6){
            if(mListener!=null){
                mListener.onComplete(text.toString());
            }
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager manager= (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(getWindowToken(),0);
                }
            },100);
        }
        postInvalidate();
    }

    private void init() {
        radius = dp2px(6);
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.WHITE);
        bgPaint.setStyle(Paint.Style.FILL);

        arcPaint = new Paint();
        bgPaint.setAntiAlias(true);
        arcPaint.setColor(Color.GRAY);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(1);

        contentPaint = new Paint();
        contentPaint.setAntiAlias(true);
        contentPaint.setColor(Color.GRAY);
        contentPaint.setStyle(Paint.Style.STROKE);

    }

    private int dp2px(int i) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * i + 0.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(mPadding, mPadding, getMeasuredWidth() - mPadding, getMeasuredHeight() - mPadding);
        canvas.drawRoundRect(rectF, radius, radius, bgPaint);
        canvas.drawRoundRect(rectF, radius, radius, arcPaint);
        float circleX;
        float circleY = getMeasuredHeight() / 2;
        float half = getMeasuredWidth() / 6 / 2;
        for (int i = 1; i < 6; i++) {
            float x = getMeasuredWidth() / 6 * i;
            canvas.drawLine(x, 0, x, getMeasuredHeight(), contentPaint);
        }
        contentPaint.setColor(Color.BLACK);
        contentPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 6; i++) {
            float x = getMeasuredWidth() / 6 * i + half;
            if (isAdd) {//增加
                if (i <= textLength - 1) {
                    canvas.drawCircle(x, getMeasuredHeight() / 2, 16, contentPaint);
                }
            } else {
                if (i <= textLength - 1) {
                    canvas.drawCircle(x, getMeasuredHeight() / 2, 16, contentPaint);
                }
            }
        }
    }
    public interface OnCompleteInputListener{
        void onComplete(String passw);
    }
    private OnCompleteInputListener mListener;

    public OnCompleteInputListener getmListener() {
        return mListener;
    }

    public void setmListener(OnCompleteInputListener mListener) {
        this.mListener = mListener;
    }
}
