import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov() {
        this(2);
    }

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    /**
     * Training method, creates data & populates HashMap myMap. Run once per training dataset
     * Adds Gram and corresponding ArrayList<String> until text is finished
     * @param text Given training data
     */
    @Override
    public void setTraining(String text) {
        super.setTraining(text);
        myWords = text.split("\\s+");

        for (int i = 0; i < myWords.length - myOrder + 1; i++) {
            WordGram gram = new WordGram(myWords, i, myOrder);
            myMap.putIfAbsent(gram, new ArrayList<String>());

            if (i == myWords.length - myOrder) {
                myMap.get(gram).add(PSEUDO_EOS);
                break;
            } else {
                myMap.get(gram).add(myWords[i + myOrder]);
            }
        }
    }

    /**
     * O(1) implementation - uses HashMap for instant access of training data
     * @param kGram input WordGram to find following characters of
     * @return Arraylist<String> of all characters following given wordgram
     */
    @Override
    public ArrayList<String> getFollows(WordGram kGram) {
        if (myMap.containsKey(kGram)) {
            return myMap.get(kGram);
        } else {
            throw new NoSuchElementException(kGram +" not in map");
        }
    }
}
