package xo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[3][3];

		if (random() == -1) {// перший хід буде гравця
			playerMove(arr, sc);
			System.out.println("Ти походив");
			print(arr);
		}

		int move = 0;// комп ще ніразу не ходив

		while (isRun(arr)) {
			boolean CPUMove = true;
			if (move == 0) {// код для 1 ходу компа

				if (firstCpuMove(arr)) {// перевіряє чи занята // середина якщо
										// занята то ставим в кут
					arr[1][1] = 10;
					move = 1;
				} else {
					arr[0][0] = 10;
					move = 1;
				}

			} else {// вже не перший хід компа

				int winChanceCPU = CpuCanWin(arr);
				System.out.println("winChanceCPU" + winChanceCPU);

				if ((winChanceCPU) != 0) {// є виграшний для компа хід
					findCPULastMove(arr, winChanceCPU);// комп робить останній
														// // вигашний хід
					CPUMove = false;

				} else if ((winChanceCPU) == 0) {// якщо нема виграшного для
													// компа, то перевіряєм чи є
													// //
					// виграшний для ігрока
					int winChancePlayer = PlayerCanWin(arr);
					System.out.println("winChancePlayer" + winChancePlayer);
					if ((winChancePlayer) != 0) {// є виграшний для іграка хід
						findPlayerLastMove(arr, winChancePlayer);// шукаєм //
																	// виграшний//
																	// хід //
																	// іграка і
																	// блокуєм
						CPUMove = false;
					} else if ((winChancePlayer) == 0) {
						if (CPUMove) {
							int corner = findCorner(arr);// ставим в любий кут
							if (corner == 0) {
								System.out.println("Всі кути заняті");
								findFreeCell(arr);
								CPUMove = false;
							}
						}
					}
				}

			} // комп зробив хід

			System.out.println("Комп походив");
			print(arr);

			if (isRun(arr)) {
				playerMove(arr, sc);
				System.out.println("Ти походив");
				print(arr);
			}

		}

		// ---------------гра закінчилась визначення результату--------//

		if (draw(arr)) {
			System.out.println("Нічия");
		}
		if (win(arr) == -3) {
			System.out.println("Ти ВИГРАВ!");
		}
		if (win(arr) == 30) {
			System.out.println("Ти програв!");
		}

		// test(arr);
		// System.out.println(win(arr));

	} // END of Main

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
				if ((sum == -3) || (sum == 30)) {
					return sum;
				}
			}
		}

		// ------------------перевірка по вертикалі------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];
				if ((sum == -3) || (sum == 30)) {
					return sum;
				}
			}
		}

		// ------------------перевірка по діагоналі------------//

		int sum = 0;
		sum = (arr[0][0]) + (arr[1][1]) + (arr[2][2]);
		if ((sum == -3) || (sum == 30)) {
			return sum;
		}

		sum = 0;
		sum = (arr[0][2]) + (arr[1][1]) + (arr[2][0]);
		if ((sum == -3) || (sum == 30)) {
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

	public static boolean firstCpuMove(int[][] arr) {// перевіряє чи занята
		// середина і повертає
		// true якщо занята
		if (arr[1][1] == 0) {
			return true;
		}
		return false;
	}

	public static int CpuCanWin(int[][] arr) { // перевіряє чи може комп
												// виграти
												// наступним ходом

		// ---------------перевірка по горизонталі--------//

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				sum += arr[i][j];

			}
			if (sum == 20) {
				return i;
			}
		}

		// ------------------перевірка по вертикалі------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];

			}
			if (sum == 20) {
				return (j + 1) * 10;
			}
		}

		// ------------------перевірка по діагоналі------------//

		int sum = 0;
		sum = (arr[0][0]) + (arr[1][1]) + (arr[2][2]);
		if (sum == 20) {
			return 100;
		}

		sum = 0;
		sum = (arr[0][2]) + (arr[1][1]) + (arr[2][0]);
		if (sum == 20) {
			return 200;
		}

		return 0;// наступного виграшного для компа немає
	}

	public static void findCPULastMove(int[][] arr, int win) { // шукаєм
																// виграшну
																// ячейку

		switch (win) {
		case 0:
			if (arr[0][0] == 0) {
				arr[0][0] = 10;
			}
			if (arr[0][1] == 0) {
				arr[0][1] = 10;
			}
			if (arr[0][2] == 0) {
				arr[0][2] = 10;
			}
			break;
		case 1:
			if (arr[1][0] == 0) {
				arr[1][0] = 10;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
			}
			if (arr[1][2] == 0) {
				arr[1][2] = 10;
			}
			break;
		case 2:
			if (arr[2][0] == 0) {
				arr[2][0] = 10;
			}
			if (arr[2][1] == 0) {
				arr[2][1] = 10;
			}
			if (arr[2][2] == 0) {
				arr[2][2] = 10;
			}
			break;

		case 10:
			if (arr[0][0] == 0) {
				arr[0][0] = 10;
			}
			if (arr[1][0] == 0) {
				arr[1][0] = 10;
			}
			if (arr[2][0] == 0) {
				arr[2][0] = 10;
			}
			break;

		case 20:
			if (arr[0][1] == 0) {
				arr[0][1] = 10;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
			}
			if (arr[2][1] == 0) {
				arr[2][1] = 10;
			}
			break;

		case 30:
			if (arr[0][2] == 0) {
				arr[0][2] = 10;
			}
			if (arr[1][2] == 0) {
				arr[1][2] = 10;
			}
			if (arr[2][2] == 0) {
				arr[2][2] = 10;
			}
			break;

		case 100:
			if (arr[0][0] == 0) {
				arr[0][0] = 10;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
			}
			if (arr[2][2] == 0) {
				arr[2][2] = 10;
			}
			break;

		case 200:
			if (arr[0][2] == 0) {
				arr[0][2] = 10;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
			}
			if (arr[2][0] == 0) {
				arr[2][0] = 10;
			}
			break;

		}

	}

	public static int PlayerCanWin(int[][] arr) { // перевіряє чи може ігрок
		// виграти
		// наступним ходом

		// ---------------перевірка по горизонталі--------//

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				sum += arr[i][j];
			}
			if (sum == -2) {
				return i + 1;
			}
		}

		// ------------------перевірка по вертикалі------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];
			}
			if (sum == -2) {
				return (j + 1) * 10;
			}
		}

		// ------------------перевірка по діагоналі------------//

		int sum = 0;
		sum = (arr[0][0]) + (arr[1][1]) + (arr[2][2]);
		if (sum == -2) {
			return 100;
		}

		sum = 0;
		sum = (arr[0][2]) + (arr[1][1]) + (arr[2][0]);
		if (sum == -2) {
			return 200;
		}

		return 0;// наступного виграшного для іграка немає
	}

	public static void findPlayerLastMove(int[][] arr, int win) { // шукаєм
		// виграшну
		// ячейку

		switch (win) {
		case 1:
			if (arr[0][0] == 0) {
				arr[0][0] = 10;
				break;
			}
			if (arr[0][1] == 0) {
				arr[0][1] = 10;
				break;
			}
			if (arr[0][2] == 0) {
				arr[0][2] = 10;
				break;
			}
			break;
		case 2:
			if (arr[1][0] == 0) {
				arr[1][0] = 10;
				break;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
				break;
			}
			if (arr[1][2] == 0) {
				arr[1][2] = 10;
				break;
			}
			break;
		case 3:
			if (arr[2][0] == 0) {
				arr[2][0] = 10;
				break;
			}
			if (arr[2][1] == 0) {
				arr[2][1] = 10;
				break;
			}
			if (arr[2][2] == 0) {
				arr[2][2] = 10;
				break;
			}
			break;

		case 10:
			if (arr[0][0] == 0) {
				arr[0][0] = 10;
				break;
			}
			if (arr[1][0] == 0) {
				arr[1][0] = 10;
				break;
			}
			if (arr[2][0] == 0) {
				arr[2][0] = 10;
				break;
			}
			break;

		case 20:
			if (arr[0][1] == 0) {
				arr[0][1] = 10;
				break;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
				break;
			}
			if (arr[2][1] == 0) {
				arr[2][1] = 10;
				break;
			}
			break;

		case 30:
			if (arr[0][2] == 0) {
				arr[0][2] = 10;
				break;
			}
			if (arr[1][2] == 0) {
				arr[1][2] = 10;
				break;
			}
			if (arr[2][2] == 0) {
				arr[2][2] = 10;
				break;
			}
			break;

		case 100:
			if (arr[0][0] == 0) {
				arr[0][0] = 10;
				break;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
				break;
			}
			if (arr[2][2] == 0) {
				arr[2][2] = 10;
				break;
			}
			break;

		case 200:
			if (arr[0][2] == 0) {
				arr[0][2] = 10;
				break;
			}
			if (arr[1][1] == 0) {
				arr[1][1] = 10;
				break;
			}
			if (arr[2][0] == 0) {
				arr[2][0] = 10;
				break;
			}
			break;

		}

	}

	public static int findCorner(int[][] arr) { // ставим в пустий кут
		if (arr[0][0] == 0) {
			arr[0][0] = 10;
			return 1;
		}
		if (arr[0][2] == 0) {
			arr[0][2] = 10;
			return 1;
		}
		if (arr[2][0] == 0) {
			arr[2][0] = 10;
			return 1;
		}
		if (arr[2][2] == 0) {
			arr[2][2] = 10;
			return 1;
		}
		return 0;
	}

	public static void playerMove(int[][] arr, Scanner sc) {// хід іграка і
															// перевірка на
		// правильність ходу
		boolean isNotEmpty = true;

		while (isNotEmpty) { // якщо клітинка не пуста, то хай ввдоить нові
								// координати
			System.out.println("enter  Y");
			int x = scan(sc);
			System.out.println("enter  X");
			int y = scan(sc);

			if ((x >= 0) && (y >= 0) && (x < 3) && (y < 3)) {

				if (arr[x][y] == 0) {
					arr[x][y] = -1;
					System.out.println("Клітинка занята");
					isNotEmpty = false;
				}
			}
		}
	}

	public static void findFreeCell(int[][] arr) {
		boolean iterate = true;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if ((arr[i][j] == 0) && (iterate)) {
					arr[i][j] = 10;
					i = 2;
					j = 2;
					iterate = false;

				}

			}

		}
	}

} // END of CLASS

// public static void test(int[][] arr) {
//
// for (int i = 0; i < arr.length; i++) {
// for (int j = 0; j < arr.length; j++) {
// arr[i][j] = -1;
// }
// }
//
// }

