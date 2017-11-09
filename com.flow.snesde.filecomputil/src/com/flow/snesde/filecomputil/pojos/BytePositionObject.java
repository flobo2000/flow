package com.flow.snesde.filecomputil.pojos;

import java.util.HashMap;

public class BytePositionObject
{
	HashMap<Integer, Integer>	bytes	= new HashMap<Integer, Integer>();

	public void registerByte(byte bla)
	{
		Integer key = new Integer(new Byte(bla).intValue());
		Integer value = bytes.get(new Byte(bla).intValue());
		if (value != null)
		{
			bytes.remove(key);
			value = new Integer(value.intValue() + 1);
		}
		else
		{
			value = new Integer(1);
		}
		bytes.put(key, value);
	}

	public int getNumberOfDifferentBytesAtThisPosition()
	{
		return bytes.keySet().size();
	}
}
