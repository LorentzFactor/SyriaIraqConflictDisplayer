import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;

import ConflictFrames.RangeConflictFrame;

public class ImagesToVideos 
{
	 IMediaWriter writer = ToolFactory.makeWriter("output.flv");
	 private int position = 0;
	 private GregorianCalendar cal = new GregorianCalendar();
	 private GregorianCalendar cal2 = new GregorianCalendar();
	
	public ImagesToVideos(ConflictFrameList inputs) throws IOException
	{
		writer.addVideoStream(0, 0, 722, 552);
		long timeframe = inputs.get(inputs.size()-1).getDate().getTime() - inputs.get(0).getDate().getTime();
		long videoLength = 60*5;
		cal.setTime(inputs.get(1).getDate()); //TODO create error trapping for this line
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		//cal.set(cal.get(cal.YEAR), cal.get(cal.MONTH), cal.get(cal.DAY_OF_MONTH));
		updateCal2();
		System.out.println("here2");
		int i = 1;
		while(!cal2.after(inputs.get(inputs.size()-1).getEndDate()))
		{
			RangeConflictFrame frame = inputs.get(i);
			
			if(frame.getEndDate().before(cal2.getTime()))
			{
				long longestTime = getAmountOfTimeInInterval(cal.getTime(), cal2.getTime(), frame.getDate(), frame.getEndDate());
				boolean c = true;
				for(int j = 1; j == 1; j = j)
				{
					if(longestTime < getAmountOfTimeInInterval(cal.getTime(), cal2.getTime(), inputs.get(i+j).getDate(), inputs.get(i+j).getEndDate()))
					{
						i++;
						System.out.println("currently on frame " + i);
						frame = inputs.get(i);
						longestTime = getAmountOfTimeInInterval(cal.getTime(), cal2.getTime(), frame.getDate(), frame.getEndDate());
					}
					
					else
					{
						if(getAmountOfTimeInInterval(cal.getTime(), cal2.getTime(), inputs.get(i).getDate(), inputs.get(i).getEndDate()) == 0)
						{
							break;
						}
					}
				}
				writeFrame(frame);
			}
			
			else
			{
				writeFrame(frame);
				cal.add(cal.DATE, 1);
				updateCal2();
			}
		}
		writer.flush();
		writer.close();
	}
	
	private void updateCal2()
	{
		cal2.setTime(cal.getTime());
		cal2.add(cal2.DATE, 1);
	}
	
	private long getAmountOfTimeInInterval(Date begin, Date end, Date oBegin, Date oEnd)
	{
		Date beginner;
		Date ender;
		
		//if begin's before oBegin
		if(begin.compareTo(oBegin) <= 0)
		{
			//if oBegin's before end
			if(oBegin.compareTo(end) <= 0)
			{
				beginner = oBegin;
			}
			
			else
			{
				return 0;
			}
		}
		
		else
		{
			beginner = begin;
		}
		
		if(oEnd.compareTo(end) <= 0)
		{
			if(begin.compareTo(oEnd) <= 0)
			{
				ender = oEnd;
			}
			
			else
			{
				return 0;
			}
		}
		
		else
		{
			ender = end;
		}
		
		return ender.getTime() - beginner.getTime();
	}
	
	private void writeFrame(RangeConflictFrame frame)
	{
		try
		{
			writer.encodeVideo(0, frame.getMap(), 1000*position, TimeUnit.MILLISECONDS);
			position++;
		} 
		catch(Exception e)
		{
			System.out.println("Failed to load file: " + frame.getStringURL() + " as a BufferedImage. Moving on the the next one.");
		}
	}
}