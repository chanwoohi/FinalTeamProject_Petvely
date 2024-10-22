package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.MessageVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.service.MarketPostService;
import kr.kh.petvely.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    MarketPostService marketPostService;

    
    @GetMapping("/message/messagebox/{mesNum}")
    public String selectMessage(Model model, @PathVariable int mesNum) {
        List<MessageVO> messages = messageService.getMessageList(mesNum);
        model.addAttribute("messages", messages);
        return "message/messagebox";
    }

    @PostMapping("/message/send")
    public String messageSend(@RequestParam("mes_content") String content,
    		@RequestParam("receiverNum") String receiverId,
    		Model model) {

    	try {
    		Integer senderNum = 2;
    		//Integer senderNum = messageService.getsenderId(senderId); 
    		Integer receiverNum = messageService.getreceiverId(receiverId); 
    		MessageVO message = new	MessageVO();
    		message.setMes_me_senderNum(senderNum);
    		message.setMes_me_receiverNum(receiverNum);
    		message.setMes_content(content);
    		boolean res = messageService.sendMessage(message);
    		model.addAttribute("senderNum",senderNum);
    		model.addAttribute("receiverNum",receiverNum);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	

    	return "/message/send";
    }
    @GetMapping("/message/send/{senderNum}")
    public String messageSend( Model model, 
                               @PathVariable int senderNum) {
        List<MemberVO> senderId = messageService.getMemberIds(senderNum);
        model.addAttribute("senderId", senderId);
        return "message/send";
    }
    @GetMapping("/message/marketmessagesend/{po_num}")
    public String MarketMessage(Model model, @PathVariable int po_num) {
    	
    	PostVO post = messageService.getPostUserNum(po_num);
    	model.addAttribute("post",post);

        return "message/marketmessagesend"; 
    }
    
    @PostMapping("/message/marketmessagesend/{po_num}")
    public String MarketMessage_post(Model model, @PathVariable int po_num,String content) {
    	
    	PostVO post = messageService.getPostUserNum(po_num);
    	System.out.println(post);
    
    	Integer senderNum = 2;
    	int receiverNum = post.getPo_me_num();
    	
    	model.addAttribute("receiverNum",receiverNum);
    	model.addAttribute("senderNum",senderNum);

    	MessageVO message = new MessageVO();
    	message.setMes_me_receiverNum(receiverNum);
    	message.setMes_me_senderNum(senderNum);
    	message.setMes_content(content);
    	boolean res = messageService.MarketMessageSend(message);
    	System.out.println("content : "+content);
    	System.out.println("senderNum:"+senderNum);
    	System.out.println("receiverNum :" + receiverNum );
  
        return "redirect:/post/market"; 
    }
    

}
