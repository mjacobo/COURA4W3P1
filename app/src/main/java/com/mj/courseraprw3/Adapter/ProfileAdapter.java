package com.mj.courseraprw3.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mj.courseraprw3.R;
import com.mj.courseraprw3.pojo.pets;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by leyenda1 on 23/09/2016.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    ArrayList<pets> mDataset;
    Context myContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView  mLikes;
        public ImageView mImageView;


        public ViewHolder(View view) {
            super(view);
            mLikes     = (TextView)  view.findViewById(R.id.actvProfileLikes);
            mImageView = (ImageView) view.findViewById(R.id.acivPetProfilePicture);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProfileAdapter(Context context, ArrayList<pets> myDataset) {
        this.mDataset  = myDataset;
        this.myContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_profile_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...

        return new ProfileAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        String tmp = String.valueOf(mDataset.get(position).getLikes());
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mLikes.setText(tmp);
        Picasso.with(myContext).load(mDataset.get(position).getURLpicture()).into( holder.mImageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
