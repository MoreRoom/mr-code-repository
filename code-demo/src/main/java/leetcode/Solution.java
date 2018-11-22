package leetcode;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

//        final int MAX_NEXT_FLAG = 9;
//        final int MOVE_SIZE = 4;
        boolean firstFlag = true;
        int forward = 0;
//        ListNode listNode = new ListNode(0);
        int l1start = l1.val;
        int l2start = l2.val;
        if (l1start == 0) return l2;
        if (l2start == 0) return l1;
        ListNode l = null;
        ListNode t = null;
        while (l1 != null && l2 != null) {
            int temp = 0;
            if (firstFlag) {
                l = new ListNode((temp = (l1.val + l2.val + forward)) % 10);
                firstFlag = false;
                l1 = l1.next;
                l2 = l2.next;
                t = l;
            } else {
                t.next = new ListNode((temp = (l1.val + l2.val + forward)) % 10);
                l1 = l1.next;
                l2 = l2.next;
                t = t.next;
            }
            forward = temp >= 10 ? 1 : 0;
        }
        ListNode s = null;
        if (l1 == null && l2 == null) {
            if (forward == 1) {
                t.next = new ListNode(1);
                return l;
            } else {
                return l;
            }
        } else if (l1 == null) {
            s = l2;
        } else if (l2 == null) {
            s = l1;
        }
        while (s != null) {
            t.next = new ListNode((s.val + forward) % 10);
            forward = s.val + forward >= 10 ? 1 : 0;
            s = s.next;
            t = t.next;
            if (forward == 0) {
                if (s != null) {
                    while (s != null) {
                        t.next = s;
                        t = t.next;
                        s = s.next;
                    }
                }
            }
        }
        if (forward == 1) {
            t.next = new ListNode(1);
        }
        return l;
        //
//        int i1 = 0;
//        int i2 = 0;
//        int count = 0;
//        do {
//            if (count < 7) {
//                i1 += (l1.val << MOVE_SIZE * count);
//                i2 += (l2.val << MOVE_SIZE * count);
//
//                l1 = l1.next;
//                l2 = l2.next;
//
//                count++;
//            }else{
//                count = 0;
//
//            }
//
//
//        } while (l1.next != null && l2.next != null);

        // int i = 10; // 0000 0000 0000 0000  0000 0000 0000 1010
        //1001
        //1000
        //0111
        //0110
        //0101
        //0100
        //0011
        //0010
        //0001
        // 节点1 节点2 节点3
        // 0000 1001 0101     //
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(9);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(3);
//        ListNode l14 = new ListNode(9);
        l11.next = l12;
//        l12.next = l13;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
//        ListNode l24 = new ListNode(1);
//        l21.next = l22;
//        l22.next = l23;

        Solution s = new Solution();
        ListNode res = s.addTwoNumbers(l11, l21);
        while (res != null) {
            System.out.print(res.val + "\t");
            res = res.next;
        }
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}