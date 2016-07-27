package com.nobrain.android.agera_sample.viewmodel.agera;

import android.util.Log;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.nobrain.android.agera_sample.domain.Photo;
import com.nobrain.android.agera_sample.domain.Photos;
import com.nobrain.android.agera_sample.network.FlickerApi;
import com.nobrain.android.agera_sample.view.FlickerView;
import com.nobrain.android.agera_sample.viewmodel.FlickerViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class FlickerViewModelAgera implements FlickerViewModel {
    private static final String TAG = "FlickerViewModelAgera";
    private MutableRepository<Integer> repository;
    private Repository<Result<List<Photo>>> resultRepository;
    private FlickerView view;
    private FlickerApi api;

    @Inject
    public FlickerViewModelAgera(FlickerView view, FlickerApi api) {
        this.view = view;
        this.api = api;
        repository = Repositories.mutableRepository(0);
        initFlickerRepository();

        Log.d(TAG, "FlickerViewModelAgera");
    }

    private void initFlickerRepository() {
        resultRepository = Repositories.repositoryWithInitialValue(Result.<List<Photo>>absent())
                .observe(repository)
                .onUpdatesPerLoop()
                .goTo(Executors.newSingleThreadExecutor())
                .getFrom(repository)
                .attemptTransform(input -> {
                    try {
                        return Result.present(api.getRecentPhoto(input, IMAGE_COUNT).execute().body());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Result.failure(e);
                    }
                })
                .orSkip()
                .thenTransform(input -> {
                    Photos photos = input.photos();
                    List<Photo> photo = photos.photo();
                    return Result.present(photo);
                })
                .compile();

        resultRepository.addUpdatable(() -> {
            view.addData(resultRepository.get().orElse(new ArrayList<>()));
        });
    }

    @Override
    public void getMore() {
        repository.accept(repository.get() + 1);
    }

}
