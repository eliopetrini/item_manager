package com.itemmanager.qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.valueOf;

public class Label {
    private static final int LABEL_HEIGHT = 200;
    private static final int LABEL_WIDTH = LABEL_HEIGHT*3;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
    private final int itemID;
    private final String itemName;
    private final String itemDescription;
    private final String itemURL;
    private String type;

    public Label(int itemID, String itemName, String itemDescription, String type, String appURL) {
        this.type = type;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        if (type.equals("item")) {
            itemURL = appURL.concat("/item/").concat(valueOf(itemID));
        } else {
            itemURL = appURL.concat("/items/").concat(valueOf(itemID));
        }
    }

    private BufferedImage generateLabelText() throws IOException {
        String labelType = "";
        BufferedImage image = new BufferedImage(LABEL_WIDTH-LABEL_HEIGHT, LABEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(itemName, 10, 60);
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        g.drawString(itemDescription, 10, 100);
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        if (type.equals("location")) {
           labelType = " - LOC";
        }
        g.drawString("ID: ".concat(String.format("%06d", itemID)).concat(" - ").concat(simpleDateFormat.format(new Date())).concat(labelType), 10, image.getHeight()-30);
        return image;
    }

    public BufferedImage generateQRCode() throws Exception {
        QRCode qrcode = new QRCode(itemURL, LABEL_HEIGHT, LABEL_HEIGHT);
        return qrcode.generate();
    }

    public BufferedImage generateLabel() throws Exception {
        BufferedImage QRCode = generateQRCode();
        BufferedImage textLabel = generateLabelText();
        BufferedImage combined = new BufferedImage(LABEL_WIDTH, LABEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = combined.getGraphics();
        g.drawImage(QRCode, 0, 0, null);
        g.drawImage(textLabel, LABEL_HEIGHT, 0, null);
        File outputFile = new File("label.jpg");
        return combined;
        //ImageIO.write(combined, "jpg", outputFile);
    }

}
