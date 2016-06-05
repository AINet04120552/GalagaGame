package galagaGame;

import java.awt.Image;

public class AlienSprite extends Sprite{	//Sprite 상속 
	private GalagaGame game;	//게임판
	
	public AlienSprite(GalagaGame game, Image imgae, int x, int y){	//생성자
		super(imgae, x, y);	//부모의 메소드를 가져와 사용
		this.game = game;	//게임판 설정
		dx = -3; // 초기에는 왼쪽으로 이동한다.
	}
	
	@Override
	public void move(){	//외계인 우주선 움직임
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 800))) { //왼쪽이나 오른쪽 벽에 닿으면 한칸 밑으로 내려감
			dx = -dx;	//x축 이동방향을 반대로 바꿈
			y += 10;	//y축을 증가시켜 아래로 내려보냄
			if (y > 600){	// 아래쪽 벽에 닿으면 게임오버
				game.endGame();
			}
		}
	super.move();	//부모 메소드 사용
	}
}
