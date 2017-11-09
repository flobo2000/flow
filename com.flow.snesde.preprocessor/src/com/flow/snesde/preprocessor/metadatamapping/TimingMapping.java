/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

/**
 * This string to string mapping represents the timing information. Mappings
 * include these two: FASTROM=120 Nanoseconds (FASTROM) and SLOWROM=200
 * Nanoseconds (SLOWROM)
 * 
 * @author flo
 * 
 */
public class TimingMapping extends KeyValueMappingStringToString
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.digisalt.dsnesds.core.wizards.create.project.mappings.
	 * KeyValueMappingStringToString#initKeys()
	 */
	@Override
	protected String[] initKeys()
	{
		return new String[]
		{ "FASTROM", "SLOWROM" };
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.digisalt.dsnesds.core.wizards.create.project.mappings.
	 * KeyValueMappingStringToString#initValues()
	 */
	@Override
	protected String[] initValues()
	{
		return new String[]
		{ "120 Nanoseconds (FASTROM)", "200 Nanoseconds (SLOWROM)" };
	}
	
}
