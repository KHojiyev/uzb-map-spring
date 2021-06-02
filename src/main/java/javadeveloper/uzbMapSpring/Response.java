package javadeveloper.uzbMapSpring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String message;
    private Boolean success;
    private Object object;

    public Response(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
