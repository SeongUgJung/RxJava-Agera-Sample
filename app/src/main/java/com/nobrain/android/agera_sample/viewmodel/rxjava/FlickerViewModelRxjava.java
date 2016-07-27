package com.nobrain.android.agera_sample.viewmodel.rxjava;

import android.util.Log;

import com.nobrain.android.agera_sample.domain.RecentPhoto;
import com.nobrain.android.agera_sample.network.FlickerApi;
import com.nobrain.android.agera_sample.view.FlickerView;
import com.nobrain.android.agera_sample.viewmodel.FlickerViewModel;

import java.io.IOException;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class FlickerViewModelRxjava implements FlickerViewModel {

    private static final String TAG = "FlickerViewModelRxjava";
    private final BehaviorSubject<Integer> pageSubject;
    private FlickerView view;
    private FlickerApi api;

    @Inject
    public FlickerViewModelRxjava(FlickerView view, FlickerApi api) {
        this.view = view;
        this.api = api;
        pageSubject = BehaviorSubject.create(1);

        initRx();

        Log.d(TAG, "FlickerViewModelRxjava");
    }

    private void initRx() {
        pageSubject
                .distinct()
                .observeOn(Schedulers.io())
                .concatMap(page -> {
                    try {
                        RecentPhoto body = api.getRecentPhoto(page, IMAGE_COUNT).execute().body();
                        return Observable.just(body);
                    } catch (IOException e) {
                        return Observable.error(e);
                    }
                }).map(recentPhoto -> recentPhoto.photos().photo())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> {
                    view.addData(photos);
                }, Throwable::printStackTrace);
    }

    @Override
    public void getMore() {
        pageSubject.onNext(pageSubject.getValue() + 1);
    }
}
