package domains;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommitToDbResponse {
    private int numberOfFoodsSaved;
    private String message;

//    public int getNumberOfFoodsSaved() {
//        return numberOfFoodsSaved;
//    }
//
//    public void setNumberOfFoodsSaved(int numberOfFoodsSaved) {
//        this.numberOfFoodsSaved = numberOfFoodsSaved;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
