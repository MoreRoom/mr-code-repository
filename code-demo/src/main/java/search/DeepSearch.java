package search;

/**
 * @ClassName DeepSearch
 * @Description 深搜算法
 * @Author MoreRoom
 * @Since 2018/10/30
 */
public class DeepSearch extends BaseData {

    public static void main(String[] args) {
        DeepSearch deepSearch = new DeepSearch();
        deepSearch.findBiggest(TWO_DIMENSIONAL_ARRAY);
    }

    /**
     * 给定一个二维数组
     * 从左上角走到右下角,只允许向右或者向下
     * 路径上数字求和,求最大的路径值
     *
     * @param sourceArray
     * @return
     */
    public int findBiggest(int[][] sourceArray) {
        int maxX = sourceArray.length;
        int maxY = sourceArray[0].length;
        return walk(sourceArray[0][0], sourceArray, 0, 0, maxX, maxY);
    }

    /**
     * @param sum         当前和
     * @param sourceArray 源数组
     * @param x           x坐标
     * @param y           y坐标
     * @param maxX        x最大值
     * @param maxY        y最大值
     * @return
     */
    public int walk(int sum, int[][] sourceArray, int x, int y, int maxX, int maxY) {
        if (x + 1 == maxX && y + 1 == maxY) { // 如果走到重点则返回
            return sum;
        }
        int a1 = 0;
        int a2 = 0;
        if (x + 1 < maxX) { // 能向下移就向下移
            a1 = walk(sum + sourceArray[x + 1][y], sourceArray, x + 1, y, maxX, maxY);
        }
        if (y + 1 < maxY) { // 能向右移就向右移
            a2 = walk(sum + sourceArray[x][y + 1], sourceArray, x, y + 1, maxX, maxY);
        }
        return a1 > a2 ? a1 : a2;
    }

}
