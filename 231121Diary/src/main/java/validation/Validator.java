package validation;

import java.util.function.Function;
import java.util.regex.Pattern;

import dao.member.MemberDao;
import vo.member.MemberCreateDto;

public class Validator {
	private final String REGEX_MEMBER_ID = "^[a-zA-Z0-9]{3,20}$";
	
	public <T> ValidationReport validate(T targetOject, Function<T, ValidationReport> function) {
		return function.apply(targetOject);
	}
	
	public ValidationReport validateMemberCreation(MemberCreateDto dto) {
		StringBuilder log = new StringBuilder();
		Boolean isPass = true;
		
		MemberDao dao = new MemberDao();
		boolean isUniqueId = dao.isUniqueId(dto);
		if(!isUniqueId) {
			log.append("아이디가 중복되었습니다.");
			isPass = false;
		}
		boolean isOkId = Pattern.matches(REGEX_MEMBER_ID, dto.getMemberId());
		if(!isOkId) {
			log.append("ID는 3자이상 20자 이하 알파벳 또는 숫자만 가능합니다.");
			isPass = false;
		}
		return new ValidationReport(isPass, log.toString());
		
	}

}
