package project8_better;  //пакет(папка), в котором находится файл с классом

import javax.swing.JOptionPane;

public class game { //главный класс, без которого игра не запустится

	public static void main(String[] args) { //главный метод, без которого класс не загрузится
		// TODO Автоматически созданная заглушка метода
        //okno window = new okno(); //создание окна игры
        
        String rez = JOptionPane.showInputDialog(null,"Введитте уровень сложности от  1до 7","Сложность игры",1); //выводим диалоговое окно
        int slogn = rez.charAt(0)-'0'; // сохраняем введённое значение
        if((slogn>=1)||(slogn<=7))		//анализируем введённое значение
        {  okno window = new okno(slogn); } //создаём окно, если уровень сложности от 1 до 7
		
        System.out.println("как дела?");
        
	}

}
