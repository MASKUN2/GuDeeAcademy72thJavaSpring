package vo.member;

public class MemberLoginDto {
	private String memberId;
	private String memberPw;
	
	public MemberLoginDto() {
	}
	private MemberLoginDto(Builder builder) {
		this.memberId = builder.memberId;
		this.memberPw = builder.memberPw;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String meberId) {
		this.memberId = meberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
	@Override
	public String toString() {
		return "MemberCreateDto [meberId=" + memberId + ", memberPw=" + memberPw + "]";
	}
	
	//builder
	public static class Builder {
		private String memberId;
		private String memberPw;
		
		public MemberLoginDto build() {
			return new MemberLoginDto(this);
		}
		
		public Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
		public Builder memberPw(String memberPw) {
			this.memberPw = memberPw;
			return this;
		}
		
	}
}
