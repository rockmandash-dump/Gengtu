
import java.io.File;
import java.net.URL;
import java.lang.String;


public class writetext {

    public static void main(String[] args) throws Exception {
        final BufferedImage image = ImageIO.read(new URL(
                "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString("Hello World!", 100, 100);
        g.dispose();

        ImageIO.write(image, "png", new File("test.png"));
    }
}
