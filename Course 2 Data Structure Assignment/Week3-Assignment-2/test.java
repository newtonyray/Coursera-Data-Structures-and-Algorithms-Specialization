import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test {
    private int[] data;
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;
    private Worker[] workers;

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        //long[] nextFreeTime = new long[numWorkers];
        workers = new Worker[numWorkers];
        for (int i = 0; i< numWorkers; i++){
            workers[i] = new Worker(i, 0);
        }
        for (int j = 0 ; j < jobs.length; j++){
            for(Worker w : workers){
                System.out.println("ID: " + w.getID() + " next free time: " + w.getTime());
            }
            assignedWorker[j] = workers[0].getID();
            startTime[j] = workers[0].getTime();
            workers[0].setTime(jobs[j]);
            siftDown(0);
        }
    }
    
    private void siftDown(int i){
        int minIdx = i;
        if(2 * i + 1 <= workers.length -1 && 2 * i + 2 <= workers.length -1 
        && workers[2 * i + 1].getTime() == workers[2 * i + 2].getTime() 
        && workers[2 * i + 1].getID() > workers[2 * i + 2].getID() ){
            Worker temp = workers[2 * i + 1];
            workers[2 * i + 1] = workers[2 * i + 2];
            workers[2 * i + 2] = temp;
        }
        if(2 * i + 1 <= workers.length -1 && workers[i].behind(workers[2 * i + 1])){
            minIdx = 2 * i + 1;
        }
        if(2 * i + 2 <= workers.length -1 && workers[minIdx].behind(workers[2 * i + 2])){
            minIdx = 2 * i + 2;
        }
        if (minIdx != i){
            Worker temp = workers[i];
            workers[i] = workers[minIdx];
            workers[minIdx] = temp;
            siftDown(minIdx);
        }
        
    }
    
    private class Worker{
        private int nextFreeTime;
        private int id;
        public Worker(int id, int time){
            this.id = id;
            nextFreeTime = time;
        }
        
        public void setTime(int time){
            nextFreeTime += time; 
        }
        
        public int getID(){
            return id;
        }
        
        public int getTime(){
            return nextFreeTime; 
        }
        
        public boolean behind(Worker w){
            
            if (this.getTime() < w.getTime()){
                return false;
            }
            else if (this.getTime() > w.getTime()){
                return true;
            }
            else {
                if (this.getID() > w.getID()){return true;}
                else{return false;}
            }
            
        }

    }
    
    public void main() {
        HashMap<Integer, String> test = new HashMap<>();
        test.put(1,"One");
        test.put(2,"Two");
        test.put(3,"Three");
        
        for(Map.Entry e : test.entrySet()){
            System.out.println(e.values());
        }
        
        
      
    }
}
