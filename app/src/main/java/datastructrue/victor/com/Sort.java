package datastructrue.victor.com;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int datas[] = new int[500000];
		for (int i = 0; i < datas.length ; i++) {
			datas[i] = (int)(Math.random() * 100);
		}
		/*for (int i = 0; i < datas.length ; i++) {
			System.out.print(datas[i] + ",");
		}
		System.out.println();*/
		Calendar c = new GregorianCalendar();
		System.out.println(c.get(Calendar.MINUTE) + ":"+ c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND));
		//insert(datas);
		qsort(0,datas.length -1 ,datas);
		c = new GregorianCalendar();
		System.out.println(c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND));
		//qsort(0,datas.length -1 ,datas);
		/*for (int i = 0; i < datas.length ; i++) {
			System.out.print(datas[i] + ",");
		}
		System.out.println();*/
	}

	public static void qsort(int start,int end,int[] datas){
		if (start >= end) {
			return;
		} else {
			int middle = quick(start,end,datas);
			qsort(start,middle -1 ,datas);
			qsort(middle + 1,end ,datas);
		}
	}



	public static int quick(int start,int end,int[] datas){
		int data = datas[end];
		int left = start - 1;
		int right = end;
		int middle = 0;
		while (true) {
			//左边开始找第一个比data大的数据
			while (left < end && datas[++left] <= data);


			//右边开始找第一个比data小的数据
			while (right > start && datas[--right] >= data);



			if (left < right) {
				change(left,right,datas);
			} else {
				change(left,end,datas);
				middle = left;
				break;
			}



			if (left == end) {
				middle = end;
				break;
			}

			if (right == start ) {
				change(right,end,datas);
				middle = start;
				break;

			}

		}
		return middle;
	}

	private static void change(int a,int b,int[] datas){
		int c = datas[a];
		datas[a] = datas[b];
		datas[b] = c;
	}

	public static void insert(int datas[]){
		for (int i = 1; i < datas.length ; i++) {
			int data = datas[i];
			int j = i - 1;
			for ( ;j >=0; j--) {
				if (data < datas[j]) {
					datas[j + 1] = datas[j];
				} else {
					datas[j + 1] = data;
					break;
				}
			}
			if (j == -1) {
				datas[0] = data;
			}
		}
	}

}
