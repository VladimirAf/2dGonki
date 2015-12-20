package project8_better; //�����(�����), � ������� ��������� ���� � �������

import java.awt.*;  //���������� ��� ������ � ��������
import java.awt.event.*; //���������� ��� ������ �������
import java.io.*; //���������� ��� ������ � �������������

import javax.imageio.*; //���������� ��� ������ � �������������
import javax.swing.*; //���������� ��� ������ � ��������

//�������� ������ ���������

class pole extends JPanel //��������� ��� ����� ������ ���������. ���� ����� �������� ������� ������ JPanel � JAVA
{
	
	int x=400; //������ ��������� ���������� �����
	private Image shapka; //����������, � ������� �������� ������ �� ����������� �����
	private Image fon;	//����������, � ������� �������� ������ �� ����������� ����
	private podar[] gamePodar;  //�������� ���������� ������� 
	private int slogn; //������� ���������
	private Image end_game;//����������� ����� ����
	public Timer timerUpdate,timerDraw; // ��� �������: ������ ��� ������ ��������,������ ��� ��������� �������
	
	
	public pole(int slogn)  //�����-����������� ������. ����� ������ ������������� ��� �������� ������
	{
		
		this.slogn=slogn;
		timerUpdate = new Timer(3000,new ActionListener(){//������ ��������� ������� ���������� ����� ��������
			public void actionPerformed(ActionEvent e){//��������� �����, ������� ����������� ��� ������������� �������
						
				updateStart();//������� ���������� ����� ��������
			} //
		});  //
		timerUpdate.start(); //�������� ������
		
		timerDraw = new Timer(25,new ActionListener(){//������ ��������� �������
			public void actionPerformed(ActionEvent e){//��������� �����, ������� ����������� ��� ������������� �������
						
				repaint();//������� ��������������� ��� ������� ������, �������� ����� paintComponent(Graphics gr)	
			}
		});
		
		timerDraw.start();//�������� ������
		
		try 
		{
			shapka = ImageIO.read(new File("shapka.png")); //���������� ����������� �����
		}
		catch(IOException exp){} //�� ������ ������, ���� ����� �������� � ������������
		
		try 
		{
			fon = ImageIO.read(new File("fon.png")); //�� �� ����� ������ � �����
		}
		catch(IOException exp){}
		
		try 
		{
			end_game = ImageIO.read(new File("end_game.png")); //�� �� ����� ������ � �����
		}
		catch(IOException exp){}
		
		//�������� �������� � ���������� ��� �������
		
				gamePodar = new podar[7];   //������ ������
				for (int i=0;i<7;i++){		//��������� �������� ����� ��� �������� 7 ��������
					
					try{  //��� ������ ��������� �� �����
						
					gamePodar[i]=new podar(ImageIO.read(new File("p"+i+".png"))); //������ 1-� ������� � ������ ������ �������
					
					}
					catch (IOException e){} //���������� ������
				}
		
	}
	
	private void updateStart()
	   {
     int kol=0; // ���������� ��� �������� �������� �� ������� ����
     for (int i=0;i<7;i++) // ���� �������� ���� �������� �������
     {
        if (gamePodar[i].act==false) // ���� ������� �� �� ������� ����
        {
           if (kol<slogn) // ���� ������� ���������� ����� ������ ��������� (�� 1 �� 7)
           {
             gamePodar[i].start(); // ����������� ������� �� ������� ����, ����� ��� ������ �������� ����
             break; // ���������� �����
           }
        }
        else kol++; // ���� ������� �� ������� ����
     }
	   }	
	
	
public void paintComponent(Graphics gr)	//����� ���������� �������
{
	super.paintComponent(gr); //��������������(���������) ��� ������, ����� �� �������� ����� �� ������ �����������
    gr.drawImage(fon,0,0,null); //���������� ����
    gr.drawImage(shapka,x,465,null); //���������� �����
    
    for (int i=0;i<7;i++)
    {
 	       gamePodar[i].draw(gr); // ����������� �������
 	       if (gamePodar[i].act==true) // ���� ������� �� ������� �������� �������
 	       {
 		   if ((gamePodar[i].y+gamePodar[i].img.getHeight(null))>=470) // ���� ������� ������ ������ �������
 		   {
 			   
 			    
 			    if (Math.abs(gamePodar[i].x - x) > 75) // ���� ������� ��������
 			    {	
 			        gr.drawImage(end_game, 300, 300, null); // ����� �������� ��������� ����
 			    	timerDraw.stop(); // ���������� ������� timerDraw 
 			    	timerUpdate.stop(); // ��������� ������� timerUpdate
 			        break; // ���������� �����
 			    }
 			    else 	
 			    		gamePodar[i].act=false; // ������ ������� � �������� ����, ���� �� ������ ������
 		   }
 	       }
    } //
    
    
}

}
