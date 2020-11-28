
import java.awt.* ;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;




public class TicTacToe implements ActionListener {

         //pentru a determina cine e primul
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel() ;
    JButton[] buttons = new JButton[9] ;
    boolean player1_turn;
    int aux=0 ;

    TicTacToe(){
        //-----Frame------
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));      //lighter shade of black cu RGB values
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        //-----TextField------
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);


        //------TitlePanel-------
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0 , 800, 100);


        //----------Button panel--------------
        button_panel.setLayout(new GridLayout(3,3));       //cum avem 9 butoane vrem sa fie 3 pe 3
        button_panel.setBackground(new Color(150, 150 ,150));


        //--------Buttons ------------
        for(int i=0 ; i< 9 ; i++) {
            buttons[i] = new JButton() ;
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setText("");
            buttons[i].addActionListener(this);

        }





        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);            // title_panel ramane blocat sus
        frame.add(button_panel);

        firstTurn() ;
        //nextMove();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0 ; i<9 ;i++) {


            if(e.getSource() == buttons[i]) {
                if(buttons[i].getText() == "") {
                    buttons[i].setForeground(new Color(255, 0 ,0));
                    buttons[i].setText("X");
                    player1_turn = false;
                    textfield.setText("0 turn");
                    int ce = check();
                    if(ce == 1){
                        for(int K=0; K<9 ; K++) {
                            buttons[K].setEnabled(false);						//Oprim butoanele
                        }
                        textfield.setText("X WINS");
                    }

                    Oturn();

                }
            }
        }
    }


   /* public void Oturn() {


        int aux = 0 ;
        for(int i= 0 ; i<9 ; i++) {
            if(buttons[i].getText() != "") {
                aux++ ;
            }
        }
        System.out.println(aux);
        if(aux != 9) {

            int randomNum=-1;
            while(randomNum == -1) {
                randomNum = 0 + (int)(Math.random() * 8);

                if(buttons[randomNum].getText() == "") {
                    buttons[randomNum].setForeground(new Color(0, 0 ,255));
                    buttons[randomNum].setText("O");
                    player1_turn = true;
                    textfield.setText("X turn");
                    int ce = check();
                }
                else {
                    randomNum = -1 ;
                }

            }
        }


    } */


		public void Oturn() {
			int bestScore = 999;
			int bestMove=0 ;

			int aux = 0 ;
			for(int i= 0 ; i<9 ; i++) {
				if(buttons[i].getText() != "") {
					aux++ ;

				}
			}
			if(aux != 9) {


                ArrayList<Magie> vect_m = new ArrayList<>();

				for(int i=0 ; i <9 ; i++) {
					if(buttons[i].getText() == "") {


						buttons[i].setText("O");


						Magie magie = minimax(buttons, 0, true) ;
                        System.out.println(magie.depth + "aaaaaaaa" + magie.scor);


                        buttons[i].setText("");


						if(magie.scor <= bestScore) {
						    magie.poz = i ;
                            vect_m.add(magie) ;

						}
					}
				}

				vect_m.sort((Magie m1 , Magie m2)->Integer.compare(m2.getDepth(), m1.getDepth()));
                System.out.println(vect_m);

			/*	for(int j = 0 ; j< vect_m.size() ; j++) {
                    for (int k = j; k < vect_m.size(); k++) {
                        if (vect_m.get(k).getDepth() < vect_m.get(k + 1).getDepth()) {
                            Magie aux1 = new Magie();
                            aux1.setDepth(vect_m.get(k).getDepth())  ;
                            aux1.setScor() =
                            vect_m.get() = vect_m.get(k + 1);
                            vect_m.get(k + 1) = aux1;
                        }
                    }
                }*/

                bestMove = vect_m.get(0).getPoz() ;

                System.out.println("--------------------------------");
                System.out.println(bestScore);
                System.out.println(bestMove);
                System.out.println("----------------aaaa----------------");
				buttons[bestMove].setForeground(new Color(0, 0 ,255));
				buttons[bestMove].setText("O");
				player1_turn = true;
				textfield.setText("X turn");
				int ce = check();

				if(ce == -1){
                    for(int K=0; K<9 ; K++) {
                        buttons[K].setEnabled(false);						//Oprim butoanele
                    }
                    textfield.setText("O WINS");
                }




		}


		}
    //-----------------MAP----------------------



    //----------------------------


    public Magie minimax(JButton buttons[], int depth, boolean isMaximizing) {
        int result = check();

       if(result != -999){
            Magie magie = new Magie();
            magie.scor = result;
            magie.depth = depth ;
            return magie ;
        }

        if(isMaximizing){
            int bestScore = -9999;
            Magie magie = new Magie();
            for(int i=0 ; i<9 ; i++){
                if(buttons[i].getText() == ""){
                    buttons[i].setText("O");
                    magie = minimax(buttons, depth + 1, false);
                    buttons[i].setText("");

                    if(magie.scor > bestScore){
                        bestScore = magie.scor;

                    }

                }
            }

            return new Magie(bestScore, magie.depth);
        } else{
            int bestScore = 9999;
            Magie magie = new Magie();
            for(int i=0 ; i<9 ; i++){
                if(buttons[i].getText() == ""){
                    buttons[i].setText("X");
                    magie = minimax(buttons, depth + 1, true);
                    buttons[i].setText("");

                    if(magie.scor < bestScore){
                        bestScore = magie.scor;

                    }

                }
            }
            return new Magie(bestScore, magie.depth);

        }




        //return 1;



    }

    //--------------------------------------------------------------

    public void firstTurn() {
        player1_turn = true;
        textfield.setText("X turn");
       // Oturn();
    }

    public int check() {
        //Check X
        if((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {

         //   xWins(0 , 1, 2) ;
            return 1;
        }
        else if((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {

         //   xWins(3 , 4, 5) ;
            return 1;
        }
        else if((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {

         //   xWins(6 , 7, 8) ;
            return 1;
        }
        else if((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {

         //   xWins(0 , 3, 6) ;
            return 1;
        }
        else if((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {

         //   xWins(1 , 4, 7) ;
            return 1;
        }
        else if((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {

          //  xWins(2 , 5, 8) ;
            return 1;
        }
        else if((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {

          //  xWins(0 , 4, 8) ;
            return 1;
        }
        else if((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {

          //  xWins(2 , 4, 6) ;
            return 1;
        }

        else if((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {

           // oWins(0 , 1, 2) ;
            return -1;
        }
        else if((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {

          //  oWins(3 , 4, 5) ;
            return -1;
        }
        else if((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {

          //  oWins(6 , 7, 8) ;
            return -1;
        }
        else if((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {

         //   oWins(0 , 3, 6) ;
            return -1;
        }
        else if((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {

          //  oWins(1 , 4, 7) ;
            return -1;
        }
        else if((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {

         //   oWins(2 , 5, 8) ;
            return -1;
        }
        else if((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {

          //  oWins(0 , 4, 8) ;
            return -1;
        }
        else if((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {

         //   oWins(2 , 4, 6) ;
            return -1;
        }
        else {
         int aux = 0 ;
            for(int i= 0 ; i<9 ; i++) {
                if(buttons[i].getText() != "") {
                    aux++ ;
                }
            } //PROBLEMA E CA AUX O SA FIE 9 SI DA EGALITATE CRED
            if(aux == 9) {
               /* buttons[0].setBackground(Color.yellow);
                buttons[1].setBackground(Color.yellow);
                buttons[2].setBackground(Color.yellow);
                buttons[3].setBackground(Color.yellow);
                buttons[4].setBackground(Color.yellow);
                buttons[5].setBackground(Color.yellow);
                buttons[6].setBackground(Color.yellow);
                buttons[7].setBackground(Color.yellow);
                buttons[8].setBackground(Color.yellow); */
                textfield.setText("EGALITATE");

                return 0;
            }
            else return -999;
        }

    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9 ; i++) {
            buttons[i].setEnabled(false);						//Oprim butoanele
        }
        textfield.setText("X WINS");

    }
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9 ; i++) {
            buttons[i].setEnabled(false);						//Oprim butoanele
        }
        textfield.setText("O WINS");

    }





}