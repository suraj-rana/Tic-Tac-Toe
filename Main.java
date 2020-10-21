package tictactoe;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // write your code here
        //System.out.println("X O O\nX X O\nO X O");
        Scanner str = new Scanner(System.in);
        //System.out.print("Enter cells: ");
       // String str2 = str.nextLine();
        String str2 = "         ";
       // str.close();
        char[] ch2 = str2.toCharArray();
        show(ch2);
       // char[][] ok = req_2d(ch2);
        //System.out.println(one_place(1,1));
        boolean empty = false;
        // errror in ENTER THE COORDINATES
        int turn1 = 0;
        do{
            boolean b = coordinates_chk(ch2,turn1);//alternate turns
            if(b) turn1++;
            empty = empty_cell(ch2);
            if(turn1>4){
                if(stateful(ch2)) break;
                }
            } while (empty);
      //  while(empty)
        if(!empty){
            stateful(ch2);
        }

            //empty = empty_cell(ch2);
            //System.out.println(empty);


    }
    static boolean empty_cell(char[] array) {
        boolean flag =true;// true means cell empty and next input to take
        int c = 0;
        for(char x: array){
            if(x==' '){
                c++;
            }
        }
        if( c==0){
            flag = false;
        }
        return flag;
    }
    //converting input to linear format array
    static int one_place(int a, int b){
        int[][] temp3 = new int[3][3];
        /*temp3[0][2]=0;
        temp3[1][2]=1;
        temp3[2][2]=2;
        temp3[0][1]=3;
        temp3[1][1]=4;
        temp3[2][1]=5;
        temp3[0][0]=6;
        temp3[1][0]=7;
        temp3[2][0]=8;*/
        int[][] temp4 = new int[3][3];
        int k = 0;
        for(int i = 2; i>=0;i--){
            for(int j = 0; j<=2;j++){
                temp4[j][i]=k++;
            }
        }
        return (temp4[a-1][b-1]);


    }

    static boolean coordinates_chk(char[] array1, int tn) {
        boolean flag = false;
        int int_input_count = 0;
        int a=0,b=0;
        int turn = tn;
        var sc_def = new Scanner(System.in);
       // sc_def.next();
        System.out.print("Enter the coordinates: ");
      //ERROR ----------------------------------------------- var a1 = sc_def.next();
        //checking if 1st input is number
        if(sc_def.hasNextInt()){
        a = sc_def.nextInt();
        int_input_count++;
            if(sc_def.hasNextInt()){        //checking if 2nd input is number
                b = sc_def.nextInt();
                int_input_count++;
            } else{
                System.out.println("You should enter numbers!");
            }
        } else{
            System.out.println("You should enter numbers!");
        }
       // sc_def.close();
        if(int_input_count==2) {
            if (a <= 3 && a >= 1 && b <= 3 && b >= 1) {
                int value = one_place(a , b );
                if (array1[value] != 'X' && array1[value] != 'O') {
                    //System.out.println(turn);
                   // turn++;
                   // System.out.println(turn);
                    if(turn%2==0){
                        array1[value] = 'X';
                    } else if(turn%2!=0){
                        array1[value] = 'O';
                    }

                    flag = true;
                    //printing full matrix code using show
                    //..................................
                    show(array1);
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    flag = false;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        } else{
            flag = false;
        }

        return flag;
    }

    static char[][] req_2d(char[] ch ) {
        //creating values for 2d array
        char[][] temp1 = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp1[i][j] = ch[3 * i + j];
                System.out.print(temp1[i][j]+" ");
            }System.out.println();
        }
        char[][] temp2 = new char[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                int x = Math.abs(i - 2);
                temp2[j][i] = temp1[x][j];
                System.out.print(temp1[j][i]+" ");
            }System.out.println();
        }
        return temp2;
    }



    static void show(char[] ch1){
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + ch1[i] + " " + ch1[i + 1] + " " + ch1[i + 2] + " |");
        }
        System.out.println("---------");
       // stateful(ch1);

    }
    static boolean stateful(char[] ch){
        int x=0;
        int o=0;
        int blank = 0;
        boolean flag = false;
        for (int i = 0; i < 9; i++) { //checking number of X and O;
            if (ch[i] == 'X') x++;
            else if (ch[i] == 'O') o++;
            else blank++;

        }
        int res = -2;
        int rx = 0, ro = 0, cx = 0, co = 0;
        if (Math.abs(x - o) <= 1) { // proceeding if difference is not greater than >=2
            res = res + 1;
            //System.out.println("Inside c1");
            for (int i = 0; i < 9; i += 3) { // checking number of fully rows with x or o
                if (ch[i] == ch[i + 1] && ch[i + 1] == ch[i + 2]) {
                  //  System.out.println("L1");
                    if (ch[i] == 'X') {
                      //  System.out.println("L2");
                        rx++;
                    } else if (ch[i] == 'O') {
                        ro++;
                    }
                }
            }
           // System.out.println(rx+" "+ro);
            //check if no two rows are filled
            if (rx >= 1 && ro >= 1) {
              //  System.out.println("Inside c2");
                System.out.println("Impossible");
            } else {
                for (int i = 0; i < 3; i++) {// checking number of fully columns with x or o
                    if (ch[i] == ch[i + 3] && ch[i + 3] == ch[i + 6]) {
                        if (ch[i] == 'X') {
                            cx++;
                        } else if (ch[i] == 'O') {
                            co++;
                        }
                    }
                }
              //  System.out.println(cx+" "+co);
                //check if no two rows are filled
                if (cx >=1 && co >=1) {
                   // System.out.println("Inside itoo");
                    System.out.println("Impossible");
                } else { //now checking if any col or row variable ==1 and declare them winner



                    if (rx > 0 || cx > 0) {
                        System.out.println("X wins");
                        flag = true;
                    } else if (ro > 0 || co > 0) {
                        System.out.println("O wins");
                        flag = true;
                    } else if(ch[0]==ch[4] && ch[4] == ch[8] && (ch[4]=='X' || ch[4]=='O')) { // diagonal check
                        System.out.println(ch[0] + " wins");
                        flag = true;
                    }
                    else if(ch[2]==ch[4] && ch[4] == ch[6] && (ch[4]=='X' || ch[4]=='O')) { //diagonal check
                        System.out.println(ch[0] + " wins");
                        flag = true;
                    } else if (x + o == 9) { //checking for draw if all elements are filled
                        System.out.println("Draw");
                    } else if (x + o + blank == 9)
                        System.out.println("Game not finished");
                }
            }
        } else {
            //System.out.println("Inside c3");
            System.out.println("Impossible");
        }
        return flag;
    }
}





