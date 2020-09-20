import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public static void main(String[] args) {
		EfficientMarkov e = new EfficientMarkov();
		e.setTraining("bbbabbabbbbaba");
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();

		/*int index = 0;
		while (index + myOrder <= myText.length()) {
			String str = myText.substring(index, index + myOrder);



			myMap.putIfAbsent(str, super.getFollows(str));
			index++;
		}*/
		for (int i = 0; i < text.length() - myOrder + 1; i++) {
			String str = myText.substring(i, i + myOrder);
			myMap.putIfAbsent(str, new ArrayList<String>());

			if (i == text.length() - myOrder) {
				myMap.get(str).add(PSEUDO_EOS);
				break;
			} else {
				myMap.get(str).add(myText.substring(i + myOrder, i + myOrder + 1));
			}
		}

		/*myMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});*/
	}

	@Override
	public ArrayList<String> getFollows(String key) {
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		} else {
			throw new NoSuchElementException(key+" not in map");
		}
	}
}	
