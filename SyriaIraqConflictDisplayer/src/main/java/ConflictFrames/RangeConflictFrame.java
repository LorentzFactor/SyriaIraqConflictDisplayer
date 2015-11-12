package ConflictFrames;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RangeConflictFrame extends ConflictFrame
{
	private String endStringDate;
	private Date endDate;
	public RangeConflictFrame(Element row, String mapFolder, DateFormat format) throws IOException, ParseException {
		super(row, mapFolder, format);
		endDate = new Date();
	}
	
	public RangeConflictFrame(Element row, Element nextRow, String mapFolder, DateFormat format) throws IOException, ParseException
	{
		super(row, mapFolder, format);
		System.out.println(getStringURL());
		Elements tableElements = nextRow.select("td");
		endStringDate = nextRow.select("a").text();
		endDate = format.parse(endStringDate);
	}
	
	public RangeConflictFrame(Element row, Date endDate, String mapFolder, DateFormat format) throws IOException, ParseException
	{
		super(row, mapFolder, format);
		this.endDate = endDate;
		format.format(endDate);
	}

	public String getEndStringDate() {
		return endStringDate;
	}

	public Date getEndDate() {
		return endDate;
	}
}