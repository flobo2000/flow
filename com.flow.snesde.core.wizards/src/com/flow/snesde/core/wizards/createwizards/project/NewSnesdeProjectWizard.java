package com.flow.snesde.core.wizards.createwizards.project;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import projectmeta.ProjectmetaFactory;
import projectmeta.ProjectmetaPackage;
import projectmeta.impl.MetadataImpl;

import com.flow.snesde.core.project.SnesdeProjectNature;
import com.flow.snesde.core.util.SnesdeGlobalConstants;
import com.flow.snesde.core.wizards.createwizards.project.pages.NewSnesdeProjectWizardPage;

/**
 * @author flo This wizard creates a new SNESDE project, the needed folder
 *         structure, the starter script and the project configuration
 */
public class NewSnesdeProjectWizard extends Wizard implements INewWizard,
		IExecutableExtension
{
	/**
	 * This caches an instance of the model package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProjectmetaPackage projectmetaPackage = ProjectmetaPackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProjectmetaFactory projectmetaFactory = projectmetaPackage
			.getProjectmetaFactory();

	/**
	 * the id referenced in plugin.xml
	 */
	public static final String id = "com.flow.snesde.core.wizards.create.project.NewProjectWizard";

	/**
	 * the first (and up to now only) wizard page
	 */
	NewSnesdeProjectWizardPage page;

	/**
	 * auto generated... whatever it does
	 */
	private IConfigurationElement _configurationElement;

	private String projectName;
	private String gameName;
	private String shortName;
	private String timing;
	private String addressing;
	private String carttype;
	private String romsize;
	private String ramsize;
	private String country;
	private String licensee;
	private String video;
	private int version;

	/**
	 * The standard constructor
	 */
	public NewSnesdeProjectWizard()
	{
		setWindowTitle("New SNESDE Project");
	}

	@Override
	public void init(final IWorkbench workbench,
			final IStructuredSelection selection)
	{

	}

	@Override
	public void addPages()
	{
		page = new NewSnesdeProjectWizardPage("New Project");
		addPage(page);
	}

	@Override
	public boolean performFinish()
	{
		projectName = page.getProjectName();
		gameName = page.getGameName();
		shortName = page.getShortname();
		timing = page.getTiming();
		addressing = page.getAdressing();
		carttype = page.getCarttype();
		romsize = page.getRomsize();
		ramsize = page.getRamsize();
		country = page.getCountry();
		licensee = page.getLicensee();
		video = page.getVideoFormat();
		version = page.getVersion();
		createProject();
		BasicNewProjectResourceWizard
				.updatePerspective(this._configurationElement);
		return true;
	}

	/**
	 * This method actually creates the project and the default resources
	 * 
	 * @param projectName
	 *            the project name to be used
	 * @return the IProject resource which is created
	 */
	public IProject createProject()
	{
		Assert.isNotNull(this.projectName);
		Assert.isNotNull(this.gameName);
		Assert.isTrue(!this.projectName.equals(""));

		try
		{
			IProject project = createIProject(this.projectName);
			addSnesdeNature(project);
			addSnesdeBuilder(project);
			String[] paths = { "Scripts", "Tilesets", "Color Palettes",
					"Tilemaps", "Music", "Samples", "Output" };
			addFoldersToProject(project, paths);

			String[] files = { "project.meta", "main.dscript",
					"Scripts/header.dscript", "Scripts/initSnes.dscript" };
			addFilesToProject(project, files);

			return project;
		}
		catch (CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param project
	 *            the project to add the builder to
	 * @throws CoreException
	 *             occurs if the builder couldn't be added
	 */
	private void addSnesdeBuilder(final IProject project) throws CoreException
	{
		// builderid
		String builderId = "com.flow.snesde.core.project.SnesdeProjectBuilder";
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();
		for (ICommand command : commands)
		{
			if (command.getBuilderName().equals(builderId))
			{
				return;
			}
		}
		// add builder to project
		ICommand command = desc.newCommand();
		command.setBuilderName(builderId);
		ICommand[] nc = new ICommand[commands.length + 1];
		// Add it before other builders.
		System.arraycopy(commands, 0, nc, 1, commands.length);
		nc[0] = command;
		desc.setBuildSpec(nc);
		project.setDescription(desc, null);
	}

	/**
	 * create the IProject
	 * 
	 * @param projectName
	 *            the name of the new project
	 * @return the iProject created
	 */
	private IProject createIProject(final String projectName)
	{
		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);

		if (!newProject.exists())
		{
			IProjectDescription desc = newProject.getWorkspace()
					.newProjectDescription(newProject.getName());
			desc.setLocationURI(null);
			try
			{
				newProject.create(desc, null);
				if (!newProject.isOpen())
				{
					newProject.open(null);
				}
			}
			catch (CoreException e)
			{
				e.printStackTrace();
			}
		}
		return newProject;
	}

	/**
	 * adds the initial files to the project
	 * 
	 * @param newProject
	 *            the IProject just created
	 * @param filenames
	 *            the array of filenames to create
	 * @throws CoreException
	 *             an exception if any of the files failed to create
	 */
	private void addFilesToProject(final IProject newProject,
			final String[] filenames) throws CoreException
	{
		for (String name : filenames)
		{
			if (name.contains("/"))
			{
				// create files in a subdirectory of the project
				IFolder folder = newProject.getFolder(name.split("/")[0]);
				if (!folder.exists())
				{
					createFolder(folder);
				}
				IFile newFile = folder.getFile(name.split("/")[1]);
				createFile(newFile, name);
			}
			else
			{
				// create files directly in the project base folder
				IFile newFile = newProject.getFile(name);
				createFile(newFile, name);
			}
		}
	}

	/**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 * 
	 * @param newProject
	 *            the project to create
	 * @param paths
	 *            the folder structure to create
	 * @throws CoreException
	 *             an exception if any of the resources failed to create
	 */
	private void addFoldersToProject(final IProject newProject,
			final String[] paths) throws CoreException
	{
		for (String path : paths)
		{
			IFolder etcFolders = newProject.getFolder(path);
			createFolder(etcFolders);
		}
	}

	/**
	 * adds the SNESDE projectnature to the IProject
	 * 
	 * @param project
	 *            the IProject to add the nature to
	 * @throws CoreException
	 *             an exception if the nature adding failed
	 */
	private void addSnesdeNature(final IProject project) throws CoreException
	{
		if (!project.hasNature(SnesdeProjectNature.NATURE_ID))
		{
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = SnesdeProjectNature.NATURE_ID;
			description.setNatureIds(newNatures);

			IProgressMonitor monitor = null;
			project.setDescription(description, monitor);
		}
	}

	/**
	 * creates one folder within the project
	 * 
	 * @param folder
	 *            the new IFolder to be created
	 * @throws CoreException
	 *             can occur if the IFolder couldn't be created
	 */
	private void createFolder(final IFolder folder) throws CoreException
	{
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder)
		{
			createFolder((IFolder) parent);
		}
		if (!folder.exists())
		{
			folder.create(false, true, null);
		}
	}

	/**
	 * creaes an IFile
	 * 
	 * @param file
	 *            the IFile to be created
	 * @param filename
	 *            the filename to be used
	 * @throws CoreException
	 *             thrown if no file was created
	 */
	private void createFile(final IFile file, final String filename)
			throws CoreException
	{
		if (!file.exists())
		{
			String stringToWrite = null;

			if (filename.equals("project.meta"))
			{
				String game4 = this.shortName;
				if (game4 == null || game4.equals(""))
				{
					game4 = this.gameName.substring(0, 4);
				}

				stringToWrite = "snesde_version="
						+ SnesdeGlobalConstants.snesdeVersion + "\n";
				stringToWrite += "longname=" + this.gameName + "\n";
				stringToWrite += "shortname=" + game4 + "\n";
				stringToWrite += "timing=" + this.timing + "\n";
				stringToWrite += "addressing=" + this.addressing + "\n";
				stringToWrite += "carttype=" + this.carttype + "\n";
				stringToWrite += "romsize=" + this.romsize + "\n";
				stringToWrite += "ramsize=" + this.ramsize + "\n";
				stringToWrite += "country=" + this.country + "\n";
				stringToWrite += "licensee=" + this.licensee + "\n";
				stringToWrite += "video=" + this.video + "\n";
				stringToWrite += "version=" + this.version + "\n";
			}
			else if (filename.equals("main.dscript"))
			{
				String date = new SimpleDateFormat("dd.MM.yyyy")
						.format(new Date());
				String time = new SimpleDateFormat("k:m:s").format(new Date());
				stringToWrite = ";Start script for SNESDE project "
						+ this.projectName
						+ "\n;Created "
						+ date
						+ " - "
						+ time
						+ "\n\nflow.include(script,header);\nflow.include(script,initSnes);\n\n;Needed to satisfy interrupt definition in \"Header.inc\".\nVBlank:\n  rti\n\n.bank 0\n.section \"MainCode\"\n\nStart:\n;Initialize the SNES.\nSnes_Init\n\n\n;Put your code here...\n\n\n;Loop forever.\nForever:\njmp Forever\n\n.ends";
			}
			else if (filename.equals("Scripts/initSnes.dscript"))
			{
				stringToWrite = ";Standardscript \"initSnes\"\n\n.MACRO Snes_Init\n	sei 	 	; Disabled interrupts\n	clc 	 	; clear carry to switch to native mode\n	xce 	 	; Xchange carry & emulation bit. native mode\n	rep 	#$18 	; Binary mode (decimal mode off), X/Y 16 bit\n        ldx 	#$1FFF  ; set stack to $1FFF\n        txs\n\n        jsr Init\n.ENDM\n\n\n.bank 0\n.section \"Snes_Init\" SEMIFREE\nInit:\n	sep 	#$30    ; X,Y,A are 8 bit numbers\n	lda 	#$8F    ; screen off, full brightness\n	sta 	$2100   ; brightness + screen enable register \n	stz 	$2101   ; Sprite register (size + address in VRAM) \n	stz 	$2102   ; Sprite registers (address of sprite memory [OAM])\n	stz 	$2103   ;    \"\"                       \"\"\n	stz 	$2105   ; Mode 0, = Graphic mode register\n	stz 	$2106   ; noplanes, no mosaic, = Mosaic register\n	stz 	$2107   ; Plane 0 map VRAM location\n	stz 	$2108   ; Plane 1 map VRAM location\n	stz 	$2109   ; Plane 2 map VRAM location\n	stz 	$210A   ; Plane 3 map VRAM location\n	stz 	$210B   ; Plane 0+1 Tile data location\n	stz 	$210C   ; Plane 2+3 Tile data location\n	stz 	$210D   ; Plane 0 scroll x (first 8 bits)\n	stz 	$210D   ; Plane 0 scroll x (last 3 bits) #$0 - #$07ff\n	stz 	$210E   ; Plane 0 scroll y (first 8 bits)\n	stz 	$210E   ; Plane 0 scroll y (last 3 bits) #$0 - #$07ff\n	stz 	$210F   ; Plane 1 scroll x (first 8 bits)\n	stz 	$210F   ; Plane 1 scroll x (last 3 bits) #$0 - #$07ff\n	stz 	$2110   ; Plane 1 scroll y (first 8 bits)\n	stz 	$2110   ; Plane 1 scroll y (last 3 bits) #$0 - #$07ff\n	stz 	$2111   ; Plane 2 scroll x (first 8 bits)\n	stz 	$2111   ; Plane 2 scroll x (last 3 bits) #$0 - #$07ff\n	stz 	$2112   ; Plane 2 scroll y (first 8 bits)\n	stz 	$2112   ; Plane 2 scroll y (last 3 bits) #$0 - #$07ff\n	stz 	$2113   ; Plane 3 scroll x (first 8 bits)\n	stz 	$2113   ; Plane 3 scroll x (last 3 bits) #$0 - #$07ff\n	stz 	$2114   ; Plane 3 scroll y (first 8 bits)\n	stz 	$2114   ; Plane 3 scroll y (last 3 bits) #$0 - #$07ff\n	lda 	#$80    ; increase VRAM address after writing to $2119\n	sta 	$2115   ; VRAM address increment register\n	stz 	$2116   ; VRAM address low\n	stz 	$2117   ; VRAM address high\n	stz 	$211A   ; Initial Mode 7 setting register\n	stz 	$211B   ; Mode 7 matrix parameter A register (low)\n	lda 	#$01\n	sta 	$211B   ; Mode 7 matrix parameter A register (high)\n	stz 	$211C   ; Mode 7 matrix parameter B register (low)\n	stz 	$211C   ; Mode 7 matrix parameter B register (high)\n	stz 	$211D   ; Mode 7 matrix parameter C register (low)\n	stz 	$211D   ; Mode 7 matrix parameter C register (high)\n	stz 	$211E   ; Mode 7 matrix parameter D register (low)\n	sta 	$211E   ; Mode 7 matrix parameter D register (high)\n	stz 	$211F   ; Mode 7 center position X register (low)\n	stz 	$211F   ; Mode 7 center position X register (high)\n	stz 	$2120   ; Mode 7 center position Y register (low)\n	stz 	$2120   ; Mode 7 center position Y register (high)\n	stz 	$2121   ; Color number register ($0-ff)\n	stz 	$2123   ; BG1 & BG2 Window mask setting register\n	stz 	$2124   ; BG3 & BG4 Window mask setting register\n	stz 	$2125   ; OBJ & Color Window mask setting register\n	stz 	$2126   ; Window 1 left position register\n	stz 	$2127   ; Window 2 left position register\n	stz 	$2128   ; Window 3 left position register\n	stz 	$2129   ; Window 4 left position register\n	stz 	$212A   ; BG1, BG2, BG3, BG4 Window Logic register\n	stz 	$212B   ; OBJ, Color Window Logic Register (or,and,xor,xnor)\n	sta 	$212C   ; Main Screen designation (planes, sprites enable)\n	stz 	$212D   ; Sub Screen designation\n	stz 	$212E   ; Window mask for Main Screen\n	stz 	$212F   ; Window mask for Sub Screen\n	lda 	#$30\n	sta 	$2130   ; Color addition & screen addition init setting\n	stz 	$2131   ; Add/Sub sub designation for screen, sprite, color\n	lda 	#$E0\n	sta 	$2132   ; color data for addition/subtraction\n	stz 	$2133   ; Screen setting (interlace x,y/enable SFX data)\n	stz 	$4200   ; Enable V-blank, interrupt, Joypad register\n	lda 	#$FF\n	sta 	$4201   ; Programmable I/O port\n	stz 	$4202   ; Multiplicand A\n	stz 	$4203   ; Multiplier B\n	stz 	$4204   ; Multiplier C\n	stz 	$4205   ; Multiplicand C\n	stz 	$4206   ; Divisor B\n	stz 	$4207   ; Horizontal Count Timer\n	stz 	$4208   ; Horizontal Count Timer MSB (most significant bit)\n	stz 	$4209   ; Vertical Count Timer\n	stz 	$420A   ; Vertical Count Timer MSB\n	stz 	$420B   ; General DMA enable (bits 0-7)\n	stz 	$420C   ; Horizontal DMA (HDMA) enable (bits 0-7)\n	stz 	$420D	; Access cycle designation (slow/fast rom)\n	cli 	 	; Enable interrupts\n	rts\n.ends\n";
			}
			else if (filename.equals("Scripts/header.dscript"))
			{
				stringToWrite = ";Standardscript \"header\"\n\n;==LoRom==\n\n.MEMORYMAP                      ; Begin describing the system architecture.\n  SLOTSIZE $8000                ; The slot is $8000 bytes in size. More details on slots later.\n  DEFAULTSLOT 0\n  SLOT 0 $8000                  ; Defines Slot 0's starting address.\n.ENDME                          ; End MemoryMap definition\n\n.ROMBANKSIZE flow.staticdata(rombanksize)      ;$8000 - Every ROM bank is 32 KBytes in size\n.ROMBANKS flow.staticdata(numberofbanks)       ;Insert the number of banks (romsizeInBytes/$8000)\n\n.SNESHEADER\n  ID flow.staticdata(shortname)   ; 1-4 letter shortname\n  \n  NAME flow.staticdata(longname)  ; Program Title - can't be over 21 bytes,\n\n  flow.staticdata(timing)         ; FASTROM/SLOWROM\n  flow.staticdata(adressing)      ; LOROM/HIROM\n\n  CARTRIDGETYPE flow.staticdata(carttype)   ; carttype from mapping document\n  ROMSIZE flow.staticdata(romsize)          ; romsize from mapping document\n  SRAMSIZE flow.staticdata(ramsize)         ; ramsize from mapping document\n  COUNTRY flow.staticdata(countrycode)      ; countrycode from mapping document\n  LICENSEECODE flow.staticdata(licensee)    ; licenseecode from mapping document\n  VERSION flow.staticdata(version)          ; $00 = 1.00, $01 = 1.1,... $FF = 1.255\n.ENDSNES\n\n.SNESNATIVEVECTOR               ; Define Native Mode interrupt vector table\n  COP EmptyHandler\n  BRK EmptyHandler\n  ABORT EmptyHandler\n  NMI VBlank\n  IRQ EmptyHandler\n.ENDNATIVEVECTOR\n\n.SNESEMUVECTOR                  ; Define Emulation Mode interrupt vector table\n  COP EmptyHandler\n  ABORT EmptyHandler\n  NMI EmptyHandler\n  RESET Start                   ; where execution starts\n  IRQBRK EmptyHandler\n.ENDEMUVECTOR\n\n.BANK 0 SLOT 0                  ; Defines the ROM bank and the slot it is inserted in memory.\n.ORG 0                          ; .ORG 0 is really $8000, because the slot starts at $8000\n.SECTION \"EmptyVectors\" SEMIFREE\n\nEmptyHandler:\n       rti\n\n.ENDS\n\n.EMPTYFILL $00                  ; fill unused areas with $00, opcode for BRK.  \n                                ; BRK will crash the snes if executed.\n";
			}
			byte[] bytes;
			if (stringToWrite == null)
			{
				bytes = "test".getBytes();
			}
			else
			{
				bytes = stringToWrite.getBytes();
			}
			if (filename.equals("project.meta"))
			{

				try
				{
					WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
					{
						@Override
						protected void execute(IProgressMonitor progressMonitor)
						{
							try
							{
								// Create a resource set
								ResourceSet resourceSet = new ResourceSetImpl();
								// Get the URI of the metadata file.
								URI fileURI = URI.createPlatformResourceURI(
										file.getFullPath().toString(), true);
								// Create a resource for this file.
								Resource resource = resourceSet
										.createResource(fileURI);

								// create Metadata object and add to resource
								// for saving
								MetadataImpl metadata = createMetadataObject();
								metadata.setGamename(gameName);
								metadata.setShortname(shortName);
								metadata.setTiming(timing);
								metadata.setRomSize(romsize);
								metadata.setAdressing(addressing);
								metadata.setRamSize(ramsize);
								metadata.setCountry(country);
								metadata.setLicensee(licensee);
								metadata.setCartridgeType(carttype);
								metadata.setVideoformat(video);
								metadata.setVersion(version);
								metadata.setIdeVersion(SnesdeGlobalConstants.snesdeVersion);
								if (metadata != null)
								{
									resource.getContents().add(metadata);
								}

								// Save the contents of the resource to the file
								// system.
								Map<Object, Object> options = new HashMap<Object, Object>();
								options.put(XMLResource.OPTION_ENCODING,
										"UTF-8");
								resource.save(options);
							}
							catch (Exception exception)
							{
								// TODO handle properly
								exception.printStackTrace();
							}
							finally
							{
								progressMonitor.done();
							}
						}
					};

					getContainer().run(false, false, operation);
					return;
				}
				catch (Exception exception)
				{
					// TODO: handle
					exception.printStackTrace();
					return;
				}
			}
			else
			{
				// create any other file without EMF
				InputStream source = new ByteArrayInputStream(bytes);
				file.create(source, IResource.NONE, null);
				file.refreshLocal(0, null);
			}
		}
	}

	@Override
	public void setInitializationData(final IConfigurationElement config,
			final String propertyName, final Object data) throws CoreException
	{
		this._configurationElement = config;
	}

	/**
	 * Create a new model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private MetadataImpl createMetadataObject()
	{
		EClass eClass = (EClass) projectmetaPackage.getEClassifier("Metadata");
		EObject rootObject = projectmetaFactory.create(eClass);
		return (MetadataImpl) rootObject;
	}

}
