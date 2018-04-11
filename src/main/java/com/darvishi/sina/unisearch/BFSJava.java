package com.darvishi.sina.unisearch;

import com.darvishi.sina.Car;

import java.util.ArrayList;

public class BFSJava {

    private int[][] getMatres(ArrayList<Car> cars) {
        int matres[][] = new int[6][6];

        for (Car car : cars) {
            switch (car.getDir()) {
                case 'h':
                    for (int i = 0; i < car.getSize(); i++)
                        matres[car.getColumn() - 1 + i][car.getRow() - 1] = car.getIndex();
                case 'v':
                    for (int i = 0; i < car.getSize(); i++)
                        matres[car.getColumn() - 1][car.getRow() - 1 + i] = car.getIndex();
            }
        }

        return matres;
    }
}
