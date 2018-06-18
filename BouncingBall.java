package open;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.JButton; 
import javax.swing.JFrame; import javax.swing.JPanel;

public class BouncingBall extends JPanel{
private static final int BOX_WIDTH = 400; // ��ü ��
private static final int BOX_HEIGHT = 300; // ��ü ����
private float ballRadius = 20; // ���� ������
private float ballX = ballRadius + 120; // ���� �ʱ� X��ġ
private float ballY = ballRadius + 80; // ���� �ʱ� Y��ġ
private float ballSpeedX = 10; // ���� X�ӵ�
private float ballSpeedY = 10; // ���� Y�ӵ�
private JButton play; // ���۹�ư
private JButton stop; // ������ư
private boolean isPlay = false; // �÷����Ұ�����

private float barX = 140;
private float barY = 200;
private float barWidth = 100;
private float barHeight = 20;

static JFrame frame;

public BouncingBall() {
	
	this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
	JButton play = new JButton("PLAY"); // ���۹�ư ����
	JButton stop = new JButton("STOP"); // ������ư ����
	add(play);
	add(stop);
	
	play.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("PLAY")) { // ���� ��ư�� ���ڿ��� PLAY���
				isPlay = true;
			}
		}
	});
	stop.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("STOP")) {// ���� ��ư�� ���ڿ��� STOP�̶��
				isPlay = false;
			}
		}
	});

class MyThread extends Thread {
	public void run() { // �����Ͽ��� �ϴ� �۾��� ������
		while (true) {
			ballY += ballSpeedY;
			if (ballY - ballRadius < 0) {
				ballSpeedY = -ballSpeedY;
				ballY = ballRadius;
			} else if (ballY + ballRadius > BOX_HEIGHT) {
				ballSpeedY = -ballSpeedY;
				ballY = BOX_HEIGHT - ballRadius;
			}
			
			if (ballX < 0 || ballX > BOX_WIDTH - ballRadius *2) {

				ballSpeedX = -ballSpeedX; // ���� �ε�ĥ ��� ������ �ٲ��.

				}	
			if (barX - ballRadius < ballX 
					&& ballY < barX  + barWidth - ballRadius
					&& barY - ballRadius * 2 < ballY
					&& barY - ballRadius * 2 + barHeight > ballY) 
			{
				ballSpeedY = -ballSpeedY;
			}
		
			if (isPlay) // isPlay ������ true�̸�
				repaint(); // �׸���.
			try {
				Thread.sleep(50); // ���� �ӵ� ����
			} catch (InterruptedException ex) {
			}
		}
	}
}
Thread t = new MyThread(); // ������ ��ü ����
t.start(); // ������ ����
}
public void paintComponent(Graphics g) {
	g.setColor(Color.WHITE);
	g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT); // ���簢��
	g.setColor(Color.RED); // ���������� ä��
	g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
			(int) (2 * ballRadius), (int) (2 * ballRadius)); // ��
	g.fillRect((int)barX, (int)barY, (int)barWidth, (int)barHeight);

}

public static void main(String[] args) {
	
	frame = new JFrame("Bouncing Ball");
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(new BorderLayout());
	frame.setSize(600,800);
	frame.setContentPane(new BouncingBall());
	frame.pack();
	frame.setVisible(true);

}


}
