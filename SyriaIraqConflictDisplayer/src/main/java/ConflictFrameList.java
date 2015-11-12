import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ConflictFrames.ConflictFrame;
import ConflictFrames.RangeConflictFrame;

public class ConflictFrameList implements Iterable<RangeConflictFrame>
{
	private ArrayList<RangeConflictFrame> frames = new ArrayList<RangeConflictFrame>();
	private DateFormat format = new SimpleDateFormat("HH:mm, dd MMMMMMMMM YYYY");
	private GregorianCalendar calendar = new GregorianCalendar();
	
	public ConflictFrameList(String fileName, String fileLocation) throws ParseException
	{
		String mapLocation = fileLocation + "\\mapFolder";
		File f = new File(mapLocation);
		/*If the folder already exists, clears the  
		folder of any files that already were in there,
		otherwise, it makes the directory*/
		if(f.exists())
		{
			if(f.list().length > 0)
			{
				for(File file : f.listFiles())
				{
					file.delete();
				}
			}
		}
		
		else
		{
			f.mkdir();
		}
		
		String html = "https://en.wikipedia.org/w/index.php?title=File:" + fileName + "#filehistory";
		  try 
		  {
		         Document doc = Jsoup.connect(html).get();
		         Elements tableRowElements = doc.select("#mw-imagepage-section-filehistory > table > tbody");
		         for(int i = 1; i < tableRowElements.get(0).children().size(); i++)
		         {
		        	 if(i != tableRowElements.get(0).children().size() - 1)
		        	 { 
		        		 frames.add(new RangeConflictFrame(tableRowElements.get(0).child(i), tableRowElements.get(0).child(i+1), mapLocation, format));
		        	 }
		        	 
		        	 else
		        	 {
		        		 frames.add(new RangeConflictFrame(tableRowElements.get(0).child(i), new Date(), mapLocation, format));
		        	 }
		         }
		         frames.add(new RangeConflictFrame( tableRowElements.get(0).child(1), tableRowElements.get(0).child(2), mapLocation, format));
		         System.out.println("here");
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
			  System.exit(1);
		  }
	}
	
	public ConflictFrame get(int i)
	{
		return frames.get(i);
	}
	
	public int size()
	{
		return frames.size();
	}

	public Iterator<RangeConflictFrame> iterator() 
	{
		return frames.iterator();
	}
}