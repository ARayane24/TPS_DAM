package com.example.tp1.TP5;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tp1.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

public class TP5Adapter extends ArrayAdapter<Etudiant> {
    ArrayList<Etudiant> cursor;
    public TP5Adapter(Context context, ArrayList<Etudiant> c) {
        super(context, 0,c);
        cursor = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tp5_list_item, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.mat)).setText(cursor.get(position).mat);
        ((TextView)convertView.findViewById(R.id.prenom)).setText(cursor.get(position).prenom);
        ((TextView)convertView.findViewById(R.id.nom)).setText(cursor.get(position).nom);

        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        byte[] blob = cursor.get(position).photo;
        Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
        Bitmap resized = Bitmap.createScaledBitmap(bmp,100,100,false);
        img.setImageBitmap(resized);

        return convertView;
    }


}
