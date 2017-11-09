/**
 */
package palette;

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
 * @see palette.PaletteFactory
 * @model kind="package"
 * @generated
 */
public interface PalettePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "palette";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/palette";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "palette";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PalettePackage eINSTANCE = palette.impl.PalettePackageImpl.init();

	/**
	 * The meta object id for the '{@link palette.impl.PaletteImpl <em>Palette</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see palette.impl.PaletteImpl
	 * @see palette.impl.PalettePackageImpl#getPalette()
	 * @generated
	 */
	int PALETTE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Colors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__COLORS = 1;

	/**
	 * The feature id for the '<em><b>Selection Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__SELECTION_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Left Selected Color</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__LEFT_SELECTED_COLOR = 3;

	/**
	 * The feature id for the '<em><b>Right Selected Color</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__RIGHT_SELECTED_COLOR = 4;

	/**
	 * The feature id for the '<em><b>Left Selected Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__LEFT_SELECTED_INDEX = 5;

	/**
	 * The feature id for the '<em><b>Right Selected Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__RIGHT_SELECTED_INDEX = 6;

	/**
	 * The number of structural features of the '<em>Palette</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Palette</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link palette.impl.ColorImpl <em>Color</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see palette.impl.ColorImpl
	 * @see palette.impl.PalettePackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 1;

	/**
	 * The feature id for the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR__R = 0;

	/**
	 * The feature id for the '<em><b>G</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR__G = 1;

	/**
	 * The feature id for the '<em><b>B</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR__B = 2;

	/**
	 * The number of structural features of the '<em>Color</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Color</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link palette.PaletteSelectionType <em>Selection Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see palette.PaletteSelectionType
	 * @see palette.impl.PalettePackageImpl#getPaletteSelectionType()
	 * @generated
	 */
	int PALETTE_SELECTION_TYPE = 2;


	/**
	 * Returns the meta object for class '{@link palette.Palette <em>Palette</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Palette</em>'.
	 * @see palette.Palette
	 * @generated
	 */
	EClass getPalette();

	/**
	 * Returns the meta object for the attribute '{@link palette.Palette#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see palette.Palette#getName()
	 * @see #getPalette()
	 * @generated
	 */
	EAttribute getPalette_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link palette.Palette#getColors <em>Colors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Colors</em>'.
	 * @see palette.Palette#getColors()
	 * @see #getPalette()
	 * @generated
	 */
	EReference getPalette_Colors();

	/**
	 * Returns the meta object for the attribute '{@link palette.Palette#getSelectionType <em>Selection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Selection Type</em>'.
	 * @see palette.Palette#getSelectionType()
	 * @see #getPalette()
	 * @generated
	 */
	EAttribute getPalette_SelectionType();

	/**
	 * Returns the meta object for the reference '{@link palette.Palette#getLeftSelectedColor <em>Left Selected Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Selected Color</em>'.
	 * @see palette.Palette#getLeftSelectedColor()
	 * @see #getPalette()
	 * @generated
	 */
	EReference getPalette_LeftSelectedColor();

	/**
	 * Returns the meta object for the reference '{@link palette.Palette#getRightSelectedColor <em>Right Selected Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Selected Color</em>'.
	 * @see palette.Palette#getRightSelectedColor()
	 * @see #getPalette()
	 * @generated
	 */
	EReference getPalette_RightSelectedColor();

	/**
	 * Returns the meta object for the attribute '{@link palette.Palette#getLeftSelectedIndex <em>Left Selected Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Left Selected Index</em>'.
	 * @see palette.Palette#getLeftSelectedIndex()
	 * @see #getPalette()
	 * @generated
	 */
	EAttribute getPalette_LeftSelectedIndex();

	/**
	 * Returns the meta object for the attribute '{@link palette.Palette#getRightSelectedIndex <em>Right Selected Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Right Selected Index</em>'.
	 * @see palette.Palette#getRightSelectedIndex()
	 * @see #getPalette()
	 * @generated
	 */
	EAttribute getPalette_RightSelectedIndex();

	/**
	 * Returns the meta object for class '{@link palette.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Color</em>'.
	 * @see palette.Color
	 * @generated
	 */
	EClass getColor();

	/**
	 * Returns the meta object for the attribute '{@link palette.Color#getR <em>R</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R</em>'.
	 * @see palette.Color#getR()
	 * @see #getColor()
	 * @generated
	 */
	EAttribute getColor_R();

	/**
	 * Returns the meta object for the attribute '{@link palette.Color#getG <em>G</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>G</em>'.
	 * @see palette.Color#getG()
	 * @see #getColor()
	 * @generated
	 */
	EAttribute getColor_G();

	/**
	 * Returns the meta object for the attribute '{@link palette.Color#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>B</em>'.
	 * @see palette.Color#getB()
	 * @see #getColor()
	 * @generated
	 */
	EAttribute getColor_B();

	/**
	 * Returns the meta object for enum '{@link palette.PaletteSelectionType <em>Selection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Selection Type</em>'.
	 * @see palette.PaletteSelectionType
	 * @generated
	 */
	EEnum getPaletteSelectionType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PaletteFactory getPaletteFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link palette.impl.PaletteImpl <em>Palette</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see palette.impl.PaletteImpl
		 * @see palette.impl.PalettePackageImpl#getPalette()
		 * @generated
		 */
		EClass PALETTE = eINSTANCE.getPalette();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE__NAME = eINSTANCE.getPalette_Name();

		/**
		 * The meta object literal for the '<em><b>Colors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE__COLORS = eINSTANCE.getPalette_Colors();

		/**
		 * The meta object literal for the '<em><b>Selection Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE__SELECTION_TYPE = eINSTANCE.getPalette_SelectionType();

		/**
		 * The meta object literal for the '<em><b>Left Selected Color</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE__LEFT_SELECTED_COLOR = eINSTANCE.getPalette_LeftSelectedColor();

		/**
		 * The meta object literal for the '<em><b>Right Selected Color</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE__RIGHT_SELECTED_COLOR = eINSTANCE.getPalette_RightSelectedColor();

		/**
		 * The meta object literal for the '<em><b>Left Selected Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE__LEFT_SELECTED_INDEX = eINSTANCE.getPalette_LeftSelectedIndex();

		/**
		 * The meta object literal for the '<em><b>Right Selected Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE__RIGHT_SELECTED_INDEX = eINSTANCE.getPalette_RightSelectedIndex();

		/**
		 * The meta object literal for the '{@link palette.impl.ColorImpl <em>Color</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see palette.impl.ColorImpl
		 * @see palette.impl.PalettePackageImpl#getColor()
		 * @generated
		 */
		EClass COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '<em><b>R</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR__R = eINSTANCE.getColor_R();

		/**
		 * The meta object literal for the '<em><b>G</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR__G = eINSTANCE.getColor_G();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR__B = eINSTANCE.getColor_B();

		/**
		 * The meta object literal for the '{@link palette.PaletteSelectionType <em>Selection Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see palette.PaletteSelectionType
		 * @see palette.impl.PalettePackageImpl#getPaletteSelectionType()
		 * @generated
		 */
		EEnum PALETTE_SELECTION_TYPE = eINSTANCE.getPaletteSelectionType();

	}

} //PalettePackage
