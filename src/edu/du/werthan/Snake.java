package edu.du.werthan;
import java.util.Random; 
import edu.du.dudraw.DUDraw;
public class Snake {
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	private static final int easy = 0;
	private static final int hard = 1;
	private static final int playAgain = 0;
	private static final int exit = 1;
	private int directionState = UP;
	private DLL snake;
	private int startingHX = 10;
	private int startingHY = 10;
	private int startingTX = 10;
	private int startingTY = 9;
	private int startingFX = 25;
	private int startingFY = 25;
	private int[] headPos = {this.startingHX, this.startingHY};
	private int[] tailPos = {this.startingTX, this.startingTX};
	private int[] foodPos = {this.startingFX, this.startingFX};
	private int startMode;
	private int lossMode;
	public Snake() {
		initializeGame(1);
	}
	public void update() {
		DUDraw.clear();
		updatePos();
		updateDirection();
		addLink();
		checkLoss();
		drawUI();
		drawSnake();
		drawFood();
		drawScore();
		DUDraw.pause(60);
	}
	public void updatePos() {
		Node current = snake.getTail();
		int[] newPos;
		if(directionState == UP) {
			while(current != null) {
				newPos = current.getVal();
				if(current == snake.getHead()) {
					if(newPos[1] + 1 > 50) {
						newPos[1] = 0;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					} else {
						newPos[1] = newPos[1]+1;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					}
				} else {
					newPos = current.getPrev().getVal();
					current.setPos(newPos[0], newPos[1]);
					current = current.getPrev();
				}
			}
		} else if(directionState == LEFT) {
			while(current != null) {
				newPos = current.getVal();
				if(current == snake.getHead()) {
					if(newPos[0] - 1 < 0) {
						newPos[0] = 50;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					} else {
						newPos[0] = newPos[0]-1;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					}
				} else {
					newPos = current.getPrev().getVal();
					current.setPos(newPos[0], newPos[1]);
					current = current.getPrev();
				}
			}
		} else if(directionState == DOWN) {
			while(current != null) {
				newPos = current.getVal();
				if(current == snake.getHead()) {
					if(newPos[1] - 1 < 0) {
						newPos[1] = 50;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					} else {
						newPos[1] = newPos[1] - 1;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					}
				} else {
					newPos = current.getPrev().getVal();
					current.setPos(newPos[0], newPos[1]);
					current = current.getPrev();
				}
			}
		} else {
			while(current != null) {
				newPos = current.getVal();
				if(current == snake.getHead()) {
					if(newPos[0] + 1 > 50) {
						newPos[0] = 0;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					} else {
						newPos[0] = newPos[0]+1;
						current.setPos(newPos[0], newPos[1]);
						current = current.getPrev();
					}
				} else {
					newPos = current.getPrev().getVal();
					current.setPos(newPos[0], newPos[1]);
					current = current.getPrev();
				}
			}
		}
	}
	public void updateDirection() {
		if(DUDraw.isKeyPressed(87)) {
			this.directionState = UP;
		}
		if(DUDraw.isKeyPressed(65)) {
			this.directionState = LEFT;
		}
		if(DUDraw.isKeyPressed(83)) {
			this.directionState = DOWN;
		}
		if(DUDraw.isKeyPressed(68)) {
			this.directionState = RIGHT;
		}
	}
	public void addLink() {
		int[] newPos = snake.getHead().getVal();
		if(snake.getHead().getVal()[0] == this.foodPos[0] && snake.getHead().getVal()[1] == this.foodPos[1]) {
			snake.addFirst(newPos);
			updateHeadPos(); 
			updateFood();
		}
	}
	public void checkLoss() {
		int[] headVal = snake.getHead().getVal();
		Node current = snake.getHead().getNext();
		if( this.startMode == easy ) {
			while(current != null) {
					if( (headVal[0] == current.getVal()[0] 
							&& headVal[1] == current.getVal()[1] ) ) {
						DUDraw.pause(1000);
						Menu lossMenu = new Menu(0);
						this.lossMode = lossMenu.lossMode();
						if(this.lossMode == playAgain) {
							initializeGame(playAgain);
						} else if(this.lossMode == exit) {
							initializeGame(exit);
						}
					}
				current = current.getNext();
			}
		} else {
			while(current != null) {
				if( (headVal[0] == current.getVal()[0] 
						&& headVal[1] == current.getVal()[1] ) ) {
					DUDraw.pause(1000);
					Menu lossMenu = new Menu(0);
					this.lossMode = lossMenu.lossMode();
					if(this.lossMode == playAgain) {
						initializeGame(0);
					} else if(this.lossMode == exit) {
						initializeGame(1);
					}
				}
				current = current.getNext();
			}
			if(( headVal[0] > 49 || headVal[0] < 1 ) 
								|| (headVal[1] > 49 || headVal[1] < 1)) {
				DUDraw.pause(1000);
				Menu lossMenu = new Menu(0);
				this.lossMode = lossMenu.lossMode();
				if(this.lossMode == playAgain) {
					initializeGame(0);
				} else if(this.lossMode == exit){
					initializeGame(1);
				}
			}				
		}
	}
	public void drawSnake() {
		drawFood();
		Node current = snake.getHead();
		while(current != null) {
			int[] pos = current.getVal();
			if(current == snake.getHead()) {
				DUDraw.setPenColor(DUDraw.RED);
			}else {
				DUDraw.setPenColor(DUDraw.GREEN);
			}
			DUDraw.filledSquare(pos[0], pos[1], 0.5);
			current = current.getNext();
		}
		DUDraw.setPenColor();
	}
	public void initializeGame(int n) {
		snake = new DLL();
		this.headPos[0] = this.startingHX;
		this.headPos[1] = this.startingHY;
		this.tailPos[0] = this.startingTX;
		this.tailPos[1] = this.startingTY;
		this.foodPos[0] = this.startingFX;
		this.foodPos[1] = this.startingFY;
		this.directionState = UP;
		nodeInitialization();
		drawInitialization();
		if(n == 1) {
			Menu startMenu = new Menu(1);
			this.startMode = startMenu.startMode();
		}
	}
	public void nodeInitialization() {
		snake.setHead(new Node(null, headPos, null));
		snake.setTail(new Node(snake.getHead(), tailPos, null));
		snake.getHead().setNext(snake.getTail());
	}
	public void drawInitialization() {
		DUDraw.setCanvasSize(500, 500);
		DUDraw.setScale(0, 50);
		DUDraw.enableDoubleBuffering();
	}
	public void drawFood() {
		DUDraw.setPenColor(DUDraw.BLUE);
		DUDraw.filledSquare(this.foodPos[0], this.foodPos[1], 0.5);
		DUDraw.setPenColor();
	}
	public void updateHeadPos() {
		if(directionState == UP) {
			int[] headVal = {snake.getHead().getVal()[0], snake.getHead().getVal()[1] + 1};
			snake.getHead().setVal(headVal);
		} else if(directionState == LEFT) {
			int[] headVal = {snake.getHead().getVal()[0] - 1, snake.getHead().getVal()[1]};
			snake.getHead().setVal(headVal);
		} else if(directionState == DOWN) {
			int[] headVal = {snake.getHead().getVal()[0], snake.getHead().getVal()[1] - 1};
			snake.getHead().setVal(headVal);
		} else {
			int[] headVal = {snake.getHead().getVal()[0] + 1, snake.getHead().getVal()[1]};
			snake.getHead().setVal(headVal);
		}
	}
	public void updateFood() {
		Random r = new Random();
		this.foodPos[0] = r.nextInt(50);
		this.foodPos[1] = r.nextInt(50);
	}
	public void drawUI() {
		if(this.startMode == hard) {
			DUDraw.setPenColor(DUDraw.BLACK);
			DUDraw.square(25, 25, 24);;
		} 
	}
	public void drawScore() {
		Integer score = snake.size();
		DUDraw.text(25, 45, score.toString());
	}
	private class Menu {
		private static final int snakeLoss = 0;
		private static final int easy = 0;
		private static final int hard = 1;
		private static final int playAgain = 0;
		private static final int startMenu = 1;
		private int myStartMode;
		private int myLossMode;
		private int menuType;
		
