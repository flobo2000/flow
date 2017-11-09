/**
 */
package tileset;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tileset.Tile#getPixelColumns <em>Pixel Columns</em>}</li>
 *   <li>{@link tileset.Tile#getPaletteName <em>Palette Name</em>}</li>
 *   <li>{@link tileset.Tile#getCharacter <em>Character</em>}</li>
 *   <li>{@link tileset.Tile#getCharacterWidth <em>Character Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see tileset.TilesetPackage#getTile()
 * @model
 * @generated
 */
public interface Tile extends EObject
{
	/**
	 * Returns the value of the '<em><b>Pixel Columns</b></em>' containment reference list.
	 * The list contents are of type {@link tileset.PixelColumn}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pixel Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pixel Columns</em>' containment reference list.
	 * @see tileset.TilesetPackage#getTile_PixelColumns()
	 * @model containment="true" lower="8" upper="8"
	 * @generated
	 */
	EList<PixelColumn> getPixelColumns();

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
	 * @see tileset.TilesetPackage#getTile_PaletteName()
	 * @model
	 * @generated
	 */
	String getPaletteName();

	/**
	 * Sets the value of the '{@link tileset.Tile#getPaletteName <em>Palette Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Palette Name</em>' attribute.
	 * @see #getPaletteName()
	 * @generated
	 */
	void setPaletteName(String value);

	/**
	 * Returns the value of the '<em><b>Character</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character</em>' attribute.
	 * @see #setCharacter(String)
	 * @see tileset.TilesetPackage#getTile_Character()
	 * @model
	 * @generated
	 */
	String getCharacter();

	/**
	 * Sets the value of the '{@link tileset.Tile#getCharacter <em>Character</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character</em>' attribute.
	 * @see #getCharacter()
	 * @generated
	 */
	void setCharacter(String value);

	/**
	 * Returns the value of the '<em><b>Character Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character Width</em>' attribute.
	 * @see #setCharacterWidth(int)
	 * @see tileset.TilesetPackage#getTile_CharacterWidth()
	 * @model
	 * @generated
	 */
	int getCharacterWidth();

	/**
	 * Sets the value of the '{@link tileset.Tile#getCharacterWidth <em>Character Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character Width</em>' attribute.
	 * @see #getCharacterWidth()
	 * @generated
	 */
	void setCharacterWidth(int value);

} // Tile
