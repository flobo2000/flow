package com.flow.snesde.core.model.util;

import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import palette.Color;
import palette.Palette;
import palette.PaletteFactory;
import palette.PalettePackage;
import tileset.Encoding;
import tileset.Pixel;
import tileset.PixelColumn;
import tileset.Tile;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

/**
 * This tool transforms a given model into a String Vector of hexbytes for
 * further processing (preprocessor inclusion of resources into asm text for
 * instance or export of resources to the local filesystem)
 * 
 * @author flo
 * 
 */
public class ModelTransformationUtil
{
	/**
	 * Transforms a byte array into a model object
	 * 
	 * @param bytes
	 * @return
	 */
	public static EObject transformByteArrayToModel(byte[] bytes, EObject o)
	{
		if (o instanceof Palette)
		{
			Palette pal = (Palette) o;
			pal = transformBytesToPalette(bytes, pal);
		}
		else if (o instanceof Tileset)
		{
			Tileset tileset = (Tileset) o;
			tileset = transformBytesToTileset(bytes, tileset, 2);
		}
		return o;
	}

	/**
	 * Transforms a given byte array into the palette model passed
	 * 
	 * @param bytes
	 *            the bytes to be read
	 * @param pal
	 *            the palette model
	 * @return the palette model to be returned
	 */
	private static Palette transformBytesToPalette(byte[] bytes, Palette pal)
	{
		for (int i = 0; i < bytes.length; i = i + 2)
		{
			String byte0 = convertByteToHexstring(bytes[i]);
			String byte1 = convertByteToHexstring(bytes[i + 1]);

			Color c = getColorForHexbytes(byte0, byte1);

			pal.getColors().add(c);
		}
		return pal;
	}

	/**
	 * Transforms a given byte array into the tileset model passed
	 * 
	 * @param bytes
	 *            the bytes to read
	 * @param tileset
	 *            the tileset model
	 * @return the tileset model to be returned
	 */
	public static Tileset transformBytesToTileset(byte[] bytes,
			Tileset tileset, int colorDepth)
	{
		if (colorDepth == 2)
		{
			tileset.setEncoding(Encoding.getByName("Enc1bpp2colors"));
			for (int i = 0; i < bytes.length; i = i + 8)
			{
				byte[] b = new byte[8];
				System.arraycopy(bytes, i, b, 0, 8);
				Tile tile = getTileForHexbytes(b);
				tileset.getTiles().add(tile);
			}
		}
		else if (colorDepth == 4)
		{
			tileset.setEncoding(Encoding.getByName("Enc2bpp4colors"));
			for (int i = 0; i < bytes.length; i = i + 16)
			{
				byte[] b = new byte[16];
				System.arraycopy(bytes, i, b, 0, 16);
				Tile tile = getTileForHexbytes(b);
				tileset.getTiles().add(tile);
			}
		}
		else if (colorDepth == 8)
		{
			tileset.setEncoding(Encoding.getByName("Enc3bpp8colors"));
			for (int i = 0; i < bytes.length; i = i + 24)
			{
				byte[] b = new byte[24];
				System.arraycopy(bytes, i, b, 0, 24);
				Tile tile = getTileForHexbytes(b);
				tileset.getTiles().add(tile);
			}
		}
		else if (colorDepth == 16)
		{
			tileset.setEncoding(Encoding.ENC4BPP16COLORS);
			for (int i = 0; i < bytes.length; i = i + 32)
			{
				byte[] b = new byte[32];
				System.arraycopy(bytes, i, b, 0, 32);
				Tile tile = getTileForHexbytes(b);
				tileset.getTiles().add(tile);
			}
		}
		else if (colorDepth == 256)
		{
			tileset.setEncoding(Encoding.getByName("Enc8bpp256colors"));
			for (int i = 0; i < bytes.length; i = i + 64)
			{
				byte[] b = new byte[64];
				System.arraycopy(bytes, i, b, 0, 64);
				Tile tile = getTileForHexbytes(b);
				tileset.getTiles().add(tile);
			}
		}

		return tileset;
	}

