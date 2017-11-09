/**
 */
package palette;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Selection Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see palette.PalettePackage#getPaletteSelectionType()
 * @model
 * @generated
 */
public enum PaletteSelectionType implements Enumerator {
	/**
	 * The '<em><b>One Color</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE_COLOR_VALUE
	 * @generated
	 * @ordered
	 */
	ONE_COLOR(0, "OneColor", "OneColor"),

	/**
	 * The '<em><b>Two Colors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TWO_COLORS_VALUE
	 * @generated
	 * @ordered
	 */
	TWO_COLORS(1, "TwoColors", "TwoColors");

	/**
	 * The '<em><b>One Color</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One Color</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE_COLOR
	 * @model name="OneColor"
	 * @generated
	 * @ordered
	 */
	public static final int ONE_COLOR_VALUE = 0;

	/**
	 * The '<em><b>Two Colors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Two Colors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TWO_COLORS
	 * @model name="TwoColors"
	 * @generated
	 * @ordered
	 */
	public static final int TWO_COLORS_VALUE = 1;

	/**
	 * An array of all the '<em><b>Selection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PaletteSelectionType[] VALUES_ARRAY =
		new PaletteSelectionType[]
		{
			ONE_COLOR,
			TWO_COLORS,
		};

	/**
	 * A public read-only list of all the '<em><b>Selection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PaletteSelectionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Selection Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PaletteSelectionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			PaletteSelectionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Selection Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PaletteSelectionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			PaletteSelectionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Selection Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PaletteSelectionType get(int value) {
		switch (value)
		{
			case ONE_COLOR_VALUE: return ONE_COLOR;
			case TWO_COLORS_VALUE: return TWO_COLORS;
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
	private PaletteSelectionType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PaletteSelectionType
