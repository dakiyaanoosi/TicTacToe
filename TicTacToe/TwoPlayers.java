package TicTacToe;

import java.util.Scanner;

class TwoPlayers extends Thread
{
    static Character board[]={' ',' ',' ',' ',' ',' ',' ',' ',' '};
    static char player[]={'X','O'};
    static int turn=0;
    static int counter=0;
    static int position=-1;

    static void print()
    {
        System.out.println();
        int c=0;
        for(int i=0;i<9;i++)
        {
            c++;
            if(c<3)
            System.out.print(" "+board[i]+" |" );
            else
            {
                System.out.println(" "+board[i]+"  ");
                if(i!=8)
                System.out.println("------------");
                c=0;
            }
        }
    }

    static boolean checkWinner()
    {
        int [][] winpatterns={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i[] : winpatterns)
        {
            if(board[i[0]]==board[i[1]] && board[i[0]]==board[i[2]] && board[i[0]]!=' ')
            {
                return true;
            }
        }
        return false;
    }

    static void lastMove()
    {
        System.out.println();
        String s="Filling last position";
        for(int i=0;i<s.length()+8;i++)
        {
            try
            {
                sleep(14);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            if(i>s.length()-1)
            System.out.print(".");
            else
            System.out.print(s.charAt(i));
        }
        System.out.println();
                
        for(int i=0;i<9;i++)
        {
            if(board[i]==' ')
            {
                position=i;
                break;
            }
        }
        board[position]=player[turn];
        print();
        counter++;

        if(checkWinner()==true)
        {
            System.out.println("\n"+player[turn]+" Wins !\n");
            return;
        }
        else
        {
            System.out.println("\nIt's a Tie !\n");
            return;
        }
    }

    static void input(Scanner sc)
    {
        System.out.print("\n\nPlayer '"+player[turn]+"' ! Enter a position (1-9) : ");
        char pos=sc.nextLine().charAt(0);
        while(true)
        {
            if(Character.isDigit(pos))
            {
                position=(pos-'0')-1;
                if(position<0 || position>8)
                System.out.print("\n\nPlayer "+player[turn]+" ... Please enter valid position : ");
                else if(board[position]!=' ')
                System.out.print("Block Already Filled! Choose different position : ");
                else
                break;
            }
            else
            System.out.print("\n\nPlayer "+player[turn]+" ... Please enter valid position : ");
            pos=sc.nextLine().charAt(0);
        }
    }

    static void play(Scanner sc)
    {
        print();
        while(counter<9)
        {
            if(counter==8)
            {
                lastMove();
            }
            else
            {
                input(sc);

                board[position]=player[turn];

                print();
                counter++;
                if(counter>4)
                {
                    if(checkWinner()==true)
                    {
                        System.out.println("\nCongratulations! Player "+player[turn]+" Wins !\n");
                        return;
                    }
                }
                turn=1-turn;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        TwoPlayers.play(sc);
        sc.close();
    }
}