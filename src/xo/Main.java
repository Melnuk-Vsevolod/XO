package xo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[3][3];

		print(arr);
		// test(arr);
		// System.out.println(win(arr));

	}

	public static int scan(Scanner sc) { // считати хід гравця
		return (sc.nextInt() - 1);
	}

	public static void print(int[][] arr) { // роздрукувати поле з Х і О
		for (int i = 0; i < arr.length; i++) {
			System.out.println("-------");
			System.out.print("|");
			for (int j = 0; j < arr.length; j++) {
				String str = "";
				if (arr[i][j] == 0) {
					str = " ";
				} else if (arr[i][j] == -1) {
					str = "X";
				} else {
					str = "O";
				}
				System.out.print(str + "|");
			}
			System.out.println("");
		}
		System.out.println("-------");
	}

	public static int random() { // повертає 1 або -1
		double rand = (Math.random());
		if (rand < 0.5) {
			return -1;
		} else {
			return 1;
		}
	}

	public static boolean draw(int[][] arr) { // перевірка нічия, якщо так
												// повертає true

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static int win(int[][] arr) { // перевірка чи хтось виграв

		// ---------------перевірка по горизонталі--------//

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				sum += arr[i][j];
				if ((sum == -3) || (sum == -3)) {
					return sum;
				}
			}
		}

		// ------------------перевірка по вертикалі------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];
				if ((sum == -3) || (sum == -3)) {
					return sum;
				}
			}
		}

		// ------------------перевірка по діагоналі------------//

		int sum = 0;
		sum = (arr[0][0]) + (arr[1][1]) + (arr[2][2]);
		if ((sum == -3) || (sum == -3)) {
			return sum;
		}

		sum = 0;
		sum = (arr[0][2]) + (arr[1][1]) + (arr[2][0]);
		if ((sum == -3) || (sum == -3)) {
			return sum;
		}

		return 0;// ніхто не виграв
	}

	public static boolean isRun(int[][] arr) {// перевірка чи гра не закінчилась
		if (draw(arr)) { // нічия
			return false;
		}
		if (win(arr) != 0) { // хтось виграв
			return false;
		}
		return true;
	}

}

// public static void test(int[][] arr) {
//
// for (int i = 0; i < arr.length; i++) {
// for (int j = 0; j < arr.length; j++) {
// arr[i][j] = -1;
// }
// }
//
// }

