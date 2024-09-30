package cn.ss.controller;

import cn.ss.pojo.PageBean;
import cn.ss.pojo.Result;
import cn.ss.pojo.User;
import cn.ss.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            String username, String name, String phone, Integer type) {
        PageBean pageBean = usersService.page(page, pageSize, username, name, phone, type);
        return Result.success(pageBean);
    }

    //@DeleteMapping("{usernames}")
    @GetMapping("delete/{usernames}")
    public Result delete(@PathVariable List<String> usernames) {
        log.info("delete usernames: {}", usernames);
        usersService.delete(usernames);
        return Result.success(null);
    }


    @GetMapping("add")
//    @PostMapping
    public Result add(@RequestBody User user) {
        log.info("add user: {}", user);
        usersService.add(user);
        return Result.success(null);
    }

    @PutMapping
//    @GetMapping("edit")
    public Result edit(@RequestBody User user) {
        log.info("edit user: {}", user);
        usersService.edit(user);
        return Result.success(null);
    }
}
