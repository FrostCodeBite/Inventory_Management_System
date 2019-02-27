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

public class DeleteProductActivity extends AppCompatActivity {
    EditText pID, pName, pCategory, pQantity, pPrice;
    Button submitBtn;

    DatabaseReference databaseReference;

    //a list to store all the artist from firebase database
    List<Products> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        toolbar.setNavigationIcon(R.drawable.back_arrow_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                startActivity(new Intent(DeleteProductActivity.this,HomeActivity.class));
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

//        final String id = pID.getText().toString();
//        final String name = pName.getText().toString();
//        final String category = pCategory.getText().toString();

        submitBtn = (Button)findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = pID.getText().toString();
//                final String name = pName.getText().toString();
//                final String category = pCategory.getText().toString();

//                if(!TextUtils.isEmpty(id)){
                    deleteProductID(id);
//                }else if(!TextUtils.isEmpty(name)){
//                    deleteProductName(name);
//                }else if(!TextUtils.isEmpty(category)){
//                    deleteProductCategory(category);
//                }
            }
        });
    }

    private boolean deleteProductCategory(String category) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(category);
        databaseReference.removeValue();

        Toast.makeText(getApplicationContext(),"Category removed",Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteProductName(String name) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(name);
        databaseReference.removeValue();

        Toast.makeText(getApplicationContext(),"Name removed",Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteProductID(String id) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(id);
        databaseReference.removeValue();

        Toast.makeText(getApplicationContext(),"ID removed",Toast.LENGTH_LONG).show();
        return true;

    }
}
