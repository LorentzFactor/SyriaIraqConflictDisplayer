import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
	 IMediaWriter writer = ToolFactory.makeWriter("output2.flv");
	
	public ImagesToVideos(ConflictFrameList inputs) throws IOException
	{
		writer.addVideoStream(0, 0, 722, 552);
		long timeframe = inputs.get(inputs.size()-1).getDate().getTime() - inputs.get(0).getDate().getTime();
		long videoLength = 60*5;
		int i = 0;
		for(RangeConflictFrame frame:inputs)
		{
			try
			{
				System.out.println("Working on image: " + i);
				writer.encodeVideo(0, frame.getMap(), 1000*i, TimeUnit.MILLISECONDS);
				i++;
			} catch(Exception e)
			{
				System.out.println("Failed to load file: " + frame.getStringURL() + " as a BufferedImage. Moving on the the next one.");
			}
		}
		writer.flush();
		writer.close();
	}
}