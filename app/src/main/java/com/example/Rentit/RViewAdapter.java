package com.example.Rentit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.RViewHolderClass> {

    ArrayList<DatabaseModel> objectDatabaseModellist;

    public RViewAdapter(ArrayList<DatabaseModel> objectDatabaseModellist) {
        this.objectDatabaseModellist = objectDatabaseModellist;
    }

    @NonNull
    @Override
    public RViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RViewHolderClass(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.singlepropertydetail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolderClass holder, int position) {
        DatabaseModel objectDatabaseModel = objectDatabaseModellist.get(position);
        holder.Ownername.setText(objectDatabaseModel.getFullname());
        holder.Ownercontact.setText(objectDatabaseModel.getContactnumber());
        holder.Propertyimage.setImageBitmap(objectDatabaseModel.getPropertyimage());
        holder.Propertylocation.setText(objectDatabaseModel.getLocation());
        holder.Propertydecsription.setText(objectDatabaseModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return objectDatabaseModellist.size();
    }

    public static class RViewHolderClass extends RecyclerView.ViewHolder{

        TextView Ownername, Ownercontact, Propertylocation, Propertydecsription;
        ImageView Propertyimage;

        public RViewHolderClass(@NonNull View itemView) {
            super(itemView);

            Ownername = (TextView) itemView.findViewById(R.id.propertyowner);
            Ownercontact = (TextView) itemView.findViewById(R.id.propertyownercontact);
            Propertyimage = (ImageView) itemView.findViewById(R.id.propertyphoto);
            Propertylocation = (TextView) itemView.findViewById(R.id.propertylocation);
            Propertydecsription = (TextView) itemView.findViewById(R.id.propertydescription);
        }
    }
}
