package cn.ss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String name;
    private String phone;
    private Integer type;

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }
}
