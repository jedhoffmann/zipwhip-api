package com.zipwhip.api.response;

import com.zipwhip.api.dto.*;
import com.zipwhip.signals.presence.Presence;
import com.zipwhip.util.Parser;

import java.util.List;

/**
 * Date: Jul 18, 2009
 * Time: 10:22:28 AM
 * <p/>
 * Will parse out objects from a specific data format. Currently the only supported format is JSON.
 */
public interface ResponseParser extends Parser<String, ServerResponse> {

    Message parseMessage(ServerResponse serverResponse) throws Exception;

    List<Message> parseMessages(ServerResponse serverResponse) throws Exception;

    String parseString(ServerResponse serverResponse) throws Exception;

    Contact parseContact(ServerResponse serverResponse) throws Exception;

    Contact parseUser(ServerResponse serverResponse) throws Exception;

    List<Contact> parseContacts(ServerResponse serverResponse) throws Exception;

    Conversation parseConversation(ServerResponse serverResponse) throws Exception;

    List<Conversation> parseConversations(ServerResponse serverResponse) throws Exception;

    DeviceToken parseDeviceToken(ServerResponse serverResponse) throws Exception;

    List<MessageToken> parseMessageTokens(ServerResponse serverResponse) throws Exception;

    List<Presence> parsePresence(ServerResponse serverResponse) throws Exception;

    EnrollmentResult parseEnrollmentResult(ServerResponse serverResponse) throws Exception;

}
