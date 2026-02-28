package io.github.iweidujiang.druid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.github.iweidujiang.druid.entity.UserDO;
import io.github.iweidujiang.druid.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 公众号: 苏渡苇
 * @since 2026-02-28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    // ✅ INSERT
    @PostMapping
    public String createUser(@RequestBody UserDO user) {
        userMapper.insert(user);
        return "User created with ID: " + user.getId();
    }

    // ✅ SELECT
    @GetMapping
    public List<UserDO> listUsers() {
        return userMapper.selectList(null);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String newName) {
        UpdateWrapper<UserDO> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id).set("name", newName);
        userMapper.update(null, wrapper);
        return "User updated";
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userMapper.deleteById(id);
        return "User deleted";
    }

    // ✅ 真·慢 SQL（MySQL）
    @GetMapping("/slow")
    public Object realSlowSql() {
        return userMapper.selectList(
                new QueryWrapper<UserDO>().apply("SLEEP(3) = 0")
        );
    }

    // ✅ 危险 DDL（会被 WallFilter 拦截）
    @GetMapping("/danger/ddl")
    public String testDDL() {
        try {
            userMapper.executeDangerDDL();
        } catch (Exception e) {
            return "WallFilter 拦截成功！原因: " + e.getMessage();
        }
        return "防火墙未生效！危险！";
    }

    // ✅ 高危 DCL
    @GetMapping("/danger/dcl")
    public String testDCL() {
        try {
            userMapper.executeDangerDCL();
        } catch (Exception e) {
            return "DCL 被拦截: " + e.getMessage();
        }
        return "DCL 未拦截！";
    }
}
