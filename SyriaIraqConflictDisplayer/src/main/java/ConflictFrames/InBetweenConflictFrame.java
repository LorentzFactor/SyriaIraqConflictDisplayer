package ConflictFrames;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.jsoup.nodes.Element;

public class InBetweenConflictFrame extends ConflictFrame 
{
	DateFormat format;
	public InBetweenConflictFrame(Element row, String mapFolder, DateFormat format) throws IOException, ParseException {
		super(row, mapFolder, format);
		this.format = format;
	}
	
	public InBetweenConflictFrame(Element row, String mapFolder, DateFormat format, Date d) throws IOException, ParseException {
		super(row, mapFolder, format);
		super.date = d;
		this.format = format;
	}
	
	@Override
	public String getStringDate()
	{
		return format.format(super.date);
	}

}
