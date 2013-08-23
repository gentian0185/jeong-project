package hs.bbs.dao;

import hs.bbs.DEFINE;
import hs.bbs.comment.CommentVO;
import hs.bbs.comment.CommentVORowMapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class BBSDao implements BBSDaoService {
	private String TAG = "BBSDao";

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub	
		//return sqlMapClientTemplate.queryForInt("select count(*) from board");
		DEFINE.printTAG(TAG, " count");
		return (int)sqlMapClientTemplate.queryForObject("getCount");
	}
	
	// Board 테이블의 모든 정보를 VO에 입력.
	@Override
	public List<BoardVO> List(int startRow) {
		return sqlMapClientTemplate.queryForList("List", startRow-1, 10);
	}

	// 비밀번호 검사
	@Override
	public List<String> getPass(String id) {
		return sqlMapClientTemplate.queryForList("getPass", id);
	}
	
	
	// Board 테이블의 한 레이블의 삽입 쿼리문을 DB에 입력. (ID, Title, Content, FileName 필요.)
	// file_name의 경우 다음코딩때 조심해야 할듯.. 예외처리 만들어야할듯.
	@Override
	public void Write(BoardVO article) {
		sqlMapClientTemplate.insert("Write", article);
	}
	@Override
	public BoardVO Content(int article_num) {
		return (BoardVO) sqlMapClientTemplate.queryForObject("Content", article_num);
	}
	
	@Override
	public void upHit(int article_num){
		sqlMapClientTemplate.update("upHit", article_num);
	}
	@Override
	public void Reply(BoardVO article) {
		upPos(article.getGroup_id(), article.getPos());
		sqlMapClientTemplate.insert("Reply", article);
	}
	@Override
	public void upPos(int group_id, int pos) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> hm = new HashMap<String, Integer>();
		hm.put("group_id", group_id);
		hm.put("pos", pos);
		sqlMapClientTemplate.update("upPos", hm);
	}
	
	@Override
	public void Update(int article_num,String title,String content){
		HashMap<String,Object> hm = new HashMap<>();
		hm.put("article_num",article_num);
		hm.put("title", title);
		hm.put("content", content);
		sqlMapClientTemplate.update("Update",hm);
	}
	@Override
	public void Delete(int article_num) {
		sqlMapClientTemplate.delete("deleteArticle",article_num);
	}

	@Override
	public List<CommentVO> getAllComment(int article_num) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getAllComment",article_num);
	}


	@Override
	public void writeComment(int article_num, String comment_content) {
		// TODO Auto-generated method stub
		HashMap<String, Object>hm = new HashMap<>();
		hm.put("article_num", article_num);
		hm.put("comment_content", comment_content);
		sqlMapClientTemplate.insert("writeComment", hm);
	}
	
	
	
	
		
//	// 게시판 하나만 자세히 보기
//	@Override
//	public BoardVO Content(int article_num) {
//		// TODO Auto-generated method stub
//		DEFINE.printTAG(TAG, "article_num is : "+article_num);
//		addCount(article_num);
//
//		StringBuffer query = new StringBuffer();
//		query.append("select * from board where article_num = ?");
//		DEFINE.printTAG(TAG, "query is : " + query);
//		
//		return sqlMapClientTemplate.queryForObject(query.toString(), new Object[]{article_num}, new BoardVORowMapper());
//	}
//	private void addCount(int article_num) {
//		// TODO Auto-generated method stub
//		DEFINE.printTAG(TAG, article_num);
//		StringBuffer query = new StringBuffer();
//		query.append("update board set hit = hit +1 where article_num= ? ");
//		DEFINE.printTAG(TAG, "query is : " + query);
//		
//		sqlMapClientTemplate.update(query.toString(), article_num);
//	}

	
//	// 글 수정
//	@Override
//	public void Update(int article_num, String title, String content,
//			String file_name) {
//		StringBuffer query = new StringBuffer();
//		query.append("update board set title= ?, content = ? , file_name =?, write_date = sysdate where article_num = ?");
//		sqlMapClientTemplate.update(query.toString(), title, content, file_name, article_num);
//	}
//
//	@Override
//	public void Delete(int article_num) {
//		// TODO Auto-generated method stub
//		
//		StringBuffer query = new StringBuffer();
//		query.append("delete from board where article_num=?");
//		DEFINE.printTAG(TAG, "query is : " + query);
//		
//		sqlMapClientTemplate.update(query.toString(), article_num);
//	}
//
//	@Override
//	public void Reply(BoardVO article) {
//		// TODO Auto-generated method stub
//
//		setPosForReply(article);
//
//		StringBuffer query = new StringBuffer();
//		query.append("insert into board values ");
//		query.append("(board_seq.nextval,?,?,?,?,?,?,0,sysdate,?)");
//		DEFINE.printTAG(TAG, "query is : " + query);
//		
//
//		sqlMapClientTemplate.update(query.toString(), article.getId(), article.getTitle(), article.getContent(), article.getGroup_id(), article.getPos() + 1, article.getDepth() + 1, article.getFile_name());
//	}
//
//	private void setPosForReply(BoardVO article) {
//		// TODO Auto-generated method stub
//
//		StringBuffer query = new StringBuffer();
//		query.append("update board ");
//		query.append("set pos=pos+1  ");
//		query.append("where group_id= ? and pos>? ");
//		DEFINE.printTAG(TAG, "query is : " + query);
//	
//		sqlMapClientTemplate.update(query.toString(), article.getGroup_id(), article.getPos());
//
//	}
//
//	private void printBoardVO(BoardVO boardVO) {
//		// TODO Auto-generated method stub
//		System.out.println("----------" + TAG + "---------");
//		System.out.println("getArticle_num : " + boardVO.getArticle_num());
//		System.out.println("getId : " + boardVO.getId());
//		System.out.println("getTitle : " + boardVO.getTitle());
//		System.out.println("getContent : " + boardVO.getContent());
//		System.out.print("getGroup_id : " + boardVO.getGroup_id());
//		System.out.print("   getPos : " + boardVO.getPos());
//		System.out.print("   getDepth : " + boardVO.getDepth());
//		System.out.println("   getHit : " + boardVO.getHit());
//		System.out.println("getWrite_date : " + boardVO.getWrite_date());
//		System.out.println("getFile_name : " + boardVO.getFile_name());
//		System.out.println();
//		System.out.println();
//	}
//
//	@Override
//	public void Update(int article_num, String title, String content, String file_name){
//		sqlMapClientTemplate.update("Update", new Object[]{article_num, title, content, file_name});
//	}
//	
//	@Override
//	public void Delete(int article_num){
//		sqlMapClientTemplate.delete("Delete", article_num);
//	}
//
//	@Override
//	public void writeComment(int article_num, String comment_content) {
//		String query="insert into comments values(comment_seq.nextval, ?, sysdate, ?)";
//		sqlMapClientTemplate.update(query, new Object[]{comment_content, article_num});
//	}
//
//	
//	@Override
//	public List<CommentVO> getAllComment(int article_num) {
//		String query="select * from comments where article_num=?";
//		return sqlMapClientTemplate.query(query, new Object[]{article_num}, new CommentVORowMapper());
//	}
}
