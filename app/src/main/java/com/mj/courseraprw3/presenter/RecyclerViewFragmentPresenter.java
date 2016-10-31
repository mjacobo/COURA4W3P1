package com.mj.courseraprw3.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mj.courseraprw3.db.ConstructorPets;
import com.mj.courseraprw3.fragment.iRecyclerViewFragmentView;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.restAPI.Adapter.RestApiAdapter;
import com.mj.courseraprw3.restAPI.ConstantesRestApi;
import com.mj.courseraprw3.restAPI.EndpointsApi;
import com.mj.courseraprw3.restAPI.model.PetResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by leyenda1 on 01/10/2016.
 */

public class RecyclerViewFragmentPresenter implements iRecyclerViewFragmentPresenter {
    private iRecyclerViewFragmentView IRecyclerViewFragmentView;
    private Context context;
    private ConstructorPets constructorPets;
    private ArrayList<pets> pets;
    private ArrayList<pets> myFriends;

    public RecyclerViewFragmentPresenter(iRecyclerViewFragmentView IRecyclerViewFragmentView, Context context) {
        this.IRecyclerViewFragmentView = IRecyclerViewFragmentView;
        this.context = context;
       // getPets();

        myFriends = new ArrayList<>();

        getRecentMedia();
    }

    @Override
    public void getPets() {
        constructorPets = new ConstructorPets(context);
        //pets = constructorPets.obtenerDatos();
        mostrarPetsRV();
    }

    @Override
    public void getRecentMedia() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonRecentMedia = restApiAdapter.buildGsonDeserializerMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.stablishConnectionApiInstagram(gsonRecentMedia);
        Call<PetResponse> petResponseCall = endpointsApi.getRecentMedia();



        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                pets = petResponse.getMyPetsPics();
                getMyFriends();
                mostrarPetsRV();
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "RecentMedia: ¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión", t.toString());
            }
        });
    }

    @Override
    public void mostrarPetsRV() {
        IRecyclerViewFragmentView.inicializarAdaptadorRV(IRecyclerViewFragmentView.CrearAdaptador(pets));
        IRecyclerViewFragmentView.generarLinearLayout();
    }

    public void getMyFriends(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFriends = restApiAdapter.buildGsonDeserializerAccount();
        EndpointsApi endpointsApi = restApiAdapter.stablishConnectionApiInstagram(gsonFriends);
        Call<PetResponse> petResponseCall = endpointsApi.getMyFriendList();

        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                myFriends = petResponse.getMyPetsPics();
                getMyFriendsRecentMedia(myFriends);
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "MyFriends: ¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión", t.toString());
            }
        });
    }

    public void getMyFriendsRecentMedia(ArrayList<pets> mFriends) {
        for (int i = 0; i < mFriends.size() ; i++) {
            getFriendRecentMedia(mFriends.get(i).getId());
        }
    }

    public void getFriendRecentMedia(String friendId){
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
                ArrayList<pets> tmpList = petResponse.getMyPetsPics();

                for (int i = 0; i < tmpList.size(); i++) {
                    pets tmpPet = new pets();
                    tmpPet.setId(tmpList.get(i).getId());
                    tmpPet.setNombreCompleto(tmpList.get(i).getNombreCompleto());
                    tmpPet.setURLpicture(tmpList.get(i).getURLpicture());
                    tmpPet.setLikes(tmpList.get(i).getLikes());

                    pets.add(tmpPet);
                }
                mostrarPetsRV();
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Friend recent media: ¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión", t.toString());
            }
        });

    }
}
