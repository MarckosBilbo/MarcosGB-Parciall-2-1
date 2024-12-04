package com.example.marcosgb_parciall_2_1.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class ClaseHorario implements Parcelable {
    private String id;         // Identificador único (Firebase key)
    private String asignatura; // Nombre de la asignatura
    private String dia;        // Día (Lunes, Martes, etc.)
    private String hora;       // Hora en formato HH:mm

    public ClaseHorario() {
        // Constructor vacío para Firebase
    }

    public ClaseHorario(String asignatura, String dia, String hora) {
        this.asignatura = asignatura;
        this.dia = dia;
        this.hora = hora;
    }

    protected ClaseHorario(Parcel in) {
        id = in.readString();
        asignatura = in.readString();
        dia = in.readString();
        hora = in.readString();
    }

    public static final Creator<ClaseHorario> CREATOR = new Creator<ClaseHorario>() {
        @Override
        public ClaseHorario createFromParcel(Parcel in) {
            return new ClaseHorario(in);
        }

        @Override
        public ClaseHorario[] newArray(int size) {
            return new ClaseHorario[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(asignatura);
        dest.writeString(dia);
        dest.writeString(hora);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
}
