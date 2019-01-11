package com.fansonq.shapebuttonlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * @author Created by：Fanson
 * Created Time: 2018/5/17 11:29
 * Describe：自定义Shape按钮（带Ripple效果）
 */

public class ShapeButton extends AppCompatButton {

    private static final String TAG = "MyShapeButton";

    /**
     * 是否开启Ripple效果
     */
    private boolean mOpenRipple;

    /**
     * 边框线宽度
     */
    private int mStrokeWidth;

    /**
     * 边框线颜色
     */
    private int mStrokeColor;

    /**
     * 矩形四个角的圆角半径
     */
    private int mFourCornerRadius;

    /**
     * 设置矩形四个角的半径，例{0, 0, 20, 20, 40, 40, 60, 60}
     */
    private float[] mCornerRadii = null;

    /**
     * 矩形四个角的圆角半径
     */
    private int mLeftTopRadius, mRightTopRadius, mRightBottomRadius, mLeftBottomRadius;

    /**
     * 背景颜色
     */
    private int mBgDefaultColor ;

    /**
     * 点击按钮时的背景颜色
     */
    private int mBgPressColor ;

    /**
     * Ripple效果的颜色
     */
    private int mBgRippleColor ;

    /**
     * 文字颜色
     */
    private int mTextColor;

    /**
     * 每次绘制Ripple的半径（刷新速率）
     */
    private int mDrawRippleRadius;

    private MyRippleDrawable mRippleDrawable;

    public ShapeButton(Context context) {
        super(context);
        init(context, null);
    }

