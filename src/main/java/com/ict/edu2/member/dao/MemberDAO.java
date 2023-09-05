package com.ict.edu2.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu2.member.vo.MemberVO;

@Service
public class MemberDAO {
    @Autowired
    private SqlSessionTemplate ss;

    public List<MemberVO> getList(){
        List<MemberVO> list = ss.selectList("members.list");
        return list;
    }

    public int getIDCnt(String m_id){
        int result = ss.selectOne("members.idcnt", m_id);
        return result;
    }

    public MemberVO  getMemberOne(String m_id){
        MemberVO mvo = ss.selectOne("members.memberone", m_id);
        return mvo;
    }

    public int  getMemberAdd(MemberVO vo){
         int result = ss.insert("members.insert", vo);
        return result;
    }

}
