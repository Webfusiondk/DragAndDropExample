package com.example.draganddrop9side;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView drag,dropZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drag = (ImageView) findViewById(R.id.drag);
        dropZone = (ImageView) findViewById(R.id.dropZone);

        drag.setOnLongClickListener(longClickListener);
        dropZone.setOnDragListener(dragListener);
        Log.d("Test","test");
    }

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            Log.e("Drag", "num : " +  action);
            switch (action){
                case  DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    break;
                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    Toast.makeText(MainActivity.this,"Item droped" , Toast.LENGTH_LONG).show();
                    v.setBackgroundColor(Color.WHITE);
                    v.invalidate();
                    break;
            }
            return true;
        }
    };
        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("longClick","Clicking");
                ClipData clipData = ClipData.newPlainText("","");
                View.DragShadowBuilder myShadow = new MyDragShadow(v);
                v.startDrag(clipData,myShadow,v,0);
                return true;
            }
        };
}