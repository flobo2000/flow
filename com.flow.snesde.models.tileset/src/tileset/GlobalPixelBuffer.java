/**
 */
package tileset;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Global Pixel Buffer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tileset.GlobalPixelBuffer#getBufferedPixels <em>Buffered Pixels</em>}</li>
 * </ul>
 * </p>
 *
 * @see tileset.TilesetPackage#getGlobalPixelBuffer()
 * @model
 * @generated
 */
public interface GlobalPixelBuffer extends EObject
{
	/**
	 * Returns the value of the '<em><b>Buffered Pixels</b></em>' containment reference list.
	 * The list contents are of type {@link tileset.GlobalPixel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buffered Pixels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buffered Pixels</em>' containment reference list.
	 * @see tileset.TilesetPackage#getGlobalPixelBuffer_BufferedPixels()
	 * @model containment="true" upper="1000000"
	 * @generated
	 */
	EList<GlobalPixel> getBufferedPixels();

} // GlobalPixelBuffer
