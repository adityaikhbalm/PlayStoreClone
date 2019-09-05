package budiluhur.ac.id.tugas2;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * Created by ipin on 9/30/2018.
 */

public class ViewPagerCustomDuration extends ViewPager {

    public ViewPagerCustomDuration(Context context) {
        super(context);
        postInitViewPager();
    }

    public ViewPagerCustomDuration(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    private ScrollerCustomDuration mScroller = null;

    private void postInitViewPager() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ScrollerCustomDuration(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
