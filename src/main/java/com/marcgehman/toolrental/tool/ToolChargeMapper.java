package com.marcgehman.toolrental.tool;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ToolChargeMapper {
        
    @Select("SELECT * FROM ToolCharges")
    public List <ToolCharge> findAll();

    @Select("SELECT * FROM ToolCharges WHERE type = #{type}")
    @ResultMap("com.marcgehman.toolrental.tool.ToolChargeMapper.ToolChargeResult")
    public ToolCharge findByType(ToolType type);

    @Delete("DELETE FROM ToolCharges WHERE type = #{type}")
    public int deleteByType(ToolType type);

    @Insert("INSERT INTO ToolCharges(type, dailyRentalCharge, weekdayCharge, weekendCharge, holidayCharge)" +
        " VALUES (#{type}, #{dailyRentalCharge}, #{weekdayCharge}, #{weekendCharge}, #{holidayCharge})")
    public int insert(ToolCharge toolCharge);

    @Update("UPDATE ToolCharges set type=#{type}, " +
        " brand=#{brand} where code=#{code}")
    public int update(ToolCharge toolCharge);

}
