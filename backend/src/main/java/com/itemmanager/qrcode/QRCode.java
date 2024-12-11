package com.itemmanager.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class QRCode {

    private String barcodeText;
    private int codeWidth;
    private int codeHeight;

    public QRCode(String barcodeText, int width, int height) {
        this.barcodeText = barcodeText;
        this.codeWidth = width;
        this.codeHeight = height;
    }

    public BufferedImage generate() throws Exception {
        //kommentar
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, codeWidth, codeHeight);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
