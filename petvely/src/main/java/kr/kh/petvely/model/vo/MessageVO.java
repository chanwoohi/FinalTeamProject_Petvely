package kr.kh.petvely.model.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageVO {
	private int mes_num;
	private String mes_content;
	private Date mes_date;
	private int mes_me_receiverNum;
	private int mes_me_senderNum;
	private String mes_readingCheck;
	private String mes_me_senderId;
	private String mes_me_receiverId;

}
