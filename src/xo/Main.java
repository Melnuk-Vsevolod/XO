package xo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[3][3];

		if (random() == -1) {// ������ ��� ���� ������
			playerMove(arr, sc);
			System.out.println("�� �������");
			print(arr);
		}

		int move = 0;// ���� �� ����� �� �����

		while (isRun(arr)) {
			boolean CPUMove = true;
			if (move == 0) {// ��� ��� 1 ���� �����

				if (firstCpuMove(arr)) {// �������� �� ������ // �������� ����
										// ������ �� ������ � ���
					arr[1][1] = 10;
					move = 1;
				} else {
					arr[0][0] = 10;
					move = 1;
				}

			} else {// ��� �� ������ ��� �����

				int winChanceCPU = CpuCanWin(arr);
				System.out.println("winChanceCPU" + winChanceCPU);

				if ((winChanceCPU) != 0) {// � ��������� ��� ����� ���
					findCPULastMove(arr, winChanceCPU);// ���� ������ �������
														// // �������� ���
					CPUMove = false;

				} else if ((winChanceCPU) == 0) {// ���� ���� ���������� ���
													// �����, �� ��������� �� �
													// //
					// ��������� ��� ������
					int winChancePlayer = PlayerCanWin(arr);
					System.out.println("winChancePlayer" + winChancePlayer);
					if ((winChancePlayer) != 0) {// � ��������� ��� ������ ���
						findPlayerLastMove(arr, winChancePlayer);// ����� //
																	// ���������//
																	// ��� //
																	// ������ �
																	// ������
						CPUMove = false;
					} else if ((winChancePlayer) == 0) {
						if (CPUMove) {
							int corner = findCorner(arr);// ������ � ����� ���
							if (corner == 0) {
								System.out.println("�� ���� �����");
								findFreeCell(arr);
								CPUMove = false;
							}
						}
					}
				}

			} // ���� ������ ���

			System.out.println("���� �������");
			print(arr);

			if (isRun(arr)) {
				playerMove(arr, sc);
				System.out.println("�� �������");
				print(arr);
			}

		}

		// ---------------��� ���������� ���������� ����������--------//

		if (draw(arr)) {
			System.out.println("ͳ���");
		}
		if (win(arr) == -3) {
			System.out.println("�� ������!");
		}
		if (win(arr) == 30) {
			System.out.println("�� �������!");
		}

		// test(arr);
		// System.out.println(win(arr));

	} // END of Main

	public static int scan(Scanner sc) { // ������� ��� ������
		return (sc.nextInt() - 1);
	}

	public static void print(int[][] arr) { // ������������ ���� � � � �
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

	public static int random() { // ������� 1 ��� -1
		double rand = (Math.random());
		if (rand < 0.5) {
			return -1;
		} else {
			return 1;
		}
	}

	public static boolean draw(int[][] arr) { // �������� ����, ���� ���

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static int win(int[][] arr) { // �������� �� ����� ������

		// ---------------�������� �� ����������--------//

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				sum += arr[i][j];
				if ((sum == -3) || (sum == 30)) {
					return sum;
				}
			}
		}

		// ------------------�������� �� ��������------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];
				if ((sum == -3) || (sum == 30)) {
					return sum;
				}
			}
		}

		// ------------------�������� �� �������------------//

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

		return 0;// ���� �� ������
	}

	public static boolean isRun(int[][] arr) {// �������� �� ��� �� ����������
		if (draw(arr)) { // ����
			return false;
		}
		if (win(arr) != 0) { // ����� ������
			return false;
		}
		return true;
	}

	public static boolean firstCpuMove(int[][] arr) {// �������� �� ������
		// �������� � �������
		// true ���� ������
		if (arr[1][1] == 0) {
			return true;
		}
		return false;
	}

	public static int CpuCanWin(int[][] arr) { // �������� �� ���� ����
												// �������
												// ��������� �����

		// ---------------�������� �� ����������--------//

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				sum += arr[i][j];

			}
			if (sum == 20) {
				return i;
			}
		}

		// ------------------�������� �� ��������------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];

			}
			if (sum == 20) {
				return (j + 1) * 10;
			}
		}

		// ------------------�������� �� �������------------//

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

		return 0;// ���������� ���������� ��� ����� ����
	}

	public static void findCPULastMove(int[][] arr, int win) { // �����
																// ��������
																// ������

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

	public static int PlayerCanWin(int[][] arr) { // �������� �� ���� �����
		// �������
		// ��������� �����

		// ---------------�������� �� ����������--------//

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				sum += arr[i][j];
			}
			if (sum == -2) {
				return i + 1;
			}
		}

		// ------------------�������� �� ��������------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];
			}
			if (sum == -2) {
				return (j + 1) * 10;
			}
		}

		// ------------------�������� �� �������------------//

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

		return 0;// ���������� ���������� ��� ������ ����
	}

	public static void findPlayerLastMove(int[][] arr, int win) { // �����
		// ��������
		// ������

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

	public static int findCorner(int[][] arr) { // ������ � ������ ���
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

	public static void playerMove(int[][] arr, Scanner sc) {// ��� ������ �
															// �������� ��
		// ����������� ����
		boolean isNotEmpty = true;

		while (isNotEmpty) { // ���� ������� �� �����, �� ��� ������� ���
								// ����������
			System.out.println("enter  Y");
			int x = scan(sc);
			System.out.println("enter  X");
			int y = scan(sc);

			if ((x >= 0) && (y >= 0) && (x < 3) && (y < 3)) {

				if (arr[x][y] == 0) {
					arr[x][y] = -1;
					System.out.println("������� ������");
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

