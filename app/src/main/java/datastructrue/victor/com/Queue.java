package datastructrue.victor.com;

public class Queue<E> {
	private DoubleLink<E> datas = new DoubleLink<E>();

	public void add (E data){
		datas.add(data);
	}
	public E getHead(){
		return datas.seeHead();
	}
	public E removeHead(){
		return datas.removeHead();
	}
}
