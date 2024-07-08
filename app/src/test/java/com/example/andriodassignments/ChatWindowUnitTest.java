package com.example.andriodassignments;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ChatWindowUnitTest {

    private Chat_window.ChatManager chatManager;

    @Before
    public void setUp() {
        chatManager = new Chat_window.ChatManager();
    }

    @Test
    public void testAddMessage() {
        String testMessage = "Hello, world!";
        chatManager.addMessage(testMessage);
        ArrayList<String> chatMessages = chatManager.getChatMessages();
        assertEquals(1, chatMessages.size());
        assertEquals(testMessage, chatMessages.get(0));
    }

    @Test
    public void testAddEmptyMessage() {
        chatManager.addMessage("");
        ArrayList<String> chatMessages = chatManager.getChatMessages();
        assertEquals(0, chatMessages.size());
    }

    @Test
    public void testAddNullMessage() {
        chatManager.addMessage(null);
        ArrayList<String> chatMessages = chatManager.getChatMessages();
        assertEquals(0, chatMessages.size());
    }
}
