package datastructrue.victor.com;

/**
 * 选择排序算法
 * Created by victor on 2016/3/30 0030.
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] datas = getSortDatas();
        System.out.println("选择排序前。。。。。。。。。");
        for (int i=0;i<datas.length;i++) {
            System.out.print(datas[i] + ",");
        }
        System.out.println();

        sort(datas);
        System.out.println("选择排序后。。。。。。。。。");
        for (int i=0;i<datas.length;i++) {
            System.out.print(datas[i] + ",");
        }
        System.out.println();
    }
    public static int[] sort (int[] datas) {
        int temp;
        for (int i=0;i<datas.length;i++) {
            int index = i;
            for (int j=i+1;j<datas.length;j++) {
                if (datas[index] > datas[j]) {
                    index = j;
                }
            }
            temp = datas[i];
            datas[i] = datas[index];
            datas[index] = temp;
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
