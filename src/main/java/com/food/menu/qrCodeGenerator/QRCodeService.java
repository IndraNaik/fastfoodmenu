package com.food.menu.qrCodeGenerator;

import org.springframework.stereotype.Service;

import java.io.IOException;
import com.google.zxing.WriterException;

@Service
public class QRCodeService {

    public void generateQRCode(String text, String filePath) throws WriterException, IOException {
        int width = 350;
        int height = 350;

        // Call the static method from QRCodeGenerator class
        QRCodeGenerator.generateQRCodeImage(text, width, height, filePath);
    }
}

