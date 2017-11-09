package com.flow.snesde.uilib.tilesetPanel;

import java.util.HashMap;

public class TilemaskFactory
{
	private static String round8 = "  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  ";

	private static String round7 = "   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String round6 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String round5 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String round4 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String round3 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String round2 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String round1 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta8 = "xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx";

	private static String recta7 = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta6 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta5 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta4 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta3 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta2 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String recta1 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static Tilemask round8t = new Tilemask(round8);
	private static Tilemask round7t = new Tilemask(round7);
	private static Tilemask round6t = new Tilemask(round6);
	private static Tilemask round5t = new Tilemask(round5);
	private static Tilemask round4t = new Tilemask(round4);
	private static Tilemask round3t = new Tilemask(round3);
	private static Tilemask round2t = new Tilemask(round2);
	private static Tilemask round1t = new Tilemask(round1);
	private static Tilemask recta8t = new Tilemask(recta8);
	private static Tilemask recta7t = new Tilemask(recta7);
	private static Tilemask recta6t = new Tilemask(recta6);
	private static Tilemask recta5t = new Tilemask(recta5);
	private static Tilemask recta4t = new Tilemask(recta4);
	private static Tilemask recta3t = new Tilemask(recta3);
	private static Tilemask recta2t = new Tilemask(recta2);
	private static Tilemask recta1t = new Tilemask(recta1);

	private static String rount8 = "  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  ";

	private static String rount7 = "   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rount6 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rount5 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rount4 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x--x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x--x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rount3 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   x-x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rount2 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rount1 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt8 = "xxxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"x------x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"xxxxxxxx";

	private static String rectt7 = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x-----x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt6 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" x----x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt5 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x---x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt4 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x--x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  x--x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt3 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   x-x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt2 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static String rectt1 = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaaaa */"        ";

	private static Tilemask rount8t = new Tilemask(rount8);
	private static Tilemask rount7t = new Tilemask(rount7);
	private static Tilemask rount6t = new Tilemask(rount6);
	private static Tilemask rount5t = new Tilemask(rount5);
	private static Tilemask rount4t = new Tilemask(rount4);
	private static Tilemask rount3t = new Tilemask(rount3);
	private static Tilemask rount2t = new Tilemask(rount2);
	private static Tilemask rount1t = new Tilemask(rount1);
	private static Tilemask rectt8t = new Tilemask(rectt8);
	private static Tilemask rectt7t = new Tilemask(rectt7);
	private static Tilemask rectt6t = new Tilemask(rectt6);
	private static Tilemask rectt5t = new Tilemask(rectt5);
	private static Tilemask rectt4t = new Tilemask(rectt4);
	private static Tilemask rectt3t = new Tilemask(rectt3);
	private static Tilemask rectt2t = new Tilemask(rectt2);
	private static Tilemask rectt1t = new Tilemask(rectt1);

	// characters for writing tool

	private static String A = "   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String B = " xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String C = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String D = " xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String E = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String F = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String G = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx  xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String H = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String I = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String J = " xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" x   xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxx  ";

	private static String K = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String L = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String M = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxx  xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String N = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxx   x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxx  x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx  xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String O = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String P = " xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String Q = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx  x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String R = " xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String S = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String T = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String U = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String V = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String W = " xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxx  xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String X = " xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String Y = " xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String Z = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"    xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String Ä = "  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xx  x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String Ö = "  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static String Ü = "  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddsss */"        ";

	private static Tilemask At = new Tilemask(A);
	private static Tilemask Bt = new Tilemask(B);
	private static Tilemask Ct = new Tilemask(C);
	private static Tilemask Dt = new Tilemask(D);
	private static Tilemask Et = new Tilemask(E);
	private static Tilemask Ft = new Tilemask(F);
	private static Tilemask Gt = new Tilemask(G);
	private static Tilemask Ht = new Tilemask(H);
	private static Tilemask It = new Tilemask(I);
	private static Tilemask Jt = new Tilemask(J);
	private static Tilemask Kt = new Tilemask(K);
	private static Tilemask Lt = new Tilemask(L);
	private static Tilemask Mt = new Tilemask(M);
	private static Tilemask Nt = new Tilemask(N);
	private static Tilemask Ot = new Tilemask(O);
	private static Tilemask Pt = new Tilemask(P);
	private static Tilemask Qt = new Tilemask(Q);
	private static Tilemask Rt = new Tilemask(R);
	private static Tilemask St = new Tilemask(S);
	private static Tilemask Tt = new Tilemask(T);
	private static Tilemask Ut = new Tilemask(U);
	private static Tilemask Vt = new Tilemask(V);
	private static Tilemask Wt = new Tilemask(W);
	private static Tilemask Xt = new Tilemask(X);
	private static Tilemask Yt = new Tilemask(Y);
	private static Tilemask Zt = new Tilemask(Z);
	private static Tilemask Ät = new Tilemask(Ä);
	private static Tilemask Öt = new Tilemask(Ö);
	private static Tilemask Üt = new Tilemask(Ü);

	private static String a = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String b = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String c = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String d = "      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssv */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String e = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx    x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String f = "  xxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String g = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx ";

	private static String h = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String i = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String j = "     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx  xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxx  ";

	private static String k = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx  xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx  xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String l = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String m = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String n = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String o = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String p = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     ";

	private static String q = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"      xx";

	private static String r = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String s = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String t = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String u = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String v = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String w = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxx xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String x = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String y = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxx ";

	private static String z = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String ä = "  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String ö = "  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String ü = "  xx xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */"        ";

	private static String ß = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx xxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssa */" xx     ";

	private static Tilemask at = new Tilemask(a);
	private static Tilemask bt = new Tilemask(b);
	private static Tilemask ct = new Tilemask(c);
	private static Tilemask dt = new Tilemask(d);
	private static Tilemask et = new Tilemask(e);
	private static Tilemask ft = new Tilemask(f);
	private static Tilemask gt = new Tilemask(g);
	private static Tilemask ht = new Tilemask(h);
	private static Tilemask it = new Tilemask(i);
	private static Tilemask jt = new Tilemask(j);
	private static Tilemask kt = new Tilemask(k);
	private static Tilemask lt = new Tilemask(l);
	private static Tilemask mt = new Tilemask(m);
	private static Tilemask nt = new Tilemask(n);
	private static Tilemask ot = new Tilemask(o);
	private static Tilemask pt = new Tilemask(p);
	private static Tilemask qt = new Tilemask(q);
	private static Tilemask rt = new Tilemask(r);
	private static Tilemask st = new Tilemask(s);
	private static Tilemask tt = new Tilemask(t);
	private static Tilemask ut = new Tilemask(u);
	private static Tilemask vt = new Tilemask(v);
	private static Tilemask wt = new Tilemask(w);
	private static Tilemask xt = new Tilemask(x);
	private static Tilemask yt = new Tilemask(y);
	private static Tilemask zt = new Tilemask(z);
	private static Tilemask ät = new Tilemask(ä);
	private static Tilemask öt = new Tilemask(ö);
	private static Tilemask üt = new Tilemask(ü);
	private static Tilemask ßt = new Tilemask(ß);

	private static String n0 = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx  xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx xxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx x xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxxx xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxx  xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n1 = "   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n2 = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"   xxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n3 = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"    xxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n4 = "  xx  xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx  xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n5 = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n6 = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n7 = " xxxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"    xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n8 = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static String n9 = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */" xx   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaa */"        ";

