import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class stickerFactory {
  public void create(InputStream inputStream, String fileName) throws Exception {
    // BufferedImage originalImage = ImageIO.read(new File("entrada/movie_1.jpg"));
    // InputStream inputStream = new URL(
    // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
    BufferedImage originalImage = ImageIO.read(inputStream);

    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    int newHeight = height + 200;
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setFont(font);
    graphics.setColor(Color.YELLOW);
    graphics.drawString("best movie ever!!!", 100, newHeight - 100);

    ImageIO.write(newImage, "png", new File(fileName));

  }
}