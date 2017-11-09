/**
 */
package tileset.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tileset.Encoding;
import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;
import tileset.Pixel;
import tileset.PixelColumn;
import tileset.Tile;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;
import tileset.ToolSelection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TilesetPackageImpl extends EPackageImpl implements TilesetPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tilesetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pixelColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pixelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass globalPixelBufferEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass globalPixelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum encodingEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum toolSelectionEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see tileset.TilesetPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TilesetPackageImpl()
	{
		super(eNS_URI, TilesetFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TilesetPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TilesetPackage init()
	{
		if (isInited) return (TilesetPackage)EPackage.Registry.INSTANCE.getEPackage(TilesetPackage.eNS_URI);

		// Obtain or create and register package
		TilesetPackageImpl theTilesetPackage = (TilesetPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TilesetPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TilesetPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTilesetPackage.createPackageContents();

		// Initialize created meta-data
		theTilesetPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTilesetPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TilesetPackage.eNS_URI, theTilesetPackage);
		return theTilesetPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTileset()
	{
		return tilesetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_Name()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTileset_Tiles()
	{
		return (EReference)tilesetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTileset_PermanentBuffer()
	{
		return (EReference)tilesetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_Encoding()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_Columns()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_PaletteName()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_LeftIndex()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_RightIndex()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_Tool()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTileset_ZoomFactor()
	{
		return (EAttribute)tilesetEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTile()
	{
		return tileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTile_PixelColumns()
	{
		return (EReference)tileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTile_PaletteName()
	{
		return (EAttribute)tileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTile_Character() {
		return (EAttribute)tileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTile_CharacterWidth() {
		return (EAttribute)tileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPixelColumn()
	{
		return pixelColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPixelColumn_Pixels()
	{
		return (EReference)pixelColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPixel()
	{
		return pixelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPixel_ColorIndex()
	{
		return (EAttribute)pixelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlobalPixelBuffer()
	{
		return globalPixelBufferEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGlobalPixelBuffer_BufferedPixels()
	{
		return (EReference)globalPixelBufferEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlobalPixel()
	{
		return globalPixelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobalPixel_Column()
	{
		return (EAttribute)globalPixelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobalPixel_Row()
	{
		return (EAttribute)globalPixelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobalPixel_ColorIndex()
	{
		return (EAttribute)globalPixelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEncoding()
	{
		return encodingEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getToolSelection()
	{
		return toolSelectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TilesetFactory getTilesetFactory()
	{
		return (TilesetFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		tilesetEClass = createEClass(TILESET);
		createEAttribute(tilesetEClass, TILESET__NAME);
		createEReference(tilesetEClass, TILESET__TILES);
		createEReference(tilesetEClass, TILESET__PERMANENT_BUFFER);
		createEAttribute(tilesetEClass, TILESET__ENCODING);
		createEAttribute(tilesetEClass, TILESET__COLUMNS);
		createEAttribute(tilesetEClass, TILESET__PALETTE_NAME);
		createEAttribute(tilesetEClass, TILESET__LEFT_INDEX);
		createEAttribute(tilesetEClass, TILESET__RIGHT_INDEX);
		createEAttribute(tilesetEClass, TILESET__TOOL);
		createEAttribute(tilesetEClass, TILESET__ZOOM_FACTOR);

		tileEClass = createEClass(TILE);
		createEReference(tileEClass, TILE__PIXEL_COLUMNS);
		createEAttribute(tileEClass, TILE__PALETTE_NAME);
		createEAttribute(tileEClass, TILE__CHARACTER);
		createEAttribute(tileEClass, TILE__CHARACTER_WIDTH);

		pixelColumnEClass = createEClass(PIXEL_COLUMN);
		createEReference(pixelColumnEClass, PIXEL_COLUMN__PIXELS);

		pixelEClass = createEClass(PIXEL);
		createEAttribute(pixelEClass, PIXEL__COLOR_INDEX);

		globalPixelBufferEClass = createEClass(GLOBAL_PIXEL_BUFFER);
		createEReference(globalPixelBufferEClass, GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS);

		globalPixelEClass = createEClass(GLOBAL_PIXEL);
		createEAttribute(globalPixelEClass, GLOBAL_PIXEL__COLUMN);
		createEAttribute(globalPixelEClass, GLOBAL_PIXEL__ROW);
		createEAttribute(globalPixelEClass, GLOBAL_PIXEL__COLOR_INDEX);

		// Create enums
		encodingEEnum = createEEnum(ENCODING);
		toolSelectionEEnum = createEEnum(TOOL_SELECTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(tilesetEClass, Tileset.class, "Tileset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTileset_Name(), ecorePackage.getEString(), "Name", null, 1, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTileset_Tiles(), this.getTile(), null, "Tiles", null, 1, 2710, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTileset_PermanentBuffer(), this.getGlobalPixelBuffer(), null, "PermanentBuffer", null, 1, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_Encoding(), this.getEncoding(), "Encoding", "enc1bpp2colors", 1, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_Columns(), ecorePackage.getEIntegerObject(), "Columns", "16", 0, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_PaletteName(), ecorePackage.getEString(), "PaletteName", null, 0, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_LeftIndex(), ecorePackage.getEIntegerObject(), "LeftIndex", "0", 0, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_RightIndex(), ecorePackage.getEIntegerObject(), "RightIndex", "0", 0, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_Tool(), this.getToolSelection(), "Tool", "Pencil", 1, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTileset_ZoomFactor(), ecorePackage.getEIntegerObject(), "ZoomFactor", "7", 1, 1, Tileset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tileEClass, Tile.class, "Tile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTile_PixelColumns(), this.getPixelColumn(), null, "PixelColumns", null, 8, 8, Tile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTile_PaletteName(), ecorePackage.getEString(), "PaletteName", null, 0, 1, Tile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTile_Character(), ecorePackage.getEString(), "Character", null, 0, 1, Tile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTile_CharacterWidth(), ecorePackage.getEInt(), "CharacterWidth", null, 0, 1, Tile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pixelColumnEClass, PixelColumn.class, "PixelColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPixelColumn_Pixels(), this.getPixel(), null, "Pixels", null, 8, 8, PixelColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pixelEClass, Pixel.class, "Pixel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPixel_ColorIndex(), ecorePackage.getEIntegerObject(), "ColorIndex", "0", 1, 1, Pixel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(globalPixelBufferEClass, GlobalPixelBuffer.class, "GlobalPixelBuffer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGlobalPixelBuffer_BufferedPixels(), this.getGlobalPixel(), null, "BufferedPixels", null, 0, 1000000, GlobalPixelBuffer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(globalPixelEClass, GlobalPixel.class, "GlobalPixel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGlobalPixel_Column(), ecorePackage.getEIntegerObject(), "Column", null, 0, 1, GlobalPixel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGlobalPixel_Row(), ecorePackage.getEIntegerObject(), "Row", null, 0, 1, GlobalPixel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGlobalPixel_ColorIndex(), ecorePackage.getEIntegerObject(), "ColorIndex", null, 0, 1, GlobalPixel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(encodingEEnum, Encoding.class, "Encoding");
		addEEnumLiteral(encodingEEnum, Encoding.ENC1BPP2COLORS);
		addEEnumLiteral(encodingEEnum, Encoding.ENC2BPP4COLORS);
		addEEnumLiteral(encodingEEnum, Encoding.ENC3BPP8COLORS);
		addEEnumLiteral(encodingEEnum, Encoding.ENC4BPP16COLORS);
		addEEnumLiteral(encodingEEnum, Encoding.ENC8BPP256COLORS);

		initEEnum(toolSelectionEEnum, ToolSelection.class, "ToolSelection");
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.PENCIL);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.LINE);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.PIPETTE);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.MAGNIFIER);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.FILL);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.MARK);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.RECTANGLE);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.ELLIPSE);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.ENUMERATION);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.TEXT);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.PALETTE);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.WIZARD);
		addEEnumLiteral(toolSelectionEEnum, ToolSelection.SPRAY);

		// Create resource
		createResource(eNS_URI);
	}

} //TilesetPackageImpl
