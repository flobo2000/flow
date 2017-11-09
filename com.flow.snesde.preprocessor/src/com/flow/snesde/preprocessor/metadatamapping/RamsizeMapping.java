/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

/**
 * A keyvaluemapping implementation for ramsizes. Supported pairs: 00=0
 * Kilobytes, 01=2 Kilobytes, 02=4 Kilobytes, 03=8 Kilobytes
 * 
 * @author flo
 * 
 */
public class RamsizeMapping extends KeyValueMappingByteToString
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
		{ 0x0, 0x1, 0x2, 0x3 };
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
		{ "0 Kilobytes", "2 Kilobytes", "4 Kilobytes", "8 Kilobytes" };
	}
	
}
