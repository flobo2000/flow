/**
 */
package tileset.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import tileset.GlobalPixel;
import tileset.TilesetPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Global Pixel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tileset.impl.GlobalPixelImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link tileset.impl.GlobalPixelImpl#getRow <em>Row</em>}</li>
 *   <li>{@link tileset.impl.GlobalPixelImpl#getColorIndex <em>Color Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GlobalPixelImpl extends MinimalEObjectImpl.Container implements GlobalPixel
{
	/**
	 * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected static final Integer COLUMN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected Integer column = COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #getRow() <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRow()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRow() <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRow()
	 * @generated
	 * @ordered
	 */
	protected Integer row = ROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getColorIndex() <em>Color Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer COLOR_INDEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColorIndex() <em>Color Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer colorIndex = COLOR_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GlobalPixelImpl()
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
		return TilesetPackage.Literals.GLOBAL_PIXEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getColumn()
	{
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumn(Integer newColumn)
	{
		Integer oldColumn = column;
		column = newColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.GLOBAL_PIXEL__COLUMN, oldColumn, column));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getRow()
	{
		return row;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRow(Integer newRow)
	{
		Integer oldRow = row;
		row = newRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.GLOBAL_PIXEL__ROW, oldRow, row));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getColorIndex()
	{
		return colorIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorIndex(Integer newColorIndex)
	{
		Integer oldColorIndex = colorIndex;
		colorIndex = newColorIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.GLOBAL_PIXEL__COLOR_INDEX, oldColorIndex, colorIndex));
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
			case TilesetPackage.GLOBAL_PIXEL__COLUMN:
				return getColumn();
			case TilesetPackage.GLOBAL_PIXEL__ROW:
				return getRow();
			case TilesetPackage.GLOBAL_PIXEL__COLOR_INDEX:
				return getColorIndex();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID) {
			case TilesetPackage.GLOBAL_PIXEL__COLUMN:
				setColumn((Integer)newValue);
				return;
			case TilesetPackage.GLOBAL_PIXEL__ROW:
				setRow((Integer)newValue);
				return;
			case TilesetPackage.GLOBAL_PIXEL__COLOR_INDEX:
				setColorIndex((Integer)newValue);
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
			case TilesetPackage.GLOBAL_PIXEL__COLUMN:
				setColumn(COLUMN_EDEFAULT);
				return;
			case TilesetPackage.GLOBAL_PIXEL__ROW:
				setRow(ROW_EDEFAULT);
				return;
			case TilesetPackage.GLOBAL_PIXEL__COLOR_INDEX:
				setColorIndex(COLOR_INDEX_EDEFAULT);
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
			case TilesetPackage.GLOBAL_PIXEL__COLUMN:
				return COLUMN_EDEFAULT == null ? column != null : !COLUMN_EDEFAULT.equals(column);
			case TilesetPackage.GLOBAL_PIXEL__ROW:
				return ROW_EDEFAULT == null ? row != null : !ROW_EDEFAULT.equals(row);
			case TilesetPackage.GLOBAL_PIXEL__COLOR_INDEX:
				return COLOR_INDEX_EDEFAULT == null ? colorIndex != null : !COLOR_INDEX_EDEFAULT.equals(colorIndex);
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
		result.append(" (Column: ");
		result.append(column);
		result.append(", Row: ");
		result.append(row);
		result.append(", ColorIndex: ");
		result.append(colorIndex);
		result.append(')');
		return result.toString();
	}

} //GlobalPixelImpl
