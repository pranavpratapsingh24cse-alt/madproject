package com.example.q4_photogallery;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private ImageView detailImageView;
    private TextView tvName, tvPath, tvSize, tvDate;
    private Button btnDelete;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImageView = findViewById(R.id.detailImageView);
        tvName = findViewById(R.id.tvName);
        tvPath = findViewById(R.id.tvPath);
        tvSize = findViewById(R.id.tvSize);
        tvDate = findViewById(R.id.tvDate);
        btnDelete = findViewById(R.id.btnDelete);

        imagePath = getIntent().getStringExtra("path");

        if (imagePath != null) {
            displayDetails();
        }

        btnDelete.setOnClickListener(v -> showDeleteConfirmation());
    }

    private void displayDetails() {
        File file = new File(imagePath);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            detailImageView.setImageBitmap(bitmap);

            tvName.setText("Name: " + file.getName());
            tvPath.setText("Path: " + file.getAbsolutePath());
            tvSize.setText("Size: " + (file.length() / 1024) + " KB");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            tvDate.setText("Date Taken: " + sdf.format(new Date(file.lastModified())));
        }
    }

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure you want to delete this image?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    deleteImage();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteImage() {
        File file = new File(imagePath);
        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to delete image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
