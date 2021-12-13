package com.marcgehman.toolrental.rental;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RentalAgreementMapper {
    
    @Select("SELECT * from tools t, RentalAgreements r WHERE t.code = r.code")
    @ResultMap("com.marcgehman.toolrental.rental.RentalAgreementMapper.RentalAgreementResult")
    public List<RentalAgreement> findAll();

    @Select("SELECT * FROM tools t, RentalAgreements r WHERE t.code = r.code AND t.code = #{code}")
    @ResultMap("com.marcgehman.toolrental.rental.RentalAgreementMapper.RentalAgreementResult")
    public RentalAgreement findById(String code);

    
    @Select("SELECT * FROM tools t, RentalAgreements r WHERE t.code = r.code AND t.code = #{code} AND r.dueDate > CURDATE()")
    @ResultMap("com.marcgehman.toolrental.rental.RentalAgreementMapper.RentalAgreementResult")
    public RentalAgreement findByIdDueAfterToday(String code);

    @Delete("DELETE FROM RentalAgreements WHERE code = #{tool.code}")
    public int deleteById(String code);

    @Insert("INSERT INTO RentalAgreements(code, rentalDays, checkoutDate, dueDate, dailyRentalCharge, chargeDays, preDiscountCharge, discountPercentage, discountAmount, finalCharge) " +
       " VALUES (#{tool.code}, #{rentalDays}, #{checkoutDate}, #{dueDate}, #{dailyRentalCharge}, #{chargeDays}, #{preDiscountCharge}, #{discountPercentage}, #{discountAmount}, #{finalCharge})")
    @ResultMap("com.marcgehman.toolrental.rental.RentalAgreementMapper.RentalAgreementResult")
    public int insert(RentalAgreement rentalAgreement);

    @Update("UPDATE RentalAgreements SET type=#{type}, " +
        " brand=#{brand} where code=#{tool.code}")
    @ResultMap("com.marcgehman.toolrental.rental.RentalAgreementMapper.RentalAgreementResult")
    public int update(RentalAgreement rentalAgreement);
}
