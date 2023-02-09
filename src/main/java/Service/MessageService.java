package Service;

import Model.Message;

import java.util.List;

import DAO.MessageDAO;

public class MessageService {
    private MessageDAO messageDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID(int message_id) {
        return messageDAO.deleteMessageByID(message_id);
    }

    public Message updateMessageByID(int message_id) {
        return messageDAO.deleteMessageByID(message_id);
    }
    
}
