package com.slewsoft.tabxperim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.slewsoft.dbhelper.DBHelper;
import com.slewsoft.dbhelper.Utils;

import static android.app.Activity.RESULT_OK;

public class SelectImageFragment extends Fragment {
    private DBHelper dbHelper;
    private ImageView imageView;
    private static final int SELECT_PHOTO = 7777;
    private Button btn_choose, btn_save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        View view = inflater.inflate(R.layout.image_select, container, false);

        imageView = view.findViewById(R.id.image_view);
        btn_choose = view.findViewById(R.id.select_image);
        btn_save = view.findViewById(R.id.save_image);
        dbHelper = new DBHelper(getActivity());

        btn_choose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PHOTO);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convert src of image view to Bitmap
                final Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                // Create dialog to enter name to save
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Enter name");

                final EditText editText = new EditText(getActivity());
                builder.setView(editText);

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editText.getText().toString();
                        if(TextUtils.isEmpty(name)) {
                            Toast.makeText(getActivity(), "Name can't be empty", Toast.LENGTH_SHORT).show();
                        } else {
                            dbHelper.addBitmap(name, Utils.getBytes(bitmap));
                            Toast.makeText(getActivity(), "Save success!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.show();
            }

        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            imageView.setImageURI(pickedImage);
            btn_save.setEnabled(true);
        }
    }
}
