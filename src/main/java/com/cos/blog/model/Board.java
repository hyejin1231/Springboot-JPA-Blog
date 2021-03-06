package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 
	private String content; // 섬머노트 라이브러리 
	
//	@ColumnDefault("0")
	private int count; // 조회수 
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, One = User -> 한명의 유저는 여러개의 글을 작성할 수 있다..!
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없어 FK를 사용하지만 자바는 오브젝트를 저장할 수 없다..!
	
	// cascade = remove -> board게시판 제거할 때 reply 댓글도 모두 지움!
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다. 난 fk가 아니기 때문에 db에 칼럼을 만들지 마세요.
	@JsonIgnoreProperties({"board"}) // 무한 참조 방지 
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}

































