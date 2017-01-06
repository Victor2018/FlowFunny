package datastructrue.victor.com;

/**
 * 冒泡排序算法
 * Created by victor on 2016/3/30 0030.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] datas = getSortDatas();
        System.out.println("冒泡排序前。。。。。。。。。");
        for (int i=0;i<datas.length;i++) {
            System.out.print(datas[i] + ",");
        }
        System.out.println();

        sort(datas);
        System.out.println("冒泡排序后。。。。。。。。。");
        for (int i=0;i<datas.length;i++) {
            System.out.print(datas[i] + ",");
        }
        System.out.println();
    }
    public static int[] sort (int[] datas) {
        int temp;
        for (int i=0;i<datas.length;i++) {
            for (int j=0;j<datas.length - 1;j++) {
                if (datas[j] > datas[j+1]) {
                    temp = datas[j];
                    datas[j] = datas[j+1];
                    datas[j+1] = temp;
                }
            }
        }
        return datas;
    }

    public static int[] getSortDatas () {
        int[] datas = new int[10];
        for (int i=0;i<datas.length;i++) {
            datas[i] = (int) (Math.random() * 100);
        }
        return datas;
    }
}
