package budiluhur.ac.id.tugas2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;
    private String from;
    private int width, height;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList, String from) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.from = from;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_for_you, null);

        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);

        holder.textTitle.setText(singleItem.getName());
        if (from.equals("movie")) {
            holder.textPrice.setVisibility(View.VISIBLE);
            holder.textPrice.setText(singleItem.getSize());
            holder.textPrice.setTextColor(ContextCompat.getColor(mContext, R.color.top3));
            holder.textSize.setVisibility(View.GONE);
            width = 83; height = 123;
        }
        else {
            holder.textSize.setVisibility(View.VISIBLE);
            holder.textSize.setText(singleItem.getSize());
            holder.textPrice.setVisibility(View.GONE);
            width = 83; height = 83;
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(2*width,2*height);
        holder.itemImage.setLayoutParams(layoutParams);
        holder.itemImage.setImageResource(singleItem.getImage());
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView textTitle, textSize, textPrice;

        protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.textTitle = (TextView) view.findViewById(R.id.nameTextView);
            this.textSize = (TextView) view.findViewById(R.id.sizeTextView);
            this.textPrice = (TextView) view.findViewById(R.id.PriceTextView);
            this.itemImage = (ImageView) view.findViewById(R.id.imageContentSlider);
        }
    }

}
