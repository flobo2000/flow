/**
 * 
 */
package com.flow.snesde.preprocessor.metadatamapping;

/**
 * This keyvaluemapping maps licensee names to integers (0..255). The following
 * mappings are supported: 0=Other, 1=Nintendo, 2=Ajinomoto, 3=Zoom, 4=Chris
 * Gray Enterprises Inc., 5=Zamuse, 6=Falcom, 8=Capcom, 9=HOT-B, 16=Banalex,
 * 17=Starfish, 18=Gremlin Graphics, 19=Electronic Arts, 20=Masaya, 21=COBRA
 * Team, 22=Field, 23=KOEI, 24=Hudson Soft, 25=Game Village, 32=KSS, 33=Sunsoft,
 * 34=POW, 35=Micro World, 38=Enix, 39=Electro Brain, 40=Kemco, 41=Seta Co.
 * Ltd., 48=Viacom New Media, 49=Carrozzeria, 50=Dynamic, 52=Magifact, 53=Hect,
 * 57=Accolade Europe Inc., 64=Seika Corp., 65=UBI Soft, 68=LifeFitness
 * Exertainment, 70=System 3, 71=Spectrum Holobyte, 80=Absolute Entertainment,
 * 81=Acclaim, 82=Activision, 83=American Sammy, 84=GameTek, 85=Hi Tech
 * Expressions, 86=LJN Toys, 96=Titus, 98=Maxis, 99=Origin, 103=Ocean,
 * 112=Infogrames, 113=Interplay, 114=LucasArts, 115=Parker Brothers,
 * 116=Konami, 117=STORM, 120=THQ Software, 128=Misawa, 129=Teichio, 130=Namco
 * Ltd., 131=Lozc, 134=Tokuma Shoten Intermedia, 135=Tsukuda Original,
 * 136=DATAM-Polystar, 144=Takara, 145=Chun Software, 146=Video System, 147=BEC,
 * 149=Varie, 150=S'Pal Inc., 151=Kaneco, 153=Pack in Video, 10=Jaleco,
 * 11=Coconuts, 12=Rage Software, 13=Micronet, 14=Technos, 15=Mebio Software,
 * 26=Yanoman, 28=Tecmo, 30=Open System, 31=Virgin Interactive Entertainment,
 * 42=Culture Brain, 43=Irem Japan, 44=Pal Soft, 45=Visit Co. Ltd., 46=INTEC
 * Inc., 47=System Sacom Corp., 59=Arcade Zone, 60=Empire Software, 61=Loriciel,
 * 75=Sculptured Software, 76=Renovation Products, 77=Black Pearl, 79=U.S. Gold,
 * 90=Mindscape, 91=Romstar, 93=Tradewest, 95=American Softworks Corp.,
 * 107=Laser Beam, 110=Elite, 122=Triffix Entertainment, 124=Microprose,
 * 139=Bullet-Proof Software, 140=Tokai Engineering, 142=Character Soft,
 * 143=I'Max, 154=Nichibutsu, 156=Imagineer, 160=Wolf Team, 161=Hori, 165=K.
 * Amusement, 170=JVC, 172=Toei Animation, 173=Toho, 177=ASCII, 178=BanDai
 * America, 182=Halken, 188=Toshiba EMI System Vision, 189=Imagesoft, 192=Taito,
 * 195=Squaresoft, 196=NHK, 197=Data East, 198=Tonkin House, 205=Meldac KAZe,
 * 206=Pony Canyon, 207=Sotsu Agency, 209=Sofel, 210=Quest, 211=Sigma, 212=Ask
 * Kodansha, 214=Naxat, 217=Banpresto, 218=Tomy, 219=Hiro, 221=NCS, 222=Human
 * Entertainment, 223=Ringler Studios, 228=T&E Software, 229=Epoch, 231=Athena,
 * 232=Asmik, 233=Natsume, 234=King A Wave, 235=Atlus, 236=Sony, 238=Psygnosis
 * Ltd., 243=Beam Software, 244=Tec Magik, 255=Digisalt
 * 
 * @author flo
 * 
 */
