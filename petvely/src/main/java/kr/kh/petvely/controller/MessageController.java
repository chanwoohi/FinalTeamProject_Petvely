package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.petvely.model.vo.MessageVO;
import kr.kh.petvely.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    
    @GetMapping("/message/messagebox/{mesNum}")
    public String selectMessage(Model model, @PathVariable int mesNum) {
        List<MessageVO> messages = messageService.getMessageList(mesNum);
        model.addAttribute("messages", messages);
        return "message/messagebox";
    }
    
    @PostMapping("/message/send/{senderNum}/{receiverNum}")
    public String messageSend(@RequestParam("mes_content") String content,
                              @PathVariable("receiverNum") int receiverNum,
                              @PathVariable("senderNum") int senderNum,
                              Model model) {
        MessageVO message = new MessageVO();
        message.setMes_content(content);
        message.setMes_me_receiverNum(receiverNum);
        message.setMes_me_senderNum(senderNum);
        
        boolean res = messageService.sendMessage(message);
        if(res) {
            model.addAttribute("message", "쪽지 보내기 성공");
        } else {
            model.addAttribute("message", "쪽지 보내기 실패");
        }
        return "message/send";
    }
   
 
}