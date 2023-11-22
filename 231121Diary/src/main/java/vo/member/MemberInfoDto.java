package vo.member;

public class MemberInfoDto {
	private int memberNo;
	private String memberId;
	private String memberPwNow;
	private String memberPwNew;
	private String memberPwNewCheck;
	
	private MemberInfoDto(int memberNo, String memberId, String memberPwNow, String memberPwNew,
			String memberPwNewCheck) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwNow = memberPwNow;
		this.memberPwNew = memberPwNew;
		this.memberPwNewCheck = memberPwNewCheck;
	}
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwNow() {
		return memberPwNow;
	}
	public void setMemberPwNow(String memberPwNow) {
		this.memberPwNow = memberPwNow;
	}
	public String getMemberPwNew() {
		return memberPwNew;
	}
	public void setMemberPwNew(String memberPwNew) {
		this.memberPwNew = memberPwNew;
	}
	public String getMemberPwNewCheck() {
		return memberPwNewCheck;
	}
	public void setMemberPwNewCheck(String memberPwNewCheck) {
		this.memberPwNewCheck = memberPwNewCheck;
	}
	@Override
	public String toString() {
		return "MemberInfoDto [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwNow=" + memberPwNow
				+ ", memberPwNew=" + memberPwNew + ", memberPwNewCheck=" + memberPwNewCheck + "]";
	}
	
	public static class builder{
		private int memberNo;
		private String memberId;
		private String memberPwNow;
		private String memberPwNew;
		private String memberPwNewCheck;
		
		public builder() {
		}		
		public MemberInfoDto build() {
			return new MemberInfoDto(this.memberNo, this.memberId, this.memberPwNow, this.memberPwNew, this.memberPwNewCheck);
		}
		
		public builder memberNo(int memberNo) {
			this.memberNo = memberNo;
			return this;
		}
		public builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
		public builder memberPwNow(String memberPwNow) {
			this.memberPwNow = memberPwNow;
			return this;
		}
		public builder memberPwNew(String memberPwNew) {
			this.memberPwNew = memberPwNew;
			return this;
		}
		public builder memberPwNewCheck(String memberPwNewCheck) {
			this.memberPwNewCheck = memberPwNewCheck;
			return this;
		}
	}
}
