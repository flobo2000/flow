/**
 */
package projectmeta.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import projectmeta.Metadata;
import projectmeta.ProjectmetaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metadata</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link projectmeta.impl.MetadataImpl#getGamename <em>Gamename</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getShortname <em>Shortname</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getTiming <em>Timing</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getAdressing <em>Adressing</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getCartridgeType <em>Cartridge Type</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getRomSize <em>Rom Size</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getRamSize <em>Ram Size</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getLicensee <em>Licensee</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getCountry <em>Country</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getVideoformat <em>Videoformat</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link projectmeta.impl.MetadataImpl#getIdeVersion <em>Ide Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MetadataImpl extends MinimalEObjectImpl.Container implements Metadata {
	/**
	 * The default value of the '{@link #getGamename() <em>Gamename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGamename()
	 * @generated
	 * @ordered
	 */
	protected static final String GAMENAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGamename() <em>Gamename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGamename()
	 * @generated
	 * @ordered
	 */
	protected String gamename = GAMENAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortname() <em>Shortname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortname()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORTNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortname() <em>Shortname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortname()
	 * @generated
	 * @ordered
	 */
	protected String shortname = SHORTNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTiming() <em>Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTiming()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTiming() <em>Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTiming()
	 * @generated
	 * @ordered
	 */
	protected String timing = TIMING_EDEFAULT;

	/**
	 * The default value of the '{@link #getAdressing() <em>Adressing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdressing()
	 * @generated
	 * @ordered
	 */
	protected static final String ADRESSING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdressing() <em>Adressing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdressing()
	 * @generated
	 * @ordered
	 */
	protected String adressing = ADRESSING_EDEFAULT;

	/**
	 * The default value of the '{@link #getCartridgeType() <em>Cartridge Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCartridgeType()
	 * @generated
	 * @ordered
	 */
	protected static final String CARTRIDGE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCartridgeType() <em>Cartridge Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCartridgeType()
	 * @generated
	 * @ordered
	 */
	protected String cartridgeType = CARTRIDGE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRomSize() <em>Rom Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRomSize()
	 * @generated
	 * @ordered
	 */
	protected static final String ROM_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRomSize() <em>Rom Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRomSize()
	 * @generated
	 * @ordered
	 */
	protected String romSize = ROM_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRamSize() <em>Ram Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRamSize()
	 * @generated
	 * @ordered
	 */
	protected static final String RAM_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRamSize() <em>Ram Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRamSize()
	 * @generated
	 * @ordered
	 */
	protected String ramSize = RAM_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLicensee() <em>Licensee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicensee()
	 * @generated
	 * @ordered
	 */
	protected static final String LICENSEE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLicensee() <em>Licensee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicensee()
	 * @generated
	 * @ordered
	 */
	protected String licensee = LICENSEE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCountry() <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountry()
	 * @generated
	 * @ordered
	 */
	protected static final String COUNTRY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCountry() <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountry()
	 * @generated
	 * @ordered
	 */
	protected String country = COUNTRY_EDEFAULT;

	/**
	 * The default value of the '{@link #getVideoformat() <em>Videoformat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVideoformat()
	 * @generated
	 * @ordered
	 */
	protected static final String VIDEOFORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVideoformat() <em>Videoformat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVideoformat()
	 * @generated
	 * @ordered
	 */
	protected String videoformat = VIDEOFORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected int version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getIdeVersion() <em>Ide Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdeVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String IDE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdeVersion() <em>Ide Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdeVersion()
	 * @generated
	 * @ordered
	 */
	protected String ideVersion = IDE_VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetadataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProjectmetaPackage.Literals.METADATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGamename() {
		return gamename;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGamename(String newGamename) {
		String oldGamename = gamename;
		gamename = newGamename;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__GAMENAME, oldGamename, gamename));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortname() {
		return shortname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortname(String newShortname) {
		String oldShortname = shortname;
		shortname = newShortname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__SHORTNAME, oldShortname, shortname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTiming() {
		return timing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTiming(String newTiming) {
		String oldTiming = timing;
		timing = newTiming;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__TIMING, oldTiming, timing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdressing() {
		return adressing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdressing(String newAdressing) {
		String oldAdressing = adressing;
		adressing = newAdressing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__ADRESSING, oldAdressing, adressing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCartridgeType() {
		return cartridgeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCartridgeType(String newCartridgeType) {
		String oldCartridgeType = cartridgeType;
		cartridgeType = newCartridgeType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__CARTRIDGE_TYPE, oldCartridgeType, cartridgeType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRomSize() {
		return romSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRomSize(String newRomSize) {
		String oldRomSize = romSize;
		romSize = newRomSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__ROM_SIZE, oldRomSize, romSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRamSize() {
		return ramSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRamSize(String newRamSize) {
		String oldRamSize = ramSize;
		ramSize = newRamSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__RAM_SIZE, oldRamSize, ramSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLicensee() {
		return licensee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLicensee(String newLicensee) {
		String oldLicensee = licensee;
		licensee = newLicensee;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__LICENSEE, oldLicensee, licensee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountry(String newCountry) {
		String oldCountry = country;
		country = newCountry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__COUNTRY, oldCountry, country));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVideoformat() {
		return videoformat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVideoformat(String newVideoformat) {
		String oldVideoformat = videoformat;
		videoformat = newVideoformat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__VIDEOFORMAT, oldVideoformat, videoformat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(int newVersion) {
		int oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdeVersion() {
		return ideVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdeVersion(String newIdeVersion) {
		String oldIdeVersion = ideVersion;
		ideVersion = newIdeVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectmetaPackage.METADATA__IDE_VERSION, oldIdeVersion, ideVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProjectmetaPackage.METADATA__GAMENAME:
				return getGamename();
			case ProjectmetaPackage.METADATA__SHORTNAME:
				return getShortname();
			case ProjectmetaPackage.METADATA__TIMING:
				return getTiming();
			case ProjectmetaPackage.METADATA__ADRESSING:
				return getAdressing();
			case ProjectmetaPackage.METADATA__CARTRIDGE_TYPE:
				return getCartridgeType();
			case ProjectmetaPackage.METADATA__ROM_SIZE:
				return getRomSize();
			case ProjectmetaPackage.METADATA__RAM_SIZE:
				return getRamSize();
			case ProjectmetaPackage.METADATA__LICENSEE:
				return getLicensee();
			case ProjectmetaPackage.METADATA__COUNTRY:
				return getCountry();
			case ProjectmetaPackage.METADATA__VIDEOFORMAT:
				return getVideoformat();
			case ProjectmetaPackage.METADATA__VERSION:
				return getVersion();
			case ProjectmetaPackage.METADATA__IDE_VERSION:
				return getIdeVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ProjectmetaPackage.METADATA__GAMENAME:
				setGamename((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__SHORTNAME:
				setShortname((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__TIMING:
				setTiming((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__ADRESSING:
				setAdressing((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__CARTRIDGE_TYPE:
				setCartridgeType((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__ROM_SIZE:
				setRomSize((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__RAM_SIZE:
				setRamSize((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__LICENSEE:
				setLicensee((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__COUNTRY:
				setCountry((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__VIDEOFORMAT:
				setVideoformat((String)newValue);
				return;
			case ProjectmetaPackage.METADATA__VERSION:
				setVersion((Integer)newValue);
				return;
			case ProjectmetaPackage.METADATA__IDE_VERSION:
				setIdeVersion((String)newValue);
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
		switch (featureID) {
			case ProjectmetaPackage.METADATA__GAMENAME:
				setGamename(GAMENAME_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__SHORTNAME:
				setShortname(SHORTNAME_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__TIMING:
				setTiming(TIMING_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__ADRESSING:
				setAdressing(ADRESSING_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__CARTRIDGE_TYPE:
				setCartridgeType(CARTRIDGE_TYPE_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__ROM_SIZE:
				setRomSize(ROM_SIZE_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__RAM_SIZE:
				setRamSize(RAM_SIZE_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__LICENSEE:
				setLicensee(LICENSEE_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__COUNTRY:
				setCountry(COUNTRY_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__VIDEOFORMAT:
				setVideoformat(VIDEOFORMAT_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ProjectmetaPackage.METADATA__IDE_VERSION:
				setIdeVersion(IDE_VERSION_EDEFAULT);
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
		switch (featureID) {
			case ProjectmetaPackage.METADATA__GAMENAME:
				return GAMENAME_EDEFAULT == null ? gamename != null : !GAMENAME_EDEFAULT.equals(gamename);
			case ProjectmetaPackage.METADATA__SHORTNAME:
				return SHORTNAME_EDEFAULT == null ? shortname != null : !SHORTNAME_EDEFAULT.equals(shortname);
			case ProjectmetaPackage.METADATA__TIMING:
				return TIMING_EDEFAULT == null ? timing != null : !TIMING_EDEFAULT.equals(timing);
			case ProjectmetaPackage.METADATA__ADRESSING:
				return ADRESSING_EDEFAULT == null ? adressing != null : !ADRESSING_EDEFAULT.equals(adressing);
			case ProjectmetaPackage.METADATA__CARTRIDGE_TYPE:
				return CARTRIDGE_TYPE_EDEFAULT == null ? cartridgeType != null : !CARTRIDGE_TYPE_EDEFAULT.equals(cartridgeType);
			case ProjectmetaPackage.METADATA__ROM_SIZE:
				return ROM_SIZE_EDEFAULT == null ? romSize != null : !ROM_SIZE_EDEFAULT.equals(romSize);
			case ProjectmetaPackage.METADATA__RAM_SIZE:
				return RAM_SIZE_EDEFAULT == null ? ramSize != null : !RAM_SIZE_EDEFAULT.equals(ramSize);
			case ProjectmetaPackage.METADATA__LICENSEE:
				return LICENSEE_EDEFAULT == null ? licensee != null : !LICENSEE_EDEFAULT.equals(licensee);
			case ProjectmetaPackage.METADATA__COUNTRY:
				return COUNTRY_EDEFAULT == null ? country != null : !COUNTRY_EDEFAULT.equals(country);
			case ProjectmetaPackage.METADATA__VIDEOFORMAT:
				return VIDEOFORMAT_EDEFAULT == null ? videoformat != null : !VIDEOFORMAT_EDEFAULT.equals(videoformat);
			case ProjectmetaPackage.METADATA__VERSION:
				return version != VERSION_EDEFAULT;
			case ProjectmetaPackage.METADATA__IDE_VERSION:
				return IDE_VERSION_EDEFAULT == null ? ideVersion != null : !IDE_VERSION_EDEFAULT.equals(ideVersion);
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
		result.append(" (Gamename: ");
		result.append(gamename);
		result.append(", Shortname: ");
		result.append(shortname);
		result.append(", Timing: ");
		result.append(timing);
		result.append(", Adressing: ");
		result.append(adressing);
		result.append(", CartridgeType: ");
		result.append(cartridgeType);
		result.append(", RomSize: ");
		result.append(romSize);
		result.append(", RamSize: ");
		result.append(ramSize);
		result.append(", Licensee: ");
		result.append(licensee);
		result.append(", Country: ");
		result.append(country);
		result.append(", Videoformat: ");
		result.append(videoformat);
		result.append(", Version: ");
		result.append(version);
		result.append(", IdeVersion: ");
		result.append(ideVersion);
		result.append(')');
		return result.toString();
	}

} //MetadataImpl
