package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Board;

@Mapper
public interface BoardRepository {

	@Select("""
				SELECT *
				FROM board AS b
				INNER JOIN article AS a
				ON a.boardId = b.id
				WHERE b.id = #{boardId}
				AND delStatus = 0;
			""")
	public Board getBoardById(int boardId);

}