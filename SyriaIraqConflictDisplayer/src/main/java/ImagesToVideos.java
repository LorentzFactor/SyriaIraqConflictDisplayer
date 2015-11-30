import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.IContainer;

import ConflictFrames.RangeConflictFrame;

public class ImagesToVideos 
{
	//private ArrayList<DataInput> dataInputs = new ArrayList<DataInput>();
	 IMediaWriter writer = ToolFactory.makeWriter("output.mp4");
	
	public ImagesToVideos(ConflictFrameList inputs) throws IOException
	{
		writer.addVideoStream(0, 0, 722, 551);
		long timeframe = inputs.get(inputs.size()-1).getDate().getTime() - inputs.get(0).getDate().getTime();
		long videoLength = 60*5;
		for(RangeConflictFrame frame:inputs)
		{
			writer.encodeVideo(0, frame.getMap(), 1, TimeUnit.NANOSECONDS);
		}
		
		writer.flush();
	}
}
