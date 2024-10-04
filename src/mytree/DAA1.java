package mytree;

public class DAA1 extends MyTree {

    // Binary Search Tree (BST)
    // 1. isBST() [20 points]
    public static boolean isBST(MyTree t) {
    	return isBST(t, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBST(MyTree t,  int lowerBound, int upperBound) {
    	if (t.getEmpty()) {
            return true;
        }

        int value = t.getValue();

        if (value > upperBound || value < lowerBound) {
        	
            return false;
        }

        return isBST(t.getLeft(), lowerBound, value - 1) && isBST(t.getRight(), value + 1, upperBound);
    }


    // 2. printDescending() [10 points]
    public static void printDescending(MyTree t) {
    	if (t != null && !t.getEmpty()) {
           
            printDescending(t.getRight());

            System.out.print(t.getValue() + " ");

            printDescending(t.getLeft()); 
    	}
    }

    // 3. max() [10 points]   
    /**
     * You have to:
     * - handle empty trees
     * - never look at left
     * - never compares values, i.e., the value of t and the right,
     *   because it's not necessary if it's BST.
     * - returns the right value as soon as found
     *
     * @param t is the tree being searched for the max value
     * @return the max value of tree t
     */
    public static int max(MyTree t) {
    	if (t == null || t.getEmpty()) {
            throw new IllegalArgumentException("Empty BST!");
        }

        while (!t.getRight().getEmpty()) {
            t = t.getRight();
        }

   
        return t.getValue();
    }

}