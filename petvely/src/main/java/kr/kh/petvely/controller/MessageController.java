package kr.kh.petvely.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.petvely.model.user.CustomUser;
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

    
    @GetMapping("/message/messagebox")
    public String selectMessage(Model model,@AuthenticationPrincipal CustomUser customUser ) {
    	    if (customUser != null) {
    	        MemberVO user = customUser.getMember();
    	        int me_num = user.getMe_num();
    	       
    	        List<MessageVO> messages = messageService.getMessageList(me_num);
    	       
    	        model.addAttribute("messages", messages);
    	        LocalDateTime now = LocalDateTime.now();
    	        for(MessageVO message : messages) {
    	        	LocalDateTime messageDate = message.getMes_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    	        	if(messageDate.plusDays(7).isBefore(now)) {
    	        		messageService.deleteMessage(message.getMes_num());
    	        	}
    	        	
    	        }
    	        
    	    } 
        return "message/messagebox";
    }

    @PostMapping("/message/send")
    public String messageSend(@RequestParam("mes_content") String content,
    		@RequestParam("receiverNum") String receiverId,
    		Model model,
    		@AuthenticationPrincipal CustomUser CustomUser) {

    	try {
    		if(CustomUser != null) {
    			MemberVO user = CustomUser.getMember();
    			int senderNum = user.getMe_num();
    			Integer receiverNum = messageService.getreceiverId(receiverId); 
    			
    			if(senderNum == receiverNum) {
    				model.addAttribute("msg","자신에게는 보낼 수 없습니다.");
    				
    				return "message/messagebox";
    			}
    			MessageVO message = new	MessageVO();
    			message.setMes_me_senderNum(senderNum);
    			message.setMes_me_receiverNum(receiverNum);
    			message.setMes_content(content);
    			message.setMes_date(new Date());
    			
    			boolean res = messageService.sendMessage(message);
    			if(res) {
    				model.addAttribute("senderNum",senderNum);
    				model.addAttribute("receiverNum",receiverNum);
    				model.addAttribute("msg","쪽지보내기 성공");
    				return "message/messagebox";
    			}
    			
    			
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	

    	return "redirect:/message/messagebox";
    }
    @GetMapping("/message/send")
    public String messageSend(Model model, @AuthenticationPrincipal CustomUser customUser) {
        if (customUser != null) {
            MemberVO user = customUser.getMember();
            int senderNum = user.getMe_num();
            

            List<MemberVO> senderId = messageService.getMemberIds(senderNum);
            model.addAttribute("senderId", senderId);
            model.addAttribute("senderNum", senderNum);
        } else {
          
            return "redirect:/login";
        }
        
        return "message/send";
    }
    @GetMapping("/message/marketmessagesend/{po_num}")
    public String MarketMessage(Model model, @PathVariable int po_num) {
    	
    	PostVO post = messageService.getPostUserNum(po_num);
    	model.addAttribute("post",post);

        return "message/marketmessagesend"; 
    }
    
    @PostMapping("/message/marketmessagesend/{po_num}")
    public String MarketMessage_post(Model model, @PathVariable int po_num,String content,
    								@AuthenticationPrincipal CustomUser customUser) {
    	
    	if(customUser != null) {
    		MemberVO user = customUser.getMember();
    		int senderNum = user.getMe_num();
    		PostVO post = messageService.getPostUserNum(po_num);
    		int receiverNum = post.getPo_me_num();
    		
    		if(senderNum == receiverNum) {
				model.addAttribute("msg","자신에게는 보낼 수 없습니다.");
				
				 return "message/messagebox";
			}
    		
    		
    		model.addAttribute("receiverNum",receiverNum);
    		model.addAttribute("senderNum",senderNum);
    		
    		MessageVO message = new MessageVO();
    		message.setMes_me_receiverNum(receiverNum);
    		message.setMes_me_senderNum(senderNum);
    		message.setMes_content(content);
    		message.setMes_date(new Date());
    		boolean res = messageService.MarketMessageSend(message);
    		
    	}
    	
  
        return "redirect:/post/market/11"; 
    }
    @GetMapping("/message/messagedetail/{mes_num}")
    public String messageDetail(Model model,@AuthenticationPrincipal CustomUser customUser,
    							@PathVariable int mes_num) {
    		try {
    			if (customUser != null) {
    				MemberVO user = customUser.getMember();
    				MessageVO message = messageService.getMessageDetail(mes_num);
    				model.addAttribute("message",message);
    				
    				
    			}
    			
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
        return "/message/messagedetail";
    
    }
}
