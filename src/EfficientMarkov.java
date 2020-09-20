import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	/**
	 * Training method, creates data & populates HashMap myMap. Run once per training dataset
	 * Adds String key and corresponding ArrayList<String> of following characters until text is finished
	 * @param text input training text
	 */
	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();

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
	}
	/**
	 * O(1) implementation - uses HashMap for instant access of training data
	 * @param key input String key to find following characters of
	 * @return Arraylist<String> of all characters following given key String
	 */
	@Override
	public ArrayList<String> getFollows(String key) {
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		} else {
			throw new NoSuchElementException(key+" not in map");
		}
	}
}	
