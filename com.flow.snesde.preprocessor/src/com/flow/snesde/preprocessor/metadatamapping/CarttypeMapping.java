/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

/**
 * A keyvaluemapping implementing the carttype byte combinations 00=ROM, 01=ROM
 * + RAM, 02=ROM + SRAM, 03=ROM + DSP, 04=ROM + RAM + DSP, 05=ROM + SRAM + DSP,
 * 13=ROM + SuperFX, 14=ROM + RAM + SuperFX, 15=ROM + SRAM + SuperFX, 35=ROM +
 * SRAM + SA-1,
 * 
 * @author flo
 * 
 */
public class CarttypeMapping extends KeyValueMappingByteToString
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
		{ 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x13, 0x14, 0x15, 0x35 };
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
		{ "ROM", "ROM + RAM", "ROM + SRAM", "ROM + DSP", "ROM + RAM + DSP",
				"ROM + SRAM + DSP", "ROM + SuperFX", "ROM + RAM + SuperFX",
				"ROM + SRAM + SuperFX", "ROM + SRAM + SA-1" };
	}
	
}
