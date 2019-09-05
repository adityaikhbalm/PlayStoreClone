package budiluhur.ac.id.tugas2.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import budiluhur.ac.id.tugas2.Data;
import budiluhur.ac.id.tugas2.MainActivity;
import budiluhur.ac.id.tugas2.R;
import budiluhur.ac.id.tugas2.RecyclerViewAdapter;

/**
 * Created by ipin on 9/29/2018.
 */

public class Home3 extends Fragment {

    private RecyclerView mRecyclerView;
    private int sy, oldsy;
    private String from;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> size = new ArrayList<>();
    private ArrayList<Integer> image = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerTopContent);

        NestedScrollView ns = (NestedScrollView) rootView.findViewById(R.id.scrollTopContent);

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
        getData();

        mRecyclerView.setHasFixedSize(true);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), name, size, image, from);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    private void getData() {
        Data dt = new Data();
        String nm[] = dt.getName();
        String sz[] = dt.getSize();
        int img[] = dt.getSlider();

        if (from.equals("home")) {
            for (int i = 0; i <= 10; i++) {
                name.add(nm[i]);
                size.add(sz[i]);
                image.add(img[i]);
            }
        }
        else if (from.equals("games")) {
            for (int i = 11; i <= 17; i++) {
                name.add(nm[i]);
                size.add(sz[i]);
                image.add(img[i]);
            }
        }
        else {
            for (int i = 18; i <= 24; i++) {
                name.add(nm[i]);
                size.add(sz[i]);
                image.add(img[i]);
            }
        }
    }
}
