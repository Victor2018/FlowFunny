package datastructrue.victor.com;

public class Stack<E> {
	
	private DoubleLink<E> datas = new DoubleLink<E>();
	
	public void add (E data){
		datas.add(data);
	}
	public E getEnd(){
		return datas.seeEnd();
	}
	public E removeEnd(){
		return datas.removeEnd();
	}
	

}
