package network.handlers;

import org.eclipse.jetty.websocket.api.Session;
import org.jetbrains.annotations.NotNull;
import protocol.CommandEjectMass;
import utils.JSONDeserializationException;
import utils.JSONHelper;

public class PacketHandlerEjectMass {

    public PacketHandlerEjectMass(@NotNull Session session, @NotNull String json) {
        CommandEjectMass commandEjectMass;
        try {
            commandEjectMass = JSONHelper.fromJSON(json, CommandEjectMass.class);
        } catch (JSONDeserializationException e) {
            e.printStackTrace();
            return;
        }
        //TODO
    }
}
