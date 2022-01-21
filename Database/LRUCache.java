import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * homework 2.1
 */
public class LRUCache{
    private Deque<Character> deque;
    private HashMap<Character, Integer> hashmap;
    private final int CACHE_SIZE;

    public LRUCache(int cacheSize){
        this.deque = new LinkedList<Character>();
        this.hashmap = new HashMap<Character, Integer>();
        this.CACHE_SIZE = cacheSize;
    }

    public Integer get(Character ch){
        //Since item is being retrieved, move item to head of the deque
        Integer i = hashmap.get(ch);
        if(i != null)
            set(ch, i);
        return i;
    }

    //update or append new data
    public void set(Character key, Integer value){
        //Check if cache size has been exceeded
        if( deque.size() == CACHE_SIZE ){
            //if capacity full and element is new, remove current tail and push new element
            if( hashmap.get(key) == null ){
                hashmap.remove( deque.pollLast() );
                hashmap.put( key,value );
                deque.push( key );
            }
            else{
                //if found in deque move item from current location to head of queue
                //Element in hashmap does need to be moved because hashmap doesn't keep order.
                //Instead, just update element value
                hashmap.put( key,value );
                deque.remove( key );
                deque.push( key );
            }
        }
        else { //else if(deque.size() < CACHE_SIZE)
            //if capacity is NOT full and key is new, add key to beginning of queue
            if( hashmap.get(key) == null ){
                hashmap.put( key,value );
                deque.push( key );
            }
            else{
                //if found in deque move item from current location to head of queue
                hashmap.put( key,value );
                deque.remove( key );
                deque.push( key );
            }
        }

    }

    //driver code
    private static void driverForLRUCache(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cacheSize=0;
        try{
            System.out.println("Demo for LRUCache program");
            System.out.println("Enter cache size: ");
            cacheSize = Integer.parseInt(br.readLine().trim()); //Change to whatever size
        }catch(Exception e){
            e.printStackTrace();
        }

        LRUCache l = new LRUCache( cacheSize );

        boolean endProgram = false;
        while( !endProgram ) {
            try {
                String[] input = {"", "", ""};
                while (!(input[0].equals("quit"))) {
                    System.out.println("Enter \"set [key] [value]\" or \"get [key]\" or \"quit\":");
                    System.out.println("Example: set a 12");
                    //Arrays.stream(input).forEach(System.out::println);
                    input = br.readLine().split(" ");
                    if (input[0].equals("get")) {
                        l.get(input[1].charAt(0));
                    } else if (input[0].equals("set")) {
                        l.set(input[1].charAt(0), Integer.parseInt(input[2]));
                    } else if (input[0].equals("quit")) {
                        endProgram = true;
                        continue;
                    } else if (input[1].length() > 1) {
                        throw new Exception("Invalid input");
                    }

                    System.out.println(l.deque);
                    System.out.println(l.hashmap);
					System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
				System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        //////////////////////////////////////
		//driver code for program
		//comment out if not testing
		///////////////////////////////////////
        driverForLRUCache();
    }
}