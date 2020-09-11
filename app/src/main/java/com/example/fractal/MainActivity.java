package com.example.fractal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import static java.lang.Math.PI;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    static int angle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Vista vista= new Vista(this);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout =
                (RelativeLayout) findViewById(R.id.relativeLayout);
        mainLayout.addView(vista);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                angle=i/2;
                vista.invalidate();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public class Vista extends View {

        public Vista(Context context){
            super(context);
        }

        public void onDraw(Canvas canvas){
            Paint paint=new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(Color.GREEN);
            int ancho=canvas.getWidth();
            int alto=canvas.getHeight();
            canvas.translate(ancho/2,alto);
            branch(200,canvas,paint);

        }

        void branch(float len,Canvas canvas,Paint paint){
            canvas.drawLine(0, 0, 0,-len, paint);
            canvas.translate(0,-len);
            if(len>1) {
                canvas.save();
                canvas.rotate(angle);
                branch((float) (len*0.7),canvas,paint);
                canvas.restore();
                canvas.save();
                canvas.rotate(-angle);
                branch((float) (len*0.7),canvas,paint);
                canvas.restore();
            }
        }

    }
};