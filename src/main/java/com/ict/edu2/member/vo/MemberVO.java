package com.ict.edu2.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@RequiredArgsConstructore : final 이나 @NonNull인 필드값만 피라미드로 받는 생성자


@Data
// 인자가 없는 기본생성자 자동생성
@NoArgsConstructor
// 모든 인자가 들어있는 생섣ㅇ자 자동 생성
@AllArgsConstructor
public class MemberVO {
    private String m_id, m_pw, m_name, m_age,m_reg;
}
