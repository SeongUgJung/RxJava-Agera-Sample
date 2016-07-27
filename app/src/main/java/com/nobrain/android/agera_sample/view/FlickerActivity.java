package com.nobrain.android.agera_sample.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.nobrain.android.agera_sample.R;
import com.nobrain.android.agera_sample.adapter.FlickerAdapter;
import com.nobrain.android.agera_sample.dagger.DaggerFlickerComponent;
import com.nobrain.android.agera_sample.dagger.FlickerModule;
import com.nobrain.android.agera_sample.domain.Photo;
import com.nobrain.android.agera_sample.viewmodel.FlickerViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlickerActivity extends AppCompatActivity implements FlickerView {

    @BindView(R.id.list_flicker)
    RecyclerView list;

    @InjectExtra
    int type;

    @Inject
    FlickerViewModel flickerViewModelAgera;
    private FlickerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flicker);

        Dart.inject(this);

        ButterKnife.bind(this);
        DaggerFlickerComponent.builder()
                .flickerModule(new FlickerModule(this, type))
                .build()
                .inject(this);


        int col = 3;
        list.setLayoutManager(new GridLayoutManager(FlickerActivity.this, col));
        int width = getResources().getDisplayMetrics().widthPixels / col;
        adapter = new FlickerAdapter(width);
        list.setAdapter(adapter);


        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItemPosition = ((GridLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();

                if (lastVisibleItemPosition == adapter.getItemCount() - 1) {
                    flickerViewModelAgera.getMore();
                }
            }
        });


    }

    @Override
    public void addData(List<Photo> photos) {
        for (Photo photo : photos) {
            adapter.add(photo);
        }
        adapter.notifyDataSetChanged();
    }
}
