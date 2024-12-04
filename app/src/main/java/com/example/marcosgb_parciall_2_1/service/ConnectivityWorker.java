package com.example.marcosgb_parciall_2_1.service;

import android.app.Application;
import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.marcosgb_parciall_2_1.modelo.RepositorioHorario;

public class ConnectivityWorker extends Worker {
    public ConnectivityWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        RepositorioHorario repositorio = new RepositorioHorario((Application) getApplicationContext());
        repositorio.fetchHorarios(); // metodo que sincroniza los horarios desde Firebase
        return Result.success();
    }
}