	private static Tilemask n0t = new Tilemask(n0);
	private static Tilemask n1t = new Tilemask(n1);
	private static Tilemask n2t = new Tilemask(n2);
	private static Tilemask n3t = new Tilemask(n3);
	private static Tilemask n4t = new Tilemask(n4);
	private static Tilemask n5t = new Tilemask(n5);
	private static Tilemask n6t = new Tilemask(n6);
	private static Tilemask n7t = new Tilemask(n7);
	private static Tilemask n8t = new Tilemask(n8);
	private static Tilemask n9t = new Tilemask(n9);

	private static String ddot = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask ddott = new Tilemask(ddot);

	private static String coma = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     ";
	private static Tilemask comat = new Tilemask(coma);

	private static String sdot = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask sdott = new Tilemask(sdot);

	private static String spce = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask spcet = new Tilemask(spce);

	private static String scol = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     ";
	private static Tilemask scolt = new Tilemask(scol);

	private static String minu = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask minut = new Tilemask(minu);

	private static String plus = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask plust = new Tilemask(plus);

	private static String mltp = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x xx x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x xx x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask mltpt = new Tilemask(mltp);

	private static String hash = "  x x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask hasht = new Tilemask(hash);

	private static String udsr = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxxxx";
	private static Tilemask udsrt = new Tilemask(udsr);

	private static String tlde = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx xx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask tldet = new Tilemask(tlde);

	private static String qtnm = "  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x    xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"    xxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask qtnmt = new Tilemask(qtnm);

	private static String qtsp = "   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"xx      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"xx    x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask qtspt = new Tilemask(qtsp);

	private static String xklm = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xklmt = new Tilemask(xklm);

	private static String xksp = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xkspt = new Tilemask(xksp);

	private static String fwsl = "   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask fwslt = new Tilemask(fwsl);

	private static String bwsl = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask bwslt = new Tilemask(bwsl);

	private static String oppt = "   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask opptt = new Tilemask(oppt);

	private static String clpt = " x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask clptt = new Tilemask(clpt);

	private static String opbc = " xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask opbct = new Tilemask(opbc);

	private static String clbc = " xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask clbct = new Tilemask(clbc);

	private static String opbr = "   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask opbrt = new Tilemask(opbr);

	private static String clbr = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask clbrt = new Tilemask(clbr);

	private static String prct = "  x   xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x  xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x  xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"    xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   xx x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx  x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx   x ";
	private static Tilemask prctt = new Tilemask(prct);

	private static String ampc = "  xxxx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxxx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx   x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx  xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxxx x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask ampct = new Tilemask(ampc);

	private static String dlar = "    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxxxxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"    x  x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxxxxx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"    x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask dlart = new Tilemask(dlar);

	private static String sqtp = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask sqtpt = new Tilemask(sqtp);

	private static String dqtp = " xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x  x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask dqtpt = new Tilemask(dqtp);

	private static String dqbt = "        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x  x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx xx  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx xx  ";
	private static Tilemask dqbtt = new Tilemask(dqbt);

	private static String cmfl = "   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xxx   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x   x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask cmflt = new Tilemask(cmfl);

	private static String dgre = "  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x  x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x  x   " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask dgret = new Tilemask(dgre);

	private static String x0xa = "  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x0xt = new Tilemask(x0xa);

	private static String x1xa = "  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x1xt = new Tilemask(x1xa);

	private static String x2xa = "  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x2xt = new Tilemask(x2xa);

	private static String x3xa = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x3xt = new Tilemask(x3xa);

	private static String x4xa = " x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x4xt = new Tilemask(x4xa);

	private static String x5xa = " xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x5xt = new Tilemask(x5xa);

	private static String x6xa = "  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x6xt = new Tilemask(x6xa);

	private static String x7xa = " xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x7xt = new Tilemask(x7xa);

	private static String x8xa = "  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x8xt = new Tilemask(x8xa);

	private static String x9xa = "  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"   x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask x9xt = new Tilemask(x9xa);

	private static String xAxa = "  x     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xAxt = new Tilemask(xAxa);

	private static String xBxa = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xBxt = new Tilemask(xBxa);

	private static String xCxa = "  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"  xx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xCxt = new Tilemask(xCxa);

	private static String xDxa = " xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x x    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xDxt = new Tilemask(xDxa);

	private static String xExa = " xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xExt = new Tilemask(xExa);

	private static String xFxa = " xxx    " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" xx     " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */" x      " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xFxt = new Tilemask(xFxa);

	private static String xx0a = "      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx0t = new Tilemask(xx0a);

	private static String xx1a = "      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx1t = new Tilemask(xx1a);

	private static String xx2a = "      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx2t = new Tilemask(xx2a);

	private static String xx3a = "     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx3t = new Tilemask(xx3a);

	private static String xx4a = "     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx4t = new Tilemask(xx4a);

	private static String xx5a = "     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx5t = new Tilemask(xx5a);

	private static String xx6a = "      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx6t = new Tilemask(xx6a);

	private static String xx7a = "     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx7t = new Tilemask(xx7a);

	private static String xx8a = "      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx8t = new Tilemask(xx8a);

	private static String xx9a = "      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"       x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xx9t = new Tilemask(xx9a);

	private static String xxAa = "      x " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xxAt = new Tilemask(xxAa);

	private static String xxBa = "     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xxBt = new Tilemask(xxBa);

	private static String xxCa = "      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"      xx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xxCt = new Tilemask(xxCa);

	private static String xxDa = "     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x x" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xxDt = new Tilemask(xxDa);

	private static String xxEa = "     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xxEt = new Tilemask(xxEa);

	private static String xxFa = "     xxx" + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     xx " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"     x  " + /* adsdsadadsadasdsadsasad */
	/* sadsadsadasdasaddssaaaa */"        ";
	private static Tilemask xxFt = new Tilemask(xxFa);

