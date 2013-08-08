package hs.bbs.list;
//쿼리문 작성.

/*
 * List.jsp의 하단의 pageview를 담당하는 클래스.
 * 	<생성자로 입력받음  defualt : int>
 * 		pageNum : 현재 페이지 넘버. 
 * 		count : 총 글의 개수.		
 * 		pageSize : 한화면에 보여줄 글 목록의 개수 
 * 		pageBlock : 한화면에 포여지는 PageNum의 수 
 * 
 *  <전/후 페이지 있는지 여부 defuat : boolean>
 * 		isPrevPage : 이전 페이지가 있는가?
 * 		isNextPage : 다음 페이지가 있는가?
 * 
 * 	<계산해야 하는 변수>
 * 		totalPage : 총 페이지의 개수
 * 		startPage : 보여지는 첫 페이지의 숫자.
 * 		endPage : 보여지는 끝 페이지의 숫자.
 * 
 *		(글번호 )
 * 		startRow : 첫번째로 보여지는 글의 pageNum
 * 		endRow : 마지막에 보여지는 글의 pageNum
 */
public class Page {
	private int pageNum;
	private int count;
	private int pageSize;
	private int pageBlock;
	private boolean isPrevPage;
	private boolean isNextPage;
	private int totalPage; 
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;
	private StringBuffer sb;

	public Page(int pageNum, int count, int pageSize, int pageBlock) {
		super();
		this.pageNum = pageNum;
		this.count = count;
		this.pageSize = pageSize;
		this.pageBlock = pageBlock;
		paging();
	}

	public boolean isPrevPage() {
		return isPrevPage; 
	}
	
	public boolean isVextPage() {
		return isNextPage;
	}		

	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}	
	public StringBuffer getSb() {
		return sb;
	}
	/*
	 * totalPage를 구한다. ( 총 개수 / 페이지당 게시글 수를 올림하여 구한다.)
	 * startRow = (현 페이지 - 1) * 페이지당 게시글 수 +1
	 * endRow = 현 페이지 * 페이지당 게시글 수
	 * startPage (1부터 10까지는 0)
	 * 			=((현재 페이지수 -1/페이지 블럭) * 페이지 블럭) +1
	 * endPage = startPage + pageBlock-1;
	 *
	 */
	public void paging(){
		totalPage = (int) Math.ceil((double) count / pageSize);
		startRow = (pageNum - 1) * pageSize+1;
		endRow = pageNum * pageSize;		
		startPage = (int)((pageNum-1) / pageBlock) * pageBlock + 1;
		endPage = startPage + pageBlock - 1;
				
		//현재 페이지가 18페이지면 ... (총 글이 185개일경우) 19페이지 20페이지가 업음.
		if(endPage > totalPage) {
			endPage = totalPage;
		}		
		if(startPage > 1) {
			isPrevPage = true;
		}		
		if(endPage < totalPage) {
			isNextPage = true;
		}		
		
		sb = new StringBuffer();
		// < 버튼 이벤트들을 나타냄.
		PrevPageTag();
		
		sb.append("&nbsp;|");
		
		// 페이지 숫자 지정에 대한 태그를 담당한다.	(시작과 끝.)		
		NumberPageTag();
		sb.append("&nbsp;|");
		
		// > 버튼 구현
		NextPageTag();	
	}
	private void NumberPageTag() {
		// TODO Auto-generated method stub
		for(int i = startPage; i <= endPage; i++) {		
			if(i == pageNum) {
				sb.append("&nbsp;&nbsp;<b><font color='#91B7EF'>");
				sb.append(i);
				sb.append("</font></b>");
			} else {
				sb.append("&nbsp;&nbsp;<a href='list.hs?" +
						"pageNum=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}
	}

	private void PrevPageTag() {
		// TODO Auto-generated method stub
		if(!isPrevPage) {
			sb.append("<img src='images/hot.gif'" +
					" width='30' height='9'>");			
		} else {
			//합쳐도 됨.
			sb.append("<img src='images/hot.gif'" +
					" width='30' height='9'");
			sb.append(" onclick='location.href=" +
					"\"list.hs?pageNum=");
			//상대적인 현재 페이지를 나타냄. 변수 pageNum 과는 다르니 헷갈리지 말자.
			sb.append(startPage - pageBlock);
			sb.append("\"' style='cursor:pointer'> ");
		}
	}
	
	private void NextPageTag(){
		if(isNextPage) {
			sb.append("<img src='images/hot.gif' width='30' height='9'");
			sb.append(" onclick='location.href=\"list.hs?pageNum=");
			sb.append(startPage + pageBlock);
			sb.append("\"' style='cursor:pointer'> ");						
		} else {
			sb.append("<img src='images/hot.gif' width='30' height='9'>");
		}
	}
}
