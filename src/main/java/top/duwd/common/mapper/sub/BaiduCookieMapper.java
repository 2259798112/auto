package top.duwd.common.mapper.sub;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import top.duwd.common.domain.sub.entity.BaiduCookie;

public interface BaiduCookieMapper extends Mapper<BaiduCookie> {


    @Select("select 1 from DUAL")
    int heart();
}