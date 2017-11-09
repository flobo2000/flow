/**
 */
package tileset.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import tileset.Pixel;
import tileset.TilesetPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pixel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tileset.impl.PixelImpl#getColorIndex <em>Color Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PixelImpl extends MinimalEObjectImpl.Container implements Pixel
{
	/**
	 * The default value of the '{@link #getColorIndex() <em>Color Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer COLOR_INDEX_EDEFAULT = new Integer(0);

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
	protected PixelImpl()
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
		return TilesetPackage.Literals.PIXEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.PIXEL__COLOR_INDEX, oldColorIndex, colorIndex));
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
			case TilesetPackage.PIXEL__COLOR_INDEX:
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
			case TilesetPackage.PIXEL__COLOR_INDEX:
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
			case TilesetPackage.PIXEL__COLOR_INDEX:
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
			case TilesetPackage.PIXEL__COLOR_INDEX:
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
		result.append(" (ColorIndex: ");
		result.append(colorIndex);
		result.append(')');
		return result.toString();
	}

} //PixelImpl