	/**
	 * creates a Tile model object out of the bytes passed
	 * 
	 * @param bytes
	 *            the bytes (should be 8/16/24/32/64
	 * @return the Tile object created
	 */
	private static Tile getTileForHexbytes(byte[] bytes)
	{
		// Encodings:
		//
		//
		// 1bpp/2colors 1Tile=8bytes
		//
		// Hex Colors/Binary
		// x00 00000000
		// xFF 11111111
		// x0F 00001111 <-- bit value 1 directly correlates to color index 1
		// x33 00110011
		// x55 01010101
		// x00 00000000
		// xFF 11111111
		// x00 11111111
		//
		//
		//
		// 2bpp/4colors 1Tile=16bytes
		//
		// ..Hex...Colors........Binary
		// .b..a...........b........a
		// x00 00 00000000 00000000 00000000
		// xFF 00 11111111 11111111 00000000
		// x00 FF 22222222 00000000 11111111
		// xFF FF 33333333 11111111 11111111
		// x00 00 00000000 00000000 00000000
		// xFF 00 11111111 11111111 00000000
		// x00 FF 22222222 00000000 11111111
		// xFF FF 33333333 11111111 11111111
		// <-- 2nd Pixel of 2nd row = 01 = ab = color 1
		//
		//
		//
		//
		// 3bpp/8colors 1Tile=24bytes
		//
		// ..Hex..................Colors............Binary
		// .c..b..............a...........c........b........a
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// x55 33 ..14bytes.. 0F 01234567 01010101 00110011 00001111
		// <-- 2nd pixel of 2nd row = 001 = abc = color 1
		//
		//
		//
		//
		// 4bpp/16colors 1Tile=32bytes
		//
		// Hex...................... Colors................Binary
		// .d..c..............b..a...........d........c........b........a
		// x55 33 ..14bytes.. 0F 00 01234567 01010101 00110011 00001111 00000000
		// x55 33 ..14bytes.. 0F FF 89ABCDEF 01010101 00110011 00001111 11111111
		// x55 33 ..14bytes.. 0F 00 01234567 01010101 00110011 00001111 00000000
		// x55 33 ..14bytes.. 0F FF 89ABCDEF 01010101 00110011 00001111 11111111
		// x55 33 ..14bytes.. 0F 00 01234567 01010101 00110011 00001111 00000000
		// x55 33 ..14bytes.. 0F FF 89ABCDEF 01010101 00110011 00001111 11111111
		// x55 33 ..14bytes.. 0F 00 01234567 01010101 00110011 00001111 00000000
		// x55 33 ..14bytes.. 0F FF 89ABCDEF 01010101 00110011 00001111 11111111
		// <-- 4th pixel of 2nd row = 1011 = abcd = color B/11

		// create the tile object
		TilesetPackage tilesetPackage = TilesetPackage.eINSTANCE;
		TilesetFactory tilesetFactory = tilesetPackage.getTilesetFactory();
		EClass eClass = (EClass) tilesetPackage.getEClassifier("Tileset");
		EObject rootObject = tilesetFactory.create(eClass);
		Tile tile = tilesetFactory.createTile();
		// TODO: this should be inside the factory and not here
		for (int column = 0; column < 8; column++)
		{
			PixelColumn col = tilesetFactory.createPixelColumn();
			for (int row = 0; row < 8; row++)
			{
				Pixel pix = tilesetFactory.createPixel();
				col.getPixels().add(row, pix);
			}
			tile.getPixelColumns().add(column, col);
		}

		// fill with data
		if (bytes.length == 8)
		{
			// convert 1bpp/2colors
			for (int row = 0; row < bytes.length; row++)
			{
				byte b = bytes[row];
				for (int column = 0; column < 8; column++)
				{
					if (testBit(b, column))
					{
						PixelColumn pixelColumn = tile.getPixelColumns().get(
								column);
						Pixel pixel = pixelColumn.getPixels().get(row);
						pixel.setColorIndex(1);
					}
				}
			}
		}
		else if (bytes.length == 16)
		{
			// convert 2bpp/4colors
			int row = 0;
			for (int i = 0; i < bytes.length; i = i + 2)
			{
				byte a = bytes[i + 1];
				byte b = bytes[i];
				for (int column = 0; column < 8; column++)
				{
					int color = 0;
					if (testBit(a, column))
					{
						color = setBitInInteger(color, 6);
					}
					if (testBit(b, column))
					{
						color = setBitInInteger(color, 7);
					}
					PixelColumn pixelColumn = tile.getPixelColumns()
							.get(column);
					Pixel pixel = pixelColumn.getPixels().get(row);
					pixel.setColorIndex(color);
				}
				row++;
			}
		}
		else if (bytes.length == 24)
		{
			// convert 3bpp/8colors
			int row = 0;
			for (int i = 0; i < 16; i = i + 2)
			{
				byte a = bytes[i + 1 + 14 + 1 - row];
				byte b = bytes[i + 1];
				byte c = bytes[i];
				for (int column = 0; column < 8; column++)
				{
					int color = 0;
					if (testBit(a, column))
					{
						color = setBitInInteger(color, 5);
					}
					if (testBit(b, column))
					{
						color = setBitInInteger(color, 6);
					}
					if (testBit(c, column))
					{
						color = setBitInInteger(color, 7);
					}
					PixelColumn pixelColumn = tile.getPixelColumns()
							.get(column);
					Pixel pixel = pixelColumn.getPixels().get(row);
					pixel.setColorIndex(color);
				}
				row++;
			}
		}
		else if (bytes.length == 32)
		{
			// convert 4bpp/16colors
			int row = 0;
			for (int i = 0; i < 16; i = i + 2)
			{
				byte a = bytes[i + 1 + 14 + 2];
				byte b = bytes[i + 1 + 14 + 1];
				byte c = bytes[i + 1];
				byte d = bytes[i];
				//
				// System.out.println("d: "
				// + Integer.toBinaryString(Integer.parseInt(
				// convertByteToHexstring(d), 16)));
				// System.out.println("c: "
				// + Integer.toBinaryString(Integer.parseInt(
				// convertByteToHexstring(c), 16)));
				// System.out.println("b: "
				// + Integer.toBinaryString(Integer.parseInt(
				// convertByteToHexstring(b), 16)));
				// System.out.println("a: "
				// + Integer.toBinaryString(Integer.parseInt(
				// convertByteToHexstring(a), 16)));
				for (int column = 0; column < 8; column++)
				{
					int color = 0;
					if (testBit(a, column))
					{
						color = setBitInInteger(color, 4);
					}
					if (testBit(b, column))
					{
						color = setBitInInteger(color, 5);
					}
					if (testBit(c, column))
					{
						color = setBitInInteger(color, 6);
					}
					if (testBit(d, column))
					{
						color = setBitInInteger(color, 7);
					}
					PixelColumn pixelColumn = tile.getPixelColumns()
							.get(column);
					Pixel pixel = pixelColumn.getPixels().get(row);
					pixel.setColorIndex(color);
				}
				row++;
			}
		}
		else if (bytes.length == 64)
		{
			// convert 8bpp/256colors/mode7
			// TODO: implement
		}

		return tile;
	}

