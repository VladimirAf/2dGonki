package project8_better; //пакет(папка), в котором находится файл с классом

import java.awt.*;  //библиотека для работы с графикой
import java.awt.event.*; //библиотека для работы таймера
import java.io.*; //библиотека для работы с изображениями

import javax.imageio.*; //библиотека для работы с изображениями
import javax.swing.*; //библиотека для работы с графикой

//описание панели отрисовки

class pole extends JPanel //описываем наш класс панели отрисовки. Этот класс является дочерью класса JPanel в JAVA
{
	
	int x=400; //задаем начальную координату шапки
	private Image shapka; //переменная, в которой хранится ссылка на изображение шапки
	private Image fon;	//переменная, в которой хранится ссылка на изображение фона
	private podar[] gamePodar;  //добавить переменную массива 
	private int slogn; //уровень сложности
	private Image end_game;//изображение конца игры
	public Timer timerUpdate,timerDraw; // Два таймера: первый для старта подарков,второй для отрисовки графики
	
	
	public pole(int slogn)  //метод-конструктор класса. Будет вызван автоматически при создании панели
	{
		
		this.slogn=slogn;
		timerUpdate = new Timer(3000,new ActionListener(){//задаем параметры таймера вызывающий полет подарков
			public void actionPerformed(ActionEvent e){//описываем метод, который выполняется при срабатываниях таймера
						
				updateStart();//функция начинающая полет подарков
			} //
		});  //
		timerUpdate.start(); //включаем таймер
		
		timerDraw = new Timer(25,new ActionListener(){//задаем параметры таймера
			public void actionPerformed(ActionEvent e){//описываем метод, который выполняется при срабатываниях таймера
						
				repaint();//функция отрисовываающая всю графику заново, вызывает метод paintComponent(Graphics gr)	
			}
		});
		
		timerDraw.start();//включаем таймер
		
		try 
		{
			shapka = ImageIO.read(new File("shapka.png")); //подключаем изображение шапки
		}
		catch(IOException exp){} //на всякий случай, если будут проблемы с изображением
		
		try 
		{
			fon = ImageIO.read(new File("fon.png")); //то же самое делаем с фоном
		}
		catch(IOException exp){}
		
		try 
		{
			end_game = ImageIO.read(new File("end_game.png")); //то же самое делаем с фоном
		}
		catch(IOException exp){}
		
		//создание подарков и заполнение ими массива
		
				gamePodar = new podar[7];   //создаём массив
				for (int i=0;i<7;i++){		//добавляем оператор цикла для создания 7 подарков
					
					try{  //для защиты программы от сбоев
						
					gamePodar[i]=new podar(ImageIO.read(new File("p"+i+".png"))); //создаём 1-й подарок в первой ячейке массива
					
					}
					catch (IOException e){} //обработчик ошибок
				}
		
	}
	
	private void updateStart()
	   {
     int kol=0; // Переменная для подсчета подарков на игровом поле
     for (int i=0;i<7;i++) // Цикл перебора всех подарков массива
     {
        if (gamePodar[i].act==false) // Если подарок не на игровом поле
        {
           if (kol<slogn) // Если текущее количество менее номера сложности (от 1 до 7)
           {
             gamePodar[i].start(); // Активизация подарка на игровом поле, вывод его сверху игрового поля
             break; // Прерывание цикла
           }
        }
        else kol++; // Если подарок на игровом поле
     }
	   }	
	
	
public void paintComponent(Graphics gr)	//метод прорисовки графики
{
	super.paintComponent(gr); //перерисовываем(обновляем) всю панели, чтобы не осталось грязи из старых изображений
    gr.drawImage(fon,0,0,null); //прорисовка фона
    gr.drawImage(shapka,x,465,null); //прорисовка шапки
    
    for (int i=0;i<7;i++)
    {
 	       gamePodar[i].draw(gr); // Отображение подарка
 	       if (gamePodar[i].act==true) // Если подарок из массива подарков активен
 	       {
 		   if ((gamePodar[i].y+gamePodar[i].img.getHeight(null))>=470) // Если подарок достиг нижней границы
 		   {
 			   
 			    
 			    if (Math.abs(gamePodar[i].x - x) > 75) // Если подарок пропущен
 			    {	
 			        gr.drawImage(end_game, 300, 300, null); // Вывод картинки Окончания игры
 			    	timerDraw.stop(); // Оставновка таймера timerDraw 
 			    	timerUpdate.stop(); // Оставнока таймера timerUpdate
 			        break; // Прерывание цикла
 			    }
 			    else 	
 			    		gamePodar[i].act=false; // Снятие подарка с игрового поля, если он пойман шапкой
 		   }
 	       }
    } //
    
    
}

}
