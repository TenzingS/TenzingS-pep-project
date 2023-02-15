package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("register", this::registerHandler);
        app.post("login", this::userLoginHandler);
        app.post("messages", this::createMessagesHandler);
        app.get("messages", this::getMessagesHandler);
        app.get("messages/{message_id}", this::getMessageByIDHandler);
        app.delete("messages/{message_id}", this::deleteMessageByIDHandler);
        app.patch("messages/{message_id}", this::updateMessageByIDHandler);
        app.get("accounts/{account_id}/messages", this::getMessagesByAccountIDHandler);
        return app;
    }

    private void registerHandler(Context ctx) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account addAccount = accountService.addAccount(account);
        if(addAccount!=null){
            ctx.json(om.writeValueAsString(addAccount));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    private void userLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account userLogIn = accountService.logIn(account.getUsername(), account.getPassword());
        if(userLogIn != null){
            ctx.json(om.writeValueAsString(userLogIn));
        }else{
            ctx.status(401);
        }
    }

    private void createMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(ctx.body(), Message.class);
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null){
            ctx.json(om.writeValueAsString(createdMessage));
            ctx.status(200);
        }else {
            ctx.status(400);
        }
    }

    private void getMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getMessageByIDHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messagebyID = messageService.getMessageByID(message_id);
        if(messagebyID != null){
            ctx.result(om.writeValueAsString(messagebyID));
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    }

    private void deleteMessageByIDHandler(Context ctx) throws JsonProcessingException {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleteMessage = messageService.deleteMessageByID(message_id);
        if(deleteMessage != null){
            ctx.json(deleteMessage);
        }else{
            ctx.status(200);
        }
    }

    private void updateMessageByIDHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageByID(message, message_id);
        if(updatedMessage == null || updatedMessage.message_text.isBlank()){
            ctx.status(400);
        }else{
            ctx.json(updatedMessage);
        }
    }

    private void getMessagesByAccountIDHandler(Context ctx) throws JsonProcessingException {
        int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messageService.getMessagesByAccountID(posted_by);
        ctx.json(messages);
    }

}