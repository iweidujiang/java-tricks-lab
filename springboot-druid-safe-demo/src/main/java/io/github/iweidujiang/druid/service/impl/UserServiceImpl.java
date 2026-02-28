package io.github.iweidujiang.druid.service.impl;

import io.github.iweidujiang.druid.entity.UserDO;
import io.github.iweidujiang.druid.mapper.UserMapper;
import io.github.iweidujiang.druid.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 公众号: 苏渡苇
 * @since 2026-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
