package com.example.lhh.pomodoro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LHH on 2016/4/5.
 */
public class CircleProgressBar extends View {
    private int maxProgress = 100;
    private int progress = 30;
    private int progressStokeWidth = 4;
    RectF oval;
    Paint paint;
    public CircleProgressBar(Context context, AttributeSet attrs){
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        if(width != height){
            int min = Math.min(width, height);
            width = min;
            height = min;
        }
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        canvas.drawColor(Color.TRANSPARENT);
        paint.setStrokeWidth(progressStokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        oval.left = progressStokeWidth/2;
        oval.top = progressStokeWidth/2;
        oval.right = width - progressStokeWidth/2;
        oval.bottom = height - progressStokeWidth/2;
        canvas.drawArc(oval, -90, 360, false, paint);
        paint.setColor(Color.rgb(0x57, 0x87, 0xb6));
        canvas.drawArc(oval, -90, ((float)progress/maxProgress) * 360, false, paint);
        paint.setStrokeWidth(1);
        String text = progress + "%";
        int textHeight = height/4;
        paint.setTextSize(textHeight);
        int textWidth = (int) paint.measureText(text, 0, text.length());
        canvas.drawText(text, width / 2  - textWidth /2, height /2 +textHeight/2, paint);
    }
    public int getMaxProgress(){
        return maxProgress;
    }
    public void setMaxProgress(int maxProgress){
        this.maxProgress = maxProgress;
    }
    public void setProgress(int Progress){
        this.progress = Progress;
        this.invalidate();
    }
    public void setProgressNoInUiThread(int progress){
        this.progress = progress;
        this.postInvalidate();
    }
}
