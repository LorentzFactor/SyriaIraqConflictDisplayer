import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.xuggle.xuggler.IContainer;

import ConflictFrames.RangeConflictFrame;

public class ImagesToVideos 
{
	//private ArrayList<DataInput> dataInputs = new ArrayList<DataInput>();
	private IContainer video = IContainer.make();
	
	public ImagesToVideos(Iterable<RangeConflictFrame> inputs) throws IOException
	{
		for(RangeConflictFrame frame:inputs)
		{
			video.open(ImageIO.createImageInputStream(frame.getUrl()), null);
		}
	}
}
