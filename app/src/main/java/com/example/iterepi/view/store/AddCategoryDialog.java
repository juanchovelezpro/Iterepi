package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.AddCategoryController;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddCategoryDialog extends AppCompatActivity {


    private ImageButton closeBtn;
    private TextInputLayout categoryNameTF;
    private TextInputLayout placeNameTF;

    private Button addCategoryBtn;
    private AddCategoryController controller;

    private Place place;
    private int placePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_category);
        this.place = (Place) getIntent().getExtras().getSerializable("place");
        placePosition = (Integer) getIntent().getExtras().getSerializable("placePosition");

        closeBtn = findViewById(R.id.closeBtn);
        categoryNameTF = findViewById(R.id.categoryNameTF);
        placeNameTF = findViewById(R.id.placeNameTF);
        placeNameTF.getEditText().setText(place.getName());
        addCategoryBtn = findViewById(R.id.updateDataBtn);

        this.controller = new AddCategoryController(this);

    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public void setCloseBtn(ImageButton closeBtn) {
        this.closeBtn = closeBtn;
    }

    public TextInputLayout getCategoryNameTF() {
        return categoryNameTF;
    }

    public void setCategoryNameTF(TextInputLayout categoryNameTF) {
        this.categoryNameTF = categoryNameTF;
    }

    public Button getAddCategoryBtn() {
        return addCategoryBtn;
    }

    public void setAddCategoryBtn(Button addPlaceBtn) {
        this.addCategoryBtn = addPlaceBtn;
    }

    public Place getPlace() {
        return place;
    }

    public int getPlacePosition() {
        return placePosition;
    }
}
