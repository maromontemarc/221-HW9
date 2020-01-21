import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Board extends Application
{
// six row seven column
    private final int ROW = 6;
    private final int COL = 7;
    private Button column1 = new Button( "C1" );
    private Button column2 = new Button( "C2" );
    private Button column3 = new Button( "C3" );
    private Button column4 = new Button( "C4" );
    private Button column5 = new Button("C5" );
    private Button column6 = new Button( "C6" );
    private Button column7 = new Button( "C7" );
    private Button reset = new Button( "Reset" );
    private GridPane gridPane = new GridPane();
    private int[][] game = new int[ 6 ][ 7 ];
    private Color player = Color.RED;

    public void createGrid()
    {
        for( int i = 0; i < COL; i++ )
        {
            for( int j = 0; j < ROW; j++ )
            {
                Circle circle = new Circle( 20 );
                circle.setFill( Color.GREY );
                gridPane.add( circle, i, j );
            }
        }
    }

    public void createGame()
    {
        for( int i = 0; i < COL; i++ )
        {
            for( int j = 0; j < ROW; j++ )
            {
                game[j][i] = 0;
            }
        }
    }

    public int checkColumn( int col )
    {
        int row = 0;

        for (int i = 0; i < ROW; i++)
        {
            int num = game[i][col];

            if (num == 0)
            {
                row = i;
            }
        }

        return row;
    }

    public void swapPlayer()
    {
        if( player == Color.RED )
        {
            player = Color.BLACK;
        }else
        {
            player = Color.RED;
        }
    }

    public boolean checkFull()
    {
        boolean full = true;

        for( int i = 0; i < COL; i++ )
        {
            for( int j = 0; j < ROW; j++ )
            {
                if( game[j][i] == 0 )
                {
                    full = false;
                }
            }
        }

        if( full )
        {
            System.out.println( "Tie! \nPress Reset to play again.\n" );
            column1.setDisable(true);
            column2.setDisable(true);
            column3.setDisable(true);
            column4.setDisable(true);
            column5.setDisable(true);
            column6.setDisable(true);
            column7.setDisable(true);
        }

        return full;
    }

    public void checkWin( int row, int col )
    {
        int playerNum = game[row][col];
        int count = 0;
        boolean win = false;

        for( int i = 0; i < COL; i++ )
        {
            if( game[row][i] == playerNum )
            {
                count++;
            }else
            {
                count = 0;
            }

            if( count >= 4 )
            {
                win = true;
            }
        }

        for ( int j = 0; j < ROW; j++ )
        {
            if( game[j][col] == playerNum )
            {
                count++;
            }else
            {
                count = 0;
            }

            if( count >= 4 )
            {

                win = true;
            }
        }
        //
        for( int k = 2; k >= 0; k-- )
        {
            count = 0;
            int rowCount, colCount;

            for( rowCount = k, colCount = 0; rowCount < ROW && colCount < COL; rowCount++, colCount++ )
            {
                if( game[rowCount][colCount] == playerNum )
                {
                    count++;
                }else
                {
                    count = 0;
                }

                if( count >= 4 )
                {

                    win = true;
                }
            }
        }

        for( int l = 3; l >= 0; l-- )
        {
            count = 0;
            int rowCount, colCount;

            for( colCount = l, rowCount = 0; colCount < COL && rowCount < ROW; rowCount++, colCount++ )
            {
                if( game[rowCount][colCount] == playerNum )
                {
                    count++;
                }else
                {
                    count = 0;
                }

                if( count >= 4 )
                {

                    win = true;
                }
            }
        }

        for( int m = 3; m < COL; m++ )
        {
            count = 0;
            int rowCount, colCount;

            for( colCount = m, rowCount = 0; colCount >= 0 && rowCount < ROW; rowCount++, colCount-- )
            {
                if( game[rowCount][colCount] == playerNum )
                {
                    count++;
                }else
                {
                    count = 0;
                }

                if( count >= 4 )
                {

                    win = true;
                }
            }
        }

        for( int n = 2; n >= 0; n-- )
        {
            count = 0;
            int rowCount, colCount;

            for( rowCount = n, colCount = 6; rowCount < ROW && colCount >= 0; rowCount++, colCount-- )
            {
                if( game[rowCount][colCount] == playerNum )
                {
                    count++;
                }else
                {
                    count = 0;
                }

                if( count >= 4 )
                {

                    win = true;
                }
            }
        }


        if( win )
        {
            System.out.println( "Player " + playerNum + " Wins! \nPress Restart.\n");
            column1.setDisable(true);
            column2.setDisable(true);
            column3.setDisable(true);
            column4.setDisable(true);
            column5.setDisable(true);
            column6.setDisable(true);
            column7.setDisable(true);
        }else
        {
            checkFull();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println( "Player 1: Red\nPlayer 2: Black\n" );

        primaryStage.setTitle( "Connect 4" );

        HBox hBox = new HBox( 3, column1, column2, column3, column4,
                            column5, column6, column7 );
        hBox.setAlignment( Pos.CENTER );

        gridPane.setPadding( new Insets( 20) );

        gridPane.setGridLinesVisible( true );

        createGrid();
        createGame();

        column1.setOnAction( new C1Handler() );
        column2.setOnAction( new C2Handler() );
        column3.setOnAction( new C3Handler() );
        column4.setOnAction( new C4Handler() );
        column5.setOnAction( new C5Handler() );
        column6.setOnAction( new C6Handler() );
        column7.setOnAction( new C7Handler() );
        reset.setOnAction( new ResetHandler() );

        VBox vBox = new VBox( 20, hBox, gridPane, reset );
        vBox.setPadding( new Insets( 20 ) );
        vBox.setAlignment( Pos.CENTER );

        Scene scene = new Scene( vBox );
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        Application.launch( args );
    }

    class C1Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();

            int row = checkColumn( 0 );

            if( row == 0 && !(game[0][0] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][0] = 1;
                } else
                {
                    game[row][0] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,0, row );
                checkWin( row, 0 );
                swapPlayer();
            }
        }
    }

    class C2Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();
            int row = checkColumn( 1 );

            if( row == 0 && !(game[0][1] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][1] = 1;
                } else
                {
                    game[row][1] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,1, row );

                checkWin( row, 1 );

                swapPlayer();
            }
        }
    }

    class C3Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();
            int row = checkColumn( 2 );

            if( row == 0 && !(game[0][2] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][2] = 1;
                } else
                {
                    game[row][2] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,2, row );

                checkWin( row, 2 );

                swapPlayer();
            }
        }
    }

    class C4Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();
            int row = checkColumn( 3 );

            if( row == 0 && !(game[0][3] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][3] = 1;
                } else
                {
                    game[row][3] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,3, row );
                checkWin( row, 3 );

                swapPlayer();
            }
        }
    }

    class C5Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();
            int row = checkColumn( 4 );

            if( row == 0 && !(game[0][4] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][4] = 1;
                } else
                {
                    game[row][4] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,4, row );
                checkWin( row, 4 );

                swapPlayer();
            }
        }
    }

    class C6Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();
            int row = checkColumn( 5 );

            if( row == 0 && !(game[0][5] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][5] = 1;
                } else
                {
                    game[row][5] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,5, row );
                checkWin( row, 5 );

                swapPlayer();
            }
        }
    }

    class C7Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            boolean full = checkFull();
            int row = checkColumn( 6 );

            if( row == 0 && !(game[0][6] == 0) )
            {
                if( !full )
                {
                    System.out.println( "Column is full.\n" );
                }
            }else
            {
                if (player == Color.RED)
                {
                    game[row][6] = 1;
                } else
                {
                    game[row][6] = 2;
                }

                Circle circle = new Circle( 20 );
                circle.setFill( player );

                gridPane.add( circle,6, row );
                checkWin( row, 6 );

                swapPlayer();
            }
        }
    }

    class ResetHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            createGrid();
            createGame();
            column1.setDisable(false);
            column2.setDisable(false);
            column3.setDisable(false);
            column4.setDisable(false);
            column5.setDisable(false);
            column6.setDisable(false);
            column7.setDisable(false);
            player = Color.RED;
        }
    }
}
