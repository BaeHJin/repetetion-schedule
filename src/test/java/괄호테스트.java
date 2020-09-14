//import org.testng.annotations.Test;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.Stack;
//
//public class 괄호테스트 {
//    ///////////////////////////////////
//    public class A{
//        int add(int i, int j){
//            return i+j;
//        }
//    }
//
//    public class B extends A{
//        public static void main(String argv[]){
//            short s = 9;
//            System.out.println(add(s,6));
//        }
//    }
//
//    ///////////////////////////////////
//    interface  aa{
//        void t();
//    }
//
//    abstract  class bb implements aa{}
//
//    class bbb implements bb{
//        public void t() { ; }
//    }
//    class aaa extends  bbb{
//        void t(int s){;}
//    }
//    ///////////////////////////////////
//
//
//    /* test **** */
//    /* asdf /* tesart */ */
//    /* asdf  // asdfa */
//    // /*asdfasdf // //2qefa */
//
//    private Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
//    @Test
//    public void tesa(){
//
//        set.
//
//    String[] a = {"((()))"};
//
//    String test = a[0].substring(0,4);
//
//        Parser parser = new Parser();
//
//        for(int i=0; i<a.length; i++){
//            System.out.println(parser.isBalanced(a[i]));
//        }
//    }
//
//
//    public static class Parser {
//
//        private Stack<String> stack;
//
//        public Parser() {
//            this.stack = new Stack<String>();
//        }
//
//        public boolean isBalanced(String bracket) {
//
//            String[] brackets = bracket.split("");
//
//            for (int i = 0; i < brackets.length; i++) {
//
//                if (isLeft(brackets[i])) {
//                    this.stack.add(brackets[i]);
//                } else if (isRight(brackets[i])) {
//
//                    if (this.stack.size() == 0) {
//                        return false;
//                    }
//
//                    if (isBalanced(brackets[i], this.stack.peek())) {
//                        this.stack.pop();
//                    } else {
//                        return false;
//                    }
//                } else
//                    return false;
//            }
//
//            if(stack.size() == 0)
//                return true;
//            else
//                return false;
//        }
//
//        private boolean isBalanced(String bracket, String stackBracket) {
//
//            if (bracket.equals("]") && stackBracket.equals("["))
//                return true;
//
//            if (bracket.equals("}") && stackBracket.equals("{"))
//                return true;
//
//            if (bracket.equals(")") && stackBracket.equals("("))
//                return true;
//
//            return false;
//        }
//
//        private boolean isLeft(String bracket) {
//            if (bracket.equals("[") || bracket.equals("{") || bracket.equals("("))
//                return true;
//            else return false;
//        }
//
//        private boolean isRight(String bracket) {
//            if (bracket.equals("]") || bracket.equals("}") || bracket.equals(")"))
//                return true;
//            else return false;
//        }
//    }
//}
