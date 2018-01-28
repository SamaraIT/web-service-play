package hr.samara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class Article implements Serializable{
    private Long id;
    private String name;
    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date created;

    public Article() {
        this.created = new Date();
    }
}
