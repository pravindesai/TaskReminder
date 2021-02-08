package com.example.taskreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    ArrayList<Integer> mImgArrayList;
    onImgeClickListner monimgeClickListner;

    public ImageAdapter(ArrayList<Integer> imgList) {
            this.mImgArrayList = imgList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.imagecontainer,null);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int imgId = mImgArrayList.get(position);
        holder.imageView.setImageResource(imgId);

    }

    @Override
    public int getItemCount() {
        return mImgArrayList.size();
    }

     class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(monimgeClickListner != null){
                        monimgeClickListner.onImageClicked(ImageAdapter.this, mImgArrayList.get(getAdapterPosition()), getAdapterPosition(), itemView);
                    }
                }
            });

        }
    }

    public interface onImgeClickListner{
        public void onImageClicked(ImageAdapter imageAdapter, int imgId, int position, View view);
    }

    public void setOnImgeClickListner(onImgeClickListner onImgeClickListner){
        this.monimgeClickListner = onImgeClickListner;
    }
}