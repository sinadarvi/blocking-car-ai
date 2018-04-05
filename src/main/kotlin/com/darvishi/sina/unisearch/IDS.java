package com.darvishi.sina.unisearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IDS {
    ArrayList<String> path = new ArrayList<String>();
    long time1 ;
    long time2 ;
   // ArrayList<state> VisitedList = new ArrayList<state>();
    ArrayList<state> ChildList = new ArrayList<state>();
    Stack<state> fringe = new Stack<>(); 
    boolean find = false;
    state current;
    int nodeCounter = 0;
    int maxNodeCounter = 0;
    int maxparentnumber ;
    state start ;
    state end ;
    successor s ;
    
    public IDS(state FirstSt , state FinalSt ){
        time1 = System.currentTimeMillis();
        start = FirstSt;
        end = FinalSt ;
        start();
        
        
        
    }

    public void start() {
        int range = 0;

       
        // VisitedList.add(start);
        outer :
        while (true) {
 fringe.push(start);
            while (!fringe.isEmpty()) {
                current = fringe.pop();
                //    VisitedList.add(current);
                if (current.compare(end)) {
                    find = true;
                    time2 = System.currentTimeMillis();
                    break outer;
                    // print(true);
                }
                ChildList.clear();
                if (current.depth < range){
                    s = new successor(current);
                    ChildList = s.GetSuccessors();
                }else{
                 ChildList = new ArrayList<>();
                }
                for (state i : ChildList) {
                    nodeCounter++;
                    if (!isGenerated(i)) {
                        fringe.add(i);
                    }
                }
                if (fringe.size() + maxparentnumber > maxNodeCounter) {
                    maxNodeCounter = fringe.size() + maxparentnumber;
                }

            }
            range++;

        }

        if (!find) {
            print(false);
        } else {
            print(true);
        }
    }

    public boolean isGenerated(state s) {
        state temp;
        temp = current ;//copy(current);
        maxparentnumber = 0 ;
        while (temp.parent != null) {
            
            if (s.compare(temp)) {
                return true;
            }
            maxparentnumber ++ ;
            
            temp = temp.parent;

        }
        

        return false;
    }



    public void print(boolean result) {
        System.out.println("IDS:");
        System.out.println();
        if (result) {
                       
            while (current.parent != null) {
                path.add(current.PathFromParent);
                current = current.parent;
            }
            System.out.println("action : "+path.size());
            System.out.println("Path:");
            for (int i = path.size()-1 ; i >= 0 ; i--) {
                System.out.println(path.get(i));
            }
            
        } else {
            System.out.println("No Soloution");
        }
        System.out.println();
       // System.out.println("Max Parent Number :" + maxparentnumber);
        System.out.println("Number of gnarated nodes : " + (nodeCounter+2));
        System.out.println("Max number of node in ram : " + (maxNodeCounter+2));
        System.out.println("Time = " + ((float) (time2 - time1) / (float) 1000) + " Sec");
        System.out.println();
    }
    
    
    state co;
    private state copy(state s) {
        co = new state(null, s.row, s.column);
        co.array = new char[s.row][s.column];
        co.parent = s.parent ;
        
//        s = new state(null, curent.row, curent.column);
//        s.array = new char[row][col];
        co.PathFromParent= s.PathFromParent ;
        for (int i = 0; i < s.row; i++) {
            for (int j = 0; j < s.column; j++) {
                co.array[i][j] = s.array[i][j];
            }
        }
        return co;
    }


}