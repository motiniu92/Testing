package com.example.updateuserpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int PERMISSION_REQUEST_CODE = 1;
    private Button btnAccessPermisson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccessPermisson = findViewById(R.id.btnAccessPermissionId);
        btnAccessPermisson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User Profile access Permission System Here
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) +
                        ContextCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.READ_CONTACTS) +
                        ContextCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                    // When Permission not Granted
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                    Manifest.permission.READ_CONTACTS) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                    Manifest.permission.WRITE_CONTACTS)) {

                        // Create AlertDialog
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                                MainActivity.this
                        );
                        builder.setTitle("Grant those Permission");
                        builder.setMessage("Your Permission is Protected");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(
                                        MainActivity.this,
                                        new String[]{
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.READ_CONTACTS,
                                                Manifest.permission.WRITE_CONTACTS
                                        },
                                        PERMISSION_REQUEST_CODE
                                );
                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    } else {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_CONTACTS,
                                        Manifest.permission.WRITE_CONTACTS
                                },
                                PERMISSION_REQUEST_CODE
                        );
                    }
                } else {
                    // When Permission are already granted
                    Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permisson Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permisson Denied", Toast.LENGTH_SHORT).show();

            }
        }
    }


}