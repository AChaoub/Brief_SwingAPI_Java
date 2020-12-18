package com.onlineTest.app;

/*Online Java Paper Test*/
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

class Quizz extends JFrame implements ActionListener{
	
	JLabel l;
	JRadioButton jb[]= new JRadioButton[4];

	String[] reponseCorrect= {"Compilé et interpreté", "Object", "commence par une majuscule","oui","oui", "Interface", 
							  ".Class"," aucun des choix", "vrai", "final","this","surchargée","vrai","vrai","vrai", " a=10 b=0 Je suis à l'interieur de finally"};
	
	static JButton b1;
	static JButton v1;
	ButtonGroup bg;
	int count=0, current=0;	

	JLabel label  = new JLabel("",SwingConstants.CENTER);
	static JLabel label1  = new JLabel("");
	
	//JPanel panel = new JPanel();
	static JTextArea textChrono = new JTextArea();
	Font f = new Font("Serif", Font.BOLD, 24);

	
	static Timer timer = new Timer();
	static int i = 300;
	
	Quizz(String s)throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		super(s);
		//panel.add(textChrono);
		textChrono.setBounds(250,5,80,30); 
		textChrono.setFont(f);
		add(textChrono);
		l=new JLabel();
		add(l);
		
		label1.setBounds(180, 250, 250, 30);
		add(label1); 
		Font  f1 = new Font("Serif", Font.BOLD,18);
		label1.setFont(f1);
		label1.setForeground(Color.RED);
		
 		bg=new ButtonGroup();
		
		for(int i=0; i< jb.length; i++){
			jb[i]=new JRadioButton();
			System.out.println(jb.length);
			if(i < 2) {
			add(jb[i]);
			jb[i].addActionListener(this);
			bg.add(jb[i]);
			}
			else if(i < 3) {
				add(jb[i]);
				jb[i].addActionListener(this);
				bg.add(jb[i]);
				}
		}
		
		b1=new JButton("Next");
		b1.addActionListener(this);
		add(b1);
		set();
		
		label.setBounds(1000, 20, 150, 30);
		add(label); 
		
		l.setBounds(350,60,2000,3400);
		jb[0].setBounds(50,80,100,20);
		jb[1].setBounds(50,110,100,20);
		jb[2].setBounds(50,140,100,20);
		b1.setBounds(120,300,100,30);
		
