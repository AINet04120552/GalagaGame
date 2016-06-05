package galagaGame;

import java.awt.Image;

public class StarShipSprite extends Sprite{	//Sprite 상속
	private GalagaGame game;	//게임판
	
	public StarShipSprite(GalagaGame game, Image image, int x, int y){	//생성자
		super(image, x, y);	//부모의 메소드를 가져와 사용
		this.game = game;	//게임판 설정
		dx = 0;	//x축 이동속도 설정
		dy = 0;	//y축 이동속도 설정
	}
	
	@Override
	public void move(){	//플레이어 우주선을 움직임
		if ((dx < 0) && (x < 10)){	//왼쪽으로 넘어가지 않게 해줌
			return;
		}
		if ((dx > 0) && (x > 800)) {	//오른쪽으로 넘어가지 않게 해줌
			return;
		}
		super.move();	//부모의 메소드를 가져와 사용
	}
	
	@Override
	public void handleCollision(Sprite other) {	//충돌을 다룬다
		if (other instanceof AlienSprite){	// 객체가 AlienSprite타입일 때 
			game.endGame();	//게임끝
		}
	}
}
