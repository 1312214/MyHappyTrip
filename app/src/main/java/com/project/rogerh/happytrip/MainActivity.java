package com.project.rogerh.happytrip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    Button btntakephoto, btngps, btngalary, btnshare, btncreateplace;
    private List<Place> dbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //setContentView(R.layout.detail_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        // Check that Google_Play_service is available on the device
        int result = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(
                        getApplicationContext());
        if ( result != ConnectionResult.SUCCESS ) {
            GooglePlayServicesUtil
                    .getErrorDialog(result, MainActivity.this, 1).show();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                addAllMarkerToMapScreen(map);
                map.addMarker(new MarkerOptions().position(new LatLng(10.774033,106.699054))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_24)));

                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Place selectedPlace = null;

                        LatLng posMarker = marker.getPosition();
                        Intent activity = new Intent(MainActivity.this,PlaceActivity.class);
                        for(int i = 0 ; i < dbase.size(); i++){
                            if(posMarker.equals(dbase.get(i).xy))
                                selectedPlace = dbase.get(i);
                        }
                        activity.putExtra("myObject", new Gson().toJson(selectedPlace));
                        startActivity(activity);
                    }
                });
            }


        });


        // init database
        initDataBase();

        //connect to id of buttons.
        btncreateplace = (Button)findViewById(R.id.button_create_place);
        btngalary = (Button)findViewById(R.id.button_galary);
        btnshare = (Button)findViewById(R.id.button_share);
        btntakephoto = (Button)findViewById(R.id.button_take_photo);
        btngps = (Button)findViewById(R.id.button_gps);

        btncreateplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // do something here.
            }
        });

        btngalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btntakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btngps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }// END onCreate





    // this method add all place markers to the map screen.
    public void addAllMarkerToMapScreen(GoogleMap mMap){
        for(int i = 0; i < dbase.size(); i++ ) {
            Place p = dbase.get(i);
            setAMarkerToMap(mMap, p.xy, p.thumnial, p.name, p.address );
        }
    }

    // this method uses to add a custom market to The map screen.
    public void setAMarkerToMap(GoogleMap mMap, LatLng markerPos,
                               int placeThumnial, String placeName, String address){
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(350, 350, conf);
        Canvas canvas1 = new Canvas(bmp);

        // paint defines the text color, stroke width and size
        Paint color = new Paint();
        color.setTextSize(10);
        color.setColor(Color.WHITE);

        // modify canvas
        canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
                placeThumnial), 0,0, color);

        // add marker to Map
        mMap.addMarker(new MarkerOptions().position(markerPos).title(placeName).snippet(address)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .anchor(0.5f, 1));

    }



    public void initDataBase(){
        dbase = new ArrayList<Place>();

        // crete image data.
        List<Integer> phoDiBoImgs = new ArrayList<>();
        List<Integer> nhaThoImgs = new ArrayList<>();
        List<Integer> nuiImgs = new ArrayList<>();

        phoDiBoImgs.add(R.drawable.phodibo1);
        phoDiBoImgs.add(R.drawable.phodibo2);
        phoDiBoImgs.add(R.drawable.phodibo3);
        nhaThoImgs.add(R.drawable.nhatho1);
        nhaThoImgs.add(R.drawable.nhatho2);
        nhaThoImgs.add(R.drawable.nhatho3);
        nuiImgs.add(R.drawable.nui1);
        nuiImgs.add(R.drawable.nui2);
        nuiImgs.add(R.drawable.nui3);

        // creata comment data.
        List<String> phoDiBoCmts = new ArrayList<>();
        List<String> nhaThoCmts = new ArrayList<>();
        List<String> nuiCmts = new ArrayList<>();

        phoDiBoCmts.add("Đây là một nơi rất thú vị, mọi người thử ghé thăm nhé =))");
        phoDiBoCmts.add("Mọi người đã đến đây chơi chưa nào ...hehehe");
        phoDiBoCmts.add("Hãy cùng nhau đi khám phá nơi này nào các bạn ơi!!");
        nhaThoCmts.add("Mọi người đã đến đây chơi chưa nào ...hehehe");
        nhaThoCmts.add("Hãy cùng nhau đi khám phá nơi này nào các bạn ơi!!");
        nhaThoCmts.add("Đây là một nơi rất thú vị, mọi người thử ghé thăm nhé =))");
        nuiCmts.add("Đây là một nơi rất thú vị, mọi người thử ghé thăm nhé =))");
        nuiCmts.add("Hãy cùng nhau đi khám phá nơi này nào các bạn ơi!!");
        nuiCmts.add("Hãy cùng nhau đi khám phá nơi này nào các bạn ơi!!");

        String phoIntro = "Đường Nguyễn Huệ là một đường phố trung tâm tại Quận 1, " +
                "Thành phố Hồ Chí Minh, nối liền Trụ sở Ủy ban Nhân dân Thành phố với " +
                "bến Bạch Đằng, bờ sông Sài Gòn.";
        String nhaIntro = "Nhà thờ chính tòa Đức Bà Sài Gòn " +
                " là nhà thờ chính tòa của Tổng giáo phận Thành phố Hồ " +
                "Chí Minh, một trong những công trình kiến trúc độc đáo của Sài Gòn, điểm đến của " +
                "du khách trong và ngoài nước, nét đặc trưng của du lịch Việt Nam";
        String nuiIntro = "Vùng Tây Bắc là vùng miền núi phía tây của miền Bắc Việt Nam, " +
                "có chung đường biên giới với Lào và Trung Quốc. Vùng này có khi được gọi " +
                "là Tây Bắc Bắc Bộ và là một trong 3 tiểu vùng địa lý tự nhiên của Bắc Bộ Việt Nam";

        LatLng nhaThoxy = new LatLng(10.779784, 106.698973);
        LatLng nuixy = new LatLng(10.776075, 106.699058);
        LatLng phoDiBoxy = new LatLng(10.776226, 106.701577);

        Place phoDiBo = new Place("Phố đi bộ Nguyễn Huệ", phoDiBoImgs,
                "112 đường Nguyễn Huệ, Quận 1, Tp Hồ Chí Minh", 5, phoIntro,
                phoDiBoCmts, phoDiBoxy, R.drawable.phodibothumnail );
        Place nhaTho = new Place("Nhà thời Đức Bà", nhaThoImgs,
                "113 Đồng Khởi, Quận 3, Tp Hồ Chí Minh", 4, nhaIntro,
                nhaThoCmts, nhaThoxy, R.drawable.nhathothumnail);
        Place nui = new Place("Vùng núi Tây Bắc - Lạng Sơn", nuiImgs,
                "Thành Phố Lạng Sơn - Vùng Tây Bắc", 4, nuiIntro,
                nuiCmts, nuixy, R.drawable.nuithumnail );

        dbase.add(phoDiBo);
        dbase.add(nhaTho);
        dbase.add(nui);
    }











    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            // In this case, filter_button is clicked.
            case R.id.filter_button:
                View filter = findViewById(R.id.filter_button);
                PopupMenu popup = new PopupMenu(this, filter);
                popup.inflate(R.menu.filter_actions);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.vui_choi_item:
                                //do something.
                                return false;
                            case R.id.di_tich_item:
                                // do something
                                return false;
                            case R.id.canh_dep_item:
                                // do sth
                                return false;


                            default: return false;
                        }
                    }
                });


                return true;
            case R.id.notification_button:
                View notifi = findViewById(R.id.notification_button);
                PopupMenu popupnoti = new PopupMenu(this,notifi);
                popupnoti.inflate(R.menu.notification);
                popupnoti.show();





            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void showFilterPopUp()
    {

    }









    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
