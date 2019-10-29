package com.example.uts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AnggrekAdapter extends RecyclerView.Adapter<AnggrekAdapter.AnggerViewHolder> {
    private ArrayList<RecycleModel> dataList;
    private Activity activity;
    private ItemClickListener clickListener;
    private Context context;

    public AnggrekAdapter(ArrayList<RecycleModel> anggreks, Activity activity, Context context) {
//    public AnggrekAdapter(Context context, ArrayList<RecycleModel> anggreks) {
        this.dataList = anggreks;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public AnggrekAdapter.AnggerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cards_layout, parent, false);
        return new AnggerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnggrekAdapter.AnggerViewHolder anggrekViewHolder, int i) {
        AssetManager manager = activity.getAssets();
        InputStream inputStream;
        try {
            inputStream = manager.open(dataList.get(i).getImage()+".jpg");
//            inputStream = manager.open(dataList.get(i).getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            System.out.print(dataList.get(i).getImage()+".jpg");
//            System.out.print(dataList.get(i).getImage());
            Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap, 150, 120, true);
            anggrekViewHolder.anggrekImage.setImageBitmap(bitmapScaled);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        anggrekViewHolder.anggrekName.setText(dataList.get(i).getTitle());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        anggrekViewHolder.anggrekPrice.setText("Rp " + decimalFormat.format(dataList.get(i).getPrice()));
//        Glide.with(context)
//                .asDrawable()
//                .load(dataList.get(i).getImage())
//                .apply(new RequestOptions().override(1080,900))
//                .into(anggrekViewHolder.anggrekImage);
//        anggrekViewHolder.anggrekName.setText(dataList.get(i).getTitle());
//        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
//        anggrekViewHolder.anggrekPrice.setText("Rp " + decimalFormat.format(dataList.get(i).getPrice()));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
//        void imageonClick(View view, int position);
    }

    public class AnggerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView anggrekImage;
        private TextView anggrekName;
        private TextView anggrekPrice;
        private CardView cardView;

        public AnggerViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            anggrekImage = (ImageView) view.findViewById(R.id.anggrekImg);
            anggrekName = (TextView) view.findViewById(R.id.Title);
            anggrekPrice = (TextView) view.findViewById(R.id.price);

//            this.anggrekName = itemView.findViewById(R.id.Title);
//            this.anggrekPrice = itemView.findViewById(R.id.price);
//            this.anggrekImage = itemView.findViewById(R.id.anggrekImg);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            anggrekImage.setOnClickListener(this);
            anggrekName.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onClick(v, getAdapterPosition());
            }
//            if (v.getId() == anggrekImage.getId()) {
////                clickListener.imageonClick(v, getAdapterPosition());
//                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            } else {
//                RecycleModel currentPosition = dataList.get(getAdapterPosition());
//                Intent detailIntent = new Intent(context, Detail.class);
//                detailIntent.putExtra("nama", currentPosition.getTitle());
//                detailIntent.putExtra("price", currentPosition.getPrice());
//                detailIntent.putExtra("image",currentPosition.getImage());
//                context.startActivity(detailIntent);
////                clickListener.nameonClick(v, getAdapterPosition());
////                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
