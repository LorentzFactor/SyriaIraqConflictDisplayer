package ConflictFrames;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		//BufferedImage image = ImageIO.read(url);
		//File f = new File(mapLocation);
		//if(!f.exists())
		//{
		//	f.createNewFile();
		//}
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public BufferedImage getMap() throws IOException
	{
		return ImageIO.read(new File(mapLocation));
	}
	
	public String getStringURL()
	{
		return stringURL;
	}

	public URL getUrl()
	{
		return url;
	}

	public String getStringDate() 
	{
		return stringDate;
	}
}