package com.slewsoft.tabxperim;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.slewsoft.dbhelper.DBHelper;
import com.slewsoft.dbhelper.Utils;

public class RetrieveImageFragment extends Fragment {
    private EditText edt_name;
    private Button btn_select;
    private ImageView imageView;
    private DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_display, container, false);

        edt_name = view.findViewById(R.id.edt_name);
        imageView = view.findViewById(R.id.image_view);
        btn_select = view.findViewById(R.id.btn_select);
        dbHelper = new DBHelper(getActivity());

        btn_select.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                byte[] data = dbHelper.getBitmapByName(name);

                if(data == null) {
                    Toast.makeText(getActivity(), name + " not found", Toast.LENGTH_SHORT).show();
                } else {
                    Bitmap bitMap = Utils.getImage(data);
                    imageView.setImageBitmap(bitMap);
                }
            }
        });
        return view;
    }
}
