/**
 */
package projectmeta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see projectmeta.ProjectmetaFactory
 * @model kind="package"
 * @generated
 */
public interface ProjectmetaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "projectmeta";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/projectmeta";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "projectmeta";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProjectmetaPackage eINSTANCE = projectmeta.impl.ProjectmetaPackageImpl.init();

	/**
	 * The meta object id for the '{@link projectmeta.impl.MetadataImpl <em>Metadata</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see projectmeta.impl.MetadataImpl
	 * @see projectmeta.impl.ProjectmetaPackageImpl#getMetadata()
	 * @generated
	 */
	int METADATA = 0;

	/**
	 * The feature id for the '<em><b>Gamename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__GAMENAME = 0;

	/**
	 * The feature id for the '<em><b>Shortname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__SHORTNAME = 1;

	/**
	 * The feature id for the '<em><b>Timing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__TIMING = 2;

	/**
	 * The feature id for the '<em><b>Adressing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__ADRESSING = 3;

	/**
	 * The feature id for the '<em><b>Cartridge Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__CARTRIDGE_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Rom Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__ROM_SIZE = 5;

	/**
	 * The feature id for the '<em><b>Ram Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__RAM_SIZE = 6;

	/**
	 * The feature id for the '<em><b>Licensee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__LICENSEE = 7;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__COUNTRY = 8;

	/**
	 * The feature id for the '<em><b>Videoformat</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__VIDEOFORMAT = 9;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__VERSION = 10;

	/**
	 * The feature id for the '<em><b>Ide Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__IDE_VERSION = 11;

	/**
	 * The number of structural features of the '<em>Metadata</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Metadata</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link projectmeta.Metadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metadata</em>'.
	 * @see projectmeta.Metadata
	 * @generated
	 */
	EClass getMetadata();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getGamename <em>Gamename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gamename</em>'.
	 * @see projectmeta.Metadata#getGamename()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Gamename();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getShortname <em>Shortname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shortname</em>'.
	 * @see projectmeta.Metadata#getShortname()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Shortname();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getTiming <em>Timing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timing</em>'.
	 * @see projectmeta.Metadata#getTiming()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Timing();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getAdressing <em>Adressing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adressing</em>'.
	 * @see projectmeta.Metadata#getAdressing()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Adressing();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getCartridgeType <em>Cartridge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cartridge Type</em>'.
	 * @see projectmeta.Metadata#getCartridgeType()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_CartridgeType();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getRomSize <em>Rom Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rom Size</em>'.
	 * @see projectmeta.Metadata#getRomSize()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_RomSize();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getRamSize <em>Ram Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ram Size</em>'.
	 * @see projectmeta.Metadata#getRamSize()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_RamSize();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getLicensee <em>Licensee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Licensee</em>'.
	 * @see projectmeta.Metadata#getLicensee()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Licensee();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see projectmeta.Metadata#getCountry()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Country();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getVideoformat <em>Videoformat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Videoformat</em>'.
	 * @see projectmeta.Metadata#getVideoformat()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Videoformat();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see projectmeta.Metadata#getVersion()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Version();

	/**
	 * Returns the meta object for the attribute '{@link projectmeta.Metadata#getIdeVersion <em>Ide Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ide Version</em>'.
	 * @see projectmeta.Metadata#getIdeVersion()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_IdeVersion();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProjectmetaFactory getProjectmetaFactory();

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
		 * The meta object literal for the '{@link projectmeta.impl.MetadataImpl <em>Metadata</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see projectmeta.impl.MetadataImpl
		 * @see projectmeta.impl.ProjectmetaPackageImpl#getMetadata()
		 * @generated
		 */
		EClass METADATA = eINSTANCE.getMetadata();

		/**
		 * The meta object literal for the '<em><b>Gamename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__GAMENAME = eINSTANCE.getMetadata_Gamename();

		/**
		 * The meta object literal for the '<em><b>Shortname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__SHORTNAME = eINSTANCE.getMetadata_Shortname();

		/**
		 * The meta object literal for the '<em><b>Timing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__TIMING = eINSTANCE.getMetadata_Timing();

		/**
		 * The meta object literal for the '<em><b>Adressing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__ADRESSING = eINSTANCE.getMetadata_Adressing();

		/**
		 * The meta object literal for the '<em><b>Cartridge Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__CARTRIDGE_TYPE = eINSTANCE.getMetadata_CartridgeType();

		/**
		 * The meta object literal for the '<em><b>Rom Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__ROM_SIZE = eINSTANCE.getMetadata_RomSize();

		/**
		 * The meta object literal for the '<em><b>Ram Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__RAM_SIZE = eINSTANCE.getMetadata_RamSize();

		/**
		 * The meta object literal for the '<em><b>Licensee</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__LICENSEE = eINSTANCE.getMetadata_Licensee();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__COUNTRY = eINSTANCE.getMetadata_Country();

		/**
		 * The meta object literal for the '<em><b>Videoformat</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__VIDEOFORMAT = eINSTANCE.getMetadata_Videoformat();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__VERSION = eINSTANCE.getMetadata_Version();

		/**
		 * The meta object literal for the '<em><b>Ide Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__IDE_VERSION = eINSTANCE.getMetadata_IdeVersion();

	}

} //ProjectmetaPackage
