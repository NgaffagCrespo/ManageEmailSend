package booksmanagement.nextGenTest.exceptions;

public class WritingsCanNotSaveException extends RuntimeException{

   public WritingsCanNotSaveException(String messages){
       super(messages);
   }
}
