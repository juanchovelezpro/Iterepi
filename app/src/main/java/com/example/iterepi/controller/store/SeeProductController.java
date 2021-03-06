package com.example.iterepi.controller.store;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Item;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeeProductActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class SeeProductController implements View.OnClickListener{

    public static final int GALLERY_CALLBACK = 1;

    private SeeProductActivity activity;
    private HTTPSWebUtilDomi utilDomi;
    private Uri tempUri;
    private String photo;

    public SeeProductController(SeeProductActivity activity) {
        this.activity = activity;
        this.activity.getBackBtn3().setOnClickListener(this);
        this.activity.getUpdateDataBtn().setOnClickListener(this);
        activity.getPhoto().setOnClickListener(this);

        if(activity.getItem()==null){
            loadItem();
        }
    }

    private void loadItem() {
        String user_id = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("sellers").child(user_id)
                .child("places").child(activity.getPlaceId())
                .child("categories").child(activity.getCategoryId())
                .child("items").child(activity.getItemId());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Item item =  dataSnapshot.getValue(Item.class);

                activity.runOnUiThread(
                        ()->{
                            if(item!=null){
                                activity.setItem(item);
                                activity.getItemName().setText(item.getName());
                                activity.getNameProductTF().getEditText().setText(item.getName());
                                activity.getPriceProductTF().getEditText().setText(item.getPrice()+"");
                                activity.getInventoryQualityTF().getEditText().setText(item.getQuantity()+"");
                                activity.getDescriptionProductTF().getEditText().setText(item.getDescription());
                            }
                        }
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.backBtn:
                activity.finish();
                break;

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String name = activity.getNameProductTF().getEditText().getText().toString();
                String price = activity.getPriceProductTF().getEditText().getText().toString();
                String inventory = activity.getInventoryQualityTF().getEditText().getText().toString();
                String description = activity.getDescriptionProductTF().getEditText().getText().toString();

                if (name.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.name), Toast.LENGTH_LONG).show();
                    break;
                }
                if (price.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.price), Toast.LENGTH_LONG).show();
                    break;
                }
                if (inventory.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.inventory_quantity), Toast.LENGTH_LONG).show();
                    break;
                }
                if (description.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.description), Toast.LENGTH_LONG).show();
                    break;
                } else {


                    if (tempUri != null) {

                        activity.getItem().setName(name);
                        activity.getItem().setDescription(description);
                        activity.getItem().setPrice(Double.parseDouble(price));
                        activity.getItem().setQuantity(Integer.parseInt(inventory));


                        FirebaseStorage storage = FirebaseStorage.getInstance();

                        storage.getReference().child("sellers").child(user_id)
                                .child("places").child(activity.getPlaceId())
                                .child("categories").child(activity.getCategoryId())
                                .child("items").child(activity.getItemId()).child("photo").putFile(tempUri).addOnCompleteListener(task -> {

                            if (task.isSuccessful()) {

                                Toast.makeText(activity.getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();

                                storage.getReference().child("sellers").child(user_id)
                                        .child("places").child(activity.getPlaceId())
                                        .child("categories").child(activity.getCategoryId())
                                        .child("items").child(activity.getItemId()).child("photo").getDownloadUrl().addOnSuccessListener(uri -> {


                                    photo = uri.toString();

                                    activity.getItem().setPhoto(photo);

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("sellers").child(user_id)
                                            .child("places").child(activity.getPlaceId())
                                            .child("categories").child(activity.getCategoryId())
                                            .child("items").child(activity.getItemId()).setValue(activity.getItem());


                                    Toast.makeText(activity, activity.getString(R.string.update_successful), Toast.LENGTH_LONG).show();
                                    Intent s = new Intent(activity, SeeCategoryActivity.class);
                                    s.putExtra("placeId", activity.getPlaceId());
                                    s.putExtra("categoryId", activity.getCategoryId());
                                    activity.startActivity(s);
                                    activity.finish();


                                });


                            }


                        });


                    }
                }
                break;

            case R.id.photo:

                Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                gal.setType("image/*");
                activity.startActivityForResult(gal, GALLERY_CALLBACK);

                break;
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case GALLERY_CALLBACK:
                    tempUri = data.getData();

                    try {
                        Bitmap image = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), tempUri);
//                        activity.getAddImageProductBtn().setImageBitmap(image);
                        Glide.with(activity.getApplicationContext()).load(tempUri).centerCrop().into(activity.getPhoto());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;

            }


        }


    }
}
