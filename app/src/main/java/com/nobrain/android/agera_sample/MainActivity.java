package com.nobrain.android.agera_sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nobrain.android.agera_sample.view.Henson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView rv = new RecyclerView(MainActivity.this);
        setContentView(rv);

        TextAdapter adapter = new TextAdapter(MainActivity.this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    private static class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final Context context;

        private TextAdapter(Context context) {this.context = context;}

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TextViewHolder(new TextView(context));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (position) {
                case 0:
                    ((TextView) holder.itemView).setText("Agera");
                    break;
                case 1:
                    ((TextView) holder.itemView).setText("RxJava");
                    break;
            }
            holder.itemView.setOnClickListener(v -> {
                context.startActivity(Henson.with(context)
                        .gotoFlickerActivity()
                        .type(position)
                        .build());
            });
        }

        @Override
        public int getItemCount() {
            return 2;
        }

    }

    private static class TextViewHolder extends RecyclerView.ViewHolder {
        public TextViewHolder(View itemView) {
            super(itemView);
            itemView.setMinimumHeight(100);
            ((TextView) itemView).setTextSize(20);
        }
    }
}
