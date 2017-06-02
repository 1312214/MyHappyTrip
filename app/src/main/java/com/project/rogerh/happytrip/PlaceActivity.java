package com.project.rogerh.happytrip;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class PlaceActivity extends AppCompatActivity {
    TextView txtTenDiaDiem, txtGioiThieu, txtComment, txtDiaChi;
    ImageView imgDiaDiem, btnAddList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        android.support.v7.app.ActionBar acb = getSupportActionBar();

        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        Place myObject = new Gson().fromJson(jsonMyObject, Place.class);


        acb.setTitle(myObject.name);
        txtGioiThieu = (TextView) findViewById(R.id.GioiThieu_ChiTiet);
        txtComment = (TextView) findViewById(R.id.comment_user);
        txtDiaChi = (TextView) findViewById(R.id.DiaChi_ChiTiet);
        imgDiaDiem = (ImageView) findViewById(R.id.CacHinhAnhDiaDiem);
        btnAddList = (ImageView) findViewById(R.id.Button_AddList);

        txtComment.setText(myObject.comments.get(1));
        txtGioiThieu.setText(myObject.intro);
        txtDiaChi.setText(myObject.address);
        imgDiaDiem.setImageResource(myObject.images.get(1));

        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đã thêm địa điểm này vào danh sách Quan tâm", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
