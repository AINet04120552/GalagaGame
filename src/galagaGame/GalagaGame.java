package galagaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GalagaGame extends JPanel implements KeyListener{	//JPanel 상속 KeyListener 구현

	private boolean running = true;	//게임중인지 알려줌
	
	private ArrayList sprites = new ArrayList();	//ArrayList 객체 생성
	private Sprite starship;	//우주선 참조변수
	
	private BufferedImage alienImage;	//외계인 이미지 참조변수
	private BufferedImage shotImage;	//총알 이미지 참조변수
	private BufferedImage shipImage;	//우주선 이미지 참조변수
	
	public GalagaGame() {
		JFrame frame = new JFrame("Galaga Game");	//메인 프레임 객체 생성
		
		frame.setSize(800,600);	//프레임 크기 설정
		frame.add(this);	//프레임에 컴포넌트 추가
		frame.setResizable(false);	//리사이징을 하지 않음
		frame.setVisible(true);	//화면에 보여줌
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//x버튼을 누르면 종료
		
		try {	//이미지를 읽는다. 
			shotImage = ImageIO.read(new File("fire.jpg"));	//불 이미지를 읽어와 저장
			shipImage = ImageIO.read(new File("starship.jpg"));	//우주선 이미지를 읽어와 저장
			alienImage = ImageIO.read(new File("alien.jpg"));	//외계인 이미지를 읽어와 저장
		} catch (IOException e){	//오류났을때
			e.printStackTrace();
		}
		this.requestFocus();	//이벤트를 받을 수 있게 함
		this.initSprites();	//우주선과 외계인 생성
		addKeyListener(this);	//keylistener 등록
	}
	
	private void initSprites() {	//우주선과 외계인을 만든다.
		starship = new StarShipSprite(this, shipImage, 370, 550);	//우주선 이미지,위치 설정 및 우주선 객체 생성
		sprites.add(starship);	//sprites에 우주선 객체 추가
		for (int y = 0; y < 5; y++){
			for (int x = 0; x < 12; x++){
				Sprite alien = new AlienSprite(this, alienImage, 100 + (x * 50), (50) + y * 30);	//외계인 이미지,위치와 갯수,게임판 설정 및 외계인 객체 생성 
				sprites.add(alien);	//sprites에 외계인 객체 추가
			}
		}
	}
	
	private void startGame() {	//게임 시작
		sprites.clear();	//sprites초기화
		initSprites();	//우주선과 외계인을 만든다.
	}
	
	public void endGame() {	//게임 끝
		//System.exit(0);
	}
	
	public void removeSprite(Sprite sprite){	//sprite삭제
		sprites.remove(sprite);	//sprite 최초의 것을 리스트로부터 삭제
	}
	
	public void fire() {	//총알이 발사되면 ArrayList 객체인 Sprites에 추가한다.
		ShotSprite shot = new ShotSprite(this, shotImage, starship.getX() + 10, starship.getY() - 30);	// 총알 이미지, 초기위치, 게임판 설정 및 총알 객체 생성
		sprites.add(shot);	//sprites에 추가
	}
	
	@Override
	public void paint(Graphics g) {	//모든 객체를 여기서 그린다. Sprites에서 객체를 꺼내서 객체의 draw()를 호출한다.
		super.paint(g);	//부모의 것을 가져와 사용
		g.setColor(Color.black);	//색상 설정
		g.fillRect(0, 0, 800, 600);	//전부 색으로 채움
		for (int i = 0; i < sprites.size(); i++){	//sprites 사이즈 만큼 반복
			Sprite sprite = (Sprite) sprites.get(i);	//sprites에 있는 것을 하나 꺼냄
			sprite.draw(g);	//그림
		}
	}
	
	public void gameLoop(){
		while (running){	//객체를 움직인다.
			for (int i = 0; i < sprites.size(); i++) {	//sprites 사이즈 만큼 반복
				Sprite sprite = (Sprite) sprites.get(i);	//sprites에 있는 것을 하나 꺼냄
				sprite.move();	//움직임
			}
			
			for (int p = 0; p < sprites.size(); p++){	//sprites 사이즈 만큼 반복
				for (int s = p + 1; s < sprites.size(); s++){	//sprites 사이즈 만큼 반복
					Sprite me = (Sprite) sprites.get(p);	//sprites에 있는 것을 하나 꺼내 me에 저장
					Sprite other = (Sprite) sprites.get(s);	//sprites에 있는 것을 하나 꺼내 other에 저장
					
					if(me.checkCollision(other)){	//충돌검사
						me.handleCollision(other);	//나랑 다른게 부딧혔는지
						other.handleCollision(me);	//다른게 나랑 부딧혔는지
					}
				}
			}
			
			repaint();	//다시 그린다.
			try {
				Thread.sleep(10);	//그림을 사람이 인지 할 수 있게 딜레이를 준다.
			} catch (Exception e){	//오류가 났을때
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	//키가 눌렸을때
		if (e.getKeyCode() == KeyEvent.VK_LEFT)	//왼쪽이 눌리면 우주선 왼쪽으로 이동
			starship.setDx(-3);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)	//오른쪽이 눌리면 우주선 오른쪽으로 이동
			starship.setDx(+3);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {	//눌린키가 떼어질때
		if (e.getKeyCode() == KeyEvent.VK_LEFT)	//왼쪽 키가 떼어지면 멈춤
			starship.setDx(0);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)	//오른쪽 키가 떼어지면 멈춤
			starship.setDx(0);
		if (e.getKeyCode() == KeyEvent.VK_SPACE)	//스페이스바가 떼어지면
			fire();	//총알 발사
	}
	
	@Override
	public void keyTyped(KeyEvent arg0){ //눌린키가 떼어질때 (유니코드 키일 경우 추가 이벤트)
	}
	
	public static void main(String argv[]) {
		GalagaGame g = new GalagaGame();	//갤러그 게임 객체 생성
		g.gameLoop();	//게임 루프 돌림
	}
}
