/**
 */
package tileset;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Global Pixel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tileset.GlobalPixel#getColumn <em>Column</em>}</li>
 *   <li>{@link tileset.GlobalPixel#getRow <em>Row</em>}</li>
 *   <li>{@link tileset.GlobalPixel#getColorIndex <em>Color Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see tileset.TilesetPackage#getGlobalPixel()
 * @model
 * @generated
 */
public interface GlobalPixel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column</em>' attribute.
	 * @see #setColumn(Integer)
	 * @see tileset.TilesetPackage#getGlobalPixel_Column()
	 * @model
	 * @generated
	 */
	Integer getColumn();

	/**
	 * Sets the value of the '{@link tileset.GlobalPixel#getColumn <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' attribute.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(Integer value);

	/**
	 * Returns the value of the '<em><b>Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row</em>' attribute.
	 * @see #setRow(Integer)
	 * @see tileset.TilesetPackage#getGlobalPixel_Row()
	 * @model
	 * @generated
	 */
	Integer getRow();

	/**
	 * Sets the value of the '{@link tileset.GlobalPixel#getRow <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row</em>' attribute.
	 * @see #getRow()
	 * @generated
	 */
	void setRow(Integer value);

	/**
	 * Returns the value of the '<em><b>Color Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Index</em>' attribute.
	 * @see #setColorIndex(Integer)
	 * @see tileset.TilesetPackage#getGlobalPixel_ColorIndex()
	 * @model
	 * @generated
	 */
	Integer getColorIndex();

	/**
	 * Sets the value of the '{@link tileset.GlobalPixel#getColorIndex <em>Color Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Index</em>' attribute.
	 * @see #getColorIndex()
	 * @generated
	 */
	void setColorIndex(Integer value);

} // GlobalPixel
