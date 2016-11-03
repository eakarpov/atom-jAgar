package server.session;

import model.GameConstants;
import model.Player;
import org.jetbrains.annotations.NotNull;
import server.entities.user.User;

/**
 * Single agar.io game session
 * <p>Single game session take place in square map, where players battle for food
 * <p>Max {@link GameConstants#MAX_PLAYERS_IN_SESSION} players can play within single game session
 *
 * @author Alpi
 */
public interface GameSession {
  /**
   * Player can join session whenever there are less then {@link GameConstants#MAX_PLAYERS_IN_SESSION} players within game session
   *
   * @param user player to join the game
   */
  void join(@NotNull User user);
}
