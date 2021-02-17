package eg.gov.iti.contract.ui.helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageConverter {

    public static String getEncodedImage(File f)throws IOException {

        byte [] data = null;
        BufferedImage bImage = ImageIO.read(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        data= bos.toByteArray();

        String encoded = Base64.getEncoder().encodeToString(data);
        return  encoded;
    }

    public static Image getDecodedImage(String encodedImage){
        byte[] dst = Base64.getDecoder().decode(encodedImage);
        Image img = new Image(new ByteArrayInputStream(dst));
        return  img;
    }

}

