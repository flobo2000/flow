/**
 */
package palette;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Palette</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link palette.Palette#getName <em>Name</em>}</li>
 *   <li>{@link palette.Palette#getColors <em>Colors</em>}</li>
 *   <li>{@link palette.Palette#getSelectionType <em>Selection Type</em>}</li>
 *   <li>{@link palette.Palette#getLeftSelectedColor <em>Left Selected Color</em>}</li>
 *   <li>{@link palette.Palette#getRightSelectedColor <em>Right Selected Color</em>}</li>
 *   <li>{@link palette.Palette#getLeftSelectedIndex <em>Left Selected Index</em>}</li>
 *   <li>{@link palette.Palette#getRightSelectedIndex <em>Right Selected Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see palette.PalettePackage#getPalette()
 * @model
 * @generated
 */
public interface Palette extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see palette.PalettePackage#getPalette_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link palette.Palette#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Colors</b></em>' containment reference list.
	 * The list contents are of type {@link palette.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Colors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Colors</em>' containment reference list.
	 * @see palette.PalettePackage#getPalette_Colors()
	 * @model containment="true" lower="2" upper="256"
	 * @generated
	 */
	EList<Color> getColors();

	/**
	 * Returns the value of the '<em><b>Selection Type</b></em>' attribute.
	 * The literals are from the enumeration {@link palette.PaletteSelectionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selection Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selection Type</em>' attribute.
	 * @see palette.PaletteSelectionType
	 * @see #setSelectionType(PaletteSelectionType)
	 * @see palette.PalettePackage#getPalette_SelectionType()
	 * @model
	 * @generated
	 */
	PaletteSelectionType getSelectionType();

	/**
	 * Sets the value of the '{@link palette.Palette#getSelectionType <em>Selection Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selection Type</em>' attribute.
	 * @see palette.PaletteSelectionType
	 * @see #getSelectionType()
	 * @generated
	 */
	void setSelectionType(PaletteSelectionType value);

	/**
	 * Returns the value of the '<em><b>Left Selected Color</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Selected Color</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Selected Color</em>' reference.
	 * @see #setLeftSelectedColor(Color)
	 * @see palette.PalettePackage#getPalette_LeftSelectedColor()
	 * @model
	 * @generated
	 */
	Color getLeftSelectedColor();

	/**
	 * Sets the value of the '{@link palette.Palette#getLeftSelectedColor <em>Left Selected Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Selected Color</em>' reference.
	 * @see #getLeftSelectedColor()
	 * @generated
	 */
	void setLeftSelectedColor(Color value);

	/**
	 * Returns the value of the '<em><b>Right Selected Color</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Selected Color</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Selected Color</em>' reference.
	 * @see #setRightSelectedColor(Color)
	 * @see palette.PalettePackage#getPalette_RightSelectedColor()
	 * @model
	 * @generated
	 */
	Color getRightSelectedColor();

	/**
	 * Sets the value of the '{@link palette.Palette#getRightSelectedColor <em>Right Selected Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Selected Color</em>' reference.
	 * @see #getRightSelectedColor()
	 * @generated
	 */
	void setRightSelectedColor(Color value);

	/**
	 * Returns the value of the '<em><b>Left Selected Index</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Selected Index</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Selected Index</em>' attribute.
	 * @see #setLeftSelectedIndex(Integer)
	 * @see palette.PalettePackage#getPalette_LeftSelectedIndex()
	 * @model default="0" required="true" ordered="false"
	 * @generated
	 */
	Integer getLeftSelectedIndex();

	/**
	 * Sets the value of the '{@link palette.Palette#getLeftSelectedIndex <em>Left Selected Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Selected Index</em>' attribute.
	 * @see #getLeftSelectedIndex()
	 * @generated
	 */
	void setLeftSelectedIndex(Integer value);

	/**
	 * Returns the value of the '<em><b>Right Selected Index</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Selected Index</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Selected Index</em>' attribute.
	 * @see #setRightSelectedIndex(Integer)
	 * @see palette.PalettePackage#getPalette_RightSelectedIndex()
	 * @model default="0" required="true" ordered="false"
	 * @generated
	 */
	Integer getRightSelectedIndex();

	/**
	 * Sets the value of the '{@link palette.Palette#getRightSelectedIndex <em>Right Selected Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Selected Index</em>' attribute.
	 * @see #getRightSelectedIndex()
	 * @generated
	 */
	void setRightSelectedIndex(Integer value);

} // Palette
