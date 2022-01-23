import java.util.*;

interface Running {
	void cls();
	void render();
	int empty();
	void player();
	void enemy();
	char winner();
	void start();
}
class Game implements Running {
	char [][] matrix = {
		{' ',' ',' '},
		{' ',' ',' '},
		{' ',' ',' '}
	};
	Scanner sc = new Scanner(System.in);
	Random ran = new Random();
	public void cls() {
		try {
			new ProcessBuilder( "cmd","/c","cls" ).inheritIO().start().waitFor();
		} catch( Exception e) {
			System.out.println(e);
		}
	}
	//Comment
	public int empty() {
		int free = 9;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrix[i][j] != ' ') {
					free--;
				}
			}
		}
		return free;
	}
	public void render() {
		System.out.printf("%c|%c|%c\n",matrix[0][0],matrix[0][1],matrix[0][2]);
		System.out.println("-+-+-");
		System.out.printf("%c|%c|%c\n",matrix[1][0],matrix[1][1],matrix[1][2]);
		System.out.println("-+-+-");
		System.out.printf("%c|%c|%c\n",matrix[2][0],matrix[2][1],matrix[2][2]);
		System.out.println();
	}
	public void player() {
		int x,y;
		System.out.print("X >> ");
		x = sc.nextInt();
		System.out.print("Y >> ");
		y = sc.nextInt();
		x--;
		y--;
		if (matrix[x][y] == ' ') {
			matrix[x][y] = 'X';
		} else if (matrix[x][y] != ' ') {
			System.out.println(">> INVALID!");
			player();
		}
	}
	//Hello
	public void enemy() {
		int x = 0,y = 0;

		while(empty() > 0) {
			x = ran.nextInt(3);
			y = ran.nextInt(3);

			if (matrix[x][y] == ' ') {
				break;
			}
		}
		matrix[x][y] = 'O';
	}
	public char winner() {
		/*
		 *00 01 02
		  10 11 12
		  20 21 22
		 * */
		if (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[0][0] != ' ')
			return matrix[0][0];
		if (matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[0][2] != ' ')
			return matrix[0][2];

		if (matrix[0][0] == matrix[0][1] && matrix[0][0] == matrix[0][2] && matrix[0][0] != ' ')
			return matrix[0][0];
		if (matrix[1][0] == matrix[1][1] && matrix[1][0] == matrix[1][2] && matrix[1][0] != ' ')
			return matrix[1][0];
		if (matrix[2][0] == matrix[2][1] && matrix[2][0] == matrix[2][2] && matrix[2][0] != ' ')
			return matrix[2][0];

		if (matrix[0][0] == matrix[1][0] && matrix[0][0] == matrix[2][0] && matrix[0][0] != ' ')
			return matrix[0][0];
		if (matrix[0][1] == matrix[1][1] && matrix[0][1] == matrix[2][1] && matrix[0][1] != ' ')
			return matrix[0][1];
		if (matrix[0][2] == matrix[1][2] && matrix[0][2] == matrix[2][2] && matrix[0][2] != ' ')
			return matrix[0][2];
		return 'D';
	}
	public void start() {
		boolean run = true;
		while(run) {
			cls();
			empty();
			enemy();
			render();
			if (empty() > 0) {
				player();
			} else if (empty() <= 0) {
				run = false;
			}
			if (winner() == 'D' && empty() == 0) {
				cls();
				render();
				System.out.println(">> NO WINNER!");
				run = false;
			} else if (winner() == 'X') {
				cls();
				render();
				System.out.println(">> X WINS!");
				run = false;
			} else if (winner() == 'O') {
				cls();
				render();
				System.out.println(">> O WINS!");
				run = false;
			}
		}
	}
} 

public class Main {
	public static void main(String [] args) {
		Game game = new Game();
		game.start();
	}
}
