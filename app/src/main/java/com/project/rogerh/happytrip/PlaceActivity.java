package com.project.rogerh.happytrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class PlaceActivity extends AppCompatActivity {
    TextView txtTenDiaDiem, txtGioiThieu, txtComment, txtDiaChi;
    ImageView imgDiaDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        Place myObject = new Gson().fromJson(jsonMyObject, Place.class);

        txtTenDiaDiem = (TextView) findViewById(R.id.TenDiaDiem);
        txtGioiThieu = (TextView) findViewById(R.id.GioiThieu_ChiTiet);
        txtComment = (TextView) findViewById(R.id.comment_user);
        txtDiaChi = (TextView) findViewById(R.id.DiaChi_ChiTiet);
        imgDiaDiem = (ImageView) findViewById(R.id.CacHinhAnhDiaDiem);


        txtComment.setText(myObject.comments.get(1));
        txtGioiThieu.setText(myObject.intro);
        txtTenDiaDiem.setText(myObject.name);
        txtDiaChi.setText(myObject.address);
        imgDiaDiem.setImageResource(myObject.images.get(1));


    }
}
