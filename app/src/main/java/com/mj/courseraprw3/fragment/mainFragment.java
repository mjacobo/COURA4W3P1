package com.mj.courseraprw3.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mj.courseraprw3.R;
import com.mj.courseraprw3.RecyclerItemClickListener;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.Adapter.MyAdapter;
import com.mj.courseraprw3.presenter.RecyclerViewFragmentPresenter;
import com.mj.courseraprw3.presenter.iRecyclerViewFragmentPresenter;

import java.util.ArrayList;



public class mainFragment extends Fragment implements  iRecyclerViewFragmentView{
    private RecyclerView mRecyclerView;
    private ItemClicked mCallback;
    private iRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_main,container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcvMyCarrousel);
        mRecyclerView.setHasFixedSize(true);
   /*     mRecyclerView.addOnItemTouchListener( new RecyclerItemClickListener( getActivity(), new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                mCallback.sendItem(position);
            }
        }));*/
        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return view;
    }

    public interface ItemClicked{
        public void sendItem(Integer element);
    }

    @Override
    public void  onAttach(Context context){
        super.onAttach(context);
        try{
            mCallback = (ItemClicked) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement TextClicked");
        }

    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void generarLinearLayout() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public MyAdapter CrearAdaptador(ArrayList<pets> Pets) {
        MyAdapter mAdapter = new MyAdapter(Pets, getActivity());
        return mAdapter;
    }

    @Override
    public void inicializarAdaptadorRV(MyAdapter adaptador) {
        mRecyclerView.setAdapter(adaptador);
    }
}
