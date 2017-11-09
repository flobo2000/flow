package com.flow.snesde.editors.scriptedit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.PatternRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.flow.snesde.preprocessor.tokens.Tokens;

public class ScriptEditorSourceViewerConfiguration extends
		SourceViewerConfiguration
{
	/**
	 * used so we can create system native resources (colors, fonts) without
	 * running into memory leaks
	 */
	private ResourceManager resManager;

	/**
	 * the content assistant for the source editor
	 */
	private final ContentAssistant contentAssistant;

	/**
	 * constructor
	 */
	public ScriptEditorSourceViewerConfiguration(IProject project)
	{
		// Initialize ContentAssistant
		contentAssistant = new ContentAssistant();

		// define a default ContentAssistProcessor
		TaskCompletionProcessor processor = new TaskCompletionProcessor(project);
		contentAssistant.setContentAssistProcessor(processor,
				IDocument.DEFAULT_CONTENT_TYPE);

		// enable auto activation
		contentAssistant.enableAutoActivation(true);
		
		contentAssistant.enableColoredLabels(true);
		contentAssistant.setAutoActivationDelay(0);
		// set a proper orientation for the content assist proposal
		contentAssistant
				.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
	{
		contentAssistant
				.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return contentAssistant;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer viewer)
	{
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dflt = new DefaultDamagerRepairer(
				createTokenScanner());
		reconciler.setDamager(dflt, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dflt, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;

	}

	/**
	 * creates the tokenscanner for syntax highlightening
	 * 
	 * @return the token scanner
	 */
	private ITokenScanner createTokenScanner()
	{
		RuleBasedScanner scanner = new RuleBasedScanner();
		scanner.setRules(createRules());
		return scanner;
	}

	/**
	 * creates the rules for the tokenscanner which will cause syntax
	 * highlightening to happen
	 */
	private IRule[] createRules()
	{
		List<IRule> rules = new ArrayList<IRule>();

		// default word token without any special highlightening
		IToken defaultWord = new Token(new TextAttribute(createColor(0, 0, 0)));

		// comments -> green text
		IToken commentToken = new Token(new TextAttribute(
				createColor(0, 155, 0)));
		// rules
		rules.add(new EndOfLineRule(";", commentToken));
		rules.add(new PatternRule("/\\*", "\\*/", commentToken, '\\', true,
				true));

		// opcodes -> dark pink + bold
		IToken opcodeToken = new Token(new TextAttribute(
				createColor(50, 0, 100), createColor(255, 255, 255), SWT.BOLD));
		WordRule rule = createDefaultWordRule(defaultWord);
		for (int i = 0; i < Tokens.opcodes.length; i++)
		{
			rule.addWord(Tokens.opcodes[i], opcodeToken);
		}
		rules.add(rule);

		// flow - directive -> ??
		IToken flowDirective = new Token(new TextAttribute(createColor(219,
				0, 50), createColor(255, 255, 255), SWT.ITALIC));
		// rules
		rule.addWord("flow.include", flowDirective);
		rule.addWord("flow.staticdata", flowDirective);

		// directive classes -> dark pink + bold
		IToken directClass = new Token(new TextAttribute(
				createColor(50, 0, 100), createColor(255, 255, 255), SWT.BOLD));
		rule.addWord("rombanksize", directClass);
		rule.addWord("numberofbanks", directClass);
		rule.addWord("shortname", directClass);
		rule.addWord("longname", directClass);
		rule.addWord("timing", directClass);
		rule.addWord("adressing", directClass);
		rule.addWord("carttype", directClass);
		rule.addWord("romsize", directClass);
		rule.addWord("ramsize", directClass);
		rule.addWord("countrycode", directClass);
		rule.addWord("licensee", directClass);
		rule.addWord("version", directClass);
		rule.addWord("script", directClass);
		rule.addWord("palette", directClass);
		rule.addWord("tileset", directClass);

		// control statements (if, then, else etc.) -> dark red text
		IToken controlStatement = new Token(new TextAttribute(createColor(219,
				0, 0), createColor(255, 255, 255), SWT.BOLD));
		// rules
		rule.addWord("if", controlStatement);
		rule.addWord("then", controlStatement);
		rule.addWord("else", controlStatement);
		rule.addWord("endif", controlStatement);
		rule.addWord("for", controlStatement);
		rule.addWord("endfor", controlStatement);
		rules.add(rule);

		// numbers -> pink text
		IToken numberToken = new Token(new TextAttribute(createColor(200, 0,
				200)));
		// rules
		rule = createHexNumberWordRule(numberToken);
		rules.add(rule);
		rule = createDecimalNumberWordRule(numberToken);
		rules.add(rule);
		rule = createBinaryNumberWordRule(numberToken);
		rules.add(rule);

		// register hashsign/other register shortcuts -> red fat text
		IToken registerToken = new Token(new TextAttribute(createColor(255, 0,
				0), createColor(255, 255, 255), SWT.BOLD | SWT.ITALIC));
		// rules
		rule = createRegisterWordRule(registerToken);
		rules.add(rule);

		// strings -> blue text
		IToken stringToken = new Token(
				new TextAttribute(createColor(0, 0, 255)));
		// rules
		rules.add(new PatternRule("\"", "\"", stringToken, '\\', true));

		// label -> ???
		IToken labelToken = new Token(new TextAttribute(createColor(0, 0, 255),
				createColor(255, 255, 255), SWT.BOLD));
		// TODO rules

		// makro -> ??
		IToken makroToken = new Token(new TextAttribute(createColor(0, 0, 255),
				createColor(255, 255, 255), SWT.BOLD));
		// rules
		// rules.add(new EndOfLineRule(".", makroToken));

		// demo from IBM presentation
		// https://www.eclipse.org/eclipse/platform-text/eclipseCon/talk.pdf
		//
		// IToken tokenA = new Token(new TextAttribute(new Color(null, 0, 0,
		// 255)));
		// IToken tokenB = new Token(new TextAttribute(new Color(null, 190, 190,
		// 190)));
		// return new IRule[] { new PatternRule(">", "<", tokenA, '\\', false),
		// new EndOfLineRule("-- ", tokenB) };

		IRule[] ruleArray = new IRule[rules.size()];
		rules.toArray(ruleArray);
		return ruleArray;
	}

	/**
	 * creates a word detection rule which then can be fed with
	 * constantRule.addWord("MYWORDTORECOGNIZE", tokenToAssociateWith); to
	 * generate tokens
	 * 
	 * @param defaultToken
	 * 
	 * @return the constant word rule
	 */
	private WordRule createDefaultWordRule(IToken defaultToken)
	{
		WordRule constantRule = new WordRule(new IWordDetector()
		{

			@Override
			public boolean isWordStart(char c)
			{
				return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '.');
			}

			@Override
			public boolean isWordPart(char c)
			{
				return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '.');
			}
		}, defaultToken, true);

		return constantRule;
	}

	/**
	 * creates a word detection rule which then can be fed with
	 * constantRule.addWord("MYWORDTORECOGNIZE", tokenToAssociateWith); to
	 * generate tokens
	 * 
	 * @param defaultToken
	 * 
	 * @return the constant word rule
	 */
	private WordRule createHexNumberWordRule(IToken defaultToken)
	{
		WordRule constantRule = new WordRule(new IWordDetector()
		{

			@Override
			public boolean isWordStart(char c)
			{
				return (c == '$');
			}

			@Override
			public boolean isWordPart(char c)
			{
				return ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f'));
			}
		}, defaultToken, true);

		return constantRule;
	}

	/**
	 * creates a word detection rule which then can be fed with
	 * constantRule.addWord("MYWORDTORECOGNIZE", tokenToAssociateWith); to
	 * generate tokens
	 * 
	 * @param defaultToken
	 * 
	 * @return the constant word rule
	 */
	private WordRule createDecimalNumberWordRule(IToken defaultToken)
	{
		WordRule constantRule = new WordRule(new IWordDetector()
		{

			@Override
			public boolean isWordStart(char c)
			{
				return (c >= '0' && c <= '9');
			}

			@Override
			public boolean isWordPart(char c)
			{
				return (c >= '0' && c <= '9');
			}
		}, defaultToken, true);

		return constantRule;
	}

	/**
	 * creates a word detection rule which then can be fed with
	 * constantRule.addWord("MYWORDTORECOGNIZE", tokenToAssociateWith); to
	 * generate tokens
	 * 
	 * @param defaultToken
	 * 
	 * @return the constant word rule
	 */
	private WordRule createBinaryNumberWordRule(IToken defaultToken)
	{
		WordRule constantRule = new WordRule(new IWordDetector()
		{

			@Override
			public boolean isWordStart(char c)
			{
				return (c == '%');
			}

			@Override
			public boolean isWordPart(char c)
			{
				return (c >= '0' && c <= '1');
			}
		}, defaultToken, true);

		return constantRule;
	}

	/**
	 * creates a word detection rule which then can be fed with
	 * constantRule.addWord("MYWORDTORECOGNIZE", tokenToAssociateWith); to
	 * generate tokens
	 * 
	 * @param defaultToken
	 * 
	 * @return the constant word rule
	 */
	private WordRule createRegisterWordRule(IToken defaultToken)
	{
		WordRule constantRule = new WordRule(new IWordDetector()
		{

			@Override
			public boolean isWordStart(char c)
			{
				return (c == '#' || c == 'A' || c == 'X' || c == 'Y'
						|| c == 'P' || c == 'S');
			}

			@Override
			public boolean isWordPart(char c)
			{
				return false;
			}
		}, defaultToken, false);

		return constantRule;
	}

	/**
	 * returns (and creates if needed) the resourcemanager to create colors,
	 * fonts, images which need to be disposed as they correlate to native
	 * resources
	 * 
	 * @return the resourcemanager
	 */
	private ResourceManager getResourceManager()
	{
		if (resManager == null)
		{
			IWorkbench wb = PlatformUI.getWorkbench();
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			resManager = new LocalResourceManager(
					JFaceResources.getResources(), win.getShell());
		}
		return resManager;
	}

	/**
	 * This method is used to create new SWT colors for this widget. They're
	 * disposed automatically once the widget is disposed.
	 * 
	 * @param r
	 *            the red value (0-255)
	 * @param g
	 *            the green value (0-255)
	 * @param b
	 *            the blue value (0-255)
	 * @return the SWT color object created
	 */
	public Color createColor(final int r, final int g, final int b)
	{
		ResourceManager man = getResourceManager();
		return man.createColor(new RGB(r, g, b));
	}

	/**
	 * creates a font object for this part
	 * 
	 * @param name
	 *            name of the font (eg. Arial)
	 * @param height
	 *            height of the font (eg. 10)
	 * @param style
	 *            the SWT style integer (eg. SWT.BOLD | SWT.ITALIC)
	 * @return the font object
	 */
	public Font createFont(final String name, final int height, final int style)
	{
		ResourceManager man = getResourceManager();
		return man.createFont(FontDescriptor.createFrom(name, height, style));
	}
}
