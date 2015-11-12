import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.media.MediaLocator;
import javax.media.protocol.DataSource;
import javax.media.protocol.URLDataSource;

import ConflictFrames.RangeConflictFrame;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;

public class Main {
	public static void main(String[] args) throws ParseException, IOException
	{
		ConflictFrameList frames = new ConflictFrameList("Syrian,_Iraqi,_and_Lebanese_insurgencies.png", "H:");
		ImagesToVideos test = new ImagesToVideos(frames);
		//DataSource[] sources = new DataSource[frames.size()];
		//for(int i = 0; i < frames.size(); i++)
		//{
		//	sources[i] = new URLDataSource(frames.get(i).getUrl());
		//}
	}
}