/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

import java.util.HashMap;

/**
 * @author flo
 * 
 */
public abstract class KeyValueMappingIntToString
{
	/**
	 * The hashmap providing the link from bytecode to string label
	 */
	HashMap<String, Integer> codes = new HashMap<String, Integer>();
	/**
	 * The hashmap providing the link from string label to bytecode
	 */
	HashMap<Integer, String> labels = new HashMap<Integer, String>();
	
	/**
	 * The standard constructor
	 */
	public KeyValueMappingIntToString()
	{
		init();
	}
	
	/**
	 * creates an instance of the keyvaluemapping. both parameters have to have
	 * the same size.
	 */
	private void init()
	{
		Integer[] keys = initKeys();
		String[] values = initValues();
		for (int i = 0; i < keys.length; i++)
		{
			this.codes.put(values[i], keys[i]);
			this.labels.put(keys[i], values[i]);
		}
	}
	
	/**
	 * This method should return the set of keys (integers). Has to be the same
	 * amount like the set of values returned by getValues().
	 * 
	 * @return the array of integer keys
	 */
	protected abstract Integer[] initKeys();
	
	/**
	 * This method should return the set of values (Strings). Has to be the same
	 * amount like the set of keys returned by getKeys().
	 * 
	 * @return the array of String values
	 */
	protected abstract String[] initValues();
	
	/**
	 * returns the bytecode for a given label
	 * 
	 * @param label
	 *          the label to search for
	 * @return the bytecode representation. can be null if not contained
	 */
	public int getCodeForLabel(final String label)
	{
		return this.codes.get(label);
	}
	
	/**
	 * returns the label for a given bytecode
	 * 
	 * @param code
	 *          the code to search for
	 * @return the label mapped to the code. can be null if not contained
	 */
	public String getLabelForCode(final byte code)
	{
		return this.labels.get(code);
	}
	
	/**
	 * This method returns the set of all labels saved in the keyvalueset
	 * 
	 * @return the set of all labels
	 */
	public String[] getLabelSet()
	{
		String[] ret = new String[this.codes.size()];
		Object[] keySet = this.codes.keySet().toArray();
		for (int i = 0; i < this.codes.keySet().size(); i++)
		{
			ret[i] = (String) keySet[i];
		}
		return ret;
	}
	
	/**
	 * This method returns the set of all byte values saved in the keyvalueset
	 * 
	 * @return the set of all bytes
	 */
	public Byte[] getCodeSet()
	{
		return (Byte[]) this.labels.keySet().toArray();
	}
}
