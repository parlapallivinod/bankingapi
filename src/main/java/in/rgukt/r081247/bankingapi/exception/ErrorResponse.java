/**
 * @author Vinod Parlapalli
 * created on 2019/11/03
 *
 * Exceptions are converted into this class object and sent to user as Response.
 */

package in.rgukt.r081247.bankingapi.exception;

import java.util.List;

public class ErrorResponse {
    //General error message about nature of error
    private String message;

    //Specific errors in API request processing
    private List<String> details;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    //Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", details=" + details +
                '}';
    }
}
