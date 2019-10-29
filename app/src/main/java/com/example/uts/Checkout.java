package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
    EditText total, bayar, kembali;
    double price, pay, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        price = getIntent().getDoubleExtra("total",0.00);
        total = findViewById(R.id.totalPrice);
        bayar = findViewById(R.id.payAmount);
        kembali = findViewById(R.id.back);

        final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        total.setText("Rp " + decimalFormat.format(price));

        bayar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (bayar.getText().toString().length() == 0){
                    pay = 0;
                }else {
                    pay = Double.parseDouble(bayar.getText().toString());
                }
                back = pay - price;
                kembali.setText("Rp. " + decimalFormat.format(back));
            }
        });
    }


    public void pay(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("total", 0.0f);
        sendNotif();
        startActivity(intent);
    }

    public void sendNotif(){
        NotificationManager notifManage = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Primary","Transaction",NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setDescription
                    ("Terima kasih telah melakukan pembayaran");

            notifManage.createNotificationChannel(channel);
        }

        Intent updateIntent = new Intent("com.android.example.uts.ACTION_UPDATE_NOTIFICATION");
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this,
                0, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

//        notifyBuilder.addAction(R.drawable.ic_pay,
//                getString(R.string.update), updatePendingIntent);

        notifManage.notify(0, notifyBuilder.build());

    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat
                .Builder(this, "Primary")
                .setContentTitle("Anggrek")
                .setContentText("Terima kasih telah melakukan Pembayaran")
                .setSmallIcon(R.drawable.ic_pay)
                .setAutoCancel(true).setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifyBuilder;

    }
}
