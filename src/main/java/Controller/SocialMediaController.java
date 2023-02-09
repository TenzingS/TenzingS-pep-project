package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.security.interfaces.RSAPrivateCrtKey;
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

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);
        app.post("messages", this::messagesHandler);
        app.get("messages", this::getMessagesHandler);
        app.get("messages/{message_id}", this::getMessageByIDHandler);
        app.delete("messages/{message_id}", this::deleteMessageByIDHandler);
        app.patch("messages/{message_id}", this::updateMessageByIDHandler);
        app.get("accounts/{account_id}/messages", this::getMessagesByAccountIDHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context ctx) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account addAccount = accountService.addAccount(account);
        if(addAccount!=null){
            ctx.json(om.writeValueAsString(addAccount));
        }else{
            ctx.status(400);
        }
    }

    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        String username = account.getUsername();
        String password = account.getPassword();
        Account userLogIn = accountService.logIn(username, password);
        if(userLogIn.username==null || userLogIn.password==null){
            ctx.status(401);
        }else{
            ctx.json(om.writeValueAsString(userLogIn));
        }
    }

    private void messagesHandler(Context ctx) {
        String jsonString = ctx.body();

        ObjectMapper om = new ObjectMapper();
    }

    private void getMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getMessageByIDHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messagebyID = messageService.getMessageByID(message_id);
        System.out.println(messagebyID);
        if(messagebyID == null){
            ctx.status(200);
        }else{
            ctx.json(mapper.writeValueAsString(messagebyID));
        }
    }

    private void deleteMessageByIDHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleteMessage = messageService.deleteMessageByID(message_id);
        System.out.println(deleteMessage);
        if(deleteMessage == null){
            ctx.status(200);
        }else{
            ctx.json(mapper.writeValueAsString(deleteMessage));
        }
    }

    private void updateMessageByIDHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updateMessage = messageService.updateMessageByID(message_id);
        System.out.println(updateMessage);
        if(updateMessage == null){
            ctx.status(200);
        }else{
            ctx.json(mapper.writeValueAsString(updateMessage));
        }
    }

    private void getMessagesByAccountIDHandler(Context ctx) {
        List<Message> messages = 
    }


}