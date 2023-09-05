package com.ict.edu2.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu2.member.dao.MemberDAO;
import com.ict.edu2.member.vo.DataVO;
import com.ict.edu2.member.vo.MemberVO;


@RestController
@RequestMapping("/member")
public class MyController {

    @Autowired 
    private MemberDAO memberDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String Hello(){
        return "Hello World";
    }
    


    @PostMapping("/login")
    public Map<String, Object> logIn(MemberVO vo, HttpSession session){
        Map<String, Object> resMap = new HashMap<>();
        DataVO dataVO = new DataVO();

        //입력받은 아이디가 존재하는지 검사
        int cnt = memberDAO.getIDCnt(vo.getM_id());
        if(cnt <=0 ){
            dataVO.setSuccess(false);
            dataVO.setMessage("아이디가 존재하지 않습니다.");
            
        }else{
            //입력받은 아이디를 이용해서 DB패스워드를 구하자
            MemberVO mvo = memberDAO.getMemberOne(vo.getM_id());
            //가지고 온 패스워드와 입력된  패스워드가 같은지 판별하자
            if(! passwordEncoder.matches(vo.getM_pw(), mvo.getM_pw())){
                dataVO.setSuccess(false);
                dataVO.setMessage("비밀번호가 틀립니다.");
                resMap.put("data", dataVO);
                return resMap;
            }else{
                //로그인 정보 저장(세션 )
                session.setAttribute("mvo", mvo);
                dataVO.setSuccess(true);
                dataVO.setMessage("로그인 성공");
                //프론트엔드에서 사용하기 위해서 저장
                dataVO.setData(mvo);
                 resMap.put("data", dataVO);
                return resMap;
            }
        }
        return resMap;
    }




    @PostMapping("/join")
    @ResponseBody
    public Map<String, Object> join(MemberVO vo){
         Map<String, Object> resMap = new HashMap<>();
         DataVO dataVO = new DataVO();

        // 패스워드 암호화를 해야된다.
         vo.setM_pw(passwordEncoder.encode(vo.getM_pw()));
         int result = memberDAO.getMemberAdd(vo);
          if(result >0 ){
            dataVO.setSuccess(true);
            dataVO.setMessage("회원가입 성공.");
          }else{
             dataVO.setSuccess(false);
            dataVO.setMessage("회원가입 실패.");
          }
            resMap.put("data", dataVO);
            return resMap;
          }
       

    @GetMapping("/list")
    public Map<String,Object> getList(){
        List<MemberVO> list = memberDAO.getList();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list",list);
        return resMap;
    }
}