	/**
	 * creates an EMF Color object out of the two bytes passed
	 * 
	 * @param byte0
	 *            byte 0
	 * @param byte1
	 *            byte 1
	 * @return the Color object
	 */
	private static Color getColorForHexbytes(String byte1, String byte0)
	{
		PalettePackage palettePackage = PalettePackage.eINSTANCE;
		PaletteFactory paletteFactory = palettePackage.getPaletteFactory();
		EClass eClass = (EClass) palettePackage.getEClassifier("Palette");
		EObject rootObject = paletteFactory.create(eClass);
		Color newColor = paletteFactory.createColor();

		// convert like this: byte0: ?bbbbbgg byte1: gggrrrrr
		// make binary string out of bytes
		String binString0 = Integer.toBinaryString(Integer.parseInt(byte0, 16));
		String binString1 = Integer.toBinaryString(Integer.parseInt(byte1, 16));
		while (binString0.length() != 8)
		{
			binString0 = "0" + binString0;
		}
		while (binString1.length() != 8)
		{
			binString1 = "0" + binString1;
		}

		// create rgb binary strings out of byte binary strings
		String binaryR = binString1.substring(3);
		String binaryG = binString0.substring(6) + binString1.substring(0, 3);
		String binaryB = binString0.substring(1, 6);

		// create color out of that
		int r = Integer.parseInt(binaryR, 2);
		int g = Integer.parseInt(binaryG, 2);
		int b = Integer.parseInt(binaryB, 2);

		// assign & return
		newColor.setR(r);
		newColor.setG(g);
		newColor.setB(b);
		return newColor;
	}

	/**
	 * Transforms the given model into a byte array
	 * 
	 * @param model
	 *            the model to transform
	 * @return the byte array
	 */
	public static byte[] transformModelToByteArray(EObject model)
	{
		Vector<String> bytes = transformModelToByteStrings(model);
		byte[] output = new byte[bytes.size()];
		for (int i = 0; i < bytes.size(); i++)
		{
			output[i] = convertHexstringToByte(bytes.get(i));
		}
		return output;
	}

	/**
	 * Transforms a model into a byte string array
	 * 
	 * @param model
	 *            the model to transform
	 * @return the string Vector
	 */
	public static Vector<String> transformModelToByteStrings(EObject model)
	{
		Vector<String> bytes = null;

		if (model instanceof Palette)
		{
			Palette pal = (Palette) model;
			bytes = transformPalette(pal);
		}
		return bytes;
	}

