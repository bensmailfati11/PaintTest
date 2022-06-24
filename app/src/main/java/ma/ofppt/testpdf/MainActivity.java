package ma.ofppt.testpdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        createPdf();
    }


        private void createPdf(){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PdfDocument myPdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();

                    PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
                    PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
                    Canvas canvas = myPage1.getCanvas();
                    canvas.drawText("welcooooome", 40, 50, myPaint);
                    myPdfDocument.finishPage(myPage1);


                    File file = new File(Environment.getExternalStorageDirectory(), "/firstpdf.pdf");
                    try {
                        myPdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();


                    }
                    myPdfDocument.close();
                }
            });
        }

    }
