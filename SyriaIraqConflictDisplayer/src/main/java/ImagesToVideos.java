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

import ConflictFrames.RangeConflictFrame;

public class ImagesToVideos 
{
	 IMediaWriter writer = ToolFactory.makeWriter("output.flv");
	
	public ImagesToVideos(ConflictFrameList inputs) throws IOException
	{
		writer.addVideoStream(0, 0, 722, 552);
		long timeframe = inputs.get(inputs.size()-1).getDate().getTime() - inputs.get(0).getDate().getTime();
		long videoLength = 60*5;
		for(RangeConflictFrame frame:inputs)
		{
			try
			{
				writer.encodeVideo(0, frame.getMap(), 1000, TimeUnit.NANOSECONDS);
			} catch(IIOException e)
			{
				System.out.println("Failed to load file: " + frame.getStringURL() + " as a BufferedImage. Moving on the the next one.");
			}
		}
		writer.flush();
	}
}
