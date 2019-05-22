package com.example.nikcname.recycleinformation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<Student> students;
    private Click clickListener;

    InfoAdapter(List<Student> students) {
        this.students = students;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;
        TextView textViewSurname;
        ImageView imageViewPhoto;

        InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewSurname = itemView.findViewById(R.id.textViewSurname);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
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

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    interface Click{
        void onItemClick(int position);
    }

    void setOnItemClickListener(Click clickListener){
        this.clickListener = clickListener;
    }

}
