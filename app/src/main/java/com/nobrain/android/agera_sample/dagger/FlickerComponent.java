package com.nobrain.android.agera_sample.dagger;

import com.nobrain.android.agera_sample.view.FlickerActivity;

import dagger.Component;

@Component(modules = FlickerModule.class)
public interface FlickerComponent {
    void inject(FlickerActivity activity);
}
