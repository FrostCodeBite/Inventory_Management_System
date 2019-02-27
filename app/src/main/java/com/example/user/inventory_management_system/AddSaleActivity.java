package com.example.user.inventory_management_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.inventory_management_system.Model.Products;
import com.example.user.inventory_management_system.Model.Sales;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AddSaleActivity extends AppCompatActivity {
    EditText editPID, editPName, editPCategory, editPQuantity;
    Button addSalebtn;

    DatabaseReference databaseReference;

    //a list to store all the artist from firebase database
    List<Sales> salesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        editPID = (EditText)findViewById(R.id.editPIDSale);
        editPName = (EditText)findViewById(R.id.editPNameSale);
        editPCategory = (EditText)findViewById(R.id.editPIDCategorySale);
        editPQuantity = (EditText)findViewById(R.id.editPQuantitySale);

        salesList = new ArrayList<Sales>();

        addSalebtn = (Button)findViewById(R.id.addSalebtn);
        addSalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
    }

    private void addProduct() {
        String id = editPID.getText().toString();
        String name = editPName.getText().toString();
        String category = editPCategory.getText().toString();
        String quantity = editPQuantity.getText().toString();

        if(!TextUtils.isEmpty(id)){
            Sales sales = new Sales(id,name,category,quantity);
            databaseReference.child(id).setValue(sales);
            Toast.makeText(this,"Product Add",Toast.LENGTH_LONG).show();
        }
    }
}
