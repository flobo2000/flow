/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

/**
 * Keyvalue implementation for Romsize mappings. Supported mappings: 256
 * Kilobytes, 09=512 Kilobytes, 0A=1 Megabyte, 0B=2 Megabytes, 0C=4 Megabytes
 * 
 * @author flo
 * 
 */
public class RomsizeMapping extends KeyValueMappingByteToString
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.digisalt.dsnesds.core.wizards.create.project.mappings.KeyValueMapping
	 * #initKeys()
	 */
	@Override
	protected Byte[] initKeys()
	{
		return new Byte[]
		{ 0x8, 0x9, 0xA, 0xB, 0xC };
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.digisalt.dsnesds.core.wizards.create.project.mappings.KeyValueMapping
	 * #initValues()
	 */
	@Override
	protected String[] initValues()
	{
		return new String[]
		{ "256 Kilobytes", "512 Kilobytes", "1 Megabyte", "2 Megabytes",
				"4 Megabytes" };
	}
	
}
