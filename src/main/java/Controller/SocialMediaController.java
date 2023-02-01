package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
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
        app.get("messages/{message_id}", this::getMessagesByIDHandler);
        app.delete("messages/{message_id}", this::deleteMessagesByIDHandler);
        app.patch("messages/{message_id}", this::updateMessagesByIDHandler);
        app.get("accounts/{account_id}/messages", this::getMessagesByAccountIDHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context ctx) {
        String jsonString = ctx.body();

        ObjectMapper om = new ObjectMapper();
    }

    private void loginHandler(Context ctx) {
        String jsonString = ctx.body();

        ObjectMapper om = new ObjectMapper();
    }

    private void messagesHandler(Context ctx) {
        String jsonString = ctx.body();

        ObjectMapper om = new ObjectMapper();
    }

    private void getMessagesHandler(Context ctx) {
        ctx.json("sample text");
    }

    private void getMessagesByIDHandler(Context ctx) {
        ctx.json("sample text");
    }

    private void deleteMessagesByIDHandler(Context ctx) {
        ctx.json("sample text");
    }

    private void updateMessagesByIDHandler(Context ctx) {
        ctx.json("sample text");
    }

    private void getMessagesByAccountIDHandler(Context ctx) {
        ctx.json("sample text");
    }


}