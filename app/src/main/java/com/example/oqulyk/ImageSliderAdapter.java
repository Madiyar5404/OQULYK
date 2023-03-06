package com.example.oqulyk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private List<Integer> mImageList;

    public ImageSliderAdapter(List<Integer> imageList) {
        mImageList = imageList;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, parent, false);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.bind(mImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    static class ImageSliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }

        public void bind(int imageResId) {
            mImageView.setImageResource(imageResId);
        }
    }
}
