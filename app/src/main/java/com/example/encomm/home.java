package com.example.encomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    TextView see;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView recyclerView = findViewById(R.id.horizontalRv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<Integer> imageIds = new ArrayList<>();
        imageIds.add(R.drawable.sale);
        imageIds.add(R.drawable.cy);
        imageIds.add(R.drawable.img);
        RecycleAdapter adapter = new RecycleAdapter(imageIds);
        recyclerView.setAdapter(adapter);



see=findViewById(R.id.see);
see.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(home.this,Allcategory.class);
        startActivity(intent);

    }
});
        Button all=findViewById(R.id.button1);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(home.this,Allcategory.class);
                startActivity(intent);
            }
        });








    }


}