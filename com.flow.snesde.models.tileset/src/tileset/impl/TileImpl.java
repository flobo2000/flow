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

import tileset.PixelColumn;
import tileset.Tile;
import tileset.TilesetPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tileset.impl.TileImpl#getPixelColumns <em>Pixel Columns</em>}</li>
 *   <li>{@link tileset.impl.TileImpl#getPaletteName <em>Palette Name</em>}</li>
 *   <li>{@link tileset.impl.TileImpl#getCharacter <em>Character</em>}</li>
 *   <li>{@link tileset.impl.TileImpl#getCharacterWidth <em>Character Width</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TileImpl extends MinimalEObjectImpl.Container implements Tile
{
	/**
	 * The cached value of the '{@link #getPixelColumns() <em>Pixel Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPixelColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<PixelColumn> pixelColumns;

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
	 * The default value of the '{@link #getCharacter() <em>Character</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacter()
	 * @generated
	 * @ordered
	 */
	protected static final String CHARACTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCharacter() <em>Character</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacter()
	 * @generated
	 * @ordered
	 */
	protected String character = CHARACTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getCharacterWidth() <em>Character Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int CHARACTER_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCharacterWidth() <em>Character Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterWidth()
	 * @generated
	 * @ordered
	 */
	protected int characterWidth = CHARACTER_WIDTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TileImpl()
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
		return TilesetPackage.Literals.TILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PixelColumn> getPixelColumns()
	{
		if (pixelColumns == null) {
			pixelColumns = new EObjectContainmentEList<PixelColumn>(PixelColumn.class, this, TilesetPackage.TILE__PIXEL_COLUMNS);
		}
		return pixelColumns;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILE__PALETTE_NAME, oldPaletteName, paletteName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCharacter() {
		return character;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacter(String newCharacter) {
		String oldCharacter = character;
		character = newCharacter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILE__CHARACTER, oldCharacter, character));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCharacterWidth() {
		return characterWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterWidth(int newCharacterWidth) {
		int oldCharacterWidth = characterWidth;
		characterWidth = newCharacterWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TilesetPackage.TILE__CHARACTER_WIDTH, oldCharacterWidth, characterWidth));
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
			case TilesetPackage.TILE__PIXEL_COLUMNS:
				return ((InternalEList<?>)getPixelColumns()).basicRemove(otherEnd, msgs);
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
			case TilesetPackage.TILE__PIXEL_COLUMNS:
				return getPixelColumns();
			case TilesetPackage.TILE__PALETTE_NAME:
				return getPaletteName();
			case TilesetPackage.TILE__CHARACTER:
				return getCharacter();
			case TilesetPackage.TILE__CHARACTER_WIDTH:
				return getCharacterWidth();
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
			case TilesetPackage.TILE__PIXEL_COLUMNS:
				getPixelColumns().clear();
				getPixelColumns().addAll((Collection<? extends PixelColumn>)newValue);
				return;
			case TilesetPackage.TILE__PALETTE_NAME:
				setPaletteName((String)newValue);
				return;
			case TilesetPackage.TILE__CHARACTER:
				setCharacter((String)newValue);
				return;
			case TilesetPackage.TILE__CHARACTER_WIDTH:
				setCharacterWidth((Integer)newValue);
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
			case TilesetPackage.TILE__PIXEL_COLUMNS:
				getPixelColumns().clear();
				return;
			case TilesetPackage.TILE__PALETTE_NAME:
				setPaletteName(PALETTE_NAME_EDEFAULT);
				return;
			case TilesetPackage.TILE__CHARACTER:
				setCharacter(CHARACTER_EDEFAULT);
				return;
			case TilesetPackage.TILE__CHARACTER_WIDTH:
				setCharacterWidth(CHARACTER_WIDTH_EDEFAULT);
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
			case TilesetPackage.TILE__PIXEL_COLUMNS:
				return pixelColumns != null && !pixelColumns.isEmpty();
			case TilesetPackage.TILE__PALETTE_NAME:
				return PALETTE_NAME_EDEFAULT == null ? paletteName != null : !PALETTE_NAME_EDEFAULT.equals(paletteName);
			case TilesetPackage.TILE__CHARACTER:
				return CHARACTER_EDEFAULT == null ? character != null : !CHARACTER_EDEFAULT.equals(character);
			case TilesetPackage.TILE__CHARACTER_WIDTH:
				return characterWidth != CHARACTER_WIDTH_EDEFAULT;
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
		result.append(" (PaletteName: ");
		result.append(paletteName);
		result.append(", Character: ");
		result.append(character);
		result.append(", CharacterWidth: ");
		result.append(characterWidth);
		result.append(')');
		return result.toString();
	}

} //TileImpl
