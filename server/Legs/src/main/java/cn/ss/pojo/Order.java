package cn.ss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private String task;
    private String sender;
    private String receiver;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    private int state;
    private String price;
}
