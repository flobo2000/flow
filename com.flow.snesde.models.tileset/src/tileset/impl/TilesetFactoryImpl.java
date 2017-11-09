/**
 */
package tileset.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tileset.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TilesetFactoryImpl extends EFactoryImpl implements TilesetFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TilesetFactory init()
	{
		try {
			TilesetFactory theTilesetFactory = (TilesetFactory)EPackage.Registry.INSTANCE.getEFactory(TilesetPackage.eNS_URI);
			if (theTilesetFactory != null) {
				return theTilesetFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TilesetFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TilesetFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID()) {
			case TilesetPackage.TILESET: return createTileset();
			case TilesetPackage.TILE: return createTile();
			case TilesetPackage.PIXEL_COLUMN: return createPixelColumn();
			case TilesetPackage.PIXEL: return createPixel();
			case TilesetPackage.GLOBAL_PIXEL_BUFFER: return createGlobalPixelBuffer();
			case TilesetPackage.GLOBAL_PIXEL: return createGlobalPixel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID()) {
			case TilesetPackage.ENCODING:
				return createEncodingFromString(eDataType, initialValue);
			case TilesetPackage.TOOL_SELECTION:
				return createToolSelectionFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID()) {
			case TilesetPackage.ENCODING:
				return convertEncodingToString(eDataType, instanceValue);
			case TilesetPackage.TOOL_SELECTION:
				return convertToolSelectionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tileset createTileset()
	{
		TilesetImpl tileset = new TilesetImpl();
		return tileset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tile createTile()
	{
		TileImpl tile = new TileImpl();
		return tile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PixelColumn createPixelColumn()
	{
		PixelColumnImpl pixelColumn = new PixelColumnImpl();
		return pixelColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pixel createPixel()
	{
		PixelImpl pixel = new PixelImpl();
		return pixel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalPixelBuffer createGlobalPixelBuffer()
	{
		GlobalPixelBufferImpl globalPixelBuffer = new GlobalPixelBufferImpl();
		return globalPixelBuffer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalPixel createGlobalPixel()
	{
		GlobalPixelImpl globalPixel = new GlobalPixelImpl();
		return globalPixel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Encoding createEncodingFromString(EDataType eDataType, String initialValue)
	{
		Encoding result = Encoding.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEncodingToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolSelection createToolSelectionFromString(EDataType eDataType, String initialValue)
	{
		ToolSelection result = ToolSelection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToolSelectionToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TilesetPackage getTilesetPackage()
	{
		return (TilesetPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TilesetPackage getPackage()
	{
		return TilesetPackage.eINSTANCE;
	}

} //TilesetFactoryImpl
