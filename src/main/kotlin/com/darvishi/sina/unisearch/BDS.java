package com.darvishi.sina.unisearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BDS {
    
    ArrayList<String> path1 = new ArrayList<String>();
    ArrayList<String> path2 = new ArrayList<String>();
    
    long time = System.currentTimeMillis();
    
    ArrayList<state> VisitedList1 = new ArrayList<state>();
    ArrayList<state> VisitedList2 = new ArrayList<state>();
    
    ArrayList<state> ChildList1 = new ArrayList<state>();
    ArrayList<state> ChildList2 = new ArrayList<state>();
    
    Queue<state> fringe1 = new LinkedList<>();
    Queue<state> fringe2 = new LinkedList<>();
    
    successor successor1 ;
    successor successor2 ;
    
    int depth1 ;
    int depth2 ;
    
    boolean find = false;
    
    int nodeCounter = 0;
    int maxNodeCounter = 0;
    state start ;
    state mid1 ;
    state mid2 ;
    state end ;
   
    
    public BDS(state FirstSt , state FinalSt){
        start = FirstSt;
        end = FinalSt ;
        
        mid1 = FirstSt ;
        mid2 = FinalSt ;
        start();
        
        
        
    }

    public void start() {
        
        
        fringe1.add(start);
        fringe2.add(end);
      //  VisitedList.add(start);
        while (true) {
            
            //compare
            if (CheckFring()) {
                find = true;
                break;
            }
            //=======================================================================================
            
            while (mid1.depth == depth1) {
                mid1 = fringe1.poll();
                VisitedList1.add(mid1);
                successor1 = new successor(mid1);
                ChildList1 = successor1.GetSuccessors();
                nodeCounter += ChildList1.size() ;
                
                for (state i : ChildList1) {
                    if (!isGenerated1(i)) {
                        fringe1.add(i);
                    }
                }
                ChildList1.clear();
            }

            
            depth1 ++ ;
            //=================================================================================
            //compare
            if (CheckFring()) {
                find = true;
                break;
            }
            //=================================================================================
            ChildList2.clear();
            while (mid2.depth == depth2) {
                mid2 = fringe2.poll();
                VisitedList2.add(mid2);
                successor2 = new successor(mid2);
                ChildList2 = successor2.GetSuccessors();
                nodeCounter += ChildList2.size();
                
                for (state i : ChildList2) {
                    if (!isGenerated1(i)) {
                        fringe2.add(i);
                    }
                }
                ChildList2.clear();

            }

            depth2 ++ ;
            
            //======================================================================================
            //nodeCounter += ChildList1.size()+ChildList2.size();
            
            if (fringe1.size()+fringe2.size()+ VisitedList1.size()+VisitedList2.size() > maxNodeCounter) {
                maxNodeCounter = fringe1.size()+fringe2.size()+ VisitedList1.size()+VisitedList2.size();
            }

        }

        if (!find) {
            print(false);
        } else {
            print(true);
        }
    }

    public boolean isGenerated1(state s) {

        for (state t : VisitedList1) {
            if (s.compare(t)) {
               // System.out.println("yess");
                return true;
              
            }
        }
        return false;
    }
    public boolean isGenerated2(state s) {

        for (state t : VisitedList2) {
            if (s.compare(t)) {
               // System.out.println("yess");
                return true;
              
            }
        }
        return false;
    }
    
    public boolean CheckFring(){
        for (state f1 : fringe1) {
            for (state f2 : fringe2) {
                if(f1.compare(f2)){
                    mid1 = f1 ;
                    mid2 = f2 ;
                    return true ;
                }
            }
        }
        return false ;   
    }



    public void print(boolean result) {
        System.out.println("BDS:");
        System.out.println();
        if (result) {
                       
            while (mid1.parent != null) {
                path1.add(mid1.PathFromParent);
                mid1 = mid1.parent;
            }
            while (mid2.parent != null) {
                path2.add(mid2.PathFromEnd);
                mid2 = mid2.parent;
            }
            System.out.println("action : "+( path1.size() + path2.size()));
            System.out.println("Path:");
            
            for (int i = path1.size()-1 ; i >= 0 ; i--) {
                System.out.println(path1.get(i));
            }
            for (int i = 0; i < path2.size() ; i++) {
                
                System.out.println(path2.get(i));
            }
            
        } else {
            System.out.println("No Soloution");
        }
        System.out.println();
        System.out.println("Number of gnarated nodes : " + (nodeCounter+2));
        System.out.println("Max number of node in ram : " + (maxNodeCounter+2));
        System.out.println("Time = " + ((float) (System.currentTimeMillis() - time) / (float) 1000) + " Sec");
        System.out.println();
    }

}