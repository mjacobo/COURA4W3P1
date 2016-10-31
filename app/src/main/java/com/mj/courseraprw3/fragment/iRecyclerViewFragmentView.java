package com.mj.courseraprw3.fragment;

import com.mj.courseraprw3.Adapter.MyAdapter;
import com.mj.courseraprw3.pojo.pets;

import java.util.ArrayList;

/**
 * Created by leyenda1 on 01/10/2016.
 */

public interface iRecyclerViewFragmentView {

    public void generarLinearLayout();

    public MyAdapter CrearAdaptador (ArrayList<pets> Pets);

    public void inicializarAdaptadorRV(MyAdapter adaptador);
}
