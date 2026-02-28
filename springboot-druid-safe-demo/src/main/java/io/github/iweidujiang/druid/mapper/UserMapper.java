package io.github.iweidujiang.druid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.iweidujiang.druid.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 公众号: 苏渡苇
 * @since 2026-02-28
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    @Select("DROP TABLE IF EXISTS test_hack_table")
    void executeDangerDDL();

    @Select("GRANT USAGE ON *.* TO 'fake_user'@'localhost'")
    void executeDangerDCL();

}
