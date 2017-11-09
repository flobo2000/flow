/**
 */
package tileset.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tileset.Pixel;
import tileset.PixelColumn;
import tileset.TilesetPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pixel Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tileset.impl.PixelColumnImpl#getPixels <em>Pixels</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PixelColumnImpl extends MinimalEObjectImpl.Container implements PixelColumn
{
	/**
	 * The cached value of the '{@link #getPixels() <em>Pixels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPixels()
	 * @generated
	 * @ordered
	 */
	protected EList<Pixel> pixels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PixelColumnImpl()
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
		return TilesetPackage.Literals.PIXEL_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Pixel> getPixels()
	{
		if (pixels == null) {
			pixels = new EObjectContainmentEList<Pixel>(Pixel.class, this, TilesetPackage.PIXEL_COLUMN__PIXELS);
		}
		return pixels;
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
			case TilesetPackage.PIXEL_COLUMN__PIXELS:
				return ((InternalEList<?>)getPixels()).basicRemove(otherEnd, msgs);
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
			case TilesetPackage.PIXEL_COLUMN__PIXELS:
				return getPixels();
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
			case TilesetPackage.PIXEL_COLUMN__PIXELS:
				getPixels().clear();
				getPixels().addAll((Collection<? extends Pixel>)newValue);
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
			case TilesetPackage.PIXEL_COLUMN__PIXELS:
				getPixels().clear();
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
			case TilesetPackage.PIXEL_COLUMN__PIXELS:
				return pixels != null && !pixels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PixelColumnImpl
