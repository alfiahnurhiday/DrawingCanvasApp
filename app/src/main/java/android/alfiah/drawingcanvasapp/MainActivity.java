package android.alfiah.drawingcanvasapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean isDrawInit;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onResume() {
        super.onResume();
        if(!isDrawInit){
            initDraw();
            final Button clearButton = findViewById(R.id.clear);
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paintView.clear();
                }
            });

            final TextView brushSize = findViewById(R.id.brushSize);
            brushSize.setText("20");

            final TextView brushColor = findViewById(R.id.brushColor);
            brushColor.setText("black");

            final Button incBrushSize = findViewById(R.id.brushSizeInc);
            incBrushSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paintView.brushSize++;
                    brushSize.setText(String.valueOf(paintView.brushSize));
                }
            });

            final Button descBrushSize = findViewById(R.id.brushSizeDec);
            descBrushSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(paintView.brushSize>1) {
                        paintView.brushSize--;
                        brushSize.setText(String.valueOf(paintView.brushSize));
                    }
                }
            });

            final Button changeColor = findViewById(R.id.changeColor);
            changeColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(paintView.brushColor == Color.BLACK){
                        paintView.brushColor = Color.RED;
                        brushColor.setText("red");
                    }else if(paintView.brushColor == Color.RED) {
                        paintView.brushColor = Color.WHITE;
                        brushColor.setText("white");
                    }else if(paintView.brushColor == Color.WHITE){
                        paintView.brushColor = Color.BLUE;
                        brushColor.setText("blue");
                    }else if(paintView.brushColor == Color.BLUE){
                        paintView.brushColor = Color.YELLOW;
                        brushColor.setText("yellow");
                    }else if(paintView.brushColor == Color.YELLOW) {
                        paintView.brushColor = Color.GREEN;
                        brushColor.setText("green");
                    }else if(paintView.brushColor == Color.GREEN) {
                        paintView.brushColor = Color.GRAY;
                        brushColor.setText("gray");
                    }else{
                        paintView.brushColor = Color.BLACK;
                        brushColor.setText("black");
                    }
                }
            });



            isDrawInit = true;
        }
    }

    private PaintView paintView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initDraw(){
        paintView =  findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        paintView.init(metrics);
    }

    static class FingerPath {
        int color;
        int strokeWidth;
        Path path;

        FingerPath(int color, int strokeWidth, Path path){
            this.color = color;
            this.strokeWidth = strokeWidth;
            this.path = path;
        }
    }
}