package com.ctis487.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ctis487.motherzilla.R;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
    Intent values;

    public MyRecyclerViewAdapter(Context context, Intent intent) {
        this.context = context;
        this.values = intent;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_profiles , parent, false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Profile profile = Commons.pdata.get(position);
        holder.name.setText(profile.getName());
        holder.age.setText(profile.getAge()+"");
        holder.speciality.setText(profile.getSpeciality());
//        holder.description.setText(profile.getDescription());

        Bitmap bitmap = BitmapFactory.decodeByteArray(profile.getImage(), 0, profile.getImage().length);
        holder.profileImage.setImageBitmap(bitmap);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("profile", profile);
                values.putExtras(bundle);
                context.startActivity(values);
            }
        });
    }
    @Override
    public int getItemCount() {
        return Commons.pdata.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, age, speciality;
        ImageView profileImage;
        ConstraintLayout layout;
        MyViewHolder(View viewItem){
            super(viewItem);
            layout = viewItem.findViewById(R.id.recycler_personality);
            name = viewItem.findViewById(R.id.tvPersonName);
            age = viewItem.findViewById(R.id.tvAge);
            speciality= viewItem.findViewById(R.id.tvSpecialty);
//            description = viewItem.findViewById(R.id.tvPersonDescription);
            profileImage = viewItem.findViewById(R.id.imgprofile);
        }
    }
}

