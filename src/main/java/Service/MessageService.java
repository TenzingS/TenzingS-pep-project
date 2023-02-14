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

    public Message createMessage(Message message){
        String message_text = message.getMessage_text();
        int posted_by = message.getPosted_by();

        
        if(message_text!= "" && message_text.length() < 255){
            return messageDAO.createMessage(message);
        }
        return null;
    }

    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID(Message message, int message_id) {
        Message messageFromDB = this.messageDAO.getMessageByID(message_id);

        if(messageFromDB == null) return null;
        messageDAO.deleteMessageByID(message, message_id);
        return this.messageDAO.getMessageByID(message_id);
    }

    public Message updateMessageByID(Message message, int message_id) {
        Message messageFromDB = this.messageDAO.getMessageByID(message_id);

        if(messageFromDB == null) return null;
        messageDAO.updateMessageByID(message, message_id);
        return this.messageDAO.getMessageByID(message_id);
    }

    // public List<Message> getMessagesByAccountID(Account account, int account_id) {
        //login first and then get the messages
    //     return messageDAO.getMessagesByAccountID(account_id);
    // }
    
}
