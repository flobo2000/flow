package com.flow.snesde.preprocessor.tokens;

public class Tokens
{
	public static final int TOKEN_OPCODE = 0; //ADC, LDA, LDX, etc.
	public static final int TOKEN_FLOW = 1; // flow
	public static final int TOKEN_FLOW_DOT = 2; // flow.
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE = 3; //include staticdata
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP = 4; //include( staticdata(
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_STATICDATAID = 5; //romsize, ramsize, etc.
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS = 6; //script palette tileset etc.
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS_COMMA = 7; //script palette tileset etc.
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS_COMMA_SCRIPTNAMES = 8; //all script names
	public static final int TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS_COMMA_PALETTENAMES = 9; //all palette names
	public static final int TOKEN_FLOWDE_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS_COMMA_TILESETNAMES = 10; //all tileset names
	public static final int TOKEN_OPCODE_SPACE = 11; //opcode + space character
	public static final int TOKEN_OPCODE_SPACE_JUMPLABEL = 12; //all jump labels
	public static final int TOKEN_UNKNOWN = -1;
	
	//START TOKENS:
	
	//opcodes
	public static final String[] opcodes = { "adc", "asl", "bcc", "blt", "bcs",
			"bgw", "beq", "bit", "bmi", "bne", "bpl", "bra", "brk", "brl",
			"bvc", "bvs", "clc", "cld", "cli", "clv", "cmp", "cop", "cpx",
			"cpy", "dec", "dea", "dex", "dey", "eor", "inc", "ina", "inx",
			"iny", "jmp", "jml", "jsr", "jsl", "lda", "ldx", "ldy", "lsr",
			"mvn", "mvp", "nop", "ora", "pea", "pei", "per", "pha", "phb",
			"phd", "phy", "php", "phx", "phy", "pla", "plb", "pld", "plp",
			"plx", "ply", "rep", "rol", "ror", "rti", "rtl", "rts", "sbc",
			"sec", "sed", "sei", "sep", "sta", "stp", "stx", "sty", "stz",
			"tax", "tay", "tcd", "tcs", "tdc", "trb", "tsb", "tsc", "tsx",
			"txa", "txs", "txy", "tya", "tyx", "wai", "wdm", "xba", "xce" };
	
	//flow token
	public static final String[] flowToken = new String[] { "flow" };
	
	
	//FLOW TOKENS:
	
	//flow directives
	public static final String[] flowDirectives = new String[] { "include",
	"staticdata" };

	//static data IDs
	public static final String[] staticDataIds = new String[] { "rombanksize",
				"numberofbanks", "shortname", "longname", "timing", "adressing",
				"carttype", "romsize", "ramsize", "countrycode", "licensee",
				"version" };
	
	
	//flow resource classes
	public static final String[] flowResourceClasses = new String[] { "script",
				"palette", "tileset" };
}
