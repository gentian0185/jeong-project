package hs.bbs.comment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CommentVORowMapper implements RowMapper<CommentVO> {

	@Override
	public CommentVO mapRow(ResultSet resultSet, int pos) throws SQLException {
		CommentVO CommentVO = new CommentVO();
		
		CommentVO.setComment_num(resultSet.getInt("comment_num"));
		CommentVO.setComment_content(resultSet.getString("comment_content"));
		CommentVO.setComment_date(resultSet.getTimestamp("comment_date"));
		CommentVO.setArticle_num(resultSet.getInt("article_num"));
		return CommentVO;
	}
}
