/**
 */
package tileset;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pixel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tileset.Pixel#getColorIndex <em>Color Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see tileset.TilesetPackage#getPixel()
 * @model
 * @generated
 */
public interface Pixel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Color Index</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Index</em>' attribute.
	 * @see #setColorIndex(Integer)
	 * @see tileset.TilesetPackage#getPixel_ColorIndex()
	 * @model default="0" required="true"
	 * @generated
	 */
	Integer getColorIndex();

	/**
	 * Sets the value of the '{@link tileset.Pixel#getColorIndex <em>Color Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Index</em>' attribute.
	 * @see #getColorIndex()
	 * @generated
	 */
	void setColorIndex(Integer value);

} // Pixel
