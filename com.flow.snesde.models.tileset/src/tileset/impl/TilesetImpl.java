/**
 */
package tileset.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tileset.Encoding;
import tileset.GlobalPixelBuffer;
import tileset.Tile;
import tileset.Tileset;
import tileset.TilesetPackage;
import tileset.ToolSelection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tileset</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tileset.impl.TilesetImpl#getName <em>Name</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getTiles <em>Tiles</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getPermanentBuffer <em>Permanent Buffer</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getPaletteName <em>Palette Name</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getLeftIndex <em>Left Index</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getRightIndex <em>Right Index</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getTool <em>Tool</em>}</li>
 *   <li>{@link tileset.impl.TilesetImpl#getZoomFactor <em>Zoom Factor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TilesetImpl extends MinimalEObjectImpl.Container implements Tileset
{
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTiles() <em>Tiles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTiles()
	 * @generated
	 * @ordered
	 */
	protected EList<Tile> tiles;

	/**
	 * The cached value of the '{@link #getPermanentBuffer() <em>Permanent Buffer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermanentBuffer()
	 * @generated
	 * @ordered
	 */
	protected GlobalPixelBuffer permanentBuffer;

	/**
	 * The default value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected static final Encoding ENCODING_EDEFAULT = Encoding.ENC1BPP2COLORS;

	/**
	 * The cached value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected Encoding encoding = ENCODING_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumns() <em>Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected static final Integer COLUMNS_EDEFAULT = new Integer(16);

	/**
	 * The cached value of the '{@link #getColumns() <em>Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected Integer columns = COLUMNS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPaletteName() <em>Palette Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaletteName()
	 * @generated
	 * @ordered
	 */
	protected static final String PALETTE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPaletteName() <em>Palette Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaletteName()
	 * @generated
	 * @ordered
	 */
	protected String paletteName = PALETTE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLeftIndex() <em>Left Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LEFT_INDEX_EDEFAULT = new Integer(0);

	/**
	 * The cached value of the '{@link #getLeftIndex() <em>Left Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer leftIndex = LEFT_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getRightIndex() <em>Right Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer RIGHT_INDEX_EDEFAULT = new Integer(0);

	/**
	 * The cached value of the '{@link #getRightIndex() <em>Right Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer rightIndex = RIGHT_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getTool() <em>Tool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTool()
	 * @generated
	 * @ordered
	 */
	protected static final ToolSelection TOOL_EDEFAULT = ToolSelection.PENCIL;

	/**
	 * The cached value of the '{@link #getTool() <em>Tool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTool()
	 * @generated
	 * @ordered
	 */
	protected ToolSelection tool = TOOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getZoomFactor() <em>Zoom Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoomFactor()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ZOOM_FACTOR_EDEFAULT = new Integer(7);

	/**
	 * The cached value of the '{@link #getZoomFactor() <em>Zoom Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoomFactor()
	 * @generated
	 * @ordered
	 */
	protected Integer zoomFactor = ZOOM_FACTOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TilesetImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return TilesetPackage.Literals.TILESET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tile> getTiles()
	{
		if (tiles == null) {
			tiles = new EObjectContainmentEList<Tile>(Tile.class, this, TilesetPackage.TILESET__TILES);
		}
		return tiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalPixelBuffer getPermanentBuffer()
	{
		if (permanentBuffer != null && permanentBuffer.eIsProxy()) {
			InternalEObject oldPermanentBuffer = (InternalEObject)permanentBuffer;
			permanentBuffer = (GlobalPixelBuffer)eResolveProxy(oldPermanentBuffer);
			if (permanentBuffer != oldPermanentBuffer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TilesetPackage.TILESET__PERMANENT_BUFFER, oldPermanentBuffer, permanentBuffer));
			}
		}
		return permanentBuffer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalPixelBuffer basicGetPermanentBuffer()
	{
		return permanentBuffer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPermanentBuffer(GlobalPixelBuffer newPermanentBuffer)
	{
		GlobalPixelBuffer oldPermanentBuffer = permanentBuffer;
		permanentBuffer = newPermanentBuffer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__PERMANENT_BUFFER, oldPermanentBuffer, permanentBuffer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Encoding getEncoding()
	{
		return encoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncoding(Encoding newEncoding)
	{
		Encoding oldEncoding = encoding;
		encoding = newEncoding == null ? ENCODING_EDEFAULT : newEncoding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__ENCODING, oldEncoding, encoding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getColumns()
	{
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumns(Integer newColumns)
	{
		Integer oldColumns = columns;
		columns = newColumns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__COLUMNS, oldColumns, columns));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPaletteName()
	{
		return paletteName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaletteName(String newPaletteName)
	{
		String oldPaletteName = paletteName;
		paletteName = newPaletteName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__PALETTE_NAME, oldPaletteName, paletteName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLeftIndex()
	{
		return leftIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftIndex(Integer newLeftIndex)
	{
		Integer oldLeftIndex = leftIndex;
		leftIndex = newLeftIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__LEFT_INDEX, oldLeftIndex, leftIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getRightIndex()
	{
		return rightIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightIndex(Integer newRightIndex)
	{
		Integer oldRightIndex = rightIndex;
		rightIndex = newRightIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__RIGHT_INDEX, oldRightIndex, rightIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolSelection getTool()
	{
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTool(ToolSelection newTool)
	{
		ToolSelection oldTool = tool;
		tool = newTool == null ? TOOL_EDEFAULT : newTool;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__TOOL, oldTool, tool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getZoomFactor()
	{
		return zoomFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setZoomFactor(Integer newZoomFactor)
	{
		Integer oldZoomFactor = zoomFactor;
		zoomFactor = newZoomFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILESET__ZOOM_FACTOR, oldZoomFactor, zoomFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID) {
			case TilesetPackage.TILESET__TILES:
				return ((InternalEList<?>)getTiles()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID) {
			case TilesetPackage.TILESET__NAME:
				return getName();
			case TilesetPackage.TILESET__TILES:
				return getTiles();
			case TilesetPackage.TILESET__PERMANENT_BUFFER:
				if (resolve) return getPermanentBuffer();
				return basicGetPermanentBuffer();
			case TilesetPackage.TILESET__ENCODING:
				return getEncoding();
			case TilesetPackage.TILESET__COLUMNS:
				return getColumns();
			case TilesetPackage.TILESET__PALETTE_NAME:
				return getPaletteName();
			case TilesetPackage.TILESET__LEFT_INDEX:
				return getLeftIndex();
			case TilesetPackage.TILESET__RIGHT_INDEX:
				return getRightIndex();
			case TilesetPackage.TILESET__TOOL:
				return getTool();
			case TilesetPackage.TILESET__ZOOM_FACTOR:
				return getZoomFactor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID) {
			case TilesetPackage.TILESET__NAME:
				setName((String)newValue);
				return;
			case TilesetPackage.TILESET__TILES:
				getTiles().clear();
				getTiles().addAll((Collection<? extends Tile>)newValue);
				return;
			case TilesetPackage.TILESET__PERMANENT_BUFFER:
				setPermanentBuffer((GlobalPixelBuffer)newValue);
				return;
			case TilesetPackage.TILESET__ENCODING:
				setEncoding((Encoding)newValue);
				return;
			case TilesetPackage.TILESET__COLUMNS:
				setColumns((Integer)newValue);
				return;
			case TilesetPackage.TILESET__PALETTE_NAME:
				setPaletteName((String)newValue);
				return;
			case TilesetPackage.TILESET__LEFT_INDEX:
				setLeftIndex((Integer)newValue);
				return;
			case TilesetPackage.TILESET__RIGHT_INDEX:
				setRightIndex((Integer)newValue);
				return;
			case TilesetPackage.TILESET__TOOL:
				setTool((ToolSelection)newValue);
				return;
			case TilesetPackage.TILESET__ZOOM_FACTOR:
				setZoomFactor((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID) {
			case TilesetPackage.TILESET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TilesetPackage.TILESET__TILES:
				getTiles().clear();
				return;
			case TilesetPackage.TILESET__PERMANENT_BUFFER:
				setPermanentBuffer((GlobalPixelBuffer)null);
				return;
			case TilesetPackage.TILESET__ENCODING:
				setEncoding(ENCODING_EDEFAULT);
				return;
			case TilesetPackage.TILESET__COLUMNS:
				setColumns(COLUMNS_EDEFAULT);
				return;
			case TilesetPackage.TILESET__PALETTE_NAME:
				setPaletteName(PALETTE_NAME_EDEFAULT);
				return;
			case TilesetPackage.TILESET__LEFT_INDEX:
				setLeftIndex(LEFT_INDEX_EDEFAULT);
				return;
			case TilesetPackage.TILESET__RIGHT_INDEX:
				setRightIndex(RIGHT_INDEX_EDEFAULT);
				return;
			case TilesetPackage.TILESET__TOOL:
				setTool(TOOL_EDEFAULT);
				return;
			case TilesetPackage.TILESET__ZOOM_FACTOR:
				setZoomFactor(ZOOM_FACTOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID) {
			case TilesetPackage.TILESET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TilesetPackage.TILESET__TILES:
				return tiles != null && !tiles.isEmpty();
			case TilesetPackage.TILESET__PERMANENT_BUFFER:
				return permanentBuffer != null;
			case TilesetPackage.TILESET__ENCODING:
				return encoding != ENCODING_EDEFAULT;
			case TilesetPackage.TILESET__COLUMNS:
				return COLUMNS_EDEFAULT == null ? columns != null : !COLUMNS_EDEFAULT.equals(columns);
			case TilesetPackage.TILESET__PALETTE_NAME:
				return PALETTE_NAME_EDEFAULT == null ? paletteName != null : !PALETTE_NAME_EDEFAULT.equals(paletteName);
			case TilesetPackage.TILESET__LEFT_INDEX:
				return LEFT_INDEX_EDEFAULT == null ? leftIndex != null : !LEFT_INDEX_EDEFAULT.equals(leftIndex);
			case TilesetPackage.TILESET__RIGHT_INDEX:
				return RIGHT_INDEX_EDEFAULT == null ? rightIndex != null : !RIGHT_INDEX_EDEFAULT.equals(rightIndex);
			case TilesetPackage.TILESET__TOOL:
				return tool != TOOL_EDEFAULT;
			case TilesetPackage.TILESET__ZOOM_FACTOR:
				return ZOOM_FACTOR_EDEFAULT == null ? zoomFactor != null : !ZOOM_FACTOR_EDEFAULT.equals(zoomFactor);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Name: ");
		result.append(name);
		result.append(", Encoding: ");
		result.append(encoding);
		result.append(", Columns: ");
		result.append(columns);
		result.append(", PaletteName: ");
		result.append(paletteName);
		result.append(", LeftIndex: ");
		result.append(leftIndex);
		result.append(", RightIndex: ");
		result.append(rightIndex);
		result.append(", Tool: ");
		result.append(tool);
		result.append(", ZoomFactor: ");
		result.append(zoomFactor);
		result.append(')');
		return result.toString();
	}

} //TilesetImpl
