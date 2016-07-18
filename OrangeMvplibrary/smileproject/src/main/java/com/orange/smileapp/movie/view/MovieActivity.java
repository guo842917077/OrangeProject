package com.orange.smileapp.movie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.orange.smileapp.R;

import butterknife.Bind;

public class MovieActivity extends AppCompatActivity {
    @Bind(R.id.backdrop)
    public ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    }
}
