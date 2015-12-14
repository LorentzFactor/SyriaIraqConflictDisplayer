package ConflictFrames;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;

public class ConflictFrame
{
	private Elements tableElements;
	private URL url;
	private String stringDate;
	protected Date date;
	private String mapLocation;
	private String stringURL;
	
	public ConflictFrame(Element row, String mapFolder, DateFormat format) throws IOException, ParseException
	{
		tableElements = row.select("td");
		stringURL = "https:" + tableElements.get(1).select("a").attr("href");
		url = new  URL("https:" + tableElements.get(1).select("a").attr("href"));
		stringDate = tableElements.get(1).select("a").text();
		date = format.parse(stringDate);
		mapLocation = mapFolder + "\\" + format.toString();
	}
	
	public Date getEndDate()
	{
		return date;
	}
	
	public BufferedImage getMap() throws IOException
	{
		BufferedImage map = ImageIO.read(url);
		BufferedImage newImage = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(map, 0, 0, null);
		g.dispose();
		return newImage;
	}
	
	public String getStringURL()
	{
		return stringURL;
	}

	public URL getUrl()
	{
		return url;
	}

	public String getEndStringDate() 
	{
		return stringDate;
	}
}