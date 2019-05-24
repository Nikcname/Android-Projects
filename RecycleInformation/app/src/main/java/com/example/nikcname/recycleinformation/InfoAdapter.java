package com.example.nikcname.recycleinformation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<Student> students;
    private Click clickListener;

    InfoAdapter(List<Student> students) {
        this.students = students;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView textViewName;
        TextView textViewSurname;
        ImageView imageViewPhoto;

        InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewSurname = itemView.findViewById(R.id.textViewSurname);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            clickListener.onItemLongClick(getAdapterPosition(), view);
            return true;
        }
    }

    @NonNull
    @Override
    public InfoAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View elementOfRecView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rec_info_item, viewGroup, false);

        return new InfoViewHolder(elementOfRecView);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoViewHolder infoViewHolder, int i) {

        infoViewHolder.textViewName.setText(students.get(i).getaName());
        infoViewHolder.textViewSurname.setText(students.get(i).getaSurname());
        Picasso.get()
                .load("https://source.unsplash.com/random")
                .resize(100, 100)
                .centerCrop()
                .into(infoViewHolder.imageViewPhoto);

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    interface Click{
        void onItemClick(int position);
        void onItemLongClick(int position, View view);
    }

    void setOnItemClickListener(Click clickListener){
        this.clickListener = clickListener;
    }

}