	private static Tilemask x00t = combineTilemasks(x0xt, xx0t);
	private static Tilemask x01t = combineTilemasks(x0xt, xx1t);
	private static Tilemask x02t = combineTilemasks(x0xt, xx2t);
	private static Tilemask x03t = combineTilemasks(x0xt, xx3t);
	private static Tilemask x04t = combineTilemasks(x0xt, xx4t);
	private static Tilemask x05t = combineTilemasks(x0xt, xx5t);
	private static Tilemask x06t = combineTilemasks(x0xt, xx6t);
	private static Tilemask x07t = combineTilemasks(x0xt, xx7t);
	private static Tilemask x08t = combineTilemasks(x0xt, xx8t);
	private static Tilemask x09t = combineTilemasks(x0xt, xx9t);
	private static Tilemask x0At = combineTilemasks(x0xt, xxAt);
	private static Tilemask x0Bt = combineTilemasks(x0xt, xxBt);
	private static Tilemask x0Ct = combineTilemasks(x0xt, xxCt);
	private static Tilemask x0Dt = combineTilemasks(x0xt, xxDt);
	private static Tilemask x0Et = combineTilemasks(x0xt, xxEt);
	private static Tilemask x0Ft = combineTilemasks(x0xt, xxFt);
	private static Tilemask x10t = combineTilemasks(x1xt, xx0t);
	private static Tilemask x11t = combineTilemasks(x1xt, xx1t);
	private static Tilemask x12t = combineTilemasks(x1xt, xx2t);
	private static Tilemask x13t = combineTilemasks(x1xt, xx3t);
	private static Tilemask x14t = combineTilemasks(x1xt, xx4t);
	private static Tilemask x15t = combineTilemasks(x1xt, xx5t);
	private static Tilemask x16t = combineTilemasks(x1xt, xx6t);
	private static Tilemask x17t = combineTilemasks(x1xt, xx7t);
	private static Tilemask x18t = combineTilemasks(x1xt, xx8t);
	private static Tilemask x19t = combineTilemasks(x1xt, xx9t);
	private static Tilemask x1At = combineTilemasks(x1xt, xxAt);
	private static Tilemask x1Bt = combineTilemasks(x1xt, xxBt);
	private static Tilemask x1Ct = combineTilemasks(x1xt, xxCt);
	private static Tilemask x1Dt = combineTilemasks(x1xt, xxDt);
	private static Tilemask x1Et = combineTilemasks(x1xt, xxEt);
	private static Tilemask x1Ft = combineTilemasks(x1xt, xxFt);
	private static Tilemask x20t = combineTilemasks(x2xt, xx0t);
	private static Tilemask x21t = combineTilemasks(x2xt, xx1t);
	private static Tilemask x22t = combineTilemasks(x2xt, xx2t);
	private static Tilemask x23t = combineTilemasks(x2xt, xx3t);
	private static Tilemask x24t = combineTilemasks(x2xt, xx4t);
	private static Tilemask x25t = combineTilemasks(x2xt, xx5t);
	private static Tilemask x26t = combineTilemasks(x2xt, xx6t);
	private static Tilemask x27t = combineTilemasks(x2xt, xx7t);
	private static Tilemask x28t = combineTilemasks(x2xt, xx8t);
	private static Tilemask x29t = combineTilemasks(x2xt, xx9t);
	private static Tilemask x2At = combineTilemasks(x2xt, xxAt);
	private static Tilemask x2Bt = combineTilemasks(x2xt, xxBt);
	private static Tilemask x2Ct = combineTilemasks(x2xt, xxCt);
	private static Tilemask x2Dt = combineTilemasks(x2xt, xxDt);
	private static Tilemask x2Et = combineTilemasks(x2xt, xxEt);
	private static Tilemask x2Ft = combineTilemasks(x2xt, xxFt);
	private static Tilemask x30t = combineTilemasks(x3xt, xx0t);
	private static Tilemask x31t = combineTilemasks(x3xt, xx1t);
	private static Tilemask x32t = combineTilemasks(x3xt, xx2t);
	private static Tilemask x33t = combineTilemasks(x3xt, xx3t);
	private static Tilemask x34t = combineTilemasks(x3xt, xx4t);
	private static Tilemask x35t = combineTilemasks(x3xt, xx5t);
	private static Tilemask x36t = combineTilemasks(x3xt, xx6t);
	private static Tilemask x37t = combineTilemasks(x3xt, xx7t);
	private static Tilemask x38t = combineTilemasks(x3xt, xx8t);
	private static Tilemask x39t = combineTilemasks(x3xt, xx9t);
	private static Tilemask x3At = combineTilemasks(x3xt, xxAt);
	private static Tilemask x3Bt = combineTilemasks(x3xt, xxBt);
	private static Tilemask x3Ct = combineTilemasks(x3xt, xxCt);
	private static Tilemask x3Dt = combineTilemasks(x3xt, xxDt);
	private static Tilemask x3Et = combineTilemasks(x3xt, xxEt);
	private static Tilemask x3Ft = combineTilemasks(x3xt, xxFt);
	private static Tilemask x40t = combineTilemasks(x4xt, xx0t);
	private static Tilemask x41t = combineTilemasks(x4xt, xx1t);
	private static Tilemask x42t = combineTilemasks(x4xt, xx2t);
	private static Tilemask x43t = combineTilemasks(x4xt, xx3t);
	private static Tilemask x44t = combineTilemasks(x4xt, xx4t);
	private static Tilemask x45t = combineTilemasks(x4xt, xx5t);
	private static Tilemask x46t = combineTilemasks(x4xt, xx6t);
	private static Tilemask x47t = combineTilemasks(x4xt, xx7t);
	private static Tilemask x48t = combineTilemasks(x4xt, xx8t);
	private static Tilemask x49t = combineTilemasks(x4xt, xx9t);
	private static Tilemask x4At = combineTilemasks(x4xt, xxAt);
	private static Tilemask x4Bt = combineTilemasks(x4xt, xxBt);
	private static Tilemask x4Ct = combineTilemasks(x4xt, xxCt);
	private static Tilemask x4Dt = combineTilemasks(x4xt, xxDt);
	private static Tilemask x4Et = combineTilemasks(x4xt, xxEt);
	private static Tilemask x4Ft = combineTilemasks(x4xt, xxFt);
	private static Tilemask x50t = combineTilemasks(x5xt, xx0t);
	private static Tilemask x51t = combineTilemasks(x5xt, xx1t);
	private static Tilemask x52t = combineTilemasks(x5xt, xx2t);
	private static Tilemask x53t = combineTilemasks(x5xt, xx3t);
	private static Tilemask x54t = combineTilemasks(x5xt, xx4t);
	private static Tilemask x55t = combineTilemasks(x5xt, xx5t);
	private static Tilemask x56t = combineTilemasks(x5xt, xx6t);
	private static Tilemask x57t = combineTilemasks(x5xt, xx7t);
	private static Tilemask x58t = combineTilemasks(x5xt, xx8t);
	private static Tilemask x59t = combineTilemasks(x5xt, xx9t);
	private static Tilemask x5At = combineTilemasks(x5xt, xxAt);
	private static Tilemask x5Bt = combineTilemasks(x5xt, xxBt);
	private static Tilemask x5Ct = combineTilemasks(x5xt, xxCt);
	private static Tilemask x5Dt = combineTilemasks(x5xt, xxDt);
	private static Tilemask x5Et = combineTilemasks(x5xt, xxEt);
	private static Tilemask x5Ft = combineTilemasks(x5xt, xxFt);
	private static Tilemask x60t = combineTilemasks(x6xt, xx0t);
	private static Tilemask x61t = combineTilemasks(x6xt, xx1t);
	private static Tilemask x62t = combineTilemasks(x6xt, xx2t);
	private static Tilemask x63t = combineTilemasks(x6xt, xx3t);
	private static Tilemask x64t = combineTilemasks(x6xt, xx4t);
	private static Tilemask x65t = combineTilemasks(x6xt, xx5t);
	private static Tilemask x66t = combineTilemasks(x6xt, xx6t);
	private static Tilemask x67t = combineTilemasks(x6xt, xx7t);
	private static Tilemask x68t = combineTilemasks(x6xt, xx8t);
	private static Tilemask x69t = combineTilemasks(x6xt, xx9t);
	private static Tilemask x6At = combineTilemasks(x6xt, xxAt);
	private static Tilemask x6Bt = combineTilemasks(x6xt, xxBt);
	private static Tilemask x6Ct = combineTilemasks(x6xt, xxCt);
	private static Tilemask x6Dt = combineTilemasks(x6xt, xxDt);
	private static Tilemask x6Et = combineTilemasks(x6xt, xxEt);
	private static Tilemask x6Ft = combineTilemasks(x6xt, xxFt);
	private static Tilemask x70t = combineTilemasks(x7xt, xx0t);
	private static Tilemask x71t = combineTilemasks(x7xt, xx1t);
	private static Tilemask x72t = combineTilemasks(x7xt, xx2t);
	private static Tilemask x73t = combineTilemasks(x7xt, xx3t);
	private static Tilemask x74t = combineTilemasks(x7xt, xx4t);
	private static Tilemask x75t = combineTilemasks(x7xt, xx5t);
	private static Tilemask x76t = combineTilemasks(x7xt, xx6t);
	private static Tilemask x77t = combineTilemasks(x7xt, xx7t);
	private static Tilemask x78t = combineTilemasks(x7xt, xx8t);
	private static Tilemask x79t = combineTilemasks(x7xt, xx9t);
	private static Tilemask x7At = combineTilemasks(x7xt, xxAt);
	private static Tilemask x7Bt = combineTilemasks(x7xt, xxBt);
	private static Tilemask x7Ct = combineTilemasks(x7xt, xxCt);
	private static Tilemask x7Dt = combineTilemasks(x7xt, xxDt);
	private static Tilemask x7Et = combineTilemasks(x7xt, xxEt);
	private static Tilemask x7Ft = combineTilemasks(x7xt, xxFt);
	private static Tilemask x80t = combineTilemasks(x8xt, xx0t);
	private static Tilemask x81t = combineTilemasks(x8xt, xx1t);
	private static Tilemask x82t = combineTilemasks(x8xt, xx2t);
	private static Tilemask x83t = combineTilemasks(x8xt, xx3t);
	private static Tilemask x84t = combineTilemasks(x8xt, xx4t);
	private static Tilemask x85t = combineTilemasks(x8xt, xx5t);
	private static Tilemask x86t = combineTilemasks(x8xt, xx6t);
	private static Tilemask x87t = combineTilemasks(x8xt, xx7t);
	private static Tilemask x88t = combineTilemasks(x8xt, xx8t);
	private static Tilemask x89t = combineTilemasks(x8xt, xx9t);
	private static Tilemask x8At = combineTilemasks(x8xt, xxAt);
	private static Tilemask x8Bt = combineTilemasks(x8xt, xxBt);
	private static Tilemask x8Ct = combineTilemasks(x8xt, xxCt);
	private static Tilemask x8Dt = combineTilemasks(x8xt, xxDt);
	private static Tilemask x8Et = combineTilemasks(x8xt, xxEt);
	private static Tilemask x8Ft = combineTilemasks(x8xt, xxFt);
	private static Tilemask x90t = combineTilemasks(x9xt, xx0t);
	private static Tilemask x91t = combineTilemasks(x9xt, xx1t);
	private static Tilemask x92t = combineTilemasks(x9xt, xx2t);
	private static Tilemask x93t = combineTilemasks(x9xt, xx3t);
	private static Tilemask x94t = combineTilemasks(x9xt, xx4t);
	private static Tilemask x95t = combineTilemasks(x9xt, xx5t);
	private static Tilemask x96t = combineTilemasks(x9xt, xx6t);
	private static Tilemask x97t = combineTilemasks(x9xt, xx7t);
	private static Tilemask x98t = combineTilemasks(x9xt, xx8t);
	private static Tilemask x99t = combineTilemasks(x9xt, xx9t);
	private static Tilemask x9At = combineTilemasks(x9xt, xxAt);
	private static Tilemask x9Bt = combineTilemasks(x9xt, xxBt);
	private static Tilemask x9Ct = combineTilemasks(x9xt, xxCt);
	private static Tilemask x9Dt = combineTilemasks(x9xt, xxDt);
	private static Tilemask x9Et = combineTilemasks(x9xt, xxEt);
	private static Tilemask x9Ft = combineTilemasks(x9xt, xxFt);
	private static Tilemask xA0t = combineTilemasks(xAxt, xx0t);
	private static Tilemask xA1t = combineTilemasks(xAxt, xx1t);
	private static Tilemask xA2t = combineTilemasks(xAxt, xx2t);
	private static Tilemask xA3t = combineTilemasks(xAxt, xx3t);
	private static Tilemask xA4t = combineTilemasks(xAxt, xx4t);
	private static Tilemask xA5t = combineTilemasks(xAxt, xx5t);
	private static Tilemask xA6t = combineTilemasks(xAxt, xx6t);
	private static Tilemask xA7t = combineTilemasks(xAxt, xx7t);
	private static Tilemask xA8t = combineTilemasks(xAxt, xx8t);
	private static Tilemask xA9t = combineTilemasks(xAxt, xx9t);
	private static Tilemask xAAt = combineTilemasks(xAxt, xxAt);
	private static Tilemask xABt = combineTilemasks(xAxt, xxBt);
	private static Tilemask xACt = combineTilemasks(xAxt, xxCt);
	private static Tilemask xADt = combineTilemasks(xAxt, xxDt);
	private static Tilemask xAEt = combineTilemasks(xAxt, xxEt);
	private static Tilemask xAFt = combineTilemasks(xAxt, xxFt);
	private static Tilemask xB0t = combineTilemasks(xBxt, xx0t);
	private static Tilemask xB1t = combineTilemasks(xBxt, xx1t);
	private static Tilemask xB2t = combineTilemasks(xBxt, xx2t);
	private static Tilemask xB3t = combineTilemasks(xBxt, xx3t);
	private static Tilemask xB4t = combineTilemasks(xBxt, xx4t);
	private static Tilemask xB5t = combineTilemasks(xBxt, xx5t);
	private static Tilemask xB6t = combineTilemasks(xBxt, xx6t);
	private static Tilemask xB7t = combineTilemasks(xBxt, xx7t);
	private static Tilemask xB8t = combineTilemasks(xBxt, xx8t);
	private static Tilemask xB9t = combineTilemasks(xBxt, xx9t);
	private static Tilemask xBAt = combineTilemasks(xBxt, xxAt);
	private static Tilemask xBBt = combineTilemasks(xBxt, xxBt);
	private static Tilemask xBCt = combineTilemasks(xBxt, xxCt);
	private static Tilemask xBDt = combineTilemasks(xBxt, xxDt);
	private static Tilemask xBEt = combineTilemasks(xBxt, xxEt);
	private static Tilemask xBFt = combineTilemasks(xBxt, xxFt);
	private static Tilemask xC0t = combineTilemasks(xCxt, xx0t);
	private static Tilemask xC1t = combineTilemasks(xCxt, xx1t);
	private static Tilemask xC2t = combineTilemasks(xCxt, xx2t);
	private static Tilemask xC3t = combineTilemasks(xCxt, xx3t);
	private static Tilemask xC4t = combineTilemasks(xCxt, xx4t);
	private static Tilemask xC5t = combineTilemasks(xCxt, xx5t);
	private static Tilemask xC6t = combineTilemasks(xCxt, xx6t);
	private static Tilemask xC7t = combineTilemasks(xCxt, xx7t);
	private static Tilemask xC8t = combineTilemasks(xCxt, xx8t);
	private static Tilemask xC9t = combineTilemasks(xCxt, xx9t);
	private static Tilemask xCAt = combineTilemasks(xCxt, xxAt);
	private static Tilemask xCBt = combineTilemasks(xCxt, xxBt);
	private static Tilemask xCCt = combineTilemasks(xCxt, xxCt);
	private static Tilemask xCDt = combineTilemasks(xCxt, xxDt);
	private static Tilemask xCEt = combineTilemasks(xCxt, xxEt);
	private static Tilemask xCFt = combineTilemasks(xCxt, xxFt);
	private static Tilemask xD0t = combineTilemasks(xDxt, xx0t);
	private static Tilemask xD1t = combineTilemasks(xDxt, xx1t);
	private static Tilemask xD2t = combineTilemasks(xDxt, xx2t);
	private static Tilemask xD3t = combineTilemasks(xDxt, xx3t);
	private static Tilemask xD4t = combineTilemasks(xDxt, xx4t);
	private static Tilemask xD5t = combineTilemasks(xDxt, xx5t);
	private static Tilemask xD6t = combineTilemasks(xDxt, xx6t);
	private static Tilemask xD7t = combineTilemasks(xDxt, xx7t);
	private static Tilemask xD8t = combineTilemasks(xDxt, xx8t);
	private static Tilemask xD9t = combineTilemasks(xDxt, xx9t);
	private static Tilemask xDAt = combineTilemasks(xDxt, xxAt);
	private static Tilemask xDBt = combineTilemasks(xDxt, xxBt);
	private static Tilemask xDCt = combineTilemasks(xDxt, xxCt);
	private static Tilemask xDDt = combineTilemasks(xDxt, xxDt);
	private static Tilemask xDEt = combineTilemasks(xDxt, xxEt);
	private static Tilemask xDFt = combineTilemasks(xDxt, xxFt);
	private static Tilemask xE0t = combineTilemasks(xExt, xx0t);
	private static Tilemask xE1t = combineTilemasks(xExt, xx1t);
	private static Tilemask xE2t = combineTilemasks(xExt, xx2t);
	private static Tilemask xE3t = combineTilemasks(xExt, xx3t);
	private static Tilemask xE4t = combineTilemasks(xExt, xx4t);
	private static Tilemask xE5t = combineTilemasks(xExt, xx5t);
	private static Tilemask xE6t = combineTilemasks(xExt, xx6t);
	private static Tilemask xE7t = combineTilemasks(xExt, xx7t);
	private static Tilemask xE8t = combineTilemasks(xExt, xx8t);
	private static Tilemask xE9t = combineTilemasks(xExt, xx9t);
	private static Tilemask xEAt = combineTilemasks(xExt, xxAt);
	private static Tilemask xEBt = combineTilemasks(xExt, xxBt);
	private static Tilemask xECt = combineTilemasks(xExt, xxCt);
	private static Tilemask xEDt = combineTilemasks(xExt, xxDt);
	private static Tilemask xEEt = combineTilemasks(xExt, xxEt);
	private static Tilemask xEFt = combineTilemasks(xExt, xxFt);
	private static Tilemask xF0t = combineTilemasks(xFxt, xx0t);
	private static Tilemask xF1t = combineTilemasks(xFxt, xx1t);
	private static Tilemask xF2t = combineTilemasks(xFxt, xx2t);
	private static Tilemask xF3t = combineTilemasks(xFxt, xx3t);
	private static Tilemask xF4t = combineTilemasks(xFxt, xx4t);
	private static Tilemask xF5t = combineTilemasks(xFxt, xx5t);
	private static Tilemask xF6t = combineTilemasks(xFxt, xx6t);
	private static Tilemask xF7t = combineTilemasks(xFxt, xx7t);
	private static Tilemask xF8t = combineTilemasks(xFxt, xx8t);
	private static Tilemask xF9t = combineTilemasks(xFxt, xx9t);
	private static Tilemask xFAt = combineTilemasks(xFxt, xxAt);
	private static Tilemask xFBt = combineTilemasks(xFxt, xxBt);
	private static Tilemask xFCt = combineTilemasks(xFxt, xxCt);
	private static Tilemask xFDt = combineTilemasks(xFxt, xxDt);
	private static Tilemask xFEt = combineTilemasks(xFxt, xxEt);
	private static Tilemask xFFt = combineTilemasks(xFxt, xxFt);

