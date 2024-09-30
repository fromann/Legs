package cn.ss.controller;

import cn.ss.pojo.Order;
import cn.ss.pojo.PageBean;
import cn.ss.pojo.Result;
import cn.ss.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/receiver/orders")
public class RecevierOrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("selectMy")
    public Result selectMy(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "50") Integer pageSize,
                            String sender, String receiver, Integer state, String task) {
        PageBean pageBean = ordersService.page(page, pageSize, sender, receiver, state, task);
        return Result.success(pageBean);
    }
    @PutMapping("backOrder")
    public Result backOrder(@RequestBody Order order) {
        order.setState(0);
        order.setReceiver("nobody");
        ordersService.edit(order);
        return Result.success(null);
    }

    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "50") Integer pageSize,
                            String sender, String receiver, String task) {
        PageBean pageBean = ordersService.page(page, pageSize, sender, receiver, 0, task);
        return Result.success(pageBean);
    }

    @PutMapping("recOrder")
    public Result recOrder(@RequestBody Order order) {
        order.setState(1);
        ordersService.edit(order);
        return Result.success(null);
    }

}
