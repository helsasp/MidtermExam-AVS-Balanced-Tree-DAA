package mytree;

public class DAA2 extends DAA1 {

    // 4. isHeightBalanced() [10 points]
    public static boolean isHeightBalanced(MyTree t) {
        if (t.getEmpty()) {
            return true;
        } else {
            int left_height = findHeight(t.getLeft());
            int right_height = findHeight(t.getRight());

            if (Math.abs(left_height - right_height) > 1) {
                return false;
            }

            return isHeightBalanced(t.getLeft()) && isHeightBalanced(t.getRight());
        }
    }


    // 5. insertHB() [10 points]
    public static MyTree insertHB(int n, MyTree t) {
        if (t.getEmpty()) {
            return new MyTree(n, emptyTree, emptyTree);
        } else if (n < t.getValue()) {
            MyTree new_Left = insertHB(n, t.getLeft());
            return rebalanceForLeft(new_Left, t.getRight(), t.getValue());
        } else {
            MyTree new_Right = insertHB(n, t.getRight());
            return rebalanceForRight(t.getLeft(), new_Right, t.getValue());
        }
    }
    
    // 6. rebalanceForLeft() [15 points]
    private static MyTree rebalanceForLeft(MyTree left, MyTree right, int value) {
        if (findHeight(left) - findHeight(right) > 1) {
            if (findHeight(left.getLeft()) >= findHeight(left.getRight())) {
                return rotateRight(left, right, value);
            } else {
                left = rotateLeft(left.getLeft(), left.getRight(), left.getValue());
                return rotateRight(left, right, value);
            }
        }
        return new MyTree(value, left, right);
    }

    // 7. rebalanceForRight() [15 points]
    private static MyTree rebalanceForRight(MyTree left, MyTree right, int value) {
        if (findHeight(right) - findHeight(left) > 1) {
            if (findHeight(right.getRight()) >= findHeight(right.getLeft())) {
                return rotateLeft(left, right, value);
            } else {
                right = rotateRight(right.getRight(), right.getLeft(), right.getValue());
                return rotateLeft(left, right, value);
            }
        }
        return new MyTree(value, left, right);
    }
    
    

    // 8. deleteHB() [10 points]
    /**
     * Deletes the value 'x' from the given tree, if it exists, and returns the new
     * Tree. Otherwise, the original tree will be returned.
     */
 // 

   
    public static MyTree deleteHB(MyTree t, int x) {
        if (t.getEmpty()) {
            return t; // Value not found, return original tree
        }

        if (x < t.getValue()) {
            MyTree newLeft = deleteHB(t.getLeft(), x);
            return rebalanceForRight(newLeft, t.getRight(), t.getValue());
        } else if (x > t.getValue()) {
            MyTree newRight = deleteHB(t.getRight(), x);
            return rebalanceForLeft(t.getLeft(), newRight, t.getValue());
        } else {
            if (t.getLeft().getEmpty()) {
                return t.getRight();
            } else if (t.getRight().getEmpty()) {
                return t.getLeft();
            } else {
                int minRight = findMin(t.getRight());
                MyTree newRight = deleteHB(t.getRight(), minRight);
                return rebalanceForLeft(t.getLeft(), newRight, minRight);
            }
        }
    }

    // Helper Functions 
    
    private static MyTree rotateRight(MyTree left, MyTree right, int value) {
        return new MyTree(left.getValue(), left.getLeft(), new MyTree(value, left.getRight(), right));
    }

    private static MyTree rotateLeft(MyTree left, MyTree right, int value) {
        return new MyTree(right.getValue(), new MyTree(value, left, right.getLeft()), right.getRight());
    }

    private static int findHeight(MyTree t) {
        if (t.getEmpty()) {
            return 0;
        } else {
            return 1 + Math.max(findHeight(t.getLeft()), findHeight(t.getRight()));
        }
    }
    
    private static int findMin(MyTree t) {
        if (t.getLeft().getEmpty()) {
            return t.getValue();
        }
        return findMin(t.getLeft());
    }

   
    }

   

