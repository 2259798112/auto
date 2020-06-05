package top.duwd.common.mapper.sub;


import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import top.duwd.common.domain.sub.entity.Keyword;

public interface KeywordMapper extends Mapper<Keyword> {

    @Select("select * from t_keyword order by id DESC limit 1")
    @ResultMap("BaseResultMap")
    Keyword findLastOne();
}