    public ShapeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Log.d(TAG, "init");
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyShapeButton);
            mBgDefaultColor = typedArray.getColor(R.styleable.MyShapeButton_bgDefaultColor, Color.WHITE);
            mBgPressColor = typedArray.getColor(R.styleable.MyShapeButton_bgPressColor, Color.LTGRAY);
            mBgRippleColor = typedArray.getColor(R.styleable.MyShapeButton_bgRippleColor, Color.DKGRAY);
            mTextColor = typedArray.getColor(R.styleable.MyShapeButton_textColor, Color.BLACK);
            mDrawRippleRadius = typedArray.getInt(R.styleable.MyShapeButton_drawRippleRadius, 12);
            mOpenRipple = typedArray.getBoolean(R.styleable.MyShapeButton_openRipple, true);
            mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.MyShapeButton_strokeWidth, 0);
            mStrokeColor = typedArray.getColor(R.styleable.MyShapeButton_strokeColor, Color.GRAY);
            mFourCornerRadius = typedArray.getDimensionPixelSize(R.styleable.MyShapeButton_fourCornerRadius, 0);
            mLeftTopRadius = typedArray.getDimensionPixelSize(R.styleable.MyShapeButton_leftTopRadius, 0);
            mRightTopRadius = typedArray.getDimensionPixelSize(R.styleable.MyShapeButton_rightTopRadius, 0);
            mRightBottomRadius = typedArray.getDimensionPixelSize(R.styleable.MyShapeButton_rightBottomRadius, 0);
            mLeftBottomRadius = typedArray.getDimensionPixelSize(R.styleable.MyShapeButton_leftBottomRadius, 0);
            typedArray.recycle();
        }
        mRippleDrawable = new MyRippleDrawable();
        //设置刷新接口
        mRippleDrawable.setCallback(this);
        setBackgroundDrawable(null);

        //设置按钮的 文字颜色
        setTextColor(mTextColor);
        //设置文字能在按钮里居中显示
        setPadding(1, 1, 1, 1);
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeight, int oldWidth, int oldHeight) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight);
        Log.d(TAG, "onSizeChanged");
        //设置drawable绘制区域
        mRippleDrawable.setBounds(0, 0, getWidth(), getHeight());
        mRippleDrawable.setWidthAndHeight(getWidth(), getHeight());
        mRippleDrawable.setOpenRipple(mOpenRipple);
        mRippleDrawable.setBgDefaultColor(mBgDefaultColor);
        mRippleDrawable.setBgPressColor(mBgPressColor);
        mRippleDrawable.setFourCornerRadius(mFourCornerRadius);
        mRippleDrawable.setEveryCornerRadius(mLeftTopRadius, mRightTopRadius, mRightBottomRadius, mLeftBottomRadius);
        if (mOpenRipple) {
            mRippleDrawable.setRippleColor(mBgRippleColor);
            mRippleDrawable.setDrawRippleRadius(mDrawRippleRadius);
        } else {
            mRippleDrawable.setMyStroke(mStrokeWidth, mStrokeColor);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
        int widthMeasure = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMeasure = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        /*
         * 依据specMode的值，（MeasureSpec有3种模式分别是UNSPECIFIED, EXACTLY和AT_MOST）
         * 如果是AT_MOST，specSize 代表的是最大可获得的空间；
         * 如果是EXACTLY，specSize 代表的是精确的尺寸；
         * 如果是UNSPECIFIED，对于控件尺寸来说，没有任何参义。
         */

        int widthSizeDefault = 90;
        int heightSizeDefault = 45;
        if (widthMeasure == MeasureSpec.AT_MOST || widthMeasure == MeasureSpec.EXACTLY) {
            widthSizeDefault = widthSize;
        }

        if (heightMeasure == MeasureSpec.AT_MOST || heightMeasure == MeasureSpec.EXACTLY) {
            heightSizeDefault = heightSize;
        }

        setMeasuredDimension(widthSizeDefault, heightSizeDefault);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        if (mRippleDrawable != null) {
            mRippleDrawable.draw(canvas);
        }
        if (canvas != null) {
            super.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        if (this.isClickable()) {
            mRippleDrawable.setTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 验证 drawable
     *
     * @param drawable 图片
     * @return true/false
     */
    @Override
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == mRippleDrawable || super.verifyDrawable(drawable);
    }

    /**
     * 设置按钮圆角半径
     *
     * @param cornerRadius 圆角半径
     */
    public ShapeButton setCircleRadius(int cornerRadius) {
        mFourCornerRadius = cornerRadius;
        mRippleDrawable.setCircleRadius(cornerRadius);
        return this;
    }

    /**
     * 设置矩形四个角的圆角半径（左上，右上，右下，左下。八个参数）
     *
     * @param leftTop     左上
     * @param rightTop    右上
     * @param rightBottom 右下
     * @param leftBottom  左下
     */
    private void initRectFCornerRadius(int leftTop, int rightTop, int rightBottom, int leftBottom) {
        setCornerRadii(new float[]{leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom});
    }

    /**
     * 设置矩形四个角的半径（左上，右上，右下，左下。八个参数）
     * 例{0, 0, 20, 20, 40, 40, 60, 60}
     *
     * @param cornerRadii 四个角的半径
     */
    public void setCornerRadii(float[] cornerRadii) {
        mRippleDrawable.setCornerRadii(cornerRadii);
    }

    /**
     * 设置四个角的半径
     * @param radius 四个角的半径
     */
    public void setFourCornerRadius(float radius){
        mRippleDrawable.setFourCornerRadius(radius);
    }


    /**
     * 设置按下时的背景颜色
     *
     * @param pressColor 颜色（int）
     */
    public void setBgPressColor(int pressColor) {
        mBgPressColor = pressColor;
        mRippleDrawable.setBgPressColor(pressColor);
    }

    /**
     * 设置默认的背景颜色
     *
     * @param bgDefaultColor 颜色（int）
     */
    public void setBgDefaultColor(int bgDefaultColor) {
        mBgDefaultColor = bgDefaultColor;
        mRippleDrawable.setBgDefaultColor(bgDefaultColor);
    }

    /**
     * 设置Ripple的颜色
     *
     * @param rippleColor 颜色（int）
     */
    public void setRippleColor(int rippleColor) {
        mBgRippleColor = rippleColor;
        mRippleDrawable.setRippleColor(rippleColor);
    }

    /**
     * 设置边框的颜色
     *
     * @param strokeColor 边框颜色（int）
     */
    public void setStrokeColor(int strokeColor) {
        mStrokeColor = strokeColor;
        mRippleDrawable.setMyStroke(mStrokeWidth,strokeColor);
    }

}

