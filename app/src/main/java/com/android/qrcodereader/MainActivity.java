package com.android.qrcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnScan;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        btnScan = (Button) findViewById(R.id.btn_scan);
        txtResult = (TextView) findViewById(R.id.txtResult);

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.qr_code);
        imageView.setImageBitmap(bitmap);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

                Frame frame = new Frame.Builder()
                        .setBitmap(bitmap).build();
                SparseArray<Barcode> barcodeSparseArray = barcodeDetector.detect(frame);

                Barcode result = barcodeSparseArray.valueAt(0);
                txtResult.setText(result.rawValue);
            }
        });

    }
}