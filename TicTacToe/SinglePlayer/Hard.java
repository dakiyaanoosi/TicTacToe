package TicTacToe.SinglePlayer;

import java.util.Scanner;
class Hard
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
        for(int i=0;i<s.length()+5;i++)
        {
            try
            {
                Thread.sleep(60);
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
            if(player[turn]=='X')
            System.out.println("\n X Wins !\n");
            else
            System.out.println("\n Computer Wins !\n");
            return;
        }
        else
        {
            System.out.println("\nIt's a Tie !\n");
            return;
        }
    }
    
    static boolean winningMove()
    {
        int [][] winpatterns={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i[] : winpatterns)
        {
            if(board[i[0]]==board[i[1]] && board[i[0]]=='O' &&  board[i[2]]==' ')
            {
                position=i[2];
                return true;
            }
            else if(board[i[0]]==board[i[2]] && board[i[0]]=='O'  &&  board[i[1]]==' ')
            {
                position=i[1];
                return true;
            }
            else if(board[i[1]]==board[i[2]] && board[i[1]]=='O'  &&  board[i[0]]==' ')
            {
                position=i[0];
                return true;
            }
        }
        return false;
    }

    static boolean blockingMove()
    {
        int [][] winpatterns={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i[] : winpatterns)
        {
            if(board[i[0]]==board[i[1]] && board[i[0]]=='X' &&  board[i[2]]==' ')
            {
                position=i[2];
                return true;
            }
            else if(board[i[0]]==board[i[2]] && board[i[0]]=='X'  &&  board[i[1]]==' ')
            {
                position=i[1];
                return true;
            }
            else if(board[i[1]]==board[i[2]] && board[i[1]]=='X'  &&  board[i[0]]==' ')
            {
                position=i[0];
                return true;
            }
        }
        return false;
    }

    static boolean occupyCorner()
    {
        int arr[]={0,2,6,8};
        for(int i : arr)
        {
            if(board[i]==' ')
            {
                position=i;
                return true;
            }
        }
        return false;
    }

    static void algo()
    {
        if(board[4]==' ')
        {
            position=4;
            return;
        }
        if(counter<3)
        {
            if(occupyCorner()==true)
            return;
            do
            {
                position = (int) (Math.random()*9);
            }
            while(board[position]!=' ');
        }
        else
        {
            if(winningMove()==true)
            return;
            if(blockingMove()==true)
            return;
            if(occupyCorner()==true)
            return;
            do
            {
                position = (int) (Math.random()*9);
            }
            while(board[position]!=' ');
        }
    }

    static void input(Scanner sc)
    {
        if(player[turn]=='X')
        {
            try
            {
                System.out.print("\n\nPlayer 'X' ! Enter a position (1-9) : ");
                char pos=sc.nextLine().charAt(0);
                while(true)
                {
                    if(Character.isDigit(pos))
                    {
                        position=(pos-'0')-1;
                        if(position<0 || position>8)
                        System.out.print("\n\nPlayer 'X' ... Please enter valid position : ");
                        else if(board[position]!=' ')
                        System.out.print("Block Already Filled! Choose different position : ");
                        else
                        break;
                    }
                    else
                    System.out.print("\n\nPlayer 'X' ... Please enter valid position : ");
                    pos=sc.nextLine().charAt(0);
                }
            }
            catch(Exception e)
            {
                System.out.println(e+" Restart Game !");
            }
        }
        else
        {
            algo();
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
                if(turn==1 || counter==7)
                {
                    System.out.println("\nComputer");
                }
                print();
                counter++;
                if(counter>4)
                {
                    if(checkWinner()==true)
                    {
                        if(player[turn]=='X')
                        System.out.println("\nCongratulations! Player "+player[turn]+" Wins !\n");
                        else
                        System.out.println("\nComputer Wins !\n");
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
        Hard.play(sc);
        sc.close();
    }
}