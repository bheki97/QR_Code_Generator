package za.ac.bheki97.qr_code_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import za.ac.bheki97.qr_code_generator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setGenerateBtnClickListener();
    }

    private void setGenerateBtnClickListener() {
        binding.generateBtn.setOnClickListener(v  ->{
            String txt = binding.qrCodeEditText.getText().toString();
            if(txt !=null&& !txt.isEmpty()){
                generateQRcode(txt);
            }else{
                Toast.makeText(MainActivity.this, "Text Cannot be Null or Empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateQRcode(String txt) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(txt.trim(), BarcodeFormat.QR_CODE,1200,1200);

            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            binding.qrCodeImg.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}