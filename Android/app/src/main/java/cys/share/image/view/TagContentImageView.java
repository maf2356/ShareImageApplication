//package cys.share.image.view;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.ImageView;
//
//import cys.share.image.R;
//
///**
// * Created by Administrator on 2016/10/28.
// */
//
//public class TagContentImageView extends ImageView{
//    private float ratio;
//
//    public TagContentImageView(Context context) {
//        super(context);
//    }
//
//    public TagContentImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        getAttrs(context, attrs);
//    }
//
//    public TagContentImageView(Context context, AttributeSet attrs, int style) {
//        super(context, attrs, style);
//        getAttrs(context, attrs);
//    }
//
//    private void getAttrs(Context context, AttributeSet attrs) {
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.tagcontentimageview);
//        ratio = ta.getFloat(R.styleable.tagcontentimageview_ratio, 1);
//        ta.recycle();
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = View.MeasureSpec.getSize(widthMeasureSpec);
//        int height = (int) ((float)width * ratio);
//        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
//    }
//
//    public void setRatio(float ratio){
//        this.ratio = ratio;
//    }
//}
