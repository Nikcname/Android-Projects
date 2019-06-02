package com.shirinov.joshgun.bubblegame2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};
    OnItemClick onItemClick;

    public MyAdapter() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvLevel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLevel = itemView.findViewById(R.id.textViewLevel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClicked(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_layout, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tvLevel.setText(levels[i]);
    }

    @Override
    public int getItemCount() {
        return levels.length;
    }

    public interface OnItemClick{
        void onClicked(int level);
    }

    public void setClickListener(OnItemClick onItemClick){

        this.onItemClick = onItemClick;

    }
}