	/**
	 * transforms a palette model to a byte string representation
	 * 
	 * @param pal
	 *            the palette model to transform
	 * @return the byte string
	 */
	private static Vector<String> transformPalette(Palette pal)
	{
		Vector<String> bytes = new Vector<String>();

		EList<Color> colors = pal.getColors();
		for (Color c : colors)
		{
			String[] twoByteStrings = getHexBytesFromColor(c);
			bytes.add(twoByteStrings[0]);
			bytes.add(twoByteStrings[1]);
		}

		return bytes;
	}

	/**
	 * converts a color object (with values 0..31 per color) to a String of the
	 * following format: $AA, $BB where $AA corresponds to %?bbbbbgg and $BB
	 * corresponds to %gggrrrrr
	 * 
	 * @param c
	 *            the color to convert
	 * @return the return string
	 */
	private static String[] getHexBytesFromColor(Color c)
	{
		// convert color values to binary strings
		String binaryR = Integer.toBinaryString(c.getR());
		String binaryG = Integer.toBinaryString(c.getG());
		String binaryB = Integer.toBinaryString(c.getB());
		// make sure the binary strings are of length 5 (that is prepend zeros)
		while (binaryR.length() != 5)
		{
			binaryR = "0" + binaryR;
		}
		while (binaryG.length() != 5)
		{
			binaryG = "0" + binaryG;
		}
		while (binaryB.length() != 5)
		{
			binaryB = "0" + binaryB;
		}

		// create resulting binary strings out of them
		String byteOne = "0" + binaryB + binaryG.substring(0, 2);
		String byteTwo = binaryG.substring(2, 5) + binaryR;

		// convert to hex strings
		String hexOne = Integer.toHexString(Integer.parseInt(byteOne, 2));
		String hexTwo = Integer.toHexString(Integer.parseInt(byteTwo, 2));
		if (hexOne.length() < 2)
		{
			hexOne = "0" + hexOne;
		}
		if (hexTwo.length() < 2)
		{
			hexTwo = "0" + hexTwo;
		}

		// for debugging
		// System.out.println("RGB:" + c.getR() + "|" + c.getG() + "|" +
		// c.getB()
		// + ", binaryRGB:" + binaryR + "_" + binaryG + "_" + binaryB
		// + ", binaryBytes: " + byteOne + "_" + byteTwo + ", heyBytes: "
		// + hexOne + "_" + hexTwo);

		return new String[] { hexTwo, hexOne };
	}

	// general purpose conversion methods

	/**
	 * converts a hexstring byte ("B5" for instance) to the corresponding byte
	 * 
	 * @param hexString
	 *            the hexstring to convert
	 * @return the byte
	 */
	private static byte convertHexstringToByte(String hexString)
	{
		if (hexString.length() > 2)
		{
			hexString = "00";
		}
		if (hexString.length() == 1)
		{
			hexString = "0" + hexString;
		}
		byte b = (byte) ((Character.digit(hexString.charAt(0), 16) << 4) + Character
				.digit(hexString.charAt(1), 16));
		return b;
	}

	/**
	 * converts a byte to a hexString
	 * 
	 * @param b
	 *            the byte to convert
	 * @return the hexstring
	 */
	private static String convertByteToHexstring(byte b)
	{
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[2];
		int v = b & 0xFF;
		hexChars[0] = hexArray[v >>> 4];
		hexChars[1] = hexArray[v & 0x0F];

		return new String(hexChars);
	}

	/**
	 * tests the byte at position x. returns true if set, false otherwise
	 * 
	 * @param toCheck
	 *            the byte to test
	 * @param bit
	 *            the bit to test (0..7) from most to least significant bit
	 * @return true if set, false otherwise
	 */
	private static boolean testBit(byte toCheck, int bit)
	{
		// bit must be 0..7
		int bitPosition = bit % 8;
		bitPosition = 7 - bitPosition;

		return (toCheck >> bitPosition & 1) == 1;
	}

	/**
	 * sets the bit at the position in the passed integer and returns it
	 * 
	 * @param toSetIn
	 *            the integer to modify
	 * @param position
	 *            the position (0..7) from most to least significant bit
	 * @return the integer with the bit set
	 */
	private static int setBitInInteger(int toSetIn, int position)
	{
		// bit must be 0..7
		position = position % 8;
		position = 7 - position;
		int setter = 1 << position;

		return (toSetIn | setter);
	}
}
