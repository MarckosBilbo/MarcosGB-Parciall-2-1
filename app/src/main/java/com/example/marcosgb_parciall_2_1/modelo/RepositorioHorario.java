package com.example.marcosgb_parciall_2_1.modelo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioHorario {
    private DatabaseReference databaseReference;
    private MutableLiveData<List<ClaseHorario>> horarios;
    private String userId;

    public RepositorioHorario(Application application) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://marcosgb-parcial-2-1-deb16-default-rtdb.europe-west1.firebasedatabase.app/");
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Obtén el ID del usuario autenticado
        databaseReference = database.getReference("usuarios").child(userId).child("horarios");
        horarios = new MutableLiveData<>();
        fetchHorarios();
    }

    public void fetchHorarios() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ClaseHorario> listaHorarios = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    ClaseHorario clase = childSnapshot.getValue(ClaseHorario.class);
                    if (clase != null) {
                        clase.setId(childSnapshot.getKey());
                        listaHorarios.add(clase);
                    }
                }
                horarios.postValue(listaHorarios);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("RepositorioHorario", "Error al obtener datos", error.toException());
            }
        });
    }

    public void agregarClase(ClaseHorario clase) {
        DatabaseReference nuevaClaseRef = databaseReference.push();
        clase.setId(nuevaClaseRef.getKey());
        nuevaClaseRef.setValue(clase);
    }

    public void eliminarClase(ClaseHorario clase) {
        databaseReference.child(clase.getId()).removeValue();
    }

    public LiveData<List<ClaseHorario>> obtenerHorarios() {
        return horarios;
    }
}