	/**
	 * hashmaps for binary string representation of round/square paint brushes
	 */
	private static HashMap<Integer, Tilemask> roundHashMap;
	private static HashMap<Integer, Tilemask> squareHashMap;
	private static HashMap<Integer, Tilemask> rountHashMap;
	private static HashMap<Integer, Tilemask> squartHashMap;

	/**
	 * function to get the round paintbrush of the given size (1..8)
	 * 
	 * @param size
	 *            the size
	 * @param twoColors
	 *            true if two colors, false if one
	 * @return the paintbrush Tilemask
	 */
	public static Tilemask getMaskForRound(int size, boolean twoColors)
	{
		if (roundHashMap == null)
		{
			roundHashMap = new HashMap<Integer, Tilemask>();
			roundHashMap.put(1, round1t);
			roundHashMap.put(2, round2t);
			roundHashMap.put(3, round3t);
			roundHashMap.put(4, round4t);
			roundHashMap.put(5, round5t);
			roundHashMap.put(6, round6t);
			roundHashMap.put(7, round7t);
			roundHashMap.put(8, round8t);
		}
		if (rountHashMap == null)
		{
			rountHashMap = new HashMap<Integer, Tilemask>();
			rountHashMap.put(1, rount1t);
			rountHashMap.put(2, rount2t);
			rountHashMap.put(3, rount3t);
			rountHashMap.put(4, rount4t);
			rountHashMap.put(5, rount5t);
			rountHashMap.put(6, rount6t);
			rountHashMap.put(7, rount7t);
			rountHashMap.put(8, rount8t);
		}
		if (!twoColors)
		{
			return roundHashMap.get(size);
		}
		return rountHashMap.get(size);
	}

