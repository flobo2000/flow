/**
 */
package palette;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Color</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link palette.Color#getR <em>R</em>}</li>
 *   <li>{@link palette.Color#getG <em>G</em>}</li>
 *   <li>{@link palette.Color#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see palette.PalettePackage#getColor()
 * @model
 * @generated
 */
public interface Color extends EObject {
	/**
	 * Returns the value of the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>R</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>R</em>' attribute.
	 * @see #setR(Integer)
	 * @see palette.PalettePackage#getColor_R()
	 * @model
	 * @generated
	 */
	Integer getR();

	/**
	 * Sets the value of the '{@link palette.Color#getR <em>R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R</em>' attribute.
	 * @see #getR()
	 * @generated
	 */
	void setR(Integer value);

	/**
	 * Returns the value of the '<em><b>G</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>G</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>G</em>' attribute.
	 * @see #setG(Integer)
	 * @see palette.PalettePackage#getColor_G()
	 * @model
	 * @generated
	 */
	Integer getG();

	/**
	 * Sets the value of the '{@link palette.Color#getG <em>G</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>G</em>' attribute.
	 * @see #getG()
	 * @generated
	 */
	void setG(Integer value);

	/**
	 * Returns the value of the '<em><b>B</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>B</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>B</em>' attribute.
	 * @see #setB(Integer)
	 * @see palette.PalettePackage#getColor_B()
	 * @model
	 * @generated
	 */
	Integer getB();

	/**
	 * Sets the value of the '{@link palette.Color#getB <em>B</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' attribute.
	 * @see #getB()
	 * @generated
	 */
	void setB(Integer value);

} // Color
