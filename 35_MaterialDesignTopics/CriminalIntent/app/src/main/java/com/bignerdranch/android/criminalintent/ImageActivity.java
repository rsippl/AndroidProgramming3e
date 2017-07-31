package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    private static final String TRANSITION_NAME = "image";

    public static Intent newIntent(Context context, Uri imagePath) {
        Intent i = new Intent(context, ImageActivity.class);
        i.setData(imagePath);

        return i;
    }

    public static void startWithTransition(Activity activity, Intent intent, View sourceView) {
        ViewCompat.setTransitionName(sourceView, TRANSITION_NAME);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, sourceView, TRANSITION_NAME);
        activity.startActivity(intent, options.toBundle());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);

        Uri pathUri = getIntent().getData();
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageURI(pathUri);
    }
}
