package rip.alpha.libraries.board;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class BoardThread implements Runnable {
    private final BoardHandler boardHandler;

    @Override
    public void run() {
        try {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.willBeOnline()) {
                    Board board = this.boardHandler.getBoard(player);
                    if (board != null) {
                        String title = this.boardHandler.getBoardTitle(player);
                        List<String> lines = this.boardHandler.getBoardLines(player);
                        board.update(player, title, lines);
                        this.boardHandler.getProvider().onBoardUpdate(player, board);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
