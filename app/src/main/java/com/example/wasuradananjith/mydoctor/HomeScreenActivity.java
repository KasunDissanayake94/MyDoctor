package com.example.wasuradananjith.mydoctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Session session;
    final DatabaseHelper myDb = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        session = new Session(this);
        if (!session.loggedin()){
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void logout(){
        session.setLoggedin(false);
        startActivity(new Intent(HomeScreenActivity.this,LoadingLogin.class));
        finish();
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
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.btnNearDoc) {
            startActivity(new Intent(HomeScreenActivity.this, NearDoctorActivity.class));
            overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
            finish();
        } else if (id == R.id.btnVidCall) {
            startActivity(new Intent(HomeScreenActivity.this, VideoActivity.class));
            overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
            finish();
        }  else if (id == R.id.btnShort) {
            startActivity(new Intent(HomeScreenActivity.this, MapsActivity.class));
            overridePendingTransition(R.anim.slide_out_top, R.anim.slide_in_bottom);
            finish();
        } else if (id == R.id.btnLogOut) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeScreenActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("Confirm Log Out...");
            alertDialog.setIcon(R.drawable.warningalert);

            // Setting Dialog Message
            alertDialog.setMessage("Are you sure you want to logout? Logging out may delete all your requests");

            // Setting Icon to Dialog
            //alertDialog.setIcon(R.drawable.save);

            // Setting Positive "Yes" Button
            alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User pressed YES button. Write Logic Here
                    Toast.makeText(getApplicationContext(), "Logging Out",
                            Toast.LENGTH_SHORT).show();
                    logout();
                    myDb.dropTable();
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            // Showing Alert Message
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
