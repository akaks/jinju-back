package com.aguang.jinjuback.dao.chat;

import com.aguang.jinjuback.pojo.chat.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChatDao {

	@Insert("INSERT INTO `chat_message`(user_id, username, photo_url, type, message, is_visitor, is_show_time, create_time) " +
			"VALUES (#{userId}, #{username}, #{photoUrl}, #{type}, #{message}, #{isVisitor}, #{isShowTime}, #{createTime})")
	Integer createChatMessage(ChatMessage chatMessage);

	@Select("select * from `chat_message` where id<#{id} order by id desc limit id=#{m},id=#{n}")
	Integer findChatMessage(@Param("m") int m, @Param("n") int n, @Param("id") int id);
}
