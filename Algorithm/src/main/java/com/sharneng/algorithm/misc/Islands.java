package com.sharneng.algorithm.misc;

import java.util.HashSet;
import java.util.Set;

public class Islands {
	public static void main(String[] args) {
		int[][] map = new int[][] { 
				{ 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 1, 1, 0, 0, 1, 0 }, 
				{ 0, 0, 0, 0, 0, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 1, 0, 1, 1, 0, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 1 }, 
				{ 0, 1, 0, 0, 1, 0, 0, 0 } };

		System.out.println(numberOfIslands1(map));
		System.out.println(numberOfIslands2(map));
		int[] result = getSumNum(new int[]{9,2,6,7,11,5,1}, 10);
		System.out.println(result.length);
		System.out.println(result[0] + "+" + result[1]);
	}

	public static int numberOfIslands1(int[][] map) {
		int number = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1 && (i == 0 || map[i - 1][j] == 0)
						&& (j == 0 || map[i][j - 1] == 0)) {
					number++;
				}
			}
		}

		return number;
	}

	public static int numberOfIslands2(int[][] map) {
		int number = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) {
					number++;
					turnToZero(map, i, j);
				}
			}
		}

		return number;
	}

	public static void turnToZero(int[][] map, int x, int y) {
		if (x >= map.length || x < 0 || y >= map[x].length || y < 0
				|| map[x][y] == 0)
			return;

		map[x][y] = 0;
		turnToZero(map, x + 1, y);
		turnToZero(map, x, y + 1);
		turnToZero(map, x - 1, y);
		turnToZero(map, x, y - 1);
	}

	public static int[] getSumNum(int[] a, int sum) {
		Set<Integer> b = new HashSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			int temp = sum - a[i];
			if (b.contains(temp)) {
				return new int[] { a[i], temp };
			}
			b.add(a[i]);
		}
		return new int[0];
	} 
}
