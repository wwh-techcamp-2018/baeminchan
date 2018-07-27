package codesquad.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        this("category not found");
    }

    public CategoryNotFoundException(String msg){
        super(msg);
    }
}