	/**
	 * function to get the rectangular paintbrush of the given size (1..8)
	 * 
	 * @param size
	 *            the size
	 * @param twoColors
	 *            true if two colors, false if one
	 * @return the paintbrush Tilemask
	 */
	public static Tilemask getMaskForSquare(int size, boolean twoColors)
	{
		if (squareHashMap == null)
		{
			squareHashMap = new HashMap<Integer, Tilemask>();
			squareHashMap.put(1, recta1t);
			squareHashMap.put(2, recta2t);
			squareHashMap.put(3, recta3t);
			squareHashMap.put(4, recta4t);
			squareHashMap.put(5, recta5t);
			squareHashMap.put(6, recta6t);
			squareHashMap.put(7, recta7t);
			squareHashMap.put(8, recta8t);
		}
		if (squartHashMap == null)
		{
			squartHashMap = new HashMap<Integer, Tilemask>();
			squartHashMap.put(1, rectt1t);
			squartHashMap.put(2, rectt2t);
			squartHashMap.put(3, rectt3t);
			squartHashMap.put(4, rectt4t);
			squartHashMap.put(5, rectt5t);
			squartHashMap.put(6, rectt6t);
			squartHashMap.put(7, rectt7t);
			squartHashMap.put(8, rectt8t);
		}
		if (!twoColors)
		{
			return squareHashMap.get(size);
		}
		return squartHashMap.get(size);
	}

