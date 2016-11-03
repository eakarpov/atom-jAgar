package zagar.network.packets;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import protocol.CommandEjectMass;
import zagar.util.JSONHelper;
import zagar.Game;

public class PacketEjectMass {
  @NotNull
  private static final Logger log = LogManager.getLogger(">>>");

  public PacketEjectMass() {
  }

  public void write() throws IOException {
    String msg = JSONHelper.toJSON(new CommandEjectMass());
    log.info("Sending [" + msg + "]");
    Game.socket.session.getRemote().sendString(msg);
  }
}
