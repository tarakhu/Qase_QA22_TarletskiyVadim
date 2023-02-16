package apiTests.models;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {

    private boolean status;
    private Project result;


}