	/**
	 * Hashmap holding all character binary strings
	 */
	private static HashMap<Character, Tilemask> textHashMap;

	/**
	 * returns the 8x8 character long binary string for the given character or
	 * null if there's none such string. The first eight characters correspond
	 * to the first row of eight pixels whereas a space character signified no
	 * color, an x character signifies a set color value
	 * 
	 * @param ch
	 *            the character to get the binary string representation for
	 * @return the binary Tilemask
	 */
	public static Tilemask getMaskForCharacter(char ch)
	{
		if (textHashMap == null)
		{
			textHashMap = new HashMap<Character, Tilemask>();
			textHashMap.put('A', At);
			textHashMap.put('B', Bt);
			textHashMap.put('C', Ct);
			textHashMap.put('D', Dt);
			textHashMap.put('E', Et);
			textHashMap.put('F', Ft);
			textHashMap.put('G', Gt);
			textHashMap.put('H', Ht);
			textHashMap.put('I', It);
			textHashMap.put('J', Jt);
			textHashMap.put('K', Kt);
			textHashMap.put('L', Lt);
			textHashMap.put('M', Mt);
			textHashMap.put('N', Nt);
			textHashMap.put('O', Ot);
			textHashMap.put('P', Pt);
			textHashMap.put('Q', Qt);
			textHashMap.put('R', Rt);
			textHashMap.put('S', St);
			textHashMap.put('T', Tt);
			textHashMap.put('U', Ut);
			textHashMap.put('V', Vt);
			textHashMap.put('W', Wt);
			textHashMap.put('X', Xt);
			textHashMap.put('Y', Yt);
			textHashMap.put('Z', Zt);
			textHashMap.put('Ä', Ät);
			textHashMap.put('Ö', Öt);
			textHashMap.put('Ü', Üt);
			textHashMap.put('a', at);
			textHashMap.put('b', bt);
			textHashMap.put('c', ct);
			textHashMap.put('d', dt);
			textHashMap.put('e', et);
			textHashMap.put('f', ft);
			textHashMap.put('g', gt);
			textHashMap.put('h', ht);
			textHashMap.put('i', it);
			textHashMap.put('j', jt);
			textHashMap.put('k', kt);
			textHashMap.put('l', lt);
			textHashMap.put('m', mt);
			textHashMap.put('n', nt);
			textHashMap.put('o', ot);
			textHashMap.put('p', pt);
			textHashMap.put('q', qt);
			textHashMap.put('r', rt);
			textHashMap.put('s', st);
			textHashMap.put('t', tt);
			textHashMap.put('u', ut);
			textHashMap.put('v', vt);
			textHashMap.put('w', wt);
			textHashMap.put('x', xt);
			textHashMap.put('y', yt);
			textHashMap.put('z', zt);
			textHashMap.put('ä', ät);
			textHashMap.put('ö', öt);
			textHashMap.put('ü', üt);
			textHashMap.put('ß', ßt);
			textHashMap.put('0', n0t);
			textHashMap.put('1', n1t);
			textHashMap.put('2', n2t);
			textHashMap.put('3', n3t);
			textHashMap.put('4', n4t);
			textHashMap.put('5', n5t);
			textHashMap.put('6', n6t);
			textHashMap.put('7', n7t);
			textHashMap.put('8', n8t);
			textHashMap.put('9', n9t);
			textHashMap.put(':', ddott);
			textHashMap.put(',', comat);
			textHashMap.put('.', sdott);
			textHashMap.put(' ', spcet);
			textHashMap.put(';', scolt);
			textHashMap.put('-', minut);
			textHashMap.put('+', plust);
			textHashMap.put('*', mltpt);
			textHashMap.put('#', hasht);
			textHashMap.put('_', udsrt);
			textHashMap.put('~', tldet);
			textHashMap.put('?', qtnmt);
			textHashMap.put('�', qtspt);
			textHashMap.put('!', xklmt);
			textHashMap.put('�', xkspt);
			textHashMap.put('/', fwslt);
			textHashMap.put('\\', bwslt);
			textHashMap.put('(', opptt);
			textHashMap.put(')', clptt);
			textHashMap.put('[', opbct);
			textHashMap.put(']', clbct);
			textHashMap.put('{', opbrt);
			textHashMap.put('}', clbrt);
			textHashMap.put('%', prctt);
			textHashMap.put('&', ampct);
			textHashMap.put('$', dlart);
			textHashMap.put('\'', sqtpt);
			textHashMap.put('�', dqtpt);
			textHashMap.put('�', dqbtt);
			textHashMap.put('^', cmflt);
			textHashMap.put('�', dgret);
		}
		return textHashMap.get(ch);
	}

	/**
	 * Hashmap holding all character hex value strings
	 */
	private static HashMap<Integer, Tilemask> hexHashMap;

