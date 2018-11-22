package leetcode;

/**
 * @ClassName FindMedianSortedArrays
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/25
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        FindMedianSortedArrays f = new FindMedianSortedArrays();
        double res = f.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(res + "");
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int array1Length = nums1.length;
        int array2Length = nums2.length;
        int totalLength = array1Length + array2Length;

        int interval = 0;
        int totalLengthox = 1;
        while (totalLength > totalLengthox) {
            totalLengthox <<= 1;
        }
        totalLengthox -= 1;
        while (totalLengthox > 1) {
            totalLengthox >>= 1;
            interval++;
        }
        boolean f = (totalLength & 0x01) == 1 ? true : false;
        if (f) {
            int targetIndex = totalLength / 2;
            return find(targetIndex, interval, array1Length, array2Length, nums1, nums2);
        } else {
            int targetIndex = totalLength / 2 - 1;
            double d1 = find(targetIndex, interval, array1Length, array2Length, nums1, nums2);
            targetIndex = totalLength / 2;
            double d2 = find(targetIndex, interval, array1Length, array2Length, nums1, nums2);
            return (d1 + d2) / 2;
        }
    }

    private double find(int targetIndex, int interval, int array1Length, int array2Length, int[] nums1, int[] nums2) {
        int currentIndex = 0;
        int h1 = 0;
        int h2 = 0;
        while (currentIndex + interval <= targetIndex) {
            h1 = h1 > array1Length - 1 ? array1Length - 1 : h1;
            h2 = h2 > array2Length - 1 ? array2Length - 1 : h2;
            if (nums1[h1] >= nums2[h2]) {
                if (array2Length == h2 + 1) { // 已经是结尾无法继续
                    while (currentIndex + interval <= targetIndex) {
                        currentIndex += interval;
                        h1 += interval;
                    }
                } else {
                    currentIndex += interval;
                    h2 += interval;
                }
            } else {
                if (array1Length == h1 + 1) { // 已经是结尾无法继续
                    while (currentIndex + interval <= targetIndex) {
                        currentIndex += interval;
                        h2 += interval;
                    }
                } else {
                    currentIndex += interval;
                    h1 += interval;
                }
            }
        }
        double res = 0;
        if (currentIndex == targetIndex) {
            return nums1[h1] > nums2[h2] ? nums1[h1] : nums2[h2];
        } else {
            while (currentIndex <= targetIndex) {
                currentIndex++;
                if (h1 == array1Length) {
                    h2 += 1;
                    if (currentIndex == targetIndex) {
                        res = nums2[h2];
                    }
                } else if (h2 == array2Length) {
                    h1 += 1;
                    if (currentIndex == targetIndex) {
                        res = nums1[h1];
                    }
                } else {
                    if (nums1[h1] >= nums2[h2]) {
                        h2 += 1;
                        if (currentIndex == targetIndex) {
                            res = nums2[h2];
                        }
                    } else {
                        h1 += 1;
                        if (currentIndex == targetIndex) {
                            res = nums1[h1];
                        }
                    }
                }
            }
        }
        return res;
    }

}
