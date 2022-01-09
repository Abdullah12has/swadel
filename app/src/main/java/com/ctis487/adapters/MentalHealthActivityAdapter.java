package com.ctis487.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ctis487.motherzilla.MentalHealthItems;
import com.ctis487.motherzilla.R;

import java.util.ArrayList;

public class MentalHealthActivityAdapter extends RecyclerView.Adapter<MentalHealthActivityAdapter.myViewHolder> {

    ArrayList<MentalHealthItems> dataholder;

    public MentalHealthActivityAdapter(ArrayList<MentalHealthItems> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_profiles, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final MentalHealthItems items = dataholder.get(position);

        holder.tvSpecialty.setText(items.getSpeciality());
        holder.tvAge.setText(items.getAge());
        holder.tvPersonName.setText(items.getName());
        holder.tvPersonDescription.setText(items.getDescription());

        Bitmap bitmap = BitmapFactory.decodeByteArray(items.getImg(), 0, items.getImg().length);
        holder.imgResource.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return  dataholder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imgResource;
        TextView tvAge, tvSpecialty, tvPersonName, tvPersonDescription;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgResource = (ImageView)itemView.findViewById(R.id.imgResource);
            tvAge = (TextView)itemView.findViewById(R.id.tvAge);
            tvSpecialty = (TextView)itemView.findViewById(R.id.tvSpecialty);
            tvPersonName = (TextView)itemView.findViewById(R.id.tvPersonName);
            tvPersonDescription = (TextView)itemView.findViewById(R.id.tvPersonDescription);
        }
    }

}
