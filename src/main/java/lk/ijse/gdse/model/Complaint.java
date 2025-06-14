package lk.ijse.gdse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complaint implements Serializable {
    private int id;
    private int userId;
    private String subject;
    private String description;
    private String status;
    private String remarks;
}
