package budiluhur.ac.id.tugas2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;
    private String from;

    public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModel> dataList, String from) {
        this.dataList = dataList;
        this.mContext = context;
        this.from = from;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_slide_fragment, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();
        final String sectionMore = dataList.get(i).getHeaderMore();

        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(sectionName);
        itemRowHolder.itemMore.setText(sectionMore);

        int color;
        if (from.equals("home"))
            color = R.color.top;
        else if (from.equals("games"))
            color = R.color.top2;
        else
            color = R.color.top3;

        itemRowHolder.itemMore.setTextColor(ContextCompat.getColor(mContext, color));

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, from);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

        SnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(itemRowHolder.recycler_view_list);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle, itemMore;
        protected RecyclerView recycler_view_list;

        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.home_for_you_title);
            this.itemMore = (TextView) view.findViewById(R.id.home_for_you_opsi);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recyclerSlide);
        }
    }
}
