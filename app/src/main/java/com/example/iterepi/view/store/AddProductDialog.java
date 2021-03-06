package com.example.iterepi.view.store;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.AddProductController;
import com.google.android.material.textfield.TextInputLayout;

public class AddProductDialog extends AppCompatActivity {

    private ImageButton closeBtn;
    private ImageButton addImageProductBtn;

    private TextInputLayout nameProductTF;
    private TextInputLayout priceProductTF;
    private TextInputLayout inventoryQualityTF;
    private TextInputLayout descriptionProductTF;

    private Spinner placeOfProductSP;
    private Spinner categoryOfProductSP;

    private Button addProductBtn;

    private AddProductController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_product);

        closeBtn = findViewById(R.id.closeBtn);
        addImageProductBtn = findViewById(R.id.addImageProductBtn);

        nameProductTF = findViewById(R.id.nameProductTF);
        priceProductTF = findViewById(R.id.priceProductTF);
        inventoryQualityTF = findViewById(R.id.inventoryQualityTF);
        descriptionProductTF = findViewById(R.id.descriptionProduCtTF);

        placeOfProductSP = findViewById(R.id.placeOfProductSP);
        categoryOfProductSP = findViewById(R.id.categoryOfProductSP);

        addProductBtn = findViewById(R.id.addProductBtn);

        controller = new AddProductController(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode, resultCode, data);
    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public ImageButton getAddImageProductBtn() {
        return addImageProductBtn;
    }

    public TextInputLayout getNameProductTF() {
        return nameProductTF;
    }

    public TextInputLayout getPriceProductTF() {
        return priceProductTF;
    }

    public TextInputLayout getInventoryQualityTF() {
        return inventoryQualityTF;
    }

    public TextInputLayout getDescriptionProductTF() {
        return descriptionProductTF;
    }

    public Spinner getPlaceOfProductSP() {
        return placeOfProductSP;
    }

    public Spinner getCategoryOfProductSP() {
        return categoryOfProductSP;
    }

    public Button getAddProductBtn() {
        return addProductBtn;
    }
}
