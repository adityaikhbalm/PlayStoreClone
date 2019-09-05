package budiluhur.ac.id.tugas2.Home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

import budiluhur.ac.id.tugas2.Data;
import budiluhur.ac.id.tugas2.MainActivity;
import budiluhur.ac.id.tugas2.R;
import budiluhur.ac.id.tugas2.RecyclerViewDataAdapter;
import budiluhur.ac.id.tugas2.SectionDataModel;
import budiluhur.ac.id.tugas2.SingleItemModel;
import budiluhur.ac.id.tugas2.SpacesItemDecoration;
import budiluhur.ac.id.tugas2.ViewPagerCustomDuration;

/**
 * Created by ipin on 9/29/2018.
 */

public class Home1 extends Fragment {

    private ViewPagerCustomDuration viewPager;
    private int currentPage = 0, sy, oldsy;
    private RecyclerView mRecyclerView;
    private ArrayList<SectionDataModel> allSampleData;
    private String[] title = {
            "Previously installed apps",
            "Recomended for You",
            "New Games",
            "Popular Apps & Games",
            "Recomended for You",
            "Top Rated Games",
            "Trending Games",
            "Populer Games",
            "New Release",
            "Recomended for You",
            "Populer Film",
            "With Subtitle"
    };
    private String from;
    private Handler handler;
    private Runnable update;
    private int i, x, batas, jumlah, jumlah2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_fragment, container, false);
        viewPager = (ViewPagerCustomDuration) rootView.findViewById(R.id.content_view);
        viewPager.setScrollDurationFactor(7);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerContent);
        NestedScrollView ns = (NestedScrollView) rootView.findViewById(R.id.scroll);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (sy > oldsy) {
                        ((MainActivity) getActivity()).hideAppBar();
                    }

                    if (sy < oldsy) {
                        ((MainActivity) getActivity()).showAppBar();
                    }
                }
                return false;
            }
        });

        ns.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                sy = scrollY;
                oldsy = oldScrollY;
            }
        });

        Bundle bundle = this.getArguments();
        from = bundle.getString("from", "home");

        handler = new Handler();
        update = new Runnable() {
            public void run() {
                handler.postDelayed(this, 5000);

                if (currentPage == 3) {
                    currentPage = 0;
                } else {
                    viewPager.setCurrentItem(currentPage++, true);
                }
            }
        };

        if(from.equals("home")) {
            SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(getActivity());
            viewPager.setAdapter(sliderPagerAdapter);
            handler.postDelayed(update, 0);
        }
        else {
            handler.removeCallbacks(update);
        }

        allSampleData = new ArrayList<SectionDataModel>();
        createDummyData();
        mRecyclerView.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(getActivity(), allSampleData, from);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(update);
    }

    public void createDummyData() {
        Data dt = new Data();
        String name[] = dt.getName();
        String size[] = dt.getSize();
        int slider[] = dt.getSlider();

        Random r = new Random();

        if (from.equals("home")) {
            i = 0; batas = 10; jumlah = 17; jumlah2 = 0;
        }
        else if (from.equals("games")) {
            i = 4; batas = 7; jumlah = 17; jumlah2 = 11;
        }
        else {
            i = 8; batas = 7; jumlah = 24; jumlah2 = 18;
        }

        for(i = 0; i <= 3; i++) {
            batas = 10;
            x = r.nextInt(jumlah - jumlah2) + jumlah2;

            if(i == 2) {
                x = 11;
                batas = 7;
            }

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle(title[i]);
            dm.setHeaderMore("MORE");

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j < batas; j++) {
                singleItem.add(new SingleItemModel(name[x], size[x], slider[x]));
                x++;
                if (x == 18) {
                    if (jumlah2 == 0)
                        x = 0;
                    else
                        x = 11;

                }
                if (x == 24) {
                    x = 18;
                }
            }

            dm.setAllItemsInSection(singleItem);
            allSampleData.add(dm);
        }
    }

    public class SliderPagerAdapter extends PagerAdapter {

        Context context;
        LayoutInflater layoutInflater;
        private int[] slider = {
                R.drawable.avenger,
                R.drawable.dragon_nest,
                R.drawable.mobile_legends
        };

        public SliderPagerAdapter(Context context) {
            this.context = context;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return slider.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View item = layoutInflater.inflate(R.layout.slider_fragment, container, false);
            ImageView image = (ImageView) item.findViewById(R.id.imgSlider);
            image.setImageResource(slider[position]);

            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
