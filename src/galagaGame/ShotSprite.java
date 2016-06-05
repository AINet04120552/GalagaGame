package galagaGame;

import java.awt.Image;

public class ShotSprite extends Sprite{	//Sprite 상속
	private GalagaGame game;	//게임판
	
	public ShotSprite(GalagaGame game, Image image, int x, int y){	//생성자
		super(image, x, y);	//부모의 메소드 사용
		this.game = game;	//게임판 설정
		dy = -3;	//총알의 y축 속도
	}
	
	@Override
	public void move() {	//총알을 움직임
		super.move();	//부모의 메소드 사용
		if (y < -100){	//-100보다 작을 때
			game.removeSprite(this);	//총알 지움
		}
	}
	
	@Override
	public void handleCollision(Sprite other){	//충돌을 다룬다
		if (other instanceof AlienSprite){	//객체가 AlienSprite일 때
			game.removeSprite(this);	//총알 지움
			game.removeSprite(other);	//외계인 지움
		}
	}
}
