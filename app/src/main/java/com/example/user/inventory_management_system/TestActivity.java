package com.example.user.inventory_management_system;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    TableLayout tableLayout;
    final List<Products> productList = new ArrayList<Products>();


    //Firebase
    //private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
    }

    public void initViews(){
        tableLayout = (TableLayout)findViewById(R.id.tbl_layout);
        addHeaders();

//        Products modelClass = new Products();
//        modelClass.setProductID("A");
//        modelClass.setProductName("B");
//        modelClass.setProductCategory("C");
//        modelClass.setProductQuantity("D");
//        modelClass.setProductPrice("E");
//        modelList.add(modelClass);
//
//        Products modelClass1 = new Products();
//        modelClass1.setProductID("A");
//        modelClass1.setProductName("B");
//        modelClass1.setProductCategory("C");
//        modelClass1.setProductQuantity("D");
//        modelClass1.setProductPrice("E");
//        modelList.add(modelClass1);

        FirebaseDatabase databaseReference = FirebaseDatabase.getInstance();
        DatabaseReference productData = databaseReference.getReference();
        productData.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<Products> productList = new ArrayList<Products>();
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    Products products = ds.getValue(Products.class);
//                    productList.add(products);

//                    No sub child comment
//                List<String> productList = new ArrayList<String>();
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    productList.add(String.valueOf(ds.child("productID").getValue()));
//                    productList.add(String.valueOf(ds.child("productName").getValue()));
//                    productList.add(String.valueOf(ds.child("productCategory").getValue()));
//                    productList.add(String.valueOf(ds.child("productQuantity").getValue()));
//                    productList.add(String.valueOf(ds.child("productPrice").getValue()));
//                }
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    Products products = child.getValue(Products.class);
                    productList.add(products);
                    addRows();
                }
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
            TableRow tr = new TableRow(TestActivity.this);
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

}