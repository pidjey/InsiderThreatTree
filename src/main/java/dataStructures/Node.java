package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Node<T>{

	private Node<T> parent;
	private T data;
	private List<Node<T>> children;

	public Node(T data){
		this.parent = null;
		this.data = data;
		this.children = new ArrayList<Node<T>>();
	}
	
	public Node(T data, Node<T> parent)
	{
		this.parent = parent;
		this.data = data;
		this.children = new ArrayList<Node<T>>();
	}

	public T getData(){
		return data;
	}
	
	public Node<T> getParent(){
		return parent;
	}

	public Node<T> getChild(int pos){
		return children.get(pos);
	}
	
	public List<Node<T>> getChildren(){
		return children;
		
	}
	public int getChildrenSize() {
		return children.size();
	}
	
	public Node<T> addChild(T data){
		children.add( new Node<T>(data, this) );
		
		return getChild(children.size()-1);
	}

	public Node<T> addChild(T data, int pos){
		children.add( pos, new Node<T>(data,this) );
		
		return getChild(pos);
	}

	public void removeChild(int pos){
		children.remove(pos);
	}
	

	
}
