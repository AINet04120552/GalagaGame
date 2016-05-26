package galagaGame;

public class AlienSprite extends Sprite{
	private GalagaGame game;
	
	public AlienSprite(GalagaGame game, Image imgae, int x, int y){
		super(imgae, x, y);
		this.game = game;
		dx = -3; // 초기에는 왼쪽으로 이동한다.
	}
	
	@Override
	public void move(){	//외계인 우주선이 경게에 닿으면 방향을 바꾸고 한 칸 밑으로 내려오게 한다.
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 800))) {
			dx = -dx;
			y += 10;
			if (y > 600){
				game.endGame();
			}
		}
	super.move();
	}
}
