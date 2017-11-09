package com.flow.snesde.editors.scriptedit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.preprocessor.tokens.Tokens;

public class TaskCompletionProcessor implements IContentAssistProcessor
{
	private IDocument document;
	private int offset;
	private List<String> proposalStrings;
	private List<ICompletionProposal> completionProposals;
	
	// the project root to obtain resource names and jump labels from
	private SnesdeProjectRoot projectRoot = null;
	// the list of all possible resource names
	private String[] scriptNames = new String[1];
	private String[] paletteNames = new String[1];
	private String[] tilesetNames = new String[1];
	// the list fo all possible jump destinations (labels)
	private String[] jumpLabels = new String[1];
	private final IProject project;

	/**
	 * Constructor
	 * 
	 * @param project
	 *            the project for this editors content assistance
	 */
	public TaskCompletionProcessor(IProject project)
	{
		this.project = project;
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset)
	{
		// refresh resource names, jump labels
		getAllCurrentProjectData();

		// put into fields as we want to access them in other methods too
		this.document = viewer.getDocument();
		this.offset = offset;

		// initialize the list of completion proposals
		proposalStrings = new ArrayList<String>();
		completionProposals = new ArrayList<ICompletionProposal>();
		clearProposals();
		
		// get the last line of the text document before the caret 
		String lastLine = getLastLine();
		

		// next we need to know what token was last and what token is to be expected.
		// it may also be that we are already within the process of this token being
		// typed. In that case, we'll just want to narrow down the selection to the
		// ones which start accordingly.
		addProposalsForLastLine(lastLine);
		
		// creates the proposalset out of the string result set
		createPorposalSet();
		
		//in case there are no completion proposals -> return an empy array
		if (completionProposals.size() == 0)
		{
			return new ICompletionProposal[0];
		}
		//otherwise return the aggregated proposals
		return completionProposals.toArray(new ICompletionProposal[completionProposals.size()]);
	}

	/**
	 * add proposed next characters depending on the input in front of the caret
	 * (that is everything which is in the line before the cursor position)
	 * 
	 * @param lastLine the last line before the caret
	 */
	private void addProposalsForLastLine(String lastLine) 
	{
		//only remove leading whitespace
		String noLeadingWhitespace = lastLine.replaceAll("^\\s+", "");
		//remove all whitespace
		String noWhitespace = lastLine.replaceAll("\\s","");
		
		String lowercase = lastLine.toLowerCase();
		String lowercaseNoWhitespace = noWhitespace.toLowerCase();
		String lowercaseNoLeadingWhitespace = noLeadingWhitespace.toLowerCase();
		
		int startedTokenFromIndex = -1;
		int lastToken = -1;
		String restline = "";
		
		//find the tokens which are already within the line
		String opcode = startsWithOneOfThese(Tokens.opcodes, lowercaseNoLeadingWhitespace);
		String flow = startsWithOneOfThese(Tokens.flowToken, lowercaseNoLeadingWhitespace);
		String directive = null;
		String includeClass = null;
		if (opcode != null)
		{
			lastToken = Tokens.TOKEN_OPCODE;
			restline = lowercaseNoLeadingWhitespace.substring(3);
			if (restline.startsWith(" ") || restline.startsWith("\t"))
			{
				lastToken = Tokens.TOKEN_OPCODE_SPACE;
				System.out.println("TOKEN_OPCODE_SPACE");
				restline = restline.replaceAll("^\\s+", "");
			}
		}
		else if (flow != null)
		{
			lastToken = Tokens.TOKEN_FLOW;
			restline = lowercaseNoLeadingWhitespace.substring(4);
			if (restline.startsWith("."))
			{
				lastToken = Tokens.TOKEN_FLOW_DOT;
				restline = restline.substring(1);
			}
			directive = startsWithOneOfThese(Tokens.flowDirectives, restline);
			if (directive != null)
			{
				lastToken = Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE;
				restline = restline.substring(directive.length());
				if (restline.startsWith("("))
				{
					lastToken = Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP;
					restline = restline.substring(1);
				}
				includeClass = startsWithOneOfThese(Tokens.flowResourceClasses, restline);
				if (includeClass != null)
				{
					lastToken = Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS;
					restline = restline.substring(includeClass.length());
					if (restline.startsWith(","))
					{
						lastToken = Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS_COMMA;
						restline = restline.substring(1);
					}
				}
			}
		}
		else
		{
			lastToken = Tokens.TOKEN_UNKNOWN;
			restline = lowercase;
		}
		
		//check if the next token has been partly typed already
		String prefix = "";
		String postfix = "";
		if (lastToken == Tokens.TOKEN_OPCODE || lastToken == Tokens.TOKEN_OPCODE_SPACE)
		{
			if (lastToken == Tokens.TOKEN_OPCODE)
			{
				prefix = " ";
			}
			if (opcode.equals("jmp") || opcode.equals("jml") ||opcode.equals("jsr") ||opcode.equals("jsl") ||opcode.equals("bcc") ||opcode.equals("bcs") ||opcode.equals("beq"))
			{
				// opcode was a jump or branch opcode -> suggest jump labels
				addProposals(jumpLabels, prefix, postfix, restline);
			}
			//TODO: other opcodes -> add suggestions here as well
		}
		else if (lastToken == Tokens.TOKEN_FLOW || lastToken == Tokens.TOKEN_FLOW_DOT)
		{
			if (lastToken == Tokens.TOKEN_FLOW)
			{
				prefix = ".";
			}
			// suggest flow directives here
			addProposals(Tokens.flowDirectives, prefix, postfix, restline);
		}
		else if (lastToken == Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE || lastToken == Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP)
		{
			if (lastToken == Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE)
			{
				prefix = "(";
			}
			if (directive.equals("include"))
			{
				// suggest resource classes
				addProposals(Tokens.flowResourceClasses, prefix, postfix, restline);
			}
			else if (directive.equals("staticdata"))
			{
				// suggest static data IDs
				postfix = ");";
				addProposals(Tokens.staticDataIds, prefix, postfix, restline);
			}
		}
		else if (lastToken == Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS || lastToken == Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS_COMMA)
		{
			if (lastToken == Tokens.TOKEN_FLOW_DOT_FLOWDIRECTIVE_OP_FLOWRESOURCECLASS)
			{
				prefix = ",";
			}
			postfix = ");";
			
			if (includeClass.equals("script"))
			{
				// suggest script names
				addProposals(scriptNames, prefix, postfix, restline);
			}
			else if (includeClass.equals("palette"))
			{
				// suggest palette names
				addProposals(paletteNames, prefix, postfix, restline);
			}
			else if (includeClass.equals("tileset"))
			{
				// suggest tileset names
				addProposals(tilesetNames, prefix, postfix, restline);
			}
			//TODO: add more classes here as we progress
		}
		else if (lastToken == Tokens.TOKEN_UNKNOWN)
		{
			// suggest opcodes, flow, etc. if we don't know what's there
			addProposals(Tokens.flowToken, prefix, postfix, restline);
			addProposals(Tokens.opcodes, prefix, postfix, restline);
		}
	}

