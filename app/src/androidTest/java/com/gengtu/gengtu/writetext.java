import java.io.File;
import java.net.URL;
import java.lang.String;

public class writetext {
        BufferedImage image = ImageIO.read(new URL("http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));
        //用BufferedImage來抓取圖檔並存在image， 可用new File(String file)抓檔案
        int fontsize = 12;

        Graphics g = image.getGraphics(); //用getGraphics來指定image給g
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontsize)); //設定字型樣式
        g.drawString("Hello World!", 100, 100); //寫下所需要的字串，這裡為Hello World!  並且加上座標X與Y的數值。
        g.dispose();//釋放系統資源，並且停止繪圖。

        ImageIO.write(image, "jpeg", new File("test.jpg"));//把加字的圖存成test.jpg
}
