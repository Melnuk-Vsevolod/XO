package xo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[3][3];

		if (random() == -1) {// ������ ��� ���� ������
			System.out.println("enter  X");
			int x = scan(sc);
			System.out.println("enter  Y");
			int y = scan(sc);

			arr[x][y] = -1;
			print(arr);

		}
		int move = 0;// ���� �� ����� �� �����
		boolean CPUMove = true;

		while (isRun(arr)) {
			if (move == 0) {// ��� ��� 1 ���� �����
				if (firstCpuMove(arr)) {// �������� �� ������
					// �������� ���� ������ �� ������ � ���
					arr[1][1] = 1;
				} else {
					arr[0][0] = 1;
				}
				move = 1;
			} else {// ��� �� ������ ��� �����

			}
			System.out.println("enter  X");
			int x = scan(sc);
			System.out.println("enter  Y");
			int y = scan(sc);

			arr[x][y] = -1;
			print(arr);

		}

		// ---------------��� ���������� ���������� ����������--------//

		if (draw(arr)) {
			System.out.println("ͳ���");
		}
		if (win(arr) == -3) {
			System.out.println("�� ������!");
		}
		if (win(arr) == 3) {
			System.out.println("�� �������!");
		}

		// test(arr);
		// System.out.println(win(arr));

	}

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
				if ((sum == -3) || (sum == 3)) {
					return sum;
				}
			}
		}

		// ------------------�������� �� ��������------------//

		for (int j = 0; j < arr.length; j++) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i][j];
				if ((sum == -3) || (sum == 3)) {
					return sum;
				}
			}
		}

		// ------------------�������� �� �������------------//

		int sum = 0;
		sum = (arr[0][0]) + (arr[1][1]) + (arr[2][2]);
		if ((sum == -3) || (sum == 3)) {
			return sum;
		}

		sum = 0;
		sum = (arr[0][2]) + (arr[1][1]) + (arr[2][0]);
		if ((sum == -3) || (sum == 3)) {
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

