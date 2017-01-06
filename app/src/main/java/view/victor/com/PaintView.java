package view.victor.com;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import flowfunny.victor.com.R;

/**
 * Created by victor on 2016/1/12.
 */
public class PaintView extends View{

    private Paint paint;
    private Canvas cacheCanvas;
    private Bitmap cachebBitmap;
    private Path path;
    private float cur_x, cur_y;
    private int paintColor = Color.GREEN;
    private int strokeWidth = 10;//画笔粗细
    private int paintWidth = 100;
    private int paintHeight = 100;

    public Bitmap getCachebBitmap() {
        return cachebBitmap;
    }

    public PaintView (Context context){
        super(context, null);
    }
    public PaintView(Context context,AttributeSet attrs) {
        // TODO Auto-generated constructor stub
        this(context, attrs, 0);
    }

    public PaintView (Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PaintView,defStyle, 0);
        paintWidth = (int) attributes.getDimension(R.styleable.PaintView_paintWidth, paintWidth);
        paintHeight = (int) attributes.getDimension(R.styleable.PaintView_paintHeight, paintHeight);
        strokeWidth = (int) attributes.getDimension(R.styleable.PaintView_strokeWidth, strokeWidth);
        paintColor = attributes.getColor(R.styleable.PaintView_paintColor,paintColor);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);//设置画笔粗细
        paint.setStyle(Paint.Style.STROKE);//设置画笔风格
        paint.setColor(paintColor);//设置画笔颜色
        path = new Path();
//        cachebBitmap = Bitmap.createBitmap(mLayoutParams.width, (int)(mLayoutParams.height*0.8), Bitmap.Config.ARGB_8888);
        cachebBitmap = Bitmap.createBitmap(paintWidth,paintHeight, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.drawColor(Color.WHITE);
    }

    public void setPaintColor (int color) {
        paintColor = color;
        paint.setColor(paintColor);
    }

    public void setStrokeWidth (int strokeWidth) {
        this.strokeWidth = strokeWidth;
        paint.setStrokeWidth(strokeWidth);
    }

    public void clear() {
        if (cacheCanvas != null) {
            cacheCanvas.drawPaint(paint);
            paint.setColor(paintColor);
            cacheCanvas.drawColor(Color.WHITE);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        canvas.drawBitmap(cachebBitmap, 0, 0, null);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
        int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
        if (curW >= w && curH >= h) {
            return;
        }

        if (curW < w)
            curW = w;
        if (curH < h)
            curH = h;

        Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
        Canvas newCanvas = new Canvas();
        newCanvas.setBitmap(newBitmap);
        if (cachebBitmap != null) {
            newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
        }
        cachebBitmap = newBitmap;
        cacheCanvas = newCanvas;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                cur_x = x;
                cur_y = y;
                path.moveTo(cur_x, cur_y);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                path.quadTo(cur_x, cur_y, x, y);
                cur_x = x;
                cur_y = y;
                break;
            }

            case MotionEvent.ACTION_UP: {
                cacheCanvas.drawPath(path, paint);
                path.reset();
                break;
            }
        }

        invalidate();

        return true;
    }
}
