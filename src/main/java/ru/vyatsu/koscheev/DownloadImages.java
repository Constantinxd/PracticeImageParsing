package ru.vyatsu.koscheev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class DownloadImages
        implements OnNewDataHandler<ArrayList<String>> {
    private int count = 0;
    private final String folder;

    public DownloadImages(ParserSettings parserSettings) {
        this.folder = parserSettings.folder + File.separator + parserSettings.request;
    }

    @Override
    public void OnNewData(Object sender, ArrayList<String> args) {
        mkdir(folder);

        for (String l : args)
            loadImage(l);
    }

    private boolean mkdir(String folder) {
        File f = new File(folder);
        return f.mkdirs();
    }

    private Image loadImage(String URL) {
        try {
            String fileName = ++count + ".png";
            BufferedImage img = ImageIO.read(new URL(URL));
            File file = new File(folder + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageIO.write(img, "png", file);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
