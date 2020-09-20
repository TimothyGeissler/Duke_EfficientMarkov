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

    @Override
    public void setTraining(String text) {
        super.setTraining(text);
        int index = 0;
        myWords = text.split(" ");

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

        /*myMap.forEach((k, v) -> {
            System.out.println(k + " - " + v);
        });*/
    }

    @Override
    public ArrayList<String> getFollows(WordGram kGram) {
        if (myMap.containsKey(kGram)) {
            return myMap.get(kGram);
        } else {
            throw new NoSuchElementException(kGram +" not in map");
        }
    }
}