		v1=new JButton("Valider");
		v1.addActionListener(this);
		add(v1);
		set();
		v1.setBounds(350,300,100,30);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(250,100);
		setVisible(true);
		setSize(600,400);
		
		
		
	}
	static TimerTask task = new TimerTask(){
		public void run(){
			String time = "0";
			try {
				time = getTime(i);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textChrono.setText(time);
			i--;
		}
	};
	public static void runTimer(){
		
			timer.schedule(task, 0, 1000 );	
		
	}
	
	


	static String getTime(int sec) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		
	    int minutes = 0;
	    int seconds = 0;
	    
	    //pour afficher
	    String strMins; 
	    String strSecs;

	    if(i==295) {
	    	
	    		label1.setText("Attention il te reste 30 sec");
	    }
	    if(i==0) {
	    	b1.setEnabled(false);
	    	v1.setEnabled(false);
	    	playSound("http://codeskulptor-demos.commondatastorage.googleapis.com/GalaxyInvaders/alien_hit.wav");
	    	//System.exit(0);	
	    }
	   if (sec >= 60){
		   
	        minutes = sec / 60;
	        seconds = sec % 60;
	    }

	    else if (sec < 60){
	    	
	        minutes = 0;
	        seconds = sec;
	    }
	    
	     

	    if(seconds < 10)
	    	strSecs = "0" + Integer.toString(seconds);
	    else
	    	strSecs = Integer.toString(seconds);
	   
	    if(minutes < 10)
	   	 strMins = "0" + Integer.toString(minutes);
	   else
		   strMins = Integer.toString(minutes);
	    
	    	
	        
	    String time =strMins + ":" + strSecs;
	    return time;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b1){
			current++;
			set();
			if(current==4){
				b1.setEnabled(false);
			}
		}
		if(count >= 40) {
			if(e.getSource() == v1) {
				current++;
				set();
				b1.setEnabled(true);
				label.setText("");
				label.setText("Score : "+count);
				
			}
			if(current== 9) {
				
				System.out.println("current" + current);
				b1.setEnabled(false);
			}
			if(current == 15) {
				System.out.println("current" + current);
				b1.setEnabled(false);
				
				label.setText("");
				label.setText("Bravo!!!!!!!!!!");
				
			}
		}

		for(int i=0 ; i < reponseCorrect.length; i++) {
			if(e.getActionCommand().equals(reponseCorrect[i])){
				count += 20;
			}
		}
		
		System.out.println(" Score ="+count);
		
		
	}
	static void playSound(String soundName) throws LineUnavailableException, UnsupportedAudioFileException, IOException
    {
       URL url = new URL(soundName);
           Clip clip = AudioSystem.getClip();
           AudioInputStream ais = AudioSystem.getAudioInputStream( url );
           clip.open(ais);
           clip.loop(Clip.LOOP_CONTINUOUSLY);
          
    }
	
	void set(){

		if(current==0){
			l.setText("<html><h1 style='color:blue;'>Question 1 :</h1> JAVA est un langage</html>");
			jb[0].setText("Compilé"); jb[1].setText("Interprété"); jb[2].setText("Compilé et interpreté");	
		}
		
		if(current==1){
			l.setText("<html>Que2:<br> Toutes les classes héritent de la classe</html>");
			jb[0].setText("Main");jb[1].setText("Object");jb[2].setText("AWT");
			
		}
	
		if(current==2){
			l.setText("<html>Que3:<br> Par convention une classe</html>");
			jb[0].setText("est en minuscule");jb[1].setText("commence par une majuscule");
			jb[2].setText("est en majuscules");
		}
		
		if(current==3){
			l.setText("<html>Que4:<br> Est-ce que on peut avoir plusieurs constructeurs pour la même classe </html>");
			jb[0].setText("oui");jb[1].setText("non");jb[2].setVisible(false);
		}
		
		if(current==4){
			l.setText("Que5:<br> Dans la ligne public class A implements B\", B est");
			jb[0].setText("oui");jb[1].setText("non");	
		}
	

		if(current==5){
			jb[2].setVisible(true);
			l.setText("<html>Que6:<br> Après la compilation, un programme écrit en <br>"
					+ "            JAVA, il se transforme en programme en bytecode<br>"
					+ "            Quelle est l’extension du programme en<br>"
					+ "            bytecode ?<br>"
					+ "</html>");
			jb[0].setText("aucun des choix");jb[1].setText(".JAVA");jb[2].setText(".Class");	
		}
		if(current==6){
			jb[2].setVisible(true);
			l.setText("<html>Que7:<br> Class Test{Public Test () {"
					+ "System.out.println(”Bonjour”);}<br>"
					+ "public Test (int i) {<br>"
					+ "this();<br>"
					+ "System.out.println(”Nous sommes en ”+i+ ” !”);};<br>"
					+ "}<br>"
					+ "qu’affichera l’instruction suivante?<br>"
					+ "Test t1=new Test (2018)</html>");
			jb[0].setText("aucun des choix");jb[1].setText("Bonjour Nous sommes en 2018");jb[2].setText("Nous sommes en 2018");
		}
	
		if(current==7){
			l.setText("<html>Que8:<br> Voici un constructeur de la classe Employee, y-at'il un problème Public void Employe(String n) Nom=n</html>");
			jb[0].setText("vrai");jb[1].setText("faux");jb[2].setVisible(false);
		}
		if(current==8){
			jb[2].setVisible(true);
			l.setText("<html>Que9:<br> Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration,on la déclare comme une constante avec le mot réservé</html>");
			jb[0].setText("aucun des choix");jb[1].setText("final");jb[2].setText(" const");
		}
		
		if(current==9){
			jb[2].setVisible(false);
			l.setText("<html>Que10:<br> Dans la ligne \"public class A implements B\", B est</html>");
			jb[0].setText("oui");jb[1].setText("non");	
		}
		if(current==10){
			jb[2].setVisible(true);
			l.setText("<html>Que11:<br> Dans une classe, on accède à ses variables grâce au mot clé</html>");
			jb[0].setText("aucun choix");jb[1].setText("super");jb[2].setText("const");	
		}
		if(current==11){
			jb[2].setVisible(true);
			l.setText("<html>Que12:<br> Toutes les classes héritent de la classe</html>");
			jb[0].setText("Main");jb[1].setText("Object");jb[2].setText("AWT");
		}
	
		if(current==12){
			jb[2].setVisible(true);
			l.setText("<html>Que13:<br> Par convention une classe</html>");
			jb[0].setText("est en minuscule");jb[1].setText("commence par une majuscule");
			jb[2].setText("est en majuscules");
		}
		if(current==13){
			jb[2].setVisible(true);
			l.setText("<html>Que14:<br> calculerSalaire(int)<br> calculerSalaire(int, double)<br> La méthode calculerSalaire est : </html>");
			jb[0].setText("aucun des choix");jb[1].setText("surchargée");jb[2].setText("redéfinie");
		}
		
		if(current==14){
			jb[2].setVisible(false);
			l.setText("<html>Que15:<br> Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite.</html>");
			jb[0].setText("vrai");jb[1].setText("faux");
			
		}
		
		
		l.setBounds(60,30,850,100);
		for(int i=0,j=0;i<=90;i+=30,j++)
			jb[j].setBounds(100,140+i,200,20);
		}
	
	public void vider() {
		jb[0].setSelected(false);
		jb[1].setSelected(false);
		jb[2].setSelected(false);
	}

	public static void main(String s[])throws LineUnavailableException, UnsupportedAudioFileException, IOException{
			
		new Quizz("Online Test Of Java");
		runTimer();
		
		
	}

}
