package com.jrbac.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.junit.Test;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class Text {
    private final String path = "E:/";

    // 创建二维码
    @Test
    public void generate() {
        try {
            // String content = "https://u.wechat.com/MKGPhkzg_QulXoC-_wzdNjA";
            String content = "wxp://f2f0S2bThxF5NHfdY3JMpxi1EKRGOgyI7pLs";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
            File file1 = new File(path, "餐巾纸.jpg");
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 解析二维码
    @Test
    public void analysis() {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            File file = new File(path + "餐巾纸.jpg");
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap, hints);
            System.out.println("result = " + result.toString());
            System.out.println("resultFormat = " + result.getBarcodeFormat());
            System.out.println("resultText = " + result.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
