package project8_better;  //�����(�����), � ������� ��������� ���� � �������

import javax.swing.JOptionPane;

public class game { //������� �����, ��� �������� ���� �� ����������

	public static void main(String[] args) { //������� �����, ��� �������� ����� �� ����������
		// TODO ������������� ��������� �������� ������
        //okno window = new okno(); //�������� ���� ����
        
        String rez = JOptionPane.showInputDialog(null,"�������� ������� ��������� ��  1�� 7","��������� ����",1); //������� ���������� ����
        int slogn = rez.charAt(0)-'0'; // ��������� �������� ��������
        if((slogn>=1)||(slogn<=7))		//����������� �������� ��������
        {  okno window = new okno(slogn); } //������ ����, ���� ������� ��������� �� 1 �� 7
		
        System.out.println("��� ����?");
        
	}

}
