import org.junit.jupiter.api.Test;

import java.util.Stack;

public class LargestRectangle {

    @Test
    public void test(){
    int[] a = {11, 11, 10, 10, 10};
    largestRectangle(a);
    }

    static long largestRectangle(int[] hist) {

        Stack<Integer> s = new Stack();
        int n = hist.length;
        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar


        int i = 0;
        while (i < n)
        {

            if (s.empty() || hist[s.peek()] <= hist[i])
                s.push(i++);
            else
            {
                tp = s.peek();
                s.pop();

                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);


                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;

    }
}
