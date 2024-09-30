package cn.ss.controller;

import cn.ss.pojo.Order;
import cn.ss.pojo.PageBean;
import cn.ss.pojo.Result;
import cn.ss.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sender/orders")
public class SenderOrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "50") Integer pageSize,
                            String sender, String receiver, Integer state, String task) {
        PageBean pageBean = ordersService.page(page, pageSize, sender, receiver, state, task);
        return Result.success(pageBean);
    }

    @PutMapping
    public Result edit(@RequestBody Order order) {
        ordersService.edit(order);
        return Result.success(null);
    }

    @GetMapping("delete/{ids}")
    public Result delete(@PathVariable List<String> ids) {
        ordersService.delete(ids);
        return Result.success(null);
    }

//    @GetMapping("add")
    @PostMapping
    public Result add(@RequestBody Order order) {
        ordersService.add(order);
        return Result.success(null);
    }
}