	/**
	 * searches for the items which may start with the String passed. returns the corresponding item if it matches. null otherwise 
	 * 
	 * @param items the items to search for
	 * @param searchFor the string to search in
	 * @return the item matching or null if none matched
	 */
	private String startsWithOneOfThese(String[] items, String searchFor) 
	{
		for (String item : items)
		{
			if (searchFor.startsWith(item.toLowerCase()))
			{
				return item;
			}
		}
		return null;
	}

	/**
	 * refresh the list of available resource names and labels
	 */
	private void getAllCurrentProjectData()
	{
		try
		{
			this.projectRoot = FlowWorkspace.getProjectRoot(project);
		}
		catch (CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scriptNames = projectRoot
				.getAllUniqueResourceNames(SnesdeProjectRoot.FILETYPE_SCRIPT);
		paletteNames = projectRoot
				.getAllUniqueResourceNames(SnesdeProjectRoot.FILETYPE_PALETTE);
		tilesetNames = projectRoot
				.getAllUniqueResourceNames(SnesdeProjectRoot.FILETYPE_TILESET);
		jumpLabels = projectRoot.getAllJumpLabels();
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset)
	{
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters()
	{
		return new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', '.', ' ', '(', ',', '\b', '\n', '\t' };
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters()
	{
		return null;
	}

	@Override
	public String getErrorMessage()
	{
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator()
	{
		return null;
	}
	
	/**
	 * Obtains the last line before the caret in the texteditor.
	 * 
	 * @return the last line as a string or null if none available
	 */
	private String getLastLine()
	{
		try 
		{
			int length = 1;
			boolean stop = false;
			boolean found = false;
			if (offset > 0)
			{
				while (!stop)
				{
					String character = document.get(offset-length, 1);
					if (character.equals("\n"))
					{
						stop = true;
						found = true;
					}
					else
					{
						length++;
					}
					if (offset-length < 0)
					{
						stop = true;
					}
				}
				//
				if(found)
				{
					length--;
					String lastLine = document.get(offset-length, length);
					return lastLine;
				}
				else
				{
					//no last line
					//System.out.println("No Lastline");
				}
			} 
		}
		catch (BadLocationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * adds the proposals passed
	 * 
	 * @param strings
	 *            the array of strings to be set
	 * @param prefix
	 *            the prefix to add before each string passed or null if none
	 * @param postfix
	 *            the postfix to be added after each string passed or null if
	 *            none
	 * @param existingStart
	 *            this variable holds the existing start of the current token.
	 *            This serves us so we can prefilter the list of strings (variable 1)
	 *            so we only show the ones which comply with this start
	 */
	private void addProposals(String[] strings, String prefix, String postfix, String existingStart)
	{
		if (prefix == null)
		{
			prefix = "";
		}
		if (postfix == null)
		{
			postfix = "";
		}
		for (String s : strings)
		{
			//only add the current string if it matches the existing start
			if (s.toLowerCase().startsWith(existingStart.toLowerCase()))
			{
				addProposal(prefix + s.substring(existingStart.length()) + postfix);
			}
//			addProposal(prefix + s + postfix);
		}
	}

	/**
	 * adds a proposal with the given string to the list of possible proposals
	 * 
	 * @param proposal
	 */
	private void addProposal(String proposal)
	{
		proposalStrings.add(proposal);
	}
	
	/**
	 * sorts the proposed strings and adds them to the proposal set
	 */
	private void createPorposalSet()
	{
		//sort the proposals
		Collections.sort(proposalStrings, String.CASE_INSENSITIVE_ORDER);
		//add them to the proposal set
		for (String s : proposalStrings)
		{
			completionProposals.add(new CompletionProposal(s, offset, 0, s.length()));			
		}
	}

	/**
	 * removes all proposals
	 */
	private void clearProposals()
	{
		while (!completionProposals.isEmpty())
		{
			completionProposals.remove(0);
		}
		while (!proposalStrings.isEmpty())
		{
			proposalStrings.remove(0);
		}
	}
}