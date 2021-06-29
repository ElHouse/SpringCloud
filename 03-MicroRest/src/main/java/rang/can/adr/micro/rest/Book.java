package rang.can.adr.micro.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String author;
    private String title;

    // standard getters and setters
}