public class LicenseeMapping extends KeyValueMappingIntToString
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.digisalt.dsnesds.core.wizards.create.project.mappings.KeyValueMapping
	 * #initKeys()
	 */
	@Override
	protected Integer[] initKeys()
	{
		return new Integer[]
		{ 0, 1, 2, 3, 4, 5, 6, 8, 9, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 32,
				33, 34, 35, 38, 39, 40, 41, 48, 49, 50, 52, 53, 57, 64, 65, 68, 70, 71,
				80, 81, 82, 83, 84, 85, 86, 96, 98, 99, 103, 112, 113, 114, 115, 116,
				117, 120, 128, 129, 130, 131, 134, 135, 136, 144, 145, 146, 147, 149,
				150, 151, 153, 10, 11, 12, 13, 14, 15, 26, 28, 30, 31, 42, 43, 44, 45,
				46, 47, 59, 60, 61, 75, 76, 77, 79, 90, 91, 93, 95, 107, 110, 122, 124,
				139, 140, 142, 143, 154, 156, 160, 161, 165, 170, 172, 173, 177, 178,
				182, 188, 189, 192, 195, 196, 197, 198, 205, 206, 207, 209, 210, 211,
				212, 214, 217, 218, 219, 221, 222, 223, 228, 229, 231, 232, 233, 234,
				235, 236, 238, 243, 244, 255 };
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.digisalt.dsnesds.core.wizards.create.project.mappings.KeyValueMapping
	 * #initValues()
	 */
	@Override
	protected String[] initValues()
	{
		return new String[]
		{ "Other", "Nintendo", "Ajinomoto", "Zoom", "Chris Gray Enterprises Inc.",
				"Zamuse", "Falcom", "Capcom", "HOT-B", "Banalex", "Starfish",
				"Gremlin Graphics", "Electronic Arts", "Masaya", "COBRA Team", "Field",
				"KOEI", "Hudson Soft", "Game Village", "KSS", "Sunsoft", "POW",
				"Micro World", "Enix", "Electro Brain", "Kemco", "Seta Co. Ltd.",
				"Viacom New Media", "Carrozzeria", "Dynamic", "Magifact", "Hect",
				"Accolade Europe Inc.", "Seika Corp.", "UBI Soft",
				"LifeFitness Exertainment", "System 3", "Spectrum Holobyte",
				"Absolute Entertainment", "Acclaim", "Activision", "American Sammy",
				"GameTek", "Hi Tech Expressions", "LJN Toys", "Titus", "Maxis",
				"Origin", "Ocean", "Infogrames", "Interplay", "LucasArts",
				"Parker Brothers", "Konami", "STORM", "THQ Software", "Misawa",
				"Teichio", "Namco Ltd.", "Lozc", "Tokuma Shoten Intermedia",
				"Tsukuda Original", "DATAM-Polystar", "Takara", "Chun Software",
				"Video System", "BEC", "Varie", "S\'Pal Inc.", "Kaneco",
				"Pack in Video", "Jaleco", "Coconuts", "Rage Software", "Micronet",
				"Technos", "Mebio Software", "Yanoman", "Tecmo", "Open System",
				"Virgin Interactive Entertainment", "Culture Brain", "Irem Japan",
				"Pal Soft", "Visit Co. Ltd.", "INTEC Inc.", "System Sacom Corp.",
				"Arcade Zone", "Empire Software", "Loriciel", "Sculptured Software",
				"Renovation Products", "Black Pearl", "U.S. Gold", "Mindscape",
				"Romstar", "Tradewest", "American Softworks Corp.", "Laser Beam",
				"Elite", "Triffix Entertainment", "Microprose",
				"Bullet-Proof Software", "Tokai Engineering", "Character Soft",
				"I\'Max", "Nichibutsu", "Imagineer", "Wolf Team", "Hori",
				"K. Amusement", "JVC", "Toei Animation", "Toho", "ASCII",
				"BanDai America", "Halken", "Toshiba EMI System Vision", "Imagesoft",
				"Taito", "Squaresoft", "NHK", "Data East", "Tonkin House",
				"Meldac KAZe", "Pony Canyon", "Sotsu Agency", "Sofel", "Quest",
				"Sigma", "Ask Kodansha", "Naxat", "Banpresto", "Tomy", "Hiro", "NCS",
				"Human Entertainment", "Ringler Studios", "T&E Software", "Epoch",
				"Athena", "Asmik", "Natsume", "King A Wave", "Atlus", "Sony",
				"Psygnosis Ltd.", "Beam Software", "Tec Magik", "Digisalt" };
	}
	
}
