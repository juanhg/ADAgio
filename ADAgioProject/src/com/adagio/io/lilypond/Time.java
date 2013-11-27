package com.adagio.io.lilypond;

public class Time{
    private int first;
    private int second;

    public Time(Integer first, Integer second) { 
        this.first = first;
        this.second = second;
    }

    public int getFirst() { return first; }
    public int getSecond() { return second; }
    
    /**
     * Gets the default duration of a note in relation with time. 
     * @return If first and second are divisible returns first/second. 
     * In other case returns second
     */
    public int defaultNoteDuration(){
    
    	if(0 == (first % second) && (first <= second)){
    		return (first/second);
    	}
    	else{
    		return second;
    	}
    }
    
    public String toString(){
    	return Integer.toString(first) + "/" + Integer.toString(second);
    }
    
}
