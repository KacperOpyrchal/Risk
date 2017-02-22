package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Holota on 2017-02-18.
 */

public class FillEmptySpace {

    Board board;

    boolean[][] inMap;
    boolean[][] citiesBoolean;
    List<Vector2> emptySpace;

    public FillEmptySpace(Board board, Pixmap pixmap){
        this.inMap = board.inMap;
        this.citiesBoolean = board.citiesBoolean;
        emptySpace = new ArrayList<Vector2>();

        this.board = board;

        addEmptySpaceToList();

        fillEmptySpaceOnMap(pixmap);
    }

    private void fillEmptySpaceOnMap(Pixmap pixmap){

        Random random = new Random();
        int rand;

        while(!isEmpty()){
            rand = random.nextInt(emptySpace.size());
            addNewCity(emptySpace.get(rand), randomColor(random, pixmap));
            //updateEmptySpace();
            emptySpace.clear();
            addEmptySpaceToList();
        }
    }

    private Pixmap randomColor(Random random, Pixmap pixmap){

        int a = random.nextInt(5);
        switch (a){
            case 0:
                //color = Color.BLACK;
                pixmap = PixMaps.pixMapTwo;
                break;
            case 1:
                //color = Color.SKY;
                pixmap = PixMaps.pixMapThree;
                break;
            case 2:
                //color = Color.BROWN;
                pixmap = PixMaps.pixMapFour;
                break;
            case 3:
                //color = Color.CORAL;
                pixmap = PixMaps.pixMapFive;
                break;
            case 4:
                //color = Color.GOLDENROD;
                pixmap = PixMaps.pixMapSix;
                break;
        }
        return pixmap;
    }

    private void addNewCity(Vector2 point, Pixmap pixmap){

        City newCity = new City(5, "aaa", board, pixmap);
        board.cities.add(newCity);
    }

    private boolean validate(Vector2 point){

        int x = (int)(point.x);
        int y = (int)(point.y);

        return (inMap[x][y] && !citiesBoolean[x][y]);
    }

    private void addEmptySpaceToList(){

        Vector2 point = new Vector2(0,0);
        for(int i = 0; i < board.xSize; i++){
            for(int j = 0; j < board.ySize; j++){
                point.x = i;
                point.y = j;
                if(validate(point))
                    emptySpace.add(point);
            }
        }
    }

    private void updateEmptySpace(){

        for(int i = 0; i < emptySpace.size(); i++){
            if(isOccupied(emptySpace.get(i))){
                emptySpace.remove(i);
                i--;
            }
        }
    }

    private boolean isOccupied(Vector2 point){

        int x = (int)(point.x);
        int y = (int)(point.y);

        return citiesBoolean[x][y];
    }

    private boolean isEmpty(){
        return emptySpace.isEmpty();
    }

}
