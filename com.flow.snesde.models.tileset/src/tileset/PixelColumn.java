/**
 */
package tileset;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pixel Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tileset.PixelColumn#getPixels <em>Pixels</em>}</li>
 * </ul>
 * </p>
 *
 * @see tileset.TilesetPackage#getPixelColumn()
 * @model
 * @generated
 */
public interface PixelColumn extends EObject
{
	/**
	 * Returns the value of the '<em><b>Pixels</b></em>' containment reference list.
	 * The list contents are of type {@link tileset.Pixel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pixels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pixels</em>' containment reference list.
	 * @see tileset.TilesetPackage#getPixelColumn_Pixels()
	 * @model containment="true" lower="8" upper="8"
	 * @generated
	 */
	EList<Pixel> getPixels();

} // PixelColumn
