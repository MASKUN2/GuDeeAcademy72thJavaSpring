package vo.member;

public class MemberDeleteDto {
	private int memberNo;
	private String memberId;
	private String memberPwNow;
	private String memberPwNowCheck;
	
	private MemberDeleteDto(int memberNo, String memberId, String memberPwNow, String memberPwNowCheck) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwNow = memberPwNow;
		this.memberPwNowCheck = memberPwNowCheck;
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



	public String getMemberPwNowCheck() {
		return memberPwNowCheck;
	}



	public void setMemberPwNowCheck(String memberPwNowCheck) {
		this.memberPwNowCheck = memberPwNowCheck;
	}

	


	@Override
	public String toString() {
		return "MemberDeleteDto [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwNow=" + memberPwNow
				+ ", memberPwNowCheck=" + memberPwNowCheck + "]";
	}




	public static class builder{
		private int memberNo;
		private String memberId;
		private String memberPwNow;
		private String memberPwNowCheck;
		
		public builder() {
		}		
		public MemberDeleteDto build() {
			return new MemberDeleteDto(this.memberNo, this.memberId, this.memberPwNow, this.memberPwNowCheck);
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
		public builder memberPwNowCheck(String memberPwNowCheck) {
			this.memberPwNowCheck = memberPwNowCheck;
			return this;
		}
	}
}
