package hs.bbs.dao;

import hs.bbs.comment.CommentVO;

import java.util.List;

public interface BBSDaoService {
	public int getCount() throws Exception;
	
	
	//두 인자값은 page 클래스에서 계산을 해준 값이다.
	public List<BoardVO> List(int startRow);

//	public String getPass(String id);
	
	public List<String> getPass(String id);
	public void Write(BoardVO article);
	public BoardVO Content(int article_num);

	public void upHit(int article_num);
//
	public void Update(int article_num, String title, String content);
//
	public void Delete(int article_num) ;
//
	public void Reply(BoardVO article);
	public void upPos(int Group_id, int pos);


	public List<CommentVO> getAllComment(int article_num);


	public void writeComment(int article_num, String comment_content);
	
//	public void writeComment(int article_num, String comment_content);
//	public List<CommentVO> getAllComment(int article_num);
}