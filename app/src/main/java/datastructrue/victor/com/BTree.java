package datastructrue.victor.com;

public class BTree {

	public static void main(String[] args) {
		BTree tree = new BTree();
		tree.add(78);
		tree.add(56);
		tree.add(97);
		tree.add(43);
		tree.add(65);
		tree.add(80);
		tree.add(105);

		System.out.println(tree);
		tree.update(56,106);
		System.out.println(tree);
	}

	private Node root;//根节点

	public BTree(){  //BTree构造方法

	}
	//	增加数据
	public void add(Object data) {//查找要输入的数据是否存在
		if (contains(data)){
			throw new RuntimeException("数据已经存在");//若数据存在，抛出运行异常
		}
		Node node = new Node(data);//若数据不存在，新建一个数据节点
		if (root == null) {//判断是否存在根节点，若不存在，把数据节点设置为根节点
			root = node;
		} else {//若根节点存在
			Node parrent = findParrent(data);//设置父节点，查找并初始化为要插入数据的父节点
			node.parent = parrent;//数据节点的父节点等于设置的父节点
			if (compare(parrent.data,data) > 0) {//比较父节点数据与节点数据的大小，若父节点的数据大，父节点的左节点等于节点
				parrent.left = node;
			} else {
				parrent.right = node;//若节点数据大，父节点的右节点等于节点
			}
		}
	}
	//更新数据
	public void update(Object oldData,Object newData){
		remove(oldData);//删除要更新的旧数据及节点
		add(newData);//增加要更新的新数据及节点
	}



