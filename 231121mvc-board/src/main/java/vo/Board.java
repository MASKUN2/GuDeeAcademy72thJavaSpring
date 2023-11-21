package vo;

import java.time.LocalDateTime;
//Board.class
public class Board {
	private Integer boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private LocalDateTime createdate;
	private LocalDateTime updatedate;
	
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public LocalDateTime getCreatedate() {
		return createdate;
	}
	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}
	public LocalDateTime getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}
	
}
