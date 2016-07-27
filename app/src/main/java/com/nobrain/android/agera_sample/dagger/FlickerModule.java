package com.nobrain.android.agera_sample.dagger;

import com.nobrain.android.agera_sample.network.dagger.ApiModule;
import com.nobrain.android.agera_sample.view.FlickerView;
import com.nobrain.android.agera_sample.viewmodel.FlickerViewModel;
import com.nobrain.android.agera_sample.viewmodel.agera.FlickerViewModelAgera;
import com.nobrain.android.agera_sample.viewmodel.rxjava.FlickerViewModelRxjava;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

@Module(includes = ApiModule.class)
public class FlickerModule {

    private FlickerView view;
    private int type;

    public FlickerModule(FlickerView view, int type) {
        this.view = view;
        this.type = type;
    }

    @Provides
    FlickerView providesFlickerView() {
        return view;
    }

    @Provides
    FlickerViewModel providesFlickerViewModel(Lazy<FlickerViewModelAgera> agera,
                                              Lazy<FlickerViewModelRxjava> rxjava) {
        if (type == 0) {
            return agera.get();
        } else {
            return rxjava.get();
        }
    }


}
