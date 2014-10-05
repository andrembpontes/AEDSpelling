package aed.spelling;

/**
 * Represents a word. Its considered a word a char sequence without spaces
 *
 * @author Andre Pontes (42845)
 */
public class Word {
    private String word;

    /**
     * Create word and verify its validity
     *
     * @param word Word
     * @throws InvalidWordException Givens word are not a valid word. Have a space char.
     */
    public Word(String word) throws InvalidWordException{
        if(word.contains(" "))
            throw new InvalidWordException();

        this.word = word;
    }

    /**
     * Return Word
     *
     * @return Word
     */
    public String getWord(){
        return this.word;
    }
}
