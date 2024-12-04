package com.example.marcosgb_parciall_2_1.modelo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class VistaModeloHorario extends AndroidViewModel {
    private RepositorioHorario repositorio;
    private LiveData<List<ClaseHorario>> horarios;

    public VistaModeloHorario(@NonNull Application application) {
        super(application);
        repositorio = new RepositorioHorario(application);
        horarios = repositorio.obtenerHorarios();
    }

    public LiveData<List<ClaseHorario>> obtenerHorarios() {
        return horarios;
    }

    public void agregarClase(ClaseHorario clase) {
        repositorio.agregarClase(clase);
    }

    public void eliminarClase(ClaseHorario clase) {
        repositorio.eliminarClase(clase);
    }
}
