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

import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;
import tileset.TilesetPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Global Pixel Buffer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tileset.impl.GlobalPixelBufferImpl#getBufferedPixels <em>Buffered Pixels</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GlobalPixelBufferImpl extends MinimalEObjectImpl.Container implements GlobalPixelBuffer
{
	/**
	 * The cached value of the '{@link #getBufferedPixels() <em>Buffered Pixels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBufferedPixels()
	 * @generated
	 * @ordered
	 */
	protected EList<GlobalPixel> bufferedPixels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GlobalPixelBufferImpl()
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
		return TilesetPackage.Literals.GLOBAL_PIXEL_BUFFER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GlobalPixel> getBufferedPixels()
	{
		if (bufferedPixels == null) {
			bufferedPixels = new EObjectContainmentEList<GlobalPixel>(GlobalPixel.class, this, TilesetPackage.GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS);
		}
		return bufferedPixels;
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
			case TilesetPackage.GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS:
				return ((InternalEList<?>)getBufferedPixels()).basicRemove(otherEnd, msgs);
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
			case TilesetPackage.GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS:
				return getBufferedPixels();
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
			case TilesetPackage.GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS:
				getBufferedPixels().clear();
				getBufferedPixels().addAll((Collection<? extends GlobalPixel>)newValue);
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
			case TilesetPackage.GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS:
				getBufferedPixels().clear();
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
			case TilesetPackage.GLOBAL_PIXEL_BUFFER__BUFFERED_PIXELS:
				return bufferedPixels != null && !bufferedPixels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GlobalPixelBufferImpl
