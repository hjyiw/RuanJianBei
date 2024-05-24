package com.hjy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractMsg {
    private MsgDTO userMsg;
    private MsgDTO assistantMsg;
}
