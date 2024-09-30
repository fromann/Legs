package cn.ss.controller;

import cn.ss.pojo.Order;
import cn.ss.pojo.PageBean;
import cn.ss.pojo.Result;
import cn.ss.pojo.User;
import cn.ss.service.OrdersService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/orders")
public class AdminOrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            String sender, String receiver, Integer state, String task) {
        PageBean pageBean = ordersService.page(page, pageSize, sender, receiver, state, task);
        return Result.success(pageBean);
    }


    //@DeleteMapping("{usernames}")
    @GetMapping("delete/{ids}")
    public Result delete(@PathVariable List<String> ids) {
        ordersService.delete(ids);
        return Result.success(null);
    }


    @GetMapping("add")
//    @PostMapping
    public Result add(@RequestBody Order order) {
        ordersService.add(order);
        return Result.success(null);
    }

    @PutMapping
//    @GetMapping("edit")
    public Result edit(@RequestBody Order order) {
        ordersService.edit(order);
        return Result.success(null);
    }
}
