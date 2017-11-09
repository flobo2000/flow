/**
 */
package tileset;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tileset.TilesetPackage
 * @generated
 */
public interface TilesetFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TilesetFactory eINSTANCE = tileset.impl.TilesetFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Tileset</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tileset</em>'.
	 * @generated
	 */
	Tileset createTileset();

	/**
	 * Returns a new object of class '<em>Tile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tile</em>'.
	 * @generated
	 */
	Tile createTile();

	/**
	 * Returns a new object of class '<em>Pixel Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pixel Column</em>'.
	 * @generated
	 */
	PixelColumn createPixelColumn();

	/**
	 * Returns a new object of class '<em>Pixel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pixel</em>'.
	 * @generated
	 */
	Pixel createPixel();

	/**
	 * Returns a new object of class '<em>Global Pixel Buffer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Global Pixel Buffer</em>'.
	 * @generated
	 */
	GlobalPixelBuffer createGlobalPixelBuffer();

	/**
	 * Returns a new object of class '<em>Global Pixel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Global Pixel</em>'.
	 * @generated
	 */
	GlobalPixel createGlobalPixel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TilesetPackage getTilesetPackage();

} //TilesetFactory
