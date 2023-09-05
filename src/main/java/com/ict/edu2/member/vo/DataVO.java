
package com.ict.edu2.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 백엔드의 결과를 담아서 프론트엔드에게 전달하는 vo이다.
public class DataVO{
    private boolean success;
    private Object data;
    private String message;
}