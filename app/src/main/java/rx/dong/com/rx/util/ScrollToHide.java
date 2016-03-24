package rx.dong.com.rx.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by SkyEyesStion on 2016/3/18.
 */
public class ScrollToHide extends RecyclerView.OnScrollListener {
    //用来标记是否正在向最后一个滑动，既是否向右滑动或向下滑动
    boolean isSlidingToLast = false;
    private LinearLayoutManager mLinearLayoutManager;
    private int totalScrollDistance;
    private boolean isShow = true;
    private static final int SCROLL_DISTANCE = 50;
    private ScrollToHideListener scrollToHideListener;

    public ScrollToHide(LinearLayoutManager mLinearLayoutManager, ScrollToHideListener
            scrollToHideListener) {
        //construct
        this.mLinearLayoutManager = mLinearLayoutManager;
        this.scrollToHideListener = scrollToHideListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView
// .getLayoutManager();
        // 当不滚动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的ItemPosition
            int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = mLinearLayoutManager.getItemCount();
//                    System.out.println("xxxxxx" + lastVisibleItem);
//                    System.out.println("xxxxxxsss" + totalItemCount);
            // 判断是否滚动到底部，并且是向右滚动
            if (lastVisibleItem == (totalItemCount - 1) &&
                    !isShow) {// (totalItemCount - 1) && isSlidingToLast
                //加载更多功能的代码
//                        Ln.e("howes right=" + manager.findLastCompletelyVisibleItemPosition());

            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisableItem = ((LinearLayoutManager) recyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();
        //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
//                if (dx > 0) {
//                    //大于0表示，正在向右滚动
//                    isSlidingToLast = true;
//                } else {
//                    //小于等于0 表示停止或向左滚动
//                    isSlidingToLast = false;
//                }
        if (firstVisableItem == 0) {
            return;
        }
        if ((dy > 0 && isShow) || (dy < 0 && !isShow)) {
            totalScrollDistance += dy;
        }
        if (totalScrollDistance > SCROLL_DISTANCE && isShow) {
            scrollToHideListener.hideView();
            isShow = false;
            totalScrollDistance = 0;
        } else if (totalScrollDistance < -SCROLL_DISTANCE && !isShow) {
//            scrollToHideListener.showView();
            isShow = true;
            totalScrollDistance = 0;
        }else {
            scrollToHideListener.showView();

        }

    }


}
