package com.mj.courseraprw3.Adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.mj.courseraprw3.GCM.RestAPI.Endpoints;
import com.mj.courseraprw3.GCM.model.UsuarioResponse;
import com.mj.courseraprw3.R;
import com.mj.courseraprw3.db.ConstructorPets;
import com.mj.courseraprw3.pojo.likesPostAnswer;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.restAPI.Adapter.RestApiAdapter;
import com.mj.courseraprw3.restAPI.ConstantesRestApi;
import com.mj.courseraprw3.restAPI.EndpointsApi;
import com.mj.courseraprw3.restAPI.model.LikeSetResponse;
import com.mj.courseraprw3.restAPI.model.LikesResponse;
import com.mj.courseraprw3.restAPI.model.PetResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leyenda1 on 16/09/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<pets> mDataset;
    Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView  nName;
        public TextView  mLikes;
        public ImageView mImageView;
        public ImageView mButton ;

        public ViewHolder(View view) {
            super(view);
            nName      = (TextView)  view.findViewById(R.id.actvPetName);
            mLikes     = (TextView)  view.findViewById(R.id.actvLikes);
            mImageView = (ImageView) view.findViewById(R.id.acivPetPicture);
            mButton    = (ImageView) view.findViewById(R.id.acivWhiteBone);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<pets> myDataset, Activity activity) {
        this.mDataset     = myDataset;
        this.activity     = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String myName = mDataset.get(position).getNombreCompleto();
        final String myID   = mDataset.get(position).getId();
        final int mpos = position;
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nName.setText(myName);
        holder.mLikes.setText(String.valueOf(mDataset.get(position).getLikes()));
        Picasso.with(activity).load(mDataset.get(position).getURLpicture()).into(holder.mImageView);


        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstructorPets consPets  = new ConstructorPets(activity);

                consPets.giveLike(mDataset.get(mpos));
                Toast.makeText(activity, "Diste like a: " + myName, Toast.LENGTH_SHORT).show();
                setMyLike(myID);
                holder.mLikes.setText(String.valueOf(consPets.gatherPetLikes(mDataset.get(mpos))));

            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public void setMyLike(String myId){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonLikesSet = restApiAdapter.buildGsonDeserializarlikesSet();
        EndpointsApi endpoints = restApiAdapter.stablishConnectionApiInstagram(gsonLikesSet);
        Call<LikeSetResponse> likeSetResponseCall = endpoints.setMyLike(myId);

        likeSetResponseCall.enqueue(new Callback<LikeSetResponse>() {
            @Override
            public void onResponse(Call<LikeSetResponse> call, Response<LikeSetResponse> response) {
                if(response.body() != null) {
                    //likesPostAnswer myTempAnsw = response.body().getMyLike();
                    Log.d("MY_LIKE", "Code:  " + response.body().toString());
                } else {
                    Log.d("MY_LIKE", "FAILED");
                }
            }

            @Override
            public void onFailure(Call<LikeSetResponse> call, Throwable t) {

            }
        });
    }
}

