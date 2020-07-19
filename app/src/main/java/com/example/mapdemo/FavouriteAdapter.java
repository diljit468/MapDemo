package com.example.mapdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapdemo.Model.Favourite;
import com.example.mapdemo.util.SQLHelperClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    Context context;
    private static final String TAG = "FavouriteAdapter";
    private List<Favourite> FavouriteArrayList = new ArrayList<>();
    String dateStr;


    public FavouriteAdapter(Context context, List<Favourite> favouriteArrayList, String date) {
        this.context = context;
        this.FavouriteArrayList=favouriteArrayList;
        this.dateStr=date;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_favourite, null);
        return new FavouriteAdapter.FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, final int position) {
        final Favourite favourite = FavouriteArrayList.get(position);
        //getting the product of the specified position
        holder.tv_latitude.setText(favourite.getLatitude());
        holder.tv_longitude.setText(favourite.getLongitude());
        holder.tv_city.setText(favourite.getCity());
        holder.tv_state.setText(favourite.getState());
        holder.tv_pincode.setText(favourite.getPincode());
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLHelperClass sqlHelperClass = new SQLHelperClass(context);
                sqlHelperClass.deleteOneRow(favourite.getId());
                Intent i = new Intent(context, MapsActivity.class);
                context.startActivity(i);
            }
        });
        holder.tv_date.setText(dateStr);


    }

    @Override
    public int getItemCount() {
        return FavouriteArrayList.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        TextView tv_latitude,tv_longitude,tv_city,tv_state,tv_pincode,tv_date;
        ImageView iv_delete;
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_latitude = (TextView)itemView.findViewById(R.id.tv_latitude);
            tv_longitude = (TextView)itemView.findViewById(R.id.tv_longitude);
            tv_city = (TextView)itemView.findViewById(R.id.tv_city);
            tv_state = (TextView)itemView.findViewById(R.id.tv_state);
            tv_pincode = (TextView)itemView.findViewById(R.id.tv_pincode);
            iv_delete =(ImageView)itemView.findViewById(R.id.iv_delete);
            tv_date = itemView.findViewById(R.id.tv_date);

        }
    }
}
