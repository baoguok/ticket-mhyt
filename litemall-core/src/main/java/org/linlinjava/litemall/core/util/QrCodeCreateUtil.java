package org.linlinjava.litemall.core.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.linlinjava.litemall.core.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;

@Service
public class QrCodeCreateUtil {
	@Autowired
    private StorageService storageService;

    /*
     * 生成QR图片
     * IN：宽高，图片格式，输出文件路径，内容
     * OUT：生成状态
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createQR(int qrCodeSize,String format,String outPath,String content) {
        HashMap ha = new HashMap();
        ha.put(EncodeHintType.CHARACTER_SET, "utf-8"); //编码格式
        ha.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); //纠错等级【L，M，Q，H】
        ha.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, ha);
            Path file = new File(outPath).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return storageService.store(new FileInputStream(new File(outPath)), bitMatrix.getRowSize(), "image/jpeg", content+".jpg");
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

	/**
     * 生成包含字符串信息的二维码图片
     * @param outputStream 文件输出流路径
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @param imageFormat 二维码的格式
     * @throws WriterException 
     * @throws IOException 
     */
    public String ccreateQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException{
            //设置二维码纠错级别ＭＡＰ
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();  
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别  
            QRCodeWriter qrCodeWriter = new QRCodeWriter();  
            //创建比特矩阵(位矩阵)的QR码编码的字符串  
            BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);  
            // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
            int matrixWidth = byteMatrix.getWidth();  
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);  
            image.createGraphics();  
            Graphics2D graphics = (Graphics2D) image.getGraphics();  
            graphics.setColor(Color.WHITE);  
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);  
            // 使用比特矩阵画并保存图像
            graphics.setColor(Color.BLACK);  
            for (int i = 0; i < matrixWidth; i++){
                for (int j = 0; j < matrixWidth; j++){
                    if (byteMatrix.get(i, j)){
                        graphics.fillRect(i-100, j-100, 1, 1);  
                    }
                }
            }
            Boolean type = ImageIO.write(image, imageFormat, outputStream);
            outputStream.close();
            String url = storageService.store(new FileInputStream(new File("storage\\"+content+".jpg")), 36000, "image/jpeg", content+".jpg");
            System.out.println(url);
            return url;  
    }  
      
    /**
     * 读二维码并输出携带的信息
     */
    public static void readQrCode(InputStream inputStream) throws IOException{  
        //从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);  
        //将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        QRCodeReader reader = new QRCodeReader();  
        Result result = null ;  
        try {
         result = reader.decode(bitmap);  
        } catch (ReaderException e) {
            e.printStackTrace();  
        }
        System.out.println(result.getText());  
    }
}
