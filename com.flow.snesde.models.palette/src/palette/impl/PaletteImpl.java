/**
 */
package palette.impl;

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
import palette.Color;
import palette.Palette;
import palette.PalettePackage;
import palette.PaletteSelectionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Palette</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link palette.impl.PaletteImpl#getName <em>Name</em>}</li>
 *   <li>{@link palette.impl.PaletteImpl#getColors <em>Colors</em>}</li>
 *   <li>{@link palette.impl.PaletteImpl#getSelectionType <em>Selection Type</em>}</li>
 *   <li>{@link palette.impl.PaletteImpl#getLeftSelectedColor <em>Left Selected Color</em>}</li>
 *   <li>{@link palette.impl.PaletteImpl#getRightSelectedColor <em>Right Selected Color</em>}</li>
 *   <li>{@link palette.impl.PaletteImpl#getLeftSelectedIndex <em>Left Selected Index</em>}</li>
 *   <li>{@link palette.impl.PaletteImpl#getRightSelectedIndex <em>Right Selected Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PaletteImpl extends MinimalEObjectImpl.Container implements Palette {
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
	 * The cached value of the '{@link #getColors() <em>Colors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColors()
	 * @generated
	 * @ordered
	 */
	protected EList<Color> colors;

	/**
	 * The default value of the '{@link #getSelectionType() <em>Selection Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectionType()
	 * @generated
	 * @ordered
	 */
	protected static final PaletteSelectionType SELECTION_TYPE_EDEFAULT = PaletteSelectionType.ONE_COLOR;

	/**
	 * The cached value of the '{@link #getSelectionType() <em>Selection Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectionType()
	 * @generated
	 * @ordered
	 */
	protected PaletteSelectionType selectionType = SELECTION_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftSelectedColor() <em>Left Selected Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftSelectedColor()
	 * @generated
	 * @ordered
	 */
	protected Color leftSelectedColor;

	/**
	 * The cached value of the '{@link #getRightSelectedColor() <em>Right Selected Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightSelectedColor()
	 * @generated
	 * @ordered
	 */
	protected Color rightSelectedColor;

	/**
	 * The default value of the '{@link #getLeftSelectedIndex() <em>Left Selected Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftSelectedIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LEFT_SELECTED_INDEX_EDEFAULT = new Integer(0);

	/**
	 * The cached value of the '{@link #getLeftSelectedIndex() <em>Left Selected Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftSelectedIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer leftSelectedIndex = LEFT_SELECTED_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getRightSelectedIndex() <em>Right Selected Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightSelectedIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer RIGHT_SELECTED_INDEX_EDEFAULT = new Integer(0);

	/**
	 * The cached value of the '{@link #getRightSelectedIndex() <em>Right Selected Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightSelectedIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer rightSelectedIndex = RIGHT_SELECTED_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PaletteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PalettePackage.Literals.PALETTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PalettePackage.PALETTE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Color> getColors() {
		if (colors == null)
		{
			colors = new EObjectContainmentEList<Color>(Color.class, this, PalettePackage.PALETTE__COLORS);
		}
		return colors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaletteSelectionType getSelectionType() {
		return selectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectionType(PaletteSelectionType newSelectionType) {
		PaletteSelectionType oldSelectionType = selectionType;
		selectionType = newSelectionType == null ? SELECTION_TYPE_EDEFAULT : newSelectionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PalettePackage.PALETTE__SELECTION_TYPE, oldSelectionType, selectionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getLeftSelectedColor() {
		if (leftSelectedColor != null && leftSelectedColor.eIsProxy())
		{
			InternalEObject oldLeftSelectedColor = (InternalEObject)leftSelectedColor;
			leftSelectedColor = (Color)eResolveProxy(oldLeftSelectedColor);
			if (leftSelectedColor != oldLeftSelectedColor)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PalettePackage.PALETTE__LEFT_SELECTED_COLOR, oldLeftSelectedColor, leftSelectedColor));
			}
		}
		return leftSelectedColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetLeftSelectedColor() {
		return leftSelectedColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftSelectedColor(Color newLeftSelectedColor) {
		Color oldLeftSelectedColor = leftSelectedColor;
		leftSelectedColor = newLeftSelectedColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PalettePackage.PALETTE__LEFT_SELECTED_COLOR, oldLeftSelectedColor, leftSelectedColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getRightSelectedColor() {
		if (rightSelectedColor != null && rightSelectedColor.eIsProxy())
		{
			InternalEObject oldRightSelectedColor = (InternalEObject)rightSelectedColor;
			rightSelectedColor = (Color)eResolveProxy(oldRightSelectedColor);
			if (rightSelectedColor != oldRightSelectedColor)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PalettePackage.PALETTE__RIGHT_SELECTED_COLOR, oldRightSelectedColor, rightSelectedColor));
			}
		}
		return rightSelectedColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetRightSelectedColor() {
		return rightSelectedColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightSelectedColor(Color newRightSelectedColor) {
		Color oldRightSelectedColor = rightSelectedColor;
		rightSelectedColor = newRightSelectedColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PalettePackage.PALETTE__RIGHT_SELECTED_COLOR, oldRightSelectedColor, rightSelectedColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLeftSelectedIndex()
	{
		return leftSelectedIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftSelectedIndex(Integer newLeftSelectedIndex)
	{
		Integer oldLeftSelectedIndex = leftSelectedIndex;
		leftSelectedIndex = newLeftSelectedIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PalettePackage.PALETTE__LEFT_SELECTED_INDEX, oldLeftSelectedIndex, leftSelectedIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getRightSelectedIndex()
	{
		return rightSelectedIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightSelectedIndex(Integer newRightSelectedIndex)
	{
		Integer oldRightSelectedIndex = rightSelectedIndex;
		rightSelectedIndex = newRightSelectedIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PalettePackage.PALETTE__RIGHT_SELECTED_INDEX, oldRightSelectedIndex, rightSelectedIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case PalettePackage.PALETTE__COLORS:
				return ((InternalEList<?>)getColors()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case PalettePackage.PALETTE__NAME:
				return getName();
			case PalettePackage.PALETTE__COLORS:
				return getColors();
			case PalettePackage.PALETTE__SELECTION_TYPE:
				return getSelectionType();
			case PalettePackage.PALETTE__LEFT_SELECTED_COLOR:
				if (resolve) return getLeftSelectedColor();
				return basicGetLeftSelectedColor();
			case PalettePackage.PALETTE__RIGHT_SELECTED_COLOR:
				if (resolve) return getRightSelectedColor();
				return basicGetRightSelectedColor();
			case PalettePackage.PALETTE__LEFT_SELECTED_INDEX:
				return getLeftSelectedIndex();
			case PalettePackage.PALETTE__RIGHT_SELECTED_INDEX:
				return getRightSelectedIndex();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case PalettePackage.PALETTE__NAME:
				setName((String)newValue);
				return;
			case PalettePackage.PALETTE__COLORS:
				getColors().clear();
				getColors().addAll((Collection<? extends Color>)newValue);
				return;
			case PalettePackage.PALETTE__SELECTION_TYPE:
				setSelectionType((PaletteSelectionType)newValue);
				return;
			case PalettePackage.PALETTE__LEFT_SELECTED_COLOR:
				setLeftSelectedColor((Color)newValue);
				return;
			case PalettePackage.PALETTE__RIGHT_SELECTED_COLOR:
				setRightSelectedColor((Color)newValue);
				return;
			case PalettePackage.PALETTE__LEFT_SELECTED_INDEX:
				setLeftSelectedIndex((Integer)newValue);
				return;
			case PalettePackage.PALETTE__RIGHT_SELECTED_INDEX:
				setRightSelectedIndex((Integer)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case PalettePackage.PALETTE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PalettePackage.PALETTE__COLORS:
				getColors().clear();
				return;
			case PalettePackage.PALETTE__SELECTION_TYPE:
				setSelectionType(SELECTION_TYPE_EDEFAULT);
				return;
			case PalettePackage.PALETTE__LEFT_SELECTED_COLOR:
				setLeftSelectedColor((Color)null);
				return;
			case PalettePackage.PALETTE__RIGHT_SELECTED_COLOR:
				setRightSelectedColor((Color)null);
				return;
			case PalettePackage.PALETTE__LEFT_SELECTED_INDEX:
				setLeftSelectedIndex(LEFT_SELECTED_INDEX_EDEFAULT);
				return;
			case PalettePackage.PALETTE__RIGHT_SELECTED_INDEX:
				setRightSelectedIndex(RIGHT_SELECTED_INDEX_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case PalettePackage.PALETTE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PalettePackage.PALETTE__COLORS:
				return colors != null && !colors.isEmpty();
			case PalettePackage.PALETTE__SELECTION_TYPE:
				return selectionType != SELECTION_TYPE_EDEFAULT;
			case PalettePackage.PALETTE__LEFT_SELECTED_COLOR:
				return leftSelectedColor != null;
			case PalettePackage.PALETTE__RIGHT_SELECTED_COLOR:
				return rightSelectedColor != null;
			case PalettePackage.PALETTE__LEFT_SELECTED_INDEX:
				return LEFT_SELECTED_INDEX_EDEFAULT == null ? leftSelectedIndex != null : !LEFT_SELECTED_INDEX_EDEFAULT.equals(leftSelectedIndex);
			case PalettePackage.PALETTE__RIGHT_SELECTED_INDEX:
				return RIGHT_SELECTED_INDEX_EDEFAULT == null ? rightSelectedIndex != null : !RIGHT_SELECTED_INDEX_EDEFAULT.equals(rightSelectedIndex);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Name: ");
		result.append(name);
		result.append(", SelectionType: ");
		result.append(selectionType);
		result.append(", LeftSelectedIndex: ");
		result.append(leftSelectedIndex);
		result.append(", RightSelectedIndex: ");
		result.append(rightSelectedIndex);
		result.append(')');
		return result.toString();
	}

} //PaletteImpl
