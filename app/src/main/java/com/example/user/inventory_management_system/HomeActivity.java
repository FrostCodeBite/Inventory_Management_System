package com.example.user.inventory_management_system;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.user.inventory_management_system.Model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    TableLayout tableLayout;
    List<Products> productList = new ArrayList<Products>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();
    }

    public void initViews(){
        tableLayout = (TableLayout)findViewById(R.id.tbl_layout);
        addHeaders();

        FirebaseDatabase databaseReference = FirebaseDatabase.getInstance();
        DatabaseReference productData = databaseReference.getReference();
        productData.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    Products products = child.getValue(Products.class);
                    productList.add(products);
                }
                addRows();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addHeaders() {

        TableLayout tl = findViewById(R.id.tbl_layout);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());

        //  tr.addView(getTextView(0, "Auditor id", Color.WHITE, Typeface.BOLD, R.color.colorAccent));
        tr.addView(getTextView(0, "ID", Color.WHITE, Typeface.BOLD, R.drawable.cell_shape_fill ));
        tr.addView(getTextView(0, "NAME", Color.WHITE, Typeface.BOLD, R.drawable.cell_shape_fill ));
        tr.addView(getTextView(0, "CATEGORY", Color.WHITE, Typeface.BOLD, R.drawable.cell_shape_fill ));
        tr.addView(getTextView(0,"QUANTITY", Color.WHITE, Typeface.BOLD, R.drawable.cell_shape_fill));
        tr.addView(getTextView(0,"PRICE", Color.WHITE, Typeface.BOLD, R.drawable.cell_shape_fill));

        tl.addView(tr, getTblLayoutParams());
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }


    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        params.weight = 1;
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {

    }

    public void addRows(){
        // Collections.reverse(trainScheduleList);
        for (int i = 0; i < productList.size(); i++) {
            TableRow tr = new TableRow(HomeActivity.this);
            tr.setLayoutParams(getLayoutParams());

            tr.addView(getRowsTextView(0, productList.get(i).getProductID(), Color.BLACK, Typeface.NORMAL, R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, productList.get(i).getProductName(), Color.BLACK, Typeface.NORMAL ,R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, productList.get(i).getProductCategory(), Color.BLACK, Typeface.NORMAL ,R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, productList.get(i).getProductQuantity(), Color.BLACK, Typeface.NORMAL ,R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, productList.get(i).getProductPrice(), Color.BLACK, Typeface.NORMAL ,R.drawable.cell_shape ));

            tableLayout.addView(tr, getTblLayoutParams());

        }

    }

    private TextView getRowsTextView(int id, String title, int color, int typeface,int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }

//    TABLE LAYOUT

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
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_add_products) {
            startActivity(new Intent(this,AddProductActivity.class));
            finish();
        } else if (id == R.id.nav_delete_products) {
            startActivity(new Intent(this,DeleteProductActivity.class));
            finish();
        } else if (id == R.id.nav_update) {
            startActivity(new Intent(this,UpdateProductActivity.class));
            finish();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
