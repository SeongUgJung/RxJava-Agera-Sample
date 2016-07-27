package com.nobrain.android.agera_sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nobrain.android.agera_sample.R;
import com.nobrain.android.agera_sample.domain.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlickerAdapter extends RecyclerView.Adapter<FlickerAdapter.FlickerViewHolder> {

    private List<Photo> photos;
    private int width;

    public FlickerAdapter(int width) {
        this.width = width;
        photos = new ArrayList<>();
    }

    public void add(Photo photo) {
        photos.add(photo);
    }

    public Photo getItem(int position) {
        return photos.get(position);
    }

    @Override
    public FlickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flicker_image, parent, false);
        FlickerViewHolder flickerViewHolder = new FlickerViewHolder(view);
        ViewGroup.LayoutParams layoutParams = flickerViewHolder.itemView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = width;
        flickerViewHolder.itemView.setLayoutParams(layoutParams);
        return flickerViewHolder;
    }

    @Override
    public void onBindViewHolder(FlickerViewHolder holder, int position) {
        Photo item = getItem(position);
        String farmId = String.valueOf(item.farm());
        String serverId = item.server();
        String id = item.id();
        String secret = item.secret();

        Picasso.with(holder.iv.getContext())
                .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", farmId, serverId, id, secret))
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class FlickerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item)
        ImageView iv;

        public FlickerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
