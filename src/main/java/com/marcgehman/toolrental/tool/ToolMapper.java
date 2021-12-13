package com.marcgehman.toolrental.tool;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ToolMapper {
    
    @Select("SELECT * FROM tools")
    public List <Tool> findAll();

    @Select("SELECT * FROM tools WHERE code = #{code}")
    public Tool findById(String code);

    @Delete("DELETE FROM tools WHERE code = #{code}")
    public int deleteById(String code);

    @Insert("INSERT INTO tools(code, type, brand) " +
        " VALUES (#{code}, #{type}, #{brand})")
    public int insert(Tool tool);

    @Update("UPDATE tools set type=#{type}, " +
        " brand=#{brand} where code=#{code}")
    public int update(Tool tool);

}
