package hs.bbs.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface BBSDaoService {
	public int getCount() throws Exception;

	public Connection getConnection() throws Exception;
	//두 인자값은 page 클래스에서 계산을 해준 값이다.
	public ArrayList<BoardVO> List(int startRow, int endRow) throws Exception;
	public void Write(BoardVO article) throws Exception;
	public BoardVO Content(int article_num) throws Exception;

	public String getPass(String id) throws Exception;

	public void Update(int article_num, String title, String content, String file_name)
			throws Exception;

	public void Delete(int article_num) throws Exception;

	public void Reply(BoardVO article) throws Exception;

	public BoardVO getArticle(int article_num) throws Exception;

}