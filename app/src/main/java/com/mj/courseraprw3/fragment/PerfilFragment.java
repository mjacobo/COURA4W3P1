package com.mj.courseraprw3.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mj.courseraprw3.Adapter.ProfileAdapter;
import com.mj.courseraprw3.R;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.restAPI.Adapter.RestApiAdapter;
import com.mj.courseraprw3.restAPI.ConstantesRestApi;
import com.mj.courseraprw3.restAPI.EndpointsApi;
import com.mj.courseraprw3.restAPI.model.PetResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private ArrayList<pets> myPetList, mojo;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView  PetName;
    private CircularImageView PetPic;
    private String myAccount;
    private Context context;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        myAccount = getArguments().getString("accountName");
        context = getContext();

        //Toast.makeText(context, "Cuenta 2: " + myAccount, Toast.LENGTH_SHORT).show();

        if(myAccount == null) {
            return v;
        }

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rcvMyPetMatrix);
        mRecyclerView.setHasFixedSize(true);

        PetName = (TextView) v.findViewById(R.id.actvProfilePetName);
        PetPic  = (CircularImageView) v.findViewById(R.id.civProfilePhoto);

        setAccount();

        return v;
    }


    public void setData(String friendId)
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonRecentMedia = restApiAdapter.buildGsonDeserializerMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.stablishConnectionApiInstagram(gsonRecentMedia);
        String url = ConstantesRestApi.KEY_GET_FRIEND_DATA + friendId + "/" +
                ConstantesRestApi.KEY_GET_FRIEND_RECENT_MEDIA + ConstantesRestApi.KEY_ACCESS_TOKEN +
                ConstantesRestApi.ACCESS_TOKEN;
        Call<PetResponse> petResponseCall = endpointsApi.getFriendRecentMedia(url);

        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                myPetList = petResponse.getMyPetsPics();
                setPicFrame(myPetList);
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Pics: ¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión", t.toString());
            }
        });
    }

    public void receiveEle(int element){
        PetName.setText(mojo.get(element).getNombreCompleto());
        Picasso.with(getContext()).load(mojo.get(element).getURLpicture()).into(PetPic);
        myPetList.clear();
        setData("");
        mRecyclerView.setAdapter(new ProfileAdapter(getContext(), myPetList));
        mRecyclerView.invalidate();
    }

    void setAccount (){
        mojo = new ArrayList<>();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonAccount = restApiAdapter.buildGsonDeserializerAccount();
        EndpointsApi endpointsApi = restApiAdapter.stablishConnectionApiInstagram(gsonAccount);
        String url =  ConstantesRestApi.KEY_GET_FRIEND_INFO + ConstantesRestApi.KEY_QUERY_TOKEN +
                myAccount  + ConstantesRestApi.KEY_SACCESS_TOKEN + ConstantesRestApi.ACCESS_TOKEN;
        Call<PetResponse> petResponseCall = endpointsApi.getMyFriendInfo(url);

        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                mojo = petResponse.getMyPetsPics();

                if (mojo.size() != 0){
                PetName.setText(mojo.get(0).getNombreCompleto());
                Picasso.with(context).load(mojo.get(0).getURLpicture()).into(PetPic);
                setData(mojo.get(0).getId());}
                else{
                    PetName.setText("Cuenta no valida");
                    PetPic.setImageResource(R.drawable.photograph);
                    setPicFrame(mojo);
                }
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión", t.toString());
            }
        });
    }

    void setPicFrame(ArrayList<pets> Carrousel){
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ProfileAdapter(context, Carrousel);
        mRecyclerView.setAdapter(mAdapter);
    }
}