		public Menu(int type) {
			this.menuType = type;
			if(this.menuType == snakeLoss) {
				lossMenu();
			} else {
				startMenu();
			}
		}
		
		public void startMenu(){
			int pressedX;
			int pressedY;
			boolean selected = false;
			DUDraw.pause(1000);
			drawMenu("EASY", "HARD", "Snake");
			while(!selected) {
				if(DUDraw.isMousePressed()){
					pressedX = (int) DUDraw.mouseX();
					pressedY = (int) DUDraw.mouseY();
					if( (pressedX > 15 && pressedX < 35) &&  
							(pressedY > 23 && pressedY < 27)) {
						this.myStartMode = easy;
						selected = true;
					} else if ( (pressedX > 15 && pressedX < 35) &&  
							(pressedY > 15 && pressedY < 19) ) {
						this.myStartMode = hard;
						selected = true;
					}
				}
			}
		}
		public void lossMenu(){
			int pressedX;
			int pressedY;
			boolean selected = false;
			drawMenu("Play Again", "Exit to Menu", "You Lose!");
			while(!selected) {
				if(DUDraw.isMousePressed()){
					pressedX = (int) DUDraw.mouseX();
					pressedY = (int) DUDraw.mouseY();
					if( (pressedX > 15 && pressedX < 35) &&  
							(pressedY > 23 && pressedY < 27)) {
						this.myLossMode = playAgain;
						selected = true;
					} else if ( (pressedX > 15 && pressedX < 35) &&  
							(pressedY > 15 && pressedY < 19) ) {
						this.myLossMode = startMenu;
						selected = true;
					}
				}
			}
		}
		public void drawMenu(String gText, String rText, String text) {
			DUDraw.clear();
			DUDraw.text(25, 35, text);
			DUDraw.setPenColor(DUDraw.GREEN);
			DUDraw.filledRectangle(25, 25, 10, 2);
			DUDraw.setPenColor(DUDraw.BLACK);
			DUDraw.text(25, 25, gText);
			DUDraw.rectangle(25, 25, 10, 2);
			DUDraw.setPenColor(DUDraw.RED);
			DUDraw.filledRectangle(25, 17, 10, 2);
			DUDraw.setPenColor(DUDraw.BLACK);
			DUDraw.text(25, 17, rText);
			DUDraw.rectangle(25, 17, 10, 2);
			DUDraw.show();
		}
		public int startMode() {
			return this.myStartMode;
		}
		public int lossMode() {
			return this.myLossMode;
		}
	}
}