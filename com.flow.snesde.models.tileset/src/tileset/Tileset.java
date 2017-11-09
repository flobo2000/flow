/**
 */
package tileset;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tileset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tileset.Tileset#getName <em>Name</em>}</li>
 *   <li>{@link tileset.Tileset#getTiles <em>Tiles</em>}</li>
 *   <li>{@link tileset.Tileset#getPermanentBuffer <em>Permanent Buffer</em>}</li>
 *   <li>{@link tileset.Tileset#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link tileset.Tileset#getColumns <em>Columns</em>}</li>
 *   <li>{@link tileset.Tileset#getPaletteName <em>Palette Name</em>}</li>
 *   <li>{@link tileset.Tileset#getLeftIndex <em>Left Index</em>}</li>
 *   <li>{@link tileset.Tileset#getRightIndex <em>Right Index</em>}</li>
 *   <li>{@link tileset.Tileset#getTool <em>Tool</em>}</li>
 *   <li>{@link tileset.Tileset#getZoomFactor <em>Zoom Factor</em>}</li>
 * </ul>
 * </p>
 *
 * @see tileset.TilesetPackage#getTileset()
 * @model
 * @generated
 */
public interface Tileset extends EObject
{
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
	 * @see tileset.TilesetPackage#getTileset_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Tiles</b></em>' containment reference list.
	 * The list contents are of type {@link tileset.Tile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tiles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tiles</em>' containment reference list.
	 * @see tileset.TilesetPackage#getTileset_Tiles()
	 * @model containment="true" required="true" upper="2710"
	 * @generated
	 */
	EList<Tile> getTiles();

	/**
	 * Returns the value of the '<em><b>Permanent Buffer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permanent Buffer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permanent Buffer</em>' reference.
	 * @see #setPermanentBuffer(GlobalPixelBuffer)
	 * @see tileset.TilesetPackage#getTileset_PermanentBuffer()
	 * @model required="true"
	 * @generated
	 */
	GlobalPixelBuffer getPermanentBuffer();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getPermanentBuffer <em>Permanent Buffer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Permanent Buffer</em>' reference.
	 * @see #getPermanentBuffer()
	 * @generated
	 */
	void setPermanentBuffer(GlobalPixelBuffer value);

	/**
	 * Returns the value of the '<em><b>Encoding</b></em>' attribute.
	 * The default value is <code>"enc1bpp2colors"</code>.
	 * The literals are from the enumeration {@link tileset.Encoding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding</em>' attribute.
	 * @see tileset.Encoding
	 * @see #setEncoding(Encoding)
	 * @see tileset.TilesetPackage#getTileset_Encoding()
	 * @model default="enc1bpp2colors" required="true"
	 * @generated
	 */
	Encoding getEncoding();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getEncoding <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' attribute.
	 * @see tileset.Encoding
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(Encoding value);

	/**
	 * Returns the value of the '<em><b>Columns</b></em>' attribute.
	 * The default value is <code>"16"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' attribute.
	 * @see #setColumns(Integer)
	 * @see tileset.TilesetPackage#getTileset_Columns()
	 * @model default="16"
	 * @generated
	 */
	Integer getColumns();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getColumns <em>Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Columns</em>' attribute.
	 * @see #getColumns()
	 * @generated
	 */
	void setColumns(Integer value);

	/**
	 * Returns the value of the '<em><b>Palette Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Palette Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Palette Name</em>' attribute.
	 * @see #setPaletteName(String)
	 * @see tileset.TilesetPackage#getTileset_PaletteName()
	 * @model
	 * @generated
	 */
	String getPaletteName();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getPaletteName <em>Palette Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Palette Name</em>' attribute.
	 * @see #getPaletteName()
	 * @generated
	 */
	void setPaletteName(String value);

	/**
	 * Returns the value of the '<em><b>Left Index</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Index</em>' attribute.
	 * @see #setLeftIndex(Integer)
	 * @see tileset.TilesetPackage#getTileset_LeftIndex()
	 * @model default="0"
	 * @generated
	 */
	Integer getLeftIndex();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getLeftIndex <em>Left Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Index</em>' attribute.
	 * @see #getLeftIndex()
	 * @generated
	 */
	void setLeftIndex(Integer value);

	/**
	 * Returns the value of the '<em><b>Right Index</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Index</em>' attribute.
	 * @see #setRightIndex(Integer)
	 * @see tileset.TilesetPackage#getTileset_RightIndex()
	 * @model default="0"
	 * @generated
	 */
	Integer getRightIndex();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getRightIndex <em>Right Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Index</em>' attribute.
	 * @see #getRightIndex()
	 * @generated
	 */
	void setRightIndex(Integer value);

	/**
	 * Returns the value of the '<em><b>Tool</b></em>' attribute.
	 * The default value is <code>"Pencil"</code>.
	 * The literals are from the enumeration {@link tileset.ToolSelection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool</em>' attribute.
	 * @see tileset.ToolSelection
	 * @see #setTool(ToolSelection)
	 * @see tileset.TilesetPackage#getTileset_Tool()
	 * @model default="Pencil" required="true"
	 * @generated
	 */
	ToolSelection getTool();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getTool <em>Tool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tool</em>' attribute.
	 * @see tileset.ToolSelection
	 * @see #getTool()
	 * @generated
	 */
	void setTool(ToolSelection value);

	/**
	 * Returns the value of the '<em><b>Zoom Factor</b></em>' attribute.
	 * The default value is <code>"7"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Zoom Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zoom Factor</em>' attribute.
	 * @see #setZoomFactor(Integer)
	 * @see tileset.TilesetPackage#getTileset_ZoomFactor()
	 * @model default="7" required="true"
	 * @generated
	 */
	Integer getZoomFactor();

	/**
	 * Sets the value of the '{@link tileset.Tileset#getZoomFactor <em>Zoom Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zoom Factor</em>' attribute.
	 * @see #getZoomFactor()
	 * @generated
	 */
	void setZoomFactor(Integer value);

} // Tileset