	/**
	 * returns the 8x8 character long binary string for the given character or
	 * null if there's none such string. The first eight characters correspond
	 * to the first row of eight pixels whereas a space character signified no
	 * color, an x character signifies a set color value
	 * 
	 * @param by
	 *            the byte to get the binary string representation for
	 * @return the binary Tilemask
	 */
	public static Tilemask getMaskForHex(int i)
	{
		if (hexHashMap == null)
		{
			hexHashMap = new HashMap<Integer, Tilemask>();
			hexHashMap.put(0, x00t);
			hexHashMap.put(1, x01t);
			hexHashMap.put(2, x02t);
			hexHashMap.put(3, x03t);
			hexHashMap.put(4, x04t);
			hexHashMap.put(5, x05t);
			hexHashMap.put(6, x06t);
			hexHashMap.put(7, x07t);
			hexHashMap.put(8, x08t);
			hexHashMap.put(9, x09t);
			hexHashMap.put(10, x0At);
			hexHashMap.put(11, x0Bt);
			hexHashMap.put(12, x0Ct);
			hexHashMap.put(13, x0Dt);
			hexHashMap.put(14, x0Et);
			hexHashMap.put(15, x0Ft);
			hexHashMap.put(16, x10t);
			hexHashMap.put(17, x11t);
			hexHashMap.put(18, x12t);
			hexHashMap.put(19, x13t);
			hexHashMap.put(20, x14t);
			hexHashMap.put(21, x15t);
			hexHashMap.put(22, x16t);
			hexHashMap.put(23, x17t);
			hexHashMap.put(24, x18t);
			hexHashMap.put(25, x19t);
			hexHashMap.put(26, x1At);
			hexHashMap.put(27, x1Bt);
			hexHashMap.put(28, x1Ct);
			hexHashMap.put(29, x1Dt);
			hexHashMap.put(30, x1Et);
			hexHashMap.put(31, x1Ft);
			hexHashMap.put(32, x20t);
			hexHashMap.put(33, x21t);
			hexHashMap.put(34, x22t);
			hexHashMap.put(35, x23t);
			hexHashMap.put(36, x24t);
			hexHashMap.put(37, x25t);
			hexHashMap.put(38, x26t);
			hexHashMap.put(39, x27t);
			hexHashMap.put(40, x28t);
			hexHashMap.put(41, x29t);
			hexHashMap.put(42, x2At);
			hexHashMap.put(43, x2Bt);
			hexHashMap.put(44, x2Ct);
			hexHashMap.put(45, x2Dt);
			hexHashMap.put(46, x2Et);
			hexHashMap.put(47, x2Ft);
			hexHashMap.put(48, x30t);
			hexHashMap.put(49, x31t);
			hexHashMap.put(50, x32t);
			hexHashMap.put(51, x33t);
			hexHashMap.put(52, x34t);
			hexHashMap.put(53, x35t);
			hexHashMap.put(54, x36t);
			hexHashMap.put(55, x37t);
			hexHashMap.put(56, x38t);
			hexHashMap.put(57, x39t);
			hexHashMap.put(58, x3At);
			hexHashMap.put(59, x3Bt);
			hexHashMap.put(60, x3Ct);
			hexHashMap.put(61, x3Dt);
			hexHashMap.put(62, x3Et);
			hexHashMap.put(63, x3Ft);
			hexHashMap.put(64, x40t);
			hexHashMap.put(65, x41t);
			hexHashMap.put(66, x42t);
			hexHashMap.put(67, x43t);
			hexHashMap.put(68, x44t);
			hexHashMap.put(69, x45t);
			hexHashMap.put(70, x46t);
			hexHashMap.put(71, x47t);
			hexHashMap.put(72, x48t);
			hexHashMap.put(73, x49t);
			hexHashMap.put(74, x4At);
			hexHashMap.put(75, x4Bt);
			hexHashMap.put(76, x4Ct);
			hexHashMap.put(77, x4Dt);
			hexHashMap.put(78, x4Et);
			hexHashMap.put(79, x4Ft);
			hexHashMap.put(80, x50t);
			hexHashMap.put(81, x51t);
			hexHashMap.put(82, x52t);
			hexHashMap.put(83, x53t);
			hexHashMap.put(84, x54t);
			hexHashMap.put(85, x55t);
			hexHashMap.put(86, x56t);
			hexHashMap.put(87, x57t);
			hexHashMap.put(88, x58t);
			hexHashMap.put(89, x59t);
			hexHashMap.put(90, x5At);
			hexHashMap.put(91, x5Bt);
			hexHashMap.put(92, x5Ct);
			hexHashMap.put(93, x5Dt);
			hexHashMap.put(94, x5Et);
			hexHashMap.put(95, x5Ft);
			hexHashMap.put(96, x60t);
			hexHashMap.put(97, x61t);
			hexHashMap.put(98, x62t);
			hexHashMap.put(99, x63t);
			hexHashMap.put(100, x64t);
			hexHashMap.put(101, x65t);
			hexHashMap.put(102, x66t);
			hexHashMap.put(103, x67t);
			hexHashMap.put(104, x68t);
			hexHashMap.put(105, x69t);
			hexHashMap.put(106, x6At);
			hexHashMap.put(107, x6Bt);
			hexHashMap.put(108, x6Ct);
			hexHashMap.put(109, x6Dt);
			hexHashMap.put(110, x6Et);
			hexHashMap.put(111, x6Ft);
			hexHashMap.put(112, x70t);
			hexHashMap.put(113, x71t);
			hexHashMap.put(114, x72t);
			hexHashMap.put(115, x73t);
			hexHashMap.put(116, x74t);
			hexHashMap.put(117, x75t);
			hexHashMap.put(118, x76t);
			hexHashMap.put(119, x77t);
			hexHashMap.put(120, x78t);
			hexHashMap.put(121, x79t);
			hexHashMap.put(122, x7At);
			hexHashMap.put(123, x7Bt);
			hexHashMap.put(124, x7Ct);
			hexHashMap.put(125, x7Dt);
			hexHashMap.put(126, x7Et);
			hexHashMap.put(127, x7Ft);
			hexHashMap.put(128, x80t);
			hexHashMap.put(129, x81t);
			hexHashMap.put(130, x82t);
			hexHashMap.put(131, x83t);
			hexHashMap.put(132, x84t);
			hexHashMap.put(133, x85t);
			hexHashMap.put(134, x86t);
			hexHashMap.put(135, x87t);
			hexHashMap.put(136, x88t);
			hexHashMap.put(137, x89t);
			hexHashMap.put(138, x8At);
			hexHashMap.put(139, x8Bt);
			hexHashMap.put(140, x8Ct);
			hexHashMap.put(141, x8Dt);
			hexHashMap.put(142, x8Et);
			hexHashMap.put(143, x8Ft);
			hexHashMap.put(144, x90t);
			hexHashMap.put(145, x91t);
			hexHashMap.put(146, x92t);
			hexHashMap.put(147, x93t);
			hexHashMap.put(148, x94t);
			hexHashMap.put(149, x95t);
			hexHashMap.put(150, x96t);
			hexHashMap.put(151, x97t);
			hexHashMap.put(152, x98t);
			hexHashMap.put(153, x99t);
			hexHashMap.put(154, x9At);
			hexHashMap.put(155, x9Bt);
			hexHashMap.put(156, x9Ct);
			hexHashMap.put(157, x9Dt);
			hexHashMap.put(158, x9Et);
			hexHashMap.put(159, x9Ft);
			hexHashMap.put(160, xA0t);
			hexHashMap.put(161, xA1t);
			hexHashMap.put(162, xA2t);
			hexHashMap.put(163, xA3t);
			hexHashMap.put(164, xA4t);
			hexHashMap.put(165, xA5t);
			hexHashMap.put(166, xA6t);
			hexHashMap.put(167, xA7t);
			hexHashMap.put(168, xA8t);
			hexHashMap.put(169, xA9t);
			hexHashMap.put(170, xAAt);
			hexHashMap.put(171, xABt);
			hexHashMap.put(172, xACt);
			hexHashMap.put(173, xADt);
			hexHashMap.put(174, xAEt);
			hexHashMap.put(175, xAFt);
			hexHashMap.put(176, xB0t);
			hexHashMap.put(177, xB1t);
			hexHashMap.put(178, xB2t);
			hexHashMap.put(179, xB3t);
			hexHashMap.put(180, xB4t);
			hexHashMap.put(181, xB5t);
			hexHashMap.put(182, xB6t);
			hexHashMap.put(183, xB7t);
			hexHashMap.put(184, xB8t);
			hexHashMap.put(185, xB9t);
			hexHashMap.put(186, xBAt);
			hexHashMap.put(187, xBBt);
			hexHashMap.put(188, xBCt);
			hexHashMap.put(189, xBDt);
			hexHashMap.put(190, xBEt);
			hexHashMap.put(191, xBFt);
			hexHashMap.put(192, xC0t);
			hexHashMap.put(193, xC1t);
			hexHashMap.put(194, xC2t);
			hexHashMap.put(195, xC3t);
			hexHashMap.put(196, xC4t);
			hexHashMap.put(197, xC5t);
			hexHashMap.put(198, xC6t);
			hexHashMap.put(199, xC7t);
			hexHashMap.put(200, xC8t);
			hexHashMap.put(201, xC9t);
			hexHashMap.put(202, xCAt);
			hexHashMap.put(203, xCBt);
			hexHashMap.put(204, xCCt);
			hexHashMap.put(205, xCDt);
			hexHashMap.put(206, xCEt);
			hexHashMap.put(207, xCFt);
			hexHashMap.put(208, xD0t);
			hexHashMap.put(209, xD1t);
			hexHashMap.put(210, xD2t);
			hexHashMap.put(211, xD3t);
			hexHashMap.put(212, xD4t);
			hexHashMap.put(213, xD5t);
			hexHashMap.put(214, xD6t);
			hexHashMap.put(215, xD7t);
			hexHashMap.put(216, xD8t);
			hexHashMap.put(217, xD9t);
			hexHashMap.put(218, xDAt);
			hexHashMap.put(219, xDBt);
			hexHashMap.put(220, xDCt);
			hexHashMap.put(221, xDDt);
			hexHashMap.put(222, xDEt);
			hexHashMap.put(223, xDFt);
			hexHashMap.put(224, xE0t);
			hexHashMap.put(225, xE1t);
			hexHashMap.put(226, xE2t);
			hexHashMap.put(227, xE3t);
			hexHashMap.put(228, xE4t);
			hexHashMap.put(229, xE5t);
			hexHashMap.put(230, xE6t);
			hexHashMap.put(231, xE7t);
			hexHashMap.put(232, xE8t);
			hexHashMap.put(233, xE9t);
			hexHashMap.put(234, xEAt);
			hexHashMap.put(235, xEBt);
			hexHashMap.put(236, xECt);
			hexHashMap.put(237, xEDt);
			hexHashMap.put(238, xEEt);
			hexHashMap.put(239, xEFt);
			hexHashMap.put(240, xF0t);
			hexHashMap.put(241, xF1t);
			hexHashMap.put(242, xF2t);
			hexHashMap.put(243, xF3t);
			hexHashMap.put(244, xF4t);
			hexHashMap.put(245, xF5t);
			hexHashMap.put(246, xF6t);
			hexHashMap.put(247, xF7t);
			hexHashMap.put(248, xF8t);
			hexHashMap.put(249, xF9t);
			hexHashMap.put(250, xFAt);
			hexHashMap.put(251, xFBt);
			hexHashMap.put(252, xFCt);
			hexHashMap.put(253, xFDt);
			hexHashMap.put(254, xFEt);
			hexHashMap.put(255, xFFt);
		}
		return hexHashMap.get(i);
	}

