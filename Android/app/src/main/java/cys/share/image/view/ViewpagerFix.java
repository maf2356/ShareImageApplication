package cys.share.image.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ViewpagerFix extends ViewPager{


    public ViewpagerFix(Context context) {
        super(context);
    }

    public ViewpagerFix(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        }catch (Exception e){
            return  false;
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        }catch (Exception e){
            return false;
        }
    }
}
