package com.flow.snesde.filecomputil.pojos;

import java.util.HashMap;

public class ColorBytePojo
{
	@SuppressWarnings("unused")
	private final int[]										equalColor											=
																																				{ 0, 0,
			255																															};
	@SuppressWarnings("unused")
	private final int[]										almostEqualColor								=
																																				{ 0,
			255, 0																														};
	@SuppressWarnings("unused")
	private final int[]										mixedColor											=
																																				{ 255,
			255, 0																														};
	@SuppressWarnings("unused")
	private final int[]										almostIndividualColor						=
																																				{ 255,
			0, 0																															};
	@SuppressWarnings("unused")
	private final int[]										individualColor									=
																																				{ 0, 0,
			0																																};

	HashMap<Integer, BytePositionObject>	byteCounter											= new HashMap<Integer, BytePositionObject>();

	private int														numberOfFiles										= 0;

	HashMap<Integer, int[]>								colors													= new HashMap<Integer, int[]>();

	private boolean												highlightCompleteEquality				= true;
	private boolean												highlightCompleteIndividuality	= true;

	public ColorBytePojo(long maxFileSize, int numberOfFiles)
	{
		this.numberOfFiles = numberOfFiles;
		for (int i = 0; i <= maxFileSize; i++)
		{
			BytePositionObject byteObject = new BytePositionObject();
			byteCounter.put(i, byteObject);
		}
		recalculateColorValues();
	}

	public void registerByteAtPosition(byte byt, int counter)
	{
		BytePositionObject bytePositionObject = byteCounter
				.get(new Integer(counter));
		bytePositionObject.registerByte(byt);
	}

	private void recalculateColorValues()
	{
		// example output for seven files:
		// 1........ 2........ 3........ 4........ 5........ 6........ 7........
		// 000255000 085255000 170255000 255255000 255170000 255085000 255000000

		colors.clear();
		for (int i = 0; i < numberOfFiles; i++)
		{
			double quotient = (double) i / (double) numberOfFiles;
			if (quotient < 0.5)
			{
				quotient = quotient * 2;
				int[] color = new int[]
				{ (int) Math.round(quotient * 255), 255, 0 };
				colors.put(i, color);
			}
			else
			{
				quotient = quotient - 0.5;
				quotient = quotient * 2;
				int[] color = new int[]
				{ 255, (255 - (int) Math.round(quotient * 255)), 0 };
				colors.put(i, color);
			}
		}

		// for (int i = 0; i < numberOfFiles; i++)
		// {
		// int[] color = colors.get(new Integer(i));
		// System.out.println(i + ": " + color[0] + "," + color[1] + "," +
		// color[2]);
		// }
	}

	public boolean isHighlightCompleteEquality()
	{
		return highlightCompleteEquality;
	}

	public void setHighlightCompleteEquality(boolean highlightCompleteEquality)
	{
		this.highlightCompleteEquality = highlightCompleteEquality;
	}

	public boolean isHighlightCompleteIndividuality()
	{
		return highlightCompleteIndividuality;
	}

	public void setHighlightCompleteIndividuality(
			boolean highlightCompleteIndividuality)
	{
		this.highlightCompleteIndividuality = highlightCompleteIndividuality;
	}

	public int[] getColorForByteAtIndex(int i)
	{
		BytePositionObject bytePositionObject = byteCounter.get(new Integer(i));
		if (bytePositionObject == null) return null;
		int numberOfDifferentBytesAtThisPosition = bytePositionObject
				.getNumberOfDifferentBytesAtThisPosition();
		int[] ret = colors.get(numberOfDifferentBytesAtThisPosition - 1);
		return ret;
	}

	public long getNumberOfBytesToDisplay()
	{
		return byteCounter.keySet().size();
	}
}
