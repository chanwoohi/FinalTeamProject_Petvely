package kr.kh.petvely.controller;

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
    public String selectMessage(Model model,@AuthenticationPrincipal CustomUser CustomUser ) {
    	    if (CustomUser != null) {
    	        MemberVO user = CustomUser.getMember();
    	        int me_num = user.getMe_num();
    	       
    	        List<MessageVO> messages = messageService.getMessageList(me_num);
    	        System.out.println(messages);
    	        System.out.println(me_num);
    	        model.addAttribute("messages", messages);
    	    } else {

    	        return "redirect:/login"; 
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
    			MessageVO message = new	MessageVO();
    			message.setMes_me_senderNum(senderNum);
    			message.setMes_me_receiverNum(receiverNum);
    			message.setMes_content(content);
    			boolean res = messageService.sendMessage(message);
    			model.addAttribute("senderNum",senderNum);
    			model.addAttribute("receiverNum",receiverNum);
    			
    			
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
    		
    		
    		
    		model.addAttribute("receiverNum",receiverNum);
    		model.addAttribute("senderNum",senderNum);
    		
    		MessageVO message = new MessageVO();
    		message.setMes_me_receiverNum(receiverNum);
    		message.setMes_me_senderNum(senderNum);
    		message.setMes_content(content);
    		boolean res = messageService.MarketMessageSend(message);
    		
    	}else {
    		return "redirect:/login";
    	}
    	
  
        return "redirect:/post/market"; 
    }
    @GetMapping("/message/messagedetail/{mes_num}")
    public String messageDetail(Model model,@AuthenticationPrincipal CustomUser CustomUser,
    							@PathVariable int mes_num) {
    		try {
    			if (CustomUser != null) {
    				MemberVO user = CustomUser.getMember();
    				MessageVO message = messageService.getMessageDetail(mes_num);
    				model.addAttribute("message",message);
    				System.out.println(user);
    				System.out.println("mes_num :"+mes_num);
    				System.out.println(message);
    				
    			}
    			
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
        return "/message/messagedetail";
    
    }
}
