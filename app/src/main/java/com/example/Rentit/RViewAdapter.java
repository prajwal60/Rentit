package com.example.Rentit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.RViewHolderClass> {

    ArrayList<DatabaseModel> objectDatabaseModellist;
    Context context;

    public RViewAdapter(ArrayList<DatabaseModel> objectDatabaseModellist, Context context) {
        this.objectDatabaseModellist = objectDatabaseModellist;
        this.context =context;
    }


    @NonNull
    @Override
    public RViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        view=inflater.inflate(R.layout.activity_singlepropertydetail,parent,false);
        final RViewHolderClass holder = new RViewHolderClass(view) ;
        holder.clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DummyPostDetail.class);
                intent.putExtra("ownername",objectDatabaseModellist.get(holder.getAdapterPosition()).getFullname());
                intent.putExtra("ownernumber",objectDatabaseModellist.get(holder.getAdapterPosition()).getContactnumber());
                intent.putExtra("location",objectDatabaseModellist.get(holder.getAdapterPosition()).getLocation());
                intent.putExtra("description",objectDatabaseModellist.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("bookingcode",objectDatabaseModellist.get(holder.getAdapterPosition()).getBooking_code());
                intent.putExtra("image",objectDatabaseModellist.get(holder.getAdapterPosition()).getPropertyimage());

                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolderClass holder, int position) {
        DatabaseModel objectDatabaseModel = objectDatabaseModellist.get(position);
        holder.Ownername.setText(objectDatabaseModel.getFullname());
        holder.Ownercontact.setText(objectDatabaseModel.getContactnumber());
        holder.Propertyimage.setImageBitmap(objectDatabaseModel.getPropertyimage());
        holder.Propertylocation.setText(objectDatabaseModel.getLocation());
        holder.Propertydecsription.setText(objectDatabaseModel.getDescription());
        holder.Booking_code.setText(objectDatabaseModel.getBooking_code());

    }

    @Override
    public int getItemCount() {
        return objectDatabaseModellist.size();
    }

    public static class RViewHolderClass extends RecyclerView.ViewHolder{

        TextView Ownername, Ownercontact, Propertylocation, Propertydecsription, Booking_code;
        Button BookTheRoom;
        ImageView Propertyimage;
        ImageView clicker;

        public RViewHolderClass(@NonNull View itemView) {
            super(itemView);

            clicker = itemView.findViewById(R.id.propertyphoto);
            Ownername = (TextView) itemView.findViewById(R.id.propertyowner);
            Ownercontact = (TextView) itemView.findViewById(R.id.propertyownercontact);
            Propertyimage = (ImageView) itemView.findViewById(R.id.propertyphoto);
            Propertylocation = (TextView) itemView.findViewById(R.id.propertylocation);
            Propertydecsription = (TextView) itemView.findViewById(R.id.propertydescription);
            Booking_code = (TextView) itemView.findViewById(R.id.bookingcode);

        }
    }
}
