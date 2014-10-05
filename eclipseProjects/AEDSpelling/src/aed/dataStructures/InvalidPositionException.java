package aed.dataStructures;

public class InvalidPositionException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public InvalidPositionException(){
        super();
    }

    public InvalidPositionException(String message){
        super(message);
    }

	public InvalidPositionException(int index, int counter) {
		System.err.println("InvalidIndex: " + index + "; Counter; " + counter);
	}

}

