package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.FutureTask;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize the views.
        TextView anggrekNamaDet = findViewById(R.id.namaDetail);
        TextView anggrekPriceDet = findViewById(R.id.priceDetail);
        ImageView anggrekImgDet = findViewById(R.id.imgDetail);

        // Set the text from the Intent extra.
        anggrekNamaDet.setText(getIntent().getStringExtra("nama"));
//        anggrekNamaDet.setText(showNama);

        //set price
        Double price = getIntent().getDoubleExtra("price",0.00);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        anggrekPriceDet.setText("Rp " + decimalFormat.format(price));
//        String price = getIntent().getStringExtra("price");
//        String res = price.replace(",", "");
//        anggrekPriceDet.setText("Rp "+res);
//        anggrekPriceDet.setText("Rp "+getIntent().getDoubleExtra("price",0.0));

        //set image
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(getIntent().getStringExtra("image")+".jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap, 900, 700, true);
            anggrekImgDet.setImageBitmap(bitmapScaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("image");
//        anggrekImgDet.setImageBitmap(bitmap);
//
//        Glide.with(this)
//                .asBitmap()
//                .load(getIntent().getIntExtra("image",0))
//                .apply(new RequestOptions().override(1080,720))
//                .into(anggrekImgDet);
    }
}
