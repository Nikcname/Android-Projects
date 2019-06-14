package com.forevermore.nikcname.nevermore;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {

    private List<Bitmap> logo;

    public MangaAdapter(List<Bitmap> logo){
        this.logo = logo;
    }

    public class MangaViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewLogo;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewLogo = itemView.findViewById(R.id.image_view_logo);

        }
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manga_item,
                viewGroup, false);

        return new MangaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder mangaViewHolder, int i) {

        mangaViewHolder.imageViewLogo.setImageBitmap(logo.get(i));

    }

    @Override
    public int getItemCount() {
        return logo.size();
    }
}
