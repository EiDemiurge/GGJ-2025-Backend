package ggj.demo;

import ggj.engine.logic.model.ModelChat;

public class Demo {
    public enum StdUsers {
        Bob(new ModelChat.ChatUser("Dobby")),
        Admin(new ModelChat.ChatUser("Adamin")),
        ;
        final ModelChat.ChatUser user;

        StdUsers(ModelChat.ChatUser user) {
            this.user = user;
        }

        public ModelChat.ChatUser get(){
                return user;
            }
    }
}
