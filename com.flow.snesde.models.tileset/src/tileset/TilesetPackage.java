/**
 */
package tileset;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tileset.TilesetFactory
 * @model kind="package"
 * @generated
 */
public interface TilesetPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tileset";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/tileset";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tileset";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TilesetPackage eINSTANCE = tileset.impl.TilesetPackageImpl.init();

	/**
	 * The meta object id for the '{@link tileset.impl.TilesetImpl <em>Tileset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.impl.TilesetImpl
	 * @see tileset.impl.TilesetPackageImpl#getTileset()
	 * @generated
	 */
	int TILESET = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__NAME = 0;

	/**
	 * The feature id for the '<em><b>Tiles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__TILES = 1;

	/**
	 * The feature id for the '<em><b>Permanent Buffer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__PERMANENT_BUFFER = 2;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__ENCODING = 3;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__COLUMNS = 4;

	/**
	 * The feature id for the '<em><b>Palette Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__PALETTE_NAME = 5;

	/**
	 * The feature id for the '<em><b>Left Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__LEFT_INDEX = 6;

	/**
	 * The feature id for the '<em><b>Right Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__RIGHT_INDEX = 7;

	/**
	 * The feature id for the '<em><b>Tool</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__TOOL = 8;

	/**
	 * The feature id for the '<em><b>Zoom Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET__ZOOM_FACTOR = 9;

	/**
	 * The number of structural features of the '<em>Tileset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Tileset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILESET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tileset.impl.TileImpl <em>Tile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.impl.TileImpl
	 * @see tileset.impl.TilesetPackageImpl#getTile()
	 * @generated
	 */
	int TILE = 1;

	/**
	 * The feature id for the '<em><b>Pixel Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE__PIXEL_COLUMNS = 0;

	/**
	 * The feature id for the '<em><b>Palette Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE__PALETTE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Character</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE__CHARACTER = 2;

	/**
	 * The feature id for the '<em><b>Character Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE__CHARACTER_WIDTH = 3;

	/**
	 * The number of structural features of the '<em>Tile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Tile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tileset.impl.PixelColumnImpl <em>Pixel Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.impl.PixelColumnImpl
	 * @see tileset.impl.TilesetPackageImpl#getPixelColumn()
	 * @generated
	 */
	int PIXEL_COLUMN = 2;

	/**
	 * The feature id for the '<em><b>Pixels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIXEL_COLUMN__PIXELS = 0;

	/**
	 * The number of structural features of the '<em>Pixel Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIXEL_COLUMN_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Pixel Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIXEL_COLUMN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tileset.impl.PixelImpl <em>Pixel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.impl.PixelImpl
	 * @see tileset.impl.TilesetPackageImpl#getPixel()
	 * @generated
	 */
	int PIXEL = 3;

	/**
	 * The feature id for the '<em><b>Color Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIXEL__COLOR_INDEX = 0;

	/**
	 * The number of structural features of the '<em>Pixel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIXEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Pixel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIXEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tileset.impl.GlobalPixelBufferImpl <em>Global Pixel Buffer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.impl.GlobalPixelBufferImpl
	 * @see tileset.impl.TilesetPackageImpl#getGlobalPixelBuffer()
	 * @generated
	 */
	int GLOBAL_PIXEL_BUFFER = 4;

	/**
	 * The feature id for the '<em><b>Buffered Pixels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS = 0;

	/**
	 * The number of structural features of the '<em>Global Pixel Buffer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL_BUFFER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Global Pixel Buffer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL_BUFFER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tileset.impl.GlobalPixelImpl <em>Global Pixel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.impl.GlobalPixelImpl
	 * @see tileset.impl.TilesetPackageImpl#getGlobalPixel()
	 * @generated
	 */
	int GLOBAL_PIXEL = 5;

	/**
	 * The feature id for the '<em><b>Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL__COLUMN = 0;

	/**
	 * The feature id for the '<em><b>Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL__ROW = 1;

	/**
	 * The feature id for the '<em><b>Color Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL__COLOR_INDEX = 2;

	/**
	 * The number of structural features of the '<em>Global Pixel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Global Pixel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_PIXEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tileset.Encoding <em>Encoding</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.Encoding
	 * @see tileset.impl.TilesetPackageImpl#getEncoding()
	 * @generated
	 */
	int ENCODING = 6;

	/**
	 * The meta object id for the '{@link tileset.ToolSelection <em>Tool Selection</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tileset.ToolSelection
	 * @see tileset.impl.TilesetPackageImpl#getToolSelection()
	 * @generated
	 */
	int TOOL_SELECTION = 7;


	/**
	 * Returns the meta object for class '{@link tileset.Tileset <em>Tileset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tileset</em>'.
	 * @see tileset.Tileset
	 * @generated
	 */
	EClass getTileset();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see tileset.Tileset#getName()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link tileset.Tileset#getTiles <em>Tiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tiles</em>'.
	 * @see tileset.Tileset#getTiles()
	 * @see #getTileset()
	 * @generated
	 */
	EReference getTileset_Tiles();

	/**
	 * Returns the meta object for the reference '{@link tileset.Tileset#getPermanentBuffer <em>Permanent Buffer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Permanent Buffer</em>'.
	 * @see tileset.Tileset#getPermanentBuffer()
	 * @see #getTileset()
	 * @generated
	 */
	EReference getTileset_PermanentBuffer();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see tileset.Tileset#getEncoding()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_Encoding();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Columns</em>'.
	 * @see tileset.Tileset#getColumns()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_Columns();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getPaletteName <em>Palette Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Palette Name</em>'.
	 * @see tileset.Tileset#getPaletteName()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_PaletteName();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getLeftIndex <em>Left Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Left Index</em>'.
	 * @see tileset.Tileset#getLeftIndex()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_LeftIndex();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getRightIndex <em>Right Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Right Index</em>'.
	 * @see tileset.Tileset#getRightIndex()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_RightIndex();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getTool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tool</em>'.
	 * @see tileset.Tileset#getTool()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_Tool();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tileset#getZoomFactor <em>Zoom Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zoom Factor</em>'.
	 * @see tileset.Tileset#getZoomFactor()
	 * @see #getTileset()
	 * @generated
	 */
	EAttribute getTileset_ZoomFactor();

	/**
	 * Returns the meta object for class '{@link tileset.Tile <em>Tile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tile</em>'.
	 * @see tileset.Tile
	 * @generated
	 */
	EClass getTile();

	/**
	 * Returns the meta object for the containment reference list '{@link tileset.Tile#getPixelColumns <em>Pixel Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pixel Columns</em>'.
	 * @see tileset.Tile#getPixelColumns()
	 * @see #getTile()
	 * @generated
	 */
	EReference getTile_PixelColumns();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tile#getPaletteName <em>Palette Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Palette Name</em>'.
	 * @see tileset.Tile#getPaletteName()
	 * @see #getTile()
	 * @generated
	 */
	EAttribute getTile_PaletteName();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tile#getCharacter <em>Character</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Character</em>'.
	 * @see tileset.Tile#getCharacter()
	 * @see #getTile()
	 * @generated
	 */
	EAttribute getTile_Character();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Tile#getCharacterWidth <em>Character Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Character Width</em>'.
	 * @see tileset.Tile#getCharacterWidth()
	 * @see #getTile()
	 * @generated
	 */
	EAttribute getTile_CharacterWidth();

	/**
	 * Returns the meta object for class '{@link tileset.PixelColumn <em>Pixel Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pixel Column</em>'.
	 * @see tileset.PixelColumn
	 * @generated
	 */
	EClass getPixelColumn();

	/**
	 * Returns the meta object for the containment reference list '{@link tileset.PixelColumn#getPixels <em>Pixels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pixels</em>'.
	 * @see tileset.PixelColumn#getPixels()
	 * @see #getPixelColumn()
	 * @generated
	 */
	EReference getPixelColumn_Pixels();

	/**
	 * Returns the meta object for class '{@link tileset.Pixel <em>Pixel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pixel</em>'.
	 * @see tileset.Pixel
	 * @generated
	 */
	EClass getPixel();

	/**
	 * Returns the meta object for the attribute '{@link tileset.Pixel#getColorIndex <em>Color Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Index</em>'.
	 * @see tileset.Pixel#getColorIndex()
	 * @see #getPixel()
	 * @generated
	 */
	EAttribute getPixel_ColorIndex();

	/**
	 * Returns the meta object for class '{@link tileset.GlobalPixelBuffer <em>Global Pixel Buffer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Global Pixel Buffer</em>'.
	 * @see tileset.GlobalPixelBuffer
	 * @generated
	 */
	EClass getGlobalPixelBuffer();

	/**
	 * Returns the meta object for the containment reference list '{@link tileset.GlobalPixelBuffer#getBufferedPixels <em>Buffered Pixels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Buffered Pixels</em>'.
	 * @see tileset.GlobalPixelBuffer#getBufferedPixels()
	 * @see #getGlobalPixelBuffer()
	 * @generated
	 */
	EReference getGlobalPixelBuffer_BufferedPixels();

	/**
	 * Returns the meta object for class '{@link tileset.GlobalPixel <em>Global Pixel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Global Pixel</em>'.
	 * @see tileset.GlobalPixel
	 * @generated
	 */
	EClass getGlobalPixel();

	/**
	 * Returns the meta object for the attribute '{@link tileset.GlobalPixel#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column</em>'.
	 * @see tileset.GlobalPixel#getColumn()
	 * @see #getGlobalPixel()
	 * @generated
	 */
	EAttribute getGlobalPixel_Column();

	/**
	 * Returns the meta object for the attribute '{@link tileset.GlobalPixel#getRow <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row</em>'.
	 * @see tileset.GlobalPixel#getRow()
	 * @see #getGlobalPixel()
	 * @generated
	 */
	EAttribute getGlobalPixel_Row();

	/**
	 * Returns the meta object for the attribute '{@link tileset.GlobalPixel#getColorIndex <em>Color Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Index</em>'.
	 * @see tileset.GlobalPixel#getColorIndex()
	 * @see #getGlobalPixel()
	 * @generated
	 */
	EAttribute getGlobalPixel_ColorIndex();

	/**
	 * Returns the meta object for enum '{@link tileset.Encoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Encoding</em>'.
	 * @see tileset.Encoding
	 * @generated
	 */
	EEnum getEncoding();

	/**
	 * Returns the meta object for enum '{@link tileset.ToolSelection <em>Tool Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tool Selection</em>'.
	 * @see tileset.ToolSelection
	 * @generated
	 */
	EEnum getToolSelection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TilesetFactory getTilesetFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link tileset.impl.TilesetImpl <em>Tileset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.impl.TilesetImpl
		 * @see tileset.impl.TilesetPackageImpl#getTileset()
		 * @generated
		 */
		EClass TILESET = eINSTANCE.getTileset();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__NAME = eINSTANCE.getTileset_Name();

		/**
		 * The meta object literal for the '<em><b>Tiles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TILESET__TILES = eINSTANCE.getTileset_Tiles();

		/**
		 * The meta object literal for the '<em><b>Permanent Buffer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TILESET__PERMANENT_BUFFER = eINSTANCE.getTileset_PermanentBuffer();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__ENCODING = eINSTANCE.getTileset_Encoding();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__COLUMNS = eINSTANCE.getTileset_Columns();

		/**
		 * The meta object literal for the '<em><b>Palette Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__PALETTE_NAME = eINSTANCE.getTileset_PaletteName();

		/**
		 * The meta object literal for the '<em><b>Left Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__LEFT_INDEX = eINSTANCE.getTileset_LeftIndex();

		/**
		 * The meta object literal for the '<em><b>Right Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__RIGHT_INDEX = eINSTANCE.getTileset_RightIndex();

		/**
		 * The meta object literal for the '<em><b>Tool</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__TOOL = eINSTANCE.getTileset_Tool();

		/**
		 * The meta object literal for the '<em><b>Zoom Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILESET__ZOOM_FACTOR = eINSTANCE.getTileset_ZoomFactor();

		/**
		 * The meta object literal for the '{@link tileset.impl.TileImpl <em>Tile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.impl.TileImpl
		 * @see tileset.impl.TilesetPackageImpl#getTile()
		 * @generated
		 */
		EClass TILE = eINSTANCE.getTile();

		/**
		 * The meta object literal for the '<em><b>Pixel Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TILE__PIXEL_COLUMNS = eINSTANCE.getTile_PixelColumns();

		/**
		 * The meta object literal for the '<em><b>Palette Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILE__PALETTE_NAME = eINSTANCE.getTile_PaletteName();

		/**
		 * The meta object literal for the '<em><b>Character</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILE__CHARACTER = eINSTANCE.getTile_Character();

		/**
		 * The meta object literal for the '<em><b>Character Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TILE__CHARACTER_WIDTH = eINSTANCE.getTile_CharacterWidth();

		/**
		 * The meta object literal for the '{@link tileset.impl.PixelColumnImpl <em>Pixel Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.impl.PixelColumnImpl
		 * @see tileset.impl.TilesetPackageImpl#getPixelColumn()
		 * @generated
		 */
		EClass PIXEL_COLUMN = eINSTANCE.getPixelColumn();

		/**
		 * The meta object literal for the '<em><b>Pixels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIXEL_COLUMN__PIXELS = eINSTANCE.getPixelColumn_Pixels();

		/**
		 * The meta object literal for the '{@link tileset.impl.PixelImpl <em>Pixel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.impl.PixelImpl
		 * @see tileset.impl.TilesetPackageImpl#getPixel()
		 * @generated
		 */
		EClass PIXEL = eINSTANCE.getPixel();

		/**
		 * The meta object literal for the '<em><b>Color Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIXEL__COLOR_INDEX = eINSTANCE.getPixel_ColorIndex();

		/**
		 * The meta object literal for the '{@link tileset.impl.GlobalPixelBufferImpl <em>Global Pixel Buffer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.impl.GlobalPixelBufferImpl
		 * @see tileset.impl.TilesetPackageImpl#getGlobalPixelBuffer()
		 * @generated
		 */
		EClass GLOBAL_PIXEL_BUFFER = eINSTANCE.getGlobalPixelBuffer();

		/**
		 * The meta object literal for the '<em><b>Buffered Pixels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS = eINSTANCE.getGlobalPixelBuffer_BufferedPixels();

		/**
		 * The meta object literal for the '{@link tileset.impl.GlobalPixelImpl <em>Global Pixel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.impl.GlobalPixelImpl
		 * @see tileset.impl.TilesetPackageImpl#getGlobalPixel()
		 * @generated
		 */
		EClass GLOBAL_PIXEL = eINSTANCE.getGlobalPixel();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBAL_PIXEL__COLUMN = eINSTANCE.getGlobalPixel_Column();

		/**
		 * The meta object literal for the '<em><b>Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBAL_PIXEL__ROW = eINSTANCE.getGlobalPixel_Row();

		/**
		 * The meta object literal for the '<em><b>Color Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBAL_PIXEL__COLOR_INDEX = eINSTANCE.getGlobalPixel_ColorIndex();

		/**
		 * The meta object literal for the '{@link tileset.Encoding <em>Encoding</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.Encoding
		 * @see tileset.impl.TilesetPackageImpl#getEncoding()
		 * @generated
		 */
		EEnum ENCODING = eINSTANCE.getEncoding();

		/**
		 * The meta object literal for the '{@link tileset.ToolSelection <em>Tool Selection</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tileset.ToolSelection
		 * @see tileset.impl.TilesetPackageImpl#getToolSelection()
		 * @generated
		 */
		EEnum TOOL_SELECTION = eINSTANCE.getToolSelection();

	}

} //TilesetPackage
