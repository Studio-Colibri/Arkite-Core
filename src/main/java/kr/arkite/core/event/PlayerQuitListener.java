/*
 * [경로] kr.arkite.core.event.PlayerQuitListener
 *
 * [역할] 플레이어 퇴장 시 처리(세션 해제/저장 등).
 * [설계 근거] Bukkit Listener, Quit 이벤트별 분리.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.event;

import kr.arkite.core.ArkiteCore;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;

public class PlayerQuitListener implements Listener {
    // PlayerQuitListener
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ArkiteCore core = (ArkiteCore) Bukkit.getPluginManager().getPlugin("Arkite-Core");
        core.getPlayerDataManager().handleQuit(player);
    }
}
