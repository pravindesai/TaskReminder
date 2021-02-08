package com.example.taskreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectImageActivity extends AppCompatActivity {
    RecyclerView Recyclerview;
    ArrayList<Integer> imageArrayList;
    ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        init();
    }

    private void init() {
        Recyclerview = findViewById(R.id.Recyclerview);

        imageArrayList = new ArrayList<Integer>();
        imageArrayList.add(R.drawable.screenshot__1_);
        imageArrayList.add(R.drawable.ic_launcher_background);
        imageArrayList.add(R.drawable.ic_launcher_background);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3 );
        Recyclerview.setLayoutManager(gridLayoutManager);

        imageAdapter = new ImageAdapter(imageArrayList);
        Recyclerview.setAdapter(imageAdapter);

        imageAdapter.setOnImgeClickListner(new ImageSelectListner());

    }

    public class ImageSelectListner implements ImageAdapter.onImgeClickListner{

        @Override
        public void onImageClicked(ImageAdapter imageAdapter, int imgId, int position, View view) {
                //Toast.makeText(SelectImageActivity.this, imgId+"",Toast.LENGTH_SHORT).show();

                Intent output = new Intent();
                output.putExtra("imgid",imgId);
                setResult(RESULT_OK,output);
                finish();
        }
    }
}