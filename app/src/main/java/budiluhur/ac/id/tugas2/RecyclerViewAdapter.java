package budiluhur.ac.id.tugas2;

/**
 * Created by ipin on 10/2/2018.
 */

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

/**
 * Created by User on 1/1/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> size = new ArrayList<>();
    private ArrayList<Integer> image = new ArrayList<>();
    private String from;
    private Context mContext;
    private int width, height;

    public RecyclerViewAdapter(Context context, ArrayList<String> name, ArrayList<String> size, ArrayList<Integer> image, String from) {
        this.name = name;
        this.size = size;
        this.image = image;
        this.from = from;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_content_fragment, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textTitle.setText(name.get(position));

        if (from.equals("movie")) {
            holder.textPrice.setVisibility(View.VISIBLE);
            holder.textPrice.setText(size.get(position));
            holder.textPrice.setTextColor(ContextCompat.getColor(mContext, R.color.top3));
            holder.textSize.setVisibility(View.INVISIBLE);
            width = 60; height = 100;
        }
        else {
            holder.textSize.setVisibility(View.VISIBLE);
            holder.textSize.setText(size.get(position));
            holder.textPrice.setVisibility(View.INVISIBLE);
            width = 60; height = 60;
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(2*width,2*height);
        holder.itemImage.setLayoutParams(layoutParams);
        holder.itemImage.setImageResource(image.get(position));
    }

    @Override
    public int getItemCount() {
        return image.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView textTitle, textSize, textPrice;
        protected ImageView itemImage;

        public ViewHolder(View view) {
            super(view);
            this.textTitle = (TextView) view.findViewById(R.id.nameTextView);
            this.textSize = (TextView) view.findViewById(R.id.sizeTextView);
            this.textPrice = (TextView) view.findViewById(R.id.PriceTextView);
            this.itemImage = (ImageView) view.findViewById(R.id.imageContentSlider);
        }
    }
}