package khamitov.tests.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
@JsonPropertyOrder({"id", "firstName", "lastName", "status"})
@Data
public class Payload {
    @JsonProperty
    private int id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private boolean status;
}
