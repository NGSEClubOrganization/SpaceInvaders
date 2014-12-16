package com.ngse.spaceinvaders;

import java.util.*;

public class Storyboard {

	Random rnd = new Random();

	// Level 1 (t 500 - 1500)
	public static final double[] Level1Times = { 551, 551, 611, 611, 651, 651,
			711, 711, 751, 751, 811, 811, 851, 851, 911, 911, 951, 951, 1111,
			1111, 1211, 1211, 1211, 1211 };
	public static final double[] Level1Xs = { 1, 611, 1, 611, 1, 611, 1, 611,
			1, 611, 1, 611, 1, 611, 1, 611, 1, 611, 1, 611, 1, 1, 611, 611 };
	public static final double[] Level1Ys = { 111, 111, 111, 111, 111, 111,
			111, 111, 111, 111, 211, 211, 211, 211, 211, 211, 211, 211, 211,
			211, 1, 611, 1, 611 };
	public static final double[] Level1DXs = { 2, -2, 2, -2, 2, -2, 2, -2, 2,
			-2, 3, -3, 3, -3, 3, -3, 3, -3, 3, -3, 2, 2, 2, 2 };
	public static final double[] Level1DYs = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2 };
	public static final int[] Level1Types = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2 };

	// Level 2 (t 1500 - 2500)
	public static final double[] Level2Times = { 1600, 1600, 1650, 1650, 1700,
			1700, 1750, 1750, 1800, 1800, 1850, 1850, 1900, 1900, 1950, 1950,
			2000, 2000, 2050, 2050, 2100, 2100, 2150, 2150, 2200, 2200, 2250,
			2250, 2300, 2300, 2350, 2350, 2400, 2400 };
	public static final double[] Level2Xs = { 266, 498, 156, 385, 354, 204,
			249, 256, 120, 385, 583, 344, 562, 75, 124, 266, 170, 64, 484, 226,
			364, 569, 573, 565, 491, 399, 593, 507, 389, 413, 314, 465, 37, 168 };
	public static final double[] Level2Ys = { 582, 123, 511, 493, 441, 340,
			531, 396, 11, 302, 56, 177, 376, 297, 49, 383, 293, 174, 316, 598,
			43, 16, 426, 168, 386, 95, 337, 580, 74, 151, 556, 486, 378, 319 };
	public static final double[] Level2DXs = { 0, 2, 2, 2, 0, 0, 0, 2, 0, -2,
			-2, -2, -2, 0, 2, -1, 0, 1, -2, 0, 1, 1, 0, 1, 0, 0, 2, 1, 0, 0, 2,
			0, 0, 1 };
	public static final double[] Level2DYs = { 0, 0, 0, 2, 0, -2, -2, 0, -2, 1,
			2, 0, 0, 2, 2, 1, 0, 1, 2, -1, 0, -2, 0, 1, 0, -1, 0, -2, 0, 2, -1,
			-1, -1, 2 };
	public static final int[] Level2Types = { 2, 0, 1, 1, 2, 0, 0, 2, 0, 1, 0,
			2, 0, 0, 1, 0, 2, 1, 0, 0, 2, 1, 1, 0, 0, 1, 0, 0, 0, 2, 2, 2, 0, 2 };

	// Level 2 (second version)
	/*
	 * public static final double[] Level2Times = {1600, 1600, 1650, 1650, 1700,
	 * 1700, 1750, 1750, 1800, 1800, 1850, 1850, 1900, 1900, 1950, 1950, 2000,
	 * 2000, 2050, 2050, 2100, 2100, 2150, 2150, 2200, 2200, 2250, 2250, 2300,
	 * 2300, 2350, 2350, 2400, 2400}; public static final double[] Level2Xs =
	 * {Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600}; public
	 * static final double[] Level2Ys = {Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600, Math.abs(rnd.nextInt()) % 600,
	 * Math.abs(rnd.nextInt()) % 600}; public static final double[] Level2DXs =
	 * {rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() %
	 * 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt()
	 * % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3,
	 * rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() %
	 * 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt()
	 * % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3,
	 * rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() %
	 * 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt()
	 * % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3,
	 * rnd.nextInt() % 3}; public static final double[] Level2DYs =
	 * {rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() %
	 * 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt()
	 * % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3,
	 * rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() %
	 * 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt()
	 * % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3,
	 * rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() %
	 * 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt()
	 * % 3, rnd.nextInt() % 3, rnd.nextInt() % 3, rnd.nextInt() % 3,
	 * rnd.nextInt() % 3}; public static final int[] Level2Types =
	 * {Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3,
	 * Math.abs(rnd.nextInt()) % 3, Math.abs(rnd.nextInt()) % 3};
	 */

}
