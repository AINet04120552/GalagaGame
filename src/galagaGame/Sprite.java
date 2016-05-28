package galagaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
	protected int x;	//현재 위치 x좌표 
	protected int y;	//현재 위치 y좌표
	protected int dx;	//단위시간에 움직이는 x방향 거리 
	protected int dy;	//단위시간에 움직이는 y방향 거리
	private Image image;	//스프라이트가 가지고 있는 이미지
	
	public Sprite(Image image, int x, int y){
		this.image = image;
		this.x = x;
		this.y = y;
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
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		myRect.setBounds(x, y, getWidth(), getHeight());
		otherRect.setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());
		
		return myRect.intersects(otherRect);
	}
	
	public void handleCollision(Sprite other){	//충돌을 처리한다.
		
	}
}
