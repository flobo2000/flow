/**
 */
package tileset;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Encoding</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see tileset.TilesetPackage#getEncoding()
 * @model
 * @generated
 */
public enum Encoding implements Enumerator
{
	/**
	 * The '<em><b>Enc1bpp2colors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENC1BPP2COLORS_VALUE
	 * @generated
	 * @ordered
	 */
	ENC1BPP2COLORS(0, "enc1bpp2colors", "enc1bpp2colors"),

	/**
	 * The '<em><b>Enc2bpp4colors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENC2BPP4COLORS_VALUE
	 * @generated
	 * @ordered
	 */
	ENC2BPP4COLORS(1, "enc2bpp4colors", "enc2bpp4colors"),

	/**
	 * The '<em><b>Enc3bpp8colors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENC3BPP8COLORS_VALUE
	 * @generated
	 * @ordered
	 */
	ENC3BPP8COLORS(3, "enc3bpp8colors", "enc3bpp8colors"),

	/**
	 * The '<em><b>Enc4bpp16colors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENC4BPP16COLORS_VALUE
	 * @generated
	 * @ordered
	 */
	ENC4BPP16COLORS(4, "enc4bpp16colors", "enc4bpp16colors"),

	/**
	 * The '<em><b>Enc8bpp256colors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENC8BPP256COLORS_VALUE
	 * @generated
	 * @ordered
	 */
	ENC8BPP256COLORS(5, "enc8bpp256colors", "enc8bpp256colors");

	/**
	 * The '<em><b>Enc1bpp2colors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Enc1bpp2colors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENC1BPP2COLORS
	 * @model name="enc1bpp2colors"
	 * @generated
	 * @ordered
	 */
	public static final int ENC1BPP2COLORS_VALUE = 0;

	/**
	 * The '<em><b>Enc2bpp4colors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Enc2bpp4colors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENC2BPP4COLORS
	 * @model name="enc2bpp4colors"
	 * @generated
	 * @ordered
	 */
	public static final int ENC2BPP4COLORS_VALUE = 1;

	/**
	 * The '<em><b>Enc3bpp8colors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Enc3bpp8colors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENC3BPP8COLORS
	 * @model name="enc3bpp8colors"
	 * @generated
	 * @ordered
	 */
	public static final int ENC3BPP8COLORS_VALUE = 3;

	/**
	 * The '<em><b>Enc4bpp16colors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Enc4bpp16colors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENC4BPP16COLORS
	 * @model name="enc4bpp16colors"
	 * @generated
	 * @ordered
	 */
	public static final int ENC4BPP16COLORS_VALUE = 4;

	/**
	 * The '<em><b>Enc8bpp256colors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Enc8bpp256colors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENC8BPP256COLORS
	 * @model name="enc8bpp256colors"
	 * @generated
	 * @ordered
	 */
	public static final int ENC8BPP256COLORS_VALUE = 5;

	/**
	 * An array of all the '<em><b>Encoding</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Encoding[] VALUES_ARRAY =
		new Encoding[] {
			ENC1BPP2COLORS,
			ENC2BPP4COLORS,
			ENC3BPP8COLORS,
			ENC4BPP16COLORS,
			ENC8BPP256COLORS,
		};

	/**
	 * A public read-only list of all the '<em><b>Encoding</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Encoding> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Encoding</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Encoding get(String literal)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Encoding result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Encoding</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Encoding getByName(String name)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Encoding result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Encoding</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Encoding get(int value)
	{
		switch (value) {
			case ENC1BPP2COLORS_VALUE: return ENC1BPP2COLORS;
			case ENC2BPP4COLORS_VALUE: return ENC2BPP4COLORS;
			case ENC3BPP8COLORS_VALUE: return ENC3BPP8COLORS;
			case ENC4BPP16COLORS_VALUE: return ENC4BPP16COLORS;
			case ENC8BPP256COLORS_VALUE: return ENC8BPP256COLORS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Encoding(int value, String name, String literal)
	{
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue()
	{
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral()
	{
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return literal;
	}
	
} //Encoding
