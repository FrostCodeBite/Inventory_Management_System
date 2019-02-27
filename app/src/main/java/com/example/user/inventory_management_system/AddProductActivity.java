package com.example.user.inventory_management_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.inventory_management_system.Model.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    EditText pID, pName, pCategory, pQantity, pPrice;
    Button submitBtn;

    DatabaseReference databaseReference;

    //a list to store all the artist from firebase database
    List<Products> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_arrow_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                startActivity(new Intent(AddProductActivity.this,HomeActivity.class));
                finish();
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        pID = (EditText)findViewById(R.id.editPID);
        pName = (EditText)findViewById(R.id.editPName);
        pCategory = (EditText)findViewById(R.id.editPQuantity);
        pQantity = (EditText)findViewById(R.id.editPQuantity);
        pPrice = (EditText)findViewById(R.id.editPPriceUnit);

        productsList = new ArrayList<Products>();

        submitBtn = (Button)findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
    }

    private void addProduct() {
        String id = pID.getText().toString();
        String name = pName.getText().toString();
        String category = pCategory.getText().toString();
        String quantity = pQantity.getText().toString();
        String price = pPrice.getText().toString();

        if(!TextUtils.isEmpty(id)){
            Products products = new Products(id,name,category,quantity,price);
            databaseReference.child(id).setValue(products);
            Toast.makeText(this,"Product Add",Toast.LENGTH_LONG).show();
        }
    }
}
