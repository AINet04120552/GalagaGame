package galagaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {	//화면 표시에서 반복 이용되는 도형의 무늬를 등록하고, 그 무늬에 다른 그림을 겹쳐서 합성시키는 기능. 고속 표시가 가능하며 연속되는 그림을 매끄럽게 움직일 수 있다.
	protected int x;	//현재 위치 x좌표 
	protected int y;	//현재 위치 y좌표
	protected int dx;	//단위시간에 움직이는 x방향 거리 
	protected int dy;	//단위시간에 움직이는 y방향 거리
	private Image image;	//스프라이트가 가지고 있는 이미지
	
	public Sprite(Image image, int x, int y){	//생성자
		this.image = image;	//이미지 설정
		this.x = x;	//x좌표 설정
		this.y = y;	//y좌표 설정
	}
	
	public int getWidth() {	//스프라이트의 가로 길이를 반환한다.
		return image.getWidth(null);
	}
	
	public int getHeight() {	//스프라이트의 세로 길이를 반환한다.
		return image.getHeight(null);
	}
	
	public void draw(Graphics g){	//스프라이트를 화면에 그린다.
		g.drawImage(image, x, y, null);
	}
	
	public void move() {	//스프라이트를 움직인다.
		x += dx;
		y += dy;
	}
	
	public void setDx(int dx) { this.dx = dx; }	//dx를 설정한다.
	
	public void setDy(int dy) { this.dy = dy; }	//dy를 설정한다.
	
	public int getDx() { return dx; } //dx를 반환한다.
	
	public int getDy() { return dy; } //dy를 반환한다.
	
	public int getX() { return x; } //x를 반환한다.
	
	public int getY() { return y; } //y를 반환한다.
	
	public boolean checkCollision(Sprite other) {	// 다른 스프라이트와이 충돌 여부를 판별한다.
		Rectangle myRect = new Rectangle();	//나의 사각형 객체 생성
		Rectangle otherRect = new Rectangle();	//내가 아닌 다른 사각형 객체 생성
		myRect.setBounds(x, y, getWidth(), getHeight());	//나의 사각형 가로, 세로 설정
		otherRect.setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());	//내가 아닌 다른 사각형 가로,세로 설정
		
		return myRect.intersects(otherRect);	//서로 겹치는 부분이 생기면 true리턴
	}
	
	public void handleCollision(Sprite other){	//충돌을 처리한다.
		
	}
}
