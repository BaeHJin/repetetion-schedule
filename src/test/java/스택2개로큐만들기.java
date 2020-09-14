import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class 스택2개로큐만들기 {
    @Test
    public  void mainTest() {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        int[] n = {10,
                1, 42,
                2,
                1, 14,
                3,
                1, 28,
                3,
                1, 60,
                1, 78,
                2,
                2};

        for (int i = 0; i < n.length; i++) {
            int operation = n[i];
            if (operation == 1) { // enqueue
                queue.enqueue(n[i+1]);
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
    }

    public static class MyQueue<T> {

        private Stack<T> fifo;
        private Stack<T> lifo;


        public MyQueue(){
        
            this.fifo = new Stack<T>();
            this.lifo = new Stack<T>();
            
        }

        public void enqueue(T num){
            this.fifo.push(num);
        }

        public void dequeue(){

            if(!lifo.empty()){
                this.lifo.pop();
            } else {

                while(!fifo.empty()){
                    T b = this.fifo.pop();
                    this.lifo.push(b);
                }
                this.lifo.pop();
            }





        }

        public Object peek(){

            if(!lifo.empty()){

                return this.lifo.peek();

            } else {
                while(!fifo.empty()){
                    this.lifo.push(this.fifo.pop());
                }

                return this.lifo.peek();
            }


        }

    }


    @Test
    public void test()    {

        String e = "}][}}(}][))]";
        String a = isBalanced(e);
        Assert.assertEquals("YES", a);

    }


    public String isBalanced(String s) {

        Stack<String> stack = new Stack();
        String[] array = s.split("");

        for(int i=0; i < array.length; i++){

            if(isLeft(array[i])){
                stack.push(array[i]);
            } else{

                if(stack.empty())
                    return "NO";

                if( isBalanced(stack.peek(), array[i]) )
                    stack.pop();
                else
                    return "NO";

            }
        }

        if(stack.empty())
            return "YES";
        else
            return "NO";

    }

    public boolean isBalanced(String peek, String s){
        if( peek.equals("{") && s.equals("}") )
            return true;

        if( peek.equals("[") && s.equals("]") )
            return true;

        if( peek.equals("(") && s.equals(")") )
            return true;

        return false;
    }

    public boolean isLeft(String s){
        if( "{".equals(s) || "[".equals(s) || "(".equals(s) )
            return true;
        else
            return false;
    }

}
