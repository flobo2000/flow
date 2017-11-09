/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

/**
 * Countrycode mapping for project metadata. Supported mappings: 00 - Japan, 01
 * - USA/Canada, 02 - Asia/Oceania/Europe, 03 - Sweden, 04 - Finland, 05 -
 * Denmark, 06 - France, 07 - Netherlands, 08 - Spain, 09 -
 * Germany/Austria/Switzerland, 0A - Italy, 0B - China/Hong Kong, 0C -
 * Indonesia, 0D - South Korea
 * 
 * @author flo
 */
public class CountrycodeMapping extends KeyValueMappingByteToString
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
		{ 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD };
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
		{ "Japan", "USA/Canada", "Asia/Oceania/Europe", "Sweden", "Finland",
				"Denmark", "France", "Netherlands", "Spain",
				"Germany/Austria/Switzerland", "Italy", "China/Hong Kong", "Indonesia",
				"South Korea" };
	}
	
}