	/**
	 * Hashmap holding all character hex value strings
	 */
	private static HashMap<Integer, Tilemask> decHashMap;

	/**
	 * returns the 8x8 character long binary string for the given character or
	 * null if there's none such string. The first eight characters correspond
	 * to the first row of eight pixels whereas a space character signified no
	 * color, an x character signifies a set color value
	 * 
	 * @param by
	 *            the byte to get the binary string representation for
	 * @return the binary Tilemask
	 */
	public static Tilemask getMaskForDec(int i)
	{
		if (decHashMap == null)
		{
			decHashMap = new HashMap<Integer, Tilemask>();
			decHashMap.put(0, x00t);
			decHashMap.put(1, x01t);
			decHashMap.put(2, x02t);
			decHashMap.put(3, x03t);
			decHashMap.put(4, x04t);
			decHashMap.put(5, x05t);
			decHashMap.put(6, x06t);
			decHashMap.put(7, x07t);
			decHashMap.put(8, x08t);
			decHashMap.put(9, x09t);
			decHashMap.put(10, x10t);
			decHashMap.put(11, x11t);
			decHashMap.put(12, x12t);
			decHashMap.put(13, x13t);
			decHashMap.put(14, x14t);
			decHashMap.put(15, x15t);
			decHashMap.put(16, x16t);
			decHashMap.put(17, x17t);
			decHashMap.put(18, x18t);
			decHashMap.put(19, x19t);
			decHashMap.put(20, x20t);
			decHashMap.put(21, x21t);
			decHashMap.put(22, x22t);
			decHashMap.put(23, x23t);
			decHashMap.put(24, x24t);
			decHashMap.put(25, x25t);
			decHashMap.put(26, x26t);
			decHashMap.put(27, x27t);
			decHashMap.put(28, x28t);
			decHashMap.put(29, x29t);
			decHashMap.put(30, x30t);
			decHashMap.put(31, x31t);
			decHashMap.put(32, x32t);
			decHashMap.put(33, x33t);
			decHashMap.put(34, x34t);
			decHashMap.put(35, x35t);
			decHashMap.put(36, x36t);
			decHashMap.put(37, x37t);
			decHashMap.put(38, x38t);
			decHashMap.put(39, x39t);
			decHashMap.put(40, x40t);
			decHashMap.put(41, x41t);
			decHashMap.put(42, x42t);
			decHashMap.put(43, x43t);
			decHashMap.put(44, x44t);
			decHashMap.put(45, x45t);
			decHashMap.put(46, x46t);
			decHashMap.put(47, x47t);
			decHashMap.put(48, x48t);
			decHashMap.put(49, x49t);
			decHashMap.put(50, x50t);
			decHashMap.put(51, x51t);
			decHashMap.put(52, x52t);
			decHashMap.put(53, x53t);
			decHashMap.put(54, x54t);
			decHashMap.put(55, x55t);
			decHashMap.put(56, x56t);
			decHashMap.put(57, x57t);
			decHashMap.put(58, x58t);
			decHashMap.put(59, x59t);
			decHashMap.put(60, x60t);
			decHashMap.put(61, x61t);
			decHashMap.put(62, x62t);
			decHashMap.put(63, x63t);
			decHashMap.put(64, x64t);
			decHashMap.put(65, x65t);
			decHashMap.put(66, x66t);
			decHashMap.put(67, x67t);
			decHashMap.put(68, x68t);
			decHashMap.put(69, x69t);
			decHashMap.put(70, x70t);
			decHashMap.put(71, x71t);
			decHashMap.put(72, x72t);
			decHashMap.put(73, x73t);
			decHashMap.put(74, x74t);
			decHashMap.put(75, x75t);
			decHashMap.put(76, x76t);
			decHashMap.put(77, x77t);
			decHashMap.put(78, x78t);
			decHashMap.put(79, x79t);
			decHashMap.put(80, x80t);
			decHashMap.put(81, x81t);
			decHashMap.put(82, x82t);
			decHashMap.put(83, x83t);
			decHashMap.put(84, x84t);
			decHashMap.put(85, x85t);
			decHashMap.put(86, x86t);
			decHashMap.put(87, x87t);
			decHashMap.put(88, x88t);
			decHashMap.put(89, x89t);
			decHashMap.put(90, x90t);
			decHashMap.put(91, x91t);
			decHashMap.put(92, x92t);
			decHashMap.put(93, x93t);
			decHashMap.put(94, x94t);
			decHashMap.put(95, x95t);
			decHashMap.put(96, x96t);
			decHashMap.put(97, x97t);
			decHashMap.put(98, x98t);
			decHashMap.put(99, x99t);
		}
		return decHashMap.get(i);
	}

	/**
	 * combines two tilemasks and creates a new one out of them applying OR to
	 * all pixel data
	 * 
	 * @param mask1
	 * @param mask2
	 * @return
	 */
	public static Tilemask combineTilemasks(Tilemask mask1, Tilemask mask2)
	{
		String bin1 = mask1.getBinaryData();
		String bin2 = mask2.getBinaryData();
		String ret = "";
		for (int i = 0; i < bin1.length(); i++)
		{
			if (bin1.charAt(i) != ' ' || bin2.charAt(i) != ' ')
			{
				ret += 'x';
			}
			else
			{
				ret += ' ';
			}
		}
		return new Tilemask(ret);
	}
}
