package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
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

    public FillEmptySpace(Board board, Color color){
        this.inMap = board.inMap;
        this.citiesBoolean = board.citiesBoolean;
        emptySpace = new ArrayList<Vector2>();

        this.board = board;

        addEmptySpaceToList();

        fillEmptySpaceOnMap(color);
    }

    private void fillEmptySpaceOnMap(Color color){

        Random random = new Random();
        int rand;

        while(!isEmpty()){
            rand = random.nextInt(emptySpace.size());
            addNewCity(emptySpace.get(rand), randomColor(random, color));
            //updateEmptySpace();
            emptySpace.clear();
            addEmptySpaceToList();
        }
    }

    private Color randomColor(Random random, Color color){

        int a = random.nextInt(5);
        switch (a){
            case 0:
                color = Color.BLACK;
                break;
            case 1:
                color = Color.SKY;
                break;
            case 2:
                color = Color.BROWN;
                break;
            case 3:
                color = Color.CORAL;
                break;
            case 4:
                color = Color.GOLDENROD;
                break;
        }
        return color;
    }

    private void addNewCity(Vector2 point, Color color){

        City newCity = new City(5, "aaa", board, color);
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
