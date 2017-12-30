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

    JinjuInfo getJinju(int id);

    ArrayList<JinjuInfo> getJinjuList(@Param("m") int m, @Param("n") int n);

    ArrayList<JinjuInfo> listByPageWithUserId(@Param("m") int m, @Param("n") int n, @Param("userId") int userId);

    ArrayList<JinjuInfo> listByPageWithoutUserId(@Param("m") int m, @Param("n") int n);

    JinjuInfo getJinjuWithUserId(@Param("id") Integer id, @Param("userId") Integer userId);

    JinjuInfo getJinjuWithoutUserId(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM jinju")
    int getListCount();

    /**
     * 创建点赞、踩
     */
    @Insert("INSERT INTO `jj_vote`(jinju_id, user_id, type, create_time) VALUES (#{jijuId}, #{userId}, #{type}, #{currentTime})")
    Integer createVote(@Param("jijuId") Integer jijuId,
                    @Param("userId") Integer userId,
                    @Param("type") Integer type,
                    @Param("currentTime") Long currentTime);

    /**
     * 删除点赞、踩
     */
    @Delete("delete from `jj_vote` where jinju_id=#{jijuId} and user_id=#{userId} and type=#{type}")
    Integer deleteVote(@Param("jijuId") Integer jijuId, @Param("userId") Integer userId, @Param("type") Integer type);

    @Select("select 1 from `jj_vote` where jinju_id=#{jijuId} and user_id=#{userId} and type=#{type}")
    Integer hasVote(@Param("jijuId") Integer jijuId,
                    @Param("userId") Integer userId,
                    @Param("type") Integer type);

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

    /**
     * 收藏
     */
    @Insert("INSERT INTO `jj_collect`(jinju_id, user_id, collect, create_time) VALUES (#{jijuId}, #{userId}, #{collect}, #{currentTime})")
    void createCollect(@Param("jijuId") Integer jijuId,
                       @Param("userId") Integer userId,
                       @Param("collect") Integer collect,
                       @Param("currentTime") Long currentTime);

    /**
     * 删除收藏
     */
    @Delete("delete from `jj_collect` where jinju_id=#{jijuId} and user_id=#{userId} ")
    Integer deleteCollect(@Param("jijuId") Integer jijuId, @Param("userId") Integer userId);

    /**
     * 收藏总数+1
     * @param jijuId
     */
    @Update("update `jinju` set comment_count=comment_count+1 where jinju_id=#{jijuId}")
    void increaseCollect(Integer jijuId);

    /**
     * 收藏总数+1
     * @param jijuId
     */
    @Update("update `jinju` set comment_count=comment_count-1 where jinju_id=#{jijuId}")
    void decreaseCollect(Integer jijuId);


}
