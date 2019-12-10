package com.example.mylibraydemo;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class KeyboaedDemo extends LinearLayout implements View.OnClickListener{
    public KeyboaedDemo(Context context) {
        super(context);
        init();
    }



    public KeyboaedDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboaedDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KeyboaedDemo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.button2){
            Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
        }
    }






      /* Button b1,b2,b3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyboard_demo);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyboaedDemo.this, "1", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyboaedDemo.this, "2", Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyboaedDemo.this, "3", Toast.LENGTH_SHORT).show();
            }
        });


    }*/
}
