package controller;

import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

public class Grid extends TilePane
{
    private int Rows = 20;
    private int Cols = 20;

    //array that holds cells
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    //loads array with cells that are normal or bombs
    public Grid(Timer timer) {
        for (int i = 0; i < 100; i++) {

            Tile bombs = new Tile(true,timer);

            tiles.add(bombs);
        }

        for (int j = 0; j < 300; j++) {

            Tile normalTile = new Tile(false,timer);

            tiles.add(normalTile);
        }

        //shuffles array
        Collections.shuffle(tiles);

        //loads random elements of the array into main tile
        for (int k = 0; k < 400; k++) {

            check(k);

            this.getChildren().add(tiles.get(k));
        }
    }

    //checks adjacent cells of current cell for bombs and counts them
    public void check(int index)
    {
        int counterBomb=0;

        for(int i = (index/Rows) - 1; i <= (index/Rows) + 1; i++)
        {
            for(int j = (index%Cols) - 1; j <= (index%Cols) + 1; j++)
            {
                try {

                    if (tiles.get(j+i*Rows).getIsBomb()) {

                        counterBomb++;

                        tiles.get(index).setBomb(counterBomb);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e ) {

                }catch (IndexOutOfBoundsException e){

                }
                }
            }
        }
}

