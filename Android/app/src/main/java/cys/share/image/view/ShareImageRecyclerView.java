package cys.share.image.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import cys.share.image.auxiliary.ShareImageAuxiliaryTool;

/**
 * Created by Administrator on 2016/11/5.
 */
public class ShareImageRecyclerView extends RecyclerView{

    int location[] = new int[2];
    public ShareImageRecyclerView(Context context) {
        super(context);
    }

    public ShareImageRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShareImageRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    boolean isTop = false;
    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        mDy = dy;
//        if(!this.canScrollVertically(-1)){
//            ShareImageAuxiliaryTool.log("到顶部了");
//            isTop = true;
//        }else{
//            isTop = false;
//        }
    }

    float downY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_DOWN){
            downY =  e.getRawY();
            ShareImageAuxiliaryTool.log("DWOOWN距离:"+downY+"");
        }else if(e.getAction() == MotionEvent.ACTION_MOVE&&(!this.canScrollVertically(-1))&&mDy<0){
            if(downY==0){
                downY = e.getRawY();
            }else {
                float flipDistance = e.getRawY()-downY;
                if(onScrollListener!=null && flipDistance > 350){

                }
                ShareImageAuxiliaryTool.log("滑动距离:" + (e.getRawY() - downY) + "");
            }

        }else if(e.getAction() == MotionEvent.ACTION_UP){
            downY = 0;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        switch (state) {
            case RecyclerView.SCROLL_STATE_DRAGGING:
                //正在滑动，手未放开
//                        Logger.i("SCROLL_STATE_DRAGGING");
                break;
            case RecyclerView.SCROLL_STATE_IDLE:
                //手放开
//                        Logger.i("SCROLL_STATE_IDLE");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                //检验发现SCROLL_STATE_SETTLING为见底往上拉或顶部往上拉状态
                /**
                 * 注意，请保证第一页填满视图，否则无法滑动导致mDy一直为0.
                 * 若将条件设为 mDy >= 0,会导致下拉刷新触发loadMore().
                 */
                if (mDy > 0 && getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
                    int lastPos = linearLayoutManager.findLastVisibleItemPosition();
                    int itemCount = getAdapter().getItemCount();
                    if (lastPos == itemCount - 1)
                        loadMore();
                }
                break;
        }

    }

    private int mDy;
    public OnLoadMoreListener mOnLoadMoreListener;

    public OnScrollListener onScrollListener;

    public interface OnLoadMoreListener {
        void loadMore();
    }

    public interface OnScrollListener{
        void loadMore();

        void refresh();
    }

    protected void loadMore() {
        if (mOnLoadMoreListener != null)
            mOnLoadMoreListener.loadMore();
    }
}
