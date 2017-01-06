package util.victor.com;

import java.util.Comparator;

import data.victor.com.SortData;

/**
 * Created by victor on 2016/1/9.
 */
public class PinyinComparator implements Comparator<SortData> {

	public int compare(SortData o1, SortData o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
