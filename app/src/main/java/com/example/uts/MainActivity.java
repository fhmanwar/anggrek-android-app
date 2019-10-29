package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.AsynchronousFileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AnggrekAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private AnggrekAdapter anggrekAdapter;
    private ArrayList<RecycleModel> anggreks;
    private TextView totalPrice,namadet,priceDet;
    private double price = 0;
    private ImageView imgdet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgdet = (ImageView) findViewById(R.id.imgDetail);
        namadet = (TextView) findViewById(R.id.namaDetail);
        priceDet = (TextView) findViewById(R.id.priceDetail);

        totalPrice = (TextView) findViewById(R.id.total);
        totalPrice.setText("Rp " + price);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        addData();

    }

    @Override
    public void onClick(View view, int position) {
        double anggrekPrice = anggreks.get(position).getPrice();
        switch (view.getId()) {
            case R.id.anggrekImg :
                price = price + (float)anggrekPrice;
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                totalPrice.setText("Rp " + decimalFormat.format(price));
                return;
            default:
                RecycleModel currentPosition = anggreks.get(position);
                Intent detailIntent = new Intent(getApplicationContext(), Detail.class);
                detailIntent.putExtra("nama", currentPosition.getTitle());
                detailIntent.putExtra("price", currentPosition.getPrice());
                detailIntent.putExtra("image",currentPosition.getImage());
                startActivity(detailIntent);
                return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.call:
                intent.setAction(Intent.ACTION_DIAL);
                String dataCall = getString(R.string.call);
                intent.setData(Uri.parse(dataCall));
                if (intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                }else {
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
                break;
            case R.id.msg:
//                String defaultApplication = Settings.Secure.getString(getContentResolver(), "sms_default_application");
//                intent = getPackageManager().getLaunchIntentForPackage(defaultApplication );
//                if (intent !=null) {
//                    startActivity(intent);
//                }
                intent.setAction(Intent.ACTION_SENDTO);
                String dataMsg = getString(R.string.msg);
                intent.setData(Uri.parse(dataMsg));  // This ensures only SMS apps respond
                if (intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                }else {
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
//                msg = "Sms Center";
                break;
            case R.id.loc:
                intent.setAction(Intent.ACTION_VIEW);
                String dataLoc = getString(R.string.loc);
                intent.setData(Uri.parse(dataLoc));
                if (intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                }else {
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }

                break;
            case R.id.update:
                Intent iMain = new Intent(this,Profil.class);
                startActivity(iMain);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addData() {
        anggreks = new ArrayList<>();

        anggreks.add(new RecycleModel("Anggrek Bulan", 150000.0, "abulan"));
        anggreks.add(new RecycleModel("Anggrek Dendrobium", 350000.0, "adendrobium"));
        anggreks.add(new RecycleModel("Anggrek Hitam", 200000.0, "ahitam"));
        anggreks.add(new RecycleModel("Anggrek Tanah", 1700000.0, "atanah"));
        anggreks.add(new RecycleModel("Anggrek Tebu", 1800000.0, "atebu"));
        anggreks.add(new RecycleModel("Anggrek Vanda", 1300000.0, "avanda"));

//        anggreks.add(new RecycleModel("Anggrek Bulan", 150000.0, "abulan.jpg"));
//        anggreks.add(new RecycleModel("Anggrek Dendrobium", 350000.0, "adendrobium.jpg"));
//        anggreks.add(new RecycleModel("Anggrek Hitam", 200000.0, "ahitam.jpg"));
//        anggreks.add(new RecycleModel("Anggrek Tanah", 1700000.0, "atanah.jpg"));
//        anggreks.add(new RecycleModel("Anggrek Tebu", 1800000.0, "atebu.jpg"));
//        anggreks.add(new RecycleModel("Anggrek Vanda", 1300000.0, "avanda.jpg"));

//        String[] AnggrekList = getResources().getStringArray(R.array.anggrekNama);
//        int[] AnggrekPrice = getResources().getIntArray(R.array.anggrekPrice);
//        TypedArray AnggrekImage = getResources().obtainTypedArray(R.array.anggrekImg);
//        anggreks.clear();
//
//        for(int i=0; i<AnggrekList.length; i++){
//            anggreks.add(new RecycleModel(
//                    AnggrekList[i],
//                    AnggrekPrice[i],
//                    AnggrekImage.getResourceId(i,0)
//            ));
//        }

//        AnggrekImage.recycle();
//        anggrekAdapter.notifyDataSetChanged();

        anggrekAdapter = new AnggrekAdapter(anggreks, this, this);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(anggrekAdapter);
        anggrekAdapter.setClickListener(this);
    }

    public void checkout(View view) {
        Intent intent = new Intent(getApplicationContext(), Checkout.class);
        intent.putExtra("total", price);
        startActivity(intent);
    }
}
