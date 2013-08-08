package hs.bbs.dao;

import hs.bbs.DEFINE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BBSDao implements BBSDaoService {
	private String TAG = "BBSDao";

	// Singleton pattern.
	public static BBSDao bbsDao = new BBSDao();

	public static BBSDao getInstance() {
		return bbsDao;
	}

	// db value.
	Connection connection;
	PreparedStatement pstmt;
	ResultSet resultSet;

	@Override
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		connection = this.getConnection();
		pstmt = connection.prepareStatement("select count (*) from board");
		resultSet = pstmt.executeQuery();
		// 결과가 하나만 출력되므로 if를 사용함.
		if (resultSet.next()) {
			count = resultSet.getInt(1);
			System.out.println("	record count is : " + count);
		}

		myClose(connection, pstmt);
		return count;
	}

	// Board 테이블의 모든 정보를 VO에 입력.
	@Override
	public ArrayList<BoardVO> List(int startRow, int endRow) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<BoardVO> arrayList = new ArrayList<BoardVO>();
		connection = this.getConnection();

		// 쿼리문 삽
		StringBuffer query = new StringBuffer();
		// 쿼리문 띄어쓰기 주의. 나는 공백을 뒤에 주었다.
		query.append("select * ");
		query.append("from (select rownum rm, bbs.* ");
		query.append("from (select * ");
		query.append("from board ");
		query.append("order by group_id desc, pos)bbs) ");
		query.append("where rm between ? and ?");

		pstmt = connection.prepareStatement(query.toString());
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);

		DEFINE.printTAG(TAG, "query is : " + query);
		resultSet = pstmt.executeQuery();

		while (resultSet.next()) {
			// testVO는 일회용이므로 while 안에.
			BoardVO boardVO = new BoardVO();
			boardVO = setBoardVO(boardVO);
			// printBoardVO(boardVO);
			arrayList.add(boardVO);
		}

		myClose(connection, pstmt);
		return arrayList;
	}

	// Board 테이블의 한 레이블의 삽입 쿼리문을 DB에 입력. (ID, Title, Content, FileName 필요.)
	// file_name의 경우 다음코딩때 조심해야 할듯.. 예외처리 만들어야할듯.
	@Override
	public void Write(BoardVO article) throws Exception {
		// TODO Auto-generated method stub
		connection = this.getConnection();

		StringBuffer query = new StringBuffer();
		query.append("insert into board ");
		query.append("values (board_seq.nextval, ?, ?, ?, board_seq.currval , 0,0,0, sysdate , ?)");

		pstmt = connection.prepareStatement(query.toString());
		pstmt.setString(1, article.getId());
		pstmt.setString(2, article.getTitle());
		pstmt.setString(3, article.getContent());
		pstmt.setString(4, article.getFile_name());

		resultSet = pstmt.executeQuery();
		myClose(connection, pstmt);
	}

	// 게시판 하나만 자세히 보기
	@Override
	public BoardVO Content(int article_num) throws Exception {
		// TODO Auto-generated method stub
		addCount(article_num);
		
		connection = this.getConnection();

		StringBuffer query = new StringBuffer();
		query.append("select * from board where article_num = ?");

		pstmt = connection.prepareStatement(query.toString());
		pstmt.setInt(1, article_num);

		resultSet = pstmt.executeQuery();

		BoardVO boardVO = new BoardVO();
		// BoardVO는 일회용
		if (resultSet.next()) {
			boardVO = setBoardVO(boardVO);
			printBoardVO(boardVO);
		}
		myClose(connection, pstmt);
		return boardVO;
	}

	private void addCount(int article_num) throws Exception{
		// TODO Auto-generated method stub
		connection = this.getConnection();
		
		StringBuffer query = new StringBuffer();
		query.append("update board set hit = hit +1 where article_num= ? ");
			
		pstmt = connection.prepareStatement(query.toString());
		pstmt.setInt(1, article_num);
		resultSet = pstmt.executeQuery();

		myClose(connection, pstmt);
	}

	// 비밀번호 검사
	public String getPass(String id) throws Exception {
		// TODO Auto-generated method stub
		String pass = null;
		connection = this.getConnection();
		pstmt = connection
				.prepareStatement("select pass from user_table where id = ?");
		pstmt.setString(1, id);
		resultSet = pstmt.executeQuery();
		// 결과가 하나만 출력되므로 if를 사용함.
		if (resultSet.next()) {
			pass = resultSet.getString("pass");
			System.out.println("	pass is : " + pass);
		}

		myClose(connection, pstmt);
		return pass;
	}
	
	//글 수정
	public void Update(int article_num, String title,String content, String file_name) throws Exception{
		connection = this.getConnection();
		
		StringBuffer query = new StringBuffer();
		query.append("update board set title= ?, content = ? , file_name =?, write_date = sysdate where article_num = ?");
		pstmt = connection.prepareStatement(query.toString());
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, file_name);
		pstmt.setInt(4, article_num);
		
		resultSet = pstmt.executeQuery();
		myClose(connection, pstmt);
	}

	public void Delete(int article_num) throws Exception {
		// TODO Auto-generated method stub
		connection = this.getConnection();

		StringBuffer query = new StringBuffer();
		query.append("delete from board where article_num=?");
		pstmt = connection.prepareStatement(query.toString());
		pstmt.setInt(1, article_num);
		System.out.println(query);

		resultSet = pstmt.executeQuery();
		myClose(connection, pstmt);

	}
	
	public void Reply(BoardVO article) throws Exception{
		// TODO Auto-generated method stub

		connection = this.getConnection();
		setPosForReply(article);
		

		
		StringBuffer query = new StringBuffer();
		query.append("insert into board values ");
		query.append("(board_seq.nextval,?,?,?,?,?,?,0,sysdate,?)");
		
		pstmt = connection.prepareStatement(query.toString());
		pstmt.setString(1, article.getId());
		pstmt.setString(2, article.getTitle());
		pstmt.setString(3, article.getContent());
		pstmt.setInt(4, article.getGroup_id());
		pstmt.setInt(5, article.getPos()+1);
		pstmt.setInt(6, article.getDepth()+1);
		pstmt.setString(7, article.getFile_name());
		
		
		pstmt.executeUpdate();
		myClose(connection, pstmt);
	}

	private void setPosForReply(BoardVO article) throws Exception{
		// TODO Auto-generated method stub

		StringBuffer query = new StringBuffer();
		query.append("update board ");
		query.append("set pos=pos+1  ");
		query.append("where group_id= ? and pos>? ");
		pstmt = connection.prepareStatement(query.toString());
		pstmt.setInt(1, article.getGroup_id());
		pstmt.setInt(2, article.getPos());
		pstmt.executeUpdate();
		
	}
	
	public BoardVO getArticle(int article_num) throws Exception {
		connection=this.getConnection();					
		String query ="select * from board where article_num=?";
		pstmt=connection.prepareStatement(query);
		pstmt.setInt(1,article_num);

		BoardVO article = new BoardVO();
		resultSet = pstmt.executeQuery();
		
		// 결과가 하나만 출력되므로 if를 사용함.
		if (resultSet.next()) {
			article = setBoardVO(article);	
			printBoardVO(article);
			
		}
		myClose(connection, pstmt);
		return article;
	}

	// -------------------------------개인 함수 -------------------------------
	// db를 연동하는 핵심메소드
	@Override
	public Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		connection = DriverManager.getConnection(url, "bbs", "1234");
		return connection;
	}

	private BoardVO setBoardVO(BoardVO boardVO) {
		// TODO Auto-generated method stub
		try {
			boardVO.setArticle_num(resultSet.getInt("Article_num"));
			boardVO.setId(resultSet.getString("id"));
			boardVO.setTitle(resultSet.getString("title"));
			boardVO.setContent(resultSet.getString("content"));
			boardVO.setGroup_id(resultSet.getInt("group_id"));
			boardVO.setPos(resultSet.getInt("pos"));
			boardVO.setDepth(resultSet.getInt("depth"));
			boardVO.setHit(resultSet.getInt("hit"));
			boardVO.setWrite_date(resultSet.getTimestamp("write_date"));
			boardVO.setFile_name(resultSet.getString("File_name"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardVO;
	}

	private void printBoardVO(BoardVO boardVO) {
		// TODO Auto-generated method stub
		System.out.println("----------" + TAG + "---------");
		System.out.println("getArticle_num : " + boardVO.getArticle_num());
		System.out.println("getId : " + boardVO.getId());
		System.out.println("getTitle : " + boardVO.getTitle());
		System.out.println("getContent : " + boardVO.getContent());
		System.out.print("getGroup_id : " + boardVO.getGroup_id());
		System.out.print("   getPos : " + boardVO.getPos());
		System.out.print("   getDepth : " + boardVO.getDepth());
		System.out.println("   getHit : " + boardVO.getHit());
		System.out.println("getWrite_date : " + boardVO.getWrite_date());
		System.out.println("getFile_name : " + boardVO.getFile_name());
		System.out.println();
		System.out.println();
	}

	private void myClose(Connection con, PreparedStatement pstmt)
			throws SQLException {
		con.close();
		pstmt.close();
	}

}