	//删除数据
	public void remove(Object data){
		Node node = find(data);//查找要删除的数据的节点是否存在
		if (node == null) {
			throw new RuntimeException("删除的数据不存在");//若数据不存在，抛出异常
		} else {
			if (node == root) {//假如删除的节点是根节点
				if (node.left == null && node.right == null) {//假如根节点的左节点跟右节点都是空的
					root = null;//根节点为空
				} else if (node.left == null) {//假如根节点的左节点为空
					root = root.right;//原根节点的右节点赋值给根节点
					root.parent = null;//根节点的父节点为空
				} else if (node.right == null) {//假如根节点的右节点为空
					root = root.left;//原根节点的左节点赋值给根节点
					root.parent = null;//根节点的父节点为空
				} else {//假如根节点的左右节点都不为空：若左节点继承为根节点
					Node right = findRight(root.left);//查找出左节点的最终右子节点
					right.right = root.right;//把根节点的右节点赋值给左节点的最右终子节点
					root.right.parent = right;//把最终右子节点的值赋给根节点的右节点
					root = root.left;//把根节点的左节点赋值给根节点
					root.parent = null;//根节点的负积点为空
				}

			} else {//假如删除的节点不是根节点
				if (node.left == null && node.right == null) {//假如节点的左右节点都为空
					if (compare(node.data,node.parent.data) > 0) {//假如节点的值大于父节点的值
						node.parent.right = null;//父节点的右节点为空
					} else {//假如节点的值小于父节点
						node.parent.left = null;//父节点的左节点为空
					}
				} else if (node.left == null) {//假如节点的左节点为空
					node.right.parent = node.parent;//把节点的父节点赋值给节点的右节点的父节点
					if (compare(node.data,node.parent.data) > 0) {//若节点的值大于节点的父节点的值
						node.parent.right = node.right;//把节点的右节点赋值给节点的父节点的右节点
					} else {//若节点的值小于节点的父节点
						node.parent.left = node.right;//把节点的右节点赋值给节点的父节点的左节点
					}
				} else if (node.right == null) {//假如节点的右节点为空
					node.left.parent = node.parent;//把节点的父节点赋值给节点的左节点的父节点
					if (compare(node.data,node.parent.data) > 0) {//若节点的值大于节点的父节点的值
						node.parent.right = node.left;//把节点的左节点赋值给节点的父节点的右节点
					} else {//若节点的值小于节点的父节点
						node.parent.left = node.left;//把节点的左节点赋值给节点的父节点的左节点
					}
				} else {//节点的左右节点都不为空：节点的左节点继承节点
					Node right = findRight(node.left);//找到节点的左节点的最终右节点
					right.right = node.right;//节点的右节点赋值给最终右节点的右节点
					node.right.parent = right;//最终右节点的值赋给节点的右节点的父节点
					if (compare(node.data,node.parent.data) > 0) {//若节点的值大于节点的父节点的值
						node.parent.right = node.left;//节点的左节点的值赋给节点的父节点的右节点
						node.left.parent = node.parent;//节点的父节点的值赋给节点的左节点的父节点
					}else {//若节点的值小于节点的父节点的值
						node.parent.left = node.left;//把节点的左节点的赋给节点的父节点的左节点
						node.left.parent = node.parent;//把节点的父节点赋给节点的左节点的父节点
					}

				}
			}
		}
	}
	//	查找节点
	private Node find(Object data){//传入查找数据
		Node temp = root;//定义节点temp并初始化为根节点
		while (temp != null) {//temp不为空
			if (temp.data.equals(data)) {//如果temp的数据与传入数据一致
				break;//跳出选择
			} else {//如果tmep的数据与传入数据不一致
				if (compare(temp.data,data) > 0) {//如果temp的数据大于传入数据
					temp = temp.left;//把temp的左节点赋给temp
				} else {
					temp = temp.right;//把temp的右节点赋给temp
				}
			}
		}
		return temp;
	}
	//	查找右节点
	private Node findRight(Node left){//传入左节点
		Node parrent = left;//新建父节点并初始化为左节点
		while (left != null){//当左节点不为空时进行循环
			parrent = left;//把左节点赋值给父节点
			left = left.right;//左节点等于左节点的右节点
		}
		return parrent;//返回父节点
	}
	//	查找父节点
	private Node findParrent(Object data){//传入比较数据
		Node parent = root;//设置父节点，初始化为根节点
		Node temp = root;//设置节点，初始化为根节点
		while (temp != null) {//判断节点是否为空，若为空，返回父节点，若不为空，执行循环
			parent = temp;//父节点等于节点
			if (compare(temp.data,data) > 0) {//若节点的数据大于传入数据，节点等于节点的左节点
				temp = temp.left;
			} else {
				temp = temp.right;//节点等于节点的右节点
			}
		}
		return parent;//返回父节点
	}
	//	构造容器，比较数据是否存在
	public boolean contains(Object data){
		boolean isExist = false;
		Node temp = root;//设置遍历节点为根节点

		while (temp != null) {
			if (temp.data.equals(data)) {//比较节点与查找数据的值，相同，返回返回值，返回值为：true
				isExist = true;
				break;
			} else {
				if (compare(temp.data,data) > 0) {//比较节点数据与查找数据的值，若节点数据大，遍历节点的左子节点，若查找数据大，遍历右子节点
					temp = temp.left;
				} else {
					temp = temp.right;
				}
			}
		}
		return isExist;//返回数据是否存在，若存在，返回true，否则false

	}
	//比较数据
	private int compare(Object o1,Object o2){
		Comparable c1 = null;//定义两个比较对象，初始化为null
		Comparable c2 = null;
		if (o1 instanceof Comparable) {//判断传入对象是否兼容比较类型，若兼容，把传入对象分别赋给比较对象
			c1  = (Comparable)o1;
			c2  = (Comparable)o2;
		} else {//若不兼容，将传入对象转换为兼容的String类型，并分别赋值给比较对象
			c1 = o1.toString();
			c2 = o2.toString();
		}

		return c1.compareTo(c2);//比较传入对象，当<、=、>时，分别返回负数，0和正数
	}
	//遍历
	private void see(StringBuilder mess,Node node){//查找遍历
		if (node != null) {//节点不为空
			see(mess,node.left);//递归遍历节点的左节点
			mess.append(node.data + ",");//把节点的值添加到mess后
			see(mess,node.right);//递归遍历节点的右节点
		}

	}
	//	ToString方法
	public String toString(){
		StringBuilder mess = new StringBuilder("[");//创建一个新的StringBuilder实例mess，且初始化为“[”
		see(mess,root);//遍历整个BTree
		mess.append("]");//在mess后面添加“]”
		return mess + "";//将StringBuilder实例mess转化为字符串
	}

	private class Node {
		Node parent;
		Node left;
		Node right;

		Object data;
		public Node(Object data) {//构造方法：传入数据
			this.data = data;//节点数据等于数据
		}
	}
}
