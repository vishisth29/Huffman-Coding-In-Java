package Huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class huffmancode {
	private HashMap<Character, String> encoder;

	private HashMap<String, Character> decoder;
	
	public huffmancode(String feeder){
		
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < feeder.length(); i++) {
			if(map.containsKey(feeder.charAt(i))) {
				int ov = map.get(feeder.charAt(i));
				int nv = ov+1;
				map.put(feeder.charAt(i), nv);
			}
			else{
				map.put(feeder.charAt(i),1);
			}
		}
		
		Heap<Node> minHeap = new Heap<>(true);
		
		Set<Map.Entry<Character, Integer>> popMap = map.entrySet();
		
		for (Map.Entry<Character, Integer> ch : popMap) {
			Node bt = new Node(ch.getKey(), ch.getValue());
			minHeap.add(bt);
			}
		
		while(minHeap.size()!=1){
			Node minOne = minHeap.remove();
			Node minTwo = minHeap.remove();
			Node toBeAdded = new Node(minOne, minTwo);
			minHeap.add(toBeAdded);
		}
		
		Node fullTree = minHeap.remove();
		
		this.encoder = new HashMap<>();
		this.decoder = new HashMap<>();
		initEncoder(fullTree, "");
	}
	
	private void initEncoder(Node node, String osf){
		if(node == null) return;
		if(node.left == null && node.right == null){
			encoder.put(node.data, osf);
			decoder.put(osf, node.data);
		}
		
		this.initEncoder(node.left, osf+0);
		this.initEncoder(node.right, osf+1);
	}
	
	public String enCode(String source){
		String rv = "";
		for (int i = 0; i < source.length(); i++) {
			String code = encoder.get(source.charAt(i));
			rv+=code+"   ";
		}
		return rv;
		
	}
	
	public String deCode(String codedString){
		String rv = "";
		String key = "";
		for (int i = 0; i < codedString.length(); i++) {
			key+=codedString.charAt(i);
			
			if(decoder.containsKey(key)){
				rv += decoder.get(key);
				key = "";
			}
			
		}
		return rv;
	}
	
	class Node implements Comparable<Node>{
		Character data;
		int cost;
		Node left;
		Node right;
		
		Node(Character data, int cost){
			this.data = data;
			this.cost = cost;
			this.left = null;
			this.right = null;
		}
		Node(Node left,Node right){
			this.data = '\0';
			this.cost = left.cost+ right.cost;
			this.left = left;
			this.right = right;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
}
