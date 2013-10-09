package com.raysmond.util;

public class Util {
	
	public static int getMin(int[] a) {
		int min = Integer.MAX_VALUE;
		for (int i : a) {
			if (i < min)
				min = i;
		}
		return min;
	}

	public static int getMax(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int i : a) {
			if (i > max)
				max = i;
		}
		return max;
	}
}
