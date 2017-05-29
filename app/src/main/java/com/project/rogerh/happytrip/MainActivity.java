package com.project.rogerh.happytrip;

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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private GoogleMap map;
    Button btntakephoto, btngps, btngalary, btnshare, btncreateplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                //map.setMyLocationEnabled(true);
            }


        });



        //connect to id of buttons.
        btncreateplace = (Button)findViewById(R.id.button_create_place);
        btngalary = (Button)findViewById(R.id.button_galary);
        btnshare = (Button)findViewById(R.id.button_share);
        btntakephoto = (Button)findViewById(R.id.button_take_photo);
        btngps = (Button)findViewById(R.id.button_gps);

        btncreateplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeCreateAPlaceButton();
                deactiveTakeAPhotoButton();
                deactiveShareButton();
                deactiveGalaryButton();
                deactiveGPSButton();


                // do something here.
            }
        });

        btngalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGalaryButton();
                deactiveGPSButton();
                deactiveShareButton();
                deactiveTakeAPhotoButton();
                deactiveCreateAPlaceButton();

                // do sth.
            }
        });


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeShareButton();
                deactiveCreateAPlaceButton();
                deactiveTakeAPhotoButton();
                deactiveGPSButton();
                deactiveGalaryButton();


                // do something.
            }
        });


        btntakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTakeAPhotoButton();
                deactiveGalaryButton();
                deactiveGPSButton();
                deactiveCreateAPlaceButton();
                deactiveShareButton();

                // do something
            }
        });


        btngps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGPSButton();
                deactiveCreateAPlaceButton();
                deactiveGalaryButton();
                deactiveShareButton();
                deactiveTakeAPhotoButton();


                //remain to do something for this method.
            }
        });


    } // END onCreate





    // This method uses to change color of the text and icon in button
    public void activeGPSButton()
    {
        btngps.setTextColor(getResources().getColor(R.color.colorWhite));
        btngps.setBackgroundColor(getResources().getColor(R.color.colorActive));
        btngps.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_gps_fixed_white_18dp,0,0);

    }

    public void deactiveGPSButton()
    {
        btngps.setTextColor(getResources().getColor(R.color.colorButtonText));
        btngps.setBackgroundColor(getResources().getColor(R.color.colorButton));
        btngps.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_gps_fixed_black_18dp,0,0);

    }


    // Take a photo button
    private void activeTakeAPhotoButton()
    {
        btntakephoto.setTextColor(getResources().getColor(R.color.colorWhite));
        btntakephoto.setBackgroundColor(getResources().getColor(R.color.colorActive));
        btntakephoto.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_photo_camera_white_18dp,0,0);

    }

    private void deactiveTakeAPhotoButton()
    {
        btntakephoto.setTextColor(getResources().getColor(R.color.colorButtonText));
        btntakephoto.setBackgroundColor(getResources().getColor(R.color.colorButton));
        btntakephoto.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_photo_camera_black_18dp,0,0);

    }

    // Share button
    private void activeShareButton()
    {
        btnshare.setTextColor(getResources().getColor(R.color.colorWhite));
        btnshare.setBackgroundColor(getResources().getColor(R.color.colorActive));
        btnshare.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_share_white_18dp,0,0);

    }

    private void deactiveShareButton()
    {
        btnshare.setTextColor(getResources().getColor(R.color.colorButtonText));
        btnshare.setBackgroundColor(getResources().getColor(R.color.colorButton));
        btnshare.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_share_black_18dp,0,0);

    }


    // Galary Button
    private void activeGalaryButton()
    {
        btngalary.setTextColor(getResources().getColor(R.color.colorWhite));
        btngalary.setBackgroundColor(getResources().getColor(R.color.colorActive));
        btngalary.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_photo_library_white_18dp,0,0);

    }

    private void deactiveGalaryButton()
    {
        btngalary.setTextColor(getResources().getColor(R.color.colorButtonText));
        btngalary.setBackgroundColor(getResources().getColor(R.color.colorButton));
        btngalary.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_photo_library_black_18dp,0,0);

    }


    // Create-a-place button
    private void activeCreateAPlaceButton()
    {
        btncreateplace.setTextColor(getResources().getColor(R.color.colorWhite));
        btncreateplace.setBackgroundColor(getResources().getColor(R.color.colorActive));
        btncreateplace.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_create_a_place_white,0,0);

    }

    private void deactiveCreateAPlaceButton()
    {
        btncreateplace.setTextColor(getResources().getColor(R.color.colorButtonText));
        btncreateplace.setBackgroundColor(getResources().getColor(R.color.colorButton));
        btncreateplace.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_create_a_place,0,0);

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
