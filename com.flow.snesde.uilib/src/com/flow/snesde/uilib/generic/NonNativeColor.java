package com.flow.snesde.uilib.generic;

public class NonNativeColor
{
	int r, g, b;

	public NonNativeColor(int r, int g, int b)
	{
		this.r = Math.abs(r) % 32;
		this.g = Math.abs(g) % 32;
		this.b = Math.abs(b) % 32;
	}

	public int getR()
	{
		return r;
	}

	public void setR(int r)
	{
		this.r = Math.abs(r % 32);
	}

	public int getG()
	{
		return g;
	}

	public void setG(int g)
	{
		this.g = Math.abs(g % 32);
	}

	public int getB()
	{
		return b;
	}

	public void setB(int b)
	{
		this.b = Math.abs(b % 32);
	}
}
