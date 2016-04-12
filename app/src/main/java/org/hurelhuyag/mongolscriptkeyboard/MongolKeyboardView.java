package org.hurelhuyag.mongolscriptkeyboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by hurlee on 4/3/16
 */
public class MongolKeyboardView extends KeyboardView {

    Field mDrawPending;
    Field mBuffer;
    Field mKeyboardChanged;
    Method onBufferDraw;
    Typeface font;
    Paint paint;

    public MongolKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public MongolKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public MongolKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        font = Typeface.createFromAsset(context.getAssets(), "fonts/MT-Tolbo-Regular.ttf");
        paint = new Paint();
        paint.setTypeface(font);
        try {
            mDrawPending = KeyboardView.class.getDeclaredField("mDrawPending");
            mBuffer = KeyboardView.class.getDeclaredField("mBuffer");
            mKeyboardChanged = KeyboardView.class.getDeclaredField("mKeyboardChanged");
            onBufferDraw = KeyboardView.class.getDeclaredMethod("onBufferDraw");
            mDrawPending.setAccessible(true);
            mBuffer.setAccessible(true);
            mKeyboardChanged.setAccessible(true);
            onBufferDraw.setAccessible(true);
        } catch (Exception e) {
            Log.e("MongolKeyboardView", "init", e);
        }
    }

    @Override
    protected boolean onLongPress(Keyboard.Key popupKey) {
        if (popupKey.codes.length == 2){
            getOnKeyboardActionListener().onKey(popupKey.codes[1], null);
            return true;
        }else{
            return super.onLongPress(popupKey);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        try {
            onDrawInternal(canvas);
        } catch (Exception e) {
            Log.e("MongolKeyboardView", "onDraw", e);
        }
    }

    public void onDrawInternal(Canvas canvas) throws Exception{
        Bitmap mBuffer = (Bitmap) this.mBuffer.get(this);
        if (mDrawPending.getBoolean(this) || mBuffer == null || mKeyboardChanged.getBoolean(this)) {
            onBufferDraw.invoke(this);
        }else
        canvas.drawBitmap(mBuffer, 0, 0, paint);
    }
}
