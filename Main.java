import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String filePath = "Caminho do diretório do arquivo contendo os links.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int imageCount = 1;

            while ((line = br.readLine()) != null) {
                String imageUrl = line.trim();

                downloadImage(imageUrl, "/Caminho da pasta no qual as imagens irão ficar/" + imageCount + ".jpg");
                imageCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadImage(String imageUrl, String destinationPath) throws Exception {
        File file = new File(destinationPath);
        file.createNewFile();
        URL url = new URL(imageUrl);
        try (InputStream in = new BufferedInputStream(url.openStream()); OutputStream out = new FileOutputStream(destinationPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}