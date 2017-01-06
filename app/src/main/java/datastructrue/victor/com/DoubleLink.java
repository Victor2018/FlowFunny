package datastructrue.victor.com;

import java.util.Iterator;

public class DoubleLink<E> implements Iterable<E> {

	public static void main(String[] args) {
		DoubleLink datas = new DoubleLink();
		datas.add("aaa");
		datas.add("bbb");
		datas.add("ccc");
		datas.add("ddd");
		datas.add("eee");
		for (Object d :datas){
			System.out.println(d);
		}
		System.out.println(datas);
		datas.remove("aaa");
		System.out.println(datas);
		datas.remove("eee");
		System.out.println(datas);
		datas.remove("ccc");
		System.out.println(datas);
		datas.update("bbb", "victor");
		System.out.println(datas);
	}

	private Node<E> head;
	private Node<E> rear;
	public DoubleLink(){

	}
	public E removeHead(){
		E data = null;
		if (head != null){
			data = head.data;
			head = head.next;

			if (head ==null){
				rear = null;
			}
			else {
				head.prev = null;
			}
		}
		return data;
	}

	public E removeEnd(){
		E data = null;
		if (rear != null){
			data = rear.data;
			rear = rear.prev;
			if (rear == null){
				head = null;
			}
			else {
				rear.next = null;
			}
		}
		return data;

	}
	public E seeHead(){
		E data = null;
		if (head != null){
			data = head.data;
		}
		return data;
	}

	public E seeEnd(){
		E data = null;
		if (rear != null) {
			data = rear.data;
		}
		return data;
	}


	//////////////////////////////
	public void add(E data){
		Node<E> node = new Node<E>(data);
		if (head == null){
			head = node;
			rear = node;

		}///////////////////////////增加数据///////////////////////
		else{
			node.prev = rear;
			rear.next = node;
			rear = node;
		}
	}
	///////////////////////////////
	public void remove(E data){
		Node node = find(data);
		if (node != null){
			if (node == head && node == rear){
				head = null;
				rear = null;
			}
			else if (node == head ){
				head = head.next;
				head.prev = null;
			}
			else if (node == rear){
				rear = rear.prev;
				rear.next = null;
			}
			else {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
		}
		else {
			throw new RuntimeException("找不到要删除在的数据");
		}

	}

	public void update(E oldData,E newData){

		Node node = find(oldData);
		if (node != null){
			node.data = newData;
		}

	}

	public String toString(){
		StringBuilder mess = new StringBuilder("[");
		Node temp = head;
		while (temp != null){
			if (temp != rear)
				mess.append(temp.data + ",");
			else
				mess.append(temp.data);
			temp = temp.next;
		}
		mess.append("]");
		return mess + "";
	}


	public Node find(E data){
		Node temp = head;
		while(temp != null){
			if (temp.data.equals(data)){
				return temp;
			}
			else{
				temp = temp.next;
			}
		}
		return null;
	}




	public boolean isEmpty(){
		return head == null;
	}
	private class Node<E>{
		Node<E> prev;
		Node<E> next;
		E data;

		public Node(E d){
			this.data = d;
		}
	}
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return  new Iterator(){
			Node<E> temp = head;

			public boolean hasNext() {
				// TODO Auto-generated method stub
				if (temp != null)
					return true;
				else
					return false;
			}

			public Object next() {
				// TODO Auto-generated method stub
				Object data = temp.data;
				temp = temp.next;
				return data;
			}

			public void remove() {
				// TODO Auto-generated method stub

			}

		};
	}
}
