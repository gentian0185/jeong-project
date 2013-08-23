package hs.bbs.dao;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int article_num; // 글번호
	private String id; // 작성자
	private String title; // 제목
	private String content; // 글
	private int group_id; // 그룹
	private int pos; // 그룹간 등록순서
	private int depth; // 깊이
	private int hit; // 조회수
	private Timestamp write_date;
	private MultipartFile springfile_name;
	private String file_name;

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}

	public MultipartFile getSpringfile_name() {
		return springfile_name;
	}

	public void setSpringfile_name(MultipartFile springfile_name) {
		this.springfile_name = springfile_name;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	
}
