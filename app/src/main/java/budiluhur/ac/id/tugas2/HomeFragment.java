package budiluhur.ac.id.tugas2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import budiluhur.ac.id.tugas2.Home.Home1;
import budiluhur.ac.id.tugas2.Home.Home2;
import budiluhur.ac.id.tugas2.Home.Home3;

/**
 * Created by ipin on 9/27/2018.
 */

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcon = {
            R.drawable.ic_explore_black_24dp,
            R.drawable.ic_insert_chart_black_24dp,
            R.drawable.ic_group_work_black_24dp
    };
    private String[] tabTitle = {
            "For You",
            "Top Charts",
            "Categories"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabs_fragment, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.content_view);
        tabLayout = (TabLayout) rootView.findViewById(R.id.content_tabs);

        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        for (int i = 0; i < 3; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            TextView text = (TextView) tab.findViewById(R.id.custom_text);
            ImageView icon = (ImageView) tab.findViewById(R.id.custom_icon);
            text.setText(tabTitle[i]);
            icon.setImageResource(tabIcon[i]);

            if(i == 0) {
                icon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.top), android.graphics.PorterDuff.Mode.SRC_IN);
                text.setTextColor(ContextCompat.getColor(getActivity(), R.color.top));
            } else {
                icon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.menu), android.graphics.PorterDuff.Mode.SRC_IN);
                text.setTextColor(ContextCompat.getColor(getActivity(), R.color.menu));
            }

            tabLayout.getTabAt(i).setCustomView(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int c = tab.getPosition();
                SetOnSelectView(tabLayout,c);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int c = tab.getPosition();
                SetUnSelectView(tabLayout,c);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    public void SetOnSelectView(TabLayout tabLayout,int position)
    {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        View selected = tab.getCustomView();
        TextView text = (TextView) selected.findViewById(R.id.custom_text);
        ImageView icon = (ImageView) selected.findViewById(R.id.custom_icon);
        icon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.top), android.graphics.PorterDuff.Mode.SRC_IN);
        text.setTextColor(ContextCompat.getColor(getActivity(), R.color.top));
    }

    public void SetUnSelectView(TabLayout tabLayout,int position)
    {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        View selected = tab.getCustomView();
        TextView text = (TextView) selected.findViewById(R.id.custom_text);
        ImageView icon = (ImageView) selected.findViewById(R.id.custom_icon);
        icon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.menu), android.graphics.PorterDuff.Mode.SRC_IN);
        text.setTextColor(ContextCompat.getColor(getActivity(), R.color.menu));
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("from", "home");
        Home1 home1 = new Home1();
        home1.setArguments(bundle);

        Home2 home2 = new Home2();
        home2.setArguments(bundle);

        Home3 home3 = new Home3();
        home3.setArguments(bundle);

        adapter.addFrag(home1, "For You");
        adapter.addFrag(home2, "Top Charts");
        adapter.addFrag(home3, "Categories");
        viewPager.setAdapter(adapter);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
