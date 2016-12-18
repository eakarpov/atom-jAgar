package network.handlers;

import dao.DatabaseAccessLayer;
import main.ApplicationContext;
import mechanics.Mechanics;
import messagesystem.Abonent;
import messagesystem.Message;
import messagesystem.MessageSystem;
import model.Cell;
import model.Location;
import model.Player;
import network.ClientConnectionServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.jetbrains.annotations.NotNull;
import protocol.CommandEjectMass;
import model.Blob;
import utils.JSONDeserializationException;
import utils.JSONHelper;

import java.awt.*;

public class PacketHandlerEjectMass {

    private static final Logger LOG = LogManager.getLogger(PacketHandlerEjectMass.class);

    public PacketHandlerEjectMass(@NotNull Session session, @NotNull String json) {

        CommandEjectMass commandEjectMass;

        try {
            commandEjectMass = JSONHelper.fromJSON(json, CommandEjectMass.class);
        } catch (JSONDeserializationException e) {
            LOG.error("CommandEjectMass - JSONDeserializationException: " + e);
            return;
        }

        final MessageSystem messageSystem = ApplicationContext.instance().get(MessageSystem.class);
        final ClientConnectionServer client = messageSystem.getService(ClientConnectionServer.class);
        final Mechanics mechanicsService = messageSystem.getService(Mechanics.class);

        messageSystem.sendMessage(new Message(client.getAddress() , mechanicsService.getAddress()) {
            @Override
            public void exec(Abonent abonent) {
                LOG.info("Recieved command " + commandEjectMass.getCommand());
                String name = commandEjectMass.getName();
                mechanicsService.ejectMove(name);
            }
        });

    }
}
