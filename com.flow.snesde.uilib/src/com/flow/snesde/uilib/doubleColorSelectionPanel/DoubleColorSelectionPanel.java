/**
 * 
 */
package com.flow.snesde.uilib.doubleColorSelectionPanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.generic.Uilib;
import com.flow.snesde.uilib.generic.UilibComposite;

/**
 * @author flo
 */
public class DoubleColorSelectionPanel extends UilibComposite
{

	private NonNativeColor[] colors = new NonNativeColor[256];

	private Integer leftSelected = 0;
	private Integer rightSelected = 1;

	/**
	 * @param parent
	 * @param style
	 */
	public DoubleColorSelectionPanel(final Composite parent, final int style)
	{
		super(parent, style);
		for (int i = 0; i < 256; i++)
		{
			colors[i] = new NonNativeColor(0, 0, 0);
		}
		colors[0].setR(31);
		colors[0].setR(31);
		colors[0].setR(31);
		colors[1].setR(31);
		colors[1].setR(31);
		colors[1].setR(31);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.flow.snesde.digui.DigUiComposite#drawContent(org.eclipse.swt.events
	 * .PaintEvent, int, int)
	 */
	@Override
	public void drawWidget(final PaintEvent e, final GC gc, final int width,
			final int height)
	{
		int max = width;
		if (height < width)
		{
			max = height;
		}
		int oneThird = (int) Math.floor(max / 3);

		// draw right color
		gc.setBackground(createColor(colors[rightSelected]));
		gc.setForeground(createColor(0, 0, 0));
		gc.fillRectangle(oneThird, oneThird, oneThird * 2 - 1, oneThird * 2 - 1);
		gc.drawRectangle(oneThird, oneThird, oneThird * 2 - 1, oneThird * 2 - 1);

		// draw shadow of left color
		HashMap<Integer, Color> greyShadows = new HashMap<Integer, Color>();
		Color bgShadow50 = Uilib.createDarkerColor(getBackground(), 50, this);
		Color bgShadow45 = Uilib.createDarkerColor(getBackground(), 45, this);
		Color bgShadow40 = Uilib.createDarkerColor(getBackground(), 40, this);
		Color bgShadow35 = Uilib.createDarkerColor(getBackground(), 35, this);
		Color bgShadow30 = Uilib.createDarkerColor(getBackground(), 30, this);
		Color bgShadow25 = Uilib.createDarkerColor(getBackground(), 25, this);
		Color bgShadow20 = Uilib.createDarkerColor(getBackground(), 20, this);
		Color bgShadow15 = Uilib.createDarkerColor(getBackground(), 15, this);
		Color bgShadow10 = Uilib.createDarkerColor(getBackground(), 9, this);
		Color bgShadow5 = Uilib.createDarkerColor(getBackground(), 3, this);
		greyShadows.put(10, bgShadow5);
		greyShadows.put(9, bgShadow10);
		greyShadows.put(8, bgShadow15);
		greyShadows.put(7, bgShadow20);
		greyShadows.put(6, bgShadow25);
		greyShadows.put(5, bgShadow30);
		greyShadows.put(4, bgShadow35);
		greyShadows.put(3, bgShadow40);
		greyShadows.put(2, bgShadow45);
		greyShadows.put(1, bgShadow50);
		HashMap<Integer, Color> rightShadows = new HashMap<Integer, Color>();
		Color rightShadow50 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 50, this);
		Color rightShadow45 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 45, this);
		Color rightShadow40 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 40, this);
		Color rightShadow35 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 35, this);
		Color rightShadow30 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 30, this);
		Color rightShadow25 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 25, this);
		Color rightShadow20 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 20, this);
		Color rightShadow15 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 15, this);
		Color rightShadow10 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 9, this);
		Color rightShadow5 = Uilib.createDarkerColor(
				createColor(colors[rightSelected]), 3, this);
		rightShadows.put(10, rightShadow5);
		rightShadows.put(9, rightShadow10);
		rightShadows.put(8, rightShadow15);
		rightShadows.put(7, rightShadow20);
		rightShadows.put(6, rightShadow25);
		rightShadows.put(5, rightShadow30);
		rightShadows.put(4, rightShadow35);
		rightShadows.put(3, rightShadow40);
		rightShadows.put(2, rightShadow45);
		rightShadows.put(1, rightShadow50);
		int i = 10;
		while (i > 0)
		{
			gc.setBackground(greyShadows.get(i));
			// grey part below left rectangle half
			gc.fillRectangle(20 - i, (oneThird * 2) + 1, oneThird - 20 + i, i);
			// grey part right of left rectangle upper half
			gc.fillRectangle((oneThird * 2) + 1, 20 - i, i, oneThird - 20 + i);
			gc.setBackground(rightShadows.get(i));
			// colored part below left rectangle half
			gc.fillRectangle(oneThird + 1, (oneThird * 2) + 1, oneThird + i, i);
			// colored part right of left rectangle lower half
			gc.fillRectangle((oneThird * 2) + 1, oneThird + 1, i, oneThird);
			i--;
		}

		// draw right color frame once more as grey shadow may overlap
		gc.setForeground(createColor(0, 0, 0));
		gc.drawRectangle(oneThird, oneThird, oneThird * 2 - 1, oneThird * 2 - 1);

		// draw left color
		gc.setBackground(createColor(colors[leftSelected]));
		gc.setForeground(createColor(0, 0, 0));
		gc.fillRectangle(0, 0, oneThird * 2, oneThird * 2);
		gc.drawRectangle(0, 0, oneThird * 2, oneThird * 2);

		// draw "L" character
		// half white border bg pixels
		Color mixedColor = Uilib.createMixedColor(
				createColor(colors[leftSelected]), createColor(255, 255, 255),
				50, this);
		gc.setForeground(mixedColor);
		gc.drawRectangle(2, (oneThird * 2) - (2 + 11), 3, 11);
		gc.drawRectangle(5, (oneThird * 2) - (2 + 3), 4, 3);
		// white bg
		gc.setForeground(createColor(255, 255, 255));
		gc.drawRectangle(3, (oneThird * 2) - (2 + 11), 1, 11);
		gc.drawRectangle(2, (oneThird * 2) - (2 + 10), 3, 9);
		gc.drawRectangle(5, (oneThird * 2) - (2 + 3), 3, 3);
		gc.drawRectangle(5, (oneThird * 2) - (2 + 2), 4, 1);
		// black font
		gc.setForeground(createColor(0, 0, 0));
		gc.drawRectangle(3, (oneThird * 2) - (3 + 9), 1, 9);
		gc.drawRectangle(5, (oneThird * 2) - (3 + 1), 3, 1);

		// draw "R" character
		// half white border bg pixels
		mixedColor = Uilib.createMixedColor(createColor(colors[rightSelected]),
				createColor(255, 255, 255), 50, this);
		gc.setForeground(mixedColor);
		gc.drawRectangle(oneThird + 2, (oneThird * 3) - (4 + 10), 5, 11);
		gc.drawRectangle(oneThird + 2, (oneThird * 3) - (4 + 9), 6, 10);
		gc.drawRectangle(oneThird + 2, (oneThird * 3) - (4 + 8), 7, 9);

		// white bg
		gc.setForeground(createColor(255, 255, 255));
		gc.drawRectangle(oneThird + 2, (oneThird * 3) - (4 + 9), 3, 9);
		gc.drawRectangle(oneThird + 6, (oneThird * 3) - (4 + 9), 1, 9);
		gc.drawRectangle(oneThird + 3, (oneThird * 3) - (4 + 10), 1, 11);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 10), 1, 10);
		gc.drawRectangle(oneThird + 7, (oneThird * 3) - (4 + 8), 1, 9);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 7), 4, 1);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 3), 4, 3);

		// black font
		gc.setForeground(createColor(0, 0, 0));
		gc.drawRectangle(oneThird + 3, (oneThird * 3) - (4 + 9), 1, 9);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 9), 1, 1);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 7), 3, 1);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 5), 1, 1);
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 8), 2, 4);
		gc.drawRectangle(oneThird + 7, (oneThird * 3) - (4 + 3), 1, 3);
		gc.setForeground(createColor(255, 255, 255));
		gc.drawRectangle(oneThird + 5, (oneThird * 3) - (4 + 7), 1, 1);
	}

	public NonNativeColor[] getColors()
	{
		return colors;
	}

	public void setColors(NonNativeColor[] colors)
	{
		this.colors = colors;
		redraw();
	}

	public Integer getLeftSelected()
	{
		return leftSelected;
	}

	public void setLeftSelected(Integer leftSelected)
	{
		this.leftSelected = leftSelected;
		redraw();
	}

	public Integer getRightSelected()
	{
		return rightSelected;
	}

	public void setRightSelected(Integer rightSelected)
	{
		this.rightSelected = rightSelected;
		redraw();
	}

	private <T extends Comparable<? super T>> List<T> asSortedList(
			Collection<T> c)
	{
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}
}
