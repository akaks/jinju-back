package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.model.Jinju;
import com.aguang.jinjuback.pojo.JinjuInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface JinjuDao {

    void createJinju(Jinju jinju);

    Jinju getJinju(int id);

    ArrayList<JinjuInfo> getJinjuList(@Param("m") int m, @Param("n") int n);

    ArrayList<JinjuInfo> listByPageWithUserId(@Param("m") int m, @Param("n") int n, @Param("userId") int userId);

    ArrayList<JinjuInfo> listByPageWithoutUserId(@Param("m") int m, @Param("n") int n);

    @Select("SELECT COUNT(*) FROM jinju")
    int getListCount();

    @Insert("INSERT INTO `jj_vote`(jinju_id, user_id, type, create_time, update_time) VALUES (#{jijuId}, #{userId}, #{type}, #{currentTime}, #{currentTime})")
    Integer createVote(@Param("jijuId") Integer jijuId,
                    @Param("userId") Integer userId,
                    @Param("type") Integer type,
                    @Param("currentTime") Long currentTime);

    @Delete("delete from `jj_vote` where jinju_id=#{jijuId} and user_id=#{userId} ")
    Integer deleteVote(@Param("jijuId") Integer jijuId, @Param("userId") Integer userId);

    @Update("update `jj_vote` set type=#{newType}, update_time=#{currentTime} where jinju_id=#{jijuId} and user_id=#{userId} and type=#{oldType}")
    Integer updateVote(@Param("jijuId") Integer jijuId,
                    @Param("userId") Integer userId,
                    @Param("newType") Integer newType,
                    @Param("oldType") Integer oldType,
                    @Param("currentTime") Long currentTime);

    /**
     * 点赞数加1
     * @param jijuId
     */
    @Update("update `jinju` set up_vote_count=up_vote_count+1 where jinju_id=#{jijuId}")
    void increaseUpVote(Integer jijuId);

    /**
     * 点赞数减1
     * @param jijuId
     */
    @Update("update `jinju` set up_vote_count=up_vote_count-1 where jinju_id=#{jijuId}")
    void decreaseUpVote(Integer jijuId);

    /**
     * 点踩数加1
     * @param jijuId
     */
    @Update("update `jinju` set down_vote_count=down_vote_count+1 where jinju_id=#{jijuId}")
    void increaseDownVote(Integer jijuId);

    /**
     * 点踩数减1
     * @param jijuId
     */
    @Update("update `jinju` set down_vote_count=down_vote_count-1 where jinju_id=#{jijuId}")
    void decreaseDownVote(Integer jijuId);
}
