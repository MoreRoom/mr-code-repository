package search;


/**
 * @ClassName DetectCycle
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/31
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // 步骤一：使用快慢指针判断链表是否有环
        ListNode slowPoint = head, fastPoint = head;
        boolean hasCycle = false;
        while (fastPoint.next != null && fastPoint.next.next != null) {
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if (slowPoint == fastPoint) {
                hasCycle = true;
                break;
            }
        }

        // 步骤二：若有环，找到入环开始的节点
        if (hasCycle) {
            ListNode newPoint = head;
            while (slowPoint != newPoint) {
                slowPoint = slowPoint.next;
                newPoint = newPoint.next;
            }
            return newPoint;
        } else {
            return null;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;

        }
    